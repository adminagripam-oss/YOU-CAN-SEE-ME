package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.z;
import java.util.List;
/* loaded from: classes.dex */
final class e0 implements d0 {
    static z.d d(Object obj, long j8) {
        return (z.d) m1.C(obj, j8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d0
    public void a(Object obj, long j8) {
        d(obj, j8).d();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d0
    public List b(Object obj, long j8) {
        z.d d8 = d(obj, j8);
        if (d8.i()) {
            return d8;
        }
        int size = d8.size();
        z.d e8 = d8.e(size == 0 ? 10 : size * 2);
        m1.R(obj, j8, e8);
        return e8;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d0
    public void c(Object obj, Object obj2, long j8) {
        z.d d8 = d(obj, j8);
        z.d d9 = d(obj2, j8);
        int size = d8.size();
        int size2 = d9.size();
        if (size > 0 && size2 > 0) {
            if (!d8.i()) {
                d8 = d8.e(size2 + size);
            }
            d8.addAll(d9);
        }
        if (size > 0) {
            d9 = d8;
        }
        m1.R(obj, j8, d9);
    }
}
