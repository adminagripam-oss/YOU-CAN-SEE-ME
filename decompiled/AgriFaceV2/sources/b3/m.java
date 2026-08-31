package b3;

import android.database.sqlite.SQLiteDatabase;
import b3.b0;
/* loaded from: classes.dex */
final /* synthetic */ class m implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private final long f4604a;

    private m(long j8) {
        this.f4604a = j8;
    }

    public static b0.b a(long j8) {
        return new m(j8);
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        Integer valueOf;
        valueOf = Integer.valueOf(((SQLiteDatabase) obj).delete("events", "timestamp_ms < ?", new String[]{String.valueOf(this.f4604a)}));
        return valueOf;
    }
}
