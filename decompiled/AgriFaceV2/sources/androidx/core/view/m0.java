package androidx.core.view;

import android.os.Build;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public abstract class m0 {

    /* renamed from: a  reason: collision with root package name */
    private static Map f3142a = Collections.synchronizedMap(new WeakHashMap());

    /* loaded from: classes.dex */
    private static class a {
        static float a(VelocityTracker velocityTracker, int i8) {
            return velocityTracker.getAxisVelocity(i8);
        }

        static float b(VelocityTracker velocityTracker, int i8, int i9) {
            return velocityTracker.getAxisVelocity(i8, i9);
        }

        static boolean c(VelocityTracker velocityTracker, int i8) {
            return velocityTracker.isAxisSupported(i8);
        }
    }

    public static void a(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        velocityTracker.addMovement(motionEvent);
        if (Build.VERSION.SDK_INT < 34 && motionEvent.getSource() == 4194304) {
            if (!f3142a.containsKey(velocityTracker)) {
                f3142a.put(velocityTracker, new n0());
            }
            ((n0) f3142a.get(velocityTracker)).a(motionEvent);
        }
    }

    public static void b(VelocityTracker velocityTracker, int i8) {
        c(velocityTracker, i8, Float.MAX_VALUE);
    }

    public static void c(VelocityTracker velocityTracker, int i8, float f8) {
        velocityTracker.computeCurrentVelocity(i8, f8);
        n0 e8 = e(velocityTracker);
        if (e8 != null) {
            e8.c(i8, f8);
        }
    }

    public static float d(VelocityTracker velocityTracker, int i8) {
        if (Build.VERSION.SDK_INT >= 34) {
            return a.a(velocityTracker, i8);
        }
        if (i8 == 0) {
            return velocityTracker.getXVelocity();
        }
        if (i8 == 1) {
            return velocityTracker.getYVelocity();
        }
        n0 e8 = e(velocityTracker);
        if (e8 != null) {
            return e8.d(i8);
        }
        return 0.0f;
    }

    private static n0 e(VelocityTracker velocityTracker) {
        return (n0) f3142a.get(velocityTracker);
    }
}
