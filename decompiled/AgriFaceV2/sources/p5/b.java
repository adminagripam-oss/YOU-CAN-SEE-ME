package p5;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.List;
/* loaded from: classes.dex */
public abstract class b {
    public static int a(int i8) {
        if (i8 != 0) {
            if (i8 != 90) {
                if (i8 != 180) {
                    if (i8 == 270) {
                        return 3;
                    }
                    throw new IllegalArgumentException("Invalid rotation: " + i8);
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }

    public static void b(PointF pointF, Matrix matrix) {
        float[] fArr = {pointF.x, pointF.y};
        matrix.mapPoints(fArr);
        pointF.set(fArr[0], fArr[1]);
    }

    public static void c(List list, Matrix matrix) {
        int size = list.size();
        float[] fArr = new float[size + size];
        for (int i8 = 0; i8 < list.size(); i8++) {
            int i9 = i8 + i8;
            fArr[i9] = ((PointF) list.get(i8)).x;
            fArr[i9 + 1] = ((PointF) list.get(i8)).y;
        }
        matrix.mapPoints(fArr);
        for (int i10 = 0; i10 < list.size(); i10++) {
            int i11 = i10 + i10;
            ((PointF) list.get(i10)).set(fArr[i11], fArr[i11 + 1]);
        }
    }

    public static void d(Rect rect, Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }
}
