package u2;

import java.util.Arrays;
import u2.m;
/* loaded from: classes.dex */
final class c extends m {

    /* renamed from: a  reason: collision with root package name */
    private final String f12055a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f12056b;

    /* renamed from: c  reason: collision with root package name */
    private final s2.d f12057c;

    /* loaded from: classes.dex */
    static final class b extends m.a {

        /* renamed from: a  reason: collision with root package name */
        private String f12058a;

        /* renamed from: b  reason: collision with root package name */
        private byte[] f12059b;

        /* renamed from: c  reason: collision with root package name */
        private s2.d f12060c;

        @Override // u2.m.a
        public m a() {
            String str = "";
            if (this.f12058a == null) {
                str = " backendName";
            }
            if (this.f12060c == null) {
                str = str + " priority";
            }
            if (str.isEmpty()) {
                return new c(this.f12058a, this.f12059b, this.f12060c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // u2.m.a
        public m.a b(String str) {
            if (str != null) {
                this.f12058a = str;
                return this;
            }
            throw new NullPointerException("Null backendName");
        }

        @Override // u2.m.a
        public m.a c(byte[] bArr) {
            this.f12059b = bArr;
            return this;
        }

        @Override // u2.m.a
        public m.a d(s2.d dVar) {
            if (dVar != null) {
                this.f12060c = dVar;
                return this;
            }
            throw new NullPointerException("Null priority");
        }
    }

    private c(String str, byte[] bArr, s2.d dVar) {
        this.f12055a = str;
        this.f12056b = bArr;
        this.f12057c = dVar;
    }

    @Override // u2.m
    public String b() {
        return this.f12055a;
    }

    @Override // u2.m
    public byte[] c() {
        return this.f12056b;
    }

    @Override // u2.m
    public s2.d d() {
        return this.f12057c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof m) {
            m mVar = (m) obj;
            if (this.f12055a.equals(mVar.b())) {
                if (Arrays.equals(this.f12056b, mVar instanceof c ? ((c) mVar).f12056b : mVar.c()) && this.f12057c.equals(mVar.d())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f12055a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.f12056b)) * 1000003) ^ this.f12057c.hashCode();
    }
}
