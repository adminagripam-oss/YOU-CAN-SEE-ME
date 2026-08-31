package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import k3.p;
import k3.q;
/* loaded from: classes.dex */
public final class DynamiteModule {

    /* renamed from: h  reason: collision with root package name */
    private static Boolean f5095h = null;

    /* renamed from: i  reason: collision with root package name */
    private static String f5096i = null;

    /* renamed from: j  reason: collision with root package name */
    private static boolean f5097j = false;

    /* renamed from: k  reason: collision with root package name */
    private static int f5098k = -1;

    /* renamed from: l  reason: collision with root package name */
    private static Boolean f5099l;

    /* renamed from: q  reason: collision with root package name */
    private static m f5104q;

    /* renamed from: r  reason: collision with root package name */
    private static n f5105r;

    /* renamed from: a  reason: collision with root package name */
    private final Context f5106a;

    /* renamed from: m  reason: collision with root package name */
    private static final ThreadLocal f5100m = new ThreadLocal();

    /* renamed from: n  reason: collision with root package name */
    private static final ThreadLocal f5101n = new com.google.android.gms.dynamite.b();

    /* renamed from: o  reason: collision with root package name */
    private static final b.a f5102o = new c();

    /* renamed from: b  reason: collision with root package name */
    public static final b f5089b = new d();

    /* renamed from: c  reason: collision with root package name */
    public static final b f5090c = new e();

    /* renamed from: d  reason: collision with root package name */
    public static final b f5091d = new f();

    /* renamed from: e  reason: collision with root package name */
    public static final b f5092e = new g();

    /* renamed from: f  reason: collision with root package name */
    public static final b f5093f = new h();

    /* renamed from: g  reason: collision with root package name */
    public static final b f5094g = new i();

    /* renamed from: p  reason: collision with root package name */
    public static final b f5103p = new j();

    /* loaded from: classes.dex */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* loaded from: classes.dex */
    public static class a extends Exception {
        /* synthetic */ a(String str, Throwable th, u3.f fVar) {
            super(str, th);
        }

        /* synthetic */ a(String str, u3.f fVar) {
            super(str);
        }
    }

    /* loaded from: classes.dex */
    public interface b {

        /* loaded from: classes.dex */
        public interface a {
            int a(Context context, String str);

            int b(Context context, String str, boolean z7);
        }

        /* renamed from: com.google.android.gms.dynamite.DynamiteModule$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0065b {

            /* renamed from: a  reason: collision with root package name */
            public int f5107a = 0;

            /* renamed from: b  reason: collision with root package name */
            public int f5108b = 0;

            /* renamed from: c  reason: collision with root package name */
            public int f5109c = 0;
        }

