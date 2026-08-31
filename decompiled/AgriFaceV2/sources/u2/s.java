package u2;

import android.content.Context;
import java.io.Closeable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class s implements Closeable {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface a {
        s a();

        a b(Context context);
    }

    abstract b3.c b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract r c();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        b().close();
    }
}
