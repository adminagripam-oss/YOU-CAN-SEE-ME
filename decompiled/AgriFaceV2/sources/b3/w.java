package b3;

import android.database.sqlite.SQLiteDatabase;
import b3.b0;
/* loaded from: classes.dex */
final /* synthetic */ class w implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private final b0 f4616a;

    /* renamed from: b  reason: collision with root package name */
    private final u2.m f4617b;

    /* renamed from: c  reason: collision with root package name */
    private final u2.h f4618c;

    private w(b0 b0Var, u2.m mVar, u2.h hVar) {
        this.f4616a = b0Var;
        this.f4617b = mVar;
        this.f4618c = hVar;
    }

    public static b0.b a(b0 b0Var, u2.m mVar, u2.h hVar) {
        return new w(b0Var, mVar, hVar);
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        return b0.T(this.f4616a, this.f4617b, this.f4618c, (SQLiteDatabase) obj);
    }
}
