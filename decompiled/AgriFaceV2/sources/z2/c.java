package z2;

import a3.p;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import u2.m;
import u2.r;
/* loaded from: classes.dex */
public class c implements e {

    /* renamed from: f  reason: collision with root package name */
    private static final Logger f13385f = Logger.getLogger(r.class.getName());

    /* renamed from: a  reason: collision with root package name */
    private final p f13386a;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f13387b;

    /* renamed from: c  reason: collision with root package name */
    private final v2.e f13388c;

    /* renamed from: d  reason: collision with root package name */
    private final b3.c f13389d;

    /* renamed from: e  reason: collision with root package name */
    private final c3.b f13390e;

    public c(Executor executor, v2.e eVar, p pVar, b3.c cVar, c3.b bVar) {
        this.f13387b = executor;
        this.f13388c = eVar;
        this.f13386a = pVar;
        this.f13389d = cVar;
        this.f13390e = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object b(c cVar, m mVar, u2.h hVar) {
        cVar.f13389d.H(mVar, hVar);
        cVar.f13386a.a(mVar, 1);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c(c cVar, m mVar, s2.h hVar, u2.h hVar2) {
        try {
            v2.m a8 = cVar.f13388c.a(mVar.b());
            if (a8 != null) {
                cVar.f13390e.b(b.b(cVar, mVar, a8.b(hVar2)));
                hVar.a(null);
                return;
            }
            String format = String.format("Transport backend '%s' is not registered", mVar.b());
            f13385f.warning(format);
            hVar.a(new IllegalArgumentException(format));
        } catch (Exception e8) {
            Logger logger = f13385f;
            logger.warning("Error scheduling event " + e8.getMessage());
            hVar.a(e8);
        }
    }

    @Override // z2.e
    public void a(m mVar, u2.h hVar, s2.h hVar2) {
        this.f13387b.execute(a.a(this, mVar, hVar2, hVar));
    }
}
