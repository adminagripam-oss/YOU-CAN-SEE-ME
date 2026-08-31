package h3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.i0;
/* loaded from: classes.dex */
public class j extends androidx.fragment.app.n {

    /* renamed from: u0  reason: collision with root package name */
    private Dialog f7244u0;

    /* renamed from: v0  reason: collision with root package name */
    private DialogInterface.OnCancelListener f7245v0;

    /* renamed from: w0  reason: collision with root package name */
    private Dialog f7246w0;

    public static j L1(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        j jVar = new j();
        Dialog dialog2 = (Dialog) k3.q.h(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        jVar.f7244u0 = dialog2;
        if (onCancelListener != null) {
            jVar.f7245v0 = onCancelListener;
        }
        return jVar;
    }

    @Override // androidx.fragment.app.n
    public Dialog E1(Bundle bundle) {
        Dialog dialog = this.f7244u0;
        if (dialog == null) {
            I1(false);
            if (this.f7246w0 == null) {
                this.f7246w0 = new AlertDialog.Builder((Context) k3.q.g(p())).create();
            }
            return this.f7246w0;
        }
        return dialog;
    }

    @Override // androidx.fragment.app.n
    public void K1(i0 i0Var, String str) {
        super.K1(i0Var, str);
    }

    @Override // androidx.fragment.app.n, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.f7245v0;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }
}
