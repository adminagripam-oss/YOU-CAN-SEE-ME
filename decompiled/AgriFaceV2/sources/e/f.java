package e;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import d7.g;
import d7.k;
/* loaded from: classes.dex */
public final class f implements Parcelable {

    /* renamed from: a  reason: collision with root package name */
    private final IntentSender f6512a;

    /* renamed from: b  reason: collision with root package name */
    private final Intent f6513b;

    /* renamed from: c  reason: collision with root package name */
    private final int f6514c;

    /* renamed from: d  reason: collision with root package name */
    private final int f6515d;

    /* renamed from: e  reason: collision with root package name */
    public static final c f6511e = new c(null);
    public static final Parcelable.Creator<f> CREATOR = new b();

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final IntentSender f6516a;

        /* renamed from: b  reason: collision with root package name */
        private Intent f6517b;

        /* renamed from: c  reason: collision with root package name */
        private int f6518c;

        /* renamed from: d  reason: collision with root package name */
        private int f6519d;

        public a(IntentSender intentSender) {
            k.e(intentSender, "intentSender");
            this.f6516a = intentSender;
        }

        public final f a() {
            return new f(this.f6516a, this.f6517b, this.f6518c, this.f6519d);
        }

        public final a b(Intent intent) {
            this.f6517b = intent;
            return this;
        }

        public final a c(int i8, int i9) {
            this.f6519d = i8;
            this.f6518c = i9;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements Parcelable.Creator {
        b() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public f createFromParcel(Parcel parcel) {
            k.e(parcel, "inParcel");
            return new f(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public f[] newArray(int i8) {
            return new f[i8];
        }
    }

    /* loaded from: classes.dex */
    public static final class c {
        private c() {
        }

        public /* synthetic */ c(g gVar) {
            this();
        }
    }

    public f(IntentSender intentSender, Intent intent, int i8, int i9) {
        k.e(intentSender, "intentSender");
        this.f6512a = intentSender;
        this.f6513b = intent;
        this.f6514c = i8;
        this.f6515d = i9;
    }

    public final Intent a() {
        return this.f6513b;
    }

    public final int b() {
        return this.f6514c;
    }

    public final int c() {
        return this.f6515d;
    }

    public final IntentSender d() {
        return this.f6512a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        k.e(parcel, "dest");
        parcel.writeParcelable(this.f6512a, i8);
        parcel.writeParcelable(this.f6513b, i8);
        parcel.writeInt(this.f6514c);
        parcel.writeInt(this.f6515d);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public f(android.os.Parcel r4) {
        /*
            r3 = this;
            java.lang.String r0 = "parcel"
            d7.k.e(r4, r0)
            java.lang.Class<android.content.IntentSender> r0 = android.content.IntentSender.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r4.readParcelable(r0)
            d7.k.b(r0)
            android.content.IntentSender r0 = (android.content.IntentSender) r0
            java.lang.Class<android.content.Intent> r1 = android.content.Intent.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Parcelable r1 = r4.readParcelable(r1)
            android.content.Intent r1 = (android.content.Intent) r1
            int r2 = r4.readInt()
            int r4 = r4.readInt()
            r3.<init>(r0, r1, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: e.f.<init>(android.os.Parcel):void");
    }
}
