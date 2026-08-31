package u0;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import u0.u;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final List f11918a = Collections.unmodifiableList(Arrays.asList(48000, 44100, 22050, 11025, 8000, 4800));

    /* renamed from: u0.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0155a {
        abstract a a();

        public final a b() {
            a a8 = a();
            String str = "";
            if (a8.c() == -1) {
                str = " audioSource";
            }
            if (a8.f() <= 0) {
                str = str + " sampleRate";
            }
            if (a8.e() <= 0) {
                str = str + " channelCount";
            }
            if (a8.b() == -1) {
                str = str + " audioFormat";
            }
            if (str.isEmpty()) {
                return a8;
            }
            throw new IllegalArgumentException("Required settings missing or non-positive:" + str);
        }

        public abstract AbstractC0155a c(int i8);

        public abstract AbstractC0155a d(int i8);

        public abstract AbstractC0155a e(int i8);

        public abstract AbstractC0155a f(int i8);
    }

    public static AbstractC0155a a() {
        return new u.b().d(-1).f(-1).e(-1).c(-1);
    }

    public abstract int b();

    public abstract int c();

    public int d() {
        return t.f(b(), e());
    }

    public abstract int e();

    public abstract int f();
}
