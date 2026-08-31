package g0;
/* loaded from: classes.dex */
final class b extends g {

    /* renamed from: a  reason: collision with root package name */
    private final float f7055a;

    /* renamed from: b  reason: collision with root package name */
    private final float f7056b;

    /* renamed from: c  reason: collision with root package name */
    private final float f7057c;

    /* renamed from: d  reason: collision with root package name */
    private final float f7058d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(float f8, float f9, float f10, float f11) {
        this.f7055a = f8;
        this.f7056b = f9;
        this.f7057c = f10;
        this.f7058d = f11;
    }

    @Override // g0.g, y.j2
    public float a() {
        return this.f7056b;
    }

    @Override // g0.g, y.j2
    public float b() {
        return this.f7055a;
    }

    @Override // g0.g, y.j2
    public float c() {
        return this.f7058d;
    }

    @Override // g0.g, y.j2
    public float d() {
        return this.f7057c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            return Float.floatToIntBits(this.f7055a) == Float.floatToIntBits(gVar.b()) && Float.floatToIntBits(this.f7056b) == Float.floatToIntBits(gVar.a()) && Float.floatToIntBits(this.f7057c) == Float.floatToIntBits(gVar.d()) && Float.floatToIntBits(this.f7058d) == Float.floatToIntBits(gVar.c());
        }
        return false;
    }

    public int hashCode() {
        return ((((((Float.floatToIntBits(this.f7055a) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.f7056b)) * 1000003) ^ Float.floatToIntBits(this.f7057c)) * 1000003) ^ Float.floatToIntBits(this.f7058d);
    }

    public String toString() {
        return "ImmutableZoomState{zoomRatio=" + this.f7055a + ", maxZoomRatio=" + this.f7056b + ", minZoomRatio=" + this.f7057c + ", linearZoom=" + this.f7058d + "}";
    }
}
