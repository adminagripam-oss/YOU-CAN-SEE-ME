package u6;

import c7.p;
import d7.k;
import d7.v;
import r6.l;
import t6.g;
import v6.h;
import v6.j;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class c {

    /* loaded from: classes.dex */
    public static final class a extends j {

        /* renamed from: f  reason: collision with root package name */
        private int f12278f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ p f12279g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Object f12280h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(t6.d dVar, p pVar, Object obj) {
            super(dVar);
            this.f12279g = pVar;
            this.f12280h = obj;
            k.c(dVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        }

        @Override // v6.a
        protected Object l(Object obj) {
            int i8 = this.f12278f;
            if (i8 == 0) {
                this.f12278f = 1;
                l.b(obj);
                k.c(this.f12279g, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                return ((p) v.a(this.f12279g, 2)).f(this.f12280h, this);
            } else if (i8 == 1) {
                this.f12278f = 2;
                l.b(obj);
                return obj;
            } else {
                throw new IllegalStateException("This coroutine had already completed".toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends v6.d {

        /* renamed from: h  reason: collision with root package name */
        private int f12281h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ p f12282i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Object f12283j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(t6.d dVar, g gVar, p pVar, Object obj) {
            super(dVar, gVar);
            this.f12282i = pVar;
            this.f12283j = obj;
            k.c(dVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        }

        @Override // v6.a
        protected Object l(Object obj) {
            int i8 = this.f12281h;
            if (i8 == 0) {
                this.f12281h = 1;
                l.b(obj);
                k.c(this.f12282i, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                return ((p) v.a(this.f12282i, 2)).f(this.f12283j, this);
            } else if (i8 == 1) {
                this.f12281h = 2;
                l.b(obj);
                return obj;
            } else {
                throw new IllegalStateException("This coroutine had already completed".toString());
            }
        }
    }

    public static t6.d a(p pVar, Object obj, t6.d dVar) {
        k.e(pVar, "<this>");
        k.e(dVar, "completion");
        t6.d a8 = h.a(dVar);
        if (pVar instanceof v6.a) {
            return ((v6.a) pVar).b(obj, a8);
        }
        g context = a8.getContext();
        return context == t6.h.f11916e ? new a(a8, pVar, obj) : new b(a8, context, pVar, obj);
    }

    public static t6.d b(t6.d dVar) {
        t6.d n8;
        k.e(dVar, "<this>");
        v6.d dVar2 = dVar instanceof v6.d ? (v6.d) dVar : null;
        return (dVar2 == null || (n8 = dVar2.n()) == null) ? dVar : n8;
    }
}
