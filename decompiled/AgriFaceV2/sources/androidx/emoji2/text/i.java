package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.os.Handler;
import androidx.core.graphics.n;
import androidx.emoji2.text.e;
import androidx.emoji2.text.i;
import f1.g;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
/* loaded from: classes.dex */
public class i extends e.c {

    /* renamed from: j  reason: collision with root package name */
    private static final a f3327j = new a();

    /* loaded from: classes.dex */
    public static class a {
        public Typeface a(Context context, g.b bVar) {
            return f1.g.a(context, null, new g.b[]{bVar});
        }

        public g.a b(Context context, f1.e eVar) {
            return f1.g.b(context, null, eVar);
        }

        public void c(Context context, ContentObserver contentObserver) {
            context.getContentResolver().unregisterContentObserver(contentObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements e.g {

        /* renamed from: a  reason: collision with root package name */
        private final Context f3328a;

        /* renamed from: b  reason: collision with root package name */
        private final f1.e f3329b;

        /* renamed from: c  reason: collision with root package name */
        private final a f3330c;

        /* renamed from: d  reason: collision with root package name */
        private final Object f3331d = new Object();

        /* renamed from: e  reason: collision with root package name */
        private Handler f3332e;

        /* renamed from: f  reason: collision with root package name */
        private Executor f3333f;

        /* renamed from: g  reason: collision with root package name */
        private ThreadPoolExecutor f3334g;

        /* renamed from: h  reason: collision with root package name */
        e.h f3335h;

        /* renamed from: i  reason: collision with root package name */
        private ContentObserver f3336i;

        /* renamed from: j  reason: collision with root package name */
        private Runnable f3337j;

        b(Context context, f1.e eVar, a aVar) {
            g1.e.k(context, "Context cannot be null");
            g1.e.k(eVar, "FontRequest cannot be null");
            this.f3328a = context.getApplicationContext();
            this.f3329b = eVar;
            this.f3330c = aVar;
        }

        private void b() {
            synchronized (this.f3331d) {
                this.f3335h = null;
                ContentObserver contentObserver = this.f3336i;
                if (contentObserver != null) {
                    this.f3330c.c(this.f3328a, contentObserver);
                    this.f3336i = null;
                }
                Handler handler = this.f3332e;
                if (handler != null) {
                    handler.removeCallbacks(this.f3337j);
                }
                this.f3332e = null;
                ThreadPoolExecutor threadPoolExecutor = this.f3334g;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f3333f = null;
                this.f3334g = null;
            }
        }

        private g.b e() {
            try {
                g.a b8 = this.f3330c.b(this.f3328a, this.f3329b);
                if (b8.c() == 0) {
                    g.b[] b9 = b8.b();
                    if (b9 == null || b9.length == 0) {
                        throw new RuntimeException("fetchFonts failed (empty result)");
                    }
                    return b9[0];
                }
                throw new RuntimeException("fetchFonts failed (" + b8.c() + ")");
            } catch (PackageManager.NameNotFoundException e8) {
                throw new RuntimeException("provider not found", e8);
            }
        }

        @Override // androidx.emoji2.text.e.g
        public void a(e.h hVar) {
            g1.e.k(hVar, "LoaderCallback cannot be null");
            synchronized (this.f3331d) {
                this.f3335h = hVar;
            }
            d();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void c() {
            synchronized (this.f3331d) {
                if (this.f3335h == null) {
                    return;
                }
                try {
                    g.b e8 = e();
                    int b8 = e8.b();
                    if (b8 == 2) {
                        synchronized (this.f3331d) {
                        }
                    }
                    if (b8 != 0) {
                        throw new RuntimeException("fetchFonts result is not OK. (" + b8 + ")");
                    }
                    androidx.core.os.l.a("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                    Typeface a8 = this.f3330c.a(this.f3328a, e8);
                    ByteBuffer e9 = n.e(this.f3328a, null, e8.d());
                    if (e9 == null || a8 == null) {
                        throw new RuntimeException("Unable to open file.");
                    }
                    l b9 = l.b(a8, e9);
                    androidx.core.os.l.b();
                    synchronized (this.f3331d) {
                        e.h hVar = this.f3335h;
                        if (hVar != null) {
                            hVar.b(b9);
                        }
                    }
                    b();
                } catch (Throwable th) {
                    synchronized (this.f3331d) {
                        e.h hVar2 = this.f3335h;
                        if (hVar2 != null) {
                            hVar2.a(th);
                        }
                        b();
                    }
                }
            }
        }

        void d() {
            synchronized (this.f3331d) {
                if (this.f3335h == null) {
                    return;
                }
                if (this.f3333f == null) {
                    ThreadPoolExecutor b8 = androidx.emoji2.text.b.b("emojiCompat");
                    this.f3334g = b8;
                    this.f3333f = b8;
                }
                this.f3333f.execute(new Runnable() { // from class: androidx.emoji2.text.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        i.b.this.c();
                    }
                });
            }
        }

        public void f(Executor executor) {
            synchronized (this.f3331d) {
                this.f3333f = executor;
            }
        }
    }

    public i(Context context, f1.e eVar) {
        super(new b(context, eVar, f3327j));
    }

    public i c(Executor executor) {
        ((b) a()).f(executor);
        return this;
    }
}
