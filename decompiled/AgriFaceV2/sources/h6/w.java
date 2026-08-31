package h6;

import android.os.Bundle;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLCredentialContract;
import i6.j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class w {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7437a;

    /* renamed from: b  reason: collision with root package name */
    private f f7438b;

    /* renamed from: c  reason: collision with root package name */
    final j.c f7439c;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            Bundle bundle;
            if (w.this.f7438b == null) {
                return;
            }
            String str = iVar.f7535a;
            Object obj = iVar.f7536b;
            z5.b.f("TextInputChannel", "Received '" + str + "' message.");
            str.hashCode();
            char c8 = 65535;
            switch (str.hashCode()) {
                case -1779068172:
                    if (str.equals("TextInput.setPlatformViewClient")) {
                        c8 = 0;
                        break;
                    }
                    break;
                case -1015421462:
                    if (str.equals("TextInput.setEditingState")) {
                        c8 = 1;
                        break;
                    }
                    break;
                case -37561188:
                    if (str.equals("TextInput.setClient")) {
                        c8 = 2;
                        break;
                    }
                    break;
                case 270476819:
                    if (str.equals("TextInput.hide")) {
                        c8 = 3;
                        break;
                    }
                    break;
                case 270803918:
                    if (str.equals("TextInput.show")) {
                        c8 = 4;
                        break;
                    }
                    break;
                case 649192816:
                    if (str.equals("TextInput.sendAppPrivateCommand")) {
                        c8 = 5;
                        break;
                    }
                    break;
                case 1204752139:
                    if (str.equals("TextInput.setEditableSizeAndTransform")) {
                        c8 = 6;
                        break;
                    }
                    break;
                case 1727570905:
                    if (str.equals("TextInput.finishAutofillContext")) {
                        c8 = 7;
                        break;
                    }
                    break;
                case 1904427655:
                    if (str.equals("TextInput.clearClient")) {
                        c8 = '\b';
                        break;
                    }
                    break;
                case 2113369584:
                    if (str.equals("TextInput.requestAutofill")) {
                        c8 = '\t';
                        break;
                    }
                    break;
            }
            try {
                switch (c8) {
                    case 0:
                        JSONObject jSONObject = (JSONObject) obj;
                        w.this.f7438b.d(jSONObject.getInt("platformViewId"), jSONObject.optBoolean("usesVirtualDisplay", false));
                        dVar.success(null);
                        return;
                    case 1:
                        w.this.f7438b.j(e.a((JSONObject) obj));
                        dVar.success(null);
                        return;
                    case 2:
                        try {
                            JSONArray jSONArray = (JSONArray) obj;
                            w.this.f7438b.h(jSONArray.getInt(0), b.a(jSONArray.getJSONObject(1)));
                            dVar.success(null);
                            return;
                        } catch (NoSuchFieldException e8) {
                            e = e8;
                            dVar.error("error", e.getMessage(), null);
                            return;
                        }
                    case 3:
                        w.this.f7438b.i();
                        break;
                    case 4:
                        w.this.f7438b.a();
                        break;
                    case 5:
                        JSONObject jSONObject2 = (JSONObject) obj;
                        String string = jSONObject2.getString("action");
                        String string2 = jSONObject2.getString("data");
                        if (string2 == null || string2.isEmpty()) {
                            bundle = null;
                        } else {
                            bundle = new Bundle();
                            bundle.putString("data", string2);
                        }
                        w.this.f7438b.c(string, bundle);
                        dVar.success(null);
                        return;
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        JSONObject jSONObject3 = (JSONObject) obj;
                        double d8 = jSONObject3.getDouble("width");
                        double d9 = jSONObject3.getDouble("height");
                        JSONArray jSONArray2 = jSONObject3.getJSONArray("transform");
                        double[] dArr = new double[16];
                        for (int i8 = 0; i8 < 16; i8++) {
                            dArr[i8] = jSONArray2.getDouble(i8);
                        }
                        w.this.f7438b.e(d8, d9, dArr);
                        dVar.success(null);
                        return;
                    case 7:
                        w.this.f7438b.g(((Boolean) obj).booleanValue());
                        break;
                    case '\b':
                        w.this.f7438b.b();
                        break;
                    case '\t':
                        w.this.f7438b.f();
                        break;
                    default:
                        dVar.notImplemented();
                        return;
                }
                dVar.success(null);
            } catch (JSONException e9) {
                e = e9;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f7441a;

        /* renamed from: b  reason: collision with root package name */
        public final boolean f7442b;

        /* renamed from: c  reason: collision with root package name */
        public final boolean f7443c;

        /* renamed from: d  reason: collision with root package name */
        public final boolean f7444d;

        /* renamed from: e  reason: collision with root package name */
        public final boolean f7445e;

        /* renamed from: f  reason: collision with root package name */
        public final d f7446f;

        /* renamed from: g  reason: collision with root package name */
        public final c f7447g;

        /* renamed from: h  reason: collision with root package name */
        public final Integer f7448h;

        /* renamed from: i  reason: collision with root package name */
        public final String f7449i;

        /* renamed from: j  reason: collision with root package name */
        public final a f7450j;

        /* renamed from: k  reason: collision with root package name */
        public final String[] f7451k;

        /* renamed from: l  reason: collision with root package name */
        public final b[] f7452l;

        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            public final String f7453a;

            /* renamed from: b  reason: collision with root package name */
            public final String[] f7454b;

            /* renamed from: c  reason: collision with root package name */
            public final e f7455c;

            /* renamed from: d  reason: collision with root package name */
            public final String f7456d;

            public a(String str, String[] strArr, String str2, e eVar) {
                this.f7453a = str;
                this.f7454b = strArr;
                this.f7456d = str2;
                this.f7455c = eVar;
            }

            public static a a(JSONObject jSONObject) {
                String string = jSONObject.getString("uniqueIdentifier");
                JSONArray jSONArray = jSONObject.getJSONArray("hints");
                String string2 = jSONObject.isNull("hintText") ? null : jSONObject.getString("hintText");
                JSONObject jSONObject2 = jSONObject.getJSONObject("editingValue");
                String[] strArr = new String[jSONArray.length()];
                for (int i8 = 0; i8 < jSONArray.length(); i8++) {
                    strArr[i8] = b(jSONArray.getString(i8));
                }
                return new a(string, strArr, string2, e.a(jSONObject2));
            }

            private static String b(String str) {
                str.hashCode();
                char c8 = 65535;
                switch (str.hashCode()) {
                    case -2058889126:
                        if (str.equals("birthdayYear")) {
                            c8 = 0;
                            break;
                        }
                        break;
                    case -1917283616:
                        if (str.equals("oneTimeCode")) {
                            c8 = 1;
                            break;
                        }
                        break;
                    case -1844815832:
                        if (str.equals("creditCardExpirationMonth")) {
                            c8 = 2;
                            break;
                        }
                        break;
                    case -1825589953:
                        if (str.equals("telephoneNumberNational")) {
                            c8 = 3;
                            break;
                        }
                        break;
                    case -1821235109:
                        if (str.equals("newPassword")) {
                            c8 = 4;
                            break;
                        }
                        break;
                    case -1757573738:
                        if (str.equals("creditCardSecurityCode")) {
                            c8 = 5;
                            break;
                        }
                        break;
                    case -1682373820:
                        if (str.equals("creditCardExpirationDay")) {
                            c8 = 6;
                            break;
                        }
                        break;
                    case -1658955742:
                        if (str.equals("fullStreetAddress")) {
                            c8 = 7;
                            break;
                        }
                        break;
                    case -1567118045:
                        if (str.equals("telephoneNumberDevice")) {
                            c8 = '\b';
                            break;
                        }
                        break;
                    case -1476752575:
                        if (str.equals("countryName")) {
                            c8 = '\t';
                            break;
                        }
                        break;
                    case -1413737489:
                        if (str.equals("middleInitial")) {
                            c8 = '\n';
                            break;
                        }
                        break;
                    case -1377792129:
                        if (str.equals("addressCity")) {
                            c8 = 11;
                            break;
                        }
                        break;
                    case -1249512767:
                        if (str.equals("gender")) {
                            c8 = '\f';
                            break;
                        }
                        break;
                    case -1186060294:
                        if (str.equals("postalAddressExtendedPostalCode")) {
                            c8 = '\r';
                            break;
                        }
                        break;
                    case -1151034798:
                        if (str.equals("creditCardNumber")) {
                            c8 = 14;
                            break;
                        }
                        break;
                    case -835992323:
                        if (str.equals("namePrefix")) {
                            c8 = 15;
                            break;
                        }
                        break;
                    case -818219584:
                        if (str.equals("middleName")) {
                            c8 = 16;
                            break;
                        }
                        break;
                    case -747304516:
                        if (str.equals("nameSuffix")) {
                            c8 = 17;
                            break;
                        }
                        break;
                    case -613980922:
                        if (str.equals("creditCardExpirationDate")) {
                            c8 = 18;
                            break;
                        }
                        break;
                    case -613352043:
                        if (str.equals("creditCardExpirationYear")) {
                            c8 = 19;
                            break;
                        }
                        break;
                    case -549230602:
                        if (str.equals("telephoneNumberCountryCode")) {
                            c8 = 20;
                            break;
                        }
                        break;
                    case -265713450:
                        if (str.equals(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME)) {
                            c8 = 21;
                            break;
                        }
                        break;
                    case 3373707:
                        if (str.equals("name")) {
                            c8 = 22;
                            break;
                        }
                        break;
                    case 96619420:
                        if (str.equals("email")) {
                            c8 = 23;
                            break;
                        }
                        break;
                    case 253202685:
                        if (str.equals("addressState")) {
                            c8 = 24;
                            break;
                        }
                        break;
                    case 588174851:
                        if (str.equals("birthdayMonth")) {
                            c8 = 25;
                            break;
                        }
                        break;
                    case 798554127:
                        if (str.equals("familyName")) {
                            c8 = 26;
                            break;
                        }
                        break;
                    case 892233837:
                        if (str.equals("telephoneNumber")) {
                            c8 = 27;
                            break;
                        }
                        break;
                    case 991032982:
                        if (str.equals("newUsername")) {
                            c8 = 28;
                            break;
                        }
                        break;
                    case 1069376125:
                        if (str.equals("birthday")) {
                            c8 = 29;
                            break;
                        }
                        break;
                    case 1216985755:
                        if (str.equals(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD)) {
                            c8 = 30;
                            break;
                        }
                        break;
                    case 1469046696:
                        if (str.equals("givenName")) {
                            c8 = 31;
                            break;
                        }
                        break;
                    case 1662667945:
                        if (str.equals("postalAddress")) {
                            c8 = ' ';
                            break;
                        }
                        break;
                    case 1921869058:
                        if (str.equals("postalAddressExtended")) {
                            c8 = '!';
                            break;
                        }
                        break;
                    case 2011152728:
                        if (str.equals("postalCode")) {
                            c8 = '\"';
                            break;
                        }
                        break;
                    case 2011773919:
                        if (str.equals("birthdayDay")) {
                            c8 = '#';
                            break;
                        }
                        break;
                }
                switch (c8) {
                    case 0:
                        return "birthDateYear";
                    case 1:
                        return "smsOTPCode";
                    case 2:
                        return "creditCardExpirationMonth";
                    case 3:
                        return "phoneNational";
                    case 4:
                        return "newPassword";
                    case 5:
                        return "creditCardSecurityCode";
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        return "creditCardExpirationDay";
                    case 7:
                        return "streetAddress";
                    case '\b':
                        return "phoneNumberDevice";
                    case '\t':
                        return "addressCountry";
                    case '\n':
                        return "personMiddleInitial";
                    case 11:
                        return "addressLocality";
                    case '\f':
                        return "gender";
                    case '\r':
                        return "extendedPostalCode";
                    case 14:
                        return "creditCardNumber";
                    case 15:
                        return "personNamePrefix";
                    case 16:
                        return "personMiddleName";
                    case 17:
                        return "personNameSuffix";
                    case 18:
                        return "creditCardExpirationDate";
                    case 19:
                        return "creditCardExpirationYear";
                    case 20:
                        return "phoneCountryCode";
                    case 21:
                        return URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME;
                    case 22:
                        return "personName";
                    case 23:
                        return "emailAddress";
                    case 24:
                        return "addressRegion";
                    case 25:
                        return "birthDateMonth";
                    case 26:
                        return "personFamilyName";
                    case 27:
                        return "phoneNumber";
                    case 28:
                        return "newUsername";
                    case 29:
                        return "birthDateFull";
                    case 30:
                        return URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD;
                    case 31:
                        return "personGivenName";
                    case ' ':
                        return "postalAddress";
                    case '!':
                        return "extendedAddress";
                    case '\"':
                        return "postalCode";
                    case '#':
                        return "birthDateDay";
                    default:
                        return str;
                }
            }
        }

        public b(boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, d dVar, c cVar, Integer num, String str, a aVar, String[] strArr, b[] bVarArr) {
            this.f7441a = z7;
            this.f7442b = z8;
            this.f7443c = z9;
            this.f7444d = z10;
            this.f7445e = z11;
            this.f7446f = dVar;
            this.f7447g = cVar;
            this.f7448h = num;
            this.f7449i = str;
            this.f7450j = aVar;
            this.f7451k = strArr;
            this.f7452l = bVarArr;
        }

        public static b a(JSONObject jSONObject) {
            b[] bVarArr;
            String string = jSONObject.getString("inputAction");
            if (string != null) {
                if (jSONObject.isNull("fields")) {
                    bVarArr = null;
                } else {
                    JSONArray jSONArray = jSONObject.getJSONArray("fields");
                    int length = jSONArray.length();
                    b[] bVarArr2 = new b[length];
                    for (int i8 = 0; i8 < length; i8++) {
                        bVarArr2[i8] = a(jSONArray.getJSONObject(i8));
                    }
                    bVarArr = bVarArr2;
                }
                Integer b8 = b(string);
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray2 = jSONObject.isNull("contentCommitMimeTypes") ? null : jSONObject.getJSONArray("contentCommitMimeTypes");
                if (jSONArray2 != null) {
                    for (int i9 = 0; i9 < jSONArray2.length(); i9++) {
                        arrayList.add(jSONArray2.optString(i9));
                    }
                }
                return new b(jSONObject.optBoolean("obscureText"), jSONObject.optBoolean("autocorrect", true), jSONObject.optBoolean("enableSuggestions"), jSONObject.optBoolean("enableIMEPersonalizedLearning"), jSONObject.optBoolean("enableDeltaModel"), d.e(jSONObject.getString("textCapitalization")), c.a(jSONObject.getJSONObject("inputType")), b8, jSONObject.isNull("actionLabel") ? null : jSONObject.getString("actionLabel"), jSONObject.isNull("autofill") ? null : a.a(jSONObject.getJSONObject("autofill")), (String[]) arrayList.toArray(new String[arrayList.size()]), bVarArr);
            }
            throw new JSONException("Configuration JSON missing 'inputAction' property.");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0073, code lost:
            if (r12.equals("TextInputAction.done") == false) goto L3;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static java.lang.Integer b(java.lang.String r12) {
            /*
                r12.hashCode()
                int r0 = r12.hashCode()
                r1 = 7
                r2 = 6
                r3 = 5
                r4 = 4
                r5 = 3
                r6 = 2
                r7 = 1
                java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
                r9 = 0
                java.lang.Integer r10 = java.lang.Integer.valueOf(r9)
                r11 = -1
                switch(r0) {
                    case -810971940: goto L76;
                    case -737377923: goto L6d;
                    case -737089298: goto L62;
                    case -737080013: goto L57;
                    case -736940669: goto L4c;
                    case 469250275: goto L41;
                    case 1241689507: goto L36;
                    case 1539450297: goto L2b;
                    case 2110497650: goto L1e;
                    default: goto L1b;
                }
            L1b:
                r7 = r11
                goto L80
            L1e:
                java.lang.String r0 = "TextInputAction.previous"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L27
                goto L1b
            L27:
                r7 = 8
                goto L80
            L2b:
                java.lang.String r0 = "TextInputAction.newline"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L34
                goto L1b
            L34:
                r7 = r1
                goto L80
            L36:
                java.lang.String r0 = "TextInputAction.go"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L3f
                goto L1b
            L3f:
                r7 = r2
                goto L80
            L41:
                java.lang.String r0 = "TextInputAction.search"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L4a
                goto L1b
            L4a:
                r7 = r3
                goto L80
            L4c:
                java.lang.String r0 = "TextInputAction.send"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L55
                goto L1b
            L55:
                r7 = r4
                goto L80
            L57:
                java.lang.String r0 = "TextInputAction.none"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L60
                goto L1b
            L60:
                r7 = r5
                goto L80
            L62:
                java.lang.String r0 = "TextInputAction.next"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L6b
                goto L1b
            L6b:
                r7 = r6
                goto L80
            L6d:
                java.lang.String r0 = "TextInputAction.done"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L80
                goto L1b
            L76:
                java.lang.String r0 = "TextInputAction.unspecified"
                boolean r12 = r12.equals(r0)
                if (r12 != 0) goto L7f
                goto L1b
            L7f:
                r7 = r9
            L80:
                switch(r7) {
                    case 0: goto La4;
                    case 1: goto L9f;
                    case 2: goto L9a;
                    case 3: goto L99;
                    case 4: goto L94;
                    case 5: goto L8f;
                    case 6: goto L8a;
                    case 7: goto L89;
                    case 8: goto L84;
                    default: goto L83;
                }
            L83:
                return r10
            L84:
                java.lang.Integer r12 = java.lang.Integer.valueOf(r1)
                return r12
            L89:
                return r8
            L8a:
                java.lang.Integer r12 = java.lang.Integer.valueOf(r6)
                return r12
            L8f:
                java.lang.Integer r12 = java.lang.Integer.valueOf(r5)
                return r12
            L94:
                java.lang.Integer r12 = java.lang.Integer.valueOf(r4)
                return r12
            L99:
                return r8
            L9a:
                java.lang.Integer r12 = java.lang.Integer.valueOf(r3)
                return r12
            L9f:
                java.lang.Integer r12 = java.lang.Integer.valueOf(r2)
                return r12
            La4:
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: h6.w.b.b(java.lang.String):java.lang.Integer");
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final g f7457a;

        /* renamed from: b  reason: collision with root package name */
        public final boolean f7458b;

        /* renamed from: c  reason: collision with root package name */
        public final boolean f7459c;

        public c(g gVar, boolean z7, boolean z8) {
            this.f7457a = gVar;
            this.f7458b = z7;
            this.f7459c = z8;
        }

        public static c a(JSONObject jSONObject) {
            return new c(g.e(jSONObject.getString("name")), jSONObject.optBoolean("signed", false), jSONObject.optBoolean("decimal", false));
        }
    }

    /* loaded from: classes.dex */
    public enum d {
        CHARACTERS("TextCapitalization.characters"),
        WORDS("TextCapitalization.words"),
        SENTENCES("TextCapitalization.sentences"),
        NONE("TextCapitalization.none");
        

        /* renamed from: e  reason: collision with root package name */
        private final String f7465e;

        d(String str) {
            this.f7465e = str;
        }

        static d e(String str) {
            d[] values;
            for (d dVar : values()) {
                if (dVar.f7465e.equals(str)) {
                    return dVar;
                }
            }
            throw new NoSuchFieldException("No such TextCapitalization: " + str);
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final String f7466a;

        /* renamed from: b  reason: collision with root package name */
        public final int f7467b;

        /* renamed from: c  reason: collision with root package name */
        public final int f7468c;

        /* renamed from: d  reason: collision with root package name */
        public final int f7469d;

        /* renamed from: e  reason: collision with root package name */
        public final int f7470e;

        public e(String str, int i8, int i9, int i10, int i11) {
            if (!(i8 == -1 && i9 == -1) && (i8 < 0 || i9 < 0)) {
                throw new IndexOutOfBoundsException("invalid selection: (" + String.valueOf(i8) + ", " + String.valueOf(i9) + ")");
            } else if (!(i10 == -1 && i11 == -1) && (i10 < 0 || i10 > i11)) {
                throw new IndexOutOfBoundsException("invalid composing range: (" + String.valueOf(i10) + ", " + String.valueOf(i11) + ")");
            } else if (i11 > str.length()) {
                throw new IndexOutOfBoundsException("invalid composing start: " + String.valueOf(i10));
            } else if (i8 > str.length()) {
                throw new IndexOutOfBoundsException("invalid selection start: " + String.valueOf(i8));
            } else if (i9 > str.length()) {
                throw new IndexOutOfBoundsException("invalid selection end: " + String.valueOf(i9));
            } else {
                this.f7466a = str;
                this.f7467b = i8;
                this.f7468c = i9;
                this.f7469d = i10;
                this.f7470e = i11;
            }
        }

        public static e a(JSONObject jSONObject) {
            return new e(jSONObject.getString("text"), jSONObject.getInt("selectionBase"), jSONObject.getInt("selectionExtent"), jSONObject.getInt("composingBase"), jSONObject.getInt("composingExtent"));
        }

        public boolean b() {
            int i8 = this.f7469d;
            return i8 >= 0 && this.f7470e > i8;
        }

        public boolean c() {
            return this.f7467b >= 0;
        }
    }

    /* loaded from: classes.dex */
    public interface f {
        void a();

        void b();

        void c(String str, Bundle bundle);

        void d(int i8, boolean z7);

        void e(double d8, double d9, double[] dArr);

        void f();

        void g(boolean z7);

        void h(int i8, b bVar);

        void i();

        void j(e eVar);
    }

    /* loaded from: classes.dex */
    public enum g {
        TEXT("TextInputType.text"),
        DATETIME("TextInputType.datetime"),
        NAME("TextInputType.name"),
        POSTAL_ADDRESS("TextInputType.address"),
        NUMBER("TextInputType.number"),
        PHONE("TextInputType.phone"),
        MULTILINE("TextInputType.multiline"),
        EMAIL_ADDRESS("TextInputType.emailAddress"),
        URL("TextInputType.url"),
        VISIBLE_PASSWORD("TextInputType.visiblePassword"),
        NONE("TextInputType.none");
        

        /* renamed from: e  reason: collision with root package name */
        private final String f7483e;

        g(String str) {
            this.f7483e = str;
        }

        static g e(String str) {
            g[] values;
            for (g gVar : values()) {
                if (gVar.f7483e.equals(str)) {
                    return gVar;
                }
            }
            throw new NoSuchFieldException("No such TextInputType: " + str);
        }
    }

    public w(a6.a aVar) {
        a aVar2 = new a();
        this.f7439c = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/textinput", i6.f.f7534a);
        this.f7437a = jVar;
        jVar.e(aVar2);
    }

    private static HashMap c(ArrayList arrayList) {
        HashMap hashMap = new HashMap();
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put(((io.flutter.plugin.editing.k) it.next()).b());
        }
        hashMap.put("deltas", jSONArray);
        return hashMap;
    }

    private static HashMap d(String str, int i8, int i9, int i10, int i11) {
        HashMap hashMap = new HashMap();
        hashMap.put("text", str);
        hashMap.put("selectionBase", Integer.valueOf(i8));
        hashMap.put("selectionExtent", Integer.valueOf(i9));
        hashMap.put("composingBase", Integer.valueOf(i10));
        hashMap.put("composingExtent", Integer.valueOf(i11));
        return hashMap;
    }

    public void b(int i8, Map map) {
        z5.b.f("TextInputChannel", "Sending 'commitContent' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.commitContent", map));
    }

    public void e(int i8) {
        z5.b.f("TextInputChannel", "Sending 'done' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.done"));
    }

    public void f(int i8) {
        z5.b.f("TextInputChannel", "Sending 'go' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.go"));
    }

    public void g(int i8) {
        z5.b.f("TextInputChannel", "Sending 'newline' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.newline"));
    }

    public void h(int i8) {
        z5.b.f("TextInputChannel", "Sending 'next' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.next"));
    }

    public void i(int i8, String str, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("action", str);
        if (bundle != null) {
            HashMap hashMap2 = new HashMap();
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj instanceof byte[]) {
                    hashMap2.put(str2, bundle.getByteArray(str2));
                } else if (obj instanceof Byte) {
                    hashMap2.put(str2, Byte.valueOf(bundle.getByte(str2)));
                } else if (obj instanceof char[]) {
                    hashMap2.put(str2, bundle.getCharArray(str2));
                } else if (obj instanceof Character) {
                    hashMap2.put(str2, Character.valueOf(bundle.getChar(str2)));
                } else if (obj instanceof CharSequence[]) {
                    hashMap2.put(str2, bundle.getCharSequenceArray(str2));
                } else if (obj instanceof CharSequence) {
                    hashMap2.put(str2, bundle.getCharSequence(str2));
                } else if (obj instanceof float[]) {
                    hashMap2.put(str2, bundle.getFloatArray(str2));
                } else if (obj instanceof Float) {
                    hashMap2.put(str2, Float.valueOf(bundle.getFloat(str2)));
                }
            }
            hashMap.put("data", hashMap2);
        }
        this.f7437a.c("TextInputClient.performPrivateCommand", Arrays.asList(Integer.valueOf(i8), hashMap));
    }

    public void j(int i8) {
        z5.b.f("TextInputChannel", "Sending 'previous' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.previous"));
    }

    public void k() {
        this.f7437a.c("TextInputClient.requestExistingInputState", null);
    }

    public void l(int i8) {
        z5.b.f("TextInputChannel", "Sending 'search' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.search"));
    }

    public void m(int i8) {
        z5.b.f("TextInputChannel", "Sending 'send' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.send"));
    }

    public void n(f fVar) {
        this.f7438b = fVar;
    }

    public void o(int i8) {
        z5.b.f("TextInputChannel", "Sending 'unspecified' message.");
        this.f7437a.c("TextInputClient.performAction", Arrays.asList(Integer.valueOf(i8), "TextInputAction.unspecified"));
    }

    public void p(int i8, String str, int i9, int i10, int i11, int i12) {
        z5.b.f("TextInputChannel", "Sending message to update editing state: \nText: " + str + "\nSelection start: " + i9 + "\nSelection end: " + i10 + "\nComposing start: " + i11 + "\nComposing end: " + i12);
        this.f7437a.c("TextInputClient.updateEditingState", Arrays.asList(Integer.valueOf(i8), d(str, i9, i10, i11, i12)));
    }

    public void q(int i8, ArrayList arrayList) {
        z5.b.f("TextInputChannel", "Sending message to update editing state with deltas: \nNumber of deltas: " + arrayList.size());
        this.f7437a.c("TextInputClient.updateEditingStateWithDeltas", Arrays.asList(Integer.valueOf(i8), c(arrayList)));
    }

    public void r(int i8, HashMap hashMap) {
        z5.b.f("TextInputChannel", "Sending message to update editing state for " + String.valueOf(hashMap.size()) + " field(s).");
        HashMap hashMap2 = new HashMap();
        for (Map.Entry entry : hashMap.entrySet()) {
            e eVar = (e) entry.getValue();
            hashMap2.put((String) entry.getKey(), d(eVar.f7466a, eVar.f7467b, eVar.f7468c, -1, -1));
        }
        this.f7437a.c("TextInputClient.updateEditingStateWithTag", Arrays.asList(Integer.valueOf(i8), hashMap2));
    }
}
