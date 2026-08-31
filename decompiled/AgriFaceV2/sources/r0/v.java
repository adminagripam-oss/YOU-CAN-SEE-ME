package r0;

import android.util.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class v {

    /* renamed from: a  reason: collision with root package name */
    public static final v f11267a;

    /* renamed from: b  reason: collision with root package name */
    public static final v f11268b;

    /* renamed from: c  reason: collision with root package name */
    public static final v f11269c;

    /* renamed from: d  reason: collision with root package name */
    public static final v f11270d;

    /* renamed from: e  reason: collision with root package name */
    public static final v f11271e;

    /* renamed from: f  reason: collision with root package name */
    public static final v f11272f;

    /* renamed from: g  reason: collision with root package name */
    static final v f11273g;

    /* renamed from: h  reason: collision with root package name */
    private static final Set f11274h;

    /* renamed from: i  reason: collision with root package name */
    private static final List f11275i;

    /* loaded from: classes.dex */
    public static abstract class b extends v {
        public b() {
            super();
        }

        static b f(int i8, String str, List list) {
            return new j(i8, str, list);
        }

        public abstract String c();

        public abstract List d();

        public abstract int e();
    }

    static {
        b f8 = b.f(4, "SD", Collections.unmodifiableList(Arrays.asList(new Size(720, 480), new Size(640, 480))));
        f11267a = f8;
        b f9 = b.f(5, "HD", Collections.singletonList(new Size(1280, 720)));
        f11268b = f9;
        b f10 = b.f(6, "FHD", Collections.singletonList(new Size(1920, 1080)));
        f11269c = f10;
        b f11 = b.f(8, "UHD", Collections.singletonList(new Size(3840, 2160)));
        f11270d = f11;
        b f12 = b.f(0, "LOWEST", Collections.emptyList());
        f11271e = f12;
        b f13 = b.f(1, "HIGHEST", Collections.emptyList());
        f11272f = f13;
        f11273g = b.f(-1, "NONE", Collections.emptyList());
        f11274h = new HashSet(Arrays.asList(f12, f13, f8, f9, f10, f11));
        f11275i = Arrays.asList(f11, f10, f9, f8);
    }

    private v() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(v vVar) {
        return f11274h.contains(vVar);
    }

    public static List b() {
        return new ArrayList(f11275i);
    }
}
