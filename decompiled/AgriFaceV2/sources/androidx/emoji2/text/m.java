package androidx.emoji2.text;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import java.util.stream.IntStream;
/* loaded from: classes.dex */
class m implements Spannable {

    /* renamed from: e  reason: collision with root package name */
    private boolean f3348e = false;

    /* renamed from: f  reason: collision with root package name */
    private Spannable f3349f;

    /* loaded from: classes.dex */
    private static class a {
        static IntStream a(CharSequence charSequence) {
            return charSequence.chars();
        }

        static IntStream b(CharSequence charSequence) {
            return charSequence.codePoints();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        b() {
        }

        boolean a(CharSequence charSequence) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c extends b {
        c() {
        }

        @Override // androidx.emoji2.text.m.b
        boolean a(CharSequence charSequence) {
            return n1.e.a(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Spannable spannable) {
        this.f3349f = spannable;
    }

    private void a() {
        Spannable spannable = this.f3349f;
        if (!this.f3348e && c().a(spannable)) {
            this.f3349f = new SpannableString(spannable);
        }
        this.f3348e = true;
    }

    static b c() {
        return Build.VERSION.SDK_INT < 28 ? new b() : new c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Spannable b() {
        return this.f3349f;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i8) {
        return this.f3349f.charAt(i8);
    }

    @Override // java.lang.CharSequence
    public IntStream chars() {
        return a.a(this.f3349f);
    }

    @Override // java.lang.CharSequence
    public IntStream codePoints() {
        return a.b(this.f3349f);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f3349f.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f3349f.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f3349f.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public Object[] getSpans(int i8, int i9, Class cls) {
        return this.f3349f.getSpans(i8, i9, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f3349f.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i8, int i9, Class cls) {
        return this.f3349f.nextSpanTransition(i8, i9, cls);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        a();
        this.f3349f.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i8, int i9, int i10) {
        a();
        this.f3349f.setSpan(obj, i8, i9, i10);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i8, int i9) {
        return this.f3349f.subSequence(i8, i9);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f3349f.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(CharSequence charSequence) {
        this.f3349f = new SpannableString(charSequence);
    }
}
