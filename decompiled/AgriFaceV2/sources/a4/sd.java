package a4;

import android.content.Context;
import android.os.SystemClock;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public final class sd {

    /* renamed from: a  reason: collision with root package name */
    private final k3.v f1076a;

    /* renamed from: b  reason: collision with root package name */
    private final AtomicLong f1077b = new AtomicLong(-1);

    sd(Context context, String str) {
        this.f1076a = k3.u.b(context, k3.w.a().b("mlkit:vision").a());
    }

    public static sd a(Context context) {
        return new sd(context, "mlkit:vision");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(long j8, Exception exc) {
        this.f1077b.set(j8);
    }

    public final synchronized void c(int i8, int i9, long j8, long j9) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f1077b.get() != -1 && elapsedRealtime - this.f1077b.get() <= TimeUnit.MINUTES.toMillis(30L)) {
            return;
        }
        this.f1076a.a(new k3.t(0, Arrays.asList(new k3.o(i8, i9, 0, j8, j9, null, null, 0)))).c(new f4.f() { // from class: a4.rd
            @Override // f4.f
            public final void a(Exception exc) {
                sd.this.b(elapsedRealtime, exc);
            }
        });
    }
}
