package m5;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;
/* loaded from: classes.dex */
public class m {

    /* renamed from: b  reason: collision with root package name */
    public static final b5.c f9186b = b5.c.a(m.class).b(b5.o.g(i.class)).b(b5.o.g(Context.class)).d(new b5.g() { // from class: m5.c0
        @Override // b5.g
        public final Object a(b5.d dVar) {
            return new m((Context) dVar.a(Context.class));
        }
    }).c();

    /* renamed from: a  reason: collision with root package name */
    protected final Context f9187a;

    public m(Context context) {
        this.f9187a = context;
    }

    public synchronized String a() {
        String string = b().getString("ml_sdk_instance_id", null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        b().edit().putString("ml_sdk_instance_id", uuid).apply();
        return uuid;
    }

    protected final SharedPreferences b() {
        return this.f9187a.getSharedPreferences("com.google.mlkit.internal", 0);
    }
}
