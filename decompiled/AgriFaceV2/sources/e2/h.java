package e2;
/* loaded from: classes.dex */
public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    public static final a f6581a = new a(null);

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public static /* synthetic */ h b(a aVar, Object obj, String str, j jVar, g gVar, int i8, Object obj2) {
            if ((i8 & 2) != 0) {
                jVar = c.f6564a.a();
            }
            if ((i8 & 4) != 0) {
                gVar = e2.a.f6559a;
            }
            return aVar.a(obj, str, jVar, gVar);
        }

        public final h a(Object obj, String str, j jVar, g gVar) {
            d7.k.e(obj, "<this>");
            d7.k.e(str, "tag");
            d7.k.e(jVar, "verificationMode");
            d7.k.e(gVar, "logger");
            return new i(obj, str, jVar, gVar);
        }
    }

    public abstract Object a();

    /* JADX INFO: Access modifiers changed from: protected */
    public final String b(Object obj, String str) {
        d7.k.e(obj, "value");
        d7.k.e(str, "message");
        return str + " value: " + obj;
    }

    public abstract h c(String str, c7.l lVar);
}
