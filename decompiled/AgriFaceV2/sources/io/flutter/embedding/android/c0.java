package io.flutter.embedding.android;

import android.util.LongSparseArray;
import android.view.MotionEvent;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public final class c0 {

    /* renamed from: c  reason: collision with root package name */
    private static c0 f7582c;

    /* renamed from: a  reason: collision with root package name */
    private final LongSparseArray f7583a = new LongSparseArray();

    /* renamed from: b  reason: collision with root package name */
    private final PriorityQueue f7584b = new PriorityQueue();

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: b  reason: collision with root package name */
        private static final AtomicLong f7585b = new AtomicLong(0);

        /* renamed from: a  reason: collision with root package name */
        private final long f7586a;

        private a(long j8) {
            this.f7586a = j8;
        }

        public static a b() {
            return c(f7585b.incrementAndGet());
        }

        public static a c(long j8) {
            return new a(j8);
        }

        public long d() {
            return this.f7586a;
        }
    }

    private c0() {
    }

    public static c0 a() {
        if (f7582c == null) {
            f7582c = new c0();
        }
        return f7582c;
    }

    public MotionEvent b(a aVar) {
        while (!this.f7584b.isEmpty() && ((Long) this.f7584b.peek()).longValue() < aVar.f7586a) {
            this.f7583a.remove(((Long) this.f7584b.poll()).longValue());
        }
        if (!this.f7584b.isEmpty() && ((Long) this.f7584b.peek()).longValue() == aVar.f7586a) {
            this.f7584b.poll();
        }
        MotionEvent motionEvent = (MotionEvent) this.f7583a.get(aVar.f7586a);
        this.f7583a.remove(aVar.f7586a);
        return motionEvent;
    }

    public a c(MotionEvent motionEvent) {
        a b8 = a.b();
        this.f7583a.put(b8.f7586a, MotionEvent.obtain(motionEvent));
        this.f7584b.add(Long.valueOf(b8.f7586a));
        return b8;
    }
}
