package androidx.camera.core;

import android.graphics.Rect;
import android.media.Image;
import java.nio.ByteBuffer;
import y.w0;
/* loaded from: classes.dex */
public interface n extends AutoCloseable {

    /* loaded from: classes.dex */
    public interface a {
        int a();

        int b();

        ByteBuffer c();
    }

    @Override // java.lang.AutoCloseable
    void close();

    int e();

    int getHeight();

    int getWidth();

    a[] i();

    void l(Rect rect);

    w0 p();

    Image z();
}
