package androidx.webkit.internal;

import org.chromium.support_lib_boundary.WebResourceRequestBoundaryInterface;
/* loaded from: classes.dex */
public class e1 {

    /* renamed from: a  reason: collision with root package name */
    private final WebResourceRequestBoundaryInterface f4015a;

    public e1(WebResourceRequestBoundaryInterface webResourceRequestBoundaryInterface) {
        this.f4015a = webResourceRequestBoundaryInterface;
    }

    public boolean a() {
        return this.f4015a.isRedirect();
    }
}
