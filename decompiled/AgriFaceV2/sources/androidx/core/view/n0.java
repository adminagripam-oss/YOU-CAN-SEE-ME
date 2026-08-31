package androidx.core.view;

import android.view.MotionEvent;
/* loaded from: classes.dex */
class n0 {

    /* renamed from: a  reason: collision with root package name */
    private final float[] f3143a = new float[20];

    /* renamed from: b  reason: collision with root package name */
    private final long[] f3144b = new long[20];

    /* renamed from: c  reason: collision with root package name */
    private float f3145c = 0.0f;

    /* renamed from: d  reason: collision with root package name */
    private int f3146d = 0;

    /* renamed from: e  reason: collision with root package name */
    private int f3147e = 0;

    private void b() {
        this.f3146d = 0;
        this.f3145c = 0.0f;
    }

    private float e() {
        long[] jArr;
        long j8;
        int i8 = this.f3146d;
        if (i8 < 2) {
            return 0.0f;
        }
        int i9 = this.f3147e;
        int i10 = ((i9 + 20) - (i8 - 1)) % 20;
        long j9 = this.f3144b[i9];
        while (true) {
            jArr = this.f3144b;
            j8 = jArr[i10];
            if (j9 - j8 <= 100) {
                break;
            }
            this.f3146d--;
            i10 = (i10 + 1) % 20;
        }
        int i11 = this.f3146d;
        if (i11 < 2) {
            return 0.0f;
        }
        if (i11 == 2) {
            int i12 = (i10 + 1) % 20;
            long j10 = jArr[i12];
            if (j8 == j10) {
                return 0.0f;
            }
            return this.f3143a[i12] / ((float) (j10 - j8));
        }
        float f8 = 0.0f;
        int i13 = 0;
        for (int i14 = 0; i14 < this.f3146d - 1; i14++) {
            int i15 = i14 + i10;
            long[] jArr2 = this.f3144b;
            long j11 = jArr2[i15 % 20];
            int i16 = (i15 + 1) % 20;
            if (jArr2[i16] != j11) {
                i13++;
                float f9 = f(f8);
                float f10 = this.f3143a[i16] / ((float) (this.f3144b[i16] - j11));
                f8 += (f10 - f9) * Math.abs(f10);
                if (i13 == 1) {
                    f8 *= 0.5f;
                }
            }
        }
        return f(f8);
    }

    private static float f(float f8) {
        return (f8 < 0.0f ? -1.0f : 1.0f) * ((float) Math.sqrt(Math.abs(f8) * 2.0f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(MotionEvent motionEvent) {
        long eventTime = motionEvent.getEventTime();
        if (this.f3146d != 0 && eventTime - this.f3144b[this.f3147e] > 40) {
            b();
        }
        int i8 = (this.f3147e + 1) % 20;
        this.f3147e = i8;
        int i9 = this.f3146d;
        if (i9 != 20) {
            this.f3146d = i9 + 1;
        }
        this.f3143a[i8] = motionEvent.getAxisValue(26);
        this.f3144b[this.f3147e] = eventTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i8, float f8) {
        float abs;
        float e8 = e() * i8;
        this.f3145c = e8;
        if (e8 < (-Math.abs(f8))) {
            abs = -Math.abs(f8);
        } else if (this.f3145c <= Math.abs(f8)) {
            return;
        } else {
            abs = Math.abs(f8);
        }
        this.f3145c = abs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float d(int i8) {
        if (i8 != 26) {
            return 0.0f;
        }
        return this.f3145c;
    }
}
