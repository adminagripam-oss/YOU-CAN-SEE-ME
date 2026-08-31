package b0;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class l0 {

    /* renamed from: a  reason: collision with root package name */
    private final Object f4274a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private final Map f4275b = new LinkedHashMap();

    /* renamed from: c  reason: collision with root package name */
    private final Set f4276c = new HashSet();

    public LinkedHashSet a() {
        LinkedHashSet linkedHashSet;
        synchronized (this.f4274a) {
            linkedHashSet = new LinkedHashSet(this.f4275b.values());
        }
        return linkedHashSet;
    }

    public void b(f0 f0Var) {
        synchronized (this.f4274a) {
            try {
                try {
                    for (String str : f0Var.c()) {
                        y.f1.a("CameraRepository", "Added camera: " + str);
                        this.f4275b.put(str, f0Var.a(str));
                    }
                } catch (y.s e8) {
                    throw new y.d1(e8);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
