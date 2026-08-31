package com.pichillilorenzo.flutter_inappwebview_android;

import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import c2.t;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import i6.i;
import i6.j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
/* loaded from: classes.dex */
public class MyCookieManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "MyCookieManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_cookiemanager";
    public static CookieManager cookieManager;
    public InAppWebViewFlutterPlugin plugin;

    public MyCookieManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    public static String getCookieExpirationDate(Long l8) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(new Date(l8.longValue()));
    }

    private static CookieManager getCookieManager() {
        if (cookieManager == null) {
            try {
                cookieManager = CookieManager.getInstance();
            } catch (IllegalArgumentException unused) {
                return null;
            } catch (Exception e8) {
                if (e8.getMessage() == null || !e8.getClass().getCanonicalName().equals("android.webkit.WebViewFactory.MissingWebViewPackageException")) {
                    throw e8;
                }
                return null;
            }
        }
        return cookieManager;
    }

    public static void init() {
        if (cookieManager == null) {
            cookieManager = getCookieManager();
        }
    }

    public void deleteAllCookies(final j.d dVar) {
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            dVar.success(Boolean.FALSE);
            return;
        }
        cookieManager.removeAllCookies(new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyCookieManager.3
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(Boolean bool) {
                dVar.success(bool);
            }
        });
        cookieManager.flush();
    }

    public void deleteCookie(String str, String str2, String str3, String str4, final j.d dVar) {
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            dVar.success(Boolean.FALSE);
            return;
        }
        String str5 = str2 + "=; Path=" + str4 + "; Max-Age=-1";
        if (str3 != null) {
            str5 = str5 + "; Domain=" + str3;
        }
        cookieManager.setCookie(str, str5 + ";", new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyCookieManager.2
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(Boolean bool) {
                dVar.success(bool);
            }
        });
        cookieManager.flush();
    }

    public void deleteCookies(String str, String str2, String str3, j.d dVar) {
        Boolean bool;
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            bool = Boolean.FALSE;
        } else {
            String cookie = cookieManager2.getCookie(str);
            if (cookie != null) {
                for (String str4 : cookie.split(";")) {
                    String str5 = str4.split("=", 2)[0].trim() + "=; Path=" + str3 + "; Max-Age=-1";
                    if (str2 != null) {
                        str5 = str5 + "; Domain=" + str2;
                    }
                    cookieManager.setCookie(str, str5 + ";", null);
                }
                cookieManager.flush();
            }
            bool = Boolean.TRUE;
        }
        dVar.success(bool);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }

    public List<Map<String, Object>> getCookies(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            return arrayList;
        }
        List arrayList2 = new ArrayList();
        String str3 = "GET_COOKIE_INFO";
        String str4 = ";";
        if (t.a("GET_COOKIE_INFO")) {
            arrayList2 = c2.a.b(cookieManager, str);
        } else {
            String cookie = cookieManager.getCookie(str);
            if (cookie != null) {
                arrayList2 = Arrays.asList(cookie.split(";"));
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            String[] split = ((String) it.next()).split(str4);
            if (split.length != 0) {
                String[] split2 = split[0].split("=", 2);
                String trim = split2[0].trim();
                String trim2 = split2.length > 1 ? split2[1].trim() : "";
                HashMap hashMap = new HashMap();
                hashMap.put("name", trim);
                hashMap.put("value", trim2);
                hashMap.put("expiresDate", null);
                hashMap.put("isSessionOnly", null);
                hashMap.put("domain", null);
                hashMap.put("sameSite", null);
                hashMap.put("isSecure", null);
                hashMap.put("isHttpOnly", null);
                hashMap.put("path", null);
                if (t.a(str3)) {
                    Boolean bool = Boolean.FALSE;
                    hashMap.put("isSecure", bool);
                    hashMap.put("isHttpOnly", bool);
                    int i8 = 1;
                    while (i8 < split.length) {
                        Iterator it2 = it;
                        String[] split3 = split[i8].split("=", 2);
                        String trim3 = split3[0].trim();
                        String str5 = str3;
                        String[] strArr = split;
                        String trim4 = split3.length > 1 ? split3[1].trim() : "";
                        if (trim3.equalsIgnoreCase("Expires")) {
                            try {
                                str2 = str4;
                                try {
                                    Date parse = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.US).parse(trim4);
                                    if (parse != null) {
                                        hashMap.put("expiresDate", Long.valueOf(parse.getTime()));
                                    }
                                } catch (ParseException e8) {
                                    e = e8;
                                    Log.e(LOG_TAG, "", e);
                                    i8++;
                                    str3 = str5;
                                    it = it2;
                                    split = strArr;
                                    str4 = str2;
                                }
                            } catch (ParseException e9) {
                                e = e9;
                                str2 = str4;
                            }
                        } else {
                            str2 = str4;
                            if (trim3.equalsIgnoreCase("Max-Age")) {
                                try {
                                    hashMap.put("expiresDate", Long.valueOf(System.currentTimeMillis() + Long.parseLong(trim4)));
                                } catch (NumberFormatException e10) {
                                    e = e10;
                                    Log.e(LOG_TAG, "", e);
                                    i8++;
                                    str3 = str5;
                                    it = it2;
                                    split = strArr;
                                    str4 = str2;
                                }
                            } else if (trim3.equalsIgnoreCase("Domain")) {
                                hashMap.put("domain", trim4);
                            } else if (trim3.equalsIgnoreCase("SameSite")) {
                                hashMap.put("sameSite", trim4);
                            } else if (trim3.equalsIgnoreCase("Secure")) {
                                hashMap.put("isSecure", Boolean.TRUE);
                            } else if (trim3.equalsIgnoreCase("HttpOnly")) {
                                hashMap.put("isHttpOnly", Boolean.TRUE);
                            } else if (trim3.equalsIgnoreCase("Path")) {
                                hashMap.put("path", trim4);
                            }
                        }
                        i8++;
                        str3 = str5;
                        it = it2;
                        split = strArr;
                        str4 = str2;
                    }
                }
                arrayList.add(hashMap);
                str3 = str3;
                it = it;
                str4 = str4;
            }
        }
        return arrayList;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        init();
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1222815761:
                if (str.equals("deleteCookie")) {
                    c8 = 0;
                    break;
                }
                break;
            case -913968963:
                if (str.equals("removeSessionCookies")) {
                    c8 = 1;
                    break;
                }
                break;
            case 126640486:
                if (str.equals("setCookie")) {
                    c8 = 2;
                    break;
                }
                break;
            case 747417188:
                if (str.equals("deleteCookies")) {
                    c8 = 3;
                    break;
                }
                break;
            case 822411705:
                if (str.equals("deleteAllCookies")) {
                    c8 = 4;
                    break;
                }
                break;
            case 1989049945:
                if (str.equals("getCookies")) {
                    c8 = 5;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                deleteCookie((String) iVar.a("url"), (String) iVar.a("name"), (String) iVar.a("domain"), (String) iVar.a("path"), dVar);
                return;
            case 1:
                removeSessionCookies(dVar);
                return;
            case 2:
                String str2 = (String) iVar.a("url");
                String str3 = (String) iVar.a("name");
                String str4 = (String) iVar.a("value");
                String str5 = (String) iVar.a("domain");
                String str6 = (String) iVar.a("path");
                String str7 = (String) iVar.a("expiresDate");
                setCookie(str2, str3, str4, str5, str6, str7 != null ? new Long(str7) : null, (Integer) iVar.a("maxAge"), (Boolean) iVar.a("isSecure"), (Boolean) iVar.a("isHttpOnly"), (String) iVar.a("sameSite"), dVar);
                return;
            case 3:
                deleteCookies((String) iVar.a("url"), (String) iVar.a("domain"), (String) iVar.a("path"), dVar);
                return;
            case 4:
                deleteAllCookies(dVar);
                return;
            case 5:
                dVar.success(getCookies((String) iVar.a("url")));
                return;
            default:
                dVar.notImplemented();
                return;
        }
    }

    public void removeSessionCookies(final j.d dVar) {
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            dVar.success(Boolean.FALSE);
            return;
        }
        cookieManager.removeSessionCookies(new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyCookieManager.4
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(Boolean bool) {
                dVar.success(bool);
            }
        });
        cookieManager.flush();
    }

    public void setCookie(String str, String str2, String str3, String str4, String str5, Long l8, Integer num, Boolean bool, Boolean bool2, String str6, final j.d dVar) {
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            dVar.success(Boolean.FALSE);
            return;
        }
        String str7 = str2 + "=" + str3 + "; Path=" + str5;
        if (str4 != null) {
            str7 = str7 + "; Domain=" + str4;
        }
        if (l8 != null) {
            str7 = str7 + "; Expires=" + getCookieExpirationDate(l8);
        }
        if (num != null) {
            str7 = str7 + "; Max-Age=" + num.toString();
        }
        if (bool != null && bool.booleanValue()) {
            str7 = str7 + "; Secure";
        }
        if (bool2 != null && bool2.booleanValue()) {
            str7 = str7 + "; HttpOnly";
        }
        if (str6 != null) {
            str7 = str7 + "; SameSite=" + str6;
        }
        cookieManager.setCookie(str, str7 + ";", new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyCookieManager.1
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(Boolean bool3) {
                dVar.success(bool3);
            }
        });
        cookieManager.flush();
    }
}
