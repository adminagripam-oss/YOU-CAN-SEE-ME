package b3;

import android.database.sqlite.SQLiteDatabase;
import b3.b0;
/* loaded from: classes.dex */
final /* synthetic */ class y implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private final String f4620a;

    private y(String str) {
        this.f4620a = str;
    }

    public static b0.b a(String str) {
        return new y(str);
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        return b0.V(this.f4620a, (SQLiteDatabase) obj);
    }
}
