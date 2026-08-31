package q5;

import androidx.lifecycle.i;
import androidx.lifecycle.l;
import androidx.lifecycle.u;
import f4.j;
import java.io.Closeable;
/* loaded from: classes.dex */
public interface d extends Closeable, l, i3.g {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    @u(i.a.ON_DESTROY)
    void close();

    j o(o5.a aVar);
}
