package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import w1.d;
/* loaded from: classes.dex */
public final class b0 {

    /* renamed from: f  reason: collision with root package name */
    public static final a f3740f = new a(null);

    /* renamed from: g  reason: collision with root package name */
    private static final Class[] f3741g = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    /* renamed from: a  reason: collision with root package name */
    private final Map f3742a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f3743b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f3744c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f3745d;

    /* renamed from: e  reason: collision with root package name */
    private final d.c f3746e;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final b0 a(Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new b0();
                }
                HashMap hashMap = new HashMap();
                for (String str : bundle2.keySet()) {
                    d7.k.d(str, "key");
                    hashMap.put(str, bundle2.get(str));
                }
                return new b0(hashMap);
            }
            ClassLoader classLoader = b0.class.getClassLoader();
            d7.k.b(classLoader);
            bundle.setClassLoader(classLoader);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            if ((parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) ? false : true) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int size = parcelableArrayList.size();
                for (int i8 = 0; i8 < size; i8++) {
                    Object obj = parcelableArrayList.get(i8);
                    d7.k.c(obj, "null cannot be cast to non-null type kotlin.String");
                    linkedHashMap.put((String) obj, parcelableArrayList2.get(i8));
                }
                return new b0(linkedHashMap);
            }
            throw new IllegalStateException("Invalid bundle passed as restored state".toString());
        }

        public final boolean b(Object obj) {
            Class[] clsArr;
            if (obj == null) {
                return true;
            }
            for (Class cls : b0.f3741g) {
                d7.k.b(cls);
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }
    }

    public b0() {
        this.f3742a = new LinkedHashMap();
        this.f3743b = new LinkedHashMap();
        this.f3744c = new LinkedHashMap();
        this.f3745d = new LinkedHashMap();
        this.f3746e = new d.c() { // from class: androidx.lifecycle.a0
            @Override // w1.d.c
            public final Bundle a() {
                Bundle d8;
                d8 = b0.d(b0.this);
                return d8;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle d(b0 b0Var) {
        Map i8;
        d7.k.e(b0Var, "this$0");
        i8 = s6.e0.i(b0Var.f3743b);
        for (Map.Entry entry : i8.entrySet()) {
            b0Var.e((String) entry.getKey(), ((d.c) entry.getValue()).a());
        }
        Set<String> keySet = b0Var.f3742a.keySet();
        ArrayList arrayList = new ArrayList(keySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : keySet) {
            arrayList.add(str);
            arrayList2.add(b0Var.f3742a.get(str));
        }
        return androidx.core.os.c.a(r6.o.a("keys", arrayList), r6.o.a("values", arrayList2));
    }

    public final d.c c() {
        return this.f3746e;
    }

    public final void e(String str, Object obj) {
        d7.k.e(str, "key");
        if (!f3740f.b(obj)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't put value with type ");
            d7.k.b(obj);
            sb.append(obj.getClass());
            sb.append(" into saved state");
            throw new IllegalArgumentException(sb.toString());
        }
        Object obj2 = this.f3744c.get(str);
        s sVar = obj2 instanceof s ? (s) obj2 : null;
        if (sVar != null) {
            sVar.o(obj);
        } else {
            this.f3742a.put(str, obj);
        }
        n7.j jVar = (n7.j) this.f3745d.get(str);
        if (jVar == null) {
            return;
        }
        jVar.setValue(obj);
    }

    public b0(Map map) {
        d7.k.e(map, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.f3742a = linkedHashMap;
        this.f3743b = new LinkedHashMap();
        this.f3744c = new LinkedHashMap();
        this.f3745d = new LinkedHashMap();
        this.f3746e = new d.c() { // from class: androidx.lifecycle.a0
            @Override // w1.d.c
            public final Bundle a() {
                Bundle d8;
                d8 = b0.d(b0.this);
                return d8;
            }
        };
        linkedHashMap.putAll(map);
    }
}
