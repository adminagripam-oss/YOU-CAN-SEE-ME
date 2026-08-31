package io.flutter.plugin.editing;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import h6.w;
import io.flutter.embedding.android.a0;
import io.flutter.plugin.editing.i;
import java.util.HashMap;
/* loaded from: classes.dex */
public class l implements i.b {

    /* renamed from: a  reason: collision with root package name */
    private final View f7896a;

    /* renamed from: b  reason: collision with root package name */
    private final InputMethodManager f7897b;

    /* renamed from: c  reason: collision with root package name */
    private final AutofillManager f7898c;

    /* renamed from: d  reason: collision with root package name */
    private final w f7899d;

    /* renamed from: e  reason: collision with root package name */
    private c f7900e = new c(c.a.NO_TARGET, 0);

    /* renamed from: f  reason: collision with root package name */
    private w.b f7901f;

    /* renamed from: g  reason: collision with root package name */
    private SparseArray f7902g;

    /* renamed from: h  reason: collision with root package name */
    private i f7903h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f7904i;

    /* renamed from: j  reason: collision with root package name */
    private InputConnection f7905j;

    /* renamed from: k  reason: collision with root package name */
    private io.flutter.plugin.platform.w f7906k;

    /* renamed from: l  reason: collision with root package name */
    private Rect f7907l;

    /* renamed from: m  reason: collision with root package name */
    private ImeSyncDeferringInsetsCallback f7908m;

    /* renamed from: n  reason: collision with root package name */
    private w.e f7909n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f7910o;

    /* loaded from: classes.dex */
    class a implements w.f {
        a() {
        }

        @Override // h6.w.f
        public void a() {
            l lVar = l.this;
            lVar.E(lVar.f7896a);
        }

        @Override // h6.w.f
        public void b() {
            l.this.l();
        }

        @Override // h6.w.f
        public void c(String str, Bundle bundle) {
            l.this.A(str, bundle);
        }

        @Override // h6.w.f
        public void d(int i8, boolean z7) {
            l.this.B(i8, z7);
        }

        @Override // h6.w.f
        public void e(double d8, double d9, double[] dArr) {
            l.this.z(d8, d9, dArr);
        }

        @Override // h6.w.f
        public void f() {
            l.this.w();
        }

        @Override // h6.w.f
        public void g(boolean z7) {
            if (l.this.f7898c == null) {
                return;
            }
            if (z7) {
                l.this.f7898c.commit();
            } else {
                l.this.f7898c.cancel();
            }
        }

        @Override // h6.w.f
        public void h(int i8, w.b bVar) {
            l.this.C(i8, bVar);
        }

        @Override // h6.w.f
        public void i() {
            if (l.this.f7900e.f7916a == c.a.PHYSICAL_DISPLAY_PLATFORM_VIEW) {
                l.this.x();
                return;
            }
            l lVar = l.this;
            lVar.r(lVar.f7896a);
        }

