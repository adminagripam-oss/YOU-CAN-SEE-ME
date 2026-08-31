package s1;

import androidx.fragment.app.p;
import d7.k;
/* loaded from: classes.dex */
public final class a extends g {

    /* renamed from: f  reason: collision with root package name */
    private final String f11525f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(p pVar, String str) {
        super(pVar, "Attempting to reuse fragment " + pVar + " with previous ID " + str);
        k.e(pVar, "fragment");
        k.e(str, "previousFragmentId");
        this.f11525f = str;
    }
}
