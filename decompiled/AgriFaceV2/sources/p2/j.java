package p2;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import c4.g;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import java.security.SecureRandom;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j implements o {

    /* renamed from: a  reason: collision with root package name */
    private final Context f10046a;

    /* renamed from: b  reason: collision with root package name */
    private final c4.e f10047b;

    /* renamed from: c  reason: collision with root package name */
    private final c4.b f10048c;

    /* renamed from: d  reason: collision with root package name */
    private final a0 f10049d;

    /* renamed from: e  reason: collision with root package name */
    private final int f10050e = s();

    /* renamed from: f  reason: collision with root package name */
    private final w f10051f;

    /* renamed from: g  reason: collision with root package name */
    private o2.a f10052g;

    /* renamed from: h  reason: collision with root package name */
    private b0 f10053h;

    /* loaded from: classes.dex */
    class a extends c4.e {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ w f10054a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Context f10055b;

        a(w wVar, Context context) {
            this.f10054a = wVar;
            this.f10055b = context;
        }

        @Override // c4.e
        public synchronized void a(LocationAvailability locationAvailability) {
            if (!locationAvailability.a() && !j.this.c(this.f10055b) && j.this.f10052g != null) {
                j.this.f10052g.a(o2.b.locationServicesDisabled);
            }
        }

        @Override // c4.e
        public synchronized void b(LocationResult locationResult) {
            if (j.this.f10053h == null) {
                Log.e("FlutterGeolocator", "LocationCallback was called with empty locationResult or no positionChangedCallback was registered.");
                j.this.f10048c.e(j.this.f10047b);
                if (j.this.f10052g != null) {
                    j.this.f10052g.a(o2.b.errorWhileAcquiringPosition);
                }
                return;
            }
            Location a8 = locationResult.a();
            if (a8 == null) {
                return;
            }
            if (a8.getExtras() == null) {
                a8.setExtras(Bundle.EMPTY);
            }
            if (this.f10054a != null) {
                a8.getExtras().putBoolean("geolocator_use_mslAltitude", this.f10054a.d());
            }
            j.this.f10049d.f(a8);
            j.this.f10053h.a(a8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10057a;

        static {
            int[] iArr = new int[l.values().length];
            f10057a = iArr;
            try {
                iArr[l.lowest.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10057a[l.low.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10057a[l.medium.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public j(Context context, w wVar) {
        this.f10046a = context;
        this.f10048c = c4.f.a(context);
        this.f10051f = wVar;
        this.f10049d = new a0(context, wVar);
        this.f10047b = new a(wVar, context);
    }

    private static LocationRequest p(w wVar) {
        if (Build.VERSION.SDK_INT < 33) {
            return q(wVar);
        }
        LocationRequest.a aVar = new LocationRequest.a(0L);
        if (wVar != null) {
            aVar.j(y(wVar.a()));
            aVar.d(wVar.c());
            aVar.i(wVar.c());
            aVar.h((float) wVar.b());
        }
        return aVar.a();
    }

    private static LocationRequest q(w wVar) {
        LocationRequest a8 = LocationRequest.a();
        if (wVar != null) {
            a8.p(y(wVar.a()));
            a8.o(wVar.c());
            a8.n(wVar.c() / 2);
            a8.q((float) wVar.b());
        }
        return a8;
    }

    private static c4.g r(LocationRequest locationRequest) {
        g.a aVar = new g.a();
        aVar.a(locationRequest);
        return aVar.b();
    }

    private synchronized int s() {
        return new SecureRandom().nextInt(65536);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void t(o2.a aVar, Exception exc) {
        Log.e("Geolocator", "Error trying to get last the last known GPS location");
        if (aVar != null) {
            aVar.a(o2.b.errorWhileAcquiringPosition);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void u(x xVar, f4.j jVar) {
        if (!jVar.j()) {
            xVar.a(o2.b.locationServicesDisabled);
        }
        c4.h hVar = (c4.h) jVar.g();
        if (hVar == null) {
            xVar.a(o2.b.locationServicesDisabled);
            return;
        }
        c4.j b8 = hVar.b();
        boolean z7 = true;
        boolean z8 = b8 != null && b8.d();
        boolean z9 = b8 != null && b8.f();
        if (!z8 && !z9) {
            z7 = false;
        }
        xVar.b(z7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(c4.h hVar) {
        x(this.f10051f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(Activity activity, o2.a aVar, Exception exc) {
        if (exc instanceof i3.h) {
            if (activity == null) {
                aVar.a(o2.b.locationServicesDisabled);
                return;
            }
            i3.h hVar = (i3.h) exc;
            if (hVar.b() == 6) {
                try {
                    hVar.c(activity, this.f10050e);
                    return;
                } catch (IntentSender.SendIntentException unused) {
                }
            }
        } else if (((i3.b) exc).b() == 8502) {
            x(this.f10051f);
            return;
        }
        aVar.a(o2.b.locationServicesDisabled);
    }

    private void x(w wVar) {
        LocationRequest p8 = p(wVar);
        this.f10049d.h();
        this.f10048c.f(p8, this.f10047b, Looper.getMainLooper());
    }

    private static int y(l lVar) {
        int i8 = b.f10057a[lVar.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                return i8 != 3 ? 100 : 102;
            }
            return 104;
        }
        return 105;
    }

    @Override // p2.o
    public boolean a(int i8, int i9) {
        if (i8 == this.f10050e) {
            if (i9 == -1) {
                w wVar = this.f10051f;
                if (wVar == null || this.f10053h == null || this.f10052g == null) {
                    return false;
                }
                x(wVar);
                return true;
            }
            o2.a aVar = this.f10052g;
            if (aVar != null) {
                aVar.a(o2.b.locationServicesDisabled);
            }
        }
        return false;
    }

    @Override // p2.o
    public void b(final x xVar) {
        c4.f.b(this.f10046a).c(new g.a().b()).a(new f4.e() { // from class: p2.e
            @Override // f4.e
            public final void a(f4.j jVar) {
                j.u(x.this, jVar);
            }
        });
    }

    @Override // p2.o
    public void d(final b0 b0Var, final o2.a aVar) {
        f4.j b8 = this.f10048c.b();
        Objects.requireNonNull(b0Var);
        b8.d(new f4.g() { // from class: p2.f
            @Override // f4.g
            public final void a(Object obj) {
                b0.this.a((Location) obj);
            }
        }).c(new f4.f() { // from class: p2.g
            @Override // f4.f
            public final void a(Exception exc) {
                j.t(o2.a.this, exc);
            }
        });
    }

    @Override // p2.o
    public void e(final Activity activity, b0 b0Var, final o2.a aVar) {
        this.f10053h = b0Var;
        this.f10052g = aVar;
        c4.f.b(this.f10046a).c(r(p(this.f10051f))).d(new f4.g() { // from class: p2.h
            @Override // f4.g
            public final void a(Object obj) {
                j.this.v((c4.h) obj);
            }
        }).c(new f4.f() { // from class: p2.i
            @Override // f4.f
            public final void a(Exception exc) {
                j.this.w(activity, aVar, exc);
            }
        });
    }

    @Override // p2.o
    public void f() {
        this.f10049d.i();
        this.f10048c.e(this.f10047b);
    }
}
