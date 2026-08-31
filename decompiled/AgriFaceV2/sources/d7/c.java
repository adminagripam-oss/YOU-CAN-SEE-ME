package d7;

import java.io.Serializable;
/* loaded from: classes.dex */
public abstract class c implements h7.a, Serializable {

    /* renamed from: k  reason: collision with root package name */
    public static final Object f6461k = a.f6468e;

    /* renamed from: e  reason: collision with root package name */
    private transient h7.a f6462e;

    /* renamed from: f  reason: collision with root package name */
    protected final Object f6463f;

    /* renamed from: g  reason: collision with root package name */
    private final Class f6464g;

    /* renamed from: h  reason: collision with root package name */
    private final String f6465h;

    /* renamed from: i  reason: collision with root package name */
    private final String f6466i;

    /* renamed from: j  reason: collision with root package name */
    private final boolean f6467j;

    /* loaded from: classes.dex */
    private static class a implements Serializable {

        /* renamed from: e  reason: collision with root package name */
        private static final a f6468e = new a();

        private a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c(Object obj, Class cls, String str, String str2, boolean z7) {
        this.f6463f = obj;
        this.f6464g = cls;
        this.f6465h = str;
        this.f6466i = str2;
        this.f6467j = z7;
    }

    public h7.a b() {
        h7.a aVar = this.f6462e;
        if (aVar == null) {
            h7.a e8 = e();
            this.f6462e = e8;
            return e8;
        }
        return aVar;
    }

    protected abstract h7.a e();

    public Object h() {
        return this.f6463f;
    }

    public String i() {
        return this.f6465h;
    }

    public h7.d j() {
        Class cls = this.f6464g;
        if (cls == null) {
            return null;
        }
        return this.f6467j ? s.c(cls) : s.b(cls);
    }

    public String k() {
        return this.f6466i;
    }
}
