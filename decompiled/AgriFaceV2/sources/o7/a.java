package o7;

import java.util.Arrays;
import r6.k;
import r6.r;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private c[] f9881a;

    /* renamed from: b  reason: collision with root package name */
    private int f9882b;

    /* renamed from: c  reason: collision with root package name */
    private int f9883c;

    /* JADX INFO: Access modifiers changed from: protected */
    public final c d() {
        c cVar;
        synchronized (this) {
            c[] cVarArr = this.f9881a;
            if (cVarArr == null) {
                cVarArr = f(2);
                this.f9881a = cVarArr;
            } else if (this.f9882b >= cVarArr.length) {
                Object[] copyOf = Arrays.copyOf(cVarArr, cVarArr.length * 2);
                d7.k.d(copyOf, "copyOf(this, newSize)");
                this.f9881a = (c[]) copyOf;
                cVarArr = (c[]) copyOf;
            }
            int i8 = this.f9883c;
            do {
                cVar = cVarArr[i8];
                if (cVar == null) {
                    cVar = e();
                    cVarArr[i8] = cVar;
                }
                i8++;
                if (i8 >= cVarArr.length) {
                    i8 = 0;
                }
                d7.k.c(cVar, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
            } while (!cVar.a(this));
            this.f9883c = i8;
            this.f9882b++;
        }
        return cVar;
    }

    protected abstract c e();

    protected abstract c[] f(int i8);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void g(c cVar) {
        int i8;
        t6.d[] b8;
        synchronized (this) {
            int i9 = this.f9882b - 1;
            this.f9882b = i9;
            if (i9 == 0) {
                this.f9883c = 0;
            }
            d7.k.c(cVar, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
            b8 = cVar.b(this);
        }
        for (t6.d dVar : b8) {
            if (dVar != null) {
                k.a aVar = r6.k.f11363e;
                dVar.i(r6.k.a(r.f11372a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final c[] h() {
        return this.f9881a;
    }
}
