package m1;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public abstract class a implements Parcelable {

    /* renamed from: a  reason: collision with root package name */
    private final Parcelable f9129a;

    /* renamed from: b  reason: collision with root package name */
    public static final a f9128b = new C0121a();
    public static final Parcelable.Creator<a> CREATOR = new b();

    /* renamed from: m1.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0121a extends a {
        C0121a() {
            super((C0121a) null);
        }
    }

    /* loaded from: classes.dex */
    static class b implements Parcelable.ClassLoaderCreator {
        b() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public a createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, null);
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: b */
        public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return a.f9128b;
            }
            throw new IllegalStateException("superState must be null");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c */
        public a[] newArray(int i8) {
            return new a[i8];
        }
    }

    private a() {
        this.f9129a = null;
    }

    public final Parcelable a() {
        return this.f9129a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeParcelable(this.f9129a, i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.f9129a = readParcelable == null ? f9128b : readParcelable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Parcelable parcelable) {
        if (parcelable == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        this.f9129a = parcelable == f9128b ? null : parcelable;
    }

    /* synthetic */ a(C0121a c0121a) {
        this();
    }
}
