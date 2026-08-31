package f;

import android.content.Context;
import android.content.Intent;
import d7.g;
import d7.k;
/* loaded from: classes.dex */
public final class c extends f.a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f6625a = new a(null);

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }
    }

    @Override // f.a
    /* renamed from: d */
    public Intent a(Context context, Intent intent) {
        k.e(context, "context");
        k.e(intent, "input");
        return intent;
    }

    @Override // f.a
    /* renamed from: e */
    public e.a c(int i8, Intent intent) {
        return new e.a(i8, intent);
    }
}