        @Override // h6.w.f
        public void j(w.e eVar) {
            l lVar = l.this;
            lVar.D(lVar.f7896a, eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements d {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ boolean f7912a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ double[] f7913b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ double[] f7914c;

        b(boolean z7, double[] dArr, double[] dArr2) {
            this.f7912a = z7;
            this.f7913b = dArr;
            this.f7914c = dArr2;
        }

        @Override // io.flutter.plugin.editing.l.d
        public void a(double d8, double d9) {
            double d10 = 1.0d;
            if (!this.f7912a) {
                double[] dArr = this.f7913b;
                d10 = 1.0d / (((dArr[3] * d8) + (dArr[7] * d9)) + dArr[15]);
            }
            double[] dArr2 = this.f7913b;
            double d11 = ((dArr2[0] * d8) + (dArr2[4] * d9) + dArr2[12]) * d10;
            double d12 = ((dArr2[1] * d8) + (dArr2[5] * d9) + dArr2[13]) * d10;
            double[] dArr3 = this.f7914c;
            if (d11 < dArr3[0]) {
                dArr3[0] = d11;
            } else if (d11 > dArr3[1]) {
                dArr3[1] = d11;
            }
            if (d12 < dArr3[2]) {
                dArr3[2] = d12;
            } else if (d12 > dArr3[3]) {
                dArr3[3] = d12;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        a f7916a;

        /* renamed from: b  reason: collision with root package name */
        int f7917b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum a {
            NO_TARGET,
            FRAMEWORK_CLIENT,
            VIRTUAL_DISPLAY_PLATFORM_VIEW,
            PHYSICAL_DISPLAY_PLATFORM_VIEW
        }

        public c(a aVar, int i8) {
            this.f7916a = aVar;
            this.f7917b = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface d {
        void a(double d8, double d9);
    }

    public l(View view, w wVar, io.flutter.plugin.platform.w wVar2) {
        this.f7896a = view;
        this.f7903h = new i(null, view);
        this.f7897b = (InputMethodManager) view.getContext().getSystemService("input_method");
        int i8 = Build.VERSION.SDK_INT;
        this.f7898c = (AutofillManager) view.getContext().getSystemService(AutofillManager.class);
        if (i8 >= 30) {
            ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = new ImeSyncDeferringInsetsCallback(view);
            this.f7908m = imeSyncDeferringInsetsCallback;
            imeSyncDeferringInsetsCallback.install();
        }
        this.f7899d = wVar;
        wVar.n(new a());
        wVar.k();
        this.f7906k = wVar2;
        wVar2.D(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B(int i8, boolean z7) {
        if (!z7) {
            this.f7900e = new c(c.a.PHYSICAL_DISPLAY_PLATFORM_VIEW, i8);
            this.f7905j = null;
            return;
        }
        this.f7896a.requestFocus();
        this.f7900e = new c(c.a.VIRTUAL_DISPLAY_PLATFORM_VIEW, i8);
        this.f7897b.restartInput(this.f7896a);
        this.f7904i = false;
    }

    private void G(w.b bVar) {
        if (bVar == null || bVar.f7450j == null) {
            this.f7902g = null;
            return;
        }
        w.b[] bVarArr = bVar.f7452l;
        SparseArray sparseArray = new SparseArray();
        this.f7902g = sparseArray;
        if (bVarArr == null) {
            sparseArray.put(bVar.f7450j.f7453a.hashCode(), bVar);
            return;
        }
        for (w.b bVar2 : bVarArr) {
            w.b.a aVar = bVar2.f7450j;
            if (aVar != null) {
                this.f7902g.put(aVar.f7453a.hashCode(), bVar2);
                this.f7898c.notifyValueChanged(this.f7896a, aVar.f7453a.hashCode(), AutofillValue.forText(aVar.f7455c.f7466a));
            }
        }
    }

    private static boolean m(w.e eVar, w.e eVar2) {
        int i8 = eVar.f7470e - eVar.f7469d;
        if (i8 != eVar2.f7470e - eVar2.f7469d) {
            return true;
        }
        for (int i9 = 0; i9 < i8; i9++) {
            if (eVar.f7466a.charAt(eVar.f7469d + i9) != eVar2.f7466a.charAt(eVar2.f7469d + i9)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(View view) {
        x();
        this.f7897b.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    private static int s(w.c cVar, boolean z7, boolean z8, boolean z9, boolean z10, w.d dVar) {
        w.g gVar = cVar.f7457a;
        if (gVar == w.g.DATETIME) {
            return 4;
        }
        if (gVar == w.g.NUMBER) {
            int i8 = cVar.f7458b ? 4098 : 2;
            return cVar.f7459c ? i8 | 8192 : i8;
        } else if (gVar == w.g.PHONE) {
            return 3;
        } else {
            if (gVar == w.g.NONE) {
                return 0;
            }
            int i9 = gVar == w.g.MULTILINE ? 131073 : gVar == w.g.EMAIL_ADDRESS ? 33 : gVar == w.g.URL ? 17 : gVar == w.g.VISIBLE_PASSWORD ? 145 : gVar == w.g.NAME ? 97 : gVar == w.g.POSTAL_ADDRESS ? 113 : 1;
            if (z7) {
                i9 = i9 | 524288 | 128;
            } else {
                if (z8) {
                    i9 |= 32768;
                }
                if (!z9) {
                    i9 = i9 | 524288 | 144;
                }
            }
            return dVar == w.d.CHARACTERS ? i9 | 4096 : dVar == w.d.WORDS ? i9 | 8192 : dVar == w.d.SENTENCES ? i9 | 16384 : i9;
        }
    }

    private boolean u() {
        return this.f7902g != null;
    }

    private void v(String str) {
        if (this.f7898c == null || !u()) {
            return;
        }
        this.f7898c.notifyValueChanged(this.f7896a, this.f7901f.f7450j.f7453a.hashCode(), AutofillValue.forText(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (this.f7898c == null || !u()) {
            return;
        }
        String str = this.f7901f.f7450j.f7453a;
        int[] iArr = new int[2];
        this.f7896a.getLocationOnScreen(iArr);
        Rect rect = new Rect(this.f7907l);
        rect.offset(iArr[0], iArr[1]);
        this.f7898c.notifyViewEntered(this.f7896a, str.hashCode(), rect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        w.b bVar;
        if (this.f7898c == null || (bVar = this.f7901f) == null || bVar.f7450j == null || !u()) {
            return;
        }
        this.f7898c.notifyViewExited(this.f7896a, this.f7901f.f7450j.f7453a.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(double d8, double d9, double[] dArr) {
        double[] dArr2 = new double[4];
        boolean z7 = dArr[3] == 0.0d && dArr[7] == 0.0d && dArr[15] == 1.0d;
        double d10 = dArr[12];
        double d11 = dArr[15];
        double d12 = d10 / d11;
        dArr2[1] = d12;
        dArr2[0] = d12;
        double d13 = dArr[13] / d11;
        dArr2[3] = d13;
        dArr2[2] = d13;
        b bVar = new b(z7, dArr, dArr2);
        bVar.a(d8, 0.0d);
        bVar.a(d8, d9);
        bVar.a(0.0d, d9);
        Float valueOf = Float.valueOf(this.f7896a.getContext().getResources().getDisplayMetrics().density);
        this.f7907l = new Rect((int) (dArr2[0] * valueOf.floatValue()), (int) (dArr2[2] * valueOf.floatValue()), (int) Math.ceil(dArr2[1] * valueOf.floatValue()), (int) Math.ceil(dArr2[3] * valueOf.floatValue()));
    }

    public void A(String str, Bundle bundle) {
        this.f7897b.sendAppPrivateCommand(this.f7896a, str, bundle);
    }

    void C(int i8, w.b bVar) {
        x();
        this.f7901f = bVar;
        this.f7900e = new c(c.a.FRAMEWORK_CLIENT, i8);
        this.f7903h.l(this);
        w.b.a aVar = bVar.f7450j;
        this.f7903h = new i(aVar != null ? aVar.f7455c : null, this.f7896a);
        G(bVar);
        this.f7904i = true;
        F();
        this.f7907l = null;
        this.f7903h.a(this);
    }

    void D(View view, w.e eVar) {
        w.e eVar2;
        if (!this.f7904i && (eVar2 = this.f7909n) != null && eVar2.b()) {
            boolean m8 = m(this.f7909n, eVar);
            this.f7904i = m8;
            if (m8) {
                z5.b.e("TextInputPlugin", "Composing region changed by the framework. Restarting the input method.");
            }
        }
        this.f7909n = eVar;
        this.f7903h.n(eVar);
        if (this.f7904i) {
            this.f7897b.restartInput(view);
            this.f7904i = false;
        }
    }

    void E(View view) {
        w.c cVar;
        w.b bVar = this.f7901f;
        if (bVar != null && (cVar = bVar.f7447g) != null && cVar.f7457a == w.g.NONE) {
            r(view);
            return;
        }
        view.requestFocus();
        this.f7897b.showSoftInput(view, 0);
    }

    public void F() {
        if (this.f7900e.f7916a == c.a.VIRTUAL_DISPLAY_PLATFORM_VIEW) {
            this.f7910o = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004d, code lost:
        if (r7 == r1.f7470e) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ac  */
    @Override // io.flutter.plugin.editing.i.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r9, boolean r10, boolean r11) {
        /*
            r8 = this;
            if (r9 == 0) goto Lb
            io.flutter.plugin.editing.i r9 = r8.f7903h
            java.lang.String r9 = r9.toString()
            r8.v(r9)
        Lb:
            io.flutter.plugin.editing.i r9 = r8.f7903h
            int r9 = r9.i()
            io.flutter.plugin.editing.i r10 = r8.f7903h
            int r10 = r10.h()
            io.flutter.plugin.editing.i r11 = r8.f7903h
            int r11 = r11.g()
            io.flutter.plugin.editing.i r0 = r8.f7903h
            int r7 = r0.f()
            io.flutter.plugin.editing.i r0 = r8.f7903h
            java.util.ArrayList r0 = r0.e()
            h6.w$e r1 = r8.f7909n
            if (r1 == 0) goto L52
            io.flutter.plugin.editing.i r1 = r8.f7903h
            java.lang.String r1 = r1.toString()
            h6.w$e r2 = r8.f7909n
            java.lang.String r2 = r2.f7466a
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L50
            h6.w$e r1 = r8.f7909n
            int r2 = r1.f7467b
            if (r9 != r2) goto L50
            int r2 = r1.f7468c
            if (r10 != r2) goto L50
            int r2 = r1.f7469d
            if (r11 != r2) goto L50
            int r1 = r1.f7470e
            if (r7 != r1) goto L50
            goto L52
        L50:
            r1 = 0
            goto L53
        L52:
            r1 = 1
        L53:
            if (r1 != 0) goto Lac
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "send EditingState to flutter: "
            r1.append(r2)
            io.flutter.plugin.editing.i r2 = r8.f7903h
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "TextInputPlugin"
            z5.b.f(r2, r1)
            h6.w$b r1 = r8.f7901f
            boolean r1 = r1.f7445e
            if (r1 == 0) goto L86
            h6.w r1 = r8.f7899d
            io.flutter.plugin.editing.l$c r2 = r8.f7900e
            int r2 = r2.f7917b
            r1.q(r2, r0)
            io.flutter.plugin.editing.i r0 = r8.f7903h
            r0.c()
            goto L99
        L86:
            h6.w r0 = r8.f7899d
            io.flutter.plugin.editing.l$c r1 = r8.f7900e
            int r1 = r1.f7917b
            io.flutter.plugin.editing.i r2 = r8.f7903h
            java.lang.String r2 = r2.toString()
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r7
            r0.p(r1, r2, r3, r4, r5, r6)
        L99:
            h6.w$e r6 = new h6.w$e
            io.flutter.plugin.editing.i r0 = r8.f7903h
            java.lang.String r1 = r0.toString()
            r0 = r6
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r7
            r0.<init>(r1, r2, r3, r4, r5)
            r8.f7909n = r6
            goto Lb1
        Lac:
            io.flutter.plugin.editing.i r9 = r8.f7903h
            r9.c()
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugin.editing.l.a(boolean, boolean, boolean):void");
    }

    public void j(SparseArray sparseArray) {
        w.b.a aVar;
        w.b.a aVar2;
        w.b bVar = this.f7901f;
        if (bVar == null || this.f7902g == null || (aVar = bVar.f7450j) == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i8 = 0; i8 < sparseArray.size(); i8++) {
            w.b bVar2 = (w.b) this.f7902g.get(sparseArray.keyAt(i8));
            if (bVar2 != null && (aVar2 = bVar2.f7450j) != null) {
                String charSequence = ((AutofillValue) sparseArray.valueAt(i8)).getTextValue().toString();
                w.e eVar = new w.e(charSequence, charSequence.length(), charSequence.length(), -1, -1);
                if (aVar2.f7453a.equals(aVar.f7453a)) {
                    this.f7903h.n(eVar);
                } else {
                    hashMap.put(aVar2.f7453a, eVar);
                }
            }
        }
        this.f7899d.r(this.f7900e.f7917b, hashMap);
    }

    public void k(int i8) {
        c cVar = this.f7900e;
        c.a aVar = cVar.f7916a;
        if ((aVar == c.a.VIRTUAL_DISPLAY_PLATFORM_VIEW || aVar == c.a.PHYSICAL_DISPLAY_PLATFORM_VIEW) && cVar.f7917b == i8) {
            this.f7900e = new c(c.a.NO_TARGET, 0);
            x();
            this.f7897b.hideSoftInputFromWindow(this.f7896a.getApplicationWindowToken(), 0);
            this.f7897b.restartInput(this.f7896a);
            this.f7904i = false;
        }
    }

    void l() {
        if (this.f7900e.f7916a == c.a.VIRTUAL_DISPLAY_PLATFORM_VIEW) {
            return;
        }
        this.f7903h.l(this);
        x();
        this.f7901f = null;
        G(null);
        this.f7900e = new c(c.a.NO_TARGET, 0);
        F();
        this.f7907l = null;
        this.f7897b.restartInput(this.f7896a);
    }

    public InputConnection n(View view, a0 a0Var, EditorInfo editorInfo) {
        c cVar = this.f7900e;
        c.a aVar = cVar.f7916a;
        if (aVar == c.a.NO_TARGET) {
            this.f7905j = null;
            return null;
        } else if (aVar == c.a.PHYSICAL_DISPLAY_PLATFORM_VIEW) {
            return null;
        } else {
            if (aVar == c.a.VIRTUAL_DISPLAY_PLATFORM_VIEW) {
                if (this.f7910o) {
                    return this.f7905j;
                }
                InputConnection onCreateInputConnection = this.f7906k.c(cVar.f7917b).onCreateInputConnection(editorInfo);
                this.f7905j = onCreateInputConnection;
                return onCreateInputConnection;
            }
            w.b bVar = this.f7901f;
            int s8 = s(bVar.f7447g, bVar.f7441a, bVar.f7442b, bVar.f7443c, bVar.f7444d, bVar.f7446f);
            editorInfo.inputType = s8;
            editorInfo.imeOptions = 33554432;
            w.b bVar2 = this.f7901f;
            if (!bVar2.f7444d) {
                editorInfo.imeOptions = 33554432 | 16777216;
            }
            Integer num = bVar2.f7448h;
            int intValue = num == null ? (s8 & 131072) != 0 ? 1 : 6 : num.intValue();
            w.b bVar3 = this.f7901f;
            String str = bVar3.f7449i;
            if (str != null) {
                editorInfo.actionLabel = str;
                editorInfo.actionId = intValue;
            }
            editorInfo.imeOptions = intValue | editorInfo.imeOptions;
            String[] strArr = bVar3.f7451k;
            if (strArr != null) {
                k1.a.c(editorInfo, strArr);
            }
            h hVar = new h(view, this.f7900e.f7917b, this.f7899d, a0Var, this.f7903h, editorInfo);
            editorInfo.initialSelStart = this.f7903h.i();
            editorInfo.initialSelEnd = this.f7903h.h();
            this.f7905j = hVar;
            return hVar;
        }
    }

    public void o() {
        this.f7906k.Q();
        this.f7899d.n(null);
        x();
        this.f7903h.l(this);
        ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = this.f7908m;
        if (imeSyncDeferringInsetsCallback != null) {
            imeSyncDeferringInsetsCallback.remove();
        }
    }

    public InputMethodManager p() {
        return this.f7897b;
    }

    public boolean q(KeyEvent keyEvent) {
        InputConnection inputConnection;
        if (!p().isAcceptingText() || (inputConnection = this.f7905j) == null) {
            return false;
        }
        return inputConnection instanceof h ? ((h) inputConnection).f(keyEvent) : inputConnection.sendKeyEvent(keyEvent);
    }

    public void t() {
        if (this.f7900e.f7916a == c.a.VIRTUAL_DISPLAY_PLATFORM_VIEW) {
            this.f7910o = true;
        }
    }

    public void y(ViewStructure viewStructure, int i8) {
        ViewStructure viewStructure2;
        CharSequence charSequence;
        Rect rect;
        if (u()) {
            String str = this.f7901f.f7450j.f7453a;
            AutofillId autofillId = viewStructure.getAutofillId();
            for (int i9 = 0; i9 < this.f7902g.size(); i9++) {
                int keyAt = this.f7902g.keyAt(i9);
                w.b.a aVar = ((w.b) this.f7902g.valueAt(i9)).f7450j;
                if (aVar != null) {
                    viewStructure.addChildCount(1);
                    ViewStructure newChild = viewStructure.newChild(i9);
                    newChild.setAutofillId(autofillId, keyAt);
                    String[] strArr = aVar.f7454b;
                    if (strArr.length > 0) {
                        newChild.setAutofillHints(strArr);
                    }
                    newChild.setAutofillType(1);
                    newChild.setVisibility(0);
                    String str2 = aVar.f7456d;
                    if (str2 != null) {
                        newChild.setHint(str2);
                    }
                    if (str.hashCode() != keyAt || (rect = this.f7907l) == null) {
                        viewStructure2 = newChild;
                        viewStructure2.setDimens(0, 0, 0, 0, 1, 1);
                        charSequence = aVar.f7455c.f7466a;
                    } else {
                        viewStructure2 = newChild;
                        newChild.setDimens(rect.left, rect.top, 0, 0, rect.width(), this.f7907l.height());
                        charSequence = this.f7903h;
                    }
                    viewStructure2.setAutofillValue(AutofillValue.forText(charSequence));
                }
            }
        }
    }
}
