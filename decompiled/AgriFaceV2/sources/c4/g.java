package c4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class g extends l3.a {
    public static final Parcelable.Creator<g> CREATOR = new n();

    /* renamed from: a  reason: collision with root package name */
    private final List f4794a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f4795b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f4796c;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final ArrayList f4797a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        private boolean f4798b = false;

        /* renamed from: c  reason: collision with root package name */
        private boolean f4799c = false;

        public a a(LocationRequest locationRequest) {
            if (locationRequest != null) {
                this.f4797a.add(locationRequest);
            }
            return this;
        }

        public g b() {
            return new g(this.f4797a, this.f4798b, this.f4799c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(List list, boolean z7, boolean z8) {
        this.f4794a = list;
        this.f4795b = z7;
        this.f4796c = z8;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        List list = this.f4794a;
        int a8 = l3.c.a(parcel);
        l3.c.n(parcel, 1, Collections.unmodifiableList(list), false);
        l3.c.c(parcel, 2, this.f4795b);
        l3.c.c(parcel, 3, this.f4796c);
        l3.c.b(parcel, a8);
    }
}
