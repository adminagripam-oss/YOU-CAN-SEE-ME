package y;

import b0.t0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public abstract class y {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a implements b0.r0 {

        /* renamed from: a  reason: collision with root package name */
        final List f12992a;

        a(List list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("Cannot set an empty CaptureStage list.");
            }
            this.f12992a = Collections.unmodifiableList(new ArrayList(list));
        }

        @Override // b0.r0
        public List a() {
            return this.f12992a;
        }
    }

    static b0.r0 a(b0.t0... t0VarArr) {
        return new a(Arrays.asList(t0VarArr));
    }

    public static b0.r0 b() {
        return a(new t0.a());
    }
}
