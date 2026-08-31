package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.x;
import java.util.Collections;
import java.util.Map;
/* loaded from: classes.dex */
public class p {

    /* renamed from: b  reason: collision with root package name */
    private static volatile p f6201b;

    /* renamed from: c  reason: collision with root package name */
    static final p f6202c = new p(true);

    /* renamed from: a  reason: collision with root package name */
    private final Map f6203a = Collections.emptyMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final Object f6204a;

        /* renamed from: b  reason: collision with root package name */
        private final int f6205b;

        a(Object obj, int i8) {
            this.f6204a = obj;
            this.f6205b = i8;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return this.f6204a == aVar.f6204a && this.f6205b == aVar.f6205b;
            }
            return false;
        }

        public int hashCode() {
            return (System.identityHashCode(this.f6204a) * 65535) + this.f6205b;
        }
    }

    p(boolean z7) {
    }

    public static p b() {
        if (z0.f6299d) {
            return f6202c;
        }
        p pVar = f6201b;
        if (pVar == null) {
            synchronized (p.class) {
                pVar = f6201b;
                if (pVar == null) {
                    pVar = o.a();
                    f6201b = pVar;
                }
            }
        }
        return pVar;
    }

    public x.c a(p0 p0Var, int i8) {
        androidx.appcompat.app.w.a(this.f6203a.get(new a(p0Var, i8)));
        return null;
    }
}
