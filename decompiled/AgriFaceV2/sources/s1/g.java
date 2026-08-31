package s1;

import androidx.fragment.app.p;
import d7.k;
/* loaded from: classes.dex */
public abstract class g extends RuntimeException {

    /* renamed from: e  reason: collision with root package name */
    private final p f11545e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(p pVar, String str) {
        super(str);
        k.e(pVar, "fragment");
        this.f11545e = pVar;
    }

    public final p a() {
        return this.f11545e;
    }
}
