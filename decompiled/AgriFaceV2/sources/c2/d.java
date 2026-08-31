package c2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private List f4747a;

    /* renamed from: b  reason: collision with root package name */
    private List f4748b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4749c;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: c  reason: collision with root package name */
        private boolean f4752c = false;

        /* renamed from: a  reason: collision with root package name */
        private List f4750a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        private List f4751b = new ArrayList();

        private List f() {
            return this.f4751b;
        }

        private List h() {
            return this.f4750a;
        }

        private boolean j() {
            return this.f4752c;
        }

        public a a(String str) {
            this.f4751b.add(str);
            return this;
        }

        public a b(String str) {
            this.f4750a.add(new b(str, "direct://"));
            return this;
        }

        public a c(String str) {
            this.f4750a.add(new b(str));
            return this;
        }

        public a d(String str, String str2) {
            this.f4750a.add(new b(str2, str));
            return this;
        }

        public d e() {
            return new d(h(), f(), j());
        }

        public a g() {
            return a("<local>");
        }

        public a i() {
            return a("<-loopback>");
        }

        public a k(boolean z7) {
            this.f4752c = z7;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private String f4753a;

        /* renamed from: b  reason: collision with root package name */
        private String f4754b;

        public b(String str) {
            this("*", str);
        }

        public String a() {
            return this.f4753a;
        }

        public String b() {
            return this.f4754b;
        }

        public b(String str, String str2) {
            this.f4753a = str;
            this.f4754b = str2;
        }
    }

    public d(List list, List list2, boolean z7) {
        this.f4747a = list;
        this.f4748b = list2;
        this.f4749c = z7;
    }

    public List a() {
        return Collections.unmodifiableList(this.f4748b);
    }

    public List b() {
        return Collections.unmodifiableList(this.f4747a);
    }

    public boolean c() {
        return this.f4749c;
    }
}
