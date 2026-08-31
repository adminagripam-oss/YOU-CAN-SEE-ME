package q5;

import a4.xe;
import a4.ye;
import android.graphics.PointF;
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private final int f10379a;

    /* renamed from: b  reason: collision with root package name */
    private final PointF f10380b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(int i8, PointF pointF) {
        this.f10379a = i8;
        this.f10380b = pointF;
    }

    public PointF a() {
        return this.f10380b;
    }

    public String toString() {
        xe a8 = ye.a("FaceLandmark");
        a8.b("type", this.f10379a);
        a8.c("position", this.f10380b);
        return a8.toString();
    }
}
