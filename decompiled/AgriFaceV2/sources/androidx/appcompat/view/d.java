package androidx.appcompat.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
/* loaded from: classes.dex */
public class d extends ContextWrapper {

    /* renamed from: f  reason: collision with root package name */
    private static Configuration f1715f;

    /* renamed from: a  reason: collision with root package name */
    private int f1716a;

    /* renamed from: b  reason: collision with root package name */
    private Resources.Theme f1717b;

    /* renamed from: c  reason: collision with root package name */
    private LayoutInflater f1718c;

    /* renamed from: d  reason: collision with root package name */
    private Configuration f1719d;

    /* renamed from: e  reason: collision with root package name */
    private Resources f1720e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static Context a(d dVar, Configuration configuration) {
            return dVar.createConfigurationContext(configuration);
        }
    }

    public d(Context context, int i8) {
        super(context);
        this.f1716a = i8;
    }

    private Resources b() {
        if (this.f1720e == null) {
            Configuration configuration = this.f1719d;
            this.f1720e = (configuration == null || e(configuration)) ? super.getResources() : a.a(this, this.f1719d).getResources();
        }
        return this.f1720e;
    }

    private void d() {
        boolean z7 = this.f1717b == null;
        if (z7) {
            this.f1717b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f1717b.setTo(theme);
            }
        }
        f(this.f1717b, this.f1716a, z7);
    }

    private static boolean e(Configuration configuration) {
        if (configuration == null) {
            return true;
        }
        if (f1715f == null) {
            Configuration configuration2 = new Configuration();
            configuration2.fontScale = 0.0f;
            f1715f = configuration2;
        }
        return configuration.equals(f1715f);
    }

    public void a(Configuration configuration) {
        if (this.f1720e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f1719d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f1719d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public int c() {
        return this.f1716a;
    }

    protected void f(Resources.Theme theme, int i8, boolean z7) {
        theme.applyStyle(i8, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return b();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if ("layout_inflater".equals(str)) {
            if (this.f1718c == null) {
                this.f1718c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return this.f1718c;
        }
        return getBaseContext().getSystemService(str);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1717b;
        if (theme != null) {
            return theme;
        }
        if (this.f1716a == 0) {
            this.f1716a = g.i.f6922d;
        }
        d();
        return this.f1717b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i8) {
        if (this.f1716a != i8) {
            this.f1716a = i8;
            d();
        }
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f1717b = theme;
    }
}
