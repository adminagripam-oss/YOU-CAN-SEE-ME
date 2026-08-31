package r0;

import android.net.Uri;
/* loaded from: classes.dex */
final class h extends t {

    /* renamed from: a  reason: collision with root package name */
    private final Uri f11057a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Uri uri) {
        if (uri == null) {
            throw new NullPointerException("Null outputUri");
        }
        this.f11057a = uri;
    }

    @Override // r0.t
    public Uri a() {
        return this.f11057a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof t) {
            return this.f11057a.equals(((t) obj).a());
        }
        return false;
    }

    public int hashCode() {
        return this.f11057a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "OutputResults{outputUri=" + this.f11057a + "}";
    }
}
