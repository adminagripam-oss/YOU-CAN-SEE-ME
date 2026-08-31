package y0;

import android.util.Range;
/* loaded from: classes.dex */
public interface q1 extends e1 {
    default boolean a(int i8, int i9) {
        return h(i8, i9) || (i() && h(i9, i8));
    }

    int b();

    Range c();

    Range d(int i8);

    Range e(int i8);

    int f();

    Range g();

    boolean h(int i8, int i9);

    boolean i();

    Range j();
}
