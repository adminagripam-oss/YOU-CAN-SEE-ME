package h3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
/* loaded from: classes.dex */
public class b extends DialogFragment {

    /* renamed from: a  reason: collision with root package name */
    private Dialog f7225a;

    /* renamed from: b  reason: collision with root package name */
    private DialogInterface.OnCancelListener f7226b;

    /* renamed from: c  reason: collision with root package name */
    private Dialog f7227c;

    public static b a(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        b bVar = new b();
        Dialog dialog2 = (Dialog) k3.q.h(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        bVar.f7225a = dialog2;
        if (onCancelListener != null) {
            bVar.f7226b = onCancelListener;
        }
        return bVar;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.f7226b;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.f7225a;
        if (dialog == null) {
            setShowsDialog(false);
            if (this.f7227c == null) {
                this.f7227c = new AlertDialog.Builder((Context) k3.q.g(getActivity())).create();
            }
            return this.f7227c;
        }
        return dialog;
    }

    @Override // android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
