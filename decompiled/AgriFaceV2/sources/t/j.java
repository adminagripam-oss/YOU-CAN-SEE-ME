package t;

import android.hardware.camera2.params.InputConfiguration;
import android.os.Build;
import java.util.Objects;
/* loaded from: classes.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    private final c f11611a;

    /* loaded from: classes.dex */
    private static class a implements c {

        /* renamed from: a  reason: collision with root package name */
        private final InputConfiguration f11612a;

        a(Object obj) {
            this.f11612a = (InputConfiguration) obj;
        }

        @Override // t.j.c
        public Object b() {
            return this.f11612a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                return Objects.equals(this.f11612a, ((c) obj).b());
            }
            return false;
        }

        public int hashCode() {
            return this.f11612a.hashCode();
        }

        public String toString() {
            return this.f11612a.toString();
        }
    }

    /* loaded from: classes.dex */
    private static final class b extends a {
        b(Object obj) {
            super(obj);
        }
    }

    /* loaded from: classes.dex */
    private interface c {
        Object b();
    }

    private j(c cVar) {
        this.f11611a = cVar;
    }

    public static j b(Object obj) {
        if (obj == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 31 ? new j(new b(obj)) : new j(new a(obj));
    }

    public Object a() {
        return this.f11611a.b();
    }

    public boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f11611a.equals(((j) obj).f11611a);
        }
        return false;
    }

    public int hashCode() {
        return this.f11611a.hashCode();
    }

    public String toString() {
        return this.f11611a.toString();
    }
}
