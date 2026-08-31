package b0;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public class i2 {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f4240a;

    /* renamed from: b  reason: collision with root package name */
    private final Set f4241b;

    /* renamed from: c  reason: collision with root package name */
    private final Set f4242c;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private boolean f4243a = true;

        /* renamed from: b  reason: collision with root package name */
        private Set f4244b;

        /* renamed from: c  reason: collision with root package name */
        private Set f4245c;

        public i2 a() {
            return new i2(this.f4243a, this.f4244b, this.f4245c);
        }

        public b b(Set set) {
            this.f4245c = new HashSet(set);
            return this;
        }

        public b c(Set set) {
            this.f4244b = new HashSet(set);
            return this;
        }

        public b d(boolean z7) {
            this.f4243a = z7;
            return this;
        }
    }

    private i2(boolean z7, Set set, Set set2) {
        this.f4240a = z7;
        this.f4241b = set == null ? Collections.emptySet() : new HashSet(set);
        this.f4242c = set2 == null ? Collections.emptySet() : new HashSet(set2);
    }

    public static i2 b() {
        return new b().d(true).a();
    }

    public boolean a(Class cls, boolean z7) {
        if (this.f4241b.contains(cls)) {
            return true;
        }
        if (this.f4242c.contains(cls)) {
            return false;
        }
        return this.f4240a && z7;
    }

    public boolean equals(Object obj) {
        if (obj instanceof i2) {
            if (this == obj) {
                return true;
            }
            i2 i2Var = (i2) obj;
            return this.f4240a == i2Var.f4240a && Objects.equals(this.f4241b, i2Var.f4241b) && Objects.equals(this.f4242c, i2Var.f4242c);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.f4240a), this.f4241b, this.f4242c);
    }

    public String toString() {
        return "QuirkSettings{enabledWhenDeviceHasQuirk=" + this.f4240a + ", forceEnabledQuirks=" + this.f4241b + ", forceDisabledQuirks=" + this.f4242c + '}';
    }
}
