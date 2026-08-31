package e7;

import d7.g;
import java.io.Serializable;
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: e  reason: collision with root package name */
    public static final a f6621e = new a(null);

    /* renamed from: f  reason: collision with root package name */
    private static final c f6622f = x6.b.f12728a.b();

    /* loaded from: classes.dex */
    public static final class a extends c implements Serializable {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        @Override // e7.c
        public int b() {
            return c.f6622f.b();
        }

        @Override // e7.c
        public int c(int i8) {
            return c.f6622f.c(i8);
        }
    }

    public abstract int b();

    public abstract int c(int i8);
}
