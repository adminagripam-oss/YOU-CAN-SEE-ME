package r0;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f10997a = Collections.unmodifiableSet(new HashSet(Arrays.asList(2, 3, 4)));

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b d(int i8, Throwable th, double d8) {
        return new d(i8, d8, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double a();

    public abstract int b();

    public abstract Throwable c();
}
