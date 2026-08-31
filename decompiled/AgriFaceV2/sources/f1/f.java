package f1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.face.ModuleDescriptor;
import f1.g;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    static final b1.e f6693a = new b1.e(16);

    /* renamed from: b  reason: collision with root package name */
    private static final ExecutorService f6694b = h.a("fonts-androidx", 10, ModuleDescriptor.MODULE_VERSION);

    /* renamed from: c  reason: collision with root package name */
    static final Object f6695c = new Object();

    /* renamed from: d  reason: collision with root package name */
    static final b1.g f6696d = new b1.g();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Callable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f6697a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Context f6698b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ f1.e f6699c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ int f6700d;

        a(String str, Context context, f1.e eVar, int i8) {
            this.f6697a = str;
            this.f6698b = context;
            this.f6699c = eVar;
            this.f6700d = i8;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public e call() {
            return f.c(this.f6697a, this.f6698b, this.f6699c, this.f6700d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements g1.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ f1.a f6701a;

        b(f1.a aVar) {
            this.f6701a = aVar;
        }

        @Override // g1.a
        /* renamed from: a */
        public void accept(e eVar) {
            if (eVar == null) {
                eVar = new e(-3);
            }
            this.f6701a.b(eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Callable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f6702a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Context f6703b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ f1.e f6704c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ int f6705d;

        c(String str, Context context, f1.e eVar, int i8) {
            this.f6702a = str;
            this.f6703b = context;
            this.f6704c = eVar;
            this.f6705d = i8;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public e call() {
            try {
                return f.c(this.f6702a, this.f6703b, this.f6704c, this.f6705d);
            } catch (Throwable unused) {
                return new e(-3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements g1.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f6706a;

        d(String str) {
            this.f6706a = str;
        }

        @Override // g1.a
        /* renamed from: a */
        public void accept(e eVar) {
            synchronized (f.f6695c) {
                b1.g gVar = f.f6696d;
                ArrayList arrayList = (ArrayList) gVar.get(this.f6706a);
                if (arrayList == null) {
                    return;
                }
                gVar.remove(this.f6706a);
                for (int i8 = 0; i8 < arrayList.size(); i8++) {
                    ((g1.a) arrayList.get(i8)).accept(eVar);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        final Typeface f6707a;

        /* renamed from: b  reason: collision with root package name */
        final int f6708b;

        e(int i8) {
            this.f6707a = null;
            this.f6708b = i8;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a() {
            return this.f6708b == 0;
        }

        e(Typeface typeface) {
            this.f6707a = typeface;
            this.f6708b = 0;
        }
    }

    private static String a(f1.e eVar, int i8) {
        return eVar.d() + "-" + i8;
    }

    private static int b(g.a aVar) {
        int i8 = 1;
        if (aVar.c() != 0) {
            return aVar.c() != 1 ? -3 : -2;
        }
        g.b[] b8 = aVar.b();
        if (b8 != null && b8.length != 0) {
            i8 = 0;
            for (g.b bVar : b8) {
                int b9 = bVar.b();
                if (b9 != 0) {
                    if (b9 < 0) {
                        return -3;
                    }
                    return b9;
                }
            }
        }
        return i8;
    }

    static e c(String str, Context context, f1.e eVar, int i8) {
        b1.e eVar2 = f6693a;
        Typeface typeface = (Typeface) eVar2.c(str);
        if (typeface != null) {
            return new e(typeface);
        }
        try {
            g.a e8 = f1.d.e(context, eVar, null);
            int b8 = b(e8);
            if (b8 != 0) {
                return new e(b8);
            }
            Typeface b9 = androidx.core.graphics.h.b(context, null, e8.b(), i8);
            if (b9 != null) {
                eVar2.d(str, b9);
                return new e(b9);
            }
            return new e(-3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new e(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface d(Context context, f1.e eVar, int i8, Executor executor, f1.a aVar) {
        String a8 = a(eVar, i8);
        Typeface typeface = (Typeface) f6693a.c(a8);
        if (typeface != null) {
            aVar.b(new e(typeface));
            return typeface;
        }
        b bVar = new b(aVar);
        synchronized (f6695c) {
            b1.g gVar = f6696d;
            ArrayList arrayList = (ArrayList) gVar.get(a8);
            if (arrayList != null) {
                arrayList.add(bVar);
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(bVar);
            gVar.put(a8, arrayList2);
            c cVar = new c(a8, context, eVar, i8);
            if (executor == null) {
                executor = f6694b;
            }
            h.b(executor, cVar, new d(a8));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface e(Context context, f1.e eVar, f1.a aVar, int i8, int i9) {
        String a8 = a(eVar, i8);
        Typeface typeface = (Typeface) f6693a.c(a8);
        if (typeface != null) {
            aVar.b(new e(typeface));
            return typeface;
        } else if (i9 == -1) {
            e c8 = c(a8, context, eVar, i8);
            aVar.b(c8);
            return c8.f6707a;
        } else {
            try {
                e eVar2 = (e) h.c(f6694b, new a(a8, context, eVar, i8), i9);
                aVar.b(eVar2);
                return eVar2.f6707a;
            } catch (InterruptedException unused) {
                aVar.b(new e(-3));
                return null;
            }
        }
    }
}
