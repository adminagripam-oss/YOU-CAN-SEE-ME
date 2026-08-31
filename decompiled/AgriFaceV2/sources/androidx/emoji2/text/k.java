package androidx.emoji2.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
abstract class k {

    /* loaded from: classes.dex */
    private static class a implements c {

        /* renamed from: a  reason: collision with root package name */
        private final ByteBuffer f3339a;

        a(ByteBuffer byteBuffer) {
            this.f3339a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // androidx.emoji2.text.k.c
        public void a(int i8) {
            ByteBuffer byteBuffer = this.f3339a;
            byteBuffer.position(byteBuffer.position() + i8);
        }

        @Override // androidx.emoji2.text.k.c
        public long b() {
            return k.c(this.f3339a.getInt());
        }

        @Override // androidx.emoji2.text.k.c
        public int c() {
            return this.f3339a.getInt();
        }

        @Override // androidx.emoji2.text.k.c
        public long d() {
            return this.f3339a.position();
        }

        @Override // androidx.emoji2.text.k.c
        public int readUnsignedShort() {
            return k.d(this.f3339a.getShort());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final long f3340a;

        /* renamed from: b  reason: collision with root package name */
        private final long f3341b;

        b(long j8, long j9) {
            this.f3340a = j8;
            this.f3341b = j9;
        }

        long a() {
            return this.f3340a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface c {
        void a(int i8);

        long b();

        int c();

        long d();

        int readUnsignedShort();
    }

    private static b a(c cVar) {
        long j8;
        cVar.a(4);
        int readUnsignedShort = cVar.readUnsignedShort();
        if (readUnsignedShort <= 100) {
            cVar.a(6);
            int i8 = 0;
            while (true) {
                if (i8 >= readUnsignedShort) {
                    j8 = -1;
                    break;
                }
                int c8 = cVar.c();
                cVar.a(4);
                j8 = cVar.b();
                cVar.a(4);
                if (1835365473 == c8) {
                    break;
                }
                i8++;
            }
            if (j8 != -1) {
                cVar.a((int) (j8 - cVar.d()));
                cVar.a(12);
                long b8 = cVar.b();
                for (int i9 = 0; i9 < b8; i9++) {
                    int c9 = cVar.c();
                    long b9 = cVar.b();
                    long b10 = cVar.b();
                    if (1164798569 == c9 || 1701669481 == c9) {
                        return new b(b9 + j8, b10);
                    }
                }
            }
            throw new IOException("Cannot read metadata.");
        }
        throw new IOException("Cannot read metadata.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static o1.b b(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position((int) a(new a(duplicate)).a());
        return o1.b.h(duplicate);
    }

    static long c(int i8) {
        return i8 & 4294967295L;
    }

    static int d(short s8) {
        return s8 & 65535;
    }
}
