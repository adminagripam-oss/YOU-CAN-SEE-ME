package androidx.webkit.internal;

import java.util.List;
import org.chromium.support_lib_boundary.WebViewCookieManagerBoundaryInterface;
/* loaded from: classes.dex */
public class l0 {

    /* renamed from: a  reason: collision with root package name */
    private final WebViewCookieManagerBoundaryInterface f4055a;

    public l0(WebViewCookieManagerBoundaryInterface webViewCookieManagerBoundaryInterface) {
        this.f4055a = webViewCookieManagerBoundaryInterface;
    }

    public List a(String str) {
        return this.f4055a.getCookieInfo(str);
    }
}
