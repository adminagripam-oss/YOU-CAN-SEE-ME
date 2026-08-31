package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class y0 extends ContextWrapper {

    /* renamed from: c  reason: collision with root package name */
    private static final Object f2533c = new Object();

    /* renamed from: d  reason: collision with root package name */
    private static ArrayList f2534d;

    /* renamed from: a  reason: collision with root package name */
    private final Resources f2535a;

    /* renamed from: b  reason: collision with root package name */
    private final Resources.Theme f2536b;

    private y0(Context context) {
        super(context);
        if (!h1.c()) {
            this.f2535a = new a1(this, context.getResources());
            this.f2536b = null;
            return;
        }
        h1 h1Var = new h1(this, context.getResources());
        this.f2535a = h1Var;
        Resources.Theme newTheme = h1Var.newTheme();
        this.f2536b = newTheme;
        newTheme.setTo(context.getTheme());
    }

    private static boolean a(Context context) {
        return ((context instanceof y0) || (context.getResources() instanceof a1) || (context.getResources() instanceof h1) || !h1.c()) ? false : true;
    }

    public static Context b(Context context) {
        if (a(context)) {
            synchronized (f2533c) {
                ArrayList arrayList = f2534d;
                if (arrayList == null) {
                    f2534d = new ArrayList();
                } else {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        WeakReference weakReference = (WeakReference) f2534d.get(size);
                        if (weakReference == null || weakReference.get() == null) {
                            f2534d.remove(size);
                        }
                    }
                    for (int size2 = f2534d.size() - 1; size2 >= 0; size2--) {
                        WeakReference weakReference2 = (WeakReference) f2534d.get(size2);
                        y0 y0Var = weakReference2 != null ? (y0) weakReference2.get() : null;
                        if (y0Var != null && y0Var.getBaseContext() == context) {
                            return y0Var;
                        }
                    }
                }
                y0 y0Var2 = new y0(context);
                f2534d.add(new WeakReference(y0Var2));
                return y0Var2;
            }
        }
        return context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f2535a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f2535a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f2536b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i8) {
        Resources.Theme theme = this.f2536b;
        if (theme == null) {
            super.setTheme(i8);
        } else {
            theme.applyStyle(i8, true);
        }
    }
}
