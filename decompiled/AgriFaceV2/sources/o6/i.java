package o6;

import android.content.Context;
import android.util.Log;
import e6.a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import o6.h;
/* loaded from: classes.dex */
public class i implements e6.a, h.a {

    /* renamed from: a  reason: collision with root package name */
    private Context f9879a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9880a;

        static {
            int[] iArr = new int[h.c.values().length];
            f9880a = iArr;
            try {
                iArr[h.c.ROOT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9880a[h.c.MUSIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9880a[h.c.PODCASTS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9880a[h.c.RINGTONES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9880a[h.c.ALARMS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9880a[h.c.NOTIFICATIONS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9880a[h.c.PICTURES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9880a[h.c.MOVIES.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9880a[h.c.DOWNLOADS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9880a[h.c.DCIM.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9880a[h.c.DOCUMENTS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private void z(i6.b bVar, Context context) {
        try {
            h.a.x(bVar, this);
        } catch (Exception e8) {
            Log.e("PathProviderPlugin", "Received exception while setting up PathProviderPlugin", e8);
        }
        this.f9879a = context;
    }

    @Override // o6.h.a
    public String d() {
        return this.f9879a.getCacheDir().getPath();
    }

    @Override // o6.h.a
    public String e() {
        return this.f9879a.getCacheDir().getPath();
    }

    @Override // o6.h.a
    public String f() {
        return p6.b.c(this.f9879a);
    }

    @Override // o6.h.a
    public List i() {
        File[] externalCacheDirs;
        ArrayList arrayList = new ArrayList();
        for (File file : this.f9879a.getExternalCacheDirs()) {
            if (file != null) {
                arrayList.add(file.getAbsolutePath());
            }
        }
        return arrayList;
    }

    @Override // o6.h.a
    public List n(h.c cVar) {
        File[] externalFilesDirs;
        ArrayList arrayList = new ArrayList();
        for (File file : this.f9879a.getExternalFilesDirs(y(cVar))) {
            if (file != null) {
                arrayList.add(file.getAbsolutePath());
            }
        }
        return arrayList;
    }

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
        z(bVar.b(), bVar.a());
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
        h.a.x(bVar.b(), null);
    }

    @Override // o6.h.a
    public String r() {
        return p6.b.d(this.f9879a);
    }

    @Override // o6.h.a
    public String u() {
        File externalFilesDir = this.f9879a.getExternalFilesDir(null);
        if (externalFilesDir == null) {
            return null;
        }
        return externalFilesDir.getAbsolutePath();
    }

    String y(h.c cVar) {
        switch (a.f9880a[cVar.ordinal()]) {
            case 1:
                return null;
            case 2:
                return "music";
            case 3:
                return "podcasts";
            case 4:
                return "ringtones";
            case 5:
                return "alarms";
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return "notifications";
            case 7:
                return "pictures";
            case 8:
                return "movies";
            case 9:
                return "downloads";
            case 10:
                return "dcim";
            case 11:
                return "documents";
            default:
                throw new RuntimeException("Unrecognized directory: " + cVar);
        }
    }
}
