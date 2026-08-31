package y5;

import i6.j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class c extends y5.a {

    /* renamed from: a  reason: collision with root package name */
    final Map f13351a;

    /* renamed from: b  reason: collision with root package name */
    final a f13352b = new a();

    /* renamed from: c  reason: collision with root package name */
    final boolean f13353c;

    /* loaded from: classes.dex */
    public class a implements f {

        /* renamed from: a  reason: collision with root package name */
        Object f13354a;

        /* renamed from: b  reason: collision with root package name */
        String f13355b;

        /* renamed from: c  reason: collision with root package name */
        String f13356c;

        /* renamed from: d  reason: collision with root package name */
        Object f13357d;

        public a() {
        }

        @Override // y5.f
        public void error(String str, String str2, Object obj) {
            this.f13355b = str;
            this.f13356c = str2;
            this.f13357d = obj;
        }

        @Override // y5.f
        public void success(Object obj) {
            this.f13354a = obj;
        }
    }

    public c(Map map, boolean z7) {
        this.f13351a = map;
        this.f13353c = z7;
    }

    @Override // y5.e
    public Object a(String str) {
        return this.f13351a.get(str);
    }

    @Override // y5.b, y5.e
    public boolean c() {
        return this.f13353c;
    }

    @Override // y5.e
    public String g() {
        return (String) this.f13351a.get("method");
    }

    @Override // y5.e
    public boolean h(String str) {
        return this.f13351a.containsKey(str);
    }

    @Override // y5.a
    public f m() {
        return this.f13352b;
    }

    public Map n() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("code", this.f13352b.f13355b);
        hashMap2.put("message", this.f13352b.f13356c);
        hashMap2.put("data", this.f13352b.f13357d);
        hashMap.put("error", hashMap2);
        return hashMap;
    }

    public Map o() {
        HashMap hashMap = new HashMap();
        hashMap.put("result", this.f13352b.f13354a);
        return hashMap;
    }

    public void p(j.d dVar) {
        a aVar = this.f13352b;
        dVar.error(aVar.f13355b, aVar.f13356c, aVar.f13357d);
    }

    public void q(List list) {
        if (c()) {
            return;
        }
        list.add(n());
    }

    public void r(List list) {
        if (c()) {
            return;
        }
        list.add(o());
    }
}
