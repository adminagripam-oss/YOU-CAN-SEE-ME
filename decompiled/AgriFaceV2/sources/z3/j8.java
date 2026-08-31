package z3;

import java.util.Arrays;
/* loaded from: classes.dex */
abstract class j8 extends k9 {

    /* renamed from: a  reason: collision with root package name */
    Object[] f13789a = new Object[4];

    /* renamed from: b  reason: collision with root package name */
    int f13790b = 0;

    /* renamed from: c  reason: collision with root package name */
    boolean f13791c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j8(int i8) {
    }

    private final void b(int i8) {
        Object[] objArr = this.f13789a;
        int length = objArr.length;
        if (length < i8) {
            int i9 = length + (length >> 1) + 1;
            if (i9 < i8) {
                int highestOneBit = Integer.highestOneBit(i8 - 1);
                i9 = highestOneBit + highestOneBit;
            }
            if (i9 < 0) {
                i9 = Integer.MAX_VALUE;
            }
            this.f13789a = Arrays.copyOf(objArr, i9);
        } else if (!this.f13791c) {
            return;
        } else {
            this.f13789a = (Object[]) objArr.clone();
        }
        this.f13791c = false;
    }

    public final j8 a(Object obj) {
        obj.getClass();
        b(this.f13790b + 1);
        Object[] objArr = this.f13789a;
        int i8 = this.f13790b;
        this.f13790b = i8 + 1;
        objArr[i8] = obj;
        return this;
    }
}
