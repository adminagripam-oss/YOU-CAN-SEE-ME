package m5;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final h5.b f9142a;

    public d(h5.b bVar) {
        this.f9142a = bVar;
    }

    public Executor a(Executor executor) {
        return executor != null ? executor : (Executor) this.f9142a.get();
    }
}
