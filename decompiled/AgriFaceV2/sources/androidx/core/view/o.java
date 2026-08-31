package androidx.core.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
/* loaded from: classes.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private final Context f3148a;

    /* renamed from: b  reason: collision with root package name */
    private final p f3149b;

    /* renamed from: c  reason: collision with root package name */
    private final b f3150c;

    /* renamed from: d  reason: collision with root package name */
    private final a f3151d;

    /* renamed from: e  reason: collision with root package name */
    private VelocityTracker f3152e;

    /* renamed from: f  reason: collision with root package name */
    private float f3153f;

    /* renamed from: g  reason: collision with root package name */
    private int f3154g;

    /* renamed from: h  reason: collision with root package name */
    private int f3155h;

    /* renamed from: i  reason: collision with root package name */
    private int f3156i;

    /* renamed from: j  reason: collision with root package name */
    private final int[] f3157j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface a {
        float a(VelocityTracker velocityTracker, MotionEvent motionEvent, int i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        void a(Context context, int[] iArr, MotionEvent motionEvent, int i8);
    }

    public o(Context context, p pVar) {
        this(context, pVar, new b() { // from class: androidx.core.view.m
            @Override // androidx.core.view.o.b
            public final void a(Context context2, int[] iArr, MotionEvent motionEvent, int i8) {
                o.c(context2, iArr, motionEvent, i8);
            }
        }, new a() { // from class: androidx.core.view.n
            @Override // androidx.core.view.o.a
            public final float a(VelocityTracker velocityTracker, MotionEvent motionEvent, int i8) {
                float f8;
                f8 = o.f(velocityTracker, motionEvent, i8);
                return f8;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, int[] iArr, MotionEvent motionEvent, int i8) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        iArr[0] = t0.f(context, viewConfiguration, motionEvent.getDeviceId(), i8, motionEvent.getSource());
        iArr[1] = t0.e(context, viewConfiguration, motionEvent.getDeviceId(), i8, motionEvent.getSource());
    }

    private boolean d(MotionEvent motionEvent, int i8) {
        int source = motionEvent.getSource();
        int deviceId = motionEvent.getDeviceId();
        if (this.f3155h == source && this.f3156i == deviceId && this.f3154g == i8) {
            return false;
        }
        this.f3150c.a(this.f3148a, this.f3157j, motionEvent, i8);
        this.f3155h = source;
        this.f3156i = deviceId;
        this.f3154g = i8;
        return true;
    }

    private float e(MotionEvent motionEvent, int i8) {
        if (this.f3152e == null) {
            this.f3152e = VelocityTracker.obtain();
        }
        return this.f3151d.a(this.f3152e, motionEvent, i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float f(VelocityTracker velocityTracker, MotionEvent motionEvent, int i8) {
        m0.a(velocityTracker, motionEvent);
        m0.b(velocityTracker, 1000);
        return m0.d(velocityTracker, i8);
    }

    public void g(MotionEvent motionEvent, int i8) {
        boolean d8 = d(motionEvent, i8);
        if (this.f3157j[0] == Integer.MAX_VALUE) {
            VelocityTracker velocityTracker = this.f3152e;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f3152e = null;
                return;
            }
            return;
        }
        float e8 = e(motionEvent, i8) * this.f3149b.b();
        float signum = Math.signum(e8);
        if (d8 || (signum != Math.signum(this.f3153f) && signum != 0.0f)) {
            this.f3149b.c();
        }
        float abs = Math.abs(e8);
        int[] iArr = this.f3157j;
        if (abs < iArr[0]) {
            return;
        }
        int i9 = iArr[1];
        float max = Math.max(-i9, Math.min(e8, i9));
        this.f3153f = this.f3149b.a(max) ? max : 0.0f;
    }

    o(Context context, p pVar, b bVar, a aVar) {
        this.f3154g = -1;
        this.f3155h = -1;
        this.f3156i = -1;
        this.f3157j = new int[]{Integer.MAX_VALUE, 0};
        this.f3148a = context;
        this.f3149b = pVar;
        this.f3150c = bVar;
        this.f3151d = aVar;
    }
}
