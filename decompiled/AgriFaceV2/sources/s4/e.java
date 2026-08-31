package s4;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import j4.r;
import java.io.IOException;
import y4.c0;
import y4.t;
import z4.k;
/* loaded from: classes.dex */
public final class e implements r {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences.Editor f11582a;

    /* renamed from: b  reason: collision with root package name */
    private final String f11583b;

    public e(Context context, String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.f11583b = str;
        Context applicationContext = context.getApplicationContext();
        this.f11582a = (str2 == null ? PreferenceManager.getDefaultSharedPreferences(applicationContext) : applicationContext.getSharedPreferences(str2, 0)).edit();
    }

    @Override // j4.r
    public void a(c0 c0Var) {
        if (!this.f11582a.putString(this.f11583b, k.b(c0Var.i())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    @Override // j4.r
    public void b(t tVar) {
        if (!this.f11582a.putString(this.f11583b, k.b(tVar.i())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
