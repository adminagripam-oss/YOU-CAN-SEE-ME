package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.fragment.app.q0;
import androidx.lifecycle.i;
import e.f;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import s1.c;
/* loaded from: classes.dex */
public abstract class i0 {
    private static boolean U = false;
    static boolean V = true;
    p A;
    private e.c F;
    private e.c G;
    private e.c H;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    private boolean N;
    private ArrayList O;
    private ArrayList P;
    private ArrayList Q;
    private l0 R;
    private c.C0146c S;

    /* renamed from: b  reason: collision with root package name */
    private boolean f3442b;

    /* renamed from: e  reason: collision with root package name */
    private ArrayList f3445e;

    /* renamed from: g  reason: collision with root package name */
    private androidx.activity.q f3447g;

    /* renamed from: x  reason: collision with root package name */
    private a0 f3464x;

    /* renamed from: y  reason: collision with root package name */
    private w f3465y;

    /* renamed from: z  reason: collision with root package name */
    private p f3466z;

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList f3441a = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private final p0 f3443c = new p0();

    /* renamed from: d  reason: collision with root package name */
    ArrayList f3444d = new ArrayList();

    /* renamed from: f  reason: collision with root package name */
    private final b0 f3446f = new b0(this);

    /* renamed from: h  reason: collision with root package name */
    androidx.fragment.app.a f3448h = null;

    /* renamed from: i  reason: collision with root package name */
    boolean f3449i = false;

    /* renamed from: j  reason: collision with root package name */
    private final androidx.activity.p f3450j = new b(false);

    /* renamed from: k  reason: collision with root package name */
    private final AtomicInteger f3451k = new AtomicInteger();

    /* renamed from: l  reason: collision with root package name */
    private final Map f3452l = Collections.synchronizedMap(new HashMap());

    /* renamed from: m  reason: collision with root package name */
    private final Map f3453m = Collections.synchronizedMap(new HashMap());

    /* renamed from: n  reason: collision with root package name */
    private final Map f3454n = Collections.synchronizedMap(new HashMap());

    /* renamed from: o  reason: collision with root package name */
    ArrayList f3455o = new ArrayList();

    /* renamed from: p  reason: collision with root package name */
    private final c0 f3456p = new c0(this);

    /* renamed from: q  reason: collision with root package name */
    private final CopyOnWriteArrayList f3457q = new CopyOnWriteArrayList();

    /* renamed from: r  reason: collision with root package name */
    private final g1.a f3458r = new g1.a() { // from class: androidx.fragment.app.d0
        @Override // g1.a
        public final void accept(Object obj) {
            i0.this.R0((Configuration) obj);
        }
    };

    /* renamed from: s  reason: collision with root package name */
    private final g1.a f3459s = new g1.a() { // from class: androidx.fragment.app.e0
        @Override // g1.a
        public final void accept(Object obj) {
            i0.this.S0((Integer) obj);
        }
    };

    /* renamed from: t  reason: collision with root package name */
    private final g1.a f3460t = new g1.a() { // from class: androidx.fragment.app.f0
        @Override // g1.a
        public final void accept(Object obj) {
            i0.this.T0((androidx.core.app.g) obj);
        }
    };

    /* renamed from: u  reason: collision with root package name */
    private final g1.a f3461u = new g1.a() { // from class: androidx.fragment.app.g0
        @Override // g1.a
        public final void accept(Object obj) {
            i0.this.U0((androidx.core.app.p) obj);
        }
    };

    /* renamed from: v  reason: collision with root package name */
    private final androidx.core.view.b0 f3462v = new c();

    /* renamed from: w  reason: collision with root package name */
    int f3463w = -1;
    private z B = null;
    private z C = new d();
    private z0 D = null;
    private z0 E = new e();
    ArrayDeque I = new ArrayDeque();
    private Runnable T = new f();

    /* loaded from: classes.dex */
    class a implements e.b {
        a() {
        }

