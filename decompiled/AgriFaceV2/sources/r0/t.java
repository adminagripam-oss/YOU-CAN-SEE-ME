package r0;

import android.net.Uri;
/* loaded from: classes.dex */
public abstract class t {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static t b(Uri uri) {
        g1.e.k(uri, "OutputUri cannot be null.");
        return new h(uri);
    }

    public abstract Uri a();
}
