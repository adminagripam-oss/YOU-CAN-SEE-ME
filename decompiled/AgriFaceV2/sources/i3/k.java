package i3;
/* loaded from: classes.dex */
public final class k extends UnsupportedOperationException {

    /* renamed from: e  reason: collision with root package name */
    private final h3.c f7507e;

    public k(h3.c cVar) {
        this.f7507e = cVar;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Missing ".concat(String.valueOf(this.f7507e));
    }
}
