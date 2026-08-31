package l0;
/* loaded from: classes.dex */
public class u implements g1.a {

    /* renamed from: a  reason: collision with root package name */
    private g1.a f8943a;

    public void a(g1.a aVar) {
        this.f8943a = aVar;
    }

    @Override // g1.a
    public void accept(Object obj) {
        d7.k.c(this.f8943a, "Listener is not set.");
        this.f8943a.accept(obj);
    }
}
