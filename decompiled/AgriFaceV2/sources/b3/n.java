package b3;

import android.database.Cursor;
import b3.b0;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class n implements b0.b {

    /* renamed from: a  reason: collision with root package name */
    private final b0 f4605a;

    /* renamed from: b  reason: collision with root package name */
    private final List f4606b;

    /* renamed from: c  reason: collision with root package name */
    private final u2.m f4607c;

    private n(b0 b0Var, List list, u2.m mVar) {
        this.f4605a = b0Var;
        this.f4606b = list;
        this.f4607c = mVar;
    }

    public static b0.b a(b0 b0Var, List list, u2.m mVar) {
        return new n(b0Var, list, mVar);
    }

    @Override // b3.b0.b
    public Object apply(Object obj) {
        return b0.R(this.f4605a, this.f4606b, this.f4607c, (Cursor) obj);
    }
}
