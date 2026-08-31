package h3;

import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
abstract class t extends r {

    /* renamed from: g  reason: collision with root package name */
    private static final WeakReference f7251g = new WeakReference(null);

    /* renamed from: f  reason: collision with root package name */
    private WeakReference f7252f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(byte[] bArr) {
        super(bArr);
        this.f7252f = f7251g;
    }

    protected abstract byte[] d0();

    @Override // h3.r
    final byte[] f() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.f7252f.get();
            if (bArr == null) {
                bArr = d0();
                this.f7252f = new WeakReference(bArr);
            }
        }
        return bArr;
    }
}
