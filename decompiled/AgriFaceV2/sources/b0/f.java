package b0;

import b0.h1;
import java.util.List;
/* loaded from: classes.dex */
final class f extends h1.b {

    /* renamed from: a  reason: collision with root package name */
    private final int f4175a;

    /* renamed from: b  reason: collision with root package name */
    private final int f4176b;

    /* renamed from: c  reason: collision with root package name */
    private final List f4177c;

    /* renamed from: d  reason: collision with root package name */
    private final List f4178d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(int i8, int i9, List list, List list2) {
        this.f4175a = i8;
        this.f4176b = i9;
        if (list == null) {
            throw new NullPointerException("Null audioProfiles");
        }
        this.f4177c = list;
        if (list2 == null) {
            throw new NullPointerException("Null videoProfiles");
        }
        this.f4178d = list2;
    }

    @Override // b0.h1
    public int a() {
        return this.f4176b;
    }

    @Override // b0.h1
    public List b() {
        return this.f4177c;
    }

    @Override // b0.h1
    public List c() {
        return this.f4178d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h1.b) {
            h1.b bVar = (h1.b) obj;
            return this.f4175a == bVar.g() && this.f4176b == bVar.a() && this.f4177c.equals(bVar.b()) && this.f4178d.equals(bVar.c());
        }
        return false;
    }

    @Override // b0.h1
    public int g() {
        return this.f4175a;
    }

    public int hashCode() {
        return ((((((this.f4175a ^ 1000003) * 1000003) ^ this.f4176b) * 1000003) ^ this.f4177c.hashCode()) * 1000003) ^ this.f4178d.hashCode();
    }

    public String toString() {
        return "ImmutableEncoderProfilesProxy{defaultDurationSeconds=" + this.f4175a + ", recommendedFileFormat=" + this.f4176b + ", audioProfiles=" + this.f4177c + ", videoProfiles=" + this.f4178d + "}";
    }
}
