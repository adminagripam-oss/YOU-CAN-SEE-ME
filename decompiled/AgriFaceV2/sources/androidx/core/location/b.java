package androidx.core.location;

import android.location.Location;
import android.location.LocationListener;
import java.util.List;
/* loaded from: classes.dex */
public interface b extends LocationListener {
    @Override // android.location.LocationListener
    default void onFlushComplete(int i8) {
    }

    @Override // android.location.LocationListener
    default void onLocationChanged(List list) {
        int size = list.size();
        for (int i8 = 0; i8 < size; i8++) {
            onLocationChanged((Location) list.get(i8));
        }
    }
}
