package com.pichillilorenzo.flutter_inappwebview_android;

import android.os.Build;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import i6.i;
import i6.j;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes.dex */
public class PlatformUtil extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "PlatformUtil";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_platformutil";
    public InAppWebViewFlutterPlugin plugin;

    public PlatformUtil(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    public static String formatDate(long j8, String str, Locale locale, TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date(j8));
    }

    public static Locale getLocaleFromString(String str) {
        if (str == null) {
            return Locale.US;
        }
        String[] split = str.split("_");
        return new Locale(split[0], split.length > 1 ? split[1] : "", split.length > 2 ? split[2] : "");
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        String formatDate;
        String str = iVar.f7535a;
        str.hashCode();
        if (str.equals("formatDate")) {
            long longValue = ((Long) iVar.a("date")).longValue();
            String str2 = (String) iVar.a("format");
            Locale localeFromString = getLocaleFromString((String) iVar.a("locale"));
            String str3 = (String) iVar.a("timezone");
            if (str3 == null) {
                str3 = "UTC";
            }
            formatDate = formatDate(longValue, str2, localeFromString, TimeZone.getTimeZone(str3));
        } else if (!str.equals("getSystemVersion")) {
            dVar.notImplemented();
            return;
        } else {
            formatDate = String.valueOf(Build.VERSION.SDK_INT);
        }
        dVar.success(formatDate);
    }
}
