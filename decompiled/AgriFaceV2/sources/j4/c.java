package j4;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import y4.c0;
/* loaded from: classes.dex */
public final class c implements q {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f8246a;

    private c(InputStream inputStream) {
        this.f8246a = inputStream;
    }

    public static q c(byte[] bArr) {
        return new c(new ByteArrayInputStream(bArr));
    }

    @Override // j4.q
    public c0 a() {
        try {
            return c0.e0(this.f8246a, com.google.crypto.tink.shaded.protobuf.p.b());
        } finally {
            this.f8246a.close();
        }
    }

    @Override // j4.q
    public y4.t b() {
        try {
            return y4.t.Z(this.f8246a, com.google.crypto.tink.shaded.protobuf.p.b());
        } finally {
            this.f8246a.close();
        }
    }
}
