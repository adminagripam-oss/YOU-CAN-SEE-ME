package y0;

import android.media.MediaCodec;
import androidx.concurrent.futures.c;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i1 implements g1 {

    /* renamed from: a  reason: collision with root package name */
    private final MediaCodec f13129a;

    /* renamed from: b  reason: collision with root package name */
    private final int f13130b;

    /* renamed from: c  reason: collision with root package name */
    private final ByteBuffer f13131c;

    /* renamed from: d  reason: collision with root package name */
    private final i4.d f13132d;

    /* renamed from: e  reason: collision with root package name */
    private final c.a f13133e;

    /* renamed from: f  reason: collision with root package name */
    private final AtomicBoolean f13134f = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    private long f13135g = 0;

    /* renamed from: h  reason: collision with root package name */
    private boolean f13136h = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i1(MediaCodec mediaCodec, int i8) {
        this.f13129a = (MediaCodec) g1.e.j(mediaCodec);
        this.f13130b = g1.e.g(i8);
        this.f13131c = mediaCodec.getInputBuffer(i8);
        final AtomicReference atomicReference = new AtomicReference();
        this.f13132d = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y0.h1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object g8;
                g8 = i1.g(atomicReference, aVar);
                return g8;
            }
        });
        this.f13133e = (c.a) g1.e.j((c.a) atomicReference.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object g(AtomicReference atomicReference, c.a aVar) {
        atomicReference.set(aVar);
        return "Terminate InputBuffer";
    }

    private void h() {
        if (this.f13134f.get()) {
            throw new IllegalStateException("The buffer is submitted or canceled.");
        }
    }

    @Override // y0.g1
    public ByteBuffer a() {
        h();
        return this.f13131c;
    }

    @Override // y0.g1
    public void b(boolean z7) {
        h();
        this.f13136h = z7;
    }

    @Override // y0.g1
    public boolean c() {
        if (this.f13134f.getAndSet(true)) {
            return false;
        }
        try {
            this.f13129a.queueInputBuffer(this.f13130b, this.f13131c.position(), this.f13131c.limit(), this.f13135g, this.f13136h ? 4 : 0);
            this.f13133e.c(null);
            return true;
        } catch (IllegalStateException e8) {
            this.f13133e.f(e8);
            return false;
        }
    }

    @Override // y0.g1
    public boolean cancel() {
        if (this.f13134f.getAndSet(true)) {
            return false;
        }
        try {
            this.f13129a.queueInputBuffer(this.f13130b, 0, 0, 0L, 0);
            this.f13133e.c(null);
        } catch (IllegalStateException e8) {
            this.f13133e.f(e8);
        }
        return true;
    }

    @Override // y0.g1
    public i4.d d() {
        return f0.n.B(this.f13132d);
    }

    @Override // y0.g1
    public void e(long j8) {
        h();
        g1.e.a(j8 >= 0);
        this.f13135g = j8;
    }
}
