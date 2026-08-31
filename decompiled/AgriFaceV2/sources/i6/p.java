package i6;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
/* loaded from: classes.dex */
public final class p implements h {

    /* renamed from: a  reason: collision with root package name */
    private static final Charset f7552a = Charset.forName("UTF8");

    /* renamed from: b  reason: collision with root package name */
    public static final p f7553b = new p();

    private p() {
    }

    @Override // i6.h
    /* renamed from: c */
    public String b(ByteBuffer byteBuffer) {
        byte[] bArr;
        int i8;
        if (byteBuffer == null) {
            return null;
        }
        int remaining = byteBuffer.remaining();
        if (byteBuffer.hasArray()) {
            bArr = byteBuffer.array();
            i8 = byteBuffer.arrayOffset();
        } else {
            bArr = new byte[remaining];
            byteBuffer.get(bArr);
            i8 = 0;
        }
        return new String(bArr, i8, remaining, f7552a);
    }

    @Override // i6.h
    /* renamed from: d */
    public ByteBuffer a(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes(f7552a);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bytes.length);
        allocateDirect.put(bytes);
        return allocateDirect;
    }
}
