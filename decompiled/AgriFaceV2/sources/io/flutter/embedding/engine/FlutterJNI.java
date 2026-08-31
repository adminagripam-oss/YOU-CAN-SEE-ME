package io.flutter.embedding.engine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Size;
import android.util.TypedValue;
import android.view.Surface;
import h6.t;
import i6.n;
import io.flutter.embedding.engine.a;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorsStack;
import io.flutter.embedding.engine.renderer.SurfaceTextureWrapper;
import io.flutter.plugin.platform.w;
import io.flutter.view.FlutterCallbackInformation;
import io.flutter.view.TextureRegistry;
import io.flutter.view.g;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes.dex */
public class FlutterJNI {
    private static final String TAG = "FlutterJNI";
    private static b asyncWaitForVsyncDelegate = null;
    private static float displayDensity = -1.0f;
    private static float displayHeight = -1.0f;
    private static float displayWidth = -1.0f;
    private static boolean initCalled = false;
    private static boolean loadLibraryCalled = false;
    private static boolean prefetchDefaultFontManagerCalled = false;
    private static float refreshRateFPS = 60.0f;
    private static String vmServiceUri;
    private a accessibilityDelegate;
    private b6.a deferredComponentManager;
    private j6.a localizationPlugin;
    private Long nativeShellHolderId;
    private a6.f platformMessageHandler;
    private w platformViewsController;
    private ReentrantReadWriteLock shellHolderLock = new ReentrantReadWriteLock();
    private final Set<a.b> engineLifecycleListeners = new CopyOnWriteArraySet();
    private final Set<io.flutter.embedding.engine.renderer.l> flutterUiDisplayListeners = new CopyOnWriteArraySet();
    private final Looper mainLooper = Looper.getMainLooper();

    /* loaded from: classes.dex */
    public interface a {
        void b(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr);

        void c(ByteBuffer byteBuffer, String[] strArr);
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(long j8);
    }

    /* loaded from: classes.dex */
    public static class c {
        public FlutterJNI a() {
            return new FlutterJNI();
        }
    }

    private static void asyncWaitForVsync(long j8) {
        b bVar = asyncWaitForVsyncDelegate;
        if (bVar == null) {
            throw new IllegalStateException("An AsyncWaitForVsyncDelegate must be registered with FlutterJNI before asyncWaitForVsync() is invoked.");
        }
        bVar.a(j8);
    }

