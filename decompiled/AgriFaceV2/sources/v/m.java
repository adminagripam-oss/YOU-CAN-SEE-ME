package v;

import android.util.Size;
import androidx.camera.camera2.internal.compat.quirk.ExtraSupportedOutputSizeQuirk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import y.f1;
/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private final String f12298a;

    /* renamed from: b  reason: collision with root package name */
    private final ExtraSupportedOutputSizeQuirk f12299b = (ExtraSupportedOutputSizeQuirk) androidx.camera.camera2.internal.compat.quirk.b.b(ExtraSupportedOutputSizeQuirk.class);

    /* renamed from: c  reason: collision with root package name */
    private final e f12300c;

    public m(String str) {
        this.f12298a = str;
        this.f12300c = new e(str);
    }

    private void a(List list, int i8) {
        ExtraSupportedOutputSizeQuirk extraSupportedOutputSizeQuirk = this.f12299b;
        if (extraSupportedOutputSizeQuirk == null) {
            return;
        }
        Size[] f8 = extraSupportedOutputSizeQuirk.f(i8);
        if (f8.length > 0) {
            list.addAll(Arrays.asList(f8));
        }
    }

    private void c(List list, int i8) {
        List a8 = this.f12300c.a(i8);
        if (a8.isEmpty()) {
            return;
        }
        list.removeAll(a8);
    }

    public Size[] b(Size[] sizeArr, int i8) {
        ArrayList arrayList = new ArrayList(Arrays.asList(sizeArr));
        a(arrayList, i8);
        c(arrayList, i8);
        if (arrayList.isEmpty()) {
            f1.l("OutputSizesCorrector", "Sizes array becomes empty after excluding problematic output sizes.");
        }
        return (Size[]) arrayList.toArray(new Size[0]);
    }
}
