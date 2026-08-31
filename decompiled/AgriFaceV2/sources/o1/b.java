package o1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
public final class b extends c {
    public static b h(ByteBuffer byteBuffer) {
        return i(byteBuffer, new b());
    }

    public static b i(ByteBuffer byteBuffer, b bVar) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return bVar.f(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public b f(int i8, ByteBuffer byteBuffer) {
        g(i8, byteBuffer);
        return this;
    }

    public void g(int i8, ByteBuffer byteBuffer) {
        c(i8, byteBuffer);
    }

    public a j(a aVar, int i8) {
        int b8 = b(6);
        if (b8 != 0) {
            return aVar.f(a(d(b8) + (i8 * 4)), this.f9821b);
        }
        return null;
    }

    public int k() {
        int b8 = b(6);
        if (b8 != 0) {
            return e(b8);
        }
        return 0;
    }

    public int l() {
        int b8 = b(4);
        if (b8 != 0) {
            return this.f9821b.getInt(b8 + this.f9820a);
        }
        return 0;
    }
}
