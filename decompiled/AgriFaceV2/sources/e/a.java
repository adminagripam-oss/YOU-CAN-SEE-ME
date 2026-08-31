package e;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0080a();

    /* renamed from: a  reason: collision with root package name */
    private final int f6488a;

    /* renamed from: b  reason: collision with root package name */
    private final Intent f6489b;

    /* renamed from: e.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0080a implements Parcelable.Creator {
        C0080a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public a[] newArray(int i8) {
            return new a[i8];
        }
    }

    public a(int i8, Intent intent) {
        this.f6488a = i8;
        this.f6489b = intent;
    }

    public static String c(int i8) {
        return i8 != -1 ? i8 != 0 ? String.valueOf(i8) : "RESULT_CANCELED" : "RESULT_OK";
    }

    public Intent a() {
        return this.f6489b;
    }

    public int b() {
        return this.f6488a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ActivityResult{resultCode=" + c(this.f6488a) + ", data=" + this.f6489b + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f6488a);
        parcel.writeInt(this.f6489b == null ? 0 : 1);
        Intent intent = this.f6489b;
        if (intent != null) {
            intent.writeToParcel(parcel, i8);
        }
    }

    a(Parcel parcel) {
        this.f6488a = parcel.readInt();
        this.f6489b = parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel);
    }
}
