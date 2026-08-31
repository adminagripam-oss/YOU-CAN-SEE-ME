package r0;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import y.g2;
/* loaded from: classes.dex */
public abstract class c1 {

    /* renamed from: a  reason: collision with root package name */
    static final c1 f11016a = d(0, a.INACTIVE);

    /* renamed from: b  reason: collision with root package name */
    static final Set f11017b = Collections.unmodifiableSet(new HashSet(Arrays.asList(0, -1)));

    /* renamed from: c  reason: collision with root package name */
    static final b0.d2 f11018c = b0.w0.g(d(0, a.ACTIVE));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum a {
        ACTIVE,
        INACTIVE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c1 d(int i8, a aVar) {
        return new m(i8, aVar, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c1 e(int i8, a aVar, g2.h hVar) {
        return new m(i8, aVar, hVar);
    }

    public abstract int a();

    public abstract g2.h b();

    public abstract a c();
}
