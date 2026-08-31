package b;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes.dex */
public interface b extends IInterface {

    /* renamed from: b  reason: collision with root package name */
    public static final String f4123b = "android$support$customtabs$ICustomTabsService".replace('$', '.');

    /* loaded from: classes.dex */
    public static abstract class a extends Binder implements b {

        /* renamed from: b.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C0057a implements b {

            /* renamed from: e  reason: collision with root package name */
            private IBinder f4124e;

            C0057a(IBinder iBinder) {
                this.f4124e = iBinder;
            }

            @Override // b.b
            public boolean B(b.a aVar, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    C0058b.d(obtain, bundle, 0);
                    this.f4124e.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public boolean M(b.a aVar, Uri uri, Bundle bundle, List list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    C0058b.d(obtain, uri, 0);
                    C0058b.d(obtain, bundle, 0);
                    C0058b.c(obtain, list, 0);
                    this.f4124e.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public boolean U(long j8) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeLong(j8);
                    this.f4124e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public boolean Z(b.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    this.f4124e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4124e;
            }

            @Override // b.b
            public boolean b0(b.a aVar, IBinder iBinder, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    obtain.writeStrongBinder(iBinder);
                    C0058b.d(obtain, bundle, 0);
                    this.f4124e.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public boolean h(b.a aVar, Uri uri) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    C0058b.d(obtain, uri, 0);
                    this.f4124e.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public boolean o(b.a aVar, Uri uri, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    C0058b.d(obtain, uri, 0);
                    C0058b.d(obtain, bundle, 0);
                    this.f4124e.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public boolean q(b.a aVar, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    C0058b.d(obtain, bundle, 0);
                    this.f4124e.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public int t(b.a aVar, String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    obtain.writeString(str);
                    C0058b.d(obtain, bundle, 0);
                    this.f4124e.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public boolean w(b.a aVar, int i8, Uri uri, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    obtain.writeInt(i8);
                    C0058b.d(obtain, uri, 0);
                    C0058b.d(obtain, bundle, 0);
                    this.f4124e.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // b.b
            public boolean y(b.a aVar, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.f4123b);
                    obtain.writeStrongInterface(aVar);
                    C0058b.d(obtain, bundle, 0);
                    this.f4124e.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static b d(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(b.f4123b);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new C0057a(iBinder) : (b) queryLocalInterface;
        }
    }

    /* renamed from: b.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0058b {
        /* JADX INFO: Access modifiers changed from: private */
        public static void c(Parcel parcel, List list, int i8) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i9 = 0; i9 < size; i9++) {
                d(parcel, (Parcelable) list.get(i9), i8);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void d(Parcel parcel, Parcelable parcelable, int i8) {
            if (parcelable == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, i8);
        }
    }

    boolean B(b.a aVar, Bundle bundle);

    boolean M(b.a aVar, Uri uri, Bundle bundle, List list);

    boolean U(long j8);

    boolean Z(b.a aVar);

    boolean b0(b.a aVar, IBinder iBinder, Bundle bundle);

    boolean h(b.a aVar, Uri uri);

    boolean o(b.a aVar, Uri uri, Bundle bundle);

    boolean q(b.a aVar, Bundle bundle);

    int t(b.a aVar, String str, Bundle bundle);

    boolean w(b.a aVar, int i8, Uri uri, Bundle bundle);

    boolean y(b.a aVar, Bundle bundle);
}
