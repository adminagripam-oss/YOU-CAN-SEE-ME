package i3;

import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public class b extends Exception {

    /* renamed from: e  reason: collision with root package name */
    protected final Status f7489e;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public b(com.google.android.gms.common.api.Status r4) {
        /*
            r3 = this;
            int r0 = r4.b()
            java.lang.String r1 = r4.c()
            if (r1 == 0) goto Lf
            java.lang.String r1 = r4.c()
            goto L11
        Lf:
            java.lang.String r1 = ""
        L11:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = ": "
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            r3.<init>(r0)
            r3.f7489e = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: i3.b.<init>(com.google.android.gms.common.api.Status):void");
    }

    public Status a() {
        return this.f7489e;
    }

    public int b() {
        return this.f7489e.b();
    }
}
