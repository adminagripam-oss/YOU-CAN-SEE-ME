package f;

import android.content.Context;
import android.content.Intent;
import d7.k;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: f.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0084a {

        /* renamed from: a  reason: collision with root package name */
        private final Object f6623a;

        public C0084a(Object obj) {
            this.f6623a = obj;
        }

        public final Object a() {
            return this.f6623a;
        }
    }

    public abstract Intent a(Context context, Object obj);

    public C0084a b(Context context, Object obj) {
        k.e(context, "context");
        return null;
    }

    public abstract Object c(int i8, Intent intent);
}
