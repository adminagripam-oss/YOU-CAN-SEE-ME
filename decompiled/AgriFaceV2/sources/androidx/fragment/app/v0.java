package androidx.fragment.app;

import android.util.Log;
import java.io.Writer;
/* loaded from: classes.dex */
final class v0 extends Writer {

    /* renamed from: e  reason: collision with root package name */
    private final String f3685e;

    /* renamed from: f  reason: collision with root package name */
    private StringBuilder f3686f = new StringBuilder(128);

    /* JADX INFO: Access modifiers changed from: package-private */
    public v0(String str) {
        this.f3685e = str;
    }

    private void b() {
        if (this.f3686f.length() > 0) {
            Log.d(this.f3685e, this.f3686f.toString());
            StringBuilder sb = this.f3686f;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        b();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        b();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i8, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            char c8 = cArr[i8 + i10];
            if (c8 == '\n') {
                b();
            } else {
                this.f3686f.append(c8);
            }
        }
    }
}
