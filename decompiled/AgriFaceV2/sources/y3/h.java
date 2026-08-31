package y3;

import java.util.Arrays;
/* loaded from: classes.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    Object[] f13228a = new Object[8];

    /* renamed from: b  reason: collision with root package name */
    int f13229b = 0;

    /* renamed from: c  reason: collision with root package name */
    g f13230c;

    public final h a(Object obj, Object obj2) {
        int i8 = this.f13229b + 1;
        Object[] objArr = this.f13228a;
        int length = objArr.length;
        int i9 = i8 + i8;
        if (i9 > length) {
            this.f13228a = Arrays.copyOf(objArr, b.a(length, i9));
        }
        w0.a(obj, obj2);
        Object[] objArr2 = this.f13228a;
        int i10 = this.f13229b;
        int i11 = i10 + i10;
        objArr2[i11] = obj;
        objArr2[i11 + 1] = obj2;
        this.f13229b = i10 + 1;
        return this;
    }

    public final i b() {
        g gVar = this.f13230c;
        if (gVar == null) {
            q g8 = q.g(this.f13229b, this.f13228a, this);
            g gVar2 = this.f13230c;
            if (gVar2 == null) {
                return g8;
            }
            throw gVar2.a();
        }
        throw gVar.a();
    }
}
