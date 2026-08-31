package m6;

import android.util.Size;
import java.util.List;
import m6.t0;
/* loaded from: classes.dex */
public class f6 implements t0.l1 {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9293a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9294b;

    /* loaded from: classes.dex */
    public static class a {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: m6.f6$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0124a implements o0.b {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Size f9295a;

            C0124a(Size size) {
                this.f9295a = size;
            }

            @Override // o0.b
            public List a(List list, int i8) {
                int indexOf = list.indexOf(this.f9295a);
                if (indexOf > -1) {
                    list.remove(indexOf);
                    list.add(0, this.f9295a);
                }
                return list;
            }
        }

        public o0.b a(Size size) {
            return new C0124a(size);
        }
    }

    public f6(v4 v4Var) {
        this(v4Var, new a());
    }

    @Override // m6.t0.l1
    public void b(Long l8, t0.n1 n1Var) {
        this.f9293a.a(this.f9294b.a(new Size(n1Var.c().intValue(), n1Var.b().intValue())), l8.longValue());
    }

    f6(v4 v4Var, a aVar) {
        this.f9293a = v4Var;
        this.f9294b = aVar;
    }
}
