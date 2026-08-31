package k1;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final b f8313a;

    /* loaded from: classes.dex */
    private static final class a implements b {

        /* renamed from: a  reason: collision with root package name */
        final InputContentInfo f8314a;

        a(Object obj) {
            this.f8314a = (InputContentInfo) obj;
        }

        @Override // k1.d.b
        public Object a() {
            return this.f8314a;
        }

        @Override // k1.d.b
        public Uri b() {
            return this.f8314a.getContentUri();
        }

        @Override // k1.d.b
        public void c() {
            this.f8314a.requestPermission();
        }

        @Override // k1.d.b
        public Uri d() {
            return this.f8314a.getLinkUri();
        }

        @Override // k1.d.b
        public ClipDescription getDescription() {
            return this.f8314a.getDescription();
        }
    }

    /* loaded from: classes.dex */
    private interface b {
        Object a();

        Uri b();

        void c();

        Uri d();

        ClipDescription getDescription();
    }

    private d(b bVar) {
        this.f8313a = bVar;
    }

    public static d f(Object obj) {
        if (obj == null) {
            return null;
        }
        return new d(new a(obj));
    }

    public Uri a() {
        return this.f8313a.b();
    }

    public ClipDescription b() {
        return this.f8313a.getDescription();
    }

    public Uri c() {
        return this.f8313a.d();
    }

    public void d() {
        this.f8313a.c();
    }

    public Object e() {
        return this.f8313a.a();
    }
}
