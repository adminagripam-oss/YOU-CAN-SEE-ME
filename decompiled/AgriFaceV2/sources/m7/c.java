package m7;

import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.face.ModuleDescriptor;
import p7.h0;
import p7.k0;
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    private static final j f9660a = new j(-1, null, null, 0);

    /* renamed from: b  reason: collision with root package name */
    public static final int f9661b;

    /* renamed from: c  reason: collision with root package name */
    private static final int f9662c;

    /* renamed from: d  reason: collision with root package name */
    public static final h0 f9663d;

    /* renamed from: e  reason: collision with root package name */
    private static final h0 f9664e;

    /* renamed from: f  reason: collision with root package name */
    private static final h0 f9665f;

    /* renamed from: g  reason: collision with root package name */
    private static final h0 f9666g;

    /* renamed from: h  reason: collision with root package name */
    private static final h0 f9667h;

    /* renamed from: i  reason: collision with root package name */
    private static final h0 f9668i;

    /* renamed from: j  reason: collision with root package name */
    private static final h0 f9669j;

    /* renamed from: k  reason: collision with root package name */
    private static final h0 f9670k;

    /* renamed from: l  reason: collision with root package name */
    private static final h0 f9671l;

    /* renamed from: m  reason: collision with root package name */
    private static final h0 f9672m;

    /* renamed from: n  reason: collision with root package name */
    private static final h0 f9673n;

    /* renamed from: o  reason: collision with root package name */
    private static final h0 f9674o;

    /* renamed from: p  reason: collision with root package name */
    private static final h0 f9675p;

    /* renamed from: q  reason: collision with root package name */
    private static final h0 f9676q;

    /* renamed from: r  reason: collision with root package name */
    private static final h0 f9677r;

    /* renamed from: s  reason: collision with root package name */
    private static final h0 f9678s;

    /* loaded from: classes.dex */
    /* synthetic */ class a extends d7.j implements c7.p {

        /* renamed from: n  reason: collision with root package name */
        public static final a f9679n = new a();

        a() {
            super(2, c.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);
        }

        @Override // c7.p
        public /* bridge */ /* synthetic */ Object f(Object obj, Object obj2) {
            return l(((Number) obj).longValue(), (j) obj2);
        }

        public final j l(long j8, j jVar) {
            return c.x(j8, jVar);
        }
    }

    static {
        int e8;
        int e9;
        e8 = k0.e("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12, null);
        f9661b = e8;
        e9 = k0.e("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", ModuleDescriptor.MODULE_VERSION, 0, 0, 12, null);
        f9662c = e9;
        f9663d = new h0("BUFFERED");
        f9664e = new h0("SHOULD_BUFFER");
        f9665f = new h0("S_RESUMING_BY_RCV");
        f9666g = new h0("RESUMING_BY_EB");
        f9667h = new h0("POISONED");
        f9668i = new h0("DONE_RCV");
        f9669j = new h0("INTERRUPTED_SEND");
        f9670k = new h0("INTERRUPTED_RCV");
        f9671l = new h0("CHANNEL_CLOSED");
        f9672m = new h0("SUSPEND");
        f9673n = new h0("SUSPEND_NO_WAITER");
        f9674o = new h0("FAILED");
        f9675p = new h0("NO_RECEIVE_RESULT");
        f9676q = new h0("CLOSE_HANDLER_CLOSED");
        f9677r = new h0("CLOSE_HANDLER_INVOKED");
        f9678s = new h0("NO_CLOSE_CAUSE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long A(int i8) {
        if (i8 != 0) {
            if (i8 != Integer.MAX_VALUE) {
                return i8;
            }
            return Long.MAX_VALUE;
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean B(k7.j jVar, Object obj, c7.l lVar) {
        Object k8 = jVar.k(obj, null, lVar);
        if (k8 != null) {
            jVar.p(k8);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean C(k7.j jVar, Object obj, c7.l lVar, int i8, Object obj2) {
        if ((i8 & 2) != 0) {
            lVar = null;
        }
        return B(jVar, obj, lVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long v(long j8, boolean z7) {
        return (z7 ? 4611686018427387904L : 0L) + j8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long w(long j8, int i8) {
        return (i8 << 60) + j8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final j x(long j8, j jVar) {
        return new j(j8, jVar, jVar.u(), 0);
    }

    public static final h7.e y() {
        return a.f9679n;
    }

    public static final h0 z() {
        return f9671l;
    }
}
