package r7;
/* loaded from: classes.dex */
public final class c extends f {

    /* renamed from: m  reason: collision with root package name */
    public static final c f11403m = new c();

    private c() {
        super(l.f11416c, l.f11417d, l.f11418e, l.f11414a);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // k7.a0
    public String toString() {
        return "Dispatchers.Default";
    }
}
