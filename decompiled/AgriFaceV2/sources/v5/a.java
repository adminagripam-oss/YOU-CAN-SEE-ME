package v5;

import android.content.Context;
import java.security.Key;
import javax.crypto.Cipher;
/* loaded from: classes.dex */
public interface a {
    byte[] a(Key key);

    Key b(byte[] bArr, String str);

    Cipher c(Context context);

    void d();
}
