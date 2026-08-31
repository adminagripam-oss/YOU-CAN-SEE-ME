package z0;

import b0.h1;
import g1.e;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import y.a0;
/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f13368a;

    /* renamed from: b  reason: collision with root package name */
    public static final Map f13369b;

    /* renamed from: c  reason: collision with root package name */
    public static final Map f13370c;

    /* renamed from: d  reason: collision with root package name */
    public static final Map f13371d;

    /* renamed from: e  reason: collision with root package name */
    private static final Map f13372e;

    static {
        HashMap hashMap = new HashMap();
        f13368a = hashMap;
        HashMap hashMap2 = new HashMap();
        f13369b = hashMap2;
        HashMap hashMap3 = new HashMap();
        f13370c = hashMap3;
        HashMap hashMap4 = new HashMap();
        f13371d = hashMap4;
        HashMap hashMap5 = new HashMap();
        f13372e = hashMap5;
        hashMap.put(8, new HashSet(Collections.singletonList(8)));
        hashMap.put(10, new HashSet(Collections.singletonList(10)));
        hashMap.put(0, new HashSet(Arrays.asList(8, 10)));
        hashMap2.put(0, new HashSet(Arrays.asList(0, 1, 2, 3, 4)));
        hashMap2.put(1, new HashSet(Collections.singletonList(0)));
        hashMap2.put(2, new HashSet(Arrays.asList(1, 2, 3, 4)));
        hashMap2.put(3, new HashSet(Collections.singletonList(1)));
        hashMap2.put(4, new HashSet(Collections.singletonList(2)));
        hashMap2.put(5, new HashSet(Collections.singletonList(3)));
        hashMap2.put(6, new HashSet(Collections.singletonList(4)));
        hashMap3.put(8, 8);
        hashMap3.put(10, 10);
        hashMap4.put(0, 1);
        hashMap4.put(1, 3);
        hashMap4.put(2, 4);
        hashMap4.put(3, 5);
        hashMap4.put(4, 6);
        HashMap hashMap6 = new HashMap();
        a0 a0Var = a0.f12730d;
        hashMap6.put(a0Var, 1);
        a0 a0Var2 = a0.f12732f;
        hashMap6.put(a0Var2, 2);
        a0 a0Var3 = a0.f12733g;
        hashMap6.put(a0Var3, 4096);
        a0 a0Var4 = a0.f12734h;
        hashMap6.put(a0Var4, 8192);
        HashMap hashMap7 = new HashMap();
        hashMap7.put(a0Var, 1);
        hashMap7.put(a0Var2, 2);
        hashMap7.put(a0Var3, 4096);
        hashMap7.put(a0Var4, 8192);
        HashMap hashMap8 = new HashMap();
        hashMap8.put(a0Var, 1);
        hashMap8.put(a0Var2, 4);
        hashMap8.put(a0Var3, 4096);
        hashMap8.put(a0Var4, 16384);
        HashMap hashMap9 = new HashMap();
        hashMap9.put(a0.f12735i, 256);
        hashMap9.put(a0.f12736j, 512);
        hashMap5.put("video/hevc", hashMap6);
        hashMap5.put("video/av01", hashMap7);
        hashMap5.put("video/x-vnd.on2.vp9", hashMap8);
        hashMap5.put("video/dolby-vision", hashMap9);
    }

    public static int a(String str, a0 a0Var) {
        Integer num;
        Map map = (Map) f13372e.get(str);
        if (map == null || (num = (Integer) map.get(a0Var)) == null) {
            return -1;
        }
        return num.intValue();
    }

    public static Set b(a0 a0Var) {
        Set set = (Set) f13368a.get(Integer.valueOf(a0Var.a()));
        return set == null ? Collections.emptySet() : set;
    }

    public static Set c(a0 a0Var) {
        Set set = (Set) f13369b.get(Integer.valueOf(a0Var.b()));
        return set == null ? Collections.emptySet() : set;
    }

    private static boolean d(int i8, a0 a0Var) {
        Set set = (Set) f13368a.get(Integer.valueOf(a0Var.a()));
        return set != null && set.contains(Integer.valueOf(i8));
    }

    private static boolean e(int i8, a0 a0Var) {
        Set set = (Set) f13369b.get(Integer.valueOf(a0Var.b()));
        return set != null && set.contains(Integer.valueOf(i8));
    }

    public static boolean f(h1.c cVar, a0 a0Var) {
        return d(cVar.b(), a0Var) && e(cVar.g(), a0Var);
    }

    public static int g(int i8) {
        Map map = f13370c;
        e.a(map.containsKey(Integer.valueOf(i8)));
        Integer num = (Integer) map.get(Integer.valueOf(i8));
        Objects.requireNonNull(num);
        return num.intValue();
    }

    public static int h(int i8) {
        Map map = f13371d;
        e.a(map.containsKey(Integer.valueOf(i8)));
        Integer num = (Integer) map.get(Integer.valueOf(i8));
        Objects.requireNonNull(num);
        return num.intValue();
    }
}
