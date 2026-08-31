package n1;

import android.os.Build;
import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class c extends SpannableStringBuilder {

    /* renamed from: e  reason: collision with root package name */
    private final Class f9746e;

    /* renamed from: f  reason: collision with root package name */
    private final List f9747f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements TextWatcher, SpanWatcher {

        /* renamed from: a  reason: collision with root package name */
        final Object f9748a;

        /* renamed from: b  reason: collision with root package name */
        private final AtomicInteger f9749b = new AtomicInteger(0);

        a(Object obj) {
            this.f9748a = obj;
        }

        private boolean b(Object obj) {
            return obj instanceof b;
        }

        final void a() {
            this.f9749b.incrementAndGet();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            ((TextWatcher) this.f9748a).afterTextChanged(editable);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
            ((TextWatcher) this.f9748a).beforeTextChanged(charSequence, i8, i9, i10);
        }

        final void c() {
            this.f9749b.decrementAndGet();
        }

        @Override // android.text.SpanWatcher
        public void onSpanAdded(Spannable spannable, Object obj, int i8, int i9) {
            if (this.f9749b.get() <= 0 || !b(obj)) {
                ((SpanWatcher) this.f9748a).onSpanAdded(spannable, obj, i8, i9);
            }
        }

        @Override // android.text.SpanWatcher
        public void onSpanChanged(Spannable spannable, Object obj, int i8, int i9, int i10, int i11) {
            int i12;
            int i13;
            if (this.f9749b.get() <= 0 || !b(obj)) {
                if (Build.VERSION.SDK_INT < 28) {
                    if (i8 > i9) {
                        i8 = 0;
                    }
                    if (i10 > i11) {
                        i12 = i8;
                        i13 = 0;
                        ((SpanWatcher) this.f9748a).onSpanChanged(spannable, obj, i12, i9, i13, i11);
                    }
                }
                i12 = i8;
                i13 = i10;
                ((SpanWatcher) this.f9748a).onSpanChanged(spannable, obj, i12, i9, i13, i11);
            }
        }

        @Override // android.text.SpanWatcher
        public void onSpanRemoved(Spannable spannable, Object obj, int i8, int i9) {
            if (this.f9749b.get() <= 0 || !b(obj)) {
                ((SpanWatcher) this.f9748a).onSpanRemoved(spannable, obj, i8, i9);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
            ((TextWatcher) this.f9748a).onTextChanged(charSequence, i8, i9, i10);
        }
    }

    c(Class cls, CharSequence charSequence) {
        super(charSequence);
        this.f9747f = new ArrayList();
        g1.e.k(cls, "watcherClass cannot be null");
        this.f9746e = cls;
    }

    private void b() {
        for (int i8 = 0; i8 < this.f9747f.size(); i8++) {
            ((a) this.f9747f.get(i8)).a();
        }
    }

    public static c c(Class cls, CharSequence charSequence) {
        return new c(cls, charSequence);
    }

    private void e() {
        for (int i8 = 0; i8 < this.f9747f.size(); i8++) {
            ((a) this.f9747f.get(i8)).onTextChanged(this, 0, length(), length());
        }
    }

    private a f(Object obj) {
        for (int i8 = 0; i8 < this.f9747f.size(); i8++) {
            a aVar = (a) this.f9747f.get(i8);
            if (aVar.f9748a == obj) {
                return aVar;
            }
        }
        return null;
    }

    private boolean g(Class cls) {
        return this.f9746e == cls;
    }

    private boolean h(Object obj) {
        return obj != null && g(obj.getClass());
    }

    private void i() {
        for (int i8 = 0; i8 < this.f9747f.size(); i8++) {
            ((a) this.f9747f.get(i8)).c();
        }
    }

    public void a() {
        b();
    }

    public void d() {
        i();
        e();
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanEnd(Object obj) {
        a f8;
        if (h(obj) && (f8 = f(obj)) != null) {
            obj = f8;
        }
        return super.getSpanEnd(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanFlags(Object obj) {
        a f8;
        if (h(obj) && (f8 = f(obj)) != null) {
            obj = f8;
        }
        return super.getSpanFlags(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanStart(Object obj) {
        a f8;
        if (h(obj) && (f8 = f(obj)) != null) {
            obj = f8;
        }
        return super.getSpanStart(obj);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public Object[] getSpans(int i8, int i9, Class cls) {
        if (g(cls)) {
            a[] aVarArr = (a[]) super.getSpans(i8, i9, a.class);
            Object[] objArr = (Object[]) Array.newInstance(cls, aVarArr.length);
            for (int i10 = 0; i10 < aVarArr.length; i10++) {
                objArr[i10] = aVarArr[i10].f9748a;
            }
            return objArr;
        }
        return super.getSpans(i8, i9, cls);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int nextSpanTransition(int i8, int i9, Class cls) {
        return super.nextSpanTransition(i8, i9, (cls == null || g(cls)) ? a.class : a.class);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public void removeSpan(Object obj) {
        a aVar;
        if (h(obj)) {
            aVar = f(obj);
            if (aVar != null) {
                obj = aVar;
            }
        } else {
            aVar = null;
        }
        super.removeSpan(obj);
        if (aVar != null) {
            this.f9747f.remove(aVar);
        }
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public void setSpan(Object obj, int i8, int i9, int i10) {
        if (h(obj)) {
            a aVar = new a(obj);
            this.f9747f.add(aVar);
            obj = aVar;
        }
        super.setSpan(obj, i8, i9, i10);
    }

    @Override // android.text.SpannableStringBuilder, java.lang.CharSequence
    public CharSequence subSequence(int i8, int i9) {
        return new c(this.f9746e, this, i8, i9);
    }

    c(Class cls, CharSequence charSequence, int i8, int i9) {
        super(charSequence, i8, i9);
        this.f9747f = new ArrayList();
        g1.e.k(cls, "watcherClass cannot be null");
        this.f9746e = cls;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder delete(int i8, int i9) {
        super.delete(i8, i9);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder insert(int i8, CharSequence charSequence) {
        super.insert(i8, charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder replace(int i8, int i9, CharSequence charSequence) {
        b();
        super.replace(i8, i9, charSequence);
        i();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(char c8) {
        super.append(c8);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder insert(int i8, CharSequence charSequence, int i9, int i10) {
        super.insert(i8, charSequence, i9, i10);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder replace(int i8, int i9, CharSequence charSequence, int i10, int i11) {
        b();
        super.replace(i8, i9, charSequence, i10, i11);
        i();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(CharSequence charSequence, int i8, int i9) {
        super.append(charSequence, i8, i9);
        return this;
    }

    @Override // android.text.SpannableStringBuilder
    public SpannableStringBuilder append(CharSequence charSequence, Object obj, int i8) {
        super.append(charSequence, obj, i8);
        return this;
    }
}
