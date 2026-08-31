package r5;

import a4.ba;
import a4.ca;
import a4.da;
import a4.ea;
import a4.ed;
import a4.g9;
import a4.h9;
import a4.i9;
import a4.k9;
import a4.l9;
import a4.n9;
import a4.od;
import a4.qd;
import a4.td;
import a4.xa;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public abstract class k {

    /* renamed from: a  reason: collision with root package name */
    static final AtomicReference f11351a = new AtomicReference();

    public static n9 a(q5.e eVar) {
        g9 g9Var = new g9();
        int d8 = eVar.d();
        g9Var.d(d8 != 1 ? d8 != 2 ? k9.UNKNOWN_LANDMARKS : k9.ALL_LANDMARKS : k9.NO_LANDMARKS);
        int b8 = eVar.b();
        g9Var.a(b8 != 1 ? b8 != 2 ? h9.UNKNOWN_CLASSIFICATIONS : h9.ALL_CLASSIFICATIONS : h9.NO_CLASSIFICATIONS);
        int e8 = eVar.e();
        g9Var.f(e8 != 1 ? e8 != 2 ? l9.UNKNOWN_PERFORMANCE : l9.ACCURATE : l9.FAST);
        int c8 = eVar.c();
        g9Var.b(c8 != 1 ? c8 != 2 ? i9.UNKNOWN_CONTOURS : i9.ALL_CONTOURS : i9.NO_CONTOURS);
        g9Var.c(Boolean.valueOf(eVar.g()));
        g9Var.e(Float.valueOf(eVar.a()));
        return g9Var.k();
    }

    public static String b() {
        return true != d() ? "play-services-mlkit-face-detection" : "face-detection";
    }

    public static void c(qd qdVar, final boolean z7, final ca caVar) {
        qdVar.f(new od() { // from class: r5.j
            @Override // a4.od
            public final ed zza() {
                boolean z8 = z7;
                ca caVar2 = caVar;
                ea eaVar = new ea();
                eaVar.e(z8 ? ba.TYPE_THICK : ba.TYPE_THIN);
                xa xaVar = new xa();
                xaVar.b(caVar2);
                eaVar.h(xaVar.c());
                return td.a(eaVar);
            }
        }, da.ON_DEVICE_FACE_LOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d() {
        AtomicReference atomicReference = f11351a;
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        boolean c8 = b.c(m5.i.c().b());
        atomicReference.set(Boolean.valueOf(c8));
        return c8;
    }
}
