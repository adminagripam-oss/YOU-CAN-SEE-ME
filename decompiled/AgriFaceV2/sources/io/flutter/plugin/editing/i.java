package io.flutter.plugin.editing;

import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import h6.w;
import java.util.ArrayList;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i extends SpannableStringBuilder {

    /* renamed from: e  reason: collision with root package name */
    private int f7870e = 0;

    /* renamed from: f  reason: collision with root package name */
    private int f7871f = 0;

    /* renamed from: g  reason: collision with root package name */
    private ArrayList f7872g = new ArrayList();

    /* renamed from: h  reason: collision with root package name */
    private ArrayList f7873h = new ArrayList();

    /* renamed from: i  reason: collision with root package name */
    private ArrayList f7874i = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    private String f7875j;

    /* renamed from: k  reason: collision with root package name */
    private String f7876k;

    /* renamed from: l  reason: collision with root package name */
    private int f7877l;

    /* renamed from: m  reason: collision with root package name */
    private int f7878m;

    /* renamed from: n  reason: collision with root package name */
    private int f7879n;

    /* renamed from: o  reason: collision with root package name */
    private int f7880o;

    /* renamed from: p  reason: collision with root package name */
    private BaseInputConnection f7881p;

    /* loaded from: classes.dex */
    class a extends BaseInputConnection {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Editable f7882a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(View view, boolean z7, Editable editable) {
            super(view, z7);
            this.f7882a = editable;
        }

        @Override // android.view.inputmethod.BaseInputConnection
        public Editable getEditable() {
            return this.f7882a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        void a(boolean z7, boolean z8, boolean z9);
    }

    public i(w.e eVar, View view) {
        this.f7881p = new a(view, true, this);
        if (eVar != null) {
            n(eVar);
        }
    }

    private void j(b bVar, boolean z7, boolean z8, boolean z9) {
        this.f7871f++;
        bVar.a(z7, z8, z9);
        this.f7871f--;
    }

    private void k(boolean z7, boolean z8, boolean z9) {
        if (z7 || z8 || z9) {
            Iterator it = this.f7872g.iterator();
            while (it.hasNext()) {
                j((b) it.next(), z7, z8, z9);
            }
        }
    }

    public void a(b bVar) {
        ArrayList arrayList;
        if (this.f7871f > 0) {
            z5.b.b("ListenableEditingState", "adding a listener " + bVar.toString() + " in a listener callback");
        }
        if (this.f7870e > 0) {
            z5.b.g("ListenableEditingState", "a listener was added to EditingState while a batch edit was in progress");
            arrayList = this.f7873h;
        } else {
            arrayList = this.f7872g;
        }
        arrayList.add(bVar);
    }

    public void b() {
        this.f7870e++;
        if (this.f7871f > 0) {
            z5.b.b("ListenableEditingState", "editing state should not be changed in a listener callback");
        }
        if (this.f7870e != 1 || this.f7872g.isEmpty()) {
            return;
        }
        this.f7876k = toString();
        this.f7877l = i();
        this.f7878m = h();
        this.f7879n = g();
        this.f7880o = f();
    }

    public void c() {
        this.f7874i.clear();
    }

    public void d() {
        int i8 = this.f7870e;
        if (i8 == 0) {
            z5.b.b("ListenableEditingState", "endBatchEdit called without a matching beginBatchEdit");
            return;
        }
        if (i8 == 1) {
            Iterator it = this.f7873h.iterator();
            while (it.hasNext()) {
                j((b) it.next(), true, true, true);
            }
            if (!this.f7872g.isEmpty()) {
                z5.b.f("ListenableEditingState", "didFinishBatchEdit with " + String.valueOf(this.f7872g.size()) + " listener(s)");
                boolean equals = toString().equals(this.f7876k) ^ true;
                boolean z7 = false;
                boolean z8 = (this.f7877l == i() && this.f7878m == h()) ? false : true;
                if (this.f7879n != g() || this.f7880o != f()) {
                    z7 = true;
                }
                k(equals, z8, z7);
            }
        }
        this.f7872g.addAll(this.f7873h);
        this.f7873h.clear();
        this.f7870e--;
    }

    public ArrayList e() {
        ArrayList arrayList = new ArrayList(this.f7874i);
        this.f7874i.clear();
        return arrayList;
    }

    public final int f() {
        return BaseInputConnection.getComposingSpanEnd(this);
    }

    public final int g() {
        return BaseInputConnection.getComposingSpanStart(this);
    }

    public final int h() {
        return Selection.getSelectionEnd(this);
    }

    public final int i() {
        return Selection.getSelectionStart(this);
    }

    public void l(b bVar) {
        if (this.f7871f > 0) {
            z5.b.b("ListenableEditingState", "removing a listener " + bVar.toString() + " in a listener callback");
        }
        this.f7872g.remove(bVar);
        if (this.f7870e > 0) {
            this.f7873h.remove(bVar);
        }
    }

    public void m(int i8, int i9) {
        if (i8 < 0 || i8 >= i9) {
            BaseInputConnection.removeComposingSpans(this);
        } else {
            this.f7881p.setComposingRegion(i8, i9);
        }
    }

    public void n(w.e eVar) {
        b();
        replace(0, length(), (CharSequence) eVar.f7466a);
        if (eVar.c()) {
            Selection.setSelection(this, eVar.f7467b, eVar.f7468c);
        } else {
            Selection.removeSelection(this);
        }
        m(eVar.f7469d, eVar.f7470e);
        c();
        d();
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public void setSpan(Object obj, int i8, int i9, int i10) {
        super.setSpan(obj, i8, i9, i10);
        this.f7874i.add(new k(toString(), i(), h(), g(), f()));
    }

    @Override // android.text.SpannableStringBuilder, java.lang.CharSequence
    public String toString() {
        String str = this.f7875j;
        if (str != null) {
            return str;
        }
        String spannableStringBuilder = super.toString();
        this.f7875j = spannableStringBuilder;
        return spannableStringBuilder;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    public SpannableStringBuilder replace(int i8, int i9, CharSequence charSequence, int i10, int i11) {
        boolean z7;
        boolean z8;
        if (this.f7871f > 0) {
            z5.b.b("ListenableEditingState", "editing state should not be changed in a listener callback");
        }
        String iVar = toString();
        int i12 = i9 - i8;
        boolean z9 = i12 != i11 - i10;
        for (int i13 = 0; i13 < i12 && !z9; i13++) {
            z9 |= charAt(i8 + i13) != charSequence.charAt(i10 + i13);
        }
        if (z9) {
            this.f7875j = null;
        }
        int i14 = i();
        int h8 = h();
        int g8 = g();
        int f8 = f();
        SpannableStringBuilder replace = super.replace(i8, i9, charSequence, i10, i11);
        boolean z10 = z9;
        this.f7874i.add(new k(iVar, i8, i9, charSequence, i(), h(), g(), f()));
        if (this.f7870e > 0) {
            return replace;
        }
        boolean z11 = (i() == i14 && h() == h8) ? false : true;
        if (g() == g8 && f() == f8) {
            z7 = z10;
            z8 = false;
        } else {
            z7 = z10;
            z8 = true;
        }
        k(z7, z11, z8);
        return replace;
    }
}
