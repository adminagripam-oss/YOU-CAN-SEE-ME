package u2;

import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n implements s2.g {

    /* renamed from: a  reason: collision with root package name */
    private final Set f12079a;

    /* renamed from: b  reason: collision with root package name */
    private final m f12080b;

    /* renamed from: c  reason: collision with root package name */
    private final q f12081c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Set set, m mVar, q qVar) {
        this.f12079a = set;
        this.f12080b = mVar;
        this.f12081c = qVar;
    }

    @Override // s2.g
    public s2.f a(String str, Class cls, s2.b bVar, s2.e eVar) {
        if (this.f12079a.contains(bVar)) {
            return new p(this.f12080b, str, bVar, eVar, this.f12081c);
        }
        throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", bVar, this.f12079a));
    }
}
