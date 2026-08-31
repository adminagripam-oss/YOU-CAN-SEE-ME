package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Arrays;
/* loaded from: classes.dex */
public final class s extends p {
    public s() {
        super(4);
    }

    public final s a(Object obj) {
        obj.getClass();
        int i8 = this.f5520b + 1;
        Object[] objArr = this.f5519a;
        int length = objArr.length;
        if (length >= i8) {
            if (this.f5521c) {
                this.f5519a = (Object[]) objArr.clone();
            }
            Object[] objArr2 = this.f5519a;
            int i9 = this.f5520b;
            this.f5520b = i9 + 1;
            objArr2[i9] = obj;
            return this;
        }
        int i10 = length + (length >> 1) + 1;
        if (i10 < i8) {
            int highestOneBit = Integer.highestOneBit(i8 - 1);
            i10 = highestOneBit + highestOneBit;
        }
        if (i10 < 0) {
            i10 = Integer.MAX_VALUE;
        }
        this.f5519a = Arrays.copyOf(objArr, i10);
        this.f5521c = false;
        Object[] objArr22 = this.f5519a;
        int i92 = this.f5520b;
        this.f5520b = i92 + 1;
        objArr22[i92] = obj;
        return this;
    }

    public final v b() {
        this.f5521c = true;
        return v.m(this.f5519a, this.f5520b);
    }
}
