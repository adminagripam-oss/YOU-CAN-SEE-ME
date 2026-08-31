package b3;

import android.database.sqlite.SQLiteDatabase;
import b3.b0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class q implements b0.d {

    /* renamed from: a  reason: collision with root package name */
    private final SQLiteDatabase f4610a;

    private q(SQLiteDatabase sQLiteDatabase) {
        this.f4610a = sQLiteDatabase;
    }

    public static b0.d b(SQLiteDatabase sQLiteDatabase) {
        return new q(sQLiteDatabase);
    }

    @Override // b3.b0.d
    public Object a() {
        return b0.x(this.f4610a);
    }
}
