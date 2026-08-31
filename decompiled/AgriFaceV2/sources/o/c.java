package o;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.d;
import androidx.browser.customtabs.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import o.a;
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final Uri f9799a;

    /* renamed from: c  reason: collision with root package name */
    private List f9801c;

    /* renamed from: d  reason: collision with root package name */
    private Bundle f9802d;

    /* renamed from: b  reason: collision with root package name */
    private final d.C0014d f9800b = new d.C0014d();

    /* renamed from: e  reason: collision with root package name */
    private a f9803e = new a.C0134a();

    /* renamed from: f  reason: collision with root package name */
    private int f9804f = 0;

    public c(Uri uri) {
        this.f9799a = uri;
    }

    public b a(i iVar) {
        if (iVar != null) {
            this.f9800b.i(iVar);
            Intent intent = this.f9800b.b().f2596a;
            intent.setData(this.f9799a);
            intent.putExtra("android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", true);
            if (this.f9801c != null) {
                intent.putExtra("android.support.customtabs.extra.ADDITIONAL_TRUSTED_ORIGINS", new ArrayList(this.f9801c));
            }
            Bundle bundle = this.f9802d;
            if (bundle != null) {
                intent.putExtra("androidx.browser.trusted.EXTRA_SPLASH_SCREEN_PARAMS", bundle);
            }
            List emptyList = Collections.emptyList();
            intent.putExtra("androidx.browser.trusted.extra.DISPLAY_MODE", this.f9803e.a());
            intent.putExtra("androidx.browser.trusted.extra.SCREEN_ORIENTATION", this.f9804f);
            return new b(intent, emptyList);
        }
        throw new NullPointerException("CustomTabsSession is required for launching a TWA");
    }

    public c b(List list) {
        this.f9801c = list;
        return this;
    }

    public c c(androidx.browser.customtabs.a aVar) {
        this.f9800b.e(aVar);
        return this;
    }

    public c d(a aVar) {
        this.f9803e = aVar;
        return this;
    }

    public c e(int i8) {
        this.f9804f = i8;
        return this;
    }
}
