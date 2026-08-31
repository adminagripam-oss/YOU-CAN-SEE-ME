package io.flutter.embedding.engine.renderer;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.SyncFence;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.w;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.view.TextureRegistry;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public class FlutterRenderer implements TextureRegistry {

    /* renamed from: i  reason: collision with root package name */
    public static boolean f7786i = false;

    /* renamed from: j  reason: collision with root package name */
    public static boolean f7787j = false;

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f7788a;

    /* renamed from: c  reason: collision with root package name */
    private Surface f7790c;

    /* renamed from: h  reason: collision with root package name */
    private final l f7795h;

    /* renamed from: b  reason: collision with root package name */
    private final AtomicLong f7789b = new AtomicLong(0);

    /* renamed from: d  reason: collision with root package name */
    private boolean f7791d = false;

    /* renamed from: e  reason: collision with root package name */
    private final Handler f7792e = new Handler();

    /* renamed from: f  reason: collision with root package name */
    private final Set f7793f = new HashSet();

    /* renamed from: g  reason: collision with root package name */
    private final List f7794g = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class ImageReaderSurfaceProducer implements TextureRegistry.SurfaceProducer, TextureRegistry.ImageConsumer, TextureRegistry.b {
        private static final boolean CLEANUP_ON_MEMORY_PRESSURE = true;
        private static final int MAX_IMAGES = 5;
        private static final String TAG = "ImageReaderSurfaceProducer";
        private static final boolean VERBOSE_LOGS = false;
        private static final boolean trimOnMemoryPressure = true;
        private final long id;
        private boolean released;
        private boolean ignoringFence = VERBOSE_LOGS;
        private int requestedWidth = 1;
        private int requestedHeight = 1;
        private boolean createNewReader = true;
        private long lastDequeueTime = 0;
        private long lastQueueTime = 0;
        private long lastScheduleTime = 0;
        private int numTrims = 0;
        private final Object lock = new Object();
        private final ArrayDeque<b> imageReaderQueue = new ArrayDeque<>();
        private final HashMap<ImageReader, b> perImageReaders = new HashMap<>();
        private a lastDequeuedImage = null;
        private b lastReaderDequeuedFrom = null;
        private TextureRegistry.SurfaceProducer.a callback = null;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class a {

            /* renamed from: a  reason: collision with root package name */
            public final Image f7796a;

            /* renamed from: b  reason: collision with root package name */
            public final long f7797b;

            public a(Image image, long j8) {
                this.f7796a = image;
                this.f7797b = j8;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class b {

            /* renamed from: a  reason: collision with root package name */
            public final ImageReader f7799a;

            /* renamed from: b  reason: collision with root package name */
            private final ArrayDeque f7800b = new ArrayDeque();

            /* renamed from: c  reason: collision with root package name */
            private boolean f7801c = ImageReaderSurfaceProducer.VERBOSE_LOGS;

            public b(ImageReader imageReader) {
                this.f7799a = imageReader;
                imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: io.flutter.embedding.engine.renderer.i
                    @Override // android.media.ImageReader.OnImageAvailableListener
                    public final void onImageAvailable(ImageReader imageReader2) {
                        FlutterRenderer.ImageReaderSurfaceProducer.b.this.f(imageReader2);
                    }
                }, new Handler(Looper.getMainLooper()));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void f(ImageReader imageReader) {
                Image image;
                try {
                    image = imageReader.acquireLatestImage();
                } catch (IllegalStateException e8) {
                    z5.b.b(ImageReaderSurfaceProducer.TAG, "onImageAvailable acquireLatestImage failed: " + e8);
                    image = null;
                }
                if (image == null) {
                    return;
                }
                if (ImageReaderSurfaceProducer.this.released || this.f7801c) {
                    image.close();
                } else {
                    ImageReaderSurfaceProducer.this.onImage(imageReader, image);
                }
            }

            boolean c() {
                if (!this.f7800b.isEmpty() || ImageReaderSurfaceProducer.this.lastReaderDequeuedFrom == this) {
                    return ImageReaderSurfaceProducer.VERBOSE_LOGS;
                }
                return true;
            }

            void d() {
                this.f7801c = true;
                this.f7799a.close();
                this.f7800b.clear();
            }

            a e() {
                if (this.f7800b.isEmpty()) {
                    return null;
                }
                return (a) this.f7800b.removeFirst();
            }

            a g(Image image) {
                if (this.f7801c) {
                    return null;
                }
                a aVar = new a(image, System.nanoTime());
                this.f7800b.add(aVar);
                while (this.f7800b.size() > 2) {
                    ((a) this.f7800b.removeFirst()).f7796a.close();
                }
                return aVar;
            }
        }

        ImageReaderSurfaceProducer(long j8) {
            this.id = j8;
        }

        private void cleanup() {
            synchronized (this.lock) {
                for (b bVar : this.perImageReaders.values()) {
                    if (this.lastReaderDequeuedFrom == bVar) {
                        this.lastReaderDequeuedFrom = null;
                    }
                    bVar.d();
                }
                this.perImageReaders.clear();
                a aVar = this.lastDequeuedImage;
                if (aVar != null) {
                    aVar.f7796a.close();
                    this.lastDequeuedImage = null;
                }
                b bVar2 = this.lastReaderDequeuedFrom;
                if (bVar2 != null) {
                    bVar2.d();
                    this.lastReaderDequeuedFrom = null;
                }
                this.imageReaderQueue.clear();
            }
        }

        private ImageReader createImageReader() {
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 33) {
                return createImageReader33();
            }
            if (i8 >= 29) {
                return createImageReader29();
            }
            throw new UnsupportedOperationException("ImageReaderPlatformViewRenderTarget requires API version 29+");
        }

        private ImageReader createImageReader29() {
            ImageReader newInstance;
            newInstance = ImageReader.newInstance(this.requestedWidth, this.requestedHeight, 34, 5, 256L);
            return newInstance;
        }

        private ImageReader createImageReader33() {
            ImageReader build;
            io.flutter.embedding.engine.renderer.h.a();
            ImageReader.Builder a8 = io.flutter.embedding.engine.renderer.g.a(this.requestedWidth, this.requestedHeight);
            a8.setMaxImages(5);
            a8.setImageFormat(34);
            a8.setUsage(256L);
            build = a8.build();
            return build;
        }

        private b getActiveReader() {
            synchronized (this.lock) {
                if (!this.createNewReader) {
                    return this.imageReaderQueue.peekLast();
                }
                this.createNewReader = VERBOSE_LOGS;
                return getOrCreatePerImageReader(createImageReader());
            }
        }

        private void maybeWaitOnFence(Image image) {
            if (image == null || this.ignoringFence) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 33) {
                waitOnFence(image);
                return;
            }
            this.ignoringFence = true;
            z5.b.a(TAG, "ImageTextureEntry can't wait on the fence on Android < 33");
        }

        private void releaseInternal() {
            cleanup();
            this.released = true;
            FlutterRenderer.this.f7794g.remove(this);
        }

        private void waitOnFence(Image image) {
            SyncFence fence;
            try {
                fence = image.getFence();
                fence.awaitForever();
            } catch (IOException unused) {
            }
        }

        @Override // io.flutter.view.TextureRegistry.ImageConsumer
        public Image acquireLatestImage() {
            a dequeueImage = dequeueImage();
            if (dequeueImage == null) {
                return null;
            }
            maybeWaitOnFence(dequeueImage.f7796a);
            return dequeueImage.f7796a;
        }

        double deltaMillis(long j8) {
            return j8 / 1000000.0d;
        }

        a dequeueImage() {
            a aVar;
            synchronized (this.lock) {
                Iterator<b> it = this.imageReaderQueue.iterator();
                aVar = null;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    b next = it.next();
                    a e8 = next.e();
                    if (e8 == null) {
                        aVar = e8;
                    } else {
                        a aVar2 = this.lastDequeuedImage;
                        if (aVar2 != null) {
                            aVar2.f7796a.close();
                        }
                        this.lastDequeuedImage = e8;
                        this.lastReaderDequeuedFrom = next;
                        aVar = e8;
                    }
                }
                pruneImageReaderQueue();
            }
            return aVar;
        }

        public void disableFenceForTest() {
            this.ignoringFence = true;
        }

        protected void finalize() {
            try {
                if (this.released) {
                    return;
                }
                releaseInternal();
                FlutterRenderer.this.f7792e.post(new g(this.id, FlutterRenderer.this.f7788a));
            } finally {
                super.finalize();
            }
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceProducer
        public int getHeight() {
            return this.requestedHeight;
        }

        b getOrCreatePerImageReader(ImageReader imageReader) {
            b bVar = this.perImageReaders.get(imageReader);
            if (bVar == null) {
                b bVar2 = new b(imageReader);
                this.perImageReaders.put(imageReader, bVar2);
                this.imageReaderQueue.add(bVar2);
                return bVar2;
            }
            return bVar;
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceProducer
        public Surface getSurface() {
            return getActiveReader().f7799a.getSurface();
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceProducer
        public int getWidth() {
            return this.requestedWidth;
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceProducer
        public long id() {
            return this.id;
        }

        public int numImageReaders() {
            int size;
            synchronized (this.lock) {
                size = this.imageReaderQueue.size();
            }
            return size;
        }

        public int numImages() {
            int i8;
            synchronized (this.lock) {
                Iterator<b> it = this.imageReaderQueue.iterator();
                i8 = 0;
                while (it.hasNext()) {
                    i8 += it.next().f7800b.size();
                }
            }
            return i8;
        }

        public int numTrims() {
            int i8;
            synchronized (this.lock) {
                i8 = this.numTrims;
            }
            return i8;
        }

        void onImage(ImageReader imageReader, Image image) {
            a g8;
            synchronized (this.lock) {
                g8 = getOrCreatePerImageReader(imageReader).g(image);
            }
            if (g8 == null) {
                return;
            }
            FlutterRenderer.this.w();
        }

        @Override // io.flutter.view.TextureRegistry.b
        public void onTrimMemory(int i8) {
            if (i8 < 40) {
                return;
            }
            synchronized (this.lock) {
                this.numTrims++;
            }
            cleanup();
            this.createNewReader = true;
            TextureRegistry.SurfaceProducer.a aVar = this.callback;
            if (aVar != null) {
                aVar.b();
            }
        }

        void pruneImageReaderQueue() {
            b peekFirst;
            while (this.imageReaderQueue.size() > 1 && (peekFirst = this.imageReaderQueue.peekFirst()) != null && peekFirst.c()) {
                this.imageReaderQueue.removeFirst();
                this.perImageReaders.remove(peekFirst.f7799a);
                peekFirst.d();
            }
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceProducer
        public void release() {
            if (this.released) {
                return;
            }
            releaseInternal();
            FlutterRenderer.this.D(this.id);
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceProducer
        public void scheduleFrame() {
            FlutterRenderer.this.w();
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceProducer
        public void setCallback(TextureRegistry.SurfaceProducer.a aVar) {
            this.callback = aVar;
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceProducer
        public void setSize(int i8, int i9) {
            int max = Math.max(1, i8);
            int max2 = Math.max(1, i9);
            if (this.requestedWidth == max && this.requestedHeight == max2) {
                return;
            }
            this.createNewReader = true;
            this.requestedHeight = max2;
            this.requestedWidth = max;
        }
    }

    /* loaded from: classes.dex */
    final class ImageTextureRegistryEntry implements TextureRegistry.ImageTextureEntry, TextureRegistry.ImageConsumer {
        private static final String TAG = "ImageTextureRegistryEntry";
        private final long id;
        private boolean ignoringFence = false;
        private Image image;
        private boolean released;

        ImageTextureRegistryEntry(long j8) {
            this.id = j8;
        }

        private void maybeWaitOnFence(Image image) {
            if (image == null || this.ignoringFence) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 33) {
                waitOnFence(image);
                return;
            }
            this.ignoringFence = true;
            z5.b.a(TAG, "ImageTextureEntry can't wait on the fence on Android < 33");
        }

        private void waitOnFence(Image image) {
            SyncFence fence;
            try {
                fence = image.getFence();
                fence.awaitForever();
            } catch (IOException unused) {
            }
        }

        @Override // io.flutter.view.TextureRegistry.ImageConsumer
        public Image acquireLatestImage() {
            Image image;
            synchronized (this) {
                image = this.image;
                this.image = null;
            }
            maybeWaitOnFence(image);
            return image;
        }

        protected void finalize() {
            try {
                if (this.released) {
                    return;
                }
                Image image = this.image;
                if (image != null) {
                    image.close();
                    this.image = null;
                }
                this.released = true;
                FlutterRenderer.this.f7792e.post(new g(this.id, FlutterRenderer.this.f7788a));
            } finally {
                super.finalize();
            }
        }

        @Override // io.flutter.view.TextureRegistry.ImageTextureEntry
        public long id() {
            return this.id;
        }

        @Override // io.flutter.view.TextureRegistry.ImageTextureEntry
        public void pushImage(Image image) {
            Image image2;
            if (this.released) {
                return;
            }
            synchronized (this) {
                image2 = this.image;
                this.image = image;
            }
            if (image2 != null) {
                z5.b.b(TAG, "Dropping PlatformView Frame");
                image2.close();
            }
            if (image != null) {
                FlutterRenderer.this.w();
            }
        }

        @Override // io.flutter.view.TextureRegistry.ImageTextureEntry
        public void release() {
            if (this.released) {
                return;
            }
            this.released = true;
            Image image = this.image;
            if (image != null) {
                image.close();
                this.image = null;
            }
            FlutterRenderer.this.D(this.id);
        }
    }

    /* loaded from: classes.dex */
    class a implements l {
        a() {
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void b() {
            FlutterRenderer.this.f7791d = false;
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void d() {
            FlutterRenderer.this.f7791d = true;
        }
    }

    /* loaded from: classes.dex */
    class b implements DefaultLifecycleObserver {
        b() {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onResume(androidx.lifecycle.m mVar) {
            z5.b.f("FlutterRenderer", "onResume called; notifying SurfaceProducers");
            for (ImageReaderSurfaceProducer imageReaderSurfaceProducer : FlutterRenderer.this.f7794g) {
                if (imageReaderSurfaceProducer.callback != null) {
                    imageReaderSurfaceProducer.callback.a();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final Rect f7805a;

        /* renamed from: b  reason: collision with root package name */
        public final e f7806b;

        /* renamed from: c  reason: collision with root package name */
        public final d f7807c;

        public c(Rect rect, e eVar) {
            this.f7805a = rect;
            this.f7806b = eVar;
            this.f7807c = d.UNKNOWN;
        }

        public c(Rect rect, e eVar, d dVar) {
            this.f7805a = rect;
            this.f7806b = eVar;
            this.f7807c = dVar;
        }
    }

    /* loaded from: classes.dex */
    public enum d {
        UNKNOWN(0),
        POSTURE_FLAT(1),
        POSTURE_HALF_OPENED(2);
        

        /* renamed from: e  reason: collision with root package name */
        public final int f7812e;

        d(int i8) {
            this.f7812e = i8;
        }
    }

    /* loaded from: classes.dex */
    public enum e {
        UNKNOWN(0),
        FOLD(1),
        HINGE(2),
        CUTOUT(3);
        

        /* renamed from: e  reason: collision with root package name */
        public final int f7818e;

        e(int i8) {
            this.f7818e = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class f implements TextureRegistry.SurfaceTextureEntry, TextureRegistry.b {

        /* renamed from: a  reason: collision with root package name */
        private final long f7819a;

        /* renamed from: b  reason: collision with root package name */
        private final SurfaceTextureWrapper f7820b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f7821c;

        /* renamed from: d  reason: collision with root package name */
        private TextureRegistry.b f7822d;

        f(long j8, SurfaceTexture surfaceTexture) {
            this.f7819a = j8;
            this.f7820b = new SurfaceTextureWrapper(surfaceTexture, new Runnable() { // from class: io.flutter.embedding.engine.renderer.j
                @Override // java.lang.Runnable
                public final void run() {
                    FlutterRenderer.f.this.c();
                }
            });
            surfaceTexture().setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: io.flutter.embedding.engine.renderer.k
                @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
                public final void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                    FlutterRenderer.f.this.d(surfaceTexture2);
                }
            }, new Handler());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(SurfaceTexture surfaceTexture) {
            if (this.f7821c || !FlutterRenderer.this.f7788a.isAttached()) {
                return;
            }
            this.f7820b.markDirty();
            FlutterRenderer.this.w();
        }

        private void e() {
            FlutterRenderer.this.v(this);
        }

        public SurfaceTextureWrapper f() {
            return this.f7820b;
        }

        protected void finalize() {
            try {
                if (this.f7821c) {
                    return;
                }
                FlutterRenderer.this.f7792e.post(new g(this.f7819a, FlutterRenderer.this.f7788a));
            } finally {
                super.finalize();
            }
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceTextureEntry
        public long id() {
            return this.f7819a;
        }

        @Override // io.flutter.view.TextureRegistry.b
        public void onTrimMemory(int i8) {
            TextureRegistry.b bVar = this.f7822d;
            if (bVar != null) {
                bVar.onTrimMemory(i8);
            }
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceTextureEntry
        public void release() {
            if (this.f7821c) {
                return;
            }
            z5.b.f("FlutterRenderer", "Releasing a SurfaceTexture (" + this.f7819a + ").");
            this.f7820b.release();
            FlutterRenderer.this.D(this.f7819a);
            e();
            this.f7821c = true;
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceTextureEntry
        public void setOnFrameConsumedListener(TextureRegistry.a aVar) {
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceTextureEntry
        public void setOnTrimMemoryListener(TextureRegistry.b bVar) {
            this.f7822d = bVar;
        }

        @Override // io.flutter.view.TextureRegistry.SurfaceTextureEntry
        public SurfaceTexture surfaceTexture() {
            return this.f7820b.surfaceTexture();
        }
    }

    /* loaded from: classes.dex */
    static final class g implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private final long f7824e;

        /* renamed from: f  reason: collision with root package name */
        private final FlutterJNI f7825f;

        /* JADX INFO: Access modifiers changed from: package-private */
        public g(long j8, FlutterJNI flutterJNI) {
            this.f7824e = j8;
            this.f7825f = flutterJNI;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f7825f.isAttached()) {
                z5.b.f("FlutterRenderer", "Releasing a Texture (" + this.f7824e + ").");
                this.f7825f.unregisterTexture(this.f7824e);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class h {

        /* renamed from: a  reason: collision with root package name */
        public float f7826a = 1.0f;

        /* renamed from: b  reason: collision with root package name */
        public int f7827b = 0;

        /* renamed from: c  reason: collision with root package name */
        public int f7828c = 0;

        /* renamed from: d  reason: collision with root package name */
        public int f7829d = 0;

        /* renamed from: e  reason: collision with root package name */
        public int f7830e = 0;

        /* renamed from: f  reason: collision with root package name */
        public int f7831f = 0;

        /* renamed from: g  reason: collision with root package name */
        public int f7832g = 0;

        /* renamed from: h  reason: collision with root package name */
        public int f7833h = 0;

        /* renamed from: i  reason: collision with root package name */
        public int f7834i = 0;

        /* renamed from: j  reason: collision with root package name */
        public int f7835j = 0;

        /* renamed from: k  reason: collision with root package name */
        public int f7836k = 0;

        /* renamed from: l  reason: collision with root package name */
        public int f7837l = 0;

        /* renamed from: m  reason: collision with root package name */
        public int f7838m = 0;

        /* renamed from: n  reason: collision with root package name */
        public int f7839n = 0;

        /* renamed from: o  reason: collision with root package name */
        public int f7840o = 0;

        /* renamed from: p  reason: collision with root package name */
        public int f7841p = -1;

        /* renamed from: q  reason: collision with root package name */
        public List f7842q = new ArrayList();

        boolean a() {
            return this.f7827b > 0 && this.f7828c > 0 && this.f7826a > 0.0f;
        }
    }

    public FlutterRenderer(FlutterJNI flutterJNI) {
        a aVar = new a();
        this.f7795h = aVar;
        this.f7788a = flutterJNI;
        flutterJNI.addIsDisplayingFlutterUiListener(aVar);
        w.l().getLifecycle().a(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(long j8) {
        this.f7788a.unregisterTexture(j8);
    }

    private void l() {
        Iterator it = this.f7793f.iterator();
        while (it.hasNext()) {
            if (((TextureRegistry.b) ((WeakReference) it.next()).get()) == null) {
                it.remove();
            }
        }
    }

    private void q(long j8, TextureRegistry.ImageConsumer imageConsumer) {
        this.f7788a.registerImageTexture(j8, imageConsumer);
    }

    private TextureRegistry.SurfaceTextureEntry r(long j8, SurfaceTexture surfaceTexture) {
        surfaceTexture.detachFromGLContext();
        f fVar = new f(j8, surfaceTexture);
        z5.b.f("FlutterRenderer", "New SurfaceTexture ID: " + fVar.id());
        t(fVar.id(), fVar.f());
        k(fVar);
        return fVar;
    }

    private void t(long j8, SurfaceTextureWrapper surfaceTextureWrapper) {
        this.f7788a.registerTexture(j8, surfaceTextureWrapper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        this.f7788a.scheduleFrame();
    }

    public void A() {
        if (this.f7790c != null) {
            this.f7788a.onSurfaceDestroyed();
            if (this.f7791d) {
                this.f7795h.b();
            }
            this.f7791d = false;
            this.f7790c = null;
        }
    }

    public void B(int i8, int i9) {
        this.f7788a.onSurfaceChanged(i8, i9);
    }

    public void C(Surface surface) {
        this.f7790c = surface;
        this.f7788a.onSurfaceWindowChanged(surface);
    }

    @Override // io.flutter.view.TextureRegistry
    public TextureRegistry.ImageTextureEntry a() {
        ImageTextureRegistryEntry imageTextureRegistryEntry = new ImageTextureRegistryEntry(this.f7789b.getAndIncrement());
        z5.b.f("FlutterRenderer", "New ImageTextureEntry ID: " + imageTextureRegistryEntry.id());
        q(imageTextureRegistryEntry.id(), imageTextureRegistryEntry);
        return imageTextureRegistryEntry;
    }

    @Override // io.flutter.view.TextureRegistry
    public TextureRegistry.SurfaceProducer b() {
        if (f7786i || Build.VERSION.SDK_INT < 29) {
            TextureRegistry.SurfaceTextureEntry c8 = c();
            n nVar = new n(c8.id(), this.f7792e, this.f7788a, c8);
            z5.b.f("FlutterRenderer", "New SurfaceTextureSurfaceProducer ID: " + c8.id());
            return nVar;
        }
        long andIncrement = this.f7789b.getAndIncrement();
        ImageReaderSurfaceProducer imageReaderSurfaceProducer = new ImageReaderSurfaceProducer(andIncrement);
        q(andIncrement, imageReaderSurfaceProducer);
        k(imageReaderSurfaceProducer);
        this.f7794g.add(imageReaderSurfaceProducer);
        z5.b.f("FlutterRenderer", "New ImageReaderSurfaceProducer ID: " + andIncrement);
        return imageReaderSurfaceProducer;
    }

    @Override // io.flutter.view.TextureRegistry
    public TextureRegistry.SurfaceTextureEntry c() {
        z5.b.f("FlutterRenderer", "Creating a SurfaceTexture.");
        return s(new SurfaceTexture(0));
    }

    public void j(l lVar) {
        this.f7788a.addIsDisplayingFlutterUiListener(lVar);
        if (this.f7791d) {
            lVar.d();
        }
    }

    void k(TextureRegistry.b bVar) {
        l();
        this.f7793f.add(new WeakReference(bVar));
    }

    public void m(ByteBuffer byteBuffer, int i8) {
        this.f7788a.dispatchPointerDataPacket(byteBuffer, i8);
    }

    public boolean n() {
        return this.f7791d;
    }

    public boolean o() {
        return this.f7788a.getIsSoftwareRenderingEnabled();
    }

    public void p(int i8) {
        Iterator it = this.f7793f.iterator();
        while (it.hasNext()) {
            TextureRegistry.b bVar = (TextureRegistry.b) ((WeakReference) it.next()).get();
            if (bVar != null) {
                bVar.onTrimMemory(i8);
            } else {
                it.remove();
            }
        }
    }

    public TextureRegistry.SurfaceTextureEntry s(SurfaceTexture surfaceTexture) {
        return r(this.f7789b.getAndIncrement(), surfaceTexture);
    }

    public void u(l lVar) {
        this.f7788a.removeIsDisplayingFlutterUiListener(lVar);
    }

    void v(TextureRegistry.b bVar) {
        for (WeakReference weakReference : this.f7793f) {
            if (weakReference.get() == bVar) {
                this.f7793f.remove(weakReference);
                return;
            }
        }
    }

    public void x(boolean z7) {
        this.f7788a.setSemanticsEnabled(z7);
    }

    public void y(h hVar) {
        if (hVar.a()) {
            z5.b.f("FlutterRenderer", "Setting viewport metrics\nSize: " + hVar.f7827b + " x " + hVar.f7828c + "\nPadding - L: " + hVar.f7832g + ", T: " + hVar.f7829d + ", R: " + hVar.f7830e + ", B: " + hVar.f7831f + "\nInsets - L: " + hVar.f7836k + ", T: " + hVar.f7833h + ", R: " + hVar.f7834i + ", B: " + hVar.f7835j + "\nSystem Gesture Insets - L: " + hVar.f7840o + ", T: " + hVar.f7837l + ", R: " + hVar.f7838m + ", B: " + hVar.f7838m + "\nDisplay Features: " + hVar.f7842q.size());
            int[] iArr = new int[hVar.f7842q.size() * 4];
            int[] iArr2 = new int[hVar.f7842q.size()];
            int[] iArr3 = new int[hVar.f7842q.size()];
            for (int i8 = 0; i8 < hVar.f7842q.size(); i8++) {
                c cVar = (c) hVar.f7842q.get(i8);
                int i9 = i8 * 4;
                Rect rect = cVar.f7805a;
                iArr[i9] = rect.left;
                iArr[i9 + 1] = rect.top;
                iArr[i9 + 2] = rect.right;
                iArr[i9 + 3] = rect.bottom;
                iArr2[i8] = cVar.f7806b.f7818e;
                iArr3[i8] = cVar.f7807c.f7812e;
            }
            this.f7788a.setViewportMetrics(hVar.f7826a, hVar.f7827b, hVar.f7828c, hVar.f7829d, hVar.f7830e, hVar.f7831f, hVar.f7832g, hVar.f7833h, hVar.f7834i, hVar.f7835j, hVar.f7836k, hVar.f7837l, hVar.f7838m, hVar.f7839n, hVar.f7840o, hVar.f7841p, iArr, iArr2, iArr3);
        }
    }

    public void z(Surface surface, boolean z7) {
        if (!z7) {
            A();
        }
        this.f7790c = surface;
        if (z7) {
            this.f7788a.onSurfaceWindowChanged(surface);
        } else {
            this.f7788a.onSurfaceCreated(surface);
        }
    }
}
