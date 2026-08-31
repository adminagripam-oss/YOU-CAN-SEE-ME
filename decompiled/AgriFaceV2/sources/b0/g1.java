package b0;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public interface g1 {

    /* renamed from: a  reason: collision with root package name */
    public static final g1 f4198a = new a();

    /* renamed from: b  reason: collision with root package name */
    public static final List f4199b = Collections.unmodifiableList(Arrays.asList(13, 10, 8, 11, 6, 5, 4, 9, 3, 7, 2));

    /* loaded from: classes.dex */
    class a implements g1 {
        a() {
        }

        @Override // b0.g1
        public h1 a(int i8) {
            return null;
        }

        @Override // b0.g1
        public boolean b(int i8) {
            return false;
        }
    }

    h1 a(int i8);

    boolean b(int i8);
}
