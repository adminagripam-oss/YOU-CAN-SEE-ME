package d7;
/* loaded from: classes.dex */
public class t {
    public h7.e a(i iVar) {
        return iVar;
    }

    public h7.b b(Class cls) {
        return new e(cls);
    }

    public h7.d c(Class cls, String str) {
        return new m(cls, str);
    }

    public h7.f d(n nVar) {
        return nVar;
    }

    public String e(h hVar) {
        String obj = hVar.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring(21) : obj;
    }

    public String f(l lVar) {
        return e(lVar);
    }
}
