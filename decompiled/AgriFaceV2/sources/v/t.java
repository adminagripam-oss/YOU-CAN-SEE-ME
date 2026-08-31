package v;

import android.util.Size;
import androidx.camera.camera2.internal.compat.quirk.ExtraCroppingQuirk;
import b0.w2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class t {

    /* renamed from: a  reason: collision with root package name */
    private final ExtraCroppingQuirk f12312a;

    public t() {
        this((ExtraCroppingQuirk) androidx.camera.camera2.internal.compat.quirk.b.b(ExtraCroppingQuirk.class));
    }

    public List a(w2.b bVar, List list) {
        Size f8;
        ExtraCroppingQuirk extraCroppingQuirk = this.f12312a;
        if (extraCroppingQuirk == null || (f8 = extraCroppingQuirk.f(bVar)) == null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(f8);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            if (!size.equals(f8)) {
                arrayList.add(size);
            }
        }
        return arrayList;
    }

    t(ExtraCroppingQuirk extraCroppingQuirk) {
        this.f12312a = extraCroppingQuirk;
    }
}
