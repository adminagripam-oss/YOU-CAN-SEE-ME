package androidx.emoji2.text;

import android.graphics.Typeface;
import android.util.SparseArray;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    private final o1.b f3342a;

    /* renamed from: b  reason: collision with root package name */
    private final char[] f3343b;

    /* renamed from: c  reason: collision with root package name */
    private final a f3344c = new a(1024);

    /* renamed from: d  reason: collision with root package name */
    private final Typeface f3345d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final SparseArray f3346a;

        /* renamed from: b  reason: collision with root package name */
        private g f3347b;

        private a() {
            this(1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a a(int i8) {
            SparseArray sparseArray = this.f3346a;
            if (sparseArray == null) {
                return null;
            }
            return (a) sparseArray.get(i8);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final g b() {
            return this.f3347b;
        }

        void c(g gVar, int i8, int i9) {
            a a8 = a(gVar.b(i8));
            if (a8 == null) {
                a8 = new a();
                this.f3346a.put(gVar.b(i8), a8);
            }
            if (i9 > i8) {
                a8.c(gVar, i8 + 1, i9);
            } else {
                a8.f3347b = gVar;
            }
        }

        a(int i8) {
            this.f3346a = new SparseArray(i8);
        }
    }

    private l(Typeface typeface, o1.b bVar) {
        this.f3345d = typeface;
        this.f3342a = bVar;
        this.f3343b = new char[bVar.k() * 2];
        a(bVar);
    }

    private void a(o1.b bVar) {
        int k8 = bVar.k();
        for (int i8 = 0; i8 < k8; i8++) {
            g gVar = new g(this, i8);
            Character.toChars(gVar.f(), this.f3343b, i8 * 2);
            h(gVar);
        }
    }

    public static l b(Typeface typeface, ByteBuffer byteBuffer) {
        try {
            androidx.core.os.l.a("EmojiCompat.MetadataRepo.create");
            return new l(typeface, k.b(byteBuffer));
        } finally {
            androidx.core.os.l.b();
        }
    }

    public char[] c() {
        return this.f3343b;
    }

    public o1.b d() {
        return this.f3342a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.f3342a.l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a f() {
        return this.f3344c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Typeface g() {
        return this.f3345d;
    }

    void h(g gVar) {
        g1.e.k(gVar, "emoji metadata cannot be null");
        g1.e.b(gVar.c() > 0, "invalid metadata codepoint length");
        this.f3344c.c(gVar, 0, gVar.c() - 1);
    }
}
