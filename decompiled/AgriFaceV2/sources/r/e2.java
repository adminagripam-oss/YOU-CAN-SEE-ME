package r;

import android.media.CamcorderProfile;
import android.media.EncoderProfiles;
import android.os.Build;
import android.util.Size;
import androidx.camera.camera2.internal.compat.quirk.CamcorderProfileResolutionQuirk;
import androidx.camera.camera2.internal.compat.quirk.InvalidVideoProfilesQuirk;
import b0.h1;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class e2 implements b0.g1 {

    /* renamed from: c  reason: collision with root package name */
    private final boolean f10453c;

    /* renamed from: d  reason: collision with root package name */
    private final String f10454d;

    /* renamed from: e  reason: collision with root package name */
    private final int f10455e;

    /* renamed from: f  reason: collision with root package name */
    private final Map f10456f = new HashMap();

    /* renamed from: g  reason: collision with root package name */
    private final b0.l2 f10457g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static EncoderProfiles a(String str, int i8) {
            return CamcorderProfile.getAll(str, i8);
        }
    }

    public e2(String str, b0.l2 l2Var) {
        boolean z7;
        int i8;
        this.f10454d = str;
        try {
            i8 = Integer.parseInt(str);
            z7 = true;
        } catch (NumberFormatException unused) {
            y.f1.l("Camera2EncoderProfilesProvider", "Camera id is not an integer: " + str + ", unable to create Camera2EncoderProfilesProvider");
            z7 = false;
            i8 = -1;
        }
        this.f10453c = z7;
        this.f10455e = i8;
        this.f10457g = l2Var;
    }

    private b0.h1 c(int i8) {
        CamcorderProfile camcorderProfile;
        try {
            camcorderProfile = CamcorderProfile.get(this.f10455e, i8);
        } catch (RuntimeException e8) {
            y.f1.m("Camera2EncoderProfilesProvider", "Unable to get CamcorderProfile by quality: " + i8, e8);
            camcorderProfile = null;
        }
        if (camcorderProfile != null) {
            return c0.a.a(camcorderProfile);
        }
        return null;
    }

    private b0.h1 d() {
        for (Integer num : b0.g1.f4199b) {
            b0.h1 a8 = a(num.intValue());
            if (a8 != null) {
                return a8;
            }
        }
        return null;
    }

    private b0.h1 e() {
        for (int size = b0.g1.f4199b.size() - 1; size >= 0; size--) {
            b0.h1 a8 = a(size);
            if (a8 != null) {
                return a8;
            }
        }
        return null;
    }

    private b0.h1 f(int i8) {
        if (Build.VERSION.SDK_INT >= 31) {
            EncoderProfiles a8 = a.a(this.f10454d, i8);
            if (a8 == null) {
                return null;
            }
            if (androidx.camera.camera2.internal.compat.quirk.b.b(InvalidVideoProfilesQuirk.class) != null) {
                y.f1.a("Camera2EncoderProfilesProvider", "EncoderProfiles contains invalid video profiles, use CamcorderProfile to create EncoderProfilesProxy.");
            } else {
                try {
                    return c0.a.b(a8);
                } catch (NullPointerException e8) {
                    y.f1.m("Camera2EncoderProfilesProvider", "Failed to create EncoderProfilesProxy, EncoderProfiles might  contain invalid video profiles. Use CamcorderProfile instead.", e8);
                }
            }
        }
        return c(i8);
    }

    private boolean g(b0.h1 h1Var) {
        CamcorderProfileResolutionQuirk camcorderProfileResolutionQuirk = (CamcorderProfileResolutionQuirk) this.f10457g.b(CamcorderProfileResolutionQuirk.class);
        if (camcorderProfileResolutionQuirk == null) {
            return true;
        }
        List c8 = h1Var.c();
        if (c8.isEmpty()) {
            return true;
        }
        h1.c cVar = (h1.c) c8.get(0);
        return camcorderProfileResolutionQuirk.f().contains(new Size(cVar.k(), cVar.h()));
    }

    @Override // b0.g1
    public b0.h1 a(int i8) {
        b0.h1 h1Var = null;
        if (this.f10453c && CamcorderProfile.hasProfile(this.f10455e, i8)) {
            if (this.f10456f.containsKey(Integer.valueOf(i8))) {
                return (b0.h1) this.f10456f.get(Integer.valueOf(i8));
            }
            b0.h1 f8 = f(i8);
            if (f8 == null || g(f8)) {
                h1Var = f8;
            } else if (i8 == 1) {
                h1Var = d();
            } else if (i8 == 0) {
                h1Var = e();
            }
            this.f10456f.put(Integer.valueOf(i8), h1Var);
            return h1Var;
        }
        return null;
    }

    @Override // b0.g1
    public boolean b(int i8) {
        return this.f10453c && a(i8) != null;
    }
}
