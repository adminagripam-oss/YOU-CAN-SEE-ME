package p1;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.emoji2.text.e;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
final class d implements InputFilter {

    /* renamed from: a  reason: collision with root package name */
    private final TextView f9991a;

    /* renamed from: b  reason: collision with root package name */
    private e.AbstractC0037e f9992b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a extends e.AbstractC0037e {

        /* renamed from: a  reason: collision with root package name */
        private final Reference f9993a;

        /* renamed from: b  reason: collision with root package name */
        private final Reference f9994b;

        a(TextView textView, d dVar) {
            this.f9993a = new WeakReference(textView);
            this.f9994b = new WeakReference(dVar);
        }

        private boolean c(TextView textView, InputFilter inputFilter) {
            InputFilter[] filters;
            if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
                return false;
            }
            for (InputFilter inputFilter2 : filters) {
                if (inputFilter2 == inputFilter) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.emoji2.text.e.AbstractC0037e
        public void b() {
            CharSequence text;
            CharSequence o8;
            super.b();
            TextView textView = (TextView) this.f9993a.get();
            if (c(textView, (InputFilter) this.f9994b.get()) && textView.isAttachedToWindow() && text != (o8 = androidx.emoji2.text.e.b().o((text = textView.getText())))) {
                int selectionStart = Selection.getSelectionStart(o8);
                int selectionEnd = Selection.getSelectionEnd(o8);
                textView.setText(o8);
                if (o8 instanceof Spannable) {
                    d.b((Spannable) o8, selectionStart, selectionEnd);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(TextView textView) {
        this.f9991a = textView;
    }

    private e.AbstractC0037e a() {
        if (this.f9992b == null) {
            this.f9992b = new a(this.f9991a, this);
        }
        return this.f9992b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Spannable spannable, int i8, int i9) {
        if (i8 >= 0 && i9 >= 0) {
            Selection.setSelection(spannable, i8, i9);
        } else if (i8 >= 0) {
            Selection.setSelection(spannable, i8);
        } else if (i9 >= 0) {
            Selection.setSelection(spannable, i9);
        }
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i8, int i9, Spanned spanned, int i10, int i11) {
        if (this.f9991a.isInEditMode()) {
            return charSequence;
        }
        int d8 = androidx.emoji2.text.e.b().d();
        if (d8 != 0) {
            boolean z7 = true;
            if (d8 == 1) {
                if (i11 == 0 && i10 == 0 && spanned.length() == 0 && charSequence == this.f9991a.getText()) {
                    z7 = false;
                }
                if (!z7 || charSequence == null) {
                    return charSequence;
                }
                if (i8 != 0 || i9 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i8, i9);
                }
                return androidx.emoji2.text.e.b().p(charSequence, 0, charSequence.length());
            } else if (d8 != 3) {
                return charSequence;
            }
        }
        androidx.emoji2.text.e.b().s(a());
        return charSequence;
    }
}
