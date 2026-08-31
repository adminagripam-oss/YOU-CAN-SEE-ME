package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
/* loaded from: classes.dex */
class m {

    /* renamed from: a  reason: collision with root package name */
    private final EditText f2392a;

    /* renamed from: b  reason: collision with root package name */
    private final p1.a f2393b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(EditText editText) {
        this.f2392a = editText;
        this.f2393b = new p1.a(editText, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyListener a(KeyListener keyListener) {
        return b(keyListener) ? this.f2393b.a(keyListener) : keyListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(KeyListener keyListener) {
        return !(keyListener instanceof NumberKeyListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(AttributeSet attributeSet, int i8) {
        TypedArray obtainStyledAttributes = this.f2392a.getContext().obtainStyledAttributes(attributeSet, g.j.f6954g0, i8, 0);
        try {
            boolean z7 = obtainStyledAttributes.hasValue(g.j.f7024u0) ? obtainStyledAttributes.getBoolean(g.j.f7024u0, true) : true;
            obtainStyledAttributes.recycle();
            e(z7);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputConnection d(InputConnection inputConnection, EditorInfo editorInfo) {
        return this.f2393b.b(inputConnection, editorInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(boolean z7) {
        this.f2393b.c(z7);
    }
}
