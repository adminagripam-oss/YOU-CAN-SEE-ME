package androidx.lifecycle;

import android.content.Context;
import androidx.lifecycle.w;
import java.util.List;
/* loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements x1.a {
    @Override // x1.a
    public List a() {
        List f8;
        f8 = s6.n.f();
        return f8;
    }

    @Override // x1.a
    /* renamed from: c */
    public m b(Context context) {
        d7.k.e(context, "context");
        androidx.startup.a e8 = androidx.startup.a.e(context);
        d7.k.d(e8, "getInstance(context)");
        if (e8.g(ProcessLifecycleInitializer.class)) {
            j.a(context);
            w.b bVar = w.f3843i;
            bVar.b(context);
            return bVar.a();
        }
        throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml".toString());
    }
}
