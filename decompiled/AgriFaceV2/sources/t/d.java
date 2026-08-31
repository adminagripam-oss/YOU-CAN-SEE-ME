package t;

import android.hardware.camera2.params.DynamicRangeProfiles;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import y.a0;
/* loaded from: classes.dex */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    private static final Map f11605a;

    /* renamed from: b  reason: collision with root package name */
    private static final Map f11606b;

    static {
        HashMap hashMap = new HashMap();
        f11605a = hashMap;
        HashMap hashMap2 = new HashMap();
        f11606b = hashMap2;
        a0 a0Var = a0.f12730d;
        hashMap.put(1L, a0Var);
        hashMap2.put(a0Var, Collections.singletonList(1L));
        hashMap.put(2L, a0.f12732f);
        hashMap2.put((a0) hashMap.get(2L), Collections.singletonList(2L));
        a0 a0Var2 = a0.f12733g;
        hashMap.put(4L, a0Var2);
        hashMap2.put(a0Var2, Collections.singletonList(4L));
        a0 a0Var3 = a0.f12734h;
        hashMap.put(8L, a0Var3);
        hashMap2.put(a0Var3, Collections.singletonList(8L));
        List<Long> asList = Arrays.asList(64L, 128L, 16L, 32L);
        for (Long l8 : asList) {
            f11605a.put(l8, a0.f12735i);
        }
        f11606b.put(a0.f12735i, asList);
        List<Long> asList2 = Arrays.asList(1024L, 2048L, 256L, 512L);
        for (Long l9 : asList2) {
            f11605a.put(l9, a0.f12736j);
        }
        f11606b.put(a0.f12736j, asList2);
    }

    public static Long a(a0 a0Var, DynamicRangeProfiles dynamicRangeProfiles) {
        Set supportedProfiles;
        List<Long> list = (List) f11606b.get(a0Var);
        if (list != null) {
            supportedProfiles = dynamicRangeProfiles.getSupportedProfiles();
            for (Long l8 : list) {
                if (supportedProfiles.contains(l8)) {
                    return l8;
                }
            }
            return null;
        }
        return null;
    }

    public static a0 b(long j8) {
        return (a0) f11605a.get(Long.valueOf(j8));
    }
}
