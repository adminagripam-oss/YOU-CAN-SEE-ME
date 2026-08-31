package a4;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d2 implements d5.g {

    /* renamed from: a  reason: collision with root package name */
    private boolean f353a = false;

    /* renamed from: b  reason: collision with root package name */
    private boolean f354b = false;

    /* renamed from: c  reason: collision with root package name */
    private d5.c f355c;

    /* renamed from: d  reason: collision with root package name */
    private final y1 f356d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d2(y1 y1Var) {
        this.f356d = y1Var;
    }

    private final void c() {
        if (this.f353a) {
            throw new d5.b("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f353a = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(d5.c cVar, boolean z7) {
        this.f353a = false;
        this.f355c = cVar;
        this.f354b = z7;
    }

    @Override // d5.g
    public final d5.g b(String str) {
        c();
        this.f356d.e(this.f355c, str, this.f354b);
        return this;
    }

    @Override // d5.g
    public final d5.g d(boolean z7) {
        c();
        this.f356d.f(this.f355c, z7 ? 1 : 0, this.f354b);
        return this;
    }
}
