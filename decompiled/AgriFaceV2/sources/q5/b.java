package q5;

import a4.xe;
import a4.ye;
import java.util.List;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final int f10363a;

    /* renamed from: b  reason: collision with root package name */
    private final List f10364b;

    public b(int i8, List list) {
        this.f10363a = i8;
        this.f10364b = list;
    }

    public List a() {
        return this.f10364b;
    }

    public String toString() {
        xe a8 = ye.a("FaceContour");
        a8.b("type", this.f10363a);
        a8.c("points", this.f10364b.toArray());
        return a8.toString();
    }
}
