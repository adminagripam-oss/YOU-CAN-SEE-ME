package d7;

import c7.w;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import s6.d0;
import s6.e0;
/* loaded from: classes.dex */
public final class e implements h7.b, d {

    /* renamed from: b  reason: collision with root package name */
    public static final a f6469b = new a(null);

    /* renamed from: c  reason: collision with root package name */
    private static final Map f6470c;

    /* renamed from: d  reason: collision with root package name */
    private static final HashMap f6471d;

    /* renamed from: e  reason: collision with root package name */
    private static final HashMap f6472e;

    /* renamed from: f  reason: collision with root package name */
    private static final HashMap f6473f;

    /* renamed from: g  reason: collision with root package name */
    private static final Map f6474g;

    /* renamed from: a  reason: collision with root package name */
    private final Class f6475a;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final String a(Class cls) {
            String str;
            k.e(cls, "jClass");
            String str2 = null;
            if (cls.isAnonymousClass() || cls.isLocalClass()) {
                return null;
            }
            if (!cls.isArray()) {
                String str3 = (String) e.f6473f.get(cls.getName());
                return str3 == null ? cls.getCanonicalName() : str3;
            }
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (str = (String) e.f6473f.get(componentType.getName())) != null) {
                str2 = str + "Array";
            }
            return str2 == null ? "kotlin.Array" : str2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x003b, code lost:
            if (r2 == null) goto L12;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.String b(java.lang.Class r7) {
            /*
                r6 = this;
                java.lang.String r0 = "jClass"
                d7.k.e(r7, r0)
                boolean r0 = r7.isAnonymousClass()
                r1 = 0
                if (r0 == 0) goto Le
                goto Lb1
            Le:
                boolean r0 = r7.isLocalClass()
                if (r0 == 0) goto L67
                java.lang.String r0 = r7.getSimpleName()
                java.lang.reflect.Method r2 = r7.getEnclosingMethod()
                r3 = 2
                r4 = 36
                if (r2 == 0) goto L41
                d7.k.b(r0)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r2 = r2.getName()
                r5.append(r2)
                r5.append(r4)
                java.lang.String r2 = r5.toString()
                java.lang.String r2 = j7.c.z(r0, r2, r1, r3, r1)
                if (r2 != 0) goto L3e
                goto L41
            L3e:
                r1 = r2
                goto Lb1
            L41:
                java.lang.reflect.Constructor r7 = r7.getEnclosingConstructor()
                d7.k.b(r0)
                if (r7 == 0) goto L62
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r7 = r7.getName()
                r2.append(r7)
                r2.append(r4)
                java.lang.String r7 = r2.toString()
                java.lang.String r1 = j7.c.z(r0, r7, r1, r3, r1)
                goto Lb1
            L62:
                java.lang.String r1 = j7.c.y(r0, r4, r1, r3, r1)
                goto Lb1
            L67:
                boolean r0 = r7.isArray()
                if (r0 == 0) goto L9c
                java.lang.Class r7 = r7.getComponentType()
                boolean r0 = r7.isPrimitive()
                java.lang.String r2 = "Array"
                if (r0 == 0) goto L99
                java.util.Map r0 = d7.e.g()
                java.lang.String r7 = r7.getName()
                java.lang.Object r7 = r0.get(r7)
                java.lang.String r7 = (java.lang.String) r7
                if (r7 == 0) goto L99
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r0.append(r7)
                r0.append(r2)
                java.lang.String r7 = r0.toString()
                r1 = r7
            L99:
                if (r1 != 0) goto Lb1
                goto L3e
            L9c:
                java.util.Map r0 = d7.e.g()
                java.lang.String r1 = r7.getName()
                java.lang.Object r0 = r0.get(r1)
                r1 = r0
                java.lang.String r1 = (java.lang.String) r1
                if (r1 != 0) goto Lb1
                java.lang.String r1 = r7.getSimpleName()
            Lb1:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: d7.e.a.b(java.lang.Class):java.lang.String");
        }

        public final boolean c(Object obj, Class cls) {
            k.e(cls, "jClass");
            Map map = e.f6470c;
            k.c(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
            Integer num = (Integer) map.get(cls);
            if (num != null) {
                return v.c(obj, num.intValue());
            }
            if (cls.isPrimitive()) {
                cls = b7.a.b(b7.a.c(cls));
            }
            return cls.isInstance(obj);
        }
    }

    static {
        List h8;
        int k8;
        Map g8;
        int a8;
        String B;
        String B2;
        int i8 = 0;
        h8 = s6.n.h(c7.a.class, c7.l.class, c7.p.class, c7.q.class, c7.r.class, c7.s.class, c7.t.class, c7.u.class, c7.v.class, w.class, c7.b.class, c7.c.class, c7.d.class, c7.e.class, c7.f.class, c7.g.class, c7.h.class, c7.i.class, c7.j.class, c7.k.class, c7.m.class, c7.n.class, c7.o.class);
        List list = h8;
        k8 = s6.o.k(list, 10);
        ArrayList arrayList = new ArrayList(k8);
        for (Object obj : list) {
            int i9 = i8 + 1;
            if (i8 < 0) {
                s6.n.j();
            }
            arrayList.add(r6.o.a((Class) obj, Integer.valueOf(i8)));
            i8 = i9;
        }
        g8 = e0.g(arrayList);
        f6470c = g8;
        HashMap hashMap = new HashMap();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        f6471d = hashMap;
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        f6472e = hashMap2;
        HashMap hashMap3 = new HashMap();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(hashMap);
        hashMap3.putAll(hashMap2);
        Collection<String> values = hashMap.values();
        k.d(values, "<get-values>(...)");
        for (String str : values) {
            StringBuilder sb = new StringBuilder();
            sb.append("kotlin.jvm.internal.");
            k.b(str);
            B2 = j7.m.B(str, '.', null, 2, null);
            sb.append(B2);
            sb.append("CompanionObject");
            r6.j a9 = r6.o.a(sb.toString(), str + ".Companion");
            hashMap3.put(a9.c(), a9.d());
        }
        for (Map.Entry entry : f6470c.entrySet()) {
            int intValue = ((Number) entry.getValue()).intValue();
            hashMap3.put(((Class) entry.getKey()).getName(), "kotlin.Function" + intValue);
        }
        f6473f = hashMap3;
        a8 = d0.a(hashMap3.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(a8);
        for (Map.Entry entry2 : hashMap3.entrySet()) {
            Object key = entry2.getKey();
            B = j7.m.B((String) entry2.getValue(), '.', null, 2, null);
            linkedHashMap.put(key, B);
        }
        f6474g = linkedHashMap;
    }

    public e(Class cls) {
        k.e(cls, "jClass");
        this.f6475a = cls;
    }

    @Override // h7.b
    public String a() {
        return f6469b.a(d());
    }

    @Override // h7.b
    public boolean b(Object obj) {
        return f6469b.c(obj, d());
    }

    @Override // h7.b
    public String c() {
        return f6469b.b(d());
    }

    @Override // d7.d
    public Class d() {
        return this.f6475a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof e) && k.a(b7.a.b(this), b7.a.b((h7.b) obj));
    }

    public int hashCode() {
        return b7.a.b(this).hashCode();
    }

    public String toString() {
        return d().toString() + " (Kotlin reflection is not available)";
    }
}
