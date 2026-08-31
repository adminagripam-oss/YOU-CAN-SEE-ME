package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.content.Context;
import android.os.SystemClock;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public final class zh {

    /* renamed from: a  reason: collision with root package name */
    private final k3.v f5980a;

    /* renamed from: b  reason: collision with root package name */
    private final AtomicLong f5981b = new AtomicLong(-1);

    zh(Context context, String str) {
        this.f5980a = k3.u.b(context, k3.w.a().b("mlkit:vision").a());
    }

    public static zh a(Context context) {
        return new zh(context, "mlkit:vision");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(long j8, Exception exc) {
        this.f5981b.set(j8);
    }

    public final synchronized void c(int i8, int i9, long j8, long j9) {
        AtomicLong atomicLong = this.f5981b;
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (atomicLong.get() != -1 && elapsedRealtime - this.f5981b.get() <= TimeUnit.MINUTES.toMillis(30L)) {
            return;
        }
        this.f5980a.a(new k3.t(0, Arrays.asList(new k3.o(25503, i9, 0, j8, j9, null, null, 0, -1)))).c(new f4.f() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.yh
            @Override // f4.f
            public final void a(Exception exc) {
                zh.this.b(elapsedRealtime, exc);
            }
        });
    }
}
