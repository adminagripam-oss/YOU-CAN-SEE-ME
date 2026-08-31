package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.c;
import androidx.appcompat.view.menu.m;
/* loaded from: classes.dex */
class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {

    /* renamed from: a  reason: collision with root package name */
    private g f1922a;

    /* renamed from: b  reason: collision with root package name */
    private androidx.appcompat.app.c f1923b;

    /* renamed from: c  reason: collision with root package name */
    e f1924c;

    /* renamed from: d  reason: collision with root package name */
    private m.a f1925d;

    public h(g gVar) {
        this.f1922a = gVar;
    }

    public void a() {
        androidx.appcompat.app.c cVar = this.f1923b;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.m.a
    public void b(g gVar, boolean z7) {
        if (z7 || gVar == this.f1922a) {
            a();
        }
        m.a aVar = this.f1925d;
        if (aVar != null) {
            aVar.b(gVar, z7);
        }
    }

    @Override // androidx.appcompat.view.menu.m.a
    public boolean c(g gVar) {
        m.a aVar = this.f1925d;
        if (aVar != null) {
            return aVar.c(gVar);
        }
        return false;
    }

    public void d(IBinder iBinder) {
        g gVar = this.f1922a;
        c.a aVar = new c.a(gVar.u());
        e eVar = new e(aVar.b(), g.g.f6897j);
        this.f1924c = eVar;
        eVar.k(this);
        this.f1922a.b(this.f1924c);
        aVar.c(this.f1924c.a(), this);
        View y7 = gVar.y();
        if (y7 != null) {
            aVar.d(y7);
        } else {
            aVar.e(gVar.w()).n(gVar.x());
        }
        aVar.j(this);
        androidx.appcompat.app.c a8 = aVar.a();
        this.f1923b = a8;
        a8.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f1923b.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f1923b.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i8) {
        this.f1922a.L((i) this.f1924c.a().getItem(i8), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f1924c.b(this.f1922a, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i8, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i8 == 82 || i8 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f1923b.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f1923b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f1922a.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f1922a.performShortcut(i8, keyEvent, 0);
    }
}
