package n0;

import android.graphics.Rect;
import android.util.Size;
import java.util.UUID;
/* loaded from: classes.dex */
final class b extends f {

    /* renamed from: a  reason: collision with root package name */
    private final UUID f9707a;

    /* renamed from: b  reason: collision with root package name */
    private final int f9708b;

    /* renamed from: c  reason: collision with root package name */
    private final int f9709c;

    /* renamed from: d  reason: collision with root package name */
    private final Rect f9710d;

    /* renamed from: e  reason: collision with root package name */
    private final Size f9711e;

    /* renamed from: f  reason: collision with root package name */
    private final int f9712f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f9713g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f9714h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(UUID uuid, int i8, int i9, Rect rect, Size size, int i10, boolean z7, boolean z8) {
        if (uuid == null) {
            throw new NullPointerException("Null getUuid");
        }
        this.f9707a = uuid;
        this.f9708b = i8;
        this.f9709c = i9;
        if (rect == null) {
            throw new NullPointerException("Null getCropRect");
        }
        this.f9710d = rect;
        if (size == null) {
            throw new NullPointerException("Null getSize");
        }
        this.f9711e = size;
        this.f9712f = i10;
        this.f9713g = z7;
        this.f9714h = z8;
    }

    @Override // n0.f
    public Rect a() {
        return this.f9710d;
    }

    @Override // n0.f
    public int b() {
        return this.f9709c;
    }

    @Override // n0.f
    public int c() {
        return this.f9712f;
    }

    @Override // n0.f
    public Size d() {
        return this.f9711e;
    }

    @Override // n0.f
    public int e() {
        return this.f9708b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            return this.f9707a.equals(fVar.f()) && this.f9708b == fVar.e() && this.f9709c == fVar.b() && this.f9710d.equals(fVar.a()) && this.f9711e.equals(fVar.d()) && this.f9712f == fVar.c() && this.f9713g == fVar.g() && this.f9714h == fVar.k();
        }
        return false;
    }

    @Override // n0.f
    UUID f() {
        return this.f9707a;
    }

    @Override // n0.f
    public boolean g() {
        return this.f9713g;
    }

    public int hashCode() {
        return ((((((((((((((this.f9707a.hashCode() ^ 1000003) * 1000003) ^ this.f9708b) * 1000003) ^ this.f9709c) * 1000003) ^ this.f9710d.hashCode()) * 1000003) ^ this.f9711e.hashCode()) * 1000003) ^ this.f9712f) * 1000003) ^ (this.f9713g ? 1231 : 1237)) * 1000003) ^ (this.f9714h ? 1231 : 1237);
    }

    @Override // n0.f
    public boolean k() {
        return this.f9714h;
    }

    public String toString() {
        return "OutConfig{getUuid=" + this.f9707a + ", getTargets=" + this.f9708b + ", getFormat=" + this.f9709c + ", getCropRect=" + this.f9710d + ", getSize=" + this.f9711e + ", getRotationDegrees=" + this.f9712f + ", isMirroring=" + this.f9713g + ", shouldRespectInputCropRect=" + this.f9714h + "}";
    }
}
