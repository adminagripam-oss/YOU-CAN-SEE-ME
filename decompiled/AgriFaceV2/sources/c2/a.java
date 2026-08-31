package c2;

import android.webkit.CookieManager;
import androidx.webkit.internal.g1;
import androidx.webkit.internal.h1;
import androidx.webkit.internal.l0;
import java.util.List;
/* loaded from: classes.dex */
public abstract class a {
    private static l0 a(CookieManager cookieManager) {
        return h1.c().a(cookieManager);
    }

    public static List b(CookieManager cookieManager, String str) {
        if (g1.Z.d()) {
            return a(cookieManager).a(str);
        }
        throw g1.a();
    }
}
