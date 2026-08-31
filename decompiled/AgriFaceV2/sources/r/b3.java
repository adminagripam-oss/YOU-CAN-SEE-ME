package r;

import android.hardware.camera2.CameraDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import r.b3;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b3 {

    /* renamed from: a  reason: collision with root package name */
    final Executor f10395a;

    /* renamed from: b  reason: collision with root package name */
    final Object f10396b = new Object();

    /* renamed from: c  reason: collision with root package name */
    final Set f10397c = new LinkedHashSet();

    /* renamed from: d  reason: collision with root package name */
    final Set f10398d = new LinkedHashSet();

    /* renamed from: e  reason: collision with root package name */
    final Set f10399e = new LinkedHashSet();

    /* renamed from: f  reason: collision with root package name */
    private final CameraDevice.StateCallback f10400f = new a();

    /* loaded from: classes.dex */
    class a extends CameraDevice.StateCallback {
        a() {
        }

        private void c() {
            List<k4> f8;
            synchronized (b3.this.f10396b) {
                f8 = b3.this.f();
                b3.this.f10399e.clear();
                b3.this.f10397c.clear();
                b3.this.f10398d.clear();
            }
            for (k4 k4Var : f8) {
                k4Var.b();
            }
        }

        private void d(final int i8) {
            final LinkedHashSet linkedHashSet = new LinkedHashSet();
            synchronized (b3.this.f10396b) {
                linkedHashSet.addAll(b3.this.f10399e);
                linkedHashSet.addAll(b3.this.f10397c);
            }
            b3.this.f10395a.execute(new Runnable() { // from class: r.z2
                @Override // java.lang.Runnable
                public final void run() {
                    b3.a.f(linkedHashSet, i8);
                }
            });
        }

        private void e() {
            final LinkedHashSet linkedHashSet = new LinkedHashSet();
            synchronized (b3.this.f10396b) {
                linkedHashSet.addAll(b3.this.f10399e);
                linkedHashSet.addAll(b3.this.f10397c);
            }
            b3.this.f10395a.execute(new Runnable() { // from class: r.a3
                @Override // java.lang.Runnable
                public final void run() {
                    b3.b(linkedHashSet);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void f(LinkedHashSet linkedHashSet, int i8) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                ((k4) it.next()).j(i8);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            e();
            c();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            e();
            c();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i8) {
            e();
            d(i8);
            c();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b3(Executor executor) {
        this.f10395a = executor;
    }

    private void a(k4 k4Var) {
        k4 k4Var2;
        Iterator it = f().iterator();
        while (it.hasNext() && (k4Var2 = (k4) it.next()) != k4Var) {
            k4Var2.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            k4 k4Var = (k4) it.next();
            k4Var.a().q(k4Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraDevice.StateCallback c() {
        return this.f10400f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List d() {
        ArrayList arrayList;
        synchronized (this.f10396b) {
            arrayList = new ArrayList(this.f10397c);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List e() {
        ArrayList arrayList;
        synchronized (this.f10396b) {
            arrayList = new ArrayList(this.f10399e);
        }
        return arrayList;
    }

    List f() {
        ArrayList arrayList;
        synchronized (this.f10396b) {
            arrayList = new ArrayList();
            arrayList.addAll(d());
            arrayList.addAll(e());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(k4 k4Var) {
        synchronized (this.f10396b) {
            this.f10397c.remove(k4Var);
            this.f10398d.remove(k4Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(k4 k4Var) {
        synchronized (this.f10396b) {
            this.f10398d.add(k4Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(k4 k4Var) {
        a(k4Var);
        synchronized (this.f10396b) {
            this.f10399e.remove(k4Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(k4 k4Var) {
        synchronized (this.f10396b) {
            this.f10397c.add(k4Var);
            this.f10399e.remove(k4Var);
        }
        a(k4Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(k4 k4Var) {
        synchronized (this.f10396b) {
            this.f10399e.add(k4Var);
        }
    }
}
