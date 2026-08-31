package io.flutter.embedding.engine;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class b {

    /* renamed from: b  reason: collision with root package name */
    private static b f7737b;

    /* renamed from: a  reason: collision with root package name */
    private final Map f7738a = new HashMap();

    b() {
    }

    public static b b() {
        if (f7737b == null) {
            f7737b = new b();
        }
        return f7737b;
    }

    public a a(String str) {
        return (a) this.f7738a.get(str);
    }

    public void c(String str, a aVar) {
        if (aVar != null) {
            this.f7738a.put(str, aVar);
        } else {
            this.f7738a.remove(str);
        }
    }

    public void d(String str) {
        c(str, null);
    }
}