        @Override // e.b
        /* renamed from: b */
        public void a(Map map) {
            StringBuilder sb;
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            ArrayList arrayList = new ArrayList(map.values());
            int[] iArr = new int[arrayList.size()];
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                iArr[i8] = ((Boolean) arrayList.get(i8)).booleanValue() ? 0 : -1;
            }
            k kVar = (k) i0.this.I.pollFirst();
            if (kVar == null) {
                sb = new StringBuilder();
                sb.append("No permissions were requested for ");
                sb.append(this);
            } else {
                String str = kVar.f3477a;
                int i9 = kVar.f3478b;
                p i10 = i0.this.f3443c.i(str);
                if (i10 != null) {
                    i10.D0(i9, strArr, iArr);
                    return;
                }
                sb = new StringBuilder();
                sb.append("Permission request result delivered for unknown Fragment ");
                sb.append(str);
            }
            Log.w("FragmentManager", sb.toString());
        }
    }

    /* loaded from: classes.dex */
    class b extends androidx.activity.p {
        b(boolean z7) {
            super(z7);
        }

        @Override // androidx.activity.p
        public void c() {
            if (i0.I0(3)) {
                Log.d("FragmentManager", "handleOnBackCancelled. PREDICTIVE_BACK = " + i0.V + " fragment manager " + i0.this);
            }
            if (i0.V) {
                i0.this.o();
                i0.this.f3448h = null;
            }
        }

        @Override // androidx.activity.p
        public void d() {
            if (i0.I0(3)) {
                Log.d("FragmentManager", "handleOnBackPressed. PREDICTIVE_BACK = " + i0.V + " fragment manager " + i0.this);
            }
            i0.this.E0();
        }

        @Override // androidx.activity.p
        public void e(androidx.activity.b bVar) {
            if (i0.I0(2)) {
                Log.v("FragmentManager", "handleOnBackProgressed. PREDICTIVE_BACK = " + i0.V + " fragment manager " + i0.this);
            }
            i0 i0Var = i0.this;
            if (i0Var.f3448h != null) {
                for (y0 y0Var : i0Var.u(new ArrayList(Collections.singletonList(i0.this.f3448h)), 0, 1)) {
                    y0Var.y(bVar);
                }
                Iterator it = i0.this.f3455o.iterator();
                if (it.hasNext()) {
                    androidx.appcompat.app.w.a(it.next());
                    throw null;
                }
            }
        }

        @Override // androidx.activity.p
        public void f(androidx.activity.b bVar) {
            if (i0.I0(3)) {
                Log.d("FragmentManager", "handleOnBackStarted. PREDICTIVE_BACK = " + i0.V + " fragment manager " + i0.this);
            }
            if (i0.V) {
                i0.this.X();
                i0.this.h1();
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements androidx.core.view.b0 {
        c() {
        }

        @Override // androidx.core.view.b0
        public boolean a(MenuItem menuItem) {
            return i0.this.J(menuItem);
        }

        @Override // androidx.core.view.b0
        public void b(Menu menu) {
            i0.this.K(menu);
        }

        @Override // androidx.core.view.b0
        public void c(Menu menu, MenuInflater menuInflater) {
            i0.this.C(menu, menuInflater);
        }

        @Override // androidx.core.view.b0
        public void d(Menu menu) {
            i0.this.O(menu);
        }
    }

    /* loaded from: classes.dex */
    class d extends z {
        d() {
        }

        @Override // androidx.fragment.app.z
        public p a(ClassLoader classLoader, String str) {
            return i0.this.v0().b(i0.this.v0().f(), str, null);
        }
    }

    /* loaded from: classes.dex */
    class e implements z0 {
        e() {
        }

        @Override // androidx.fragment.app.z0
        public y0 a(ViewGroup viewGroup) {
            return new androidx.fragment.app.f(viewGroup);
        }
    }

    /* loaded from: classes.dex */
    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i0.this.a0(true);
        }
    }

    /* loaded from: classes.dex */
    class g implements m0 {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ p f3473a;

        g(p pVar) {
            this.f3473a = pVar;
        }

        @Override // androidx.fragment.app.m0
        public void a(i0 i0Var, p pVar) {
            this.f3473a.h0(pVar);
        }
    }

    /* loaded from: classes.dex */
    class h implements e.b {
        h() {
        }

        @Override // e.b
        /* renamed from: b */
        public void a(e.a aVar) {
            k kVar = (k) i0.this.I.pollLast();
            if (kVar == null) {
                Log.w("FragmentManager", "No Activities were started for result for " + this);
                return;
            }
            String str = kVar.f3477a;
            int i8 = kVar.f3478b;
            p i9 = i0.this.f3443c.i(str);
            if (i9 != null) {
                i9.e0(i8, aVar.b(), aVar.a());
                return;
            }
            Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str);
        }
    }

    /* loaded from: classes.dex */
    class i implements e.b {
        i() {
        }

        @Override // e.b
        /* renamed from: b */
        public void a(e.a aVar) {
            k kVar = (k) i0.this.I.pollFirst();
            if (kVar == null) {
                Log.w("FragmentManager", "No IntentSenders were started for " + this);
                return;
            }
            String str = kVar.f3477a;
            int i8 = kVar.f3478b;
            p i9 = i0.this.f3443c.i(str);
            if (i9 != null) {
                i9.e0(i8, aVar.b(), aVar.a());
                return;
            }
            Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
        }
    }

    /* loaded from: classes.dex */
    static class j extends f.a {
        j() {
        }

        @Override // f.a
        /* renamed from: d */
        public Intent a(Context context, e.f fVar) {
            Bundle bundleExtra;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent a8 = fVar.a();
            if (a8 != null && (bundleExtra = a8.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                a8.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (a8.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    fVar = new f.a(fVar.d()).b(null).c(fVar.c(), fVar.b()).a();
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", fVar);
            if (i0.I0(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        @Override // f.a
        /* renamed from: e */
        public e.a c(int i8, Intent intent) {
            return new e.a(i8, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class k implements Parcelable {
        public static final Parcelable.Creator<k> CREATOR = new a();

        /* renamed from: a  reason: collision with root package name */
        String f3477a;

        /* renamed from: b  reason: collision with root package name */
        int f3478b;

        /* loaded from: classes.dex */
        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public k createFromParcel(Parcel parcel) {
                return new k(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public k[] newArray(int i8) {
                return new k[i8];
            }
        }

        k(Parcel parcel) {
            this.f3477a = parcel.readString();
            this.f3478b = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            parcel.writeString(this.f3477a);
            parcel.writeInt(this.f3478b);
        }

        k(String str, int i8) {
            this.f3477a = str;
            this.f3478b = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface l {
        boolean a(ArrayList arrayList, ArrayList arrayList2);
    }

    /* loaded from: classes.dex */
    private class m implements l {

        /* renamed from: a  reason: collision with root package name */
        final String f3479a;

        /* renamed from: b  reason: collision with root package name */
        final int f3480b;

        /* renamed from: c  reason: collision with root package name */
        final int f3481c;

        m(String str, int i8, int i9) {
            this.f3479a = str;
            this.f3480b = i8;
            this.f3481c = i9;
        }

        @Override // androidx.fragment.app.i0.l
        public boolean a(ArrayList arrayList, ArrayList arrayList2) {
            p pVar = i0.this.A;
            if (pVar == null || this.f3480b >= 0 || this.f3479a != null || !pVar.o().c1()) {
                return i0.this.f1(arrayList, arrayList2, this.f3479a, this.f3480b, this.f3481c);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class n implements l {
        n() {
        }

        @Override // androidx.fragment.app.i0.l
        public boolean a(ArrayList arrayList, ArrayList arrayList2) {
            boolean g12 = i0.this.g1(arrayList, arrayList2);
            i0 i0Var = i0.this;
            i0Var.f3449i = true;
            if (!i0Var.f3455o.isEmpty() && arrayList.size() > 0) {
                ((Boolean) arrayList2.get(arrayList.size() - 1)).booleanValue();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    linkedHashSet.addAll(i0.this.n0((androidx.fragment.app.a) it.next()));
                }
                Iterator it2 = i0.this.f3455o.iterator();
                while (it2.hasNext()) {
                    androidx.appcompat.app.w.a(it2.next());
                    Iterator it3 = linkedHashSet.iterator();
                    if (it3.hasNext()) {
                        p pVar = (p) it3.next();
                        throw null;
                    }
                }
            }
            return g12;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static p C0(View view) {
        Object tag = view.getTag(r1.b.f11310a);
        if (tag instanceof p) {
            return (p) tag;
        }
        return null;
    }

    public static boolean I0(int i8) {
        return U || Log.isLoggable("FragmentManager", i8);
    }

    private boolean J0(p pVar) {
        return (pVar.F && pVar.G) || pVar.f3580w.p();
    }

    private boolean K0() {
        p pVar = this.f3466z;
        if (pVar == null) {
            return true;
        }
        return pVar.U() && this.f3466z.C().K0();
    }

    private void L(p pVar) {
        if (pVar == null || !pVar.equals(f0(pVar.f3563f))) {
            return;
        }
        pVar.c1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void R0(Configuration configuration) {
        if (K0()) {
            z(configuration, false);
        }
    }

    private void S(int i8) {
        try {
            this.f3442b = true;
            this.f3443c.d(i8);
            X0(i8, false);
            for (y0 y0Var : t()) {
                y0Var.q();
            }
            this.f3442b = false;
            a0(true);
        } catch (Throwable th) {
            this.f3442b = false;
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S0(Integer num) {
        if (K0() && num.intValue() == 80) {
            F(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T0(androidx.core.app.g gVar) {
        if (K0()) {
            G(gVar.a(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void U0(androidx.core.app.p pVar) {
        if (K0()) {
            N(pVar.a(), false);
        }
    }

    private void V() {
        if (this.N) {
            this.N = false;
            u1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
        for (y0 y0Var : t()) {
            y0Var.q();
        }
    }

    private void Z(boolean z7) {
        if (this.f3442b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.f3464x == null) {
            if (!this.M) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        } else if (Looper.myLooper() != this.f3464x.h().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else {
            if (!z7) {
                q();
            }
            if (this.O == null) {
                this.O = new ArrayList();
                this.P = new ArrayList();
            }
        }
    }

    private static void c0(ArrayList arrayList, ArrayList arrayList2, int i8, int i9) {
        while (i8 < i9) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i8);
            if (((Boolean) arrayList2.get(i8)).booleanValue()) {
                aVar.n(-1);
                aVar.s();
            } else {
                aVar.n(1);
                aVar.r();
            }
            i8++;
        }
    }

    private void d0(ArrayList arrayList, ArrayList arrayList2, int i8, int i9) {
        boolean z7 = ((androidx.fragment.app.a) arrayList.get(i8)).f3633r;
        ArrayList arrayList3 = this.Q;
        if (arrayList3 == null) {
            this.Q = new ArrayList();
        } else {
            arrayList3.clear();
        }
        this.Q.addAll(this.f3443c.o());
        p z02 = z0();
        boolean z8 = false;
        for (int i10 = i8; i10 < i9; i10++) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList.get(i10);
            z02 = !((Boolean) arrayList2.get(i10)).booleanValue() ? aVar.t(this.Q, z02) : aVar.w(this.Q, z02);
            z8 = z8 || aVar.f3624i;
        }
        this.Q.clear();
        if (!z7 && this.f3463w >= 1) {
            for (int i11 = i8; i11 < i9; i11++) {
                Iterator it = ((androidx.fragment.app.a) arrayList.get(i11)).f3618c.iterator();
                while (it.hasNext()) {
                    p pVar = ((q0.a) it.next()).f3636b;
                    if (pVar != null && pVar.f3578u != null) {
                        this.f3443c.r(v(pVar));
                    }
                }
            }
        }
        c0(arrayList, arrayList2, i8, i9);
        boolean booleanValue = ((Boolean) arrayList2.get(i9 - 1)).booleanValue();
        if (z8 && !this.f3455o.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                linkedHashSet.addAll(n0((androidx.fragment.app.a) it2.next()));
            }
            if (this.f3448h == null) {
                Iterator it3 = this.f3455o.iterator();
                while (it3.hasNext()) {
                    androidx.appcompat.app.w.a(it3.next());
                    Iterator it4 = linkedHashSet.iterator();
                    if (it4.hasNext()) {
                        p pVar2 = (p) it4.next();
                        throw null;
                    }
                }
                Iterator it5 = this.f3455o.iterator();
                while (it5.hasNext()) {
                    androidx.appcompat.app.w.a(it5.next());
                    Iterator it6 = linkedHashSet.iterator();
                    if (it6.hasNext()) {
                        p pVar3 = (p) it6.next();
                        throw null;
                    }
                }
            }
        }
        for (int i12 = i8; i12 < i9; i12++) {
            androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) arrayList.get(i12);
            if (booleanValue) {
                for (int size = aVar2.f3618c.size() - 1; size >= 0; size--) {
                    p pVar4 = ((q0.a) aVar2.f3618c.get(size)).f3636b;
                    if (pVar4 != null) {
                        v(pVar4).m();
                    }
                }
            } else {
                Iterator it7 = aVar2.f3618c.iterator();
                while (it7.hasNext()) {
                    p pVar5 = ((q0.a) it7.next()).f3636b;
                    if (pVar5 != null) {
                        v(pVar5).m();
                    }
                }
            }
        }
        X0(this.f3463w, true);
        for (y0 y0Var : u(arrayList, i8, i9)) {
            y0Var.B(booleanValue);
            y0Var.x();
            y0Var.n();
        }
        while (i8 < i9) {
            androidx.fragment.app.a aVar3 = (androidx.fragment.app.a) arrayList.get(i8);
            if (((Boolean) arrayList2.get(i8)).booleanValue() && aVar3.f3352v >= 0) {
                aVar3.f3352v = -1;
            }
            aVar3.v();
            i8++;
        }
        if (z8) {
            k1();
        }
    }

    private boolean e1(String str, int i8, int i9) {
        a0(false);
        Z(true);
        p pVar = this.A;
        if (pVar == null || i8 >= 0 || str != null || !pVar.o().c1()) {
            boolean f12 = f1(this.O, this.P, str, i8, i9);
            if (f12) {
                this.f3442b = true;
                try {
                    j1(this.O, this.P);
                } finally {
                    r();
                }
            }
            w1();
            V();
            this.f3443c.b();
            return f12;
        }
        return true;
    }

    private int g0(String str, int i8, boolean z7) {
        if (this.f3444d.isEmpty()) {
            return -1;
        }
        if (str == null && i8 < 0) {
            if (z7) {
                return 0;
            }
            return this.f3444d.size() - 1;
        }
        int size = this.f3444d.size() - 1;
        while (size >= 0) {
            androidx.fragment.app.a aVar = (androidx.fragment.app.a) this.f3444d.get(size);
            if ((str != null && str.equals(aVar.u())) || (i8 >= 0 && i8 == aVar.f3352v)) {
                break;
            }
            size--;
        }
        if (size < 0) {
            return size;
        }
        if (!z7) {
            if (size == this.f3444d.size() - 1) {
                return -1;
            }
            return size + 1;
        }
        while (size > 0) {
            androidx.fragment.app.a aVar2 = (androidx.fragment.app.a) this.f3444d.get(size - 1);
            if ((str == null || !str.equals(aVar2.u())) && (i8 < 0 || i8 != aVar2.f3352v)) {
                return size;
            }
            size--;
        }
        return size;
    }

    private void j1(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i8 = 0;
        int i9 = 0;
        while (i8 < size) {
            if (!((androidx.fragment.app.a) arrayList.get(i8)).f3633r) {
                if (i9 != i8) {
                    d0(arrayList, arrayList2, i9, i8);
                }
                i9 = i8 + 1;
                if (((Boolean) arrayList2.get(i8)).booleanValue()) {
                    while (i9 < size && ((Boolean) arrayList2.get(i9)).booleanValue() && !((androidx.fragment.app.a) arrayList.get(i9)).f3633r) {
                        i9++;
                    }
                }
                d0(arrayList, arrayList2, i8, i9);
                i8 = i9 - 1;
            }
            i8++;
        }
        if (i9 != size) {
            d0(arrayList, arrayList2, i9, size);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i0 k0(View view) {
        u uVar;
        p l02 = l0(view);
        if (l02 != null) {
            if (l02.U()) {
                return l02.o();
            }
            throw new IllegalStateException("The Fragment " + l02 + " that owns View " + view + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
        }
        Context context = view.getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                uVar = null;
                break;
            } else if (context instanceof u) {
                uVar = (u) context;
                break;
            } else {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        if (uVar != null) {
            return uVar.getSupportFragmentManager();
        }
        throw new IllegalStateException("View " + view + " is not within a subclass of FragmentActivity.");
    }

    private void k1() {
        if (this.f3455o.size() <= 0) {
            return;
        }
        androidx.appcompat.app.w.a(this.f3455o.get(0));
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static p l0(View view) {
        while (view != null) {
            p C0 = C0(view);
            if (C0 != null) {
                return C0;
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    private void m0() {
        for (y0 y0Var : t()) {
            y0Var.r();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m1(int i8) {
        int i9 = 4097;
        if (i8 != 4097) {
            if (i8 != 8194) {
                i9 = 8197;
                if (i8 == 8197) {
                    return 4100;
                }
                if (i8 == 4099) {
                    return 4099;
                }
                if (i8 != 4100) {
                    return 0;
                }
            }
            return i9;
        }
        return 8194;
    }

    private boolean o0(ArrayList arrayList, ArrayList arrayList2) {
        synchronized (this.f3441a) {
            if (this.f3441a.isEmpty()) {
                return false;
            }
            int size = this.f3441a.size();
            boolean z7 = false;
            for (int i8 = 0; i8 < size; i8++) {
                z7 |= ((l) this.f3441a.get(i8)).a(arrayList, arrayList2);
            }
            this.f3441a.clear();
            this.f3464x.h().removeCallbacks(this.T);
            return z7;
        }
    }

    private void q() {
        if (P0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private l0 q0(p pVar) {
        return this.R.k(pVar);
    }

    private void r() {
        this.f3442b = false;
        this.P.clear();
        this.O.clear();
    }

    private void s() {
        a0 a0Var = this.f3464x;
        if (a0Var instanceof androidx.lifecycle.o0 ? this.f3443c.p().o() : a0Var.f() instanceof Activity ? !((Activity) this.f3464x.f()).isChangingConfigurations() : true) {
            for (androidx.fragment.app.c cVar : this.f3452l.values()) {
                for (String str : cVar.f3376a) {
                    this.f3443c.p().h(str, false);
                }
            }
        }
    }

    private ViewGroup s0(p pVar) {
        ViewGroup viewGroup = pVar.I;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (pVar.f3583z > 0 && this.f3465y.d()) {
            View c8 = this.f3465y.c(pVar.f3583z);
            if (c8 instanceof ViewGroup) {
                return (ViewGroup) c8;
            }
        }
        return null;
    }

    private void s1(p pVar) {
        ViewGroup s02 = s0(pVar);
        if (s02 == null || pVar.q() + pVar.t() + pVar.E() + pVar.F() <= 0) {
            return;
        }
        if (s02.getTag(r1.b.f11312c) == null) {
            s02.setTag(r1.b.f11312c, pVar);
        }
        ((p) s02.getTag(r1.b.f11312c)).t1(pVar.D());
    }

    private Set t() {
        HashSet hashSet = new HashSet();
        for (o0 o0Var : this.f3443c.k()) {
            ViewGroup viewGroup = o0Var.k().I;
            if (viewGroup != null) {
                hashSet.add(y0.v(viewGroup, A0()));
            }
        }
        return hashSet;
    }

    private void u1() {
        for (o0 o0Var : this.f3443c.k()) {
            a1(o0Var);
        }
    }

    private void v1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new v0("FragmentManager"));
        a0 a0Var = this.f3464x;
        try {
            if (a0Var != null) {
                a0Var.i("  ", null, printWriter, new String[0]);
            } else {
                W("  ", null, printWriter, new String[0]);
            }
        } catch (Exception e8) {
            Log.e("FragmentManager", "Failed dumping state", e8);
        }
        throw runtimeException;
    }

    private void w1() {
        synchronized (this.f3441a) {
            boolean z7 = true;
            if (!this.f3441a.isEmpty()) {
                this.f3450j.j(true);
                if (I0(3)) {
                    Log.d("FragmentManager", "FragmentManager " + this + " enabling OnBackPressedCallback, caused by non-empty pending actions");
                }
                return;
            }
            z7 = (p0() <= 0 || !N0(this.f3466z)) ? false : false;
            if (I0(3)) {
                Log.d("FragmentManager", "OnBackPressedCallback for FragmentManager " + this + " enabled state is " + z7);
            }
            this.f3450j.j(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean A(MenuItem menuItem) {
        if (this.f3463w < 1) {
            return false;
        }
        for (p pVar : this.f3443c.o()) {
            if (pVar != null && pVar.N0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public z0 A0() {
        z0 z0Var = this.D;
        if (z0Var != null) {
            return z0Var;
        }
        p pVar = this.f3466z;
        return pVar != null ? pVar.f3578u.A0() : this.E;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B() {
        this.K = false;
        this.L = false;
        this.R.q(false);
        S(1);
    }

    public c.C0146c B0() {
        return this.S;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean C(Menu menu, MenuInflater menuInflater) {
        if (this.f3463w < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z7 = false;
        for (p pVar : this.f3443c.o()) {
            if (pVar != null && M0(pVar) && pVar.P0(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(pVar);
                z7 = true;
            }
        }
        if (this.f3445e != null) {
            for (int i8 = 0; i8 < this.f3445e.size(); i8++) {
                p pVar2 = (p) this.f3445e.get(i8);
                if (arrayList == null || !arrayList.contains(pVar2)) {
                    pVar2.p0();
                }
            }
        }
        this.f3445e = arrayList;
        return z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D() {
        this.M = true;
        a0(true);
        X();
        s();
        S(-1);
        a0 a0Var = this.f3464x;
        if (a0Var instanceof androidx.core.content.d) {
            ((androidx.core.content.d) a0Var).removeOnTrimMemoryListener(this.f3459s);
        }
        a0 a0Var2 = this.f3464x;
        if (a0Var2 instanceof androidx.core.content.c) {
            ((androidx.core.content.c) a0Var2).removeOnConfigurationChangedListener(this.f3458r);
        }
        a0 a0Var3 = this.f3464x;
        if (a0Var3 instanceof androidx.core.app.n) {
            ((androidx.core.app.n) a0Var3).removeOnMultiWindowModeChangedListener(this.f3460t);
        }
        a0 a0Var4 = this.f3464x;
        if (a0Var4 instanceof androidx.core.app.o) {
            ((androidx.core.app.o) a0Var4).removeOnPictureInPictureModeChangedListener(this.f3461u);
        }
        a0 a0Var5 = this.f3464x;
        if ((a0Var5 instanceof androidx.core.view.w) && this.f3466z == null) {
            ((androidx.core.view.w) a0Var5).removeMenuProvider(this.f3462v);
        }
        this.f3464x = null;
        this.f3465y = null;
        this.f3466z = null;
        if (this.f3447g != null) {
            this.f3450j.h();
            this.f3447g = null;
        }
        e.c cVar = this.F;
        if (cVar != null) {
            cVar.c();
            this.G.c();
            this.H.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.lifecycle.n0 D0(p pVar) {
        return this.R.n(pVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void E() {
        S(1);
    }

    void E0() {
        a0(true);
        if (!V || this.f3448h == null) {
            if (this.f3450j.g()) {
                if (I0(3)) {
                    Log.d("FragmentManager", "Calling popBackStackImmediate via onBackPressed callback");
                }
                c1();
                return;
            }
            if (I0(3)) {
                Log.d("FragmentManager", "Calling onBackPressed via onBackPressed callback");
            }
            this.f3447g.k();
            return;
        }
        if (!this.f3455o.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(n0(this.f3448h));
            Iterator it = this.f3455o.iterator();
            while (it.hasNext()) {
                androidx.appcompat.app.w.a(it.next());
                Iterator it2 = linkedHashSet.iterator();
                if (it2.hasNext()) {
                    p pVar = (p) it2.next();
                    throw null;
                }
            }
        }
        Iterator it3 = this.f3448h.f3618c.iterator();
        while (it3.hasNext()) {
            p pVar2 = ((q0.a) it3.next()).f3636b;
            if (pVar2 != null) {
                pVar2.f3571n = false;
            }
        }
        for (y0 y0Var : u(new ArrayList(Collections.singletonList(this.f3448h)), 0, 1)) {
            y0Var.f();
        }
        this.f3448h = null;
        w1();
        if (I0(3)) {
            Log.d("FragmentManager", "Op is being set to null");
            Log.d("FragmentManager", "OnBackPressedCallback enabled=" + this.f3450j.g() + " for  FragmentManager " + this);
        }
    }

    void F(boolean z7) {
        if (z7 && (this.f3464x instanceof androidx.core.content.d)) {
            v1(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
        }
        for (p pVar : this.f3443c.o()) {
            if (pVar != null) {
                pVar.V0();
                if (z7) {
                    pVar.f3580w.F(true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F0(p pVar) {
        if (I0(2)) {
            Log.v("FragmentManager", "hide: " + pVar);
        }
        if (pVar.B) {
            return;
        }
        pVar.B = true;
        pVar.P = true ^ pVar.P;
        s1(pVar);
    }

    void G(boolean z7, boolean z8) {
        if (z8 && (this.f3464x instanceof androidx.core.app.n)) {
            v1(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
        }
        for (p pVar : this.f3443c.o()) {
            if (pVar != null) {
                pVar.W0(z7);
                if (z8) {
                    pVar.f3580w.G(z7, true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G0(p pVar) {
        if (pVar.f3569l && J0(pVar)) {
            this.J = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H(p pVar) {
        Iterator it = this.f3457q.iterator();
        while (it.hasNext()) {
            ((m0) it.next()).a(this, pVar);
        }
    }

    public boolean H0() {
        return this.M;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I() {
        for (p pVar : this.f3443c.l()) {
            if (pVar != null) {
                pVar.t0(pVar.V());
                pVar.f3580w.I();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean J(MenuItem menuItem) {
        if (this.f3463w < 1) {
            return false;
        }
        for (p pVar : this.f3443c.o()) {
            if (pVar != null && pVar.X0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void K(Menu menu) {
        if (this.f3463w < 1) {
            return;
        }
        for (p pVar : this.f3443c.o()) {
            if (pVar != null) {
                pVar.Y0(menu);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean L0(p pVar) {
        if (pVar == null) {
            return false;
        }
        return pVar.V();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void M() {
        S(5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean M0(p pVar) {
        if (pVar == null) {
            return true;
        }
        return pVar.X();
    }

    void N(boolean z7, boolean z8) {
        if (z8 && (this.f3464x instanceof androidx.core.app.o)) {
            v1(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
        }
        for (p pVar : this.f3443c.o()) {
            if (pVar != null) {
                pVar.a1(z7);
                if (z8) {
                    pVar.f3580w.N(z7, true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean N0(p pVar) {
        if (pVar == null) {
            return true;
        }
        i0 i0Var = pVar.f3578u;
        return pVar.equals(i0Var.z0()) && N0(i0Var.f3466z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean O(Menu menu) {
        boolean z7 = false;
        if (this.f3463w < 1) {
            return false;
        }
        for (p pVar : this.f3443c.o()) {
            if (pVar != null && M0(pVar) && pVar.b1(menu)) {
                z7 = true;
            }
        }
        return z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean O0(int i8) {
        return this.f3463w >= i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void P() {
        w1();
        L(this.A);
    }

    public boolean P0() {
        return this.K || this.L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Q() {
        this.K = false;
        this.L = false;
        this.R.q(false);
        S(7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void R() {
        this.K = false;
        this.L = false;
        this.R.q(false);
        S(5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void T() {
        this.L = true;
        this.R.q(true);
        S(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void U() {
        S(2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void V0(p pVar, Intent intent, int i8, Bundle bundle) {
        if (this.F == null) {
            this.f3464x.l(pVar, intent, i8, bundle);
            return;
        }
        this.I.addLast(new k(pVar.f3563f, i8));
        if (bundle != null) {
            intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        }
        this.F.a(intent);
    }

    public void W(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        String str2 = str + "    ";
        this.f3443c.e(str, fileDescriptor, printWriter, strArr);
        ArrayList arrayList = this.f3445e;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i8 = 0; i8 < size; i8++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i8);
                printWriter.print(": ");
                printWriter.println(((p) this.f3445e.get(i8)).toString());
            }
        }
        int size2 = this.f3444d.size();
        if (size2 > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i9 = 0; i9 < size2; i9++) {
                androidx.fragment.app.a aVar = (androidx.fragment.app.a) this.f3444d.get(i9);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i9);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.p(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.f3451k.get());
        synchronized (this.f3441a) {
            int size3 = this.f3441a.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i10 = 0; i10 < size3; i10++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i10);
                    printWriter.print(": ");
                    printWriter.println((l) this.f3441a.get(i10));
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f3464x);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f3465y);
        if (this.f3466z != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f3466z);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f3463w);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.K);
        printWriter.print(" mStopped=");
        printWriter.print(this.L);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.M);
        if (this.J) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.J);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void W0(p pVar, IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) {
        Intent intent2;
        if (this.G == null) {
            this.f3464x.m(pVar, intentSender, i8, intent, i9, i10, i11, bundle);
            return;
        }
        if (bundle != null) {
            if (intent == null) {
                intent2 = new Intent();
                intent2.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
            } else {
                intent2 = intent;
            }
            if (I0(2)) {
                Log.v("FragmentManager", "ActivityOptions " + bundle + " were added to fillInIntent " + intent2 + " for fragment " + pVar);
            }
            intent2.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        } else {
            intent2 = intent;
        }
        e.f a8 = new f.a(intentSender).b(intent2).c(i10, i9).a();
        this.I.addLast(new k(pVar.f3563f, i8));
        if (I0(2)) {
            Log.v("FragmentManager", "Fragment " + pVar + "is launching an IntentSender for result ");
        }
        this.G.a(a8);
    }

    void X0(int i8, boolean z7) {
        a0 a0Var;
        if (this.f3464x == null && i8 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z7 || i8 != this.f3463w) {
            this.f3463w = i8;
            this.f3443c.t();
            u1();
            if (this.J && (a0Var = this.f3464x) != null && this.f3463w == 7) {
                a0Var.n();
                this.J = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Y(l lVar, boolean z7) {
        if (!z7) {
            if (this.f3464x == null) {
                if (!this.M) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            q();
        }
        synchronized (this.f3441a) {
            if (this.f3464x == null) {
                if (!z7) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
                return;
            }
            this.f3441a.add(lVar);
            o1();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Y0() {
        if (this.f3464x == null) {
            return;
        }
        this.K = false;
        this.L = false;
        this.R.q(false);
        for (p pVar : this.f3443c.o()) {
            if (pVar != null) {
                pVar.c0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Z0(x xVar) {
        View view;
        for (o0 o0Var : this.f3443c.k()) {
            p k8 = o0Var.k();
            if (k8.f3583z == xVar.getId() && (view = k8.J) != null && view.getParent() == null) {
                k8.I = xVar;
                o0Var.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a0(boolean z7) {
        Z(z7);
        boolean z8 = false;
        while (o0(this.O, this.P)) {
            z8 = true;
            this.f3442b = true;
            try {
                j1(this.O, this.P);
            } finally {
                r();
            }
        }
        w1();
        V();
        this.f3443c.b();
        return z8;
    }

    void a1(o0 o0Var) {
        p k8 = o0Var.k();
        if (k8.K) {
            if (this.f3442b) {
                this.N = true;
                return;
            }
            k8.K = false;
            o0Var.m();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b0(l lVar, boolean z7) {
        if (z7 && (this.f3464x == null || this.M)) {
            return;
        }
        Z(z7);
        if (lVar.a(this.O, this.P)) {
            this.f3442b = true;
            try {
                j1(this.O, this.P);
            } finally {
                r();
            }
        }
        w1();
        V();
        this.f3443c.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b1(int i8, int i9, boolean z7) {
        if (i8 >= 0) {
            Y(new m(null, i8, i9), z7);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i8);
    }

    public boolean c1() {
        return e1(null, -1, 0);
    }

    public boolean d1(int i8, int i9) {
        if (i8 >= 0) {
            return e1(null, i8, i9);
        }
        throw new IllegalArgumentException("Bad id: " + i8);
    }

    public boolean e0() {
        boolean a02 = a0(true);
        m0();
        return a02;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p f0(String str) {
        return this.f3443c.f(str);
    }

    boolean f1(ArrayList arrayList, ArrayList arrayList2, String str, int i8, int i9) {
        int g02 = g0(str, i8, (i9 & 1) != 0);
        if (g02 < 0) {
            return false;
        }
        for (int size = this.f3444d.size() - 1; size >= g02; size--) {
            arrayList.add((androidx.fragment.app.a) this.f3444d.remove(size));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    boolean g1(ArrayList arrayList, ArrayList arrayList2) {
        ArrayList arrayList3 = this.f3444d;
        androidx.fragment.app.a aVar = (androidx.fragment.app.a) arrayList3.get(arrayList3.size() - 1);
        this.f3448h = aVar;
        Iterator it = aVar.f3618c.iterator();
        while (it.hasNext()) {
            p pVar = ((q0.a) it.next()).f3636b;
            if (pVar != null) {
                pVar.f3571n = true;
            }
        }
        return f1(arrayList, arrayList2, null, -1, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(androidx.fragment.app.a aVar) {
        this.f3444d.add(aVar);
    }

    public p h0(int i8) {
        return this.f3443c.g(i8);
    }

    void h1() {
        Y(new n(), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0 i(p pVar) {
        String str = pVar.S;
        if (str != null) {
            s1.c.f(pVar, str);
        }
        if (I0(2)) {
            Log.v("FragmentManager", "add: " + pVar);
        }
        o0 v7 = v(pVar);
        pVar.f3578u = this;
        this.f3443c.r(v7);
        if (!pVar.C) {
            this.f3443c.a(pVar);
            pVar.f3570m = false;
            if (pVar.J == null) {
                pVar.P = false;
            }
            if (J0(pVar)) {
                this.J = true;
            }
        }
        return v7;
    }

    public p i0(String str) {
        return this.f3443c.h(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i1(p pVar) {
        if (I0(2)) {
            Log.v("FragmentManager", "remove: " + pVar + " nesting=" + pVar.f3577t);
        }
        boolean z7 = !pVar.W();
        if (!pVar.C || z7) {
            this.f3443c.u(pVar);
            if (J0(pVar)) {
                this.J = true;
            }
            pVar.f3570m = true;
            s1(pVar);
        }
    }

    public void j(m0 m0Var) {
        this.f3457q.add(m0Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p j0(String str) {
        return this.f3443c.i(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k() {
        return this.f3451k.getAndIncrement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0150  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void l(androidx.fragment.app.a0 r4, androidx.fragment.app.w r5, androidx.fragment.app.p r6) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.i0.l(androidx.fragment.app.a0, androidx.fragment.app.w, androidx.fragment.app.p):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l1(Parcelable parcelable) {
        o0 o0Var;
        Bundle bundle;
        Bundle bundle2;
        if (parcelable == null) {
            return;
        }
        Bundle bundle3 = (Bundle) parcelable;
        for (String str : bundle3.keySet()) {
            if (str.startsWith("result_") && (bundle2 = bundle3.getBundle(str)) != null) {
                bundle2.setClassLoader(this.f3464x.f().getClassLoader());
                this.f3453m.put(str.substring(7), bundle2);
            }
        }
        HashMap hashMap = new HashMap();
        for (String str2 : bundle3.keySet()) {
            if (str2.startsWith("fragment_") && (bundle = bundle3.getBundle(str2)) != null) {
                bundle.setClassLoader(this.f3464x.f().getClassLoader());
                hashMap.put(str2.substring(9), bundle);
            }
        }
        this.f3443c.x(hashMap);
        k0 k0Var = (k0) bundle3.getParcelable("state");
        if (k0Var == null) {
            return;
        }
        this.f3443c.v();
        Iterator it = k0Var.f3490a.iterator();
        while (it.hasNext()) {
            Bundle B = this.f3443c.B((String) it.next(), null);
            if (B != null) {
                p j8 = this.R.j(((n0) B.getParcelable("state")).f3532b);
                if (j8 != null) {
                    if (I0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + j8);
                    }
                    o0Var = new o0(this.f3456p, this.f3443c, j8, B);
                } else {
                    o0Var = new o0(this.f3456p, this.f3443c, this.f3464x.f().getClassLoader(), t0(), B);
                }
                p k8 = o0Var.k();
                k8.f3557b = B;
                k8.f3578u = this;
                if (I0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + k8.f3563f + "): " + k8);
                }
                o0Var.o(this.f3464x.f().getClassLoader());
                this.f3443c.r(o0Var);
                o0Var.s(this.f3463w);
            }
        }
        for (p pVar : this.R.m()) {
            if (!this.f3443c.c(pVar.f3563f)) {
                if (I0(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + pVar + " that was not found in the set of active Fragments " + k0Var.f3490a);
                }
                this.R.p(pVar);
                pVar.f3578u = this;
                o0 o0Var2 = new o0(this.f3456p, this.f3443c, pVar);
                o0Var2.s(1);
                o0Var2.m();
                pVar.f3570m = true;
                o0Var2.m();
            }
        }
        this.f3443c.w(k0Var.f3491b);
        if (k0Var.f3492c != null) {
            this.f3444d = new ArrayList(k0Var.f3492c.length);
            int i8 = 0;
            while (true) {
                androidx.fragment.app.b[] bVarArr = k0Var.f3492c;
                if (i8 >= bVarArr.length) {
                    break;
                }
                androidx.fragment.app.a b8 = bVarArr[i8].b(this);
                if (I0(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i8 + " (index " + b8.f3352v + "): " + b8);
                    PrintWriter printWriter = new PrintWriter(new v0("FragmentManager"));
                    b8.q("  ", printWriter, false);
                    printWriter.close();
                }
                this.f3444d.add(b8);
                i8++;
            }
        } else {
            this.f3444d = new ArrayList();
        }
        this.f3451k.set(k0Var.f3493d);
        String str3 = k0Var.f3494e;
        if (str3 != null) {
            p f02 = f0(str3);
            this.A = f02;
            L(f02);
        }
        ArrayList arrayList = k0Var.f3495f;
        if (arrayList != null) {
            for (int i9 = 0; i9 < arrayList.size(); i9++) {
                this.f3452l.put((String) arrayList.get(i9), (androidx.fragment.app.c) k0Var.f3496g.get(i9));
            }
        }
        this.I = new ArrayDeque(k0Var.f3497h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(p pVar) {
        if (I0(2)) {
            Log.v("FragmentManager", "attach: " + pVar);
        }
        if (pVar.C) {
            pVar.C = false;
            if (pVar.f3569l) {
                return;
            }
            this.f3443c.a(pVar);
            if (I0(2)) {
                Log.v("FragmentManager", "add from attach: " + pVar);
            }
            if (J0(pVar)) {
                this.J = true;
            }
        }
    }

    public q0 n() {
        return new androidx.fragment.app.a(this);
    }

    Set n0(androidx.fragment.app.a aVar) {
        HashSet hashSet = new HashSet();
        for (int i8 = 0; i8 < aVar.f3618c.size(); i8++) {
            p pVar = ((q0.a) aVar.f3618c.get(i8)).f3636b;
            if (pVar != null && aVar.f3624i) {
                hashSet.add(pVar);
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: n1 */
    public Bundle Q0() {
        androidx.fragment.app.b[] bVarArr;
        Bundle bundle = new Bundle();
        m0();
        X();
        a0(true);
        this.K = true;
        this.R.q(true);
        ArrayList y7 = this.f3443c.y();
        HashMap m8 = this.f3443c.m();
        if (!m8.isEmpty()) {
            ArrayList z7 = this.f3443c.z();
            int size = this.f3444d.size();
            if (size > 0) {
                bVarArr = new androidx.fragment.app.b[size];
                for (int i8 = 0; i8 < size; i8++) {
                    bVarArr[i8] = new androidx.fragment.app.b((androidx.fragment.app.a) this.f3444d.get(i8));
                    if (I0(2)) {
                        Log.v("FragmentManager", "saveAllState: adding back stack #" + i8 + ": " + this.f3444d.get(i8));
                    }
                }
            } else {
                bVarArr = null;
            }
            k0 k0Var = new k0();
            k0Var.f3490a = y7;
            k0Var.f3491b = z7;
            k0Var.f3492c = bVarArr;
            k0Var.f3493d = this.f3451k.get();
            p pVar = this.A;
            if (pVar != null) {
                k0Var.f3494e = pVar.f3563f;
            }
            k0Var.f3495f.addAll(this.f3452l.keySet());
            k0Var.f3496g.addAll(this.f3452l.values());
            k0Var.f3497h = new ArrayList(this.I);
            bundle.putParcelable("state", k0Var);
            for (String str : this.f3453m.keySet()) {
                bundle.putBundle("result_" + str, (Bundle) this.f3453m.get(str));
            }
            for (String str2 : m8.keySet()) {
                bundle.putBundle("fragment_" + str2, (Bundle) m8.get(str2));
            }
        } else if (I0(2)) {
            Log.v("FragmentManager", "saveAllState: no fragments!");
        }
        return bundle;
    }

    void o() {
        androidx.fragment.app.a aVar = this.f3448h;
        if (aVar != null) {
            aVar.f3351u = false;
            aVar.f();
            e0();
            Iterator it = this.f3455o.iterator();
            if (it.hasNext()) {
                androidx.appcompat.app.w.a(it.next());
                throw null;
            }
        }
    }

    void o1() {
        synchronized (this.f3441a) {
            boolean z7 = true;
            if (this.f3441a.size() != 1) {
                z7 = false;
            }
            if (z7) {
                this.f3464x.h().removeCallbacks(this.T);
                this.f3464x.h().post(this.T);
                w1();
            }
        }
    }

    boolean p() {
        boolean z7 = false;
        for (p pVar : this.f3443c.l()) {
            if (pVar != null) {
                z7 = J0(pVar);
                continue;
            }
            if (z7) {
                return true;
            }
        }
        return false;
    }

    public int p0() {
        return this.f3444d.size() + (this.f3448h != null ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p1(p pVar, boolean z7) {
        ViewGroup s02 = s0(pVar);
        if (s02 == null || !(s02 instanceof x)) {
            return;
        }
        ((x) s02).setDrawDisappearingViewsLast(!z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q1(p pVar, i.b bVar) {
        if (pVar.equals(f0(pVar.f3563f)) && (pVar.f3579v == null || pVar.f3578u == this)) {
            pVar.T = bVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + pVar + " is not an active fragment of FragmentManager " + this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public w r0() {
        return this.f3465y;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r1(p pVar) {
        if (pVar == null || (pVar.equals(f0(pVar.f3563f)) && (pVar.f3579v == null || pVar.f3578u == this))) {
            p pVar2 = this.A;
            this.A = pVar;
            L(pVar2);
            L(this.A);
            return;
        }
        throw new IllegalArgumentException("Fragment " + pVar + " is not an active fragment of FragmentManager " + this);
    }

    public z t0() {
        z zVar = this.B;
        if (zVar != null) {
            return zVar;
        }
        p pVar = this.f3466z;
        return pVar != null ? pVar.f3578u.t0() : this.C;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t1(p pVar) {
        if (I0(2)) {
            Log.v("FragmentManager", "show: " + pVar);
        }
        if (pVar.B) {
            pVar.B = false;
            pVar.P = !pVar.P;
        }
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        p pVar = this.f3466z;
        if (pVar != null) {
            sb.append(pVar.getClass().getSimpleName());
            sb.append("{");
            obj = this.f3466z;
        } else {
            a0 a0Var = this.f3464x;
            if (a0Var == null) {
                sb.append("null");
                sb.append("}}");
                return sb.toString();
            }
            sb.append(a0Var.getClass().getSimpleName());
            sb.append("{");
            obj = this.f3464x;
        }
        sb.append(Integer.toHexString(System.identityHashCode(obj)));
        sb.append("}");
        sb.append("}}");
        return sb.toString();
    }

    Set u(ArrayList arrayList, int i8, int i9) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i8 < i9) {
            Iterator it = ((androidx.fragment.app.a) arrayList.get(i8)).f3618c.iterator();
            while (it.hasNext()) {
                p pVar = ((q0.a) it.next()).f3636b;
                if (pVar != null && (viewGroup = pVar.I) != null) {
                    hashSet.add(y0.u(viewGroup, this));
                }
            }
            i8++;
        }
        return hashSet;
    }

    public List u0() {
        return this.f3443c.o();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0 v(p pVar) {
        o0 n8 = this.f3443c.n(pVar.f3563f);
        if (n8 != null) {
            return n8;
        }
        o0 o0Var = new o0(this.f3456p, this.f3443c, pVar);
        o0Var.o(this.f3464x.f().getClassLoader());
        o0Var.s(this.f3463w);
        return o0Var;
    }

    public a0 v0() {
        return this.f3464x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(p pVar) {
        if (I0(2)) {
            Log.v("FragmentManager", "detach: " + pVar);
        }
        if (pVar.C) {
            return;
        }
        pVar.C = true;
        if (pVar.f3569l) {
            if (I0(2)) {
                Log.v("FragmentManager", "remove from detach: " + pVar);
            }
            this.f3443c.u(pVar);
            if (J0(pVar)) {
                this.J = true;
            }
            s1(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LayoutInflater.Factory2 w0() {
        return this.f3446f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x() {
        this.K = false;
        this.L = false;
        this.R.q(false);
        S(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0 x0() {
        return this.f3456p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y() {
        this.K = false;
        this.L = false;
        this.R.q(false);
        S(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p y0() {
        return this.f3466z;
    }

    void z(Configuration configuration, boolean z7) {
        if (z7 && (this.f3464x instanceof androidx.core.content.c)) {
            v1(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
        }
        for (p pVar : this.f3443c.o()) {
            if (pVar != null) {
                pVar.M0(configuration);
                if (z7) {
                    pVar.f3580w.z(configuration, true);
                }
            }
        }
    }

    public p z0() {
        return this.A;
    }
}
