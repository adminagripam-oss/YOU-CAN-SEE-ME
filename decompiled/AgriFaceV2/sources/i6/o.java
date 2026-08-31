package i6;

import i6.n;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
public final class o implements k {

    /* renamed from: b  reason: collision with root package name */
    public static final o f7550b = new o(n.f7547a);

    /* renamed from: a  reason: collision with root package name */
    private final n f7551a;

    public o(n nVar) {
        this.f7551a = nVar;
    }

    @Override // i6.k
    public ByteBuffer a(i iVar) {
        n.a aVar = new n.a();
        this.f7551a.p(aVar, iVar.f7535a);
        this.f7551a.p(aVar, iVar.f7536b);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(aVar.size());
        allocateDirect.put(aVar.b(), 0, aVar.size());
        return allocateDirect;
    }

    @Override // i6.k
    public ByteBuffer b(Object obj) {
        n.a aVar = new n.a();
        aVar.write(0);
        this.f7551a.p(aVar, obj);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(aVar.size());
        allocateDirect.put(aVar.b(), 0, aVar.size());
        return allocateDirect;
    }

    @Override // i6.k
    public ByteBuffer c(String str, String str2, Object obj, String str3) {
        n.a aVar = new n.a();
        aVar.write(1);
        this.f7551a.p(aVar, str);
        this.f7551a.p(aVar, str2);
        if (obj instanceof Throwable) {
            this.f7551a.p(aVar, z5.b.d((Throwable) obj));
        } else {
            this.f7551a.p(aVar, obj);
        }
        this.f7551a.p(aVar, str3);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(aVar.size());
        allocateDirect.put(aVar.b(), 0, aVar.size());
        return allocateDirect;
    }

    @Override // i6.k
    public ByteBuffer d(String str, String str2, Object obj) {
        n.a aVar = new n.a();
        aVar.write(1);
        this.f7551a.p(aVar, str);
        this.f7551a.p(aVar, str2);
        if (obj instanceof Throwable) {
            this.f7551a.p(aVar, z5.b.d((Throwable) obj));
        } else {
            this.f7551a.p(aVar, obj);
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(aVar.size());
        allocateDirect.put(aVar.b(), 0, aVar.size());
        return allocateDirect;
    }

    @Override // i6.k
    public i e(ByteBuffer byteBuffer) {
        byteBuffer.order(ByteOrder.nativeOrder());
        Object f8 = this.f7551a.f(byteBuffer);
        Object f9 = this.f7551a.f(byteBuffer);
        if (!(f8 instanceof String) || byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Method call corrupted");
        }
        return new i((String) f8, f9);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
        if (r0 == 1) goto L8;
     */
    @Override // i6.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object f(java.nio.ByteBuffer r5) {
        /*
            r4 = this;
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            r5.order(r0)
            byte r0 = r5.get()
            if (r0 == 0) goto L11
            r1 = 1
            if (r0 != r1) goto L4a
            goto L1e
        L11:
            i6.n r0 = r4.f7551a
            java.lang.Object r0 = r0.f(r5)
            boolean r1 = r5.hasRemaining()
            if (r1 != 0) goto L1e
            return r0
        L1e:
            i6.n r0 = r4.f7551a
            java.lang.Object r0 = r0.f(r5)
            i6.n r1 = r4.f7551a
            java.lang.Object r1 = r1.f(r5)
            i6.n r2 = r4.f7551a
            java.lang.Object r2 = r2.f(r5)
            boolean r3 = r0 instanceof java.lang.String
            if (r3 == 0) goto L4a
            if (r1 == 0) goto L3a
            boolean r3 = r1 instanceof java.lang.String
            if (r3 == 0) goto L4a
        L3a:
            boolean r5 = r5.hasRemaining()
            if (r5 != 0) goto L4a
            i6.d r5 = new i6.d
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = (java.lang.String) r1
            r5.<init>(r0, r1, r2)
            throw r5
        L4a:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Envelope corrupted"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: i6.o.f(java.nio.ByteBuffer):java.lang.Object");
    }
}
