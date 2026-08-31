package a1;

import android.util.Pair;
import androidx.camera.video.internal.compat.quirk.NegativeLatLongSavesIncorrectlyQuirk;
/* loaded from: classes.dex */
public abstract class a {
    public static Pair a(double d8, double d9) {
        if (androidx.camera.video.internal.compat.quirk.a.b(NegativeLatLongSavesIncorrectlyQuirk.class) != null) {
            d8 = b(d8);
            d9 = b(d9);
        }
        return Pair.create(Double.valueOf(d8), Double.valueOf(d9));
    }

    private static double b(double d8) {
        return d8 >= 0.0d ? d8 : ((d8 * 10000.0d) - 1.0d) / 10000.0d;
    }
}
