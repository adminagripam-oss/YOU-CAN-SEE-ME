package b5;

import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class p extends q {

    /* renamed from: e  reason: collision with root package name */
    private final List f4674e;

    public p(List list) {
        super("Dependency cycle detected: " + Arrays.toString(list.toArray()));
        this.f4674e = list;
    }
}
