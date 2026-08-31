package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import k3.q;
import l3.c;
import org.json.JSONArray;
import org.json.JSONObject;
import q3.b;
import q3.d;
@Deprecated
/* loaded from: classes.dex */
public class GoogleSignInAccount extends l3.a implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new a();

    /* renamed from: n  reason: collision with root package name */
    public static final b f4941n = d.a();

    /* renamed from: a  reason: collision with root package name */
    final int f4942a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4943b;

    /* renamed from: c  reason: collision with root package name */
    private final String f4944c;

    /* renamed from: d  reason: collision with root package name */
    private final String f4945d;

    /* renamed from: e  reason: collision with root package name */
    private final String f4946e;

    /* renamed from: f  reason: collision with root package name */
    private final Uri f4947f;

    /* renamed from: g  reason: collision with root package name */
    private String f4948g;

    /* renamed from: h  reason: collision with root package name */
    private final long f4949h;

    /* renamed from: i  reason: collision with root package name */
    private final String f4950i;

    /* renamed from: j  reason: collision with root package name */
    final List f4951j;

    /* renamed from: k  reason: collision with root package name */
    private final String f4952k;

    /* renamed from: l  reason: collision with root package name */
    private final String f4953l;

    /* renamed from: m  reason: collision with root package name */
    private final Set f4954m = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInAccount(int i8, String str, String str2, String str3, String str4, Uri uri, String str5, long j8, String str6, List list, String str7, String str8) {
        this.f4942a = i8;
        this.f4943b = str;
        this.f4944c = str2;
        this.f4945d = str3;
        this.f4946e = str4;
        this.f4947f = uri;
        this.f4948g = str5;
        this.f4949h = j8;
        this.f4950i = str6;
        this.f4951j = list;
        this.f4952k = str7;
        this.f4953l = str8;
    }

    public static GoogleSignInAccount j(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Long l8, String str7, Set set) {
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, l8.longValue(), q.e(str7), new ArrayList((Collection) q.g(set)), str5, str6);
    }

    public static GoogleSignInAccount k(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl");
        Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i8 = 0; i8 < length; i8++) {
            hashSet.add(new Scope(jSONArray.getString(i8)));
        }
        GoogleSignInAccount j8 = j(jSONObject.optString("id"), jSONObject.has("tokenId") ? jSONObject.optString("tokenId") : null, jSONObject.has("email") ? jSONObject.optString("email") : null, jSONObject.has("displayName") ? jSONObject.optString("displayName") : null, jSONObject.has("givenName") ? jSONObject.optString("givenName") : null, jSONObject.has("familyName") ? jSONObject.optString("familyName") : null, parse, Long.valueOf(parseLong), jSONObject.getString("obfuscatedIdentifier"), hashSet);
        j8.f4948g = jSONObject.has("serverAuthCode") ? jSONObject.optString("serverAuthCode") : null;
        return j8;
    }

    public String a() {
        return this.f4946e;
    }

    public String b() {
        return this.f4945d;
    }

    public String c() {
        return this.f4953l;
    }

    public String d() {
        return this.f4952k;
    }

    public String e() {
        return this.f4943b;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof GoogleSignInAccount) {
            GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
            return googleSignInAccount.f4950i.equals(this.f4950i) && googleSignInAccount.h().equals(h());
        }
        return false;
    }

    public String f() {
        return this.f4944c;
    }

    public Uri g() {
        return this.f4947f;
    }

    public Set h() {
        HashSet hashSet = new HashSet(this.f4951j);
        hashSet.addAll(this.f4954m);
        return hashSet;
    }

    public int hashCode() {
        return ((this.f4950i.hashCode() + 527) * 31) + h().hashCode();
    }

    public String i() {
        return this.f4948g;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = c.a(parcel);
        c.g(parcel, 1, this.f4942a);
        c.k(parcel, 2, e(), false);
        c.k(parcel, 3, f(), false);
        c.k(parcel, 4, b(), false);
        c.k(parcel, 5, a(), false);
        c.j(parcel, 6, g(), i8, false);
        c.k(parcel, 7, i(), false);
        c.i(parcel, 8, this.f4949h);
        c.k(parcel, 9, this.f4950i, false);
        c.n(parcel, 10, this.f4951j, false);
        c.k(parcel, 11, d(), false);
        c.k(parcel, 12, c(), false);
        c.b(parcel, a8);
    }
}
