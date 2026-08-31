package y;

import android.graphics.Rect;
import android.util.Size;
import y.q1;
/* loaded from: classes.dex */
final class e extends q1.a {

    /* renamed from: a  reason: collision with root package name */
    private final Size f12762a;

    /* renamed from: b  reason: collision with root package name */
    private final Rect f12763b;

    /* renamed from: c  reason: collision with root package name */
    private final int f12764c;

    /* loaded from: classes.dex */
    static final class b extends q1.a.AbstractC0164a {

        /* renamed from: a  reason: collision with root package name */
        private Size f12765a;

        /* renamed from: b  reason: collision with root package name */
        private Rect f12766b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f12767c;

        @Override // y.q1.a.AbstractC0164a
        q1.a a() {
            String str = "";
            if (this.f12765a == null) {
                str = " resolution";
            }
            if (this.f12766b == null) {
                str = str + " cropRect";
            }
            if (this.f12767c == null) {
                str = str + " rotationDegrees";
            }
            if (str.isEmpty()) {
                return new e(this.f12765a, this.f12766b, this.f12767c.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // y.q1.a.AbstractC0164a
        q1.a.AbstractC0164a b(Rect rect) {
            if (rect != null) {
                this.f12766b = rect;
                return this;
            }
            throw new NullPointerException("Null cropRect");
        }

        @Override // y.q1.a.AbstractC0164a
        q1.a.AbstractC0164a c(int i8) {
            this.f12767c = Integer.valueOf(i8);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public q1.a.AbstractC0164a d(Size size) {
            if (size != null) {
                this.f12765a = size;
                return this;
            }
            throw new NullPointerException("Null resolution");
        }
    }

    private e(Size size, Rect rect, int i8) {
        this.f12762a = size;
        this.f12763b = rect;
        this.f12764c = i8;
    }

    @Override // y.q1.a
    Rect a() {
        return this.f12763b;
    }

    @Override // y.q1.a
    Size b() {
        return this.f12762a;
    }

    @Override // y.q1.a
    int c() {
        return this.f12764c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof q1.a) {
            q1.a aVar = (q1.a) obj;
            return this.f12762a.equals(aVar.b()) && this.f12763b.equals(aVar.a()) && this.f12764c == aVar.c();
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f12762a.hashCode() ^ 1000003) * 1000003) ^ this.f12763b.hashCode()) * 1000003) ^ this.f12764c;
    }

    public String toString() {
        return "ResolutionInfoInternal{resolution=" + this.f12762a + ", cropRect=" + this.f12763b + ", rotationDegrees=" + this.f12764c + "}";
    }
}
