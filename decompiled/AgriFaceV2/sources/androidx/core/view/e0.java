package androidx.core.view;

import android.view.View;
/* loaded from: classes.dex */
public interface e0 {
    void onNestedPreScroll(View view, int i8, int i9, int[] iArr, int i10);

    void onNestedScroll(View view, int i8, int i9, int i10, int i11, int i12);

    void onNestedScrollAccepted(View view, View view2, int i8, int i9);

    boolean onStartNestedScroll(View view, View view2, int i8, int i9);

    void onStopNestedScroll(View view, int i8);
}
