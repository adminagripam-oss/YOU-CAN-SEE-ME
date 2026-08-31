package s1;

import androidx.fragment.app.p;
import d7.k;
/* loaded from: classes.dex */
public final class i extends g {

    /* renamed from: f  reason: collision with root package name */
    private final p f11547f;

    /* renamed from: g  reason: collision with root package name */
    private final int f11548g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(p pVar, p pVar2, int i8) {
        super(pVar, "Attempting to nest fragment " + pVar + " within the view of parent fragment " + pVar2 + " via container with ID " + i8 + " without using parent's childFragmentManager");
        k.e(pVar, "fragment");
        k.e(pVar2, "expectedParentFragment");
        this.f11547f = pVar2;
        this.f11548g = i8;
    }
}
