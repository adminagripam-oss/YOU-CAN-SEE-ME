package u5;

import java.util.Map;
/* loaded from: classes.dex */
public class s {

    /* renamed from: o  reason: collision with root package name */
    private static final Boolean f12232o;

    /* renamed from: p  reason: collision with root package name */
    private static final Boolean f12233p;

    /* renamed from: q  reason: collision with root package name */
    private static final Boolean f12234q;

    /* renamed from: r  reason: collision with root package name */
    private static final Boolean f12235r;

    /* renamed from: s  reason: collision with root package name */
    private static final Boolean f12236s;

    /* renamed from: a  reason: collision with root package name */
    private final String f12237a;

    /* renamed from: b  reason: collision with root package name */
    private final String f12238b;

    /* renamed from: c  reason: collision with root package name */
    private final String f12239c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f12240d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f12241e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f12242f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f12243g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f12244h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f12245i;

    /* renamed from: j  reason: collision with root package name */
    private final String f12246j;

    /* renamed from: k  reason: collision with root package name */
    private final String f12247k;

    /* renamed from: l  reason: collision with root package name */
    private final String f12248l;

    /* renamed from: m  reason: collision with root package name */
    private final String f12249m;

    /* renamed from: n  reason: collision with root package name */
    private final String f12250n;

    static {
        Boolean bool = Boolean.FALSE;
        f12232o = bool;
        f12233p = Boolean.TRUE;
        f12234q = bool;
        f12235r = bool;
        f12236s = bool;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x00ed, code lost:
        if (r7.isEmpty() == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public s(java.util.Map r7) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u5.s.<init>(java.util.Map):void");
    }

    private boolean c(Map map, String str, boolean z7) {
        Object obj = map.get(str);
        return obj instanceof String ? Boolean.parseBoolean((String) obj) : z7;
    }

    private String h(Map map, String str) {
        if (map.containsKey(str)) {
            Object obj = map.get(str);
            if (obj instanceof String) {
                String str2 = (String) obj;
                if (!str2.isEmpty()) {
                    return str2;
                }
            }
            return null;
        }
        return null;
    }

    private String o(Map map, String str, String str2) {
        String h8 = h(map, str);
        return h8 != null ? h8 : str2;
    }

    private String p(Map map, String str, String str2, String str3) {
        String h8 = h(map, str);
        if (h8 != null) {
            return h8;
        }
        String h9 = h(map, str2);
        return h9 != null ? h9 : str3;
    }

    public String a() {
        return this.f12248l;
    }

    public String b() {
        return this.f12246j;
    }

    public String d() {
        String str = this.f12238b;
        return str != null ? str : this.f12237a;
    }

    public String e() {
        if (this.f12238b != null) {
            return "FlutterSecureKeyStorage:" + this.f12238b;
        }
        return "FlutterSecureKeyStorage";
    }

    public boolean f() {
        return this.f12244h;
    }

    public String g() {
        if (this.f12238b != null) {
            return "." + this.f12238b;
        }
        return "";
    }

    public String i() {
        return this.f12247k;
    }

    public String j() {
        return this.f12249m;
    }

    public String k() {
        return this.f12250n;
    }

    public String l() {
        return this.f12239c;
    }

    public String m() {
        return this.f12237a;
    }

    public String n() {
        return this.f12238b;
    }

    public boolean q() {
        return this.f12238b != null;
    }

    public boolean r() {
        return this.f12245i;
    }

    public boolean s() {
        return this.f12243g;
    }

    public boolean t() {
        return this.f12240d;
    }

    public String toString() {
        return "FlutterSecureStorageConfig{sharedPreferencesName='" + this.f12237a + "', sharedPreferencesKeyPrefix='" + this.f12239c + "', deleteOnFailure=" + this.f12240d + ", migrateOnAlgorithmChange=" + this.f12241e + ", migrateWithBackup=" + this.f12242f + ", enforceBiometrics=" + this.f12244h + ", storageNamespace='" + this.f12238b + "'}";
    }

    public boolean u() {
        return this.f12241e;
    }

    public boolean v() {
        return this.f12242f;
    }
}
