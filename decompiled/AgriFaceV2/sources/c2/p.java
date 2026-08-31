package c2;

import android.webkit.WebResourceRequest;
import androidx.webkit.internal.a;
import androidx.webkit.internal.e1;
import androidx.webkit.internal.g1;
import androidx.webkit.internal.h1;
/* loaded from: classes.dex */
public abstract class p {
    private static e1 a(WebResourceRequest webResourceRequest) {
        return h1.c().k(webResourceRequest);
    }

    public static boolean b(WebResourceRequest webResourceRequest) {
        a.c cVar = g1.f4043u;
        if (cVar.c()) {
            return androidx.webkit.internal.c.j(webResourceRequest);
        }
        if (cVar.d()) {
            return a(webResourceRequest).a();
        }
        throw g1.a();
    }
}
