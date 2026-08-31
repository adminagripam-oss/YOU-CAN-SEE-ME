package v5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import javax.crypto.Cipher;
import u5.y;
/* loaded from: classes.dex */
public class p {

    /* renamed from: f  reason: collision with root package name */
    private static final e f12412f = e.RSA_ECB_PKCS1Padding;

    /* renamed from: g  reason: collision with root package name */
    private static final o f12413g = o.AES_CBC_PKCS7Padding;

    /* renamed from: a  reason: collision with root package name */
    private final e f12414a;

    /* renamed from: b  reason: collision with root package name */
    private final o f12415b;

    /* renamed from: c  reason: collision with root package name */
    private final e f12416c;

    /* renamed from: d  reason: collision with root package name */
    private final o f12417d;

    /* renamed from: e  reason: collision with root package name */
    private final u5.s f12418e;

    public p(y yVar, String str, String str2, u5.s sVar) {
        o oVar;
        this.f12418e = sVar;
        String f8 = yVar.f("FlutterSecureSAlgorithmKey", null);
        String f9 = yVar.f("FlutterSecureSAlgorithmStorage", null);
        if (f8 == null || f9 == null) {
            this.f12414a = f12412f;
            oVar = f12413g;
        } else {
            this.f12414a = e.e(f8);
            oVar = o.e(f9);
        }
        this.f12415b = oVar;
        o e8 = o.e(str2);
        int i8 = e8.f12411f;
        int i9 = Build.VERSION.SDK_INT;
        this.f12417d = i8 > i9 ? f12413g : e8;
        e e9 = e.e(str);
        this.f12416c = e9.f12400f > i9 ? f12412f : e9;
        if ((f8 == null || f9 == null) && !sVar.v()) {
            SharedPreferences.Editor b8 = yVar.b();
            i(b8);
            b8.apply();
        }
    }

    private boolean g(a aVar) {
        return aVar instanceof j;
    }

    public boolean a() {
        return this.f12414a != this.f12416c;
    }

    m b(Context context, a aVar, Cipher cipher, o oVar) {
        if (oVar == o.AES_GCM_NoPadding) {
            return g(aVar) ? new s(context, aVar, cipher, this.f12418e) : new t(context, aVar, cipher, this.f12418e);
        }
        q qVar = oVar.f12410e;
        if (qVar != null) {
            return qVar.a(context, aVar, cipher, this.f12418e);
        }
        throw new Exception("No implementation available for algorithm: " + oVar.name());
    }

    public a c(Context context) {
        return this.f12416c.f12399e.a(context, this.f12418e);
    }

    public m d(Context context, Cipher cipher) {
        return b(context, this.f12416c.f12399e.a(context, this.f12418e), cipher, this.f12417d);
    }

    public a e(Context context) {
        return this.f12414a.f12399e.a(context, this.f12418e);
    }

    public m f(Context context, Cipher cipher) {
        return b(context, this.f12414a.f12399e.a(context, this.f12418e), cipher, this.f12415b);
    }

    public boolean h() {
        return (this.f12414a == this.f12416c && this.f12415b == this.f12417d) ? false : true;
    }

    public void i(SharedPreferences.Editor editor) {
        editor.putString("FlutterSecureSAlgorithmKey", this.f12416c.name());
        editor.putString("FlutterSecureSAlgorithmStorage", this.f12417d.name());
    }
}