        C0065b a(Context context, String str, a aVar);
    }

    private DynamiteModule(Context context) {
        q.g(context);
        this.f5106a = context;
    }

    public static int a(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            Class<?> loadClass = classLoader.loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (p.a(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            String valueOf = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", "Module descriptor id '" + valueOf + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e8) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e8.getMessage())));
            return 0;
        }
    }

    public static int b(Context context, String str) {
        return e(context, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x024c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.dynamite.DynamiteModule d(android.content.Context r17, com.google.android.gms.dynamite.DynamiteModule.b r18, java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 723
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.d(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$b, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x01b0 -> B:131:0x01b5). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:110:0x01b2 -> B:131:0x01b5). Please submit an issue!!! */
    public static int e(Context context, String str, boolean z7) {
        Field declaredField;
        Throwable th;
        RemoteException e8;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = f5095h;
                Cursor cursor2 = null;
                int i8 = 0;
                if (bool == null) {
                    try {
                        declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e9) {
                        String obj = e9.toString();
                        Log.w("DynamiteModule", "Failed to load module via V2: " + obj);
                        bool = Boolean.FALSE;
                    }
                    synchronized (declaredField.getDeclaringClass()) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader != ClassLoader.getSystemClassLoader()) {
                            if (classLoader != null) {
                                try {
                                    h(classLoader);
                                } catch (a unused) {
                                }
                                bool = Boolean.TRUE;
                                f5095h = bool;
                            } else if (!j(context)) {
                                return 0;
                            } else {
                                if (!f5097j) {
                                    Boolean bool2 = Boolean.TRUE;
                                    if (!bool2.equals(null)) {
                                        try {
                                            int f8 = f(context, str, z7, true);
                                            String str2 = f5096i;
                                            if (str2 != null && !str2.isEmpty()) {
                                                ClassLoader a8 = u3.d.a();
                                                if (a8 == null) {
                                                    if (Build.VERSION.SDK_INT >= 29) {
                                                        u3.b.a();
                                                        String str3 = f5096i;
                                                        q.g(str3);
                                                        a8 = u3.a.a(str3, ClassLoader.getSystemClassLoader());
                                                    } else {
                                                        String str4 = f5096i;
                                                        q.g(str4);
                                                        a8 = new com.google.android.gms.dynamite.a(str4, ClassLoader.getSystemClassLoader());
                                                    }
                                                }
                                                h(a8);
                                                declaredField.set(null, a8);
                                                f5095h = bool2;
                                                return f8;
                                            }
                                            return f8;
                                        } catch (a unused2) {
                                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                                        }
                                    }
                                }
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                            }
                        }
                        bool = Boolean.FALSE;
                        f5095h = bool;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return f(context, str, z7, false);
                    } catch (a e10) {
                        String message = e10.getMessage();
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + message);
                        return 0;
                    }
                }
                m k8 = k(context);
                try {
                    if (k8 != null) {
                        try {
                            int f9 = k8.f();
                            if (f9 >= 3) {
                                k kVar = (k) f5100m.get();
                                if (kVar == null || (cursor = kVar.f5110a) == null) {
                                    Cursor cursor3 = (Cursor) t3.b.f(k8.i0(t3.b.d0(context), str, z7, ((Long) f5101n.get()).longValue()));
                                    if (cursor3 != null) {
                                        try {
                                            if (cursor3.moveToFirst()) {
                                                int i9 = cursor3.getInt(0);
                                                if (i9 <= 0 || !i(cursor3)) {
                                                    cursor2 = cursor3;
                                                }
                                                if (cursor2 != null) {
                                                    cursor2.close();
                                                }
                                                i8 = i9;
                                            }
                                        } catch (RemoteException e11) {
                                            e8 = e11;
                                            cursor2 = cursor3;
                                            String message2 = e8.getMessage();
                                            Log.w("DynamiteModule", "Failed to retrieve remote module version: " + message2);
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                            return i8;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            cursor2 = cursor3;
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                    if (cursor3 != null) {
                                        cursor3.close();
                                    }
                                } else {
                                    i8 = cursor.getInt(0);
                                }
                            } else if (f9 == 2) {
                                Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                                i8 = k8.e0(t3.b.d0(context), str, z7);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                i8 = k8.d0(t3.b.d0(context), str, z7);
                            }
                        } catch (RemoteException e12) {
                            e8 = e12;
                        }
                    }
                    return i8;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        } catch (Throwable th4) {
            q3.c.a(context, th4);
            throw th4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x009f, code lost:
        r8.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dc  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int f(android.content.Context r8, java.lang.String r9, boolean r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.f(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    private static DynamiteModule g(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    private static void h(ClassLoader classLoader) {
        n nVar;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                nVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                nVar = queryLocalInterface instanceof n ? (n) queryLocalInterface : new n(iBinder);
            }
            f5105r = nVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e8) {
            throw new a("Failed to instantiate dynamite loader", e8, null);
        }
    }

    private static boolean i(Cursor cursor) {
        k kVar = (k) f5100m.get();
        if (kVar == null || kVar.f5110a != null) {
            return false;
        }
        kVar.f5110a = cursor;
        return true;
    }

    private static boolean j(Context context) {
        ApplicationInfo applicationInfo;
        Boolean bool = Boolean.TRUE;
        if (bool.equals(null) || bool.equals(f5099l)) {
            return true;
        }
        boolean z7 = false;
        if (f5099l == null) {
            ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (h3.e.f().h(context, 10000000) == 0 && resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                z7 = true;
            }
            Boolean valueOf = Boolean.valueOf(z7);
            f5099l = valueOf;
            z7 = valueOf.booleanValue();
            if (z7 && (applicationInfo = resolveContentProvider.applicationInfo) != null && (applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                f5097j = true;
            }
        }
        if (!z7) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z7;
    }

    private static m k(Context context) {
        m mVar;
        synchronized (DynamiteModule.class) {
            m mVar2 = f5104q;
            if (mVar2 != null) {
                return mVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    mVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    mVar = queryLocalInterface instanceof m ? (m) queryLocalInterface : new m(iBinder);
                }
                if (mVar != null) {
                    f5104q = mVar;
                    return mVar;
                }
            } catch (Exception e8) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e8.getMessage());
            }
            return null;
        }
    }

    public IBinder c(String str) {
        try {
            return (IBinder) this.f5106a.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e8) {
            throw new a("Failed to instantiate module class: ".concat(String.valueOf(str)), e8, null);
        }
    }
}
