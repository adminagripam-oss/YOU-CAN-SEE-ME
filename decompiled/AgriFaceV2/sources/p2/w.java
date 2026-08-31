package p2;

import java.util.Map;
/* loaded from: classes.dex */
public class w {

    /* renamed from: a  reason: collision with root package name */
    private final l f10081a;

    /* renamed from: b  reason: collision with root package name */
    private final long f10082b;

    /* renamed from: c  reason: collision with root package name */
    private final long f10083c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f10084d;

    private w(l lVar, long j8, long j9, boolean z7) {
        this.f10081a = lVar;
        this.f10082b = j8;
        this.f10083c = j9;
        this.f10084d = z7;
    }

    public static w e(Map map) {
        if (map == null) {
            return new w(l.best, 0L, 5000L, false);
        }
        Integer num = (Integer) map.get("accuracy");
        Integer num2 = (Integer) map.get("distanceFilter");
        Integer num3 = (Integer) map.get("timeInterval");
        Boolean bool = (Boolean) map.get("useMSLAltitude");
        l lVar = l.best;
        boolean z7 = true;
        if (num != null) {
            int intValue = num.intValue();
            if (intValue == 0) {
                lVar = l.lowest;
            } else if (intValue == 1) {
                lVar = l.low;
            } else if (intValue == 2) {
                lVar = l.medium;
            } else if (intValue == 3) {
                lVar = l.high;
            } else if (intValue == 5) {
                lVar = l.bestForNavigation;
            }
        }
        return new w(lVar, num2 != null ? num2.intValue() : 0L, num3 != null ? num3.intValue() : 5000L, (bool == null || !bool.booleanValue()) ? false : false);
    }

    public l a() {
        return this.f10081a;
    }

    public long b() {
        return this.f10082b;
    }

    public long c() {
        return this.f10083c;
    }

    public boolean d() {
        return this.f10084d;
    }
}
