package s3;

import android.content.Context;
/* loaded from: classes.dex */
public class c {

    /* renamed from: b  reason: collision with root package name */
    private static final c f11560b = new c();

    /* renamed from: a  reason: collision with root package name */
    private b f11561a = null;

    public static b a(Context context) {
        return f11560b.b(context);
    }

    public final synchronized b b(Context context) {
        if (this.f11561a == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.f11561a = new b(context);
        }
        return this.f11561a;
    }
}
