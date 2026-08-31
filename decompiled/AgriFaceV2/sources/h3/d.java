package h3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import androidx.core.app.j;
import com.google.android.gms.common.api.GoogleApiActivity;
import k3.b0;
import k3.e0;
/* loaded from: classes.dex */
public class d extends e {

    /* renamed from: c  reason: collision with root package name */
    private String f7234c;

    /* renamed from: e  reason: collision with root package name */
    private static final Object f7232e = new Object();

    /* renamed from: f  reason: collision with root package name */
    private static final d f7233f = new d();

    /* renamed from: d  reason: collision with root package name */
    public static final int f7231d = e.f7235a;

    public static d m() {
        return f7233f;
    }

    @Override // h3.e
    public Intent b(Context context, int i8, String str) {
        return super.b(context, i8, str);
    }

    @Override // h3.e
    public PendingIntent c(Context context, int i8, int i9) {
        return super.c(context, i8, i9);
    }

    @Override // h3.e
    public final String e(int i8) {
        return super.e(i8);
    }

    @Override // h3.e
    public int g(Context context) {
        return super.g(context);
    }

    @Override // h3.e
    public int h(Context context, int i8) {
        return super.h(context, i8);
    }

    @Override // h3.e
    public final boolean j(int i8) {
        return super.j(i8);
    }

    public Dialog k(Activity activity, int i8, int i9, DialogInterface.OnCancelListener onCancelListener) {
        return p(activity, i8, e0.b(activity, b(activity, i8, "d"), i9), onCancelListener, null);
    }

    public PendingIntent l(Context context, a aVar) {
        return aVar.d() ? aVar.c() : c(context, aVar.a(), 0);
    }

    public boolean n(Activity activity, int i8, int i9, DialogInterface.OnCancelListener onCancelListener) {
        Dialog k8 = k(activity, i8, i9, onCancelListener);
        if (k8 == null) {
            return false;
        }
        s(activity, k8, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    public void o(Context context, int i8) {
        t(context, i8, null, d(context, i8, 0, "n"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    final Dialog p(Context context, int i8, e0 e0Var, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnClickListener onClickListener) {
        if (i8 == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843529, typedValue, true);
        AlertDialog.Builder builder = "Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId)) ? new AlertDialog.Builder(context, 5) : null;
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(b0.c(context, i8));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String b8 = b0.b(context, i8);
        if (b8 != null) {
            if (e0Var == null) {
                e0Var = onClickListener;
            }
            builder.setPositiveButton(b8, e0Var);
        }
        String f8 = b0.f(context, i8);
        if (f8 != null) {
            builder.setTitle(f8);
        }
        Log.w("GoogleApiAvailability", String.format("Creating dialog for Google Play services availability issue. ConnectionResult=%s", Integer.valueOf(i8)), new IllegalArgumentException());
        return builder.create();
    }

    public final Dialog q(Activity activity, DialogInterface.OnCancelListener onCancelListener) {
        ProgressBar progressBar = new ProgressBar(activity, null, 16842874);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(progressBar);
        builder.setMessage(b0.c(activity, 18));
        builder.setPositiveButton("", (DialogInterface.OnClickListener) null);
        AlertDialog create = builder.create();
        s(activity, create, "GooglePlayServicesUpdatingDialog", onCancelListener);
        return create;
    }

    public final j3.q r(Context context, j3.p pVar) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        j3.q qVar = new j3.q(pVar);
        v3.f.m(context, qVar, intentFilter);
        qVar.a(context);
        if (i(context, "com.google.android.gms")) {
            return qVar;
        }
        pVar.a();
        qVar.b();
        return null;
    }

    final void s(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        try {
            if (activity instanceof androidx.fragment.app.u) {
                j.L1(dialog, onCancelListener).K1(((androidx.fragment.app.u) activity).getSupportFragmentManager(), str);
                return;
            }
        } catch (NoClassDefFoundError unused) {
        }
        b.a(dialog, onCancelListener).show(activity.getFragmentManager(), str);
    }

    final void t(Context context, int i8, String str, PendingIntent pendingIntent) {
        int i9;
        String str2;
        Log.w("GoogleApiAvailability", String.format("GMS core API Availability. ConnectionResult=%s, tag=%s", Integer.valueOf(i8), null), new IllegalArgumentException());
        if (i8 == 18) {
            u(context);
        } else if (pendingIntent == null) {
            if (i8 == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
            }
        } else {
            String e8 = b0.e(context, i8);
            String d8 = b0.d(context, i8);
            Resources resources = context.getResources();
            NotificationManager notificationManager = (NotificationManager) k3.q.g(context.getSystemService("notification"));
            j.d p8 = new j.d(context).l(true).e(true).j(e8).p(new j.b().h(d8));
            if (q3.e.b(context)) {
                k3.q.i(q3.g.d());
                p8.o(context.getApplicationInfo().icon).n(2);
                if (q3.e.c(context)) {
                    p8.a(g3.a.f7105a, resources.getString(g3.b.f7120o), pendingIntent);
                } else {
                    p8.h(pendingIntent);
                }
            } else {
                p8.o(17301642).q(resources.getString(g3.b.f7113h)).r(System.currentTimeMillis()).h(pendingIntent).i(d8);
            }
            if (q3.g.g()) {
                k3.q.i(q3.g.g());
                synchronized (f7232e) {
                    str2 = this.f7234c;
                }
                if (str2 == null) {
                    str2 = "com.google.android.gms.availability";
                    NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
                    String string = context.getResources().getString(g3.b.f7112g);
                    if (notificationChannel == null) {
                        notificationChannel = new NotificationChannel("com.google.android.gms.availability", string, 4);
                    } else if (!string.contentEquals(notificationChannel.getName())) {
                        notificationChannel.setName(string);
                    }
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                p8.f(str2);
            }
            Notification b8 = p8.b();
            if (i8 == 1 || i8 == 2 || i8 == 3) {
                g.f7239b.set(false);
                i9 = 10436;
            } else {
                i9 = 39789;
            }
            notificationManager.notify(i9, b8);
        }
    }

    final void u(Context context) {
        new k(this, context).sendEmptyMessageDelayed(1, 120000L);
    }

    public final boolean v(Activity activity, j3.f fVar, int i8, int i9, DialogInterface.OnCancelListener onCancelListener) {
        Dialog p8 = p(activity, i8, e0.c(fVar, b(activity, i8, "d"), 2), onCancelListener, null);
        if (p8 == null) {
            return false;
        }
        s(activity, p8, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    public final boolean w(Context context, a aVar, int i8) {
        PendingIntent l8;
        if (s3.a.a(context) || (l8 = l(context, aVar)) == null) {
            return false;
        }
        t(context, aVar.a(), null, PendingIntent.getActivity(context, 0, GoogleApiActivity.a(context, l8, i8, true), v3.g.f12356a | 134217728));
        return true;
    }
}
