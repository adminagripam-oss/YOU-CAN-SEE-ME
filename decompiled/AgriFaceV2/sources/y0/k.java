package y0;

import android.media.MediaCodec;
import androidx.concurrent.futures.c;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public class k implements i {

    /* renamed from: e  reason: collision with root package name */
    private final MediaCodec f13140e;

    /* renamed from: f  reason: collision with root package name */
    private final MediaCodec.BufferInfo f13141f;

    /* renamed from: g  reason: collision with root package name */
    private final int f13142g;

    /* renamed from: h  reason: collision with root package name */
    private final ByteBuffer f13143h;

    /* renamed from: i  reason: collision with root package name */
    private final i4.d f13144i;

    /* renamed from: j  reason: collision with root package name */
    private final c.a f13145j;

    /* renamed from: k  reason: collision with root package name */
    private final AtomicBoolean f13146k = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(MediaCodec mediaCodec, int i8, MediaCodec.BufferInfo bufferInfo) {
        this.f13140e = (MediaCodec) g1.e.j(mediaCodec);
        this.f13142g = i8;
        this.f13143h = mediaCodec.getOutputBuffer(i8);
        this.f13141f = (MediaCodec.BufferInfo) g1.e.j(bufferInfo);
        final AtomicReference atomicReference = new AtomicReference();
        this.f13144i = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y0.j
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object d8;
                d8 = k.d(atomicReference, aVar);
                return d8;
            }
        });
        this.f13145j = (c.a) g1.e.j((c.a) atomicReference.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object d(AtomicReference atomicReference, c.a aVar) {
        atomicReference.set(aVar);
        return "Data closed";
    }

    private void f() {
        if (this.f13146k.get()) {
            throw new IllegalStateException("encoded data is closed.");
        }
    }

    @Override // y0.i
    public MediaCodec.BufferInfo F() {
        return this.f13141f;
    }

    @Override // y0.i
    public boolean I() {
        return (this.f13141f.flags & 1) != 0;
    }

    @Override // y0.i
    public ByteBuffer a() {
        f();
        this.f13143h.position(this.f13141f.offset);
        ByteBuffer byteBuffer = this.f13143h;
        MediaCodec.BufferInfo bufferInfo = this.f13141f;
        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
        return this.f13143h;
    }

    public i4.d c() {
        return f0.n.B(this.f13144i);
    }

    @Override // y0.i, java.lang.AutoCloseable
    public void close() {
        if (this.f13146k.getAndSet(true)) {
            return;
        }
        try {
            this.f13140e.releaseOutputBuffer(this.f13142g, false);
            this.f13145j.c(null);
        } catch (IllegalStateException e8) {
            this.f13145j.f(e8);
        }
    }

    @Override // y0.i
    public long size() {
        return this.f13141f.size;
    }

    @Override // y0.i
    public long y() {
        return this.f13141f.presentationTimeUs;
    }
}
