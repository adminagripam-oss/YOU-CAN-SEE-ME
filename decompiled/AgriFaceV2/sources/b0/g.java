package b0;

import b0.h1;
/* loaded from: classes.dex */
final class g extends h1.c {

    /* renamed from: a  reason: collision with root package name */
    private final int f4188a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4189b;

    /* renamed from: c  reason: collision with root package name */
    private final int f4190c;

    /* renamed from: d  reason: collision with root package name */
    private final int f4191d;

    /* renamed from: e  reason: collision with root package name */
    private final int f4192e;

    /* renamed from: f  reason: collision with root package name */
    private final int f4193f;

    /* renamed from: g  reason: collision with root package name */
    private final int f4194g;

    /* renamed from: h  reason: collision with root package name */
    private final int f4195h;

    /* renamed from: i  reason: collision with root package name */
    private final int f4196i;

    /* renamed from: j  reason: collision with root package name */
    private final int f4197j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(int i8, String str, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        this.f4188a = i8;
        if (str == null) {
            throw new NullPointerException("Null mediaType");
        }
        this.f4189b = str;
        this.f4190c = i9;
        this.f4191d = i10;
        this.f4192e = i11;
        this.f4193f = i12;
        this.f4194g = i13;
        this.f4195h = i14;
        this.f4196i = i15;
        this.f4197j = i16;
    }

    @Override // b0.h1.c
    public int b() {
        return this.f4195h;
    }

    @Override // b0.h1.c
    public int c() {
        return this.f4190c;
    }

    @Override // b0.h1.c
    public int d() {
        return this.f4196i;
    }

    @Override // b0.h1.c
    public int e() {
        return this.f4188a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h1.c) {
            h1.c cVar = (h1.c) obj;
            return this.f4188a == cVar.e() && this.f4189b.equals(cVar.i()) && this.f4190c == cVar.c() && this.f4191d == cVar.f() && this.f4192e == cVar.k() && this.f4193f == cVar.h() && this.f4194g == cVar.j() && this.f4195h == cVar.b() && this.f4196i == cVar.d() && this.f4197j == cVar.g();
        }
        return false;
    }

    @Override // b0.h1.c
    public int f() {
        return this.f4191d;
    }

    @Override // b0.h1.c
    public int g() {
        return this.f4197j;
    }

    @Override // b0.h1.c
    public int h() {
        return this.f4193f;
    }

    public int hashCode() {
        return ((((((((((((((((((this.f4188a ^ 1000003) * 1000003) ^ this.f4189b.hashCode()) * 1000003) ^ this.f4190c) * 1000003) ^ this.f4191d) * 1000003) ^ this.f4192e) * 1000003) ^ this.f4193f) * 1000003) ^ this.f4194g) * 1000003) ^ this.f4195h) * 1000003) ^ this.f4196i) * 1000003) ^ this.f4197j;
    }

    @Override // b0.h1.c
    public String i() {
        return this.f4189b;
    }

    @Override // b0.h1.c
    public int j() {
        return this.f4194g;
    }

    @Override // b0.h1.c
    public int k() {
        return this.f4192e;
    }

    public String toString() {
        return "VideoProfileProxy{codec=" + this.f4188a + ", mediaType=" + this.f4189b + ", bitrate=" + this.f4190c + ", frameRate=" + this.f4191d + ", width=" + this.f4192e + ", height=" + this.f4193f + ", profile=" + this.f4194g + ", bitDepth=" + this.f4195h + ", chromaSubsampling=" + this.f4196i + ", hdrFormat=" + this.f4197j + "}";
    }
}
