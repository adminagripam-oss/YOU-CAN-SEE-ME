package l3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class b {

    /* loaded from: classes.dex */
    public static class a extends RuntimeException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public a(java.lang.String r3, android.os.Parcel r4) {
            /*
                r2 = this;
                int r0 = r4.dataPosition()
                int r4 = r4.dataSize()
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r3)
                java.lang.String r3 = " Parcel: pos="
                r1.append(r3)
                r1.append(r0)
                java.lang.String r3 = " size="
                r1.append(r3)
                r1.append(r4)
                java.lang.String r3 = r1.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: l3.b.a.<init>(java.lang.String, android.os.Parcel):void");
        }
    }

    public static Bundle a(Parcel parcel, int i8) {
        int p8 = p(parcel, i8);
        int dataPosition = parcel.dataPosition();
        if (p8 == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(dataPosition + p8);
        return readBundle;
    }

    public static int[] b(Parcel parcel, int i8) {
        int p8 = p(parcel, i8);
        int dataPosition = parcel.dataPosition();
        if (p8 == 0) {
            return null;
        }
        int[] createIntArray = parcel.createIntArray();
        parcel.setDataPosition(dataPosition + p8);
        return createIntArray;
    }

    public static Parcelable c(Parcel parcel, int i8, Parcelable.Creator creator) {
        int p8 = p(parcel, i8);
        int dataPosition = parcel.dataPosition();
        if (p8 == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + p8);
        return parcelable;
    }

    public static String d(Parcel parcel, int i8) {
        int p8 = p(parcel, i8);
        int dataPosition = parcel.dataPosition();
        if (p8 == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + p8);
        return readString;
    }

    public static ArrayList e(Parcel parcel, int i8) {
        int p8 = p(parcel, i8);
        int dataPosition = parcel.dataPosition();
        if (p8 == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(dataPosition + p8);
        return createStringArrayList;
    }

    public static Object[] f(Parcel parcel, int i8, Parcelable.Creator creator) {
        int p8 = p(parcel, i8);
        int dataPosition = parcel.dataPosition();
        if (p8 == 0) {
            return null;
        }
        Object[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(dataPosition + p8);
        return createTypedArray;
    }

    public static ArrayList g(Parcel parcel, int i8, Parcelable.Creator creator) {
        int p8 = p(parcel, i8);
        int dataPosition = parcel.dataPosition();
        if (p8 == 0) {
            return null;
        }
        ArrayList createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(dataPosition + p8);
        return createTypedArrayList;
    }

    public static void h(Parcel parcel, int i8) {
        if (parcel.dataPosition() == i8) {
            return;
        }
        throw new a("Overread allowed size end=" + i8, parcel);
    }

    public static int i(int i8) {
        return (char) i8;
    }

    public static boolean j(Parcel parcel, int i8) {
        s(parcel, i8, 4);
        return parcel.readInt() != 0;
    }

    public static float k(Parcel parcel, int i8) {
        s(parcel, i8, 4);
        return parcel.readFloat();
    }

    public static int l(Parcel parcel) {
        return parcel.readInt();
    }

    public static IBinder m(Parcel parcel, int i8) {
        int p8 = p(parcel, i8);
        int dataPosition = parcel.dataPosition();
        if (p8 == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(dataPosition + p8);
        return readStrongBinder;
    }

    public static int n(Parcel parcel, int i8) {
        s(parcel, i8, 4);
        return parcel.readInt();
    }

    public static long o(Parcel parcel, int i8) {
        s(parcel, i8, 8);
        return parcel.readLong();
    }

    public static int p(Parcel parcel, int i8) {
        return (i8 & (-65536)) != -65536 ? (char) (i8 >> 16) : parcel.readInt();
    }

    public static void q(Parcel parcel, int i8) {
        parcel.setDataPosition(parcel.dataPosition() + p(parcel, i8));
    }

    public static int r(Parcel parcel) {
        int l8 = l(parcel);
        int p8 = p(parcel, l8);
        int i8 = i(l8);
        int dataPosition = parcel.dataPosition();
        if (i8 == 20293) {
            int i9 = p8 + dataPosition;
            if (i9 < dataPosition || i9 > parcel.dataSize()) {
                throw new a("Size read is invalid start=" + dataPosition + " end=" + i9, parcel);
            }
            return i9;
        }
        throw new a("Expected object header. Got 0x".concat(String.valueOf(Integer.toHexString(l8))), parcel);
    }

    private static void s(Parcel parcel, int i8, int i9) {
        int p8 = p(parcel, i8);
        if (p8 == i9) {
            return;
        }
        String hexString = Integer.toHexString(p8);
        throw new a("Expected size " + i9 + " got " + p8 + " (0x" + hexString + ")", parcel);
    }
}
