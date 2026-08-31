package androidx.emoji2.text;

import android.content.Context;
import androidx.emoji2.text.EmojiCompatInitializer;
import androidx.emoji2.text.e;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.ProcessLifecycleInitializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
/* loaded from: classes.dex */
public class EmojiCompatInitializer implements x1.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements DefaultLifecycleObserver {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ androidx.lifecycle.i f3265e;

        a(androidx.lifecycle.i iVar) {
            this.f3265e = iVar;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onResume(androidx.lifecycle.m mVar) {
            EmojiCompatInitializer.this.e();
            this.f3265e.c(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends e.c {
        protected b(Context context) {
            super(new c(context));
            b(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c implements e.g {

        /* renamed from: a  reason: collision with root package name */
        private final Context f3267a;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a extends e.h {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ e.h f3268a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ ThreadPoolExecutor f3269b;

            a(e.h hVar, ThreadPoolExecutor threadPoolExecutor) {
                this.f3268a = hVar;
                this.f3269b = threadPoolExecutor;
            }

            @Override // androidx.emoji2.text.e.h
            public void a(Throwable th) {
                try {
                    this.f3268a.a(th);
                } finally {
                    this.f3269b.shutdown();
                }
            }

            @Override // androidx.emoji2.text.e.h
            public void b(l lVar) {
                try {
                    this.f3268a.b(lVar);
                } finally {
                    this.f3269b.shutdown();
                }
            }
        }

        c(Context context) {
            this.f3267a = context.getApplicationContext();
        }

        @Override // androidx.emoji2.text.e.g
        public void a(final e.h hVar) {
            final ThreadPoolExecutor b8 = androidx.emoji2.text.b.b("EmojiCompatInitializer");
            b8.execute(new Runnable() { // from class: androidx.emoji2.text.f
                @Override // java.lang.Runnable
                public final void run() {
                    EmojiCompatInitializer.c.this.d(hVar, b8);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: c */
        public void d(e.h hVar, ThreadPoolExecutor threadPoolExecutor) {
            try {
                i a8 = androidx.emoji2.text.c.a(this.f3267a);
                if (a8 == null) {
                    throw new RuntimeException("EmojiCompat font provider not available on this device.");
                }
                a8.c(threadPoolExecutor);
                a8.a().a(new a(hVar, threadPoolExecutor));
            } catch (Throwable th) {
                hVar.a(th);
                threadPoolExecutor.shutdown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                androidx.core.os.l.a("EmojiCompat.EmojiCompatInitializer.run");
                if (e.h()) {
                    e.b().k();
                }
            } finally {
                androidx.core.os.l.b();
            }
        }
    }

    @Override // x1.a
    public List a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // x1.a
    /* renamed from: c */
    public Boolean b(Context context) {
        e.g(new b(context));
        d(context);
        return Boolean.TRUE;
    }

    void d(Context context) {
        androidx.lifecycle.i lifecycle = ((androidx.lifecycle.m) androidx.startup.a.e(context).f(ProcessLifecycleInitializer.class)).getLifecycle();
        lifecycle.a(new a(lifecycle));
    }

    void e() {
        androidx.emoji2.text.b.d().postDelayed(new d(), 500L);
    }
}
