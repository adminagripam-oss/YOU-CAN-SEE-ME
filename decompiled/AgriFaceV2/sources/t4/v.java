package t4;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public abstract class v {

    /* renamed from: a  reason: collision with root package name */
    public static final a5.a f11883a = a5.a.a(new byte[0]);

    public static final a5.a a(int i8) {
        return a5.a.a(ByteBuffer.allocate(5).put((byte) 0).putInt(i8).array());
    }

    public static final a5.a b(int i8) {
        return a5.a.a(ByteBuffer.allocate(5).put((byte) 1).putInt(i8).array());
    }
}
