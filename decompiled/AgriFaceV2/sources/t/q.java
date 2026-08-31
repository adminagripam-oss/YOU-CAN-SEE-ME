package t;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.os.Build;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    private final c f11620a;

    /* loaded from: classes.dex */
    private static final class a implements c {

        /* renamed from: a  reason: collision with root package name */
        private final SessionConfiguration f11621a;

        /* renamed from: b  reason: collision with root package name */
        private final List f11622b;

        a(int i8, List list, Executor executor, CameraCaptureSession.StateCallback stateCallback) {
            this(new SessionConfiguration(i8, q.h(list), executor, stateCallback));
        }

        @Override // t.q.c
        public CameraCaptureSession.StateCallback a() {
            return this.f11621a.getStateCallback();
        }

        @Override // t.q.c
        public j b() {
            return j.b(this.f11621a.getInputConfiguration());
        }

        @Override // t.q.c
        public Executor c() {
            return this.f11621a.getExecutor();
        }

        @Override // t.q.c
        public Object d() {
            return this.f11621a;
        }

        @Override // t.q.c
        public int e() {
            return this.f11621a.getSessionType();
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                return Objects.equals(this.f11621a, ((a) obj).f11621a);
            }
            return false;
        }

        @Override // t.q.c
        public void f(j jVar) {
            this.f11621a.setInputConfiguration((InputConfiguration) jVar.a());
        }

        @Override // t.q.c
        public List g() {
            return this.f11622b;
        }

        @Override // t.q.c
        public void h(CaptureRequest captureRequest) {
            this.f11621a.setSessionParameters(captureRequest);
        }

        public int hashCode() {
            return this.f11621a.hashCode();
        }

        a(Object obj) {
            SessionConfiguration sessionConfiguration = (SessionConfiguration) obj;
            this.f11621a = sessionConfiguration;
            this.f11622b = Collections.unmodifiableList(q.i(sessionConfiguration.getOutputConfigurations()));
        }
    }

    /* loaded from: classes.dex */
    private static final class b implements c {

        /* renamed from: a  reason: collision with root package name */
        private final List f11623a;

        /* renamed from: b  reason: collision with root package name */
        private final CameraCaptureSession.StateCallback f11624b;

        /* renamed from: c  reason: collision with root package name */
        private final Executor f11625c;

        /* renamed from: d  reason: collision with root package name */
        private final int f11626d;

        /* renamed from: e  reason: collision with root package name */
        private j f11627e = null;

        /* renamed from: f  reason: collision with root package name */
        private CaptureRequest f11628f = null;

        b(int i8, List list, Executor executor, CameraCaptureSession.StateCallback stateCallback) {
            this.f11626d = i8;
            this.f11623a = Collections.unmodifiableList(new ArrayList(list));
            this.f11624b = stateCallback;
            this.f11625c = executor;
        }

        @Override // t.q.c
        public CameraCaptureSession.StateCallback a() {
            return this.f11624b;
        }

        @Override // t.q.c
        public j b() {
            return this.f11627e;
        }

        @Override // t.q.c
        public Executor c() {
            return this.f11625c;
        }

        @Override // t.q.c
        public Object d() {
            return null;
        }

        @Override // t.q.c
        public int e() {
            return this.f11626d;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (Objects.equals(this.f11627e, bVar.f11627e) && this.f11626d == bVar.f11626d && this.f11623a.size() == bVar.f11623a.size()) {
                    for (int i8 = 0; i8 < this.f11623a.size(); i8++) {
                        if (!((k) this.f11623a.get(i8)).equals(bVar.f11623a.get(i8))) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        @Override // t.q.c
        public void f(j jVar) {
            if (this.f11626d == 1) {
                throw new UnsupportedOperationException("Method not supported for high speed session types");
            }
            this.f11627e = jVar;
        }

        @Override // t.q.c
        public List g() {
            return this.f11623a;
        }

        @Override // t.q.c
        public void h(CaptureRequest captureRequest) {
            this.f11628f = captureRequest;
        }

        public int hashCode() {
            int hashCode = this.f11623a.hashCode() ^ 31;
            int i8 = (hashCode << 5) - hashCode;
            j jVar = this.f11627e;
            int hashCode2 = (jVar == null ? 0 : jVar.hashCode()) ^ i8;
            return this.f11626d ^ ((hashCode2 << 5) - hashCode2);
        }
    }

    /* loaded from: classes.dex */
    private interface c {
        CameraCaptureSession.StateCallback a();

        j b();

        Executor c();

        Object d();

        int e();

        void f(j jVar);

        List g();

        void h(CaptureRequest captureRequest);
    }

    public q(int i8, List list, Executor executor, CameraCaptureSession.StateCallback stateCallback) {
        this.f11620a = Build.VERSION.SDK_INT < 28 ? new b(i8, list, executor, stateCallback) : new a(i8, list, executor, stateCallback);
    }

    public static List h(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((OutputConfiguration) ((k) it.next()).i());
        }
        return arrayList;
    }

    static List i(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(k.j((OutputConfiguration) it.next()));
        }
        return arrayList;
    }

    public Executor a() {
        return this.f11620a.c();
    }

    public j b() {
        return this.f11620a.b();
    }

    public List c() {
        return this.f11620a.g();
    }

    public int d() {
        return this.f11620a.e();
    }

    public CameraCaptureSession.StateCallback e() {
        return this.f11620a.a();
    }

    public boolean equals(Object obj) {
        if (obj instanceof q) {
            return this.f11620a.equals(((q) obj).f11620a);
        }
        return false;
    }

    public void f(j jVar) {
        this.f11620a.f(jVar);
    }

    public void g(CaptureRequest captureRequest) {
        this.f11620a.h(captureRequest);
    }

    public int hashCode() {
        return this.f11620a.hashCode();
    }

    public Object j() {
        return this.f11620a.d();
    }
}
