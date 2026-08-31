package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class s0 implements e5.b {

    /* renamed from: d  reason: collision with root package name */
    private static final d5.d f5585d = new d5.d() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.r0
        @Override // d5.d
        public final void a(Object obj, Object obj2) {
            d5.e eVar = (d5.e) obj2;
            int i8 = s0.f5586e;
            throw new d5.b("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
        }
    };

    /* renamed from: e  reason: collision with root package name */
    public static final /* synthetic */ int f5586e = 0;

    /* renamed from: a  reason: collision with root package name */
    private final Map f5587a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final Map f5588b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final d5.d f5589c = f5585d;

    @Override // e5.b
    public final /* bridge */ /* synthetic */ e5.b a(Class cls, d5.d dVar) {
        this.f5587a.put(cls, dVar);
        this.f5588b.remove(cls);
        return this;
    }

    public final t0 b() {
        return new t0(new HashMap(this.f5587a), new HashMap(this.f5588b), this.f5589c);
    }
}
