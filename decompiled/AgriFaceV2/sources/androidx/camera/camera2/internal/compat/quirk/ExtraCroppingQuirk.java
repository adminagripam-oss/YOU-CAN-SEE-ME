package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import android.util.Range;
import android.util.Size;
import b0.h2;
import b0.w2;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes.dex */
public class ExtraCroppingQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final Map f2631a;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f2632a;

        static {
            int[] iArr = new int[w2.b.values().length];
            f2632a = iArr;
            try {
                iArr[w2.b.PRIV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2632a[w2.b.YUV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2632a[w2.b.JPEG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f2631a = hashMap;
        hashMap.put("SM-T580", null);
        hashMap.put("SM-J710MN", new Range(21, 26));
        hashMap.put("SM-A320FL", null);
        hashMap.put("SM-G570M", null);
        hashMap.put("SM-G610F", null);
        hashMap.put("SM-G610M", new Range(21, 26));
    }

    private static boolean g() {
        if ("samsung".equalsIgnoreCase(Build.BRAND) && f2631a.containsKey(Build.MODEL.toUpperCase(Locale.US))) {
            Range range = (Range) f2631a.get(Build.MODEL.toUpperCase(Locale.US));
            if (range == null) {
                return true;
            }
            return range.contains((Range) Integer.valueOf(Build.VERSION.SDK_INT));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean h() {
        return g();
    }

    public Size f(w2.b bVar) {
        if (g()) {
            int i8 = a.f2632a[bVar.ordinal()];
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 != 3) {
                        return null;
                    }
                    return new Size(3264, 1836);
                }
                return new Size(1280, 720);
            }
            return new Size(1920, 1080);
        }
        return null;
    }
}
