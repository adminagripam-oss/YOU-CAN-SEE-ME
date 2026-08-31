package p7;
/* loaded from: classes.dex */
public final class i extends RuntimeException {

    /* renamed from: e  reason: collision with root package name */
    private final transient t6.g f10150e;

    public i(t6.g gVar) {
        this.f10150e = gVar;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.f10150e.toString();
    }
}
