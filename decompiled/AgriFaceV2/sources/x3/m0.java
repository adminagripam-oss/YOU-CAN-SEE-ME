package x3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes.dex */
public abstract class m0 {

    /* renamed from: a  reason: collision with root package name */
    private static final SimpleDateFormat f12689a;

    /* renamed from: b  reason: collision with root package name */
    private static final SimpleDateFormat f12690b;

    /* renamed from: c  reason: collision with root package name */
    private static final StringBuilder f12691c;

    static {
        Locale locale = Locale.ROOT;
        f12689a = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", locale);
        f12690b = new SimpleDateFormat("MM-dd HH:mm:ss", locale);
        f12691c = new StringBuilder(33);
    }

    public static String a(long j8) {
        return j8 >= 0 ? f12689a.format(new Date(j8)) : Long.toString(j8);
    }

    public static String b(long j8) {
        String sb;
        StringBuilder sb2 = f12691c;
        synchronized (sb2) {
            sb2.setLength(0);
            c(j8, sb2);
            sb = sb2.toString();
        }
        return sb;
    }

    public static StringBuilder c(long j8, StringBuilder sb) {
        int i8 = (j8 > 0L ? 1 : (j8 == 0L ? 0 : -1));
        if (i8 == 0) {
            sb.append("0s");
            return sb;
        }
        sb.ensureCapacity(sb.length() + 27);
        boolean z7 = false;
        if (i8 < 0) {
            sb.append("-");
            if (j8 != Long.MIN_VALUE) {
                j8 = -j8;
            } else {
                j8 = Long.MAX_VALUE;
                z7 = true;
            }
        }
        if (j8 >= 86400000) {
            sb.append(j8 / 86400000);
            sb.append("d");
            j8 %= 86400000;
        }
        if (true == z7) {
            j8 = 25975808;
        }
        if (j8 >= 3600000) {
            sb.append(j8 / 3600000);
            sb.append("h");
            j8 %= 3600000;
        }
        if (j8 >= 60000) {
            sb.append(j8 / 60000);
            sb.append("m");
            j8 %= 60000;
        }
        if (j8 >= 1000) {
            sb.append(j8 / 1000);
            sb.append("s");
            j8 %= 1000;
        }
        if (j8 > 0) {
            sb.append(j8);
            sb.append("ms");
        }
        return sb;
    }
}
