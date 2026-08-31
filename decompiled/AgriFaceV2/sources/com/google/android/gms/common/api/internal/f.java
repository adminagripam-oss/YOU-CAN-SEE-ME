package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.c;
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public final e f5012a;

    /* renamed from: b  reason: collision with root package name */
    public final h f5013b;

    /* renamed from: c  reason: collision with root package name */
    public final Runnable f5014c;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private j3.i f5015a;

        /* renamed from: b  reason: collision with root package name */
        private j3.i f5016b;

        /* renamed from: d  reason: collision with root package name */
        private c f5018d;

        /* renamed from: e  reason: collision with root package name */
        private h3.c[] f5019e;

        /* renamed from: g  reason: collision with root package name */
        private int f5021g;

        /* renamed from: c  reason: collision with root package name */
        private Runnable f5017c = new Runnable() { // from class: j3.u
            @Override // java.lang.Runnable
            public final void run() {
            }
        };

        /* renamed from: f  reason: collision with root package name */
        private boolean f5020f = true;

        /* synthetic */ a(j3.v vVar) {
        }

        public f a() {
            k3.q.b(this.f5015a != null, "Must set register function");
            k3.q.b(this.f5016b != null, "Must set unregister function");
            k3.q.b(this.f5018d != null, "Must set holder");
            return new f(new x(this, this.f5018d, this.f5019e, this.f5020f, this.f5021g), new y(this, (c.a) k3.q.h(this.f5018d.b(), "Key must not be null")), this.f5017c, null);
        }

        public a b(j3.i iVar) {
            this.f5015a = iVar;
            return this;
        }

        public a c(int i8) {
            this.f5021g = i8;
            return this;
        }

        public a d(j3.i iVar) {
            this.f5016b = iVar;
            return this;
        }

        public a e(c cVar) {
            this.f5018d = cVar;
            return this;
        }
    }

    /* synthetic */ f(e eVar, h hVar, Runnable runnable, j3.w wVar) {
        this.f5012a = eVar;
        this.f5013b = hVar;
        this.f5014c = runnable;
    }

    public static a a() {
        return new a(null);
    }
}
