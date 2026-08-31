package h6;

import android.os.Build;
import android.util.DisplayMetrics;
import i6.a;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes.dex */
public class t {

    /* renamed from: b  reason: collision with root package name */
    private static final a f7415b = new a();

    /* renamed from: a  reason: collision with root package name */
    public final i6.a f7416a;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final ConcurrentLinkedQueue f7417a = new ConcurrentLinkedQueue();

        /* renamed from: b  reason: collision with root package name */
        private b f7418b;

        /* renamed from: c  reason: collision with root package name */
        private b f7419c;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: h6.t$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0097a implements a.e {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ b f7420a;

            C0097a(b bVar) {
                this.f7420a = bVar;
            }

            @Override // i6.a.e
            public void a(Object obj) {
                a.this.f7417a.remove(this.f7420a);
                if (a.this.f7417a.isEmpty()) {
                    return;
                }
                z5.b.b("SettingsChannel", "The queue becomes empty after removing config generation " + String.valueOf(this.f7420a.f7423a));
            }
        }

        /* loaded from: classes.dex */
        public static class b {

            /* renamed from: c  reason: collision with root package name */
            private static int f7422c = Integer.MIN_VALUE;

            /* renamed from: a  reason: collision with root package name */
            public final int f7423a;

            /* renamed from: b  reason: collision with root package name */
            private final DisplayMetrics f7424b;

            public b(DisplayMetrics displayMetrics) {
                int i8 = f7422c;
                f7422c = i8 + 1;
                this.f7423a = i8;
                this.f7424b = displayMetrics;
            }
        }

        public a.e b(b bVar) {
            this.f7417a.add(bVar);
            b bVar2 = this.f7419c;
            this.f7419c = bVar;
            if (bVar2 == null) {
                return null;
            }
            return new C0097a(bVar2);
        }

        public b c(int i8) {
            b bVar;
            StringBuilder sb;
            String valueOf;
            if (this.f7418b == null) {
                this.f7418b = (b) this.f7417a.poll();
            }
            while (true) {
                bVar = this.f7418b;
                if (bVar == null || bVar.f7423a >= i8) {
                    break;
                }
                this.f7418b = (b) this.f7417a.poll();
            }
            if (bVar == null) {
                sb = new StringBuilder();
                sb.append("Cannot find config with generation: ");
                sb.append(String.valueOf(i8));
                valueOf = ", after exhausting the queue.";
            } else if (bVar.f7423a == i8) {
                return bVar;
            } else {
                sb = new StringBuilder();
                sb.append("Cannot find config with generation: ");
                sb.append(String.valueOf(i8));
                sb.append(", the oldest config is now: ");
                valueOf = String.valueOf(this.f7418b.f7423a);
            }
            sb.append(valueOf);
            z5.b.b("SettingsChannel", sb.toString());
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final i6.a f7425a;

        /* renamed from: b  reason: collision with root package name */
        private Map f7426b = new HashMap();

        /* renamed from: c  reason: collision with root package name */
        private DisplayMetrics f7427c;

        b(i6.a aVar) {
            this.f7425a = aVar;
        }

        public void a() {
            z5.b.f("SettingsChannel", "Sending message: \ntextScaleFactor: " + this.f7426b.get("textScaleFactor") + "\nalwaysUse24HourFormat: " + this.f7426b.get("alwaysUse24HourFormat") + "\nplatformBrightness: " + this.f7426b.get("platformBrightness"));
            DisplayMetrics displayMetrics = this.f7427c;
            if (!t.c() || displayMetrics == null) {
                this.f7425a.c(this.f7426b);
                return;
            }
            a.b bVar = new a.b(displayMetrics);
            a.e b8 = t.f7415b.b(bVar);
            this.f7426b.put("configurationId", Integer.valueOf(bVar.f7423a));
            this.f7425a.d(this.f7426b, b8);
        }

        public b b(boolean z7) {
            this.f7426b.put("brieflyShowPassword", Boolean.valueOf(z7));
            return this;
        }

        public b c(DisplayMetrics displayMetrics) {
            this.f7427c = displayMetrics;
            return this;
        }

        public b d(boolean z7) {
            this.f7426b.put("nativeSpellCheckServiceDefined", Boolean.valueOf(z7));
            return this;
        }

        public b e(c cVar) {
            this.f7426b.put("platformBrightness", cVar.f7431e);
            return this;
        }

        public b f(float f8) {
            this.f7426b.put("textScaleFactor", Float.valueOf(f8));
            return this;
        }

        public b g(boolean z7) {
            this.f7426b.put("alwaysUse24HourFormat", Boolean.valueOf(z7));
            return this;
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        light("light"),
        dark("dark");
        

        /* renamed from: e  reason: collision with root package name */
        public String f7431e;

        c(String str) {
            this.f7431e = str;
        }
    }

    public t(a6.a aVar) {
        this.f7416a = new i6.a(aVar, "flutter/settings", i6.e.f7533a);
    }

    public static DisplayMetrics b(int i8) {
        a.b c8 = f7415b.c(i8);
        if (c8 == null) {
            return null;
        }
        return c8.f7424b;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 34;
    }

    public b d() {
        return new b(this.f7416a);
    }
}
