package a4;

import java.io.Serializable;
/* loaded from: classes.dex */
final class l0 extends r implements Serializable {

    /* renamed from: e  reason: collision with root package name */
    final Object f764e;

    /* renamed from: f  reason: collision with root package name */
    final Object f765f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0(Object obj, Object obj2) {
        this.f764e = obj;
        this.f765f = obj2;
    }

    @Override // a4.r, java.util.Map.Entry
    public final Object getKey() {
        return this.f764e;
    }

    @Override // a4.r, java.util.Map.Entry
    public final Object getValue() {
        return this.f765f;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
