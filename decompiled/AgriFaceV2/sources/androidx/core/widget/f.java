package androidx.core.widget;

import android.widget.ListView;
/* loaded from: classes.dex */
public class f extends a {

    /* renamed from: s  reason: collision with root package name */
    private final ListView f3258s;

    public f(ListView listView) {
        super(listView);
        this.f3258s = listView;
    }

    @Override // androidx.core.widget.a
    public boolean a(int i8) {
        return false;
    }

    @Override // androidx.core.widget.a
    public boolean b(int i8) {
        ListView listView = this.f3258s;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i9 = firstVisiblePosition + childCount;
        if (i8 > 0) {
            if (i9 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else if (i8 >= 0) {
            return false;
        } else {
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.core.widget.a
    public void j(int i8, int i9) {
        this.f3258s.scrollListBy(i9);
    }
}
