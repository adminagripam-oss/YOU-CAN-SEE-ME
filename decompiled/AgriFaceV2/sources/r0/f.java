package r0;

import android.location.Location;
import java.io.File;
import r0.q;
/* loaded from: classes.dex */
final class f extends q.b {

    /* renamed from: a  reason: collision with root package name */
    private final long f11040a;

    /* renamed from: b  reason: collision with root package name */
    private final long f11041b;

    /* renamed from: c  reason: collision with root package name */
    private final Location f11042c;

    /* renamed from: d  reason: collision with root package name */
    private final File f11043d;

    /* loaded from: classes.dex */
    static final class b extends q.b.a {

        /* renamed from: a  reason: collision with root package name */
        private Long f11044a;

        /* renamed from: b  reason: collision with root package name */
        private Long f11045b;

        /* renamed from: c  reason: collision with root package name */
        private Location f11046c;

        /* renamed from: d  reason: collision with root package name */
        private File f11047d;

        @Override // r0.q.b.a
        q.b c() {
            String str = "";
            if (this.f11044a == null) {
                str = " fileSizeLimit";
            }
            if (this.f11045b == null) {
                str = str + " durationLimitMillis";
            }
            if (this.f11047d == null) {
                str = str + " file";
            }
            if (str.isEmpty()) {
                return new f(this.f11044a.longValue(), this.f11045b.longValue(), this.f11046c, this.f11047d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // r0.q.b.a
        q.b.a d(File file) {
            if (file != null) {
                this.f11047d = file;
                return this;
            }
            throw new NullPointerException("Null file");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // r0.s.b.a
        /* renamed from: e */
        public q.b.a a(long j8) {
            this.f11045b = Long.valueOf(j8);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // r0.s.b.a
        /* renamed from: f */
        public q.b.a b(long j8) {
            this.f11044a = Long.valueOf(j8);
            return this;
        }
    }

    private f(long j8, long j9, Location location, File file) {
        this.f11040a = j8;
        this.f11041b = j9;
        this.f11042c = location;
        this.f11043d = file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.s.b
    public long a() {
        return this.f11041b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.s.b
    public long b() {
        return this.f11040a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.s.b
    public Location c() {
        return this.f11042c;
    }

    @Override // r0.q.b
    File d() {
        return this.f11043d;
    }

    public boolean equals(Object obj) {
        Location location;
        if (obj == this) {
            return true;
        }
        if (obj instanceof q.b) {
            q.b bVar = (q.b) obj;
            return this.f11040a == bVar.b() && this.f11041b == bVar.a() && ((location = this.f11042c) != null ? location.equals(bVar.c()) : bVar.c() == null) && this.f11043d.equals(bVar.d());
        }
        return false;
    }

    public int hashCode() {
        long j8 = this.f11040a;
        long j9 = this.f11041b;
        int i8 = (((((int) (j8 ^ (j8 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j9 >>> 32) ^ j9))) * 1000003;
        Location location = this.f11042c;
        return ((i8 ^ (location == null ? 0 : location.hashCode())) * 1000003) ^ this.f11043d.hashCode();
    }

    public String toString() {
        return "FileOutputOptionsInternal{fileSizeLimit=" + this.f11040a + ", durationLimitMillis=" + this.f11041b + ", location=" + this.f11042c + ", file=" + this.f11043d + "}";
    }
}
