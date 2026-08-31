package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.core.content.res.d;
import androidx.core.content.res.f;
import f1.g;
/* loaded from: classes.dex */
public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    private static final m f2994a;

    /* renamed from: b  reason: collision with root package name */
    private static final b1.e f2995b;

    /* loaded from: classes.dex */
    public static class a extends g.c {

        /* renamed from: a  reason: collision with root package name */
        private f.e f2996a;

        public a(f.e eVar) {
            this.f2996a = eVar;
        }

        @Override // f1.g.c
        public void a(int i8) {
            f.e eVar = this.f2996a;
            if (eVar != null) {
                eVar.f(i8);
            }
        }

        @Override // f1.g.c
        public void b(Typeface typeface) {
            f.e eVar = this.f2996a;
            if (eVar != null) {
                eVar.g(typeface);
            }
        }
    }

    static {
        int i8 = Build.VERSION.SDK_INT;
        f2994a = i8 >= 29 ? new l() : i8 >= 28 ? new k() : new j();
        f2995b = new b1.e(16);
    }

    public static Typeface a(Context context, Typeface typeface, int i8) {
        if (context != null) {
            return Typeface.create(typeface, i8);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, g.b[] bVarArr, int i8) {
        return f2994a.b(context, cancellationSignal, bVarArr, i8);
    }

    public static Typeface c(Context context, d.b bVar, Resources resources, int i8, String str, int i9, int i10, f.e eVar, Handler handler, boolean z7) {
        Typeface a8;
        if (bVar instanceof d.e) {
            d.e eVar2 = (d.e) bVar;
            Typeface g8 = g(eVar2.c());
            if (g8 != null) {
                if (eVar != null) {
                    eVar.d(g8, handler);
                }
                return g8;
            }
            boolean z8 = !z7 ? eVar != null : eVar2.a() != 0;
            int d8 = z7 ? eVar2.d() : -1;
            a8 = f1.g.c(context, eVar2.b(), i10, z8, d8, f.e.e(handler), new a(eVar));
        } else {
            a8 = f2994a.a(context, (d.c) bVar, resources, i10);
            if (eVar != null) {
                if (a8 != null) {
                    eVar.d(a8, handler);
                } else {
                    eVar.c(-3, handler);
                }
            }
        }
        if (a8 != null) {
            f2995b.d(e(resources, i8, str, i9, i10), a8);
        }
        return a8;
    }

    public static Typeface d(Context context, Resources resources, int i8, String str, int i9, int i10) {
        Typeface c8 = f2994a.c(context, resources, i8, str, i10);
        if (c8 != null) {
            f2995b.d(e(resources, i8, str, i9, i10), c8);
        }
        return c8;
    }

    private static String e(Resources resources, int i8, String str, int i9, int i10) {
        return resources.getResourcePackageName(i8) + '-' + str + '-' + i9 + '-' + i8 + '-' + i10;
    }

    public static Typeface f(Resources resources, int i8, String str, int i9, int i10) {
        return (Typeface) f2995b.c(e(resources, i8, str, i9, i10));
    }

    private static Typeface g(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Typeface create = Typeface.create(str, 0);
        Typeface create2 = Typeface.create(Typeface.DEFAULT, 0);
        if (create == null || create.equals(create2)) {
            return null;
        }
        return create;
    }
}
