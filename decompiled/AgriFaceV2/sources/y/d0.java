package y;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class d0 {

    /* renamed from: a  reason: collision with root package name */
    private final List f12752a;

    /* renamed from: b  reason: collision with root package name */
    private final List f12753b;

    /* renamed from: c  reason: collision with root package name */
    private final List f12754c;

    /* renamed from: d  reason: collision with root package name */
    private final long f12755d;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final List f12756a;

        /* renamed from: b  reason: collision with root package name */
        final List f12757b;

        /* renamed from: c  reason: collision with root package name */
        final List f12758c;

        /* renamed from: d  reason: collision with root package name */
        long f12759d;

        public a(d0 d0Var) {
            ArrayList arrayList = new ArrayList();
            this.f12756a = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.f12757b = arrayList2;
            ArrayList arrayList3 = new ArrayList();
            this.f12758c = arrayList3;
            this.f12759d = 5000L;
            arrayList.addAll(d0Var.c());
            arrayList2.addAll(d0Var.b());
            arrayList3.addAll(d0Var.d());
            this.f12759d = d0Var.a();
        }

        public a a(i1 i1Var) {
            return b(i1Var, 7);
        }

        public a b(i1 i1Var, int i8) {
            boolean z7 = false;
            g1.e.b(i1Var != null, "Point cannot be null.");
            if (i8 >= 1 && i8 <= 7) {
                z7 = true;
            }
            g1.e.b(z7, "Invalid metering mode " + i8);
            if ((i8 & 1) != 0) {
                this.f12756a.add(i1Var);
            }
            if ((i8 & 2) != 0) {
                this.f12757b.add(i1Var);
            }
            if ((i8 & 4) != 0) {
                this.f12758c.add(i1Var);
            }
            return this;
        }

        public d0 c() {
            return new d0(this);
        }

        public a d() {
            this.f12759d = 0L;
            return this;
        }

        public a e(int i8) {
            if ((i8 & 1) != 0) {
                this.f12756a.clear();
            }
            if ((i8 & 2) != 0) {
                this.f12757b.clear();
            }
            if ((i8 & 4) != 0) {
                this.f12758c.clear();
            }
            return this;
        }

        public a(i1 i1Var) {
            this(i1Var, 7);
        }

        public a(i1 i1Var, int i8) {
            this.f12756a = new ArrayList();
            this.f12757b = new ArrayList();
            this.f12758c = new ArrayList();
            this.f12759d = 5000L;
            b(i1Var, i8);
        }
    }

    d0(a aVar) {
        this.f12752a = Collections.unmodifiableList(aVar.f12756a);
        this.f12753b = Collections.unmodifiableList(aVar.f12757b);
        this.f12754c = Collections.unmodifiableList(aVar.f12758c);
        this.f12755d = aVar.f12759d;
    }

    public long a() {
        return this.f12755d;
    }

    public List b() {
        return this.f12753b;
    }

    public List c() {
        return this.f12752a;
    }

    public List d() {
        return this.f12754c;
    }

    public boolean e() {
        return this.f12755d > 0;
    }
}
