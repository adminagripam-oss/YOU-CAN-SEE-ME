package p2;

import android.content.Context;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Bundle;
import android.os.Handler;
import java.util.Calendar;
/* loaded from: classes.dex */
public class a0 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f10012a;

    /* renamed from: b  reason: collision with root package name */
    private final LocationManager f10013b;

    /* renamed from: c  reason: collision with root package name */
    private final w f10014c;

    /* renamed from: f  reason: collision with root package name */
    private String f10017f;

    /* renamed from: g  reason: collision with root package name */
    private double f10018g;

    /* renamed from: h  reason: collision with root package name */
    private double f10019h;

    /* renamed from: i  reason: collision with root package name */
    private Calendar f10020i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f10021j = false;

    /* renamed from: d  reason: collision with root package name */
    private OnNmeaMessageListener f10015d = new OnNmeaMessageListener() { // from class: p2.z
        @Override // android.location.OnNmeaMessageListener
        public final void onNmeaMessage(String str, long j8) {
            a0.this.g(str, j8);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private GnssStatus.Callback f10016e = new a();

    /* loaded from: classes.dex */
    class a extends GnssStatus.Callback {
        a() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            a0.this.f10018g = gnssStatus.getSatelliteCount();
            a0.this.f10019h = 0.0d;
            for (int i8 = 0; i8 < a0.this.f10018g; i8++) {
                if (gnssStatus.usedInFix(i8)) {
                    a0.e(a0.this);
                }
            }
        }
    }

    public a0(Context context, w wVar) {
        this.f10012a = context;
        this.f10014c = wVar;
        this.f10013b = (LocationManager) context.getSystemService("location");
    }

    static /* synthetic */ double e(a0 a0Var) {
        double d8 = a0Var.f10019h + 1.0d;
        a0Var.f10019h = d8;
        return d8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(String str, long j8) {
        if (str.trim().matches("^\\$..GGA.*$")) {
            this.f10017f = str;
            this.f10020i = Calendar.getInstance();
        }
    }

    public void f(Location location) {
        if (location == null) {
            return;
        }
        if (location.getExtras() == null) {
            location.setExtras(Bundle.EMPTY);
        }
        location.getExtras().putDouble("geolocator_mslSatelliteCount", this.f10018g);
        location.getExtras().putDouble("geolocator_mslSatellitesUsedInFix", this.f10019h);
        if (this.f10017f == null || this.f10014c == null || !this.f10021j) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, -5);
        Calendar calendar2 = this.f10020i;
        if ((calendar2 == null || !calendar2.before(calendar)) && this.f10014c.d()) {
            String[] split = this.f10017f.split(",");
            String str = split[0];
            if (!this.f10017f.trim().matches("^\\$..GGA.*$") || split.length <= 9 || split[9].isEmpty()) {
                return;
            }
            double parseDouble = Double.parseDouble(split[9]);
            if (location.getExtras() == null) {
                location.setExtras(Bundle.EMPTY);
            }
            location.getExtras().putDouble("geolocator_mslAltitude", parseDouble);
        }
    }

    public void h() {
        if (this.f10021j || this.f10014c == null || this.f10013b == null || this.f10012a.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            return;
        }
        this.f10013b.addNmeaListener(this.f10015d, (Handler) null);
        this.f10013b.registerGnssStatusCallback(this.f10016e, (Handler) null);
        this.f10021j = true;
    }

    public void i() {
        LocationManager locationManager;
        if (this.f10014c == null || (locationManager = this.f10013b) == null) {
            return;
        }
        locationManager.removeNmeaListener(this.f10015d);
        this.f10013b.unregisterGnssStatusCallback(this.f10016e);
        this.f10021j = false;
    }
}
