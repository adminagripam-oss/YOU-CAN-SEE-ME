package y0;

import android.media.MediaCodec;
import androidx.concurrent.futures.c;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public class g implements i {

    /* renamed from: e  reason: collision with root package name */
    private final ByteBuffer f13056e;

    /* renamed from: f  reason: collision with root package name */
    private final MediaCodec.BufferInfo f13057f;

    /* renamed from: g  reason: collision with root package name */
    private final i4.d f13058g;

    /* renamed from: h  reason: collision with root package name */
    private final c.a f13059h;

    public g(i iVar) {
        this.f13057f = d(iVar);
        this.f13056e = c(iVar);
        final AtomicReference atomicReference = new AtomicReference();
        this.f13058g = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y0.f
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object f8;
                f8 = g.f(atomicReference, aVar);
                return f8;
            }
        });
        this.f13059h = (c.a) g1.e.j((c.a) atomicReference.get());
    }

    private ByteBuffer c(i iVar) {
        ByteBuffer a8 = iVar.a();
        MediaCodec.BufferInfo F = iVar.F();
        a8.position(F.offset);
        a8.limit(F.offset + F.size);
        ByteBuffer allocate = ByteBuffer.allocate(F.size);
        allocate.order(a8.order());
        allocate.put(a8);
        allocate.flip();
        return allocate;
    }

    private MediaCodec.BufferInfo d(i iVar) {
        MediaCodec.BufferInfo F = iVar.F();
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        bufferInfo.set(0, F.size, F.presentationTimeUs, F.flags);
        return bufferInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object f(AtomicReference atomicReference, c.a aVar) {
        atomicReference.set(aVar);
        return "Data closed";
    }

    @Override // y0.i
    public MediaCodec.BufferInfo F() {
        return this.f13057f;
    }

    @Override // y0.i
    public boolean I() {
        return (this.f13057f.flags & 1) != 0;
    }

    @Override // y0.i
    public ByteBuffer a() {
        return this.f13056e;
    }

    @Override // y0.i, java.lang.AutoCloseable
    public void close() {
        this.f13059h.c(null);
    }

    @Override // y0.i
    public long size() {
        return this.f13057f.size;
    }

    @Override // y0.i
    public long y() {
        return this.f13057f.presentationTimeUs;
    }
}
