package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import androidx.core.content.res.d;
import f1.g;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class m {

    /* renamed from: a  reason: collision with root package name */
    private ConcurrentHashMap f3009a = new ConcurrentHashMap();

    /* loaded from: classes.dex */
    class a implements b {
        a() {
        }

        @Override // androidx.core.graphics.m.b
        /* renamed from: c */
        public int a(g.b bVar) {
            return bVar.e();
        }

        @Override // androidx.core.graphics.m.b
        /* renamed from: d */
        public boolean b(g.b bVar) {
            return bVar.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface b {
        int a(Object obj);

        boolean b(Object obj);
    }

    private static Object d(Object[] objArr, int i8, b bVar) {
        return e(objArr, (i8 & 1) == 0 ? 400 : 700, (i8 & 2) != 0, bVar);
    }

    private static Object e(Object[] objArr, int i8, boolean z7, b bVar) {
        Object obj = null;
        int i9 = Integer.MAX_VALUE;
        for (Object obj2 : objArr) {
            int abs = (Math.abs(bVar.a(obj2) - i8) * 2) + (bVar.b(obj2) == z7 ? 0 : 1);
            if (obj == null || i9 > abs) {
                obj = obj2;
                i9 = abs;
            }
        }
        return obj;
    }

    public abstract Typeface a(Context context, d.c cVar, Resources resources, int i8);

    public abstract Typeface b(Context context, CancellationSignal cancellationSignal, g.b[] bVarArr, int i8);

    public Typeface c(Context context, Resources resources, int i8, String str, int i9) {
        File d8 = n.d(context);
        if (d8 == null) {
            return null;
        }
        try {
            if (n.b(d8, resources, i8)) {
                return Typeface.createFromFile(d8.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            d8.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g.b f(g.b[] bVarArr, int i8) {
        return (g.b) d(bVarArr, i8, new a());
    }
}
