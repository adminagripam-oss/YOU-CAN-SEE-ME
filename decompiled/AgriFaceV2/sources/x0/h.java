package x0;

import b0.h1;
import x0.e;
/* loaded from: classes.dex */
final class h extends e {

    /* renamed from: a  reason: collision with root package name */
    private final String f12619a;

    /* renamed from: b  reason: collision with root package name */
    private final int f12620b;

    /* renamed from: c  reason: collision with root package name */
    private final h1.a f12621c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends e.a {

        /* renamed from: a  reason: collision with root package name */
        private String f12622a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f12623b;

        /* renamed from: c  reason: collision with root package name */
        private h1.a f12624c;

        @Override // x0.e.a
        public e b() {
            String str = "";
            if (this.f12622a == null) {
                str = " mimeType";
            }
            if (this.f12623b == null) {
                str = str + " profile";
            }
            if (str.isEmpty()) {
                return new h(this.f12622a, this.f12623b.intValue(), this.f12624c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // x0.e.a
        public e.a c(h1.a aVar) {
            this.f12624c = aVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public e.a d(String str) {
            if (str != null) {
                this.f12622a = str;
                return this;
            }
            throw new NullPointerException("Null mimeType");
        }

        @Override // x0.j.a
        /* renamed from: e */
        public e.a a(int i8) {
            this.f12623b = Integer.valueOf(i8);
            return this;
        }
    }

    private h(String str, int i8, h1.a aVar) {
        this.f12619a = str;
        this.f12620b = i8;
        this.f12621c = aVar;
    }

    @Override // x0.j
    public String a() {
        return this.f12619a;
    }

    @Override // x0.j
    public int b() {
        return this.f12620b;
    }

    @Override // x0.e
    public h1.a d() {
        return this.f12621c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            if (this.f12619a.equals(eVar.a()) && this.f12620b == eVar.b()) {
                h1.a aVar = this.f12621c;
                h1.a d8 = eVar.d();
                if (aVar == null) {
                    if (d8 == null) {
                        return true;
                    }
                } else if (aVar.equals(d8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f12619a.hashCode() ^ 1000003) * 1000003) ^ this.f12620b) * 1000003;
        h1.a aVar = this.f12621c;
        return hashCode ^ (aVar == null ? 0 : aVar.hashCode());
    }

    public String toString() {
        return "AudioMimeInfo{mimeType=" + this.f12619a + ", profile=" + this.f12620b + ", compatibleAudioProfile=" + this.f12621c + "}";
    }
}
