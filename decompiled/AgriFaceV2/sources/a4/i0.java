package a4;

import java.util.Arrays;
/* loaded from: classes.dex */
abstract class i0 extends j0 {

    /* renamed from: a  reason: collision with root package name */
    Object[] f660a = new Object[4];

    /* renamed from: b  reason: collision with root package name */
    int f661b = 0;

    /* renamed from: c  reason: collision with root package name */
    boolean f662c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i0(int i8) {
    }

    private final void b(int i8) {
        Object[] objArr = this.f660a;
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
            this.f660a = Arrays.copyOf(objArr, i9);
        } else if (!this.f662c) {
            return;
        } else {
            this.f660a = (Object[]) objArr.clone();
        }
        this.f662c = false;
    }

    public final i0 a(Object obj) {
        obj.getClass();
        b(this.f661b + 1);
        Object[] objArr = this.f660a;
        int i8 = this.f661b;
        this.f661b = i8 + 1;
        objArr[i8] = obj;
        return this;
    }
}
