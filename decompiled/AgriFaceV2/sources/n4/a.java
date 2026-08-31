package n4;

import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final c f9762a = new C0132a(false);

    /* renamed from: n4.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0132a implements c {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicBoolean f9763a;

        C0132a(boolean z7) {
            this.f9763a = new AtomicBoolean(z7);
        }

        @Override // n4.c
        public boolean getValue() {
            return this.f9763a.get();
        }
    }
}
