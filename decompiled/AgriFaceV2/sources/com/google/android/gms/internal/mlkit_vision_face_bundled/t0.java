package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes.dex */
public final class t0 {

    /* renamed from: a  reason: collision with root package name */
    private final Map f5600a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f5601b;

    /* renamed from: c  reason: collision with root package name */
    private final d5.d f5602c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t0(Map map, Map map2, d5.d dVar) {
        this.f5600a = map;
        this.f5601b = map2;
        this.f5602c = dVar;
    }

    public final byte[] a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new q0(byteArrayOutputStream, this.f5600a, this.f5601b, this.f5602c).h(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