    public static Bitmap decodeImage(ByteBuffer byteBuffer, final long j8) {
        ImageDecoder.Source createSource;
        Bitmap decodeBitmap;
        if (Build.VERSION.SDK_INT >= 28) {
            createSource = ImageDecoder.createSource(byteBuffer);
            try {
                decodeBitmap = ImageDecoder.decodeBitmap(createSource, new ImageDecoder.OnHeaderDecodedListener() { // from class: io.flutter.embedding.engine.k
                    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                        FlutterJNI.lambda$decodeImage$0(j8, imageDecoder, imageInfo, source);
                    }
                });
                return decodeBitmap;
            } catch (IOException e8) {
                z5.b.c(TAG, "Failed to decode image", e8);
            }
        }
        return null;
    }

    private void ensureAttachedToNative() {
        if (this.nativeShellHolderId == null) {
            throw new RuntimeException("Cannot execute operation because FlutterJNI is not attached to native.");
        }
    }

    private void ensureNotAttachedToNative() {
        if (this.nativeShellHolderId != null) {
            throw new RuntimeException("Cannot execute operation because FlutterJNI is attached to native.");
        }
    }

    private void ensureRunningOnMainThread() {
        if (Looper.myLooper() == this.mainLooper) {
            return;
        }
        throw new RuntimeException("Methods marked with @UiThread must be executed on the main thread. Current thread: " + Thread.currentThread().getName());
    }

    @Deprecated
    public static String getObservatoryUri() {
        return vmServiceUri;
    }

    public static String getVMServiceUri() {
        return vmServiceUri;
    }

    private void handlePlatformMessageResponse(int i8, ByteBuffer byteBuffer) {
        a6.f fVar = this.platformMessageHandler;
        if (fVar != null) {
            fVar.f(i8, byteBuffer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$decodeImage$0(long j8, ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        Size size;
        imageDecoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
        imageDecoder.setAllocator(1);
        size = imageInfo.getSize();
        nativeImageHeaderCallback(j8, size.getWidth(), size.getHeight());
    }

    private native long nativeAttach(FlutterJNI flutterJNI);

    private native void nativeCleanupMessageData(long j8);

    private native void nativeDeferredComponentInstallFailure(int i8, String str, boolean z7);

    private native void nativeDestroy(long j8);

    private native void nativeDispatchEmptyPlatformMessage(long j8, String str, int i8);

    private native void nativeDispatchPlatformMessage(long j8, String str, ByteBuffer byteBuffer, int i8, int i9);

    private native void nativeDispatchPointerDataPacket(long j8, ByteBuffer byteBuffer, int i8);

    private native void nativeDispatchSemanticsAction(long j8, int i8, int i9, ByteBuffer byteBuffer, int i10);

    private native boolean nativeFlutterTextUtilsIsEmoji(int i8);

    private native boolean nativeFlutterTextUtilsIsEmojiModifier(int i8);

    private native boolean nativeFlutterTextUtilsIsEmojiModifierBase(int i8);

    private native boolean nativeFlutterTextUtilsIsRegionalIndicator(int i8);

    private native boolean nativeFlutterTextUtilsIsVariationSelector(int i8);

    private native Bitmap nativeGetBitmap(long j8);

    private native boolean nativeGetIsSoftwareRenderingEnabled();

    public static native void nativeImageHeaderCallback(long j8, int i8, int i9);

    private static native void nativeInit(Context context, String[] strArr, String str, String str2, String str3, long j8);

    private native void nativeInvokePlatformMessageEmptyResponseCallback(long j8, int i8);

    private native void nativeInvokePlatformMessageResponseCallback(long j8, int i8, ByteBuffer byteBuffer, int i9);

    private native void nativeLoadDartDeferredLibrary(long j8, int i8, String[] strArr);

    @Deprecated
    public static native FlutterCallbackInformation nativeLookupCallbackInformation(long j8);

    private native void nativeMarkTextureFrameAvailable(long j8, long j9);

    private native void nativeNotifyLowMemoryWarning(long j8);

    private native void nativeOnVsync(long j8, long j9, long j10);

    private static native void nativePrefetchDefaultFontManager();

    private native void nativeRegisterImageTexture(long j8, long j9, WeakReference<TextureRegistry.ImageConsumer> weakReference);

    private native void nativeRegisterTexture(long j8, long j9, WeakReference<SurfaceTextureWrapper> weakReference);

    private native void nativeRunBundleAndSnapshotFromLibrary(long j8, String str, String str2, String str3, AssetManager assetManager, List<String> list);

    private native void nativeScheduleFrame(long j8);

    private native void nativeSetAccessibilityFeatures(long j8, int i8);

    private native void nativeSetSemanticsEnabled(long j8, boolean z7);

    private native void nativeSetViewportMetrics(long j8, float f8, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int[] iArr, int[] iArr2, int[] iArr3);

    private native FlutterJNI nativeSpawn(long j8, String str, String str2, String str3, List<String> list);

    private native void nativeSurfaceChanged(long j8, int i8, int i9);

    private native void nativeSurfaceCreated(long j8, Surface surface);

    private native void nativeSurfaceDestroyed(long j8);

    private native void nativeSurfaceWindowChanged(long j8, Surface surface);

    private native void nativeUnregisterTexture(long j8, long j9);

    private native void nativeUpdateDisplayMetrics(long j8);

    private native void nativeUpdateJavaAssetManager(long j8, AssetManager assetManager, String str);

    private native void nativeUpdateRefreshRate(float f8);

    private void onPreEngineRestart() {
        for (a.b bVar : this.engineLifecycleListeners) {
            bVar.b();
        }
    }

    private void updateCustomAccessibilityActions(ByteBuffer byteBuffer, String[] strArr) {
        ensureRunningOnMainThread();
        a aVar = this.accessibilityDelegate;
        if (aVar != null) {
            aVar.c(byteBuffer, strArr);
        }
    }

    private void updateSemantics(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
        ensureRunningOnMainThread();
        a aVar = this.accessibilityDelegate;
        if (aVar != null) {
            aVar.b(byteBuffer, strArr, byteBufferArr);
        }
    }

    public void addEngineLifecycleListener(a.b bVar) {
        ensureRunningOnMainThread();
        this.engineLifecycleListeners.add(bVar);
    }

    public void addIsDisplayingFlutterUiListener(io.flutter.embedding.engine.renderer.l lVar) {
        ensureRunningOnMainThread();
        this.flutterUiDisplayListeners.add(lVar);
    }

    public void attachToNative() {
        ensureRunningOnMainThread();
        ensureNotAttachedToNative();
        this.shellHolderLock.writeLock().lock();
        try {
            this.nativeShellHolderId = Long.valueOf(performNativeAttach(this));
        } finally {
            this.shellHolderLock.writeLock().unlock();
        }
    }

    public void cleanupMessageData(long j8) {
        nativeCleanupMessageData(j8);
    }

    public String[] computePlatformResolvedLocale(String[] strArr) {
        if (this.localizationPlugin == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < strArr.length; i8 += 3) {
            String str = strArr[i8 + 0];
            String str2 = strArr[i8 + 1];
            String str3 = strArr[i8 + 2];
            Locale.Builder builder = new Locale.Builder();
            if (!str.isEmpty()) {
                builder.setLanguage(str);
            }
            if (!str2.isEmpty()) {
                builder.setRegion(str2);
            }
            if (!str3.isEmpty()) {
                builder.setScript(str3);
            }
            arrayList.add(builder.build());
        }
        Locale c8 = this.localizationPlugin.c(arrayList);
        return c8 == null ? new String[0] : new String[]{c8.getLanguage(), c8.getCountry(), c8.getScript()};
    }

    public FlutterOverlaySurface createOverlaySurface() {
        ensureRunningOnMainThread();
        w wVar = this.platformViewsController;
        if (wVar != null) {
            return wVar.K();
        }
        throw new RuntimeException("platformViewsController must be set before attempting to position an overlay surface");
    }

    public void deferredComponentInstallFailure(int i8, String str, boolean z7) {
        ensureRunningOnMainThread();
        nativeDeferredComponentInstallFailure(i8, str, z7);
    }

    public void destroyOverlaySurfaces() {
        ensureRunningOnMainThread();
        w wVar = this.platformViewsController;
        if (wVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to destroy an overlay surface");
        }
        wVar.N();
    }

    public void detachFromNativeAndReleaseResources() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        this.shellHolderLock.writeLock().lock();
        try {
            nativeDestroy(this.nativeShellHolderId.longValue());
            this.nativeShellHolderId = null;
        } finally {
            this.shellHolderLock.writeLock().unlock();
        }
    }

    public void dispatchEmptyPlatformMessage(String str, int i8) {
        ensureRunningOnMainThread();
        if (isAttached()) {
            nativeDispatchEmptyPlatformMessage(this.nativeShellHolderId.longValue(), str, i8);
            return;
        }
        z5.b.g(TAG, "Tried to send a platform message to Flutter, but FlutterJNI was detached from native C++. Could not send. Channel: " + str + ". Response ID: " + i8);
    }

    public void dispatchPlatformMessage(String str, ByteBuffer byteBuffer, int i8, int i9) {
        ensureRunningOnMainThread();
        if (isAttached()) {
            nativeDispatchPlatformMessage(this.nativeShellHolderId.longValue(), str, byteBuffer, i8, i9);
            return;
        }
        z5.b.g(TAG, "Tried to send a platform message to Flutter, but FlutterJNI was detached from native C++. Could not send. Channel: " + str + ". Response ID: " + i9);
    }

    public void dispatchPointerDataPacket(ByteBuffer byteBuffer, int i8) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeDispatchPointerDataPacket(this.nativeShellHolderId.longValue(), byteBuffer, i8);
    }

    public void dispatchSemanticsAction(int i8, int i9, ByteBuffer byteBuffer, int i10) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeDispatchSemanticsAction(this.nativeShellHolderId.longValue(), i8, i9, byteBuffer, i10);
    }

    public Bitmap getBitmap() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        return nativeGetBitmap(this.nativeShellHolderId.longValue());
    }

    public boolean getIsSoftwareRenderingEnabled() {
        return nativeGetIsSoftwareRenderingEnabled();
    }

    public float getScaledFontSize(float f8, int i8) {
        DisplayMetrics b8 = t.b(i8);
        if (b8 == null) {
            z5.b.b(TAG, "getScaledFontSize called with configurationId " + String.valueOf(i8) + ", which can't be found.");
            return -1.0f;
        }
        return TypedValue.applyDimension(2, f8, b8) / b8.density;
    }

    public void handlePlatformMessage(String str, ByteBuffer byteBuffer, int i8, long j8) {
        a6.f fVar = this.platformMessageHandler;
        if (fVar != null) {
            fVar.g(str, byteBuffer, i8, j8);
        } else {
            nativeCleanupMessageData(j8);
        }
    }

    public void init(Context context, String[] strArr, String str, String str2, String str3, long j8) {
        if (initCalled) {
            z5.b.g(TAG, "FlutterJNI.init called more than once");
        }
        nativeInit(context, strArr, str, str2, str3, j8);
        initCalled = true;
    }

    public void invokePlatformMessageEmptyResponseCallback(int i8) {
        this.shellHolderLock.readLock().lock();
        try {
            if (isAttached()) {
                nativeInvokePlatformMessageEmptyResponseCallback(this.nativeShellHolderId.longValue(), i8);
            } else {
                z5.b.g(TAG, "Tried to send a platform message response, but FlutterJNI was detached from native C++. Could not send. Response ID: " + i8);
            }
        } finally {
            this.shellHolderLock.readLock().unlock();
        }
    }

    public void invokePlatformMessageResponseCallback(int i8, ByteBuffer byteBuffer, int i9) {
        if (!byteBuffer.isDirect()) {
            throw new IllegalArgumentException("Expected a direct ByteBuffer.");
        }
        this.shellHolderLock.readLock().lock();
        try {
            if (isAttached()) {
                nativeInvokePlatformMessageResponseCallback(this.nativeShellHolderId.longValue(), i8, byteBuffer, i9);
            } else {
                z5.b.g(TAG, "Tried to send a platform message response, but FlutterJNI was detached from native C++. Could not send. Response ID: " + i8);
            }
        } finally {
            this.shellHolderLock.readLock().unlock();
        }
    }

    public boolean isAttached() {
        return this.nativeShellHolderId != null;
    }

    public boolean isCodePointEmoji(int i8) {
        return nativeFlutterTextUtilsIsEmoji(i8);
    }

    public boolean isCodePointEmojiModifier(int i8) {
        return nativeFlutterTextUtilsIsEmojiModifier(i8);
    }

    public boolean isCodePointEmojiModifierBase(int i8) {
        return nativeFlutterTextUtilsIsEmojiModifierBase(i8);
    }

    public boolean isCodePointRegionalIndicator(int i8) {
        return nativeFlutterTextUtilsIsRegionalIndicator(i8);
    }

    public boolean isCodePointVariantSelector(int i8) {
        return nativeFlutterTextUtilsIsVariationSelector(i8);
    }

    public void loadDartDeferredLibrary(int i8, String[] strArr) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeLoadDartDeferredLibrary(this.nativeShellHolderId.longValue(), i8, strArr);
    }

    public void loadLibrary() {
        if (loadLibraryCalled) {
            z5.b.g(TAG, "FlutterJNI.loadLibrary called more than once");
        }
        System.loadLibrary("flutter");
        loadLibraryCalled = true;
    }

    public void markTextureFrameAvailable(long j8) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeMarkTextureFrameAvailable(this.nativeShellHolderId.longValue(), j8);
    }

    public void notifyLowMemoryWarning() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeNotifyLowMemoryWarning(this.nativeShellHolderId.longValue());
    }

    public void onBeginFrame() {
        ensureRunningOnMainThread();
        w wVar = this.platformViewsController;
        if (wVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to begin the frame");
        }
        wVar.h0();
    }

    public void onDisplayOverlaySurface(int i8, int i9, int i10, int i11, int i12) {
        ensureRunningOnMainThread();
        w wVar = this.platformViewsController;
        if (wVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to position an overlay surface");
        }
        wVar.j0(i8, i9, i10, i11, i12);
    }

    public void onDisplayPlatformView(int i8, int i9, int i10, int i11, int i12, int i13, int i14, FlutterMutatorsStack flutterMutatorsStack) {
        ensureRunningOnMainThread();
        w wVar = this.platformViewsController;
        if (wVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to position a platform view");
        }
        wVar.k0(i8, i9, i10, i11, i12, i13, i14, flutterMutatorsStack);
    }

    public void onEndFrame() {
        ensureRunningOnMainThread();
        w wVar = this.platformViewsController;
        if (wVar == null) {
            throw new RuntimeException("platformViewsController must be set before attempting to end the frame");
        }
        wVar.l0();
    }

    public void onFirstFrame() {
        ensureRunningOnMainThread();
        for (io.flutter.embedding.engine.renderer.l lVar : this.flutterUiDisplayListeners) {
            lVar.d();
        }
    }

    void onRenderingStopped() {
        ensureRunningOnMainThread();
        for (io.flutter.embedding.engine.renderer.l lVar : this.flutterUiDisplayListeners) {
            lVar.b();
        }
    }

    public void onSurfaceChanged(int i8, int i9) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSurfaceChanged(this.nativeShellHolderId.longValue(), i8, i9);
    }

    public void onSurfaceCreated(Surface surface) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSurfaceCreated(this.nativeShellHolderId.longValue(), surface);
    }

    public void onSurfaceDestroyed() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        onRenderingStopped();
        nativeSurfaceDestroyed(this.nativeShellHolderId.longValue());
    }

    public void onSurfaceWindowChanged(Surface surface) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSurfaceWindowChanged(this.nativeShellHolderId.longValue(), surface);
    }

    public void onVsync(long j8, long j9, long j10) {
        nativeOnVsync(j8, j9, j10);
    }

    public long performNativeAttach(FlutterJNI flutterJNI) {
        return nativeAttach(flutterJNI);
    }

    public void prefetchDefaultFontManager() {
        if (prefetchDefaultFontManagerCalled) {
            z5.b.g(TAG, "FlutterJNI.prefetchDefaultFontManager called more than once");
        }
        nativePrefetchDefaultFontManager();
        prefetchDefaultFontManagerCalled = true;
    }

    public void registerImageTexture(long j8, TextureRegistry.ImageConsumer imageConsumer) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeRegisterImageTexture(this.nativeShellHolderId.longValue(), j8, new WeakReference<>(imageConsumer));
    }

    public void registerTexture(long j8, SurfaceTextureWrapper surfaceTextureWrapper) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeRegisterTexture(this.nativeShellHolderId.longValue(), j8, new WeakReference<>(surfaceTextureWrapper));
    }

    public void removeEngineLifecycleListener(a.b bVar) {
        ensureRunningOnMainThread();
        this.engineLifecycleListeners.remove(bVar);
    }

    public void removeIsDisplayingFlutterUiListener(io.flutter.embedding.engine.renderer.l lVar) {
        ensureRunningOnMainThread();
        this.flutterUiDisplayListeners.remove(lVar);
    }

    public void requestDartDeferredLibrary(int i8) {
        z5.b.b(TAG, "No DeferredComponentManager found. Android setup must be completed before using split AOT deferred components.");
    }

    public void runBundleAndSnapshotFromLibrary(String str, String str2, String str3, AssetManager assetManager, List<String> list) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeRunBundleAndSnapshotFromLibrary(this.nativeShellHolderId.longValue(), str, str2, str3, assetManager, list);
    }

    public void scheduleFrame() {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeScheduleFrame(this.nativeShellHolderId.longValue());
    }

    public void setAccessibilityDelegate(a aVar) {
        ensureRunningOnMainThread();
        this.accessibilityDelegate = aVar;
    }

    public void setAccessibilityFeatures(int i8) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSetAccessibilityFeatures(this.nativeShellHolderId.longValue(), i8);
    }

    public void setAsyncWaitForVsyncDelegate(b bVar) {
        asyncWaitForVsyncDelegate = bVar;
    }

    public void setDeferredComponentManager(b6.a aVar) {
        ensureRunningOnMainThread();
        if (aVar != null) {
            aVar.a(this);
        }
    }

    public void setLocalizationPlugin(j6.a aVar) {
        ensureRunningOnMainThread();
        this.localizationPlugin = aVar;
    }

    public void setPlatformMessageHandler(a6.f fVar) {
        ensureRunningOnMainThread();
        this.platformMessageHandler = fVar;
    }

    public void setPlatformViewsController(w wVar) {
        ensureRunningOnMainThread();
        this.platformViewsController = wVar;
    }

    public void setRefreshRateFPS(float f8) {
        refreshRateFPS = f8;
        updateRefreshRate();
    }

    public void setSemanticsEnabled(boolean z7) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSetSemanticsEnabled(this.nativeShellHolderId.longValue(), z7);
    }

    public void setViewportMetrics(float f8, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int[] iArr, int[] iArr2, int[] iArr3) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeSetViewportMetrics(this.nativeShellHolderId.longValue(), f8, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, iArr, iArr2, iArr3);
    }

    public FlutterJNI spawn(String str, String str2, String str3, List<String> list) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        FlutterJNI nativeSpawn = nativeSpawn(this.nativeShellHolderId.longValue(), str, str2, str3, list);
        Long l8 = nativeSpawn.nativeShellHolderId;
        p6.c.a((l8 == null || l8.longValue() == 0) ? false : true, "Failed to spawn new JNI connected shell from existing shell.");
        return nativeSpawn;
    }

    public void unregisterTexture(long j8) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeUnregisterTexture(this.nativeShellHolderId.longValue(), j8);
    }

    public void updateDisplayMetrics(int i8, float f8, float f9, float f10) {
        displayWidth = f8;
        displayHeight = f9;
        displayDensity = f10;
        if (loadLibraryCalled) {
            nativeUpdateDisplayMetrics(this.nativeShellHolderId.longValue());
        }
    }

    public void updateJavaAssetManager(AssetManager assetManager, String str) {
        ensureRunningOnMainThread();
        ensureAttachedToNative();
        nativeUpdateJavaAssetManager(this.nativeShellHolderId.longValue(), assetManager, str);
    }

    public void updateRefreshRate() {
        if (loadLibraryCalled) {
            nativeUpdateRefreshRate(refreshRateFPS);
        }
    }

    public void dispatchSemanticsAction(int i8, g.EnumC0108g enumC0108g) {
        dispatchSemanticsAction(i8, enumC0108g, null);
    }

    public void dispatchSemanticsAction(int i8, g.EnumC0108g enumC0108g, Object obj) {
        ByteBuffer byteBuffer;
        int i9;
        ensureAttachedToNative();
        if (obj != null) {
            byteBuffer = n.f7547a.a(obj);
            i9 = byteBuffer.position();
        } else {
            byteBuffer = null;
            i9 = 0;
        }
        dispatchSemanticsAction(i8, enumC0108g.f8102e, byteBuffer, i9);
    }
}
