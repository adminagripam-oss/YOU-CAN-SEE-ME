package b5;

import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class r implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private final Map.Entry f4675e;

    private r(Map.Entry entry, g5.a aVar) {
        this.f4675e = entry;
    }

    public static Runnable a(Map.Entry entry, g5.a aVar) {
        return new r(entry, aVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        s.c(this.f4675e, null);
    }
}
