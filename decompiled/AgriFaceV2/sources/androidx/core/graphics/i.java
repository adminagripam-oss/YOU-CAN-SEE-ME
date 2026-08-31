package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import androidx.core.content.res.d;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
abstract class i extends m {

    /* renamed from: b  reason: collision with root package name */
    private static Class f2997b = null;

    /* renamed from: c  reason: collision with root package name */
    private static Constructor f2998c = null;

    /* renamed from: d  reason: collision with root package name */
    private static Method f2999d = null;

    /* renamed from: e  reason: collision with root package name */
    private static Method f3000e = null;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f3001f = false;

    private static boolean g(Object obj, String str, int i8, boolean z7) {
        i();
        try {
            return ((Boolean) f2999d.invoke(obj, str, Integer.valueOf(i8), Boolean.valueOf(z7))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e8) {
            throw new RuntimeException(e8);
        }
    }

    private static Typeface h(Object obj) {
        i();
        try {
            Object newInstance = Array.newInstance(f2997b, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) f3000e.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException e8) {
            throw new RuntimeException(e8);
        }
    }

    private static void i() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        if (f3001f) {
            return;
        }
        f3001f = true;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e8) {
            Log.e("TypefaceCompatApi21Impl", e8.getClass().getName(), e8);
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
        }
        f2998c = constructor;
        f2997b = cls;
        f2999d = method2;
        f3000e = method;
    }

    private static Object j() {
        i();
        try {
            return f2998c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e8) {
            throw new RuntimeException(e8);
        }
    }

    @Override // androidx.core.graphics.m
    public Typeface a(Context context, d.c cVar, Resources resources, int i8) {
        d.C0023d[] a8;
        Object j8 = j();
        for (d.C0023d c0023d : cVar.a()) {
            File d8 = n.d(context);
            if (d8 == null) {
                return null;
            }
            try {
                if (!n.b(d8, resources, c0023d.b())) {
                    return null;
                }
                if (!g(j8, d8.getPath(), c0023d.e(), c0023d.f())) {
                    return null;
                }
            } catch (RuntimeException unused) {
                return null;
            } finally {
                d8.delete();
            }
        }
        return h(j8);
    }
}
