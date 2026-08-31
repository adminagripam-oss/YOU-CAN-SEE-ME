package f1;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
/* loaded from: classes.dex */
public abstract class g {

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final int f6709a;

        /* renamed from: b  reason: collision with root package name */
        private final b[] f6710b;

        public a(int i8, b[] bVarArr) {
            this.f6709a = i8;
            this.f6710b = bVarArr;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static a a(int i8, b[] bVarArr) {
            return new a(i8, bVarArr);
        }

        public b[] b() {
            return this.f6710b;
        }

        public int c() {
            return this.f6709a;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Uri f6711a;

        /* renamed from: b  reason: collision with root package name */
        private final int f6712b;

        /* renamed from: c  reason: collision with root package name */
        private final int f6713c;

        /* renamed from: d  reason: collision with root package name */
        private final boolean f6714d;

        /* renamed from: e  reason: collision with root package name */
        private final int f6715e;

        public b(Uri uri, int i8, int i9, boolean z7, int i10) {
            this.f6711a = (Uri) g1.e.j(uri);
            this.f6712b = i8;
            this.f6713c = i9;
            this.f6714d = z7;
            this.f6715e = i10;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static b a(Uri uri, int i8, int i9, boolean z7, int i10) {
            return new b(uri, i8, i9, z7, i10);
        }

        public int b() {
            return this.f6715e;
        }

        public int c() {
            return this.f6712b;
        }

        public Uri d() {
            return this.f6711a;
        }

        public int e() {
            return this.f6713c;
        }

        public boolean f() {
            return this.f6714d;
        }
    }

    /* loaded from: classes.dex */
    public static class c {
        public abstract void a(int i8);

        public abstract void b(Typeface typeface);
    }

    public static Typeface a(Context context, CancellationSignal cancellationSignal, b[] bVarArr) {
        return androidx.core.graphics.h.b(context, cancellationSignal, bVarArr, 0);
    }

    public static a b(Context context, CancellationSignal cancellationSignal, e eVar) {
        return d.e(context, eVar, cancellationSignal);
    }

    public static Typeface c(Context context, e eVar, int i8, boolean z7, int i9, Handler handler, c cVar) {
        f1.a aVar = new f1.a(cVar, handler);
        return z7 ? f.e(context, eVar, aVar, i8, i9) : f.d(context, eVar, i8, null, aVar);
    }
}
