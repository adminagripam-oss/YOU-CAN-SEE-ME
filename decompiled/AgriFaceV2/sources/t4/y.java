package t4;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class y {

    /* renamed from: b  reason: collision with root package name */
    private static final a5.a f11890b = a5.a.a(new byte[0]);

    /* renamed from: a  reason: collision with root package name */
    private final Map f11891a;

    /* loaded from: classes.dex */
    class a implements Iterable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ List f11892e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ List f11893f;

        a(List list, List list2) {
            this.f11892e = list;
            this.f11893f = list2;
        }

        @Override // java.lang.Iterable
        public Iterator iterator() {
            return new c(this.f11892e.iterator(), this.f11893f.iterator(), null);
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Map f11895a = new HashMap();

        public y a() {
            return new y(this.f11895a, null);
        }

        public b b(a5.a aVar, Object obj) {
            List list;
            if (aVar.c() == 0 || aVar.c() == 5) {
                if (this.f11895a.containsKey(aVar)) {
                    list = (List) this.f11895a.get(aVar);
                } else {
                    ArrayList arrayList = new ArrayList();
                    this.f11895a.put(aVar, arrayList);
                    list = arrayList;
                }
                list.add(obj);
                return this;
            }
            throw new GeneralSecurityException("PrefixMap only supports 0 and 5 byte prefixes");
        }
    }

    /* loaded from: classes.dex */
    private static class c implements Iterator {

        /* renamed from: e  reason: collision with root package name */
        private final Iterator f11896e;

        /* renamed from: f  reason: collision with root package name */
        private final Iterator f11897f;

        private c(Iterator it, Iterator it2) {
            this.f11896e = it;
            this.f11897f = it2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f11896e.hasNext() || this.f11897f.hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            return (this.f11896e.hasNext() ? this.f11896e : this.f11897f).next();
        }

        /* synthetic */ c(Iterator it, Iterator it2, a aVar) {
            this(it, it2);
        }
    }

    private y(Map map) {
        this.f11891a = map;
    }

    public Iterable a(byte[] bArr) {
        List list = (List) this.f11891a.get(f11890b);
        List list2 = bArr.length >= 5 ? (List) this.f11891a.get(a5.a.b(bArr, 0, 5)) : null;
        return (list == null && list2 == null) ? new ArrayList() : list == null ? list2 : list2 == null ? list : new a(list2, list);
    }

    /* synthetic */ y(Map map, a aVar) {
        this(map);
    }
}
