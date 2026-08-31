package k3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;
/* loaded from: classes.dex */
public abstract class b0 {

    /* renamed from: a  reason: collision with root package name */
    private static final b1.g f8330a = new b1.g();

    /* renamed from: b  reason: collision with root package name */
    private static Locale f8331b;

    public static String a(Context context) {
        String packageName = context.getPackageName();
        try {
            return s3.c.a(context).b(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    public static String b(Context context, int i8) {
        return context.getResources().getString(i8 != 1 ? i8 != 2 ? i8 != 3 ? 17039370 : g3.b.f7106a : g3.b.f7115j : g3.b.f7109d);
    }

    public static String c(Context context, int i8) {
        Resources resources = context.getResources();
        String a8 = a(context);
        if (i8 != 1) {
            if (i8 == 2) {
                return q3.e.c(context) ? resources.getString(g3.b.f7119n) : resources.getString(g3.b.f7116k, a8);
            } else if (i8 != 3) {
                if (i8 != 5) {
                    if (i8 != 7) {
                        if (i8 != 9) {
                            if (i8 != 20) {
                                switch (i8) {
                                    case 16:
                                        return g(context, "common_google_play_services_api_unavailable_text", a8);
                                    case 17:
                                        return g(context, "common_google_play_services_sign_in_failed_text", a8);
                                    case 18:
                                        return resources.getString(g3.b.f7118m, a8);
                                    default:
                                        return resources.getString(h3.i.f7243a, a8);
                                }
                            }
                            return g(context, "common_google_play_services_restricted_profile_text", a8);
                        }
                        return resources.getString(g3.b.f7114i, a8);
                    }
                    return g(context, "common_google_play_services_network_error_text", a8);
                }
                return g(context, "common_google_play_services_invalid_account_text", a8);
            } else {
                return resources.getString(g3.b.f7107b, a8);
            }
        }
        return resources.getString(g3.b.f7110e, a8);
    }

    public static String d(Context context, int i8) {
        return (i8 == 6 || i8 == 19) ? g(context, "common_google_play_services_resolution_required_text", a(context)) : c(context, i8);
    }

    public static String e(Context context, int i8) {
        String h8 = i8 == 6 ? h(context, "common_google_play_services_resolution_required_title") : f(context, i8);
        return h8 == null ? context.getResources().getString(g3.b.f7113h) : h8;
    }

    public static String f(Context context, int i8) {
        String str;
        Resources resources = context.getResources();
        switch (i8) {
            case 1:
                return resources.getString(g3.b.f7111f);
            case 2:
                return resources.getString(g3.b.f7117l);
            case 3:
                return resources.getString(g3.b.f7108c);
            case 4:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return h(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return h(context, "common_google_play_services_network_error_title");
            case 8:
                str = "Internal error occurred. Please see logs for detailed information";
                break;
            case 9:
                str = "Google Play services is invalid. Cannot recover.";
                break;
            case 10:
                str = "Developer error occurred. Please see logs for detailed information";
                break;
            case 11:
                str = "The application is not licensed to the user.";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            default:
                str = "Unexpected error code " + i8;
                break;
            case 16:
                str = "One of the API components you attempted to connect to is not available.";
                break;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return h(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return h(context, "common_google_play_services_restricted_profile_title");
        }
        Log.e("GoogleApiAvailability", str);
        return null;
    }

    private static String g(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String h8 = h(context, str);
        if (h8 == null) {
            h8 = resources.getString(h3.i.f7243a);
        }
        return String.format(resources.getConfiguration().locale, h8, str2);
    }

    private static String h(Context context, String str) {
        b1.g gVar = f8330a;
        synchronized (gVar) {
            Locale c8 = androidx.core.os.e.a(context.getResources().getConfiguration()).c(0);
            if (!c8.equals(f8331b)) {
                gVar.clear();
                f8331b = c8;
            }
            String str2 = (String) gVar.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources c9 = h3.f.c(context);
            if (c9 == null) {
                return null;
            }
            int identifier = c9.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                Log.w("GoogleApiAvailability", "Missing resource: " + str);
                return null;
            }
            String string = c9.getString(identifier);
            if (!TextUtils.isEmpty(string)) {
                gVar.put(str, string);
                return string;
            }
            Log.w("GoogleApiAvailability", "Got empty resource: " + str);
            return null;
        }
    }
}
