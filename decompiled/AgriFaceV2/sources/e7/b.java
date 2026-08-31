package e7;

import d7.k;
import java.util.Random;
/* loaded from: classes.dex */
public final class b extends e7.a {

    /* renamed from: g  reason: collision with root package name */
    private final a f6620g = new a();

    /* loaded from: classes.dex */
    public static final class a extends ThreadLocal {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Random initialValue() {
            return new Random();
        }
    }

    @Override // e7.a
    public Random d() {
        Object obj = this.f6620g.get();
        k.d(obj, "get(...)");
        return (Random) obj;
    }
}
