package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.i0;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k0 implements Parcelable {
    public static final Parcelable.Creator<k0> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    ArrayList f3490a;

    /* renamed from: b  reason: collision with root package name */
    ArrayList f3491b;

    /* renamed from: c  reason: collision with root package name */
    b[] f3492c;

    /* renamed from: d  reason: collision with root package name */
    int f3493d;

    /* renamed from: e  reason: collision with root package name */
    String f3494e;

    /* renamed from: f  reason: collision with root package name */
    ArrayList f3495f;

    /* renamed from: g  reason: collision with root package name */
    ArrayList f3496g;

    /* renamed from: h  reason: collision with root package name */
    ArrayList f3497h;

    /* loaded from: classes.dex */
    class a implements Parcelable.Creator {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public k0 createFromParcel(Parcel parcel) {
            return new k0(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public k0[] newArray(int i8) {
            return new k0[i8];
        }
    }

    public k0() {
        this.f3494e = null;
        this.f3495f = new ArrayList();
        this.f3496g = new ArrayList();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeStringList(this.f3490a);
        parcel.writeStringList(this.f3491b);
        parcel.writeTypedArray(this.f3492c, i8);
        parcel.writeInt(this.f3493d);
        parcel.writeString(this.f3494e);
        parcel.writeStringList(this.f3495f);
        parcel.writeTypedList(this.f3496g);
        parcel.writeTypedList(this.f3497h);
    }

    public k0(Parcel parcel) {
        this.f3494e = null;
        this.f3495f = new ArrayList();
        this.f3496g = new ArrayList();
        this.f3490a = parcel.createStringArrayList();
        this.f3491b = parcel.createStringArrayList();
        this.f3492c = (b[]) parcel.createTypedArray(b.CREATOR);
        this.f3493d = parcel.readInt();
        this.f3494e = parcel.readString();
        this.f3495f = parcel.createStringArrayList();
        this.f3496g = parcel.createTypedArrayList(c.CREATOR);
        this.f3497h = parcel.createTypedArrayList(i0.k.CREATOR);
    }
}
