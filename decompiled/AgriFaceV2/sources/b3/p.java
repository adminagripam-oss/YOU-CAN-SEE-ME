package b3;

import android.database.Cursor;
import b3.b0;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class p implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private final Map f4609a;

    private p(Map map) {
        this.f4609a = map;
    }

    public static b0.b a(Map map) {
        return new p(map);
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        return b0.S(this.f4609a, (Cursor) obj);
    }
}
