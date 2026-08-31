package androidx.fragment.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
/* loaded from: classes.dex */
public class n extends p implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    /* renamed from: e0  reason: collision with root package name */
    private Handler f3509e0;

    /* renamed from: n0  reason: collision with root package name */
    private boolean f3518n0;

    /* renamed from: p0  reason: collision with root package name */
    private Dialog f3520p0;

    /* renamed from: q0  reason: collision with root package name */
    private boolean f3521q0;

    /* renamed from: r0  reason: collision with root package name */
    private boolean f3522r0;

    /* renamed from: s0  reason: collision with root package name */
    private boolean f3523s0;

    /* renamed from: f0  reason: collision with root package name */
    private Runnable f3510f0 = new a();

    /* renamed from: g0  reason: collision with root package name */
    private DialogInterface.OnCancelListener f3511g0 = new b();

    /* renamed from: h0  reason: collision with root package name */
    private DialogInterface.OnDismissListener f3512h0 = new c();

    /* renamed from: i0  reason: collision with root package name */
    private int f3513i0 = 0;

    /* renamed from: j0  reason: collision with root package name */
    private int f3514j0 = 0;

    /* renamed from: k0  reason: collision with root package name */
    private boolean f3515k0 = true;

    /* renamed from: l0  reason: collision with root package name */
    private boolean f3516l0 = true;

    /* renamed from: m0  reason: collision with root package name */
    private int f3517m0 = -1;

    /* renamed from: o0  reason: collision with root package name */
    private androidx.lifecycle.t f3519o0 = new d();

    /* renamed from: t0  reason: collision with root package name */
    private boolean f3524t0 = false;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            n.this.f3512h0.onDismiss(n.this.f3520p0);
        }
    }

    /* loaded from: classes.dex */
    class b implements DialogInterface.OnCancelListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            if (n.this.f3520p0 != null) {
                n nVar = n.this;
                nVar.onCancel(nVar.f3520p0);
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements DialogInterface.OnDismissListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (n.this.f3520p0 != null) {
                n nVar = n.this;
                nVar.onDismiss(nVar.f3520p0);
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements androidx.lifecycle.t {
        d() {
        }

        @Override // androidx.lifecycle.t
        /* renamed from: b */
        public void a(androidx.lifecycle.m mVar) {
            if (mVar == null || !n.this.f3516l0) {
                return;
            }
            View l12 = n.this.l1();
            if (l12.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            if (n.this.f3520p0 != null) {
                if (i0.I0(3)) {
                    Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + n.this.f3520p0);
                }
                n.this.f3520p0.setContentView(l12);
            }
        }
    }

    /* loaded from: classes.dex */
    class e extends w {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ w f3529a;

        e(w wVar) {
            this.f3529a = wVar;
        }

        @Override // androidx.fragment.app.w
        public View c(int i8) {
            return this.f3529a.d() ? this.f3529a.c(i8) : n.this.F1(i8);
        }

        @Override // androidx.fragment.app.w
        public boolean d() {
            return this.f3529a.d() || n.this.G1();
        }
    }

    private void C1(boolean z7, boolean z8, boolean z9) {
        if (this.f3522r0) {
            return;
        }
        this.f3522r0 = true;
        this.f3523s0 = false;
        Dialog dialog = this.f3520p0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.f3520p0.dismiss();
            if (!z8) {
                if (Looper.myLooper() == this.f3509e0.getLooper()) {
                    onDismiss(this.f3520p0);
                } else {
                    this.f3509e0.post(this.f3510f0);
                }
            }
        }
        this.f3521q0 = true;
        if (this.f3517m0 >= 0) {
            if (z9) {
                C().d1(this.f3517m0, 1);
            } else {
                C().b1(this.f3517m0, 1, z7);
            }
            this.f3517m0 = -1;
            return;
        }
        q0 n8 = C().n();
        n8.m(true);
        n8.l(this);
        if (z9) {
            n8.h();
        } else if (z7) {
            n8.g();
        } else {
            n8.f();
        }
    }

    private void H1(Bundle bundle) {
        if (this.f3516l0 && !this.f3524t0) {
            try {
                this.f3518n0 = true;
                Dialog E1 = E1(bundle);
                this.f3520p0 = E1;
                if (this.f3516l0) {
                    J1(E1, this.f3513i0);
                    Context p8 = p();
                    if (p8 instanceof Activity) {
                        this.f3520p0.setOwnerActivity((Activity) p8);
                    }
                    this.f3520p0.setCancelable(this.f3515k0);
                    this.f3520p0.setOnCancelListener(this.f3511g0);
                    this.f3520p0.setOnDismissListener(this.f3512h0);
                    this.f3524t0 = true;
                } else {
                    this.f3520p0 = null;
                }
            } finally {
                this.f3518n0 = false;
            }
        }
    }

    public int D1() {
        return this.f3514j0;
    }

    public Dialog E1(Bundle bundle) {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new androidx.activity.l(k1(), D1());
    }

    @Override // androidx.fragment.app.p
    public void F0(Bundle bundle) {
        super.F0(bundle);
        Dialog dialog = this.f3520p0;
        if (dialog != null) {
            Bundle onSaveInstanceState = dialog.onSaveInstanceState();
            onSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", onSaveInstanceState);
        }
        int i8 = this.f3513i0;
        if (i8 != 0) {
            bundle.putInt("android:style", i8);
        }
        int i9 = this.f3514j0;
        if (i9 != 0) {
            bundle.putInt("android:theme", i9);
        }
        boolean z7 = this.f3515k0;
        if (!z7) {
            bundle.putBoolean("android:cancelable", z7);
        }
        boolean z8 = this.f3516l0;
        if (!z8) {
            bundle.putBoolean("android:showsDialog", z8);
        }
        int i10 = this.f3517m0;
        if (i10 != -1) {
            bundle.putInt("android:backStackId", i10);
        }
    }

    View F1(int i8) {
        Dialog dialog = this.f3520p0;
        if (dialog != null) {
            return dialog.findViewById(i8);
        }
        return null;
    }

    @Override // androidx.fragment.app.p
    public void G0() {
        super.G0();
        Dialog dialog = this.f3520p0;
        if (dialog != null) {
            this.f3521q0 = false;
            dialog.show();
            View decorView = this.f3520p0.getWindow().getDecorView();
            androidx.lifecycle.p0.a(decorView, this);
            androidx.lifecycle.q0.a(decorView, this);
            w1.g.a(decorView, this);
        }
    }

    boolean G1() {
        return this.f3524t0;
    }

    @Override // androidx.fragment.app.p
    public void H0() {
        super.H0();
        Dialog dialog = this.f3520p0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public void I1(boolean z7) {
        this.f3516l0 = z7;
    }

    @Override // androidx.fragment.app.p
    public void J0(Bundle bundle) {
        Bundle bundle2;
        super.J0(bundle);
        if (this.f3520p0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.f3520p0.onRestoreInstanceState(bundle2);
    }

    public void J1(Dialog dialog, int i8) {
        if (i8 != 1 && i8 != 2) {
            if (i8 != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void K1(i0 i0Var, String str) {
        this.f3522r0 = false;
        this.f3523s0 = true;
        q0 n8 = i0Var.n();
        n8.m(true);
        n8.d(this, str);
        n8.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.fragment.app.p
    public void Q0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.Q0(layoutInflater, viewGroup, bundle);
        if (this.J != null || this.f3520p0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.f3520p0.onRestoreInstanceState(bundle2);
    }

    @Override // androidx.fragment.app.p
    public void d0(Bundle bundle) {
        super.d0(bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.fragment.app.p
    public w f() {
        return new e(super.f());
    }

    @Override // androidx.fragment.app.p
    public void g0(Context context) {
        super.g0(context);
        Q().i(this.f3519o0);
        if (this.f3523s0) {
            return;
        }
        this.f3522r0 = false;
    }

    @Override // androidx.fragment.app.p
    public void j0(Bundle bundle) {
        super.j0(bundle);
        this.f3509e0 = new Handler();
        this.f3516l0 = this.f3583z == 0;
        if (bundle != null) {
            this.f3513i0 = bundle.getInt("android:style", 0);
            this.f3514j0 = bundle.getInt("android:theme", 0);
            this.f3515k0 = bundle.getBoolean("android:cancelable", true);
            this.f3516l0 = bundle.getBoolean("android:showsDialog", this.f3516l0);
            this.f3517m0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.f3521q0) {
            return;
        }
        if (i0.I0(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        C1(true, true, false);
    }

    @Override // androidx.fragment.app.p
    public void q0() {
        super.q0();
        Dialog dialog = this.f3520p0;
        if (dialog != null) {
            this.f3521q0 = true;
            dialog.setOnDismissListener(null);
            this.f3520p0.dismiss();
            if (!this.f3522r0) {
                onDismiss(this.f3520p0);
            }
            this.f3520p0 = null;
            this.f3524t0 = false;
        }
    }

    @Override // androidx.fragment.app.p
    public void r0() {
        super.r0();
        if (!this.f3523s0 && !this.f3522r0) {
            this.f3522r0 = true;
        }
        Q().m(this.f3519o0);
    }

    @Override // androidx.fragment.app.p
    public LayoutInflater s0(Bundle bundle) {
        StringBuilder sb;
        String str;
        LayoutInflater s02 = super.s0(bundle);
        if (this.f3516l0 && !this.f3518n0) {
            H1(bundle);
            if (i0.I0(2)) {
                Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
            }
            Dialog dialog = this.f3520p0;
            return dialog != null ? s02.cloneInContext(dialog.getContext()) : s02;
        }
        if (i0.I0(2)) {
            String str2 = "getting layout inflater for DialogFragment " + this;
            if (this.f3516l0) {
                sb = new StringBuilder();
                str = "mCreatingDialog = true: ";
            } else {
                sb = new StringBuilder();
                str = "mShowsDialog = false: ";
            }
            sb.append(str);
            sb.append(str2);
            Log.d("FragmentManager", sb.toString());
        }
        return s02;
    }
}
