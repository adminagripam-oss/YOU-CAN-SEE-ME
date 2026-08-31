package io.flutter.embedding.engine;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class e {

    /* renamed from: b  reason: collision with root package name */
    private static volatile e f7771b;

    /* renamed from: a  reason: collision with root package name */
    private final Map f7772a = new HashMap();

    e() {
    }

    public static e b() {
        if (f7771b == null) {
            synchronized (e.class) {
                if (f7771b == null) {
                    f7771b = new e();
                }
            }
        }
        return f7771b;
    }

    public d a(String str) {
        return (d) this.f7772a.get(str);
    }
}
