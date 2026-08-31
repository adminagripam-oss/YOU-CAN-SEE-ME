package l3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes.dex */
public abstract class c {
    public static int a(Parcel parcel) {
        return o(parcel, 20293);
    }

    public static void b(Parcel parcel, int i8) {
        p(parcel, i8);
    }

    public static void c(Parcel parcel, int i8, boolean z7) {
        q(parcel, i8, 4);
        parcel.writeInt(z7 ? 1 : 0);
    }

    public static void d(Parcel parcel, int i8, Bundle bundle, boolean z7) {
        if (bundle == null) {
            if (z7) {
                q(parcel, i8, 0);
                return;
            }
            return;
        }
        int o8 = o(parcel, i8);
        parcel.writeBundle(bundle);
        p(parcel, o8);
    }

    public static void e(Parcel parcel, int i8, float f8) {
        q(parcel, i8, 4);
        parcel.writeFloat(f8);
    }

    public static void f(Parcel parcel, int i8, IBinder iBinder, boolean z7) {
        if (iBinder == null) {
            if (z7) {
                q(parcel, i8, 0);
                return;
            }
            return;
        }
        int o8 = o(parcel, i8);
        parcel.writeStrongBinder(iBinder);
        p(parcel, o8);
    }

    public static void g(Parcel parcel, int i8, int i9) {
        q(parcel, i8, 4);
        parcel.writeInt(i9);
    }

    public static void h(Parcel parcel, int i8, int[] iArr, boolean z7) {
        if (iArr == null) {
            if (z7) {
                q(parcel, i8, 0);
                return;
            }
            return;
        }
        int o8 = o(parcel, i8);
        parcel.writeIntArray(iArr);
        p(parcel, o8);
    }

    public static void i(Parcel parcel, int i8, long j8) {
        q(parcel, i8, 8);
        parcel.writeLong(j8);
    }

    public static void j(Parcel parcel, int i8, Parcelable parcelable, int i9, boolean z7) {
        if (parcelable == null) {
            if (z7) {
                q(parcel, i8, 0);
                return;
            }
            return;
        }
        int o8 = o(parcel, i8);
        parcelable.writeToParcel(parcel, i9);
        p(parcel, o8);
    }

    public static void k(Parcel parcel, int i8, String str, boolean z7) {
        if (str == null) {
            if (z7) {
                q(parcel, i8, 0);
                return;
            }
            return;
        }
        int o8 = o(parcel, i8);
        parcel.writeString(str);
        p(parcel, o8);
    }

    public static void l(Parcel parcel, int i8, List list, boolean z7) {
        if (list == null) {
            if (z7) {
                q(parcel, i8, 0);
                return;
            }
            return;
        }
        int o8 = o(parcel, i8);
        parcel.writeStringList(list);
        p(parcel, o8);
    }

    public static void m(Parcel parcel, int i8, Parcelable[] parcelableArr, int i9, boolean z7) {
        if (parcelableArr == null) {
            if (z7) {
                q(parcel, i8, 0);
                return;
            }
            return;
        }
        int o8 = o(parcel, i8);
        parcel.writeInt(parcelableArr.length);
        for (Parcelable parcelable : parcelableArr) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                r(parcel, parcelable, i9);
            }
        }
        p(parcel, o8);
    }

    public static void n(Parcel parcel, int i8, List list, boolean z7) {
        if (list == null) {
            if (z7) {
                q(parcel, i8, 0);
                return;
            }
            return;
        }
        int o8 = o(parcel, i8);
        int size = list.size();
        parcel.writeInt(size);
        for (int i9 = 0; i9 < size; i9++) {
            Parcelable parcelable = (Parcelable) list.get(i9);
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                r(parcel, parcelable, 0);
            }
        }
        p(parcel, o8);
    }

    private static int o(Parcel parcel, int i8) {
        parcel.writeInt(i8 | (-65536));
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void p(Parcel parcel, int i8) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i8 - 4);
        parcel.writeInt(dataPosition - i8);
        parcel.setDataPosition(dataPosition);
    }

    private static void q(Parcel parcel, int i8, int i9) {
        parcel.writeInt(i8 | (i9 << 16));
    }

    private static void r(Parcel parcel, Parcelable parcelable, int i8) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i8);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }
}
