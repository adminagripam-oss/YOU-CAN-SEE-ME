package com.google.android.datatransport.cct;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import u2.f;
/* loaded from: classes.dex */
public final class a implements f {

    /* renamed from: c  reason: collision with root package name */
    static final String f4915c;

    /* renamed from: d  reason: collision with root package name */
    static final String f4916d;

    /* renamed from: e  reason: collision with root package name */
    private static final String f4917e;

    /* renamed from: f  reason: collision with root package name */
    private static final Set f4918f;

    /* renamed from: g  reason: collision with root package name */
    public static final a f4919g;

    /* renamed from: h  reason: collision with root package name */
    public static final a f4920h;

    /* renamed from: a  reason: collision with root package name */
    private final String f4921a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4922b;

    static {
        String a8 = e.a("hts/frbslgiggolai.o/0clgbthfra=snpoo", "tp:/ieaeogn.ogepscmvc/o/ac?omtjo_rt3");
        f4915c = a8;
        String a9 = e.a("hts/frbslgigp.ogepscmv/ieo/eaybtho", "tp:/ieaeogn-agolai.o/1frlglgc/aclg");
        f4916d = a9;
        String a10 = e.a("AzSCki82AwsLzKd5O8zo", "IayckHiZRO1EFl1aGoK");
        f4917e = a10;
        f4918f = Collections.unmodifiableSet(new HashSet(Arrays.asList(s2.b.b("proto"), s2.b.b("json"))));
        f4919g = new a(a8, null);
        f4920h = new a(a9, a10);
    }

    public a(String str, String str2) {
        this.f4921a = str;
        this.f4922b = str2;
    }

    public static a d(byte[] bArr) {
        String str = new String(bArr, Charset.forName("UTF-8"));
        if (str.startsWith("1$")) {
            String[] split = str.substring(2).split(Pattern.quote("\\"), 2);
            if (split.length == 2) {
                String str2 = split[0];
                if (str2.isEmpty()) {
                    throw new IllegalArgumentException("Missing endpoint in CCTDestination extras");
                }
                String str3 = split[1];
                if (str3.isEmpty()) {
                    str3 = null;
                }
                return new a(str2, str3);
            }
            throw new IllegalArgumentException("Extra is not a valid encoded LegacyFlgDestination");
        }
        throw new IllegalArgumentException("Version marker missing from extras");
    }

    @Override // u2.f
    public Set a() {
        return f4918f;
    }

    @Override // u2.e
    public byte[] b() {
        return c();
    }

    public byte[] c() {
        String str = this.f4922b;
        if (str == null && this.f4921a == null) {
            return null;
        }
        Object[] objArr = new Object[4];
        objArr[0] = "1$";
        objArr[1] = this.f4921a;
        objArr[2] = "\\";
        if (str == null) {
            str = "";
        }
        objArr[3] = str;
        return String.format("%s%s%s%s", objArr).getBytes(Charset.forName("UTF-8"));
    }

    public String e() {
        return this.f4922b;
    }

    public String f() {
        return this.f4921a;
    }

    @Override // u2.e
    public String getName() {
        return "cct";
    }
}
