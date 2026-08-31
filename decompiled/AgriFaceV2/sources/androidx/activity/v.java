package androidx.activity;

import android.view.View;
/* loaded from: classes.dex */
public abstract class v {
    public static final void a(View view, s sVar) {
        d7.k.e(view, "<this>");
        d7.k.e(sVar, "onBackPressedDispatcherOwner");
        view.setTag(t.f1470b, sVar);
    }
}
