package b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public interface c extends IInterface {

    /* renamed from: c  reason: collision with root package name */
    public static final String f4125c = "android$support$customtabs$IEngagementSignalsCallback".replace('$', '.');

    /* loaded from: classes.dex */
    public static abstract class a extends Binder implements c {
        public a() {
            attachInterface(this, c.f4125c);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) {
            String str = c.f4125c;
            if (i8 >= 1 && i8 <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i8 == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i8 == 2) {
                onVerticalScrollEvent(parcel.readInt() != 0, (Bundle) b.b(parcel, Bundle.CREATOR));
            } else if (i8 == 3) {
                onGreatestScrollPercentageIncreased(parcel.readInt(), (Bundle) b.b(parcel, Bundle.CREATOR));
            } else if (i8 != 4) {
                return super.onTransact(i8, parcel, parcel2, i9);
            } else {
                onSessionEnded(parcel.readInt() != 0, (Bundle) b.b(parcel, Bundle.CREATOR));
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static Object b(Parcel parcel, Parcelable.Creator creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }
    }

    void onGreatestScrollPercentageIncreased(int i8, Bundle bundle);

    void onSessionEnded(boolean z7, Bundle bundle);

    void onVerticalScrollEvent(boolean z7, Bundle bundle);
}
