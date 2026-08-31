package y5;

import i6.i;
import i6.j;
/* loaded from: classes.dex */
public class d extends y5.a {

    /* renamed from: a  reason: collision with root package name */
    public final a f13359a;

    /* renamed from: b  reason: collision with root package name */
    final i f13360b;

    /* loaded from: classes.dex */
    class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final j.d f13361a;

        a(j.d dVar) {
            this.f13361a = dVar;
        }

        @Override // y5.f
        public void error(String str, String str2, Object obj) {
            this.f13361a.error(str, str2, obj);
        }

        @Override // y5.f
        public void success(Object obj) {
            this.f13361a.success(obj);
        }
    }

    public d(i iVar, j.d dVar) {
        this.f13360b = iVar;
        this.f13359a = new a(dVar);
    }

    @Override // y5.e
    public Object a(String str) {
        return this.f13360b.a(str);
    }

    @Override // y5.e
    public String g() {
        return this.f13360b.f7535a;
    }

    @Override // y5.e
    public boolean h(String str) {
        return this.f13360b.c(str);
    }

    @Override // y5.a
    public f m() {
        return this.f13359a;
    }
}
