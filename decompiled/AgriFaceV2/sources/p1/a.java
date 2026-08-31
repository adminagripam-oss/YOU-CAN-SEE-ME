package p1;

import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final b f9981a;

    /* renamed from: b  reason: collision with root package name */
    private int f9982b = Integer.MAX_VALUE;

    /* renamed from: c  reason: collision with root package name */
    private int f9983c = 0;

    /* renamed from: p1.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0138a extends b {

        /* renamed from: a  reason: collision with root package name */
        private final EditText f9984a;

        /* renamed from: b  reason: collision with root package name */
        private final g f9985b;

        C0138a(EditText editText, boolean z7) {
            this.f9984a = editText;
            g gVar = new g(editText, z7);
            this.f9985b = gVar;
            editText.addTextChangedListener(gVar);
            editText.setEditableFactory(p1.b.getInstance());
        }

        @Override // p1.a.b
        KeyListener a(KeyListener keyListener) {
            if (keyListener instanceof e) {
                return keyListener;
            }
            if (keyListener == null) {
                return null;
            }
            return keyListener instanceof NumberKeyListener ? keyListener : new e(keyListener);
        }

        @Override // p1.a.b
        InputConnection b(InputConnection inputConnection, EditorInfo editorInfo) {
            return inputConnection instanceof c ? inputConnection : new c(this.f9984a, inputConnection, editorInfo);
        }

        @Override // p1.a.b
        void c(boolean z7) {
            this.f9985b.c(z7);
        }
    }

    /* loaded from: classes.dex */
    static class b {
        b() {
        }

        abstract KeyListener a(KeyListener keyListener);

        abstract InputConnection b(InputConnection inputConnection, EditorInfo editorInfo);

        abstract void c(boolean z7);
    }

    public a(EditText editText, boolean z7) {
        g1.e.k(editText, "editText cannot be null");
        this.f9981a = new C0138a(editText, z7);
    }

    public KeyListener a(KeyListener keyListener) {
        return this.f9981a.a(keyListener);
    }

    public InputConnection b(InputConnection inputConnection, EditorInfo editorInfo) {
        if (inputConnection == null) {
            return null;
        }
        return this.f9981a.b(inputConnection, editorInfo);
    }

    public void c(boolean z7) {
        this.f9981a.c(z7);
    }
}
