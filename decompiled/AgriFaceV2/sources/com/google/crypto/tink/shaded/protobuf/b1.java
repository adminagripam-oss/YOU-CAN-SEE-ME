package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b1 implements n0 {

    /* renamed from: a  reason: collision with root package name */
    private final p0 f6053a;

    /* renamed from: b  reason: collision with root package name */
    private final String f6054b;

    /* renamed from: c  reason: collision with root package name */
    private final Object[] f6055c;

    /* renamed from: d  reason: collision with root package name */
    private final int f6056d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b1(p0 p0Var, String str, Object[] objArr) {
        char charAt;
        this.f6053a = p0Var;
        this.f6054b = str;
        this.f6055c = objArr;
        int charAt2 = str.charAt(0);
        if (charAt2 >= 55296) {
            int i8 = charAt2 & 8191;
            int i9 = 13;
            int i10 = 1;
            while (true) {
                int i11 = i10 + 1;
                charAt = str.charAt(i10);
                if (charAt < 55296) {
                    break;
                }
                i8 |= (charAt & 8191) << i9;
                i9 += 13;
                i10 = i11;
            }
            charAt2 = i8 | (charAt << i9);
        }
        this.f6056d = charAt2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public boolean a() {
        return (this.f6056d & 2) == 2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public y0 b() {
        int i8 = this.f6056d;
        return (i8 & 1) != 0 ? y0.PROTO2 : (i8 & 4) == 4 ? y0.EDITIONS : y0.PROTO3;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public p0 c() {
        return this.f6053a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object[] d() {
        return this.f6055c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String e() {
        return this.f6054b;
    }
}
