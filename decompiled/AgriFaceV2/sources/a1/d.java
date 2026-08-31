package a1;

import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
import androidx.camera.video.internal.compat.quirk.VideoQualityQuirk;
import b0.g0;
import b0.g1;
import b0.h1;
import b0.h2;
import b0.l2;
import java.util.HashMap;
import java.util.Map;
import r0.v;
/* loaded from: classes.dex */
public class d implements g1 {

    /* renamed from: f  reason: collision with root package name */
    private static final Map f158f;

    /* renamed from: c  reason: collision with root package name */
    private final g1 f159c;

    /* renamed from: d  reason: collision with root package name */
    private final g0 f160d;

    /* renamed from: e  reason: collision with root package name */
    private final l2 f161e;

    static {
        HashMap hashMap = new HashMap();
        f158f = hashMap;
        hashMap.put(1, v.f11272f);
        hashMap.put(8, v.f11270d);
        hashMap.put(6, v.f11269c);
        hashMap.put(5, v.f11268b);
        hashMap.put(4, v.f11267a);
        hashMap.put(0, v.f11271e);
    }

    public d(g1 g1Var, g0 g0Var, l2 l2Var) {
        this.f159c = g1Var;
        this.f160d = g0Var;
        this.f161e = l2Var;
    }

    private boolean c(int i8) {
        v vVar = (v) f158f.get(Integer.valueOf(i8));
        if (vVar != null) {
            for (VideoQualityQuirk videoQualityQuirk : this.f161e.c(VideoQualityQuirk.class)) {
                if (videoQualityQuirk != null && videoQualityQuirk.c(this.f160d, vVar) && !d(videoQualityQuirk)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private static boolean d(h2 h2Var) {
        return (h2Var instanceof SurfaceProcessingQuirk) && ((SurfaceProcessingQuirk) h2Var).a();
    }

    @Override // b0.g1
    public h1 a(int i8) {
        if (b(i8)) {
            return this.f159c.a(i8);
        }
        return null;
    }

    @Override // b0.g1
    public boolean b(int i8) {
        return this.f159c.b(i8) && c(i8);
    }
}
