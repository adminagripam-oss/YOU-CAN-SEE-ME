package b3;

import android.database.Cursor;
import b3.b0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class u implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private static final u f4614a = new u();

    private u() {
    }

    public static b0.b a() {
        return f4614a;
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        return Boolean.valueOf(((Cursor) obj).moveToNext());
    }
}
