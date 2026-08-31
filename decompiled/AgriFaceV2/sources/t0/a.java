package t0;

import b0.h1;
import java.util.List;
/* loaded from: classes.dex */
final class a extends g {

    /* renamed from: a  reason: collision with root package name */
    private final int f11629a;

    /* renamed from: b  reason: collision with root package name */
    private final int f11630b;

    /* renamed from: c  reason: collision with root package name */
    private final List f11631c;

    /* renamed from: d  reason: collision with root package name */
    private final List f11632d;

    /* renamed from: e  reason: collision with root package name */
    private final h1.a f11633e;

    /* renamed from: f  reason: collision with root package name */
    private final h1.c f11634f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(int i8, int i9, List list, List list2, h1.a aVar, h1.c cVar) {
        this.f11629a = i8;
        this.f11630b = i9;
        if (list == null) {
            throw new NullPointerException("Null audioProfiles");
        }
        this.f11631c = list;
        if (list2 == null) {
            throw new NullPointerException("Null videoProfiles");
        }
        this.f11632d = list2;
        this.f11633e = aVar;
        if (cVar == null) {
            throw new NullPointerException("Null defaultVideoProfile");
        }
        this.f11634f = cVar;
    }

    @Override // b0.h1
    public int a() {
        return this.f11630b;
    }

    @Override // b0.h1
    public List b() {
        return this.f11631c;
    }

    @Override // b0.h1
    public List c() {
        return this.f11632d;
    }

    public boolean equals(Object obj) {
        h1.a aVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            return this.f11629a == gVar.g() && this.f11630b == gVar.a() && this.f11631c.equals(gVar.b()) && this.f11632d.equals(gVar.c()) && ((aVar = this.f11633e) != null ? aVar.equals(gVar.j()) : gVar.j() == null) && this.f11634f.equals(gVar.k());
        }
        return false;
    }

    @Override // b0.h1
    public int g() {
        return this.f11629a;
    }

    public int hashCode() {
        int hashCode = (((((((this.f11629a ^ 1000003) * 1000003) ^ this.f11630b) * 1000003) ^ this.f11631c.hashCode()) * 1000003) ^ this.f11632d.hashCode()) * 1000003;
        h1.a aVar = this.f11633e;
        return ((hashCode ^ (aVar == null ? 0 : aVar.hashCode())) * 1000003) ^ this.f11634f.hashCode();
    }

    @Override // t0.g
    public h1.a j() {
        return this.f11633e;
    }

    @Override // t0.g
    public h1.c k() {
        return this.f11634f;
    }

    public String toString() {
        return "VideoValidatedEncoderProfilesProxy{defaultDurationSeconds=" + this.f11629a + ", recommendedFileFormat=" + this.f11630b + ", audioProfiles=" + this.f11631c + ", videoProfiles=" + this.f11632d + ", defaultAudioProfile=" + this.f11633e + ", defaultVideoProfile=" + this.f11634f + "}";
    }
}
