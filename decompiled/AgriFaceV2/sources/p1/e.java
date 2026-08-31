package p1;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
/* loaded from: classes.dex */
final class e implements KeyListener {

    /* renamed from: a  reason: collision with root package name */
    private final KeyListener f9995a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9996b;

    /* loaded from: classes.dex */
    public static class a {
        public boolean a(Editable editable, int i8, KeyEvent keyEvent) {
            return androidx.emoji2.text.e.f(editable, i8, keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(KeyListener keyListener) {
        this(keyListener, new a());
    }

    @Override // android.text.method.KeyListener
    public void clearMetaKeyState(View view, Editable editable, int i8) {
        this.f9995a.clearMetaKeyState(view, editable, i8);
    }

    @Override // android.text.method.KeyListener
    public int getInputType() {
        return this.f9995a.getInputType();
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyDown(View view, Editable editable, int i8, KeyEvent keyEvent) {
        return this.f9996b.a(editable, i8, keyEvent) || this.f9995a.onKeyDown(view, editable, i8, keyEvent);
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
        return this.f9995a.onKeyOther(view, editable, keyEvent);
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyUp(View view, Editable editable, int i8, KeyEvent keyEvent) {
        return this.f9995a.onKeyUp(view, editable, i8, keyEvent);
    }

    e(KeyListener keyListener, a aVar) {
        this.f9995a = keyListener;
        this.f9996b = aVar;
    }
}
