package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.q0;
import androidx.lifecycle.i;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    final int[] f3359a;

    /* renamed from: b  reason: collision with root package name */
    final ArrayList f3360b;

    /* renamed from: c  reason: collision with root package name */
    final int[] f3361c;

    /* renamed from: d  reason: collision with root package name */
    final int[] f3362d;

    /* renamed from: e  reason: collision with root package name */
    final int f3363e;

    /* renamed from: f  reason: collision with root package name */
    final String f3364f;

    /* renamed from: g  reason: collision with root package name */
    final int f3365g;

    /* renamed from: h  reason: collision with root package name */
    final int f3366h;

    /* renamed from: i  reason: collision with root package name */
    final CharSequence f3367i;

    /* renamed from: j  reason: collision with root package name */
    final int f3368j;

    /* renamed from: k  reason: collision with root package name */
    final CharSequence f3369k;

    /* renamed from: l  reason: collision with root package name */
    final ArrayList f3370l;

    /* renamed from: m  reason: collision with root package name */
    final ArrayList f3371m;

    /* renamed from: n  reason: collision with root package name */
    final boolean f3372n;

    /* loaded from: classes.dex */
    class a implements Parcelable.Creator {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public b[] newArray(int i8) {
            return new b[i8];
        }
    }

    b(Parcel parcel) {
        this.f3359a = parcel.createIntArray();
        this.f3360b = parcel.createStringArrayList();
        this.f3361c = parcel.createIntArray();
        this.f3362d = parcel.createIntArray();
        this.f3363e = parcel.readInt();
        this.f3364f = parcel.readString();
        this.f3365g = parcel.readInt();
        this.f3366h = parcel.readInt();
        this.f3367i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f3368j = parcel.readInt();
        this.f3369k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f3370l = parcel.createStringArrayList();
        this.f3371m = parcel.createStringArrayList();
        this.f3372n = parcel.readInt() != 0;
    }

    private void a(androidx.fragment.app.a aVar) {
        int i8 = 0;
        int i9 = 0;
        while (true) {
            boolean z7 = true;
            if (i8 >= this.f3359a.length) {
                aVar.f3623h = this.f3363e;
                aVar.f3626k = this.f3364f;
                aVar.f3624i = true;
                aVar.f3627l = this.f3366h;
                aVar.f3628m = this.f3367i;
                aVar.f3629n = this.f3368j;
                aVar.f3630o = this.f3369k;
                aVar.f3631p = this.f3370l;
                aVar.f3632q = this.f3371m;
                aVar.f3633r = this.f3372n;
                return;
            }
            q0.a aVar2 = new q0.a();
            int i10 = i8 + 1;
            aVar2.f3635a = this.f3359a[i8];
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i9 + " base fragment #" + this.f3359a[i10]);
            }
            aVar2.f3642h = i.b.values()[this.f3361c[i9]];
            aVar2.f3643i = i.b.values()[this.f3362d[i9]];
            int[] iArr = this.f3359a;
            int i11 = i10 + 1;
            if (iArr[i10] == 0) {
                z7 = false;
            }
            aVar2.f3637c = z7;
            int i12 = i11 + 1;
            int i13 = iArr[i11];
            aVar2.f3638d = i13;
            int i14 = i12 + 1;
            int i15 = iArr[i12];
            aVar2.f3639e = i15;
            int i16 = i14 + 1;
            int i17 = iArr[i14];
            aVar2.f3640f = i17;
            int i18 = iArr[i16];
            aVar2.f3641g = i18;
            aVar.f3619d = i13;
            aVar.f3620e = i15;
            aVar.f3621f = i17;
            aVar.f3622g = i18;
            aVar.e(aVar2);
            i9++;
            i8 = i16 + 1;
        }
    }

    public androidx.fragment.app.a b(i0 i0Var) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(i0Var);
        a(aVar);
        aVar.f3352v = this.f3365g;
        for (int i8 = 0; i8 < this.f3360b.size(); i8++) {
            String str = (String) this.f3360b.get(i8);
            if (str != null) {
                ((q0.a) aVar.f3618c.get(i8)).f3636b = i0Var.f0(str);
            }
        }
        aVar.n(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeIntArray(this.f3359a);
        parcel.writeStringList(this.f3360b);
        parcel.writeIntArray(this.f3361c);
        parcel.writeIntArray(this.f3362d);
        parcel.writeInt(this.f3363e);
        parcel.writeString(this.f3364f);
        parcel.writeInt(this.f3365g);
        parcel.writeInt(this.f3366h);
        TextUtils.writeToParcel(this.f3367i, parcel, 0);
        parcel.writeInt(this.f3368j);
        TextUtils.writeToParcel(this.f3369k, parcel, 0);
        parcel.writeStringList(this.f3370l);
        parcel.writeStringList(this.f3371m);
        parcel.writeInt(this.f3372n ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(androidx.fragment.app.a aVar) {
        int size = aVar.f3618c.size();
        this.f3359a = new int[size * 6];
        if (!aVar.f3624i) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f3360b = new ArrayList(size);
        this.f3361c = new int[size];
        this.f3362d = new int[size];
        int i8 = 0;
        int i9 = 0;
        while (i8 < size) {
            q0.a aVar2 = (q0.a) aVar.f3618c.get(i8);
            int i10 = i9 + 1;
            this.f3359a[i9] = aVar2.f3635a;
            ArrayList arrayList = this.f3360b;
            p pVar = aVar2.f3636b;
            arrayList.add(pVar != null ? pVar.f3563f : null);
            int[] iArr = this.f3359a;
            int i11 = i10 + 1;
            iArr[i10] = aVar2.f3637c ? 1 : 0;
            int i12 = i11 + 1;
            iArr[i11] = aVar2.f3638d;
            int i13 = i12 + 1;
            iArr[i12] = aVar2.f3639e;
            int i14 = i13 + 1;
            iArr[i13] = aVar2.f3640f;
            iArr[i14] = aVar2.f3641g;
            this.f3361c[i8] = aVar2.f3642h.ordinal();
            this.f3362d[i8] = aVar2.f3643i.ordinal();
            i8++;
            i9 = i14 + 1;
        }
        this.f3363e = aVar.f3623h;
        this.f3364f = aVar.f3626k;
        this.f3365g = aVar.f3352v;
        this.f3366h = aVar.f3627l;
        this.f3367i = aVar.f3628m;
        this.f3368j = aVar.f3629n;
        this.f3369k = aVar.f3630o;
        this.f3370l = aVar.f3631p;
        this.f3371m = aVar.f3632q;
        this.f3372n = aVar.f3633r;
    }
}
