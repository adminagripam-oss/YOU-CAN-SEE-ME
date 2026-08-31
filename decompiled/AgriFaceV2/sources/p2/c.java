package p2;

import i6.j;
/* loaded from: classes.dex */
public class c implements x {

    /* renamed from: a  reason: collision with root package name */
    private j.d f10027a;

    public c(j.d dVar) {
        this.f10027a = dVar;
    }

    @Override // p2.x
    public void a(o2.b bVar) {
        this.f10027a.error(bVar.toString(), bVar.e(), null);
    }

    @Override // p2.x
    public void b(boolean z7) {
        this.f10027a.success(Boolean.valueOf(z7));
    }
}
