package v;

import android.util.Size;
import androidx.camera.camera2.internal.compat.quirk.RepeatingStreamConstraintForVideoRecordingQuirk;
import java.util.ArrayList;
import java.util.Comparator;
/* loaded from: classes.dex */
public class w {

    /* renamed from: b  reason: collision with root package name */
    private static final Size f12315b = new Size(320, 240);

    /* renamed from: c  reason: collision with root package name */
    private static final Comparator f12316c = new d0.e();

    /* renamed from: a  reason: collision with root package name */
    private final RepeatingStreamConstraintForVideoRecordingQuirk f12317a = (RepeatingStreamConstraintForVideoRecordingQuirk) androidx.camera.camera2.internal.compat.quirk.b.b(RepeatingStreamConstraintForVideoRecordingQuirk.class);

    public Size[] a(Size[] sizeArr) {
        if (this.f12317a == null || !RepeatingStreamConstraintForVideoRecordingQuirk.f()) {
            return sizeArr;
        }
        ArrayList arrayList = new ArrayList();
        for (Size size : sizeArr) {
            if (f12316c.compare(size, f12315b) >= 0) {
                arrayList.add(size);
            }
        }
        return (Size[]) arrayList.toArray(new Size[0]);
    }
}
