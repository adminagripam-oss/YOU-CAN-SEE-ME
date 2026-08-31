package b0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public abstract class o {

    /* loaded from: classes.dex */
    public static final class a extends n {

        /* renamed from: a  reason: collision with root package name */
        private final List f4288a = new ArrayList();

        a(List list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                n nVar = (n) it.next();
                if (!(nVar instanceof b)) {
                    this.f4288a.add(nVar);
                }
            }
        }

        @Override // b0.n
        public void a(int i8) {
            for (n nVar : this.f4288a) {
                nVar.a(i8);
            }
        }

        @Override // b0.n
        public void b(int i8, x xVar) {
            for (n nVar : this.f4288a) {
                nVar.b(i8, xVar);
            }
        }

        @Override // b0.n
        public void c(int i8, p pVar) {
            for (n nVar : this.f4288a) {
                nVar.c(i8, pVar);
            }
        }

        @Override // b0.n
        public void d(int i8) {
            for (n nVar : this.f4288a) {
                nVar.d(i8);
            }
        }

        public List e() {
            return this.f4288a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends n {
        b() {
        }

        @Override // b0.n
        public void b(int i8, x xVar) {
        }

        @Override // b0.n
        public void c(int i8, p pVar) {
        }

        @Override // b0.n
        public void d(int i8) {
        }
    }

    static n a(List list) {
        return list.isEmpty() ? c() : list.size() == 1 ? (n) list.get(0) : new a(list);
    }

    public static n b(n... nVarArr) {
        return a(Arrays.asList(nVarArr));
    }

    public static n c() {
        return new b();
    }
}
