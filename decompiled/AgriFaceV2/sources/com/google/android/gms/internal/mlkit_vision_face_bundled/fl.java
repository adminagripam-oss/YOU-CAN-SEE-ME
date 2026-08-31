package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class fl extends LinkedHashMap {

    /* renamed from: f  reason: collision with root package name */
    private static final fl f5235f;

    /* renamed from: e  reason: collision with root package name */
    private boolean f5236e;

    static {
        fl flVar = new fl();
        f5235f = flVar;
        flVar.f5236e = false;
    }

    private fl() {
        this.f5236e = true;
    }

    public static fl a() {
        return f5235f;
    }

    private static int h(Object obj) {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = uk.f5674b;
            int length = bArr.length;
            int b8 = uk.b(length, bArr, 0, length);
            if (b8 == 0) {
                return 1;
            }
            return b8;
        }
        return obj.hashCode();
    }

    private final void i() {
        if (!this.f5236e) {
            throw new UnsupportedOperationException();
        }
    }

    public final fl c() {
        return isEmpty() ? new fl() : new fl(this);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        i();
        super.clear();
    }

    public final void d() {
        this.f5236e = false;
    }

    public final void e(fl flVar) {
        i();
        if (flVar.isEmpty()) {
            return;
        }
        putAll(flVar);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        boolean equals;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this == map) {
                return true;
            }
            if (size() != map.size()) {
                return false;
            }
            Iterator it = entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!map.containsKey(entry.getKey())) {
                    return false;
                }
                Object value = entry.getValue();
                Object obj2 = map.get(entry.getKey());
                if ((value instanceof byte[]) && (obj2 instanceof byte[])) {
                    equals = Arrays.equals((byte[]) value, (byte[]) obj2);
                    continue;
                } else {
                    equals = value.equals(obj2);
                    continue;
                }
                if (!equals) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final boolean g() {
        return this.f5236e;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        Iterator it = entrySet().iterator();
        int i8 = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            i8 += h(entry.getValue()) ^ h(entry.getKey());
        }
        return i8;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        i();
        byte[] bArr = uk.f5674b;
        obj.getClass();
        obj2.getClass();
        return super.put(obj, obj2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        i();
        for (Object obj : map.keySet()) {
            byte[] bArr = uk.f5674b;
            obj.getClass();
            map.get(obj).getClass();
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        i();
        return super.remove(obj);
    }

    private fl(Map map) {
        super(map);
        this.f5236e = true;
    }
}
