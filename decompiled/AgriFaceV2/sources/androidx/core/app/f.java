package androidx.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.w;
import androidx.core.view.t;
import androidx.lifecycle.i;
import androidx.lifecycle.y;
/* loaded from: classes.dex */
public abstract class f extends Activity implements androidx.lifecycle.m, t.a {
    private final b1.g extraDataMap = new b1.g();
    private final androidx.lifecycle.n lifecycleRegistry = new androidx.lifecycle.n(this);

    /* loaded from: classes.dex */
    public static class a {
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x003e, code lost:
        if (r4.equals("--list-dumpables") == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0047, code lost:
        if (r4.equals("--dump-dumpable") == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004e, code lost:
        if (android.os.Build.VERSION.SDK_INT < 33) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0052, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean a(java.lang.String[] r4) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            if (r4 == 0) goto Lf
            int r2 = r4.length
            if (r2 != 0) goto L9
            r2 = r0
            goto La
        L9:
            r2 = r1
        La:
            if (r2 == 0) goto Ld
            goto Lf
        Ld:
            r2 = r1
            goto L10
        Lf:
            r2 = r0
        L10:
            if (r2 != 0) goto L65
            r4 = r4[r1]
            int r2 = r4.hashCode()
            switch(r2) {
                case -645125871: goto L53;
                case 100470631: goto L41;
                case 472614934: goto L38;
                case 1159329357: goto L26;
                case 1455016274: goto L1c;
                default: goto L1b;
            }
        L1b:
            goto L65
        L1c:
            java.lang.String r2 = "--autofill"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L25
            goto L65
        L25:
            return r0
        L26:
            java.lang.String r2 = "--contentcapture"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L2f
            goto L65
        L2f:
            int r4 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            if (r4 < r2) goto L36
            goto L37
        L36:
            r0 = r1
        L37:
            return r0
        L38:
            java.lang.String r2 = "--list-dumpables"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L4a
            goto L65
        L41:
            java.lang.String r2 = "--dump-dumpable"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L4a
            goto L65
        L4a:
            int r4 = android.os.Build.VERSION.SDK_INT
            r2 = 33
            if (r4 < r2) goto L51
            goto L52
        L51:
            r0 = r1
        L52:
            return r0
        L53:
            java.lang.String r2 = "--translation"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L5c
            goto L65
        L5c:
            int r4 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r4 < r2) goto L63
            goto L64
        L63:
            r0 = r1
        L64:
            return r0
        L65:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.f.a(java.lang.String[]):boolean");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        d7.k.e(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        d7.k.d(decorView, "window.decorView");
        if (t.d(decorView, keyEvent)) {
            return true;
        }
        return t.e(this, decorView, this, keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        d7.k.e(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        d7.k.d(decorView, "window.decorView");
        if (t.d(decorView, keyEvent)) {
            return true;
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public <T extends a> T getExtraData(Class<T> cls) {
        d7.k.e(cls, "extraDataClass");
        w.a(this.extraDataMap.get(cls));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        y.f3857b.c(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        d7.k.e(bundle, "outState");
        this.lifecycleRegistry.m(i.b.CREATED);
        super.onSaveInstanceState(bundle);
    }

    public void putExtraData(a aVar) {
        d7.k.e(aVar, "extraData");
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean shouldDumpInternalState(String[] strArr) {
        return !a(strArr);
    }

    @Override // androidx.core.view.t.a
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        d7.k.e(keyEvent, "event");
        return super.dispatchKeyEvent(keyEvent);
    }
}
