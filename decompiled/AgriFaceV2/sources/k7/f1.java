package k7;

import java.util.concurrent.CancellationException;
/* loaded from: classes.dex */
public final class f1 extends CancellationException {

    /* renamed from: e  reason: collision with root package name */
    public final transient e1 f8735e;

    public f1(String str, Throwable th, e1 e1Var) {
        super(str);
        this.f8735e = e1Var;
        if (th != null) {
            initCause(th);
        }
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof f1) {
                f1 f1Var = (f1) obj;
                if (!d7.k.a(f1Var.getMessage(), getMessage()) || !d7.k.a(f1Var.f8735e, this.f8735e) || !d7.k.a(f1Var.getCause(), getCause())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public int hashCode() {
        String message = getMessage();
        d7.k.b(message);
        int hashCode = ((message.hashCode() * 31) + this.f8735e.hashCode()) * 31;
        Throwable cause = getCause();
        return hashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + "; job=" + this.f8735e;
    }
}
