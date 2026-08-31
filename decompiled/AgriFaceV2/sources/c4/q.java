package c4;

import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import x3.m0;
/* loaded from: classes.dex */
public abstract class q {

    /* renamed from: a  reason: collision with root package name */
    private static final DecimalFormat f4808a;

    /* renamed from: b  reason: collision with root package name */
    private static final DecimalFormat f4809b;

    /* renamed from: c  reason: collision with root package name */
    private static final StringBuilder f4810c;

    /* renamed from: d  reason: collision with root package name */
    public static final /* synthetic */ int f4811d = 0;

    static {
        Locale locale = Locale.ROOT;
        f4808a = new DecimalFormat(".000000", DecimalFormatSymbols.getInstance(locale));
        DecimalFormat decimalFormat = new DecimalFormat(".##", DecimalFormatSymbols.getInstance(locale));
        f4809b = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        f4810c = new StringBuilder();
    }

    public static StringBuilder a(Location location, StringBuilder sb) {
        sb.ensureCapacity(100);
        if (location == null) {
            sb.append((String) null);
            return sb;
        }
        sb.append("{");
        sb.append(location.getProvider());
        sb.append(", ");
        if (androidx.core.location.a.l(location)) {
            sb.append("mock, ");
        }
        DecimalFormat decimalFormat = f4808a;
        sb.append(decimalFormat.format(location.getLatitude()));
        sb.append(",");
        sb.append(decimalFormat.format(location.getLongitude()));
        if (location.hasAccuracy()) {
            sb.append("±");
            sb.append(f4809b.format(location.getAccuracy()));
            sb.append("m");
        }
        if (location.hasAltitude()) {
            sb.append(", alt=");
            DecimalFormat decimalFormat2 = f4809b;
            sb.append(decimalFormat2.format(location.getAltitude()));
            if (androidx.core.location.a.k(location)) {
                sb.append("±");
                sb.append(decimalFormat2.format(androidx.core.location.a.h(location)));
            }
            sb.append("m");
        }
        if (location.hasSpeed()) {
            sb.append(", spd=");
            DecimalFormat decimalFormat3 = f4809b;
            sb.append(decimalFormat3.format(location.getSpeed()));
            if (androidx.core.location.a.j(location)) {
                sb.append("±");
                sb.append(decimalFormat3.format(androidx.core.location.a.g(location)));
            }
            sb.append("m/s");
        }
        if (location.hasBearing()) {
            sb.append(", brg=");
            DecimalFormat decimalFormat4 = f4809b;
            sb.append(decimalFormat4.format(location.getBearing()));
            if (androidx.core.location.a.i(location)) {
                sb.append("±");
                sb.append(decimalFormat4.format(androidx.core.location.a.a(location)));
            }
            sb.append("°");
        }
        Bundle extras = location.getExtras();
        String string = extras != null ? extras.getString("floorLabel") : null;
        if (string != null) {
            sb.append(", fl=");
            sb.append(string);
        }
        Bundle extras2 = location.getExtras();
        String string2 = extras2 != null ? extras2.getString("levelId") : null;
        if (string2 != null) {
            sb.append(", lv=");
            sb.append(string2);
        }
        long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        sb.append(", ert=");
        sb.append(m0.a(androidx.core.location.a.b(location) + currentTimeMillis));
        sb.append('}');
        return sb;
    }
}
