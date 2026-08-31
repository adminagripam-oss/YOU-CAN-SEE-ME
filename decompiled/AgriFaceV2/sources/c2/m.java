package c2;

import java.util.Objects;
/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private final n[] f4764a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4765b;

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f4766c;

    /* renamed from: d  reason: collision with root package name */
    private final int f4767d;

    public m(String str, n[] nVarArr) {
        this.f4765b = str;
        this.f4766c = null;
        this.f4764a = nVarArr;
        this.f4767d = 0;
    }

    private void a(int i8) {
        if (i8 == this.f4767d) {
            return;
        }
        throw new IllegalStateException("Wrong data accessor type detected. " + f(this.f4767d) + " expected, but got " + f(i8));
    }

    private String f(int i8) {
        return i8 != 0 ? i8 != 1 ? "Unknown" : "ArrayBuffer" : "String";
    }

    public byte[] b() {
        a(1);
        Objects.requireNonNull(this.f4766c);
        return this.f4766c;
    }

    public String c() {
        a(0);
        return this.f4765b;
    }

    public n[] d() {
        return this.f4764a;
    }

    public int e() {
        return this.f4767d;
    }

    public m(byte[] bArr, n[] nVarArr) {
        Objects.requireNonNull(bArr);
        this.f4766c = bArr;
        this.f4765b = null;
        this.f4764a = nVarArr;
        this.f4767d = 1;
    }
}
