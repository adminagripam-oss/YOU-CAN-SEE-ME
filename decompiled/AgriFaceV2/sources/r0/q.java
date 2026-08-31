package r0;

import java.io.File;
import r0.f;
import r0.s;
/* loaded from: classes.dex */
public final class q extends s {

    /* renamed from: b  reason: collision with root package name */
    private final b f11117b;

    /* loaded from: classes.dex */
    public static final class a extends s.a {

        /* renamed from: b  reason: collision with root package name */
        private final b.a f11118b;

        public a(File file) {
            super(new f.b());
            g1.e.k(file, "File can't be null.");
            b.a aVar = (b.a) this.f11252a;
            this.f11118b = aVar;
            aVar.d(file);
        }

        public q a() {
            return new q(this.f11118b.c());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class b extends s.b {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public static abstract class a extends s.b.a {
            abstract b c();

            abstract a d(File file);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract File d();
    }

    q(b bVar) {
        super(bVar);
        this.f11117b = bVar;
    }

    public File d() {
        return this.f11117b.d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof q) {
            return this.f11117b.equals(((q) obj).f11117b);
        }
        return false;
    }

    public int hashCode() {
        return this.f11117b.hashCode();
    }

    public String toString() {
        return this.f11117b.toString().replaceFirst("FileOutputOptionsInternal", "FileOutputOptions");
    }
}
