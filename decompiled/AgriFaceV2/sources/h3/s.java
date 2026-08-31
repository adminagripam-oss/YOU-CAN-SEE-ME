package h3;

import java.util.Arrays;
/* loaded from: classes.dex */
final class s extends r {

    /* renamed from: f  reason: collision with root package name */
    private final byte[] f7250f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.f7250f = bArr;
    }

    @Override // h3.r
    final byte[] f() {
        return this.f7250f;
    }
}
