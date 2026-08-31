package b5;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class i implements h5.b {

    /* renamed from: a  reason: collision with root package name */
    private final m f4649a;

    /* renamed from: b  reason: collision with root package name */
    private final c f4650b;

    private i(m mVar, c cVar) {
        this.f4649a = mVar;
        this.f4650b = cVar;
    }

    public static h5.b a(m mVar, c cVar) {
        return new i(mVar, cVar);
    }

    @Override // h5.b
    public Object get() {
        Object a8;
        a8 = r1.d().a(new b0(this.f4650b, this.f4649a));
        return a8;
    }
}
