package io.flutter.plugin.platform;

import android.view.Surface;
import io.flutter.view.TextureRegistry;
/* loaded from: classes.dex */
public class d0 implements o {

    /* renamed from: a  reason: collision with root package name */
    private TextureRegistry.SurfaceProducer f7943a;

    public d0(TextureRegistry.SurfaceProducer surfaceProducer) {
        this.f7943a = surfaceProducer;
    }

    @Override // io.flutter.plugin.platform.o
    public long a() {
        return this.f7943a.id();
    }

    @Override // io.flutter.plugin.platform.o
    public void b(int i8, int i9) {
        this.f7943a.setSize(i8, i9);
    }

    @Override // io.flutter.plugin.platform.o
    public int getHeight() {
        return this.f7943a.getHeight();
    }

    @Override // io.flutter.plugin.platform.o
    public Surface getSurface() {
        return this.f7943a.getSurface();
    }

    @Override // io.flutter.plugin.platform.o
    public int getWidth() {
        return this.f7943a.getWidth();
    }

    @Override // io.flutter.plugin.platform.o
    public void release() {
        this.f7943a.release();
        this.f7943a = null;
    }

    @Override // io.flutter.plugin.platform.o
    public void scheduleFrame() {
        this.f7943a.scheduleFrame();
    }
}
