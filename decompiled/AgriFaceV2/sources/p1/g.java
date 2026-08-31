package p1;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.e;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
final class g implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    private final EditText f10002a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f10003b;

    /* renamed from: c  reason: collision with root package name */
    private e.AbstractC0037e f10004c;

    /* renamed from: d  reason: collision with root package name */
    private int f10005d = Integer.MAX_VALUE;

    /* renamed from: e  reason: collision with root package name */
    private int f10006e = 0;

    /* renamed from: f  reason: collision with root package name */
    private boolean f10007f = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a extends e.AbstractC0037e {

        /* renamed from: a  reason: collision with root package name */
        private final Reference f10008a;

        a(EditText editText) {
            this.f10008a = new WeakReference(editText);
        }

        @Override // androidx.emoji2.text.e.AbstractC0037e
        public void b() {
            super.b();
            g.b((EditText) this.f10008a.get(), 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(EditText editText, boolean z7) {
        this.f10002a = editText;
        this.f10003b = z7;
    }

    private e.AbstractC0037e a() {
        if (this.f10004c == null) {
            this.f10004c = new a(this.f10002a);
        }
        return this.f10004c;
    }

    static void b(EditText editText, int i8) {
        if (i8 == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            androidx.emoji2.text.e.b().o(editableText);
            d.b(editableText, selectionStart, selectionEnd);
        }
    }

    private boolean d() {
        return (this.f10007f && (this.f10003b || androidx.emoji2.text.e.h())) ? false : true;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
    }

    public void c(boolean z7) {
        if (this.f10007f != z7) {
            if (this.f10004c != null) {
                androidx.emoji2.text.e.b().t(this.f10004c);
            }
            this.f10007f = z7;
            if (z7) {
                b(this.f10002a, androidx.emoji2.text.e.b().d());
            }
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        if (this.f10002a.isInEditMode() || d() || i9 > i10 || !(charSequence instanceof Spannable)) {
            return;
        }
        int d8 = androidx.emoji2.text.e.b().d();
        if (d8 != 0) {
            if (d8 == 1) {
                androidx.emoji2.text.e.b().r((Spannable) charSequence, i8, i8 + i10, this.f10005d, this.f10006e);
                return;
            } else if (d8 != 3) {
                return;
            }
        }
        androidx.emoji2.text.e.b().s(a());
    }
}
