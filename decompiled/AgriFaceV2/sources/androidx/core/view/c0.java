package androidx.core.view;

import android.view.MotionEvent;
/* loaded from: classes.dex */
public abstract class c0 {
    public static boolean a(MotionEvent motionEvent, int i8) {
        return (motionEvent.getSource() & i8) == i8;
    }
}
