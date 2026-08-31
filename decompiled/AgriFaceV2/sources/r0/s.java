package r0;

import android.location.Location;
/* loaded from: classes.dex */
public abstract class s {

    /* renamed from: a  reason: collision with root package name */
    private final b f11251a;

    /* loaded from: classes.dex */
    static abstract class a {

        /* renamed from: a  reason: collision with root package name */
        final b.a f11252a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(b.a aVar) {
            this.f11252a = aVar;
            aVar.b(0L);
            aVar.a(0L);
        }
    }

    /* loaded from: classes.dex */
    static abstract class b {

        /* loaded from: classes.dex */
        static abstract class a {
            abstract Object a(long j8);

            abstract Object b(long j8);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long b();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Location c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(b bVar) {
        this.f11251a = bVar;
    }

    public long a() {
        return this.f11251a.a();
    }

    public long b() {
        return this.f11251a.b();
    }

    public Location c() {
        return this.f11251a.c();
    }
}
