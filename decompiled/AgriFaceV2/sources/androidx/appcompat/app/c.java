package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertController;
/* loaded from: classes.dex */
public class c extends p implements DialogInterface {

    /* renamed from: f  reason: collision with root package name */
    final AlertController f1574f;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final AlertController.b f1575a;

        /* renamed from: b  reason: collision with root package name */
        private final int f1576b;

        public a(Context context) {
            this(context, c.j(context, 0));
        }

        public c a() {
            c cVar = new c(this.f1575a.f1500a, this.f1576b);
            this.f1575a.a(cVar.f1574f);
            cVar.setCancelable(this.f1575a.f1517r);
            if (this.f1575a.f1517r) {
                cVar.setCanceledOnTouchOutside(true);
            }
            cVar.setOnCancelListener(this.f1575a.f1518s);
            cVar.setOnDismissListener(this.f1575a.f1519t);
            DialogInterface.OnKeyListener onKeyListener = this.f1575a.f1520u;
            if (onKeyListener != null) {
                cVar.setOnKeyListener(onKeyListener);
            }
            return cVar;
        }

        public Context b() {
            return this.f1575a.f1500a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f1575a;
            bVar.f1522w = listAdapter;
            bVar.f1523x = onClickListener;
            return this;
        }

        public a d(View view) {
            this.f1575a.f1506g = view;
            return this;
        }

        public a e(Drawable drawable) {
            this.f1575a.f1503d = drawable;
            return this;
        }

        public a f(CharSequence charSequence) {
            this.f1575a.f1507h = charSequence;
            return this;
        }

        public a g(int i8, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f1575a;
            bVar.f1511l = bVar.f1500a.getText(i8);
            this.f1575a.f1513n = onClickListener;
            return this;
        }

        public a h(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f1575a;
            bVar.f1511l = charSequence;
            bVar.f1513n = onClickListener;
            return this;
        }

        public a i(DialogInterface.OnCancelListener onCancelListener) {
            this.f1575a.f1518s = onCancelListener;
            return this;
        }

        public a j(DialogInterface.OnKeyListener onKeyListener) {
            this.f1575a.f1520u = onKeyListener;
            return this;
        }

        public a k(int i8, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f1575a;
            bVar.f1508i = bVar.f1500a.getText(i8);
            this.f1575a.f1510k = onClickListener;
            return this;
        }

        public a l(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f1575a;
            bVar.f1508i = charSequence;
            bVar.f1510k = onClickListener;
            return this;
        }

        public a m(ListAdapter listAdapter, int i8, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f1575a;
            bVar.f1522w = listAdapter;
            bVar.f1523x = onClickListener;
            bVar.I = i8;
            bVar.H = true;
            return this;
        }

        public a n(CharSequence charSequence) {
            this.f1575a.f1505f = charSequence;
            return this;
        }

        public a(Context context, int i8) {
            this.f1575a = new AlertController.b(new ContextThemeWrapper(context, c.j(context, i8)));
            this.f1576b = i8;
        }
    }

    protected c(Context context, int i8) {
        super(context, j(context, i8));
        this.f1574f = new AlertController(getContext(), this, getWindow());
    }

    static int j(Context context, int i8) {
        if (((i8 >>> 24) & 255) >= 1) {
            return i8;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(g.a.f6803l, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView i() {
        return this.f1574f.d();
    }

    public void k(View view) {
        this.f1574f.r(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.p, androidx.activity.l, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f1574f.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i8, KeyEvent keyEvent) {
        if (this.f1574f.f(i8, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i8, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i8, KeyEvent keyEvent) {
        if (this.f1574f.g(i8, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i8, keyEvent);
    }

    @Override // androidx.appcompat.app.p, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f1574f.p(charSequence);
    }
}
