package y;

import android.graphics.Rect;
import android.util.Size;
import y.e;
/* loaded from: classes.dex */
public class q1 {

    /* renamed from: a  reason: collision with root package name */
    private final a f12889a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: y.q1$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static abstract class AbstractC0164a {
            abstract a a();

            abstract AbstractC0164a b(Rect rect);

            abstract AbstractC0164a c(int i8);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Rect a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Size b();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int c();
    }

    public q1(Size size, Rect rect, int i8) {
        this.f12889a = new e.b().d(size).b(rect).c(i8).a();
    }

    public Size a() {
        return this.f12889a.b();
    }

    public boolean equals(Object obj) {
        return this.f12889a.equals(obj);
    }

    public int hashCode() {
        return this.f12889a.hashCode();
    }

    public String toString() {
        return this.f12889a.toString();
    }
}
