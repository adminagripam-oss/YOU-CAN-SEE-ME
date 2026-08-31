package f1;

import android.graphics.Typeface;
import android.os.Handler;
import f1.f;
import f1.g;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final g.c f6677a;

    /* renamed from: b  reason: collision with root package name */
    private final Handler f6678b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f1.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0085a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ g.c f6679e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ Typeface f6680f;

        RunnableC0085a(g.c cVar, Typeface typeface) {
            this.f6679e = cVar;
            this.f6680f = typeface;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6679e.b(this.f6680f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ g.c f6682e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ int f6683f;

        b(g.c cVar, int i8) {
            this.f6682e = cVar;
            this.f6683f = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6682e.a(this.f6683f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(g.c cVar, Handler handler) {
        this.f6677a = cVar;
        this.f6678b = handler;
    }

    private void a(int i8) {
        this.f6678b.post(new b(this.f6677a, i8));
    }

    private void c(Typeface typeface) {
        this.f6678b.post(new RunnableC0085a(this.f6677a, typeface));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(f.e eVar) {
        if (eVar.a()) {
            c(eVar.f6707a);
        } else {
            a(eVar.f6708b);
        }
    }
}
