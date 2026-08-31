package m6;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Display;
import android.view.WindowManager;
import h6.o;
/* loaded from: classes.dex */
public class i0 {

    /* renamed from: g  reason: collision with root package name */
    private static final IntentFilter f9320g = new IntentFilter("android.intent.action.CONFIGURATION_CHANGED");

    /* renamed from: a  reason: collision with root package name */
    private final Activity f9321a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f9322b;

    /* renamed from: c  reason: collision with root package name */
    private final int f9323c;

    /* renamed from: d  reason: collision with root package name */
    private final b f9324d;

    /* renamed from: e  reason: collision with root package name */
    private o.f f9325e;

    /* renamed from: f  reason: collision with root package name */
    private BroadcastReceiver f9326f;

    /* loaded from: classes.dex */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            i0.this.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        void a(o.f fVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i0(Activity activity, boolean z7, int i8, b bVar) {
        this.f9321a = activity;
        this.f9322b = z7;
        this.f9323c = i8;
        this.f9324d = bVar;
    }

    static void d(o.f fVar, o.f fVar2, b bVar) {
        if (fVar.equals(fVar2)) {
            return;
        }
        bVar.a(fVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return b().getRotation();
    }

    Display b() {
        return ((WindowManager) this.f9321a.getSystemService("window")).getDefaultDisplay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o.f c() {
        int a8 = a();
        int i8 = this.f9321a.getResources().getConfiguration().orientation;
        return i8 != 1 ? i8 != 2 ? o.f.PORTRAIT_UP : (a8 == 0 || a8 == 1) ? o.f.LANDSCAPE_LEFT : o.f.LANDSCAPE_RIGHT : (a8 == 0 || a8 == 1) ? o.f.PORTRAIT_UP : o.f.PORTRAIT_DOWN;
    }

    void e() {
        o.f c8 = c();
        d(c8, this.f9325e, this.f9324d);
        this.f9325e = c8;
    }

    public void f() {
        if (this.f9326f != null) {
            return;
        }
        a aVar = new a();
        this.f9326f = aVar;
        this.f9321a.registerReceiver(aVar, f9320g);
        this.f9326f.onReceive(this.f9321a, null);
    }

    public void g() {
        BroadcastReceiver broadcastReceiver = this.f9326f;
        if (broadcastReceiver == null) {
            return;
        }
        this.f9321a.unregisterReceiver(broadcastReceiver);
        this.f9326f = null;
    }
}
