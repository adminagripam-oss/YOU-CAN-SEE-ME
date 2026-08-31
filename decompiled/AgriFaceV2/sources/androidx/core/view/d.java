package androidx.core.view;

import android.content.ClipData;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.Objects;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final f f3115a;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final c f3116a;

        public a(ClipData clipData, int i8) {
            this.f3116a = Build.VERSION.SDK_INT >= 31 ? new b(clipData, i8) : new C0033d(clipData, i8);
        }

        public d a() {
            return this.f3116a.a();
        }

        public a b(Bundle bundle) {
            this.f3116a.b(bundle);
            return this;
        }

        public a c(int i8) {
            this.f3116a.d(i8);
            return this;
        }

        public a d(Uri uri) {
            this.f3116a.c(uri);
            return this;
        }
    }

    /* loaded from: classes.dex */
    private static final class b implements c {

        /* renamed from: a  reason: collision with root package name */
        private final ContentInfo.Builder f3117a;

        b(ClipData clipData, int i8) {
            this.f3117a = androidx.core.view.g.a(clipData, i8);
        }

        @Override // androidx.core.view.d.c
        public d a() {
            ContentInfo build;
            build = this.f3117a.build();
            return new d(new e(build));
        }

        @Override // androidx.core.view.d.c
        public void b(Bundle bundle) {
            this.f3117a.setExtras(bundle);
        }

        @Override // androidx.core.view.d.c
        public void c(Uri uri) {
            this.f3117a.setLinkUri(uri);
        }

        @Override // androidx.core.view.d.c
        public void d(int i8) {
            this.f3117a.setFlags(i8);
        }
    }

    /* loaded from: classes.dex */
    private interface c {
        d a();

        void b(Bundle bundle);

        void c(Uri uri);

        void d(int i8);
    }

    /* renamed from: androidx.core.view.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static final class C0033d implements c {

        /* renamed from: a  reason: collision with root package name */
        ClipData f3118a;

        /* renamed from: b  reason: collision with root package name */
        int f3119b;

        /* renamed from: c  reason: collision with root package name */
        int f3120c;

        /* renamed from: d  reason: collision with root package name */
        Uri f3121d;

        /* renamed from: e  reason: collision with root package name */
        Bundle f3122e;

        C0033d(ClipData clipData, int i8) {
            this.f3118a = clipData;
            this.f3119b = i8;
        }

        @Override // androidx.core.view.d.c
        public d a() {
            return new d(new g(this));
        }

        @Override // androidx.core.view.d.c
        public void b(Bundle bundle) {
            this.f3122e = bundle;
        }

        @Override // androidx.core.view.d.c
        public void c(Uri uri) {
            this.f3121d = uri;
        }

        @Override // androidx.core.view.d.c
        public void d(int i8) {
            this.f3120c = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class e implements f {

        /* renamed from: a  reason: collision with root package name */
        private final ContentInfo f3123a;

        e(ContentInfo contentInfo) {
            this.f3123a = androidx.core.view.c.a(g1.e.j(contentInfo));
        }

        @Override // androidx.core.view.d.f
        public ClipData a() {
            ClipData clip;
            clip = this.f3123a.getClip();
            return clip;
        }

        @Override // androidx.core.view.d.f
        public int b() {
            int flags;
            flags = this.f3123a.getFlags();
            return flags;
        }

        @Override // androidx.core.view.d.f
        public ContentInfo c() {
            return this.f3123a;
        }

        @Override // androidx.core.view.d.f
        public int d() {
            int source;
            source = this.f3123a.getSource();
            return source;
        }

        public String toString() {
            return "ContentInfoCompat{" + this.f3123a + "}";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface f {
        ClipData a();

        int b();

        ContentInfo c();

        int d();
    }

    /* loaded from: classes.dex */
    private static final class g implements f {

        /* renamed from: a  reason: collision with root package name */
        private final ClipData f3124a;

        /* renamed from: b  reason: collision with root package name */
        private final int f3125b;

        /* renamed from: c  reason: collision with root package name */
        private final int f3126c;

        /* renamed from: d  reason: collision with root package name */
        private final Uri f3127d;

        /* renamed from: e  reason: collision with root package name */
        private final Bundle f3128e;

        g(C0033d c0033d) {
            this.f3124a = (ClipData) g1.e.j(c0033d.f3118a);
            this.f3125b = g1.e.e(c0033d.f3119b, 0, 5, "source");
            this.f3126c = g1.e.i(c0033d.f3120c, 1);
            this.f3127d = c0033d.f3121d;
            this.f3128e = c0033d.f3122e;
        }

        @Override // androidx.core.view.d.f
        public ClipData a() {
            return this.f3124a;
        }

        @Override // androidx.core.view.d.f
        public int b() {
            return this.f3126c;
        }

        @Override // androidx.core.view.d.f
        public ContentInfo c() {
            return null;
        }

        @Override // androidx.core.view.d.f
        public int d() {
            return this.f3125b;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("ContentInfoCompat{clip=");
            sb.append(this.f3124a.getDescription());
            sb.append(", source=");
            sb.append(d.e(this.f3125b));
            sb.append(", flags=");
            sb.append(d.a(this.f3126c));
            if (this.f3127d == null) {
                str = "";
            } else {
                str = ", hasLinkUri(" + this.f3127d.toString().length() + ")";
            }
            sb.append(str);
            sb.append(this.f3128e != null ? ", hasExtras" : "");
            sb.append("}");
            return sb.toString();
        }
    }

    d(f fVar) {
        this.f3115a = fVar;
    }

    static String a(int i8) {
        return (i8 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i8);
    }

    static String e(int i8) {
        return i8 != 0 ? i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? i8 != 5 ? String.valueOf(i8) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP";
    }

    public static d g(ContentInfo contentInfo) {
        return new d(new e(contentInfo));
    }

    public ClipData b() {
        return this.f3115a.a();
    }

    public int c() {
        return this.f3115a.b();
    }

    public int d() {
        return this.f3115a.d();
    }

    public ContentInfo f() {
        ContentInfo c8 = this.f3115a.c();
        Objects.requireNonNull(c8);
        return androidx.core.view.c.a(c8);
    }

    public String toString() {
        return this.f3115a.toString();
    }
}
