package a3;

import a3.b;
import android.app.job.JobInfo;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class d {

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private d3.a f183a;

        /* renamed from: b  reason: collision with root package name */
        private Map f184b = new HashMap();

        public a a(s2.d dVar, b bVar) {
            this.f184b.put(dVar, bVar);
            return this;
        }

        public d b() {
            if (this.f183a != null) {
                if (this.f184b.keySet().size() >= s2.d.values().length) {
                    Map map = this.f184b;
                    this.f184b = new HashMap();
                    return d.d(this.f183a, map);
                }
                throw new IllegalStateException("Not all priorities have been configured");
            }
            throw new NullPointerException("missing required property: clock");
        }

        public a c(d3.a aVar) {
            this.f183a = aVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {

        /* loaded from: classes.dex */
        public static abstract class a {
            public abstract b a();

            public abstract a b(long j8);

            public abstract a c(Set set);

            public abstract a d(long j8);
        }

        public static a a() {
            return new b.C0000b().c(Collections.emptySet());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long b();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Set c();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long d();
    }

    /* loaded from: classes.dex */
    public enum c {
        NETWORK_UNMETERED,
        DEVICE_IDLE,
        DEVICE_CHARGING
    }

    private long a(int i8, long j8) {
        int i9;
        return (long) (Math.pow(3.0d, i8 - 1) * j8 * Math.max(1.0d, Math.log(10000.0d) / Math.log((j8 > 1 ? j8 : 2L) * i9)));
    }

    public static a b() {
        return new a();
    }

    static d d(d3.a aVar, Map map) {
        return new a3.a(aVar, map);
    }

    public static d f(d3.a aVar) {
        return b().a(s2.d.DEFAULT, b.a().b(30000L).d(86400000L).a()).a(s2.d.HIGHEST, b.a().b(1000L).d(86400000L).a()).a(s2.d.VERY_LOW, b.a().b(86400000L).d(86400000L).c(i(c.NETWORK_UNMETERED, c.DEVICE_IDLE)).a()).c(aVar).b();
    }

    private static Set i(Object... objArr) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(objArr)));
    }

    private void j(JobInfo.Builder builder, Set set) {
        if (set.contains(c.NETWORK_UNMETERED)) {
            builder.setRequiredNetworkType(2);
        } else {
            builder.setRequiredNetworkType(1);
        }
        if (set.contains(c.DEVICE_CHARGING)) {
            builder.setRequiresCharging(true);
        }
        if (set.contains(c.DEVICE_IDLE)) {
            builder.setRequiresDeviceIdle(true);
        }
    }

    public JobInfo.Builder c(JobInfo.Builder builder, s2.d dVar, long j8, int i8) {
        builder.setMinimumLatency(g(dVar, j8, i8));
        j(builder, ((b) h().get(dVar)).c());
        return builder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract d3.a e();

    public long g(s2.d dVar, long j8, int i8) {
        long a8 = j8 - e().a();
        b bVar = (b) h().get(dVar);
        return Math.min(Math.max(a(i8, bVar.b()), a8), bVar.d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Map h();
}
