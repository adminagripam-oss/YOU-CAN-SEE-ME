package v6;
/* loaded from: classes.dex */
public abstract class j extends a {
    public j(t6.d dVar) {
        super(dVar);
        if (dVar != null) {
            if (!(dVar.getContext() == t6.h.f11916e)) {
                throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext".toString());
            }
        }
    }

    @Override // t6.d
    public t6.g getContext() {
        return t6.h.f11916e;
    }
}
