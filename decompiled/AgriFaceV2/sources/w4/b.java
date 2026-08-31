package w4;

import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class b extends e {

    /* renamed from: a  reason: collision with root package name */
    private final int f12476a;

    private b(int i8) {
        this.f12476a = i8;
    }

    public static b b(int i8) {
        if (i8 == 16 || i8 == 32) {
            return new b(i8);
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit and 256-bit are supported", Integer.valueOf(i8 * 8)));
    }

    @Override // j4.w
    public boolean a() {
        return false;
    }

    public int c() {
        return this.f12476a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof b) && ((b) obj).c() == c();
    }

    public int hashCode() {
        return Objects.hash(b.class, Integer.valueOf(this.f12476a));
    }

    public String toString() {
        return "AesCmac PRF Parameters (" + this.f12476a + "-byte key)";
    }
}
