package b0;

import b0.h1;
/* loaded from: classes.dex */
final class e extends h1.a {

    /* renamed from: a  reason: collision with root package name */
    private final int f4167a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4168b;

    /* renamed from: c  reason: collision with root package name */
    private final int f4169c;

    /* renamed from: d  reason: collision with root package name */
    private final int f4170d;

    /* renamed from: e  reason: collision with root package name */
    private final int f4171e;

    /* renamed from: f  reason: collision with root package name */
    private final int f4172f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(int i8, String str, int i9, int i10, int i11, int i12) {
        this.f4167a = i8;
        if (str == null) {
            throw new NullPointerException("Null mediaType");
        }
        this.f4168b = str;
        this.f4169c = i9;
        this.f4170d = i10;
        this.f4171e = i11;
        this.f4172f = i12;
    }

    @Override // b0.h1.a
    public int b() {
        return this.f4169c;
    }

    @Override // b0.h1.a
    public int c() {
        return this.f4171e;
    }

    @Override // b0.h1.a
    public int d() {
        return this.f4167a;
    }

    @Override // b0.h1.a
    public String e() {
        return this.f4168b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h1.a) {
            h1.a aVar = (h1.a) obj;
            return this.f4167a == aVar.d() && this.f4168b.equals(aVar.e()) && this.f4169c == aVar.b() && this.f4170d == aVar.g() && this.f4171e == aVar.c() && this.f4172f == aVar.f();
        }
        return false;
    }

    @Override // b0.h1.a
    public int f() {
        return this.f4172f;
    }

    @Override // b0.h1.a
    public int g() {
        return this.f4170d;
    }

    public int hashCode() {
        return ((((((((((this.f4167a ^ 1000003) * 1000003) ^ this.f4168b.hashCode()) * 1000003) ^ this.f4169c) * 1000003) ^ this.f4170d) * 1000003) ^ this.f4171e) * 1000003) ^ this.f4172f;
    }

    public String toString() {
        return "AudioProfileProxy{codec=" + this.f4167a + ", mediaType=" + this.f4168b + ", bitrate=" + this.f4169c + ", sampleRate=" + this.f4170d + ", channels=" + this.f4171e + ", profile=" + this.f4172f + "}";
    }
}
