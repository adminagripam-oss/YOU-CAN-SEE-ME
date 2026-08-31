package b3;

import android.database.sqlite.SQLiteDatabase;
import b3.b0;
/* loaded from: classes.dex */
final /* synthetic */ class k implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private final b0 f4601a;

    /* renamed from: b  reason: collision with root package name */
    private final u2.m f4602b;

    private k(b0 b0Var, u2.m mVar) {
        this.f4601a = b0Var;
        this.f4602b = mVar;
    }

    public static b0.b a(b0 b0Var, u2.m mVar) {
        return new k(b0Var, mVar);
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        return b0.Q(this.f4601a, this.f4602b, (SQLiteDatabase) obj);
    }
}
