package t3;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes.dex */
public interface a extends IInterface {

    /* renamed from: t3.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractBinderC0152a extends w3.b implements a {
        public AbstractBinderC0152a() {
            super("com.google.android.gms.dynamic.IObjectWrapper");
        }

        public static a e(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            return queryLocalInterface instanceof a ? (a) queryLocalInterface : new c(iBinder);
        }
    }
}
