package io.flutter.embedding.engine;

import a6.a;
import android.content.Context;
import io.flutter.embedding.engine.a;
import io.flutter.plugin.platform.w;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    final List f7761a = new ArrayList();

    /* loaded from: classes.dex */
    class a implements a.b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ io.flutter.embedding.engine.a f7762a;

        a(io.flutter.embedding.engine.a aVar) {
            this.f7762a = aVar;
        }

        @Override // io.flutter.embedding.engine.a.b
        public void a() {
            d.this.f7761a.remove(this.f7762a);
        }

        @Override // io.flutter.embedding.engine.a.b
        public void b() {
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private Context f7764a;

        /* renamed from: b  reason: collision with root package name */
        private a.b f7765b;

        /* renamed from: c  reason: collision with root package name */
        private String f7766c;

        /* renamed from: d  reason: collision with root package name */
        private List f7767d;

        /* renamed from: e  reason: collision with root package name */
        private w f7768e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f7769f = true;

        /* renamed from: g  reason: collision with root package name */
        private boolean f7770g = false;

        public b(Context context) {
            this.f7764a = context;
        }

        public boolean a() {
            return this.f7769f;
        }

        public Context b() {
            return this.f7764a;
        }

        public a.b c() {
            return this.f7765b;
        }

        public List d() {
            return this.f7767d;
        }

        public String e() {
            return this.f7766c;
        }

        public w f() {
            return this.f7768e;
        }

        public boolean g() {
            return this.f7770g;
        }

        public b h(boolean z7) {
            this.f7769f = z7;
            return this;
        }

        public b i(a.b bVar) {
            this.f7765b = bVar;
            return this;
        }

        public b j(List list) {
            this.f7767d = list;
            return this;
        }

        public b k(String str) {
            this.f7766c = str;
            return this;
        }

        public b l(boolean z7) {
            this.f7770g = z7;
            return this;
        }
    }

    public d(Context context, String[] strArr) {
        c6.d c8 = z5.a.e().c();
        if (c8.k()) {
            return;
        }
        c8.m(context.getApplicationContext());
        c8.f(context.getApplicationContext(), strArr);
    }

    public io.flutter.embedding.engine.a a(b bVar) {
        io.flutter.embedding.engine.a A;
        Context b8 = bVar.b();
        a.b c8 = bVar.c();
        String e8 = bVar.e();
        List d8 = bVar.d();
        w f8 = bVar.f();
        if (f8 == null) {
            f8 = new w();
        }
        w wVar = f8;
        boolean a8 = bVar.a();
        boolean g8 = bVar.g();
        a.b a9 = c8 == null ? a.b.a() : c8;
        if (this.f7761a.size() == 0) {
            A = b(b8, wVar, a8, g8);
            if (e8 != null) {
                A.o().c(e8);
            }
            A.k().i(a9, d8);
        } else {
            A = ((io.flutter.embedding.engine.a) this.f7761a.get(0)).A(b8, a9, e8, d8, wVar, a8, g8);
        }
        this.f7761a.add(A);
        A.e(new a(A));
        return A;
    }

    io.flutter.embedding.engine.a b(Context context, w wVar, boolean z7, boolean z8) {
        return new io.flutter.embedding.engine.a(context, null, null, wVar, null, z7, z8, this);
    }
}
