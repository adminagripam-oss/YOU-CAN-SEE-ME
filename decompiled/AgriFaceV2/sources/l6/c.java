package l6;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import e6.a;
import h6.r;
import i6.j;
import i6.l;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class c implements e6.a, f6.a, l, r.b {

    /* renamed from: a  reason: collision with root package name */
    private final r f9064a;

    /* renamed from: b  reason: collision with root package name */
    private final PackageManager f9065b;

    /* renamed from: c  reason: collision with root package name */
    private f6.c f9066c;

    /* renamed from: d  reason: collision with root package name */
    private Map f9067d;

    /* renamed from: e  reason: collision with root package name */
    private Map f9068e = new HashMap();

    public c(r rVar) {
        this.f9064a = rVar;
        this.f9065b = rVar.f7401b;
        rVar.b(this);
    }

    private void c() {
        List<ResolveInfo> queryIntentActivities;
        PackageManager.ResolveInfoFlags of;
        this.f9067d = new HashMap();
        int i8 = Build.VERSION.SDK_INT;
        Intent type = new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        if (i8 >= 33) {
            PackageManager packageManager = this.f9065b;
            of = PackageManager.ResolveInfoFlags.of(0L);
            queryIntentActivities = packageManager.queryIntentActivities(type, of);
        } else {
            queryIntentActivities = this.f9065b.queryIntentActivities(type, 0);
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str = resolveInfo.activityInfo.name;
            resolveInfo.loadLabel(this.f9065b).toString();
            this.f9067d.put(str, resolveInfo);
        }
    }

    @Override // h6.r.b
    public void a(String str, String str2, boolean z7, j.d dVar) {
        if (this.f9066c == null) {
            dVar.error("error", "Plugin not bound to an Activity", null);
            return;
        }
        Map map = this.f9067d;
        if (map == null) {
            dVar.error("error", "Can not process text actions before calling queryTextActions", null);
            return;
        }
        ResolveInfo resolveInfo = (ResolveInfo) map.get(str);
        if (resolveInfo == null) {
            dVar.error("error", "Text processing activity not found", null);
            return;
        }
        Integer valueOf = Integer.valueOf(dVar.hashCode());
        this.f9068e.put(valueOf, dVar);
        Intent intent = new Intent();
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        intent.setClassName(activityInfo.packageName, activityInfo.name);
        intent.setAction("android.intent.action.PROCESS_TEXT");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.PROCESS_TEXT", str2);
        intent.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", z7);
        this.f9066c.getActivity().startActivityForResult(intent, valueOf.intValue());
    }

    @Override // h6.r.b
    public Map b() {
        if (this.f9067d == null) {
            c();
        }
        HashMap hashMap = new HashMap();
        for (String str : this.f9067d.keySet()) {
            hashMap.put(str, ((ResolveInfo) this.f9067d.get(str)).loadLabel(this.f9065b).toString());
        }
        return hashMap;
    }

    @Override // i6.l, com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.ActivityResultListener
    public boolean onActivityResult(int i8, int i9, Intent intent) {
        if (this.f9068e.containsKey(Integer.valueOf(i8))) {
            ((j.d) this.f9068e.remove(Integer.valueOf(i8))).success(i9 == -1 ? intent.getStringExtra("android.intent.extra.PROCESS_TEXT") : null);
            return true;
        }
        return false;
    }

    @Override // f6.a
    public void onAttachedToActivity(f6.c cVar) {
        this.f9066c = cVar;
        cVar.a(this);
    }

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
    }

    @Override // f6.a
    public void onDetachedFromActivity() {
        this.f9066c.c(this);
        this.f9066c = null;
    }

    @Override // f6.a
    public void onDetachedFromActivityForConfigChanges() {
        this.f9066c.c(this);
        this.f9066c = null;
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
    }

    @Override // f6.a
    public void onReattachedToActivityForConfigChanges(f6.c cVar) {
        this.f9066c = cVar;
        cVar.a(this);
    }
}
