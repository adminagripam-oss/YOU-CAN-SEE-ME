package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    private static final androidx.concurrent.futures.d f3907a = androidx.concurrent.futures.d.r();

    /* renamed from: b  reason: collision with root package name */
    private static final Object f3908b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private static c f3909c = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        static PackageInfo a(PackageManager packageManager, Context context) {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        }
    }

    /* loaded from: classes.dex */
    static class b {

        /* renamed from: a  reason: collision with root package name */
        final int f3910a;

        /* renamed from: b  reason: collision with root package name */
        final int f3911b;

        /* renamed from: c  reason: collision with root package name */
        final long f3912c;

        /* renamed from: d  reason: collision with root package name */
        final long f3913d;

        b(int i8, int i9, long j8, long j9) {
            this.f3910a = i8;
            this.f3911b = i9;
            this.f3912c = j8;
            this.f3913d = j9;
        }

        static b a(File file) {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                b bVar = new b(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
                dataInputStream.close();
                return bVar;
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        void b(File file) {
            file.delete();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(this.f3910a);
                dataOutputStream.writeInt(this.f3911b);
                dataOutputStream.writeLong(this.f3912c);
                dataOutputStream.writeLong(this.f3913d);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f3911b == bVar.f3911b && this.f3912c == bVar.f3912c && this.f3910a == bVar.f3910a && this.f3913d == bVar.f3913d;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.f3911b), Long.valueOf(this.f3912c), Integer.valueOf(this.f3910a), Long.valueOf(this.f3913d));
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        final int f3914a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f3915b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f3916c;

        c(int i8, boolean z7, boolean z8) {
            this.f3914a = i8;
            this.f3916c = z8;
            this.f3915b = z7;
        }
    }

    private static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return (Build.VERSION.SDK_INT >= 33 ? a.a(packageManager, context) : packageManager.getPackageInfo(context.getPackageName(), 0)).lastUpdateTime;
    }

    private static c b(int i8, boolean z7, boolean z8) {
        c cVar = new c(i8, z7, z8);
        f3909c = cVar;
        f3907a.o(cVar);
        return f3909c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(20:14|(1:78)(1:18)|19|(1:77)(1:23)|24|25|26|(2:63|64)(1:28)|29|(8:36|(1:40)|(1:47)|48|(2:55|56)|52|53|54)|(1:62)|(1:40)|(3:42|45|47)|48|(1:50)|55|56|52|53|54) */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x009d, code lost:
        r3 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00cc, code lost:
        r3 = 196608;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.profileinstaller.o.c c(android.content.Context r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.profileinstaller.o.c(android.content.Context, boolean):androidx.profileinstaller.o$c");
    }
}
