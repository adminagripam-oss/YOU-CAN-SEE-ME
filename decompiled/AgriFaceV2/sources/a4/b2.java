package a4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes.dex */
public final class b2 {

    /* renamed from: a  reason: collision with root package name */
    private final Map f265a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f266b;

    /* renamed from: c  reason: collision with root package name */
    private final d5.d f267c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b2(Map map, Map map2, d5.d dVar) {
        this.f265a = map;
        this.f266b = map2;
        this.f267c = dVar;
    }

    public final byte[] a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new y1(byteArrayOutputStream, this.f265a, this.f266b, this.f267c).h(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
