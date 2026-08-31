package u2;

import android.content.Context;
import b3.c0;
import b3.i0;
import u2.s;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends s {

    /* renamed from: e  reason: collision with root package name */
    private q6.a f12061e;

    /* renamed from: f  reason: collision with root package name */
    private q6.a f12062f;

    /* renamed from: g  reason: collision with root package name */
    private q6.a f12063g;

    /* renamed from: h  reason: collision with root package name */
    private q6.a f12064h;

    /* renamed from: i  reason: collision with root package name */
    private q6.a f12065i;

    /* renamed from: j  reason: collision with root package name */
    private q6.a f12066j;

    /* renamed from: k  reason: collision with root package name */
    private q6.a f12067k;

    /* renamed from: l  reason: collision with root package name */
    private q6.a f12068l;

    /* renamed from: m  reason: collision with root package name */
    private q6.a f12069m;

    /* renamed from: n  reason: collision with root package name */
    private q6.a f12070n;

    /* renamed from: o  reason: collision with root package name */
    private q6.a f12071o;

    /* renamed from: p  reason: collision with root package name */
    private q6.a f12072p;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b implements s.a {

        /* renamed from: a  reason: collision with root package name */
        private Context f12073a;

        private b() {
        }

        @Override // u2.s.a
        public s a() {
            w2.d.a(this.f12073a, Context.class);
            return new d(this.f12073a);
        }

        @Override // u2.s.a
        /* renamed from: c */
        public b b(Context context) {
            this.f12073a = (Context) w2.d.b(context);
            return this;
        }
    }

    private d(Context context) {
        f(context);
    }

    public static s.a d() {
        return new b();
    }

    private void f(Context context) {
        this.f12061e = w2.a.a(j.a());
        w2.b a8 = w2.c.a(context);
        this.f12062f = a8;
        v2.j a9 = v2.j.a(a8, d3.c.a(), d3.d.a());
        this.f12063g = a9;
        this.f12064h = w2.a.a(v2.l.a(this.f12062f, a9));
        this.f12065i = i0.a(this.f12062f, b3.f.a(), b3.g.a());
        this.f12066j = w2.a.a(c0.a(d3.c.a(), d3.d.a(), b3.h.a(), this.f12065i));
        z2.g b8 = z2.g.b(d3.c.a());
        this.f12067k = b8;
        z2.i a10 = z2.i.a(this.f12062f, this.f12066j, b8, d3.d.a());
        this.f12068l = a10;
        q6.a aVar = this.f12061e;
        q6.a aVar2 = this.f12064h;
        q6.a aVar3 = this.f12066j;
        this.f12069m = z2.d.a(aVar, aVar2, a10, aVar3, aVar3);
        q6.a aVar4 = this.f12062f;
        q6.a aVar5 = this.f12064h;
        q6.a aVar6 = this.f12066j;
        this.f12070n = a3.k.a(aVar4, aVar5, aVar6, this.f12068l, this.f12061e, aVar6, d3.c.a());
        q6.a aVar7 = this.f12061e;
        q6.a aVar8 = this.f12066j;
        this.f12071o = a3.o.a(aVar7, aVar8, this.f12068l, aVar8);
        this.f12072p = w2.a.a(t.a(d3.c.a(), d3.d.a(), this.f12069m, this.f12070n, this.f12071o));
    }

    @Override // u2.s
    b3.c b() {
        return (b3.c) this.f12066j.get();
    }

    @Override // u2.s
    r c() {
        return (r) this.f12072p.get();
    }
}
