package androidx.appcompat.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.d0;
import androidx.appcompat.widget.h0;
import androidx.appcompat.widget.y0;
import androidx.core.view.p0;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class q {

    /* renamed from: b  reason: collision with root package name */
    private static final Class[] f1668b = {Context.class, AttributeSet.class};

    /* renamed from: c  reason: collision with root package name */
    private static final int[] f1669c = {16843375};

    /* renamed from: d  reason: collision with root package name */
    private static final int[] f1670d = {16844160};

    /* renamed from: e  reason: collision with root package name */
    private static final int[] f1671e = {16844156};

    /* renamed from: f  reason: collision with root package name */
    private static final int[] f1672f = {16844148};

    /* renamed from: g  reason: collision with root package name */
    private static final String[] f1673g = {"android.widget.", "android.view.", "android.webkit."};

    /* renamed from: h  reason: collision with root package name */
    private static final b1.g f1674h = new b1.g();

    /* renamed from: a  reason: collision with root package name */
    private final Object[] f1675a = new Object[2];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        private final View f1676a;

        /* renamed from: b  reason: collision with root package name */
        private final String f1677b;

        /* renamed from: c  reason: collision with root package name */
        private Method f1678c;

        /* renamed from: d  reason: collision with root package name */
        private Context f1679d;

        public a(View view, String str) {
            this.f1676a = view;
            this.f1677b = str;
        }

        private void a(Context context) {
            int id;
            String str;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.f1677b, View.class)) != null) {
                        this.f1678c = method;
                        this.f1679d = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            if (this.f1676a.getId() == -1) {
                str = "";
            } else {
                str = " with id '" + this.f1676a.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.f1677b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f1676a.getClass() + str);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f1678c == null) {
                a(this.f1676a.getContext());
            }
            try {
                this.f1678c.invoke(this.f1679d, view);
            } catch (IllegalAccessException e8) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e8);
            } catch (InvocationTargetException e9) {
                throw new IllegalStateException("Could not execute method for android:onClick", e9);
            }
        }
    }

    private void a(Context context, View view, AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT > 28) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1670d);
        if (obtainStyledAttributes.hasValue(0)) {
            p0.O(view, obtainStyledAttributes.getBoolean(0, false));
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, f1671e);
        if (obtainStyledAttributes2.hasValue(0)) {
            p0.P(view, obtainStyledAttributes2.getString(0));
        }
        obtainStyledAttributes2.recycle();
        TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, f1672f);
        if (obtainStyledAttributes3.hasValue(0)) {
            p0.W(view, obtainStyledAttributes3.getBoolean(0, false));
        }
        obtainStyledAttributes3.recycle();
    }

    private void b(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if ((context instanceof ContextWrapper) && p0.w(view)) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1669c);
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new a(view, string));
            }
            obtainStyledAttributes.recycle();
        }
    }

    private View s(Context context, String str, String str2) {
        String str3;
        b1.g gVar = f1674h;
        Constructor constructor = (Constructor) gVar.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(f1668b);
            gVar.put(str, constructor);
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.f1675a);
    }

    private View t(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            Object[] objArr = this.f1675a;
            objArr[0] = context;
            objArr[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return s(context, str, null);
            }
            int i8 = 0;
            while (true) {
                String[] strArr = f1673g;
                if (i8 >= strArr.length) {
                    return null;
                }
                View s8 = s(context, str, strArr[i8]);
                if (s8 != null) {
                    return s8;
                }
                i8++;
            }
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr2 = this.f1675a;
            objArr2[0] = null;
            objArr2[1] = null;
        }
    }

    private static Context u(Context context, AttributeSet attributeSet, boolean z7, boolean z8) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.j.f7017s3, 0, 0);
        int resourceId = z7 ? obtainStyledAttributes.getResourceId(g.j.f7022t3, 0) : 0;
        if (z8 && resourceId == 0 && (resourceId = obtainStyledAttributes.getResourceId(g.j.f7027u3, 0)) != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        obtainStyledAttributes.recycle();
        return resourceId != 0 ? ((context instanceof androidx.appcompat.view.d) && ((androidx.appcompat.view.d) context).c() == resourceId) ? context : new androidx.appcompat.view.d(context, resourceId) : context;
    }

    private void v(View view, String str) {
        if (view != null) {
            return;
        }
        throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
    }

    protected androidx.appcompat.widget.d c(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.d(context, attributeSet);
    }

    protected androidx.appcompat.widget.f d(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.f(context, attributeSet);
    }

    protected androidx.appcompat.widget.g e(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.g(context, attributeSet);
    }

    protected androidx.appcompat.widget.h f(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.h(context, attributeSet);
    }

    protected androidx.appcompat.widget.l g(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.l(context, attributeSet);
    }

    protected androidx.appcompat.widget.p h(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.p(context, attributeSet);
    }

    protected androidx.appcompat.widget.r i(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.r(context, attributeSet);
    }

    protected androidx.appcompat.widget.s j(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.s(context, attributeSet);
    }

    protected androidx.appcompat.widget.v k(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.v(context, attributeSet);
    }

    protected androidx.appcompat.widget.w l(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.w(context, attributeSet);
    }

    protected androidx.appcompat.widget.y m(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.y(context, attributeSet);
    }

    protected androidx.appcompat.widget.a0 n(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.a0(context, attributeSet);
    }

    protected d0 o(Context context, AttributeSet attributeSet) {
        return new d0(context, attributeSet);
    }

    protected h0 p(Context context, AttributeSet attributeSet) {
        return new h0(context, attributeSet);
    }

    protected View q(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    public final View r(View view, String str, Context context, AttributeSet attributeSet, boolean z7, boolean z8, boolean z9, boolean z10) {
        View l8;
        Context context2 = (!z7 || view == null) ? context : view.getContext();
        if (z8 || z9) {
            context2 = u(context2, attributeSet, z8, z9);
        }
        if (z10) {
            context2 = y0.b(context2);
        }
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    c8 = 0;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    c8 = 1;
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    c8 = 2;
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c8 = 3;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    c8 = 4;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    c8 = 5;
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    c8 = 6;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    c8 = 7;
                    break;
                }
                break;
            case 799298502:
                if (str.equals("ToggleButton")) {
                    c8 = '\b';
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c8 = '\t';
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    c8 = '\n';
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    c8 = 11;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c8 = '\f';
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    c8 = '\r';
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                l8 = l(context2, attributeSet);
                v(l8, str);
                break;
            case 1:
                l8 = f(context2, attributeSet);
                v(l8, str);
                break;
            case 2:
                l8 = j(context2, attributeSet);
                v(l8, str);
                break;
            case 3:
                l8 = o(context2, attributeSet);
                v(l8, str);
                break;
            case 4:
                l8 = h(context2, attributeSet);
                v(l8, str);
                break;
            case 5:
                l8 = m(context2, attributeSet);
                v(l8, str);
                break;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                l8 = n(context2, attributeSet);
                v(l8, str);
                break;
            case 7:
                l8 = k(context2, attributeSet);
                v(l8, str);
                break;
            case '\b':
                l8 = p(context2, attributeSet);
                v(l8, str);
                break;
            case '\t':
                l8 = i(context2, attributeSet);
                v(l8, str);
                break;
            case '\n':
                l8 = c(context2, attributeSet);
                v(l8, str);
                break;
            case 11:
                l8 = e(context2, attributeSet);
                v(l8, str);
                break;
            case '\f':
                l8 = g(context2, attributeSet);
                v(l8, str);
                break;
            case '\r':
                l8 = d(context2, attributeSet);
                v(l8, str);
                break;
            default:
                l8 = q(context2, str, attributeSet);
                break;
        }
        if (l8 == null && context != context2) {
            l8 = t(context2, str, attributeSet);
        }
        if (l8 != null) {
            b(l8, attributeSet);
            a(context2, l8, attributeSet);
        }
        return l8;
    }
}
