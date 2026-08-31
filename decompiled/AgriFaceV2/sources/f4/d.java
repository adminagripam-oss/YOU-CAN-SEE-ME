package f4;
/* loaded from: classes.dex */
public final class d extends IllegalStateException {
    private d(String str, Throwable th) {
        super(str, th);
    }

    public static IllegalStateException a(j jVar) {
        if (jVar.i()) {
            Exception f8 = jVar.f();
            return new d("Complete with: ".concat(f8 != null ? "failure" : jVar.j() ? "result ".concat(String.valueOf(jVar.g())) : jVar.h() ? "cancellation" : "unknown issue"), f8);
        }
        return new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
    }
}
