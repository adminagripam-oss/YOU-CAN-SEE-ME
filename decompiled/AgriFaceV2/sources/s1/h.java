package s1;

import android.view.ViewGroup;
import androidx.fragment.app.p;
import d7.k;
/* loaded from: classes.dex */
public final class h extends g {

    /* renamed from: f  reason: collision with root package name */
    private final ViewGroup f11546f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(p pVar, ViewGroup viewGroup) {
        super(pVar, "Attempting to add fragment " + pVar + " to container " + viewGroup + " which is not a FragmentContainerView");
        k.e(pVar, "fragment");
        k.e(viewGroup, "container");
        this.f11546f = viewGroup;
    }
}
