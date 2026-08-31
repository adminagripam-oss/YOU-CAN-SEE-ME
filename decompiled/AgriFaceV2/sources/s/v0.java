package s;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;
import s.t0;
import y.f1;
/* loaded from: classes.dex */
abstract class v0 implements t0.a {

    /* renamed from: a  reason: collision with root package name */
    final StreamConfigurationMap f11520a;

    /* loaded from: classes.dex */
    static class a {
        static Size[] a(StreamConfigurationMap streamConfigurationMap, int i8) {
            return streamConfigurationMap.getHighResolutionOutputSizes(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public v0(StreamConfigurationMap streamConfigurationMap) {
        this.f11520a = streamConfigurationMap;
    }

    @Override // s.t0.a
    public StreamConfigurationMap a() {
        return this.f11520a;
    }

    @Override // s.t0.a
    public Size[] b(int i8) {
        return a.a(this.f11520a, i8);
    }

    @Override // s.t0.a
    public int[] d() {
        try {
            return this.f11520a.getOutputFormats();
        } catch (IllegalArgumentException | NullPointerException e8) {
            f1.m("StreamConfigurationMapCompatBaseImpl", "Failed to get output formats from StreamConfigurationMap", e8);
            return null;
        }
    }
}
