package androidx.camera.core;

import android.media.Image;
import android.media.ImageReader;
import android.view.Surface;
import b0.q1;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d implements q1 {

    /* renamed from: a  reason: collision with root package name */
    private final ImageReader f2674a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f2675b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private boolean f2676c = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(ImageReader imageReader) {
        this.f2674a = imageReader;
    }

    private boolean h(RuntimeException runtimeException) {
        return "ImageReaderContext is not initialized".equals(runtimeException.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(q1.a aVar) {
        aVar.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(Executor executor, final q1.a aVar, ImageReader imageReader) {
        synchronized (this.f2675b) {
            if (!this.f2676c) {
                executor.execute(new Runnable() { // from class: androidx.camera.core.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        d.this.i(aVar);
                    }
                });
            }
        }
    }

    @Override // b0.q1
    public int a() {
        int imageFormat;
        synchronized (this.f2675b) {
            imageFormat = this.f2674a.getImageFormat();
        }
        return imageFormat;
    }

    @Override // b0.q1
    public n acquireLatestImage() {
        Image image;
        synchronized (this.f2675b) {
            try {
                image = this.f2674a.acquireLatestImage();
            } catch (RuntimeException e8) {
                if (!h(e8)) {
                    throw e8;
                }
                image = null;
            }
            if (image == null) {
                return null;
            }
            return new a(image);
        }
    }

    @Override // b0.q1
    public void c() {
        synchronized (this.f2675b) {
            this.f2676c = true;
            this.f2674a.setOnImageAvailableListener(null, null);
        }
    }

    @Override // b0.q1
    public void close() {
        synchronized (this.f2675b) {
            this.f2674a.close();
        }
    }

    @Override // b0.q1
    public void d(final q1.a aVar, final Executor executor) {
        synchronized (this.f2675b) {
            this.f2676c = false;
            this.f2674a.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: androidx.camera.core.b
                @Override // android.media.ImageReader.OnImageAvailableListener
                public final void onImageAvailable(ImageReader imageReader) {
                    d.this.j(executor, aVar, imageReader);
                }
            }, d0.m.a());
        }
    }

    @Override // b0.q1
    public int e() {
        int maxImages;
        synchronized (this.f2675b) {
            maxImages = this.f2674a.getMaxImages();
        }
        return maxImages;
    }

    @Override // b0.q1
    public n f() {
        Image image;
        synchronized (this.f2675b) {
            try {
                image = this.f2674a.acquireNextImage();
            } catch (RuntimeException e8) {
                if (!h(e8)) {
                    throw e8;
                }
                image = null;
            }
            if (image == null) {
                return null;
            }
            return new a(image);
        }
    }

    @Override // b0.q1
    public int getHeight() {
        int height;
        synchronized (this.f2675b) {
            height = this.f2674a.getHeight();
        }
        return height;
    }

    @Override // b0.q1
    public Surface getSurface() {
        Surface surface;
        synchronized (this.f2675b) {
            surface = this.f2674a.getSurface();
        }
        return surface;
    }

    @Override // b0.q1
    public int getWidth() {
        int width;
        synchronized (this.f2675b) {
            width = this.f2674a.getWidth();
        }
        return width;
    }
}
