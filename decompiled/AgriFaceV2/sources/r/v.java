package r;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class v implements b0.f0 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f10814a;

    /* renamed from: b  reason: collision with root package name */
    private final z.a f10815b;

    /* renamed from: c  reason: collision with root package name */
    private final b0.p0 f10816c;

    /* renamed from: d  reason: collision with root package name */
    private final b0.o0 f10817d;

    /* renamed from: e  reason: collision with root package name */
    private final s.o0 f10818e;

    /* renamed from: f  reason: collision with root package name */
    private final List f10819f;

    /* renamed from: g  reason: collision with root package name */
    private final d3 f10820g;

    /* renamed from: h  reason: collision with root package name */
    private final long f10821h;

    /* renamed from: i  reason: collision with root package name */
    private final Map f10822i = new HashMap();

    public v(Context context, b0.p0 p0Var, y.q qVar, long j8) {
        this.f10814a = context;
        this.f10816c = p0Var;
        s.o0 b8 = s.o0.b(context, p0Var.c());
        this.f10818e = b8;
        this.f10820g = d3.c(context);
        this.f10819f = e(l2.b(this, qVar));
        w.a aVar = new w.a(b8);
        this.f10815b = aVar;
        b0.o0 o0Var = new b0.o0(aVar, 1);
        this.f10817d = o0Var;
        aVar.d(o0Var);
        this.f10821h = j8;
    }

    private List e(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.equals("0") || str.equals("1") || k2.a(this.f10818e, str)) {
                arrayList.add(str);
            } else {
                y.f1.a("Camera2CameraFactory", "Camera " + str + " is filtered out because its capabilities do not contain REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE.");
            }
        }
        return arrayList;
    }

    @Override // b0.f0
    public b0.h0 a(String str) {
        if (this.f10819f.contains(str)) {
            return new l0(this.f10814a, this.f10818e, str, f(str), this.f10815b, this.f10817d, this.f10816c.b(), this.f10816c.c(), this.f10820g, this.f10821h);
        }
        throw new IllegalArgumentException("The given camera id is not on the available camera id list.");
    }

    @Override // b0.f0
    public Set c() {
        return new LinkedHashSet(this.f10819f);
    }

    @Override // b0.f0
    public z.a d() {
        return this.f10815b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r0 f(String str) {
        try {
            r0 r0Var = (r0) this.f10822i.get(str);
            if (r0Var == null) {
                r0 r0Var2 = new r0(str, this.f10818e);
                this.f10822i.put(str, r0Var2);
                return r0Var2;
            }
            return r0Var;
        } catch (s.g e8) {
            throw n2.a(e8);
        }
    }

    @Override // b0.f0
    /* renamed from: g */
    public s.o0 b() {
        return this.f10818e;
    }
}
