package j2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import androidx.window.extensions.layout.FoldingFeature;
import androidx.window.extensions.layout.WindowLayoutInfo;
import h2.c;
import h2.d;
import h2.j;
import h2.k;
import h2.o;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public static final f f8198a = new f();

    private f() {
    }

    private final boolean d(k kVar, e2.b bVar) {
        Rect a8 = kVar.a();
        if (bVar.e()) {
            return false;
        }
        if (bVar.d() == a8.width() || bVar.a() == a8.height()) {
            if (bVar.d() >= a8.width() || bVar.a() >= a8.height()) {
                return (bVar.d() == a8.width() && bVar.a() == a8.height()) ? false : true;
            }
            return false;
        }
        return false;
    }

    public final h2.c a(k kVar, FoldingFeature foldingFeature) {
        d.b a8;
        c.b bVar;
        d7.k.e(kVar, "windowMetrics");
        d7.k.e(foldingFeature, "oemFeature");
        int type = foldingFeature.getType();
        if (type == 1) {
            a8 = d.b.f7183b.a();
        } else if (type != 2) {
            return null;
        } else {
            a8 = d.b.f7183b.b();
        }
        int state = foldingFeature.getState();
        if (state == 1) {
            bVar = c.b.f7176c;
        } else if (state != 2) {
            return null;
        } else {
            bVar = c.b.f7177d;
        }
        Rect bounds = foldingFeature.getBounds();
        d7.k.d(bounds, "oemFeature.bounds");
        if (d(kVar, new e2.b(bounds))) {
            Rect bounds2 = foldingFeature.getBounds();
            d7.k.d(bounds2, "oemFeature.bounds");
            return new h2.d(new e2.b(bounds2), a8, bVar);
        }
        return null;
    }

    public final j b(Context context, WindowLayoutInfo windowLayoutInfo) {
        k c8;
        d7.k.e(context, "context");
        d7.k.e(windowLayoutInfo, "info");
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 30) {
            c8 = o.f7217b.d(context);
        } else if (i8 < 29 || !(context instanceof Activity)) {
            throw new UnsupportedOperationException("Display Features are only supported after Q. Display features for non-Activity contexts are not expected to be reported on devices running Q.");
        } else {
            c8 = o.f7217b.c((Activity) context);
        }
        return c(c8, windowLayoutInfo);
    }

    public final j c(k kVar, WindowLayoutInfo windowLayoutInfo) {
        h2.c cVar;
        d7.k.e(kVar, "windowMetrics");
        d7.k.e(windowLayoutInfo, "info");
        List<FoldingFeature> displayFeatures = windowLayoutInfo.getDisplayFeatures();
        d7.k.d(displayFeatures, "info.displayFeatures");
        ArrayList arrayList = new ArrayList();
        for (FoldingFeature foldingFeature : displayFeatures) {
            if (foldingFeature instanceof FoldingFeature) {
                f fVar = f8198a;
                d7.k.d(foldingFeature, "feature");
                cVar = fVar.a(kVar, foldingFeature);
            } else {
                cVar = null;
            }
            if (cVar != null) {
                arrayList.add(cVar);
            }
        }
        return new j(arrayList);
    }
}
