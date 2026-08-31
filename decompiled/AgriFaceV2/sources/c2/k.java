package c2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private int f4757a;

    /* renamed from: b  reason: collision with root package name */
    private final List f4758b;

    /* renamed from: c  reason: collision with root package name */
    private int f4759c;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private int f4760a = 0;

        /* renamed from: b  reason: collision with root package name */
        private final List f4761b = new ArrayList();

        /* renamed from: c  reason: collision with root package name */
        private int f4762c = 1;

        public a a(int... iArr) {
            for (int i8 : iArr) {
                this.f4760a = i8 | this.f4760a;
            }
            return this;
        }

        public a b(String... strArr) {
            this.f4761b.addAll(Arrays.asList(strArr));
            return this;
        }

        public k c() {
            return new k(this.f4760a, this.f4761b, this.f4762c);
        }

        public a d(int i8) {
            this.f4762c = i8;
            return this;
        }
    }

    public k(int i8, List list, int i9) {
        ArrayList arrayList = new ArrayList();
        this.f4758b = arrayList;
        this.f4757a = i8;
        arrayList.addAll(list);
        this.f4759c = i9;
    }

    public List a() {
        return this.f4758b;
    }

    public int b() {
        return this.f4757a;
    }

    public int c() {
        return this.f4759c;
    }
}
