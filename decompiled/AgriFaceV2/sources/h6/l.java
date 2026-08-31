package h6;

import i6.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7300a;

    /* renamed from: b  reason: collision with root package name */
    private b f7301b;

    /* renamed from: c  reason: collision with root package name */
    public final j.c f7302c;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            if (l.this.f7301b == null) {
                return;
            }
            String str = iVar.f7535a;
            str.hashCode();
            if (!str.equals("Localization.getStringResource")) {
                dVar.notImplemented();
                return;
            }
            JSONObject jSONObject = (JSONObject) iVar.b();
            try {
                dVar.success(l.this.f7301b.a(jSONObject.getString("key"), jSONObject.has("locale") ? jSONObject.getString("locale") : null));
            } catch (JSONException e8) {
                dVar.error("error", e8.getMessage(), null);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        String a(String str, String str2);
    }

    public l(a6.a aVar) {
        a aVar2 = new a();
        this.f7302c = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/localization", i6.f.f7534a);
        this.f7300a = jVar;
        jVar.e(aVar2);
    }

    public void b(List list) {
        z5.b.f("LocalizationChannel", "Sending Locales to Flutter.");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Locale locale = (Locale) it.next();
            z5.b.f("LocalizationChannel", "Locale (Language: " + locale.getLanguage() + ", Country: " + locale.getCountry() + ", Variant: " + locale.getVariant() + ")");
            arrayList.add(locale.getLanguage());
            arrayList.add(locale.getCountry());
            arrayList.add(locale.getScript());
            arrayList.add(locale.getVariant());
        }
        this.f7300a.c("setLocale", arrayList);
    }

    public void c(b bVar) {
        this.f7301b = bVar;
    }
}
