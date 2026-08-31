package t;

import android.hardware.camera2.params.DynamicRangeProfiles;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import t.g;
import y.a0;
/* loaded from: classes.dex */
class h implements g.a {

    /* renamed from: a  reason: collision with root package name */
    private final DynamicRangeProfiles f11608a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Object obj) {
        this.f11608a = (DynamicRangeProfiles) obj;
    }

    private Long d(a0 a0Var) {
        return d.a(a0Var, this.f11608a);
    }

    private static Set e(Set set) {
        if (set.isEmpty()) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(set.size());
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(f(((Long) it.next()).longValue()));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private static a0 f(long j8) {
        a0 b8 = d.b(j8);
        return (a0) g1.e.k(b8, "Dynamic range profile cannot be converted to a DynamicRange object: " + j8);
    }

    @Override // t.g.a
    public DynamicRangeProfiles a() {
        return this.f11608a;
    }

    @Override // t.g.a
    public Set b() {
        return e(this.f11608a.getSupportedProfiles());
    }

    @Override // t.g.a
    public Set c(a0 a0Var) {
        Long d8 = d(a0Var);
        boolean z7 = d8 != null;
        g1.e.b(z7, "DynamicRange is not supported: " + a0Var);
        return e(this.f11608a.getProfileCaptureRequestConstraints(d8.longValue()));
    }
}
