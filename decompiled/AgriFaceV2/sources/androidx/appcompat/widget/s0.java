package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public final class s0 {

    /* renamed from: i  reason: collision with root package name */
    private static s0 f2475i;

    /* renamed from: a  reason: collision with root package name */
    private WeakHashMap f2477a;

    /* renamed from: b  reason: collision with root package name */
    private b1.g f2478b;

    /* renamed from: c  reason: collision with root package name */
    private b1.h f2479c;

    /* renamed from: d  reason: collision with root package name */
    private final WeakHashMap f2480d = new WeakHashMap(0);

    /* renamed from: e  reason: collision with root package name */
    private TypedValue f2481e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f2482f;

    /* renamed from: g  reason: collision with root package name */
    private c f2483g;

    /* renamed from: h  reason: collision with root package name */
    private static final PorterDuff.Mode f2474h = PorterDuff.Mode.SRC_IN;

    /* renamed from: j  reason: collision with root package name */
    private static final a f2476j = new a(6);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a extends b1.e {
        public a(int i8) {
            super(i8);
        }

        private static int h(int i8, PorterDuff.Mode mode) {
            return ((i8 + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter i(int i8, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) c(Integer.valueOf(h(i8, mode)));
        }

        PorterDuffColorFilter j(int i8, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) d(Integer.valueOf(h(i8, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface b {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* loaded from: classes.dex */
    public interface c {
        boolean a(Context context, int i8, Drawable drawable);

        PorterDuff.Mode b(int i8);

        Drawable c(s0 s0Var, Context context, int i8);

        ColorStateList d(Context context, int i8);

        boolean e(Context context, int i8, Drawable drawable);
    }

    private synchronized boolean a(Context context, long j8, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            b1.d dVar = (b1.d) this.f2480d.get(context);
            if (dVar == null) {
                dVar = new b1.d();
                this.f2480d.put(context, dVar);
            }
            dVar.i(j8, new WeakReference(constantState));
            return true;
        }
        return false;
    }

    private void b(Context context, int i8, ColorStateList colorStateList) {
        if (this.f2477a == null) {
            this.f2477a = new WeakHashMap();
        }
        b1.h hVar = (b1.h) this.f2477a.get(context);
        if (hVar == null) {
            hVar = new b1.h();
            this.f2477a.put(context, hVar);
        }
        hVar.a(i8, colorStateList);
    }

    private void c(Context context) {
        if (this.f2482f) {
            return;
        }
        this.f2482f = true;
        Drawable i8 = i(context, j.b.f8180a);
        if (i8 == null || !p(i8)) {
            this.f2482f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private static long d(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    private Drawable e(Context context, int i8) {
        if (this.f2481e == null) {
            this.f2481e = new TypedValue();
        }
        TypedValue typedValue = this.f2481e;
        context.getResources().getValue(i8, typedValue, true);
        long d8 = d(typedValue);
        Drawable h8 = h(context, d8);
        if (h8 != null) {
            return h8;
        }
        c cVar = this.f2483g;
        Drawable c8 = cVar == null ? null : cVar.c(this, context, i8);
        if (c8 != null) {
            c8.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, d8, c8);
        }
        return c8;
    }

    private static PorterDuffColorFilter f(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return k(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized s0 g() {
        s0 s0Var;
        synchronized (s0.class) {
            if (f2475i == null) {
                s0 s0Var2 = new s0();
                f2475i = s0Var2;
                o(s0Var2);
            }
            s0Var = f2475i;
        }
        return s0Var;
    }

    private synchronized Drawable h(Context context, long j8) {
        b1.d dVar = (b1.d) this.f2480d.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) dVar.e(j8);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.j(j8);
        }
        return null;
    }

    public static synchronized PorterDuffColorFilter k(int i8, PorterDuff.Mode mode) {
        PorterDuffColorFilter i9;
        synchronized (s0.class) {
            a aVar = f2476j;
            i9 = aVar.i(i8, mode);
            if (i9 == null) {
                i9 = new PorterDuffColorFilter(i8, mode);
                aVar.j(i8, mode, i9);
            }
        }
        return i9;
    }

    private ColorStateList m(Context context, int i8) {
        b1.h hVar;
        WeakHashMap weakHashMap = this.f2477a;
        if (weakHashMap == null || (hVar = (b1.h) weakHashMap.get(context)) == null) {
            return null;
        }
        return (ColorStateList) hVar.g(i8);
    }

    private static void o(s0 s0Var) {
    }

    private static boolean p(Drawable drawable) {
        return (drawable instanceof a2.b) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable q(Context context, int i8) {
        int next;
        b1.g gVar = this.f2478b;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        b1.h hVar = this.f2479c;
        if (hVar != null) {
            String str = (String) hVar.g(i8);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.f2478b.get(str) == null)) {
                return null;
            }
        } else {
            this.f2479c = new b1.h();
        }
        if (this.f2481e == null) {
            this.f2481e = new TypedValue();
        }
        TypedValue typedValue = this.f2481e;
        Resources resources = context.getResources();
        resources.getValue(i8, typedValue, true);
        long d8 = d(typedValue);
        Drawable h8 = h(context, d8);
        if (h8 != null) {
            return h8;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i8);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                while (true) {
                    next = xml.next();
                    if (next == 2 || next == 1) {
                        break;
                    }
                }
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.f2479c.a(i8, name);
                b bVar = (b) this.f2478b.get(name);
                if (bVar != null) {
                    h8 = bVar.a(context, xml, asAttributeSet, context.getTheme());
                }
                if (h8 != null) {
                    h8.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, d8, h8);
                }
            } catch (Exception e8) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e8);
            }
        }
        if (h8 == null) {
            this.f2479c.a(i8, "appcompat_skip_skip");
        }
        return h8;
    }

    private Drawable u(Context context, int i8, boolean z7, Drawable drawable) {
        ColorStateList l8 = l(context, i8);
        if (l8 == null) {
            c cVar = this.f2483g;
            if ((cVar == null || !cVar.e(context, i8, drawable)) && !w(context, i8, drawable) && z7) {
                return null;
            }
            return drawable;
        }
        if (k0.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable i9 = androidx.core.graphics.drawable.a.i(drawable);
        androidx.core.graphics.drawable.a.g(i9, l8);
        PorterDuff.Mode n8 = n(i8);
        if (n8 != null) {
            androidx.core.graphics.drawable.a.h(i9, n8);
            return i9;
        }
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void v(Drawable drawable, z0 z0Var, int[] iArr) {
        int[] state = drawable.getState();
        if (k0.a(drawable)) {
            if (!(drawable.mutate() == drawable)) {
                Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
                return;
            }
        }
        if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
            drawable.setState(new int[0]);
            drawable.setState(state);
        }
        boolean z7 = z0Var.f2546d;
        if (z7 || z0Var.f2545c) {
            drawable.setColorFilter(f(z7 ? z0Var.f2543a : null, z0Var.f2545c ? z0Var.f2544b : f2474h, iArr));
        } else {
            drawable.clearColorFilter();
        }
    }

    public synchronized Drawable i(Context context, int i8) {
        return j(context, i8, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable j(Context context, int i8, boolean z7) {
        Drawable q8;
        c(context);
        q8 = q(context, i8);
        if (q8 == null) {
            q8 = e(context, i8);
        }
        if (q8 == null) {
            q8 = androidx.core.content.a.d(context, i8);
        }
        if (q8 != null) {
            q8 = u(context, i8, z7, q8);
        }
        if (q8 != null) {
            k0.b(q8);
        }
        return q8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ColorStateList l(Context context, int i8) {
        ColorStateList m8;
        m8 = m(context, i8);
        if (m8 == null) {
            c cVar = this.f2483g;
            m8 = cVar == null ? null : cVar.d(context, i8);
            if (m8 != null) {
                b(context, i8, m8);
            }
        }
        return m8;
    }

    PorterDuff.Mode n(int i8) {
        c cVar = this.f2483g;
        if (cVar == null) {
            return null;
        }
        return cVar.b(i8);
    }

    public synchronized void r(Context context) {
        b1.d dVar = (b1.d) this.f2480d.get(context);
        if (dVar != null) {
            dVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable s(Context context, h1 h1Var, int i8) {
        Drawable q8 = q(context, i8);
        if (q8 == null) {
            q8 = h1Var.a(i8);
        }
        if (q8 != null) {
            return u(context, i8, false, q8);
        }
        return null;
    }

    public synchronized void t(c cVar) {
        this.f2483g = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean w(Context context, int i8, Drawable drawable) {
        c cVar = this.f2483g;
        return cVar != null && cVar.a(context, i8, drawable);
    }
}
