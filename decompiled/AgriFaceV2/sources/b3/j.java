package b3;

import android.database.sqlite.SQLiteDatabase;
import b3.b0;
/* loaded from: classes.dex */
final /* synthetic */ class j implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private final long f4599a;

    /* renamed from: b  reason: collision with root package name */
    private final u2.m f4600b;

    private j(long j8, u2.m mVar) {
        this.f4599a = j8;
        this.f4600b = mVar;
    }

    public static b0.b a(long j8, u2.m mVar) {
        return new j(j8, mVar);
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        return b0.W(this.f4599a, this.f4600b, (SQLiteDatabase) obj);
    }
}
