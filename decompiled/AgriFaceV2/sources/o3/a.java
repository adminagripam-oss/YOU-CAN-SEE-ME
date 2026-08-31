package o3;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import k3.p;
import k3.q;
/* loaded from: classes.dex */
public class a extends l3.a {
    public static final Parcelable.Creator<a> CREATOR = new d();

    /* renamed from: e  reason: collision with root package name */
    private static final Comparator f9834e = new Comparator() { // from class: o3.c
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            h3.c cVar = (h3.c) obj;
            h3.c cVar2 = (h3.c) obj2;
            Parcelable.Creator<a> creator = a.CREATOR;
            return !cVar.a().equals(cVar2.a()) ? cVar.a().compareTo(cVar2.a()) : (cVar.b() > cVar2.b() ? 1 : (cVar.b() == cVar2.b() ? 0 : -1));
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final List f9835a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f9836b;

    /* renamed from: c  reason: collision with root package name */
    private final String f9837c;

    /* renamed from: d  reason: collision with root package name */
    private final String f9838d;

    public a(List list, boolean z7, String str, String str2) {
        q.g(list);
        this.f9835a = list;
        this.f9836b = z7;
        this.f9837c = str;
        this.f9838d = str2;
    }

    public static a a(n3.f fVar) {
        return c(fVar.a(), true);
    }

    static a c(List list, boolean z7) {
        TreeSet treeSet = new TreeSet(f9834e);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Collections.addAll(treeSet, ((i3.g) it.next()).b());
        }
        return new a(new ArrayList(treeSet), z7, null, null);
    }

    public List b() {
        return this.f9835a;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof a)) {
            a aVar = (a) obj;
            return this.f9836b == aVar.f9836b && p.a(this.f9835a, aVar.f9835a) && p.a(this.f9837c, aVar.f9837c) && p.a(this.f9838d, aVar.f9838d);
        }
        return false;
    }

    public final int hashCode() {
        return p.b(Boolean.valueOf(this.f9836b), this.f9835a, this.f9837c, this.f9838d);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.n(parcel, 1, b(), false);
        l3.c.c(parcel, 2, this.f9836b);
        l3.c.k(parcel, 3, this.f9837c, false);
        l3.c.k(parcel, 4, this.f9838d, false);
        l3.c.b(parcel, a8);
    }
}
