package b3;

import android.database.sqlite.SQLiteDatabase;
import b3.h0;
/* loaded from: classes.dex */
final /* synthetic */ class f0 implements h0.a {

    /* renamed from: a  reason: collision with root package name */
    private static final f0 f4584a = new f0();

    private f0() {
    }

    public static h0.a b() {
        return f4584a;
    }

    @Override // b3.h0.a
    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN payload_encoding TEXT");
    }
}
