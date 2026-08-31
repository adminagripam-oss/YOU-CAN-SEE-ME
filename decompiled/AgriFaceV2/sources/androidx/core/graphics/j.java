package androidx.core.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.content.res.d;
import f1.g;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;
/* loaded from: classes.dex */
public class j extends i {

    /* renamed from: g  reason: collision with root package name */
    protected final Class f3002g;

    /* renamed from: h  reason: collision with root package name */
    protected final Constructor f3003h;

    /* renamed from: i  reason: collision with root package name */
    protected final Method f3004i;

    /* renamed from: j  reason: collision with root package name */
    protected final Method f3005j;

    /* renamed from: k  reason: collision with root package name */
    protected final Method f3006k;

    /* renamed from: l  reason: collision with root package name */
    protected final Method f3007l;

    /* renamed from: m  reason: collision with root package name */
    protected final Method f3008m;

    public j() {
        Class cls;
        Constructor constructor;
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        try {
            cls = t();
            constructor = u(cls);
            method = q(cls);
            method2 = r(cls);
            method3 = v(cls);
            method4 = p(cls);
            method5 = s(cls);
        } catch (ClassNotFoundException | NoSuchMethodException e8) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e8.getClass().getName(), e8);
            cls = null;
            constructor = null;
            method = null;
            method2 = null;
            method3 = null;
            method4 = null;
            method5 = null;
        }
        this.f3002g = cls;
        this.f3003h = constructor;
        this.f3004i = method;
        this.f3005j = method2;
        this.f3006k = method3;
        this.f3007l = method4;
        this.f3008m = method5;
    }

    private Object j() {
        try {
            return this.f3003h.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    private void k(Object obj) {
        try {
            this.f3007l.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    private boolean l(Context context, Object obj, String str, int i8, int i9, int i10, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f3004i.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(i10), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean m(Object obj, ByteBuffer byteBuffer, int i8, int i9, int i10) {
        try {
            return ((Boolean) this.f3005j.invoke(obj, byteBuffer, Integer.valueOf(i8), null, Integer.valueOf(i9), Integer.valueOf(i10))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean n(Object obj) {
        try {
            return ((Boolean) this.f3006k.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean o() {
        if (this.f3004i == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.f3004i != null;
    }

    @Override // androidx.core.graphics.i, androidx.core.graphics.m
    public Typeface a(Context context, d.c cVar, Resources resources, int i8) {
        d.C0023d[] a8;
        if (o()) {
            Object j8 = j();
            if (j8 == null) {
                return null;
            }
            for (d.C0023d c0023d : cVar.a()) {
                if (!l(context, j8, c0023d.a(), c0023d.c(), c0023d.e(), c0023d.f() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(c0023d.d()))) {
                    k(j8);
                    return null;
                }
            }
            if (n(j8)) {
                return h(j8);
            }
            return null;
        }
        return super.a(context, cVar, resources, i8);
    }

    @Override // androidx.core.graphics.m
    public Typeface b(Context context, CancellationSignal cancellationSignal, g.b[] bVarArr, int i8) {
        Typeface h8;
        if (bVarArr.length < 1) {
            return null;
        }
        if (!o()) {
            g.b f8 = f(bVarArr, i8);
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(f8.d(), "r", cancellationSignal);
                if (openFileDescriptor == null) {
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return null;
                }
                Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(f8.e()).setItalic(f8.f()).build();
                openFileDescriptor.close();
                return build;
            } catch (IOException unused) {
                return null;
            }
        }
        Map f9 = n.f(context, bVarArr, cancellationSignal);
        Object j8 = j();
        if (j8 == null) {
            return null;
        }
        boolean z7 = false;
        for (g.b bVar : bVarArr) {
            ByteBuffer byteBuffer = (ByteBuffer) f9.get(bVar.d());
            if (byteBuffer != null) {
                if (!m(j8, byteBuffer, bVar.c(), bVar.e(), bVar.f() ? 1 : 0)) {
                    k(j8);
                    return null;
                }
                z7 = true;
            }
        }
        if (!z7) {
            k(j8);
            return null;
        } else if (n(j8) && (h8 = h(j8)) != null) {
            return Typeface.create(h8, i8);
        } else {
            return null;
        }
    }

    @Override // androidx.core.graphics.m
    public Typeface c(Context context, Resources resources, int i8, String str, int i9) {
        if (o()) {
            Object j8 = j();
            if (j8 == null) {
                return null;
            }
            if (!l(context, j8, str, 0, -1, -1, null)) {
                k(j8);
                return null;
            } else if (n(j8)) {
                return h(j8);
            } else {
                return null;
            }
        }
        return super.c(context, resources, i8, str, i9);
    }

    protected Typeface h(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.f3002g, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.f3008m.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    protected Method p(Class cls) {
        return cls.getMethod("abortCreation", new Class[0]);
    }

    protected Method q(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    protected Method r(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    protected Method s(Class cls) {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    protected Class t() {
        return Class.forName("android.graphics.FontFamily");
    }

    protected Constructor u(Class cls) {
        return cls.getConstructor(new Class[0]);
    }

    protected Method v(Class cls) {
        return cls.getMethod("freeze", new Class[0]);
    }
}
