package io.flutter.embedding.android;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
public class v {

    /* renamed from: a  reason: collision with root package name */
    long f7682a;

    /* renamed from: b  reason: collision with root package name */
    b f7683b;

    /* renamed from: c  reason: collision with root package name */
    long f7684c;

    /* renamed from: d  reason: collision with root package name */
    long f7685d;

    /* renamed from: e  reason: collision with root package name */
    boolean f7686e;

    /* renamed from: f  reason: collision with root package name */
    a f7687f;

    /* renamed from: g  reason: collision with root package name */
    String f7688g;

    /* loaded from: classes.dex */
    public enum a {
        kKeyboard(0),
        kDirectionalPad(1),
        kGamepad(2),
        kJoystick(3),
        kHdmi(4);
        

        /* renamed from: e  reason: collision with root package name */
        private final long f7695e;

        a(long j8) {
            this.f7695e = j8;
        }

        public long e() {
            return this.f7695e;
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        kDown(0),
        kUp(1),
        kRepeat(2);
        

        /* renamed from: e  reason: collision with root package name */
        private long f7700e;

        b(long j8) {
            this.f7700e = j8;
        }

        public long e() {
            return this.f7700e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer a() {
        try {
            String str = this.f7688g;
            byte[] bytes = str == null ? null : str.getBytes("UTF-8");
            int length = bytes == null ? 0 : bytes.length;
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(length + 56);
            allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
            allocateDirect.putLong(length);
            allocateDirect.putLong(this.f7682a);
            allocateDirect.putLong(this.f7683b.e());
            allocateDirect.putLong(this.f7684c);
            allocateDirect.putLong(this.f7685d);
            allocateDirect.putLong(this.f7686e ? 1L : 0L);
            allocateDirect.putLong(this.f7687f.e());
            if (bytes != null) {
                allocateDirect.put(bytes);
            }
            return allocateDirect;
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError("UTF-8 not supported");
        }
    }
}
