package u2;

import java.util.Arrays;
/* loaded from: classes.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    private final s2.b f12074a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f12075b;

    public g(s2.b bVar, byte[] bArr) {
        if (bVar == null) {
            throw new NullPointerException("encoding is null");
        }
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        this.f12074a = bVar;
        this.f12075b = bArr;
    }

    public byte[] a() {
        return this.f12075b;
    }

    public s2.b b() {
        return this.f12074a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (this.f12074a.equals(gVar.f12074a)) {
                return Arrays.equals(this.f12075b, gVar.f12075b);
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((this.f12074a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.f12075b);
    }

    public String toString() {
        return "EncodedPayload{encoding=" + this.f12074a + ", bytes=[...]}";
    }
}
