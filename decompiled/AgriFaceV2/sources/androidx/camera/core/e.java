package androidx.camera.core;

import android.graphics.Rect;
import android.media.Image;
import androidx.camera.core.n;
import java.util.HashSet;
import java.util.Set;
import y.w0;
/* loaded from: classes.dex */
public abstract class e implements n {

    /* renamed from: f  reason: collision with root package name */
    protected final n f2678f;

    /* renamed from: e  reason: collision with root package name */
    private final Object f2677e = new Object();

    /* renamed from: g  reason: collision with root package name */
    private final Set f2679g = new HashSet();

    /* loaded from: classes.dex */
    public interface a {
        void b(n nVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public e(n nVar) {
        this.f2678f = nVar;
    }

    public void b(a aVar) {
        synchronized (this.f2677e) {
            this.f2679g.add(aVar);
        }
    }

    protected void c() {
        HashSet<a> hashSet;
        synchronized (this.f2677e) {
            hashSet = new HashSet(this.f2679g);
        }
        for (a aVar : hashSet) {
            aVar.b(this);
        }
    }

    @Override // androidx.camera.core.n, java.lang.AutoCloseable
    public void close() {
        this.f2678f.close();
        c();
    }

    @Override // androidx.camera.core.n
    public int e() {
        return this.f2678f.e();
    }

    @Override // androidx.camera.core.n
    public int getHeight() {
        return this.f2678f.getHeight();
    }

    @Override // androidx.camera.core.n
    public int getWidth() {
        return this.f2678f.getWidth();
    }

    @Override // androidx.camera.core.n
    public n.a[] i() {
        return this.f2678f.i();
    }

    @Override // androidx.camera.core.n
    public void l(Rect rect) {
        this.f2678f.l(rect);
    }

    @Override // androidx.camera.core.n
    public w0 p() {
        return this.f2678f.p();
    }

    @Override // androidx.camera.core.n
    public Image z() {
        return this.f2678f.z();
    }
}
