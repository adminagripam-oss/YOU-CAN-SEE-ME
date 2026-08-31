package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
/* loaded from: classes.dex */
class b extends a {

    /* renamed from: d  reason: collision with root package name */
    private final SparseIntArray f3988d;

    /* renamed from: e  reason: collision with root package name */
    private final Parcel f3989e;

    /* renamed from: f  reason: collision with root package name */
    private final int f3990f;

    /* renamed from: g  reason: collision with root package name */
    private final int f3991g;

    /* renamed from: h  reason: collision with root package name */
    private final String f3992h;

    /* renamed from: i  reason: collision with root package name */
    private int f3993i;

    /* renamed from: j  reason: collision with root package name */
    private int f3994j;

    /* renamed from: k  reason: collision with root package name */
    private int f3995k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new b1.a(), new b1.a(), new b1.a());
    }

    @Override // androidx.versionedparcelable.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f3989e.writeInt(-1);
            return;
        }
        this.f3989e.writeInt(bArr.length);
        this.f3989e.writeByteArray(bArr);
    }

    @Override // androidx.versionedparcelable.a
    protected void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f3989e, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void E(int i8) {
        this.f3989e.writeInt(i8);
    }

    @Override // androidx.versionedparcelable.a
    public void G(Parcelable parcelable) {
        this.f3989e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void I(String str) {
        this.f3989e.writeString(str);
    }

    @Override // androidx.versionedparcelable.a
    public void a() {
        int i8 = this.f3993i;
        if (i8 >= 0) {
            int i9 = this.f3988d.get(i8);
            int dataPosition = this.f3989e.dataPosition();
            this.f3989e.setDataPosition(i9);
            this.f3989e.writeInt(dataPosition - i9);
            this.f3989e.setDataPosition(dataPosition);
        }
    }

    @Override // androidx.versionedparcelable.a
    protected a b() {
        Parcel parcel = this.f3989e;
        int dataPosition = parcel.dataPosition();
        int i8 = this.f3994j;
        if (i8 == this.f3990f) {
            i8 = this.f3991g;
        }
        int i9 = i8;
        return new b(parcel, dataPosition, i9, this.f3992h + "  ", this.f3985a, this.f3986b, this.f3987c);
    }

    @Override // androidx.versionedparcelable.a
    public boolean g() {
        return this.f3989e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.a
    public byte[] i() {
        int readInt = this.f3989e.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.f3989e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.a
    protected CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f3989e);
    }

    @Override // androidx.versionedparcelable.a
    public boolean m(int i8) {
        while (this.f3994j < this.f3991g) {
            int i9 = this.f3995k;
            if (i9 == i8) {
                return true;
            }
            if (String.valueOf(i9).compareTo(String.valueOf(i8)) > 0) {
                return false;
            }
            this.f3989e.setDataPosition(this.f3994j);
            int readInt = this.f3989e.readInt();
            this.f3995k = this.f3989e.readInt();
            this.f3994j += readInt;
        }
        return this.f3995k == i8;
    }

    @Override // androidx.versionedparcelable.a
    public int o() {
        return this.f3989e.readInt();
    }

    @Override // androidx.versionedparcelable.a
    public Parcelable q() {
        return this.f3989e.readParcelable(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.a
    public String s() {
        return this.f3989e.readString();
    }

    @Override // androidx.versionedparcelable.a
    public void w(int i8) {
        a();
        this.f3993i = i8;
        this.f3988d.put(i8, this.f3989e.dataPosition());
        E(0);
        E(i8);
    }

    @Override // androidx.versionedparcelable.a
    public void y(boolean z7) {
        this.f3989e.writeInt(z7 ? 1 : 0);
    }

    private b(Parcel parcel, int i8, int i9, String str, b1.a aVar, b1.a aVar2, b1.a aVar3) {
        super(aVar, aVar2, aVar3);
        this.f3988d = new SparseIntArray();
        this.f3993i = -1;
        this.f3995k = -1;
        this.f3989e = parcel;
        this.f3990f = i8;
        this.f3991g = i9;
        this.f3994j = i8;
        this.f3992h = str;
    }
}
