package f7;

import d7.k;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/* loaded from: classes.dex */
public final class a extends e7.a {
    @Override // e7.a
    public Random d() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        k.d(current, "current(...)");
        return current;
    }
}
