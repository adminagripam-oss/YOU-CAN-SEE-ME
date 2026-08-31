package f3;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import k3.q;
import org.json.JSONException;
/* loaded from: classes.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static final Lock f6731c = new ReentrantLock();

    /* renamed from: d  reason: collision with root package name */
    private static a f6732d;

    /* renamed from: a  reason: collision with root package name */
    private final Lock f6733a = new ReentrantLock();

    /* renamed from: b  reason: collision with root package name */
    private final SharedPreferences f6734b;

    a(Context context) {
        this.f6734b = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static a a(Context context) {
        q.g(context);
        Lock lock = f6731c;
        lock.lock();
        try {
            if (f6732d == null) {
                f6732d = new a(context.getApplicationContext());
            }
            a aVar = f6732d;
            lock.unlock();
            return aVar;
        } catch (Throwable th) {
            f6731c.unlock();
            throw th;
        }
    }

    private static final String d(String str, String str2) {
        return str + ":" + str2;
    }

    public GoogleSignInAccount b() {
        String c8;
        String c9 = c("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(c9) || (c8 = c(d("googleSignInAccount", c9))) == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.k(c8);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected final String c(String str) {
        this.f6733a.lock();
        try {
            return this.f6734b.getString(str, null);
        } finally {
            this.f6733a.unlock();
        }
    }
}
