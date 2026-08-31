package z3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes.dex */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    private final Map f13948a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f13949b;

    /* renamed from: c  reason: collision with root package name */
    private final d5.d f13950c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(Map map, Map map2, d5.d dVar) {
        this.f13948a = map;
        this.f13949b = map2;
        this.f13950c = dVar;
    }

    public final byte[] a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new l(byteArrayOutputStream, this.f13948a, this.f13949b, this.f13950c).h(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
