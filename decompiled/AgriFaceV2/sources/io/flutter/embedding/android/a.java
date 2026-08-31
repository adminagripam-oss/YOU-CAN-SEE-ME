package io.flutter.embedding.android;

import android.content.Context;
import android.graphics.Matrix;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class a {

    /* renamed from: e  reason: collision with root package name */
    private static final Matrix f7556e = new Matrix();

    /* renamed from: a  reason: collision with root package name */
    private final FlutterRenderer f7557a;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f7559c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f7560d = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final c0 f7558b = c0.a();

    public a(FlutterRenderer flutterRenderer, boolean z7) {
        this.f7557a = flutterRenderer;
        this.f7559c = z7;
    }

    private void a(MotionEvent motionEvent, int i8, int i9, int i10, Matrix matrix, ByteBuffer byteBuffer) {
        b(motionEvent, i8, i9, i10, matrix, byteBuffer, null);
    }

    private void b(MotionEvent motionEvent, int i8, int i9, int i10, Matrix matrix, ByteBuffer byteBuffer, Context context) {
        long j8;
        long j9;
        double d8;
        double d9;
        double d10;
        double d11;
        double d12;
        double d13;
        double d14;
        InputDevice.MotionRange motionRange;
        int i11 = -1;
        if (i9 == -1) {
            return;
        }
        int pointerId = motionEvent.getPointerId(i8);
        int f8 = f(motionEvent.getToolType(i8));
        float[] fArr = {motionEvent.getX(i8), motionEvent.getY(i8)};
        matrix.mapPoints(fArr);
        if (f8 == 1) {
            j8 = motionEvent.getButtonState() & 31;
            if (j8 == 0 && motionEvent.getSource() == 8194 && i9 == 4) {
                this.f7560d.put(Integer.valueOf(pointerId), fArr);
            }
        } else {
            j8 = f8 == 2 ? (motionEvent.getButtonState() >> 4) & 15 : 0L;
        }
        boolean containsKey = this.f7560d.containsKey(Integer.valueOf(pointerId));
        if (containsKey) {
            int e8 = e(i9);
            if (e8 == -1) {
                return;
            }
            i11 = e8;
        }
        long d15 = this.f7559c ? this.f7558b.c(motionEvent).d() : 0L;
        int i12 = motionEvent.getActionMasked() == 8 ? 1 : 0;
        int i13 = i11;
        byteBuffer.putLong(d15);
        byteBuffer.putLong(motionEvent.getEventTime() * 1000);
        if (containsKey) {
            byteBuffer.putLong(i13);
            j9 = 4;
        } else {
            byteBuffer.putLong(i9);
            j9 = f8;
        }
        byteBuffer.putLong(j9);
        byteBuffer.putLong(i12);
        byteBuffer.putLong(pointerId);
        byteBuffer.putLong(0L);
        if (containsKey) {
            float[] fArr2 = (float[]) this.f7560d.get(Integer.valueOf(pointerId));
            byteBuffer.putDouble(fArr2[0]);
            d8 = fArr2[1];
        } else {
            byteBuffer.putDouble(fArr[0]);
            d8 = fArr[1];
        }
        byteBuffer.putDouble(d8);
        byteBuffer.putDouble(0.0d);
        byteBuffer.putDouble(0.0d);
        byteBuffer.putLong(j8);
        byteBuffer.putLong(0L);
        byteBuffer.putLong(0L);
        byteBuffer.putDouble(motionEvent.getPressure(i8));
        if (motionEvent.getDevice() == null || (motionRange = motionEvent.getDevice().getMotionRange(2)) == null) {
            d9 = 1.0d;
            d10 = 0.0d;
        } else {
            d10 = motionRange.getMin();
            d9 = motionRange.getMax();
        }
        byteBuffer.putDouble(d10);
        byteBuffer.putDouble(d9);
        if (f8 == 2) {
            byteBuffer.putDouble(motionEvent.getAxisValue(24, i8));
            d11 = 0.0d;
        } else {
            d11 = 0.0d;
            byteBuffer.putDouble(0.0d);
        }
        byteBuffer.putDouble(d11);
        byteBuffer.putDouble(motionEvent.getSize(i8));
        byteBuffer.putDouble(motionEvent.getToolMajor(i8));
        byteBuffer.putDouble(motionEvent.getToolMinor(i8));
        byteBuffer.putDouble(d11);
        byteBuffer.putDouble(d11);
        byteBuffer.putDouble(motionEvent.getAxisValue(8, i8));
        if (f8 == 2) {
            byteBuffer.putDouble(motionEvent.getAxisValue(25, i8));
        } else {
            byteBuffer.putDouble(d11);
        }
        byteBuffer.putLong(i10);
        if (i12 == 1) {
            if (context != null) {
                d13 = c(context);
                d14 = g(context);
            } else {
                d13 = 48.0d;
                d14 = 48.0d;
            }
            byteBuffer.putDouble(d13 * (-motionEvent.getAxisValue(10, i8)));
            byteBuffer.putDouble(d14 * (-motionEvent.getAxisValue(9, i8)));
        } else {
            byteBuffer.putDouble(0.0d);
            byteBuffer.putDouble(0.0d);
        }
        if (containsKey) {
            float[] fArr3 = (float[]) this.f7560d.get(Integer.valueOf(pointerId));
            byteBuffer.putDouble(fArr[0] - fArr3[0]);
            byteBuffer.putDouble(fArr[1] - fArr3[1]);
            d12 = 0.0d;
        } else {
            d12 = 0.0d;
            byteBuffer.putDouble(0.0d);
            byteBuffer.putDouble(0.0d);
        }
        byteBuffer.putDouble(d12);
        byteBuffer.putDouble(d12);
        byteBuffer.putDouble(1.0d);
        byteBuffer.putDouble(d12);
        byteBuffer.putLong(0L);
        if (containsKey && i13 == 9) {
            this.f7560d.remove(Integer.valueOf(pointerId));
        }
    }

    private float c(Context context) {
        return ViewConfiguration.get(context).getScaledHorizontalScrollFactor();
    }

    private int d(int i8) {
        if (i8 == 0) {
            return 4;
        }
        if (i8 == 1) {
            return 6;
        }
        if (i8 == 5) {
            return 4;
        }
        if (i8 == 6) {
            return 6;
        }
        if (i8 == 2) {
            return 5;
        }
        if (i8 == 7) {
            return 3;
        }
        if (i8 == 3) {
            return 0;
        }
        return i8 == 8 ? 3 : -1;
    }

    private int e(int i8) {
        if (i8 == 4) {
            return 7;
        }
        if (i8 == 5) {
            return 8;
        }
        return (i8 == 6 || i8 == 0) ? 9 : -1;
    }

    private int f(int i8) {
        if (i8 != 1) {
            if (i8 != 2) {
                if (i8 != 3) {
                    return i8 != 4 ? 5 : 3;
                }
                return 1;
            }
            return 2;
        }
        return 0;
    }

    private float g(Context context) {
        return h(context);
    }

    private float h(Context context) {
        return ViewConfiguration.get(context).getScaledVerticalScrollFactor();
    }

    public boolean i(MotionEvent motionEvent, Context context) {
        boolean isFromSource = motionEvent.isFromSource(2);
        boolean z7 = motionEvent.getActionMasked() == 7 || motionEvent.getActionMasked() == 8;
        if (isFromSource && z7) {
            int d8 = d(motionEvent.getActionMasked());
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(motionEvent.getPointerCount() * 36 * 8);
            allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
            b(motionEvent, motionEvent.getActionIndex(), d8, 0, f7556e, allocateDirect, context);
            if (allocateDirect.position() % 288 == 0) {
                this.f7557a.m(allocateDirect, allocateDirect.position());
                return true;
            }
            throw new AssertionError("Packet position is not on field boundary.");
        }
        return false;
    }

    public boolean j(MotionEvent motionEvent) {
        return k(motionEvent, f7556e);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean k(android.view.MotionEvent r13, android.graphics.Matrix r14) {
        /*
            r12 = this;
            int r0 = r13.getPointerCount()
            int r1 = r0 * 36
            int r1 = r1 * 8
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocateDirect(r1)
            java.nio.ByteOrder r2 = java.nio.ByteOrder.LITTLE_ENDIAN
            r1.order(r2)
            int r2 = r13.getActionMasked()
            int r3 = r13.getActionMasked()
            int r9 = r12.d(r3)
            r3 = 0
            r10 = 1
            if (r2 == 0) goto L27
            r4 = 5
            if (r2 != r4) goto L25
            goto L27
        L25:
            r4 = r3
            goto L28
        L27:
            r4 = r10
        L28:
            if (r4 != 0) goto L31
            if (r2 == r10) goto L2f
            r5 = 6
            if (r2 != r5) goto L31
        L2f:
            r2 = r10
            goto L32
        L31:
            r2 = r3
        L32:
            if (r4 == 0) goto L42
        L34:
            int r4 = r13.getActionIndex()
            r6 = 0
            r2 = r12
            r3 = r13
            r5 = r9
            r7 = r14
            r8 = r1
            r2.a(r3, r4, r5, r6, r7, r8)
            goto L6f
        L42:
            r11 = r3
            if (r2 == 0) goto L60
        L45:
            if (r11 >= r0) goto L34
            int r2 = r13.getActionIndex()
            if (r11 == r2) goto L5d
            int r2 = r13.getToolType(r11)
            if (r2 != r10) goto L5d
            r5 = 5
            r6 = 1
            r2 = r12
            r3 = r13
            r4 = r11
            r7 = r14
            r8 = r1
            r2.a(r3, r4, r5, r6, r7, r8)
        L5d:
            int r11 = r11 + 1
            goto L45
        L60:
            if (r11 >= r0) goto L6f
            r6 = 0
            r2 = r12
            r3 = r13
            r4 = r11
            r5 = r9
            r7 = r14
            r8 = r1
            r2.a(r3, r4, r5, r6, r7, r8)
            int r11 = r11 + 1
            goto L60
        L6f:
            int r13 = r1.position()
            int r13 = r13 % 288
            if (r13 != 0) goto L81
            io.flutter.embedding.engine.renderer.FlutterRenderer r13 = r12.f7557a
            int r14 = r1.position()
            r13.m(r1, r14)
            return r10
        L81:
            java.lang.AssertionError r13 = new java.lang.AssertionError
            java.lang.String r14 = "Packet position is not on field boundary"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.android.a.k(android.view.MotionEvent, android.graphics.Matrix):boolean");
    }
}
