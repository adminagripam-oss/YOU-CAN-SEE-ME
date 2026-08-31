package androidx.webkit.internal;

import java.util.Objects;
import org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface;
/* loaded from: classes.dex */
public class b1 implements WebMessagePayloadBoundaryInterface {

    /* renamed from: a  reason: collision with root package name */
    private final int f4008a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4009b;

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f4010c;

    public b1(String str) {
        this.f4008a = 0;
        this.f4009b = str;
        this.f4010c = null;
    }

    private void a(int i8) {
        if (this.f4008a == i8) {
            return;
        }
        throw new IllegalStateException("Expected " + i8 + ", but type is " + this.f4008a);
    }

    @Override // org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface
    public byte[] getAsArrayBuffer() {
        a(1);
        byte[] bArr = this.f4010c;
        Objects.requireNonNull(bArr);
        return bArr;
    }

    @Override // org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface
    public String getAsString() {
        a(0);
        return this.f4009b;
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[0];
    }

    @Override // org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface
    public int getType() {
        return this.f4008a;
    }

    public b1(byte[] bArr) {
        this.f4008a = 1;
        this.f4009b = null;
        this.f4010c = bArr;
    }
}
