package b3;

import android.database.sqlite.SQLiteDatabase;
import b3.b0;
/* loaded from: classes.dex */
final /* synthetic */ class a0 implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private final b0 f4564a;

    /* renamed from: b  reason: collision with root package name */
    private final u2.m f4565b;

    private a0(b0 b0Var, u2.m mVar) {
        this.f4564a = b0Var;
        this.f4565b = mVar;
    }

    public static b0.b a(b0 b0Var, u2.m mVar) {
        return new a0(b0Var, mVar);
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        return b0.G(this.f4564a, this.f4565b, (SQLiteDatabase) obj);
    }
}
