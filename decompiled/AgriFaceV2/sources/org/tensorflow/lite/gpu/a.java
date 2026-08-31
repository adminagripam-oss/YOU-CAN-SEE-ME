package org.tensorflow.lite.gpu;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private boolean f9914a = true;

    /* renamed from: b  reason: collision with root package name */
    boolean f9915b = true;

    /* renamed from: c  reason: collision with root package name */
    int f9916c = 0;

    /* renamed from: d  reason: collision with root package name */
    String f9917d = null;

    /* renamed from: e  reason: collision with root package name */
    String f9918e = null;

    /* renamed from: f  reason: collision with root package name */
    EnumC0137a f9919f = EnumC0137a.UNSET;

    /* renamed from: org.tensorflow.lite.gpu.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0137a {
        UNSET(0),
        OPENCL(1),
        OPENGL(2);
        

        /* renamed from: e  reason: collision with root package name */
        private final int f9924e;

        EnumC0137a(int i8) {
            this.f9924e = i8;
        }

        public int e() {
            return this.f9924e;
        }
    }

    public boolean a() {
        return this.f9915b;
    }

    public EnumC0137a b() {
        return this.f9919f;
    }

    public int c() {
        return this.f9916c;
    }

    public String d() {
        return this.f9918e;
    }

    public String e() {
        return this.f9917d;
    }

    public boolean f() {
        return this.f9914a;
    }
}
