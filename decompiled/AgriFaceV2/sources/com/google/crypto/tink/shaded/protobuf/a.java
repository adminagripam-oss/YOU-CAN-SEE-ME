package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.h;
import com.google.crypto.tink.shaded.protobuf.p0;
import java.io.IOException;
/* loaded from: classes.dex */
public abstract class a implements p0 {
    protected int memoizedHashCode = 0;

    /* renamed from: com.google.crypto.tink.shaded.protobuf.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0066a implements p0.a {
        /* JADX INFO: Access modifiers changed from: protected */
        public static i1 g(p0 p0Var) {
            return new i1(p0Var);
        }
    }

    private String g(String str) {
        return "Serializing " + getClass().getName() + " to a " + str + " threw an IOException (should never happen).";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int b(d1 d1Var);

    /* JADX INFO: Access modifiers changed from: package-private */
    public i1 h() {
        return new i1(this);
    }

    public byte[] i() {
        try {
            byte[] bArr = new byte[a()];
            k S = k.S(bArr);
            d(S);
            S.c();
            return bArr;
        } catch (IOException e8) {
            throw new RuntimeException(g("byte array"), e8);
        }
    }

    public h j() {
        try {
            h.C0067h r8 = h.r(a());
            d(r8.b());
            return r8.a();
        } catch (IOException e8) {
            throw new RuntimeException(g("ByteString"), e8);
        }
    }
}
