package a3;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Base64;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.zip.Adler32;
/* loaded from: classes.dex */
public class c implements p {

    /* renamed from: a  reason: collision with root package name */
    private final Context f180a;

    /* renamed from: b  reason: collision with root package name */
    private final b3.c f181b;

    /* renamed from: c  reason: collision with root package name */
    private final d f182c;

    public c(Context context, b3.c cVar, d dVar) {
        this.f180a = context;
        this.f181b = cVar;
        this.f182c = dVar;
    }

    private boolean d(JobScheduler jobScheduler, int i8, int i9) {
        for (JobInfo jobInfo : jobScheduler.getAllPendingJobs()) {
            int i10 = jobInfo.getExtras().getInt("attemptNumber");
            if (jobInfo.getId() == i8) {
                return i10 >= i9;
            }
        }
        return false;
    }

    @Override // a3.p
    public void a(u2.m mVar, int i8) {
        b(mVar, i8, false);
    }

    @Override // a3.p
    public void b(u2.m mVar, int i8, boolean z7) {
        ComponentName componentName = new ComponentName(this.f180a, JobInfoSchedulerService.class);
        JobScheduler jobScheduler = (JobScheduler) this.f180a.getSystemService("jobscheduler");
        int c8 = c(mVar);
        if (!z7 && d(jobScheduler, c8, i8)) {
            x2.a.a("JobInfoScheduler", "Upload for context %s is already scheduled. Returning...", mVar);
            return;
        }
        long u7 = this.f181b.u(mVar);
        JobInfo.Builder c9 = this.f182c.c(new JobInfo.Builder(c8, componentName), mVar.d(), u7, i8);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putInt("attemptNumber", i8);
        persistableBundle.putString("backendName", mVar.b());
        persistableBundle.putInt("priority", e3.a.a(mVar.d()));
        if (mVar.c() != null) {
            persistableBundle.putString("extras", Base64.encodeToString(mVar.c(), 0));
        }
        c9.setExtras(persistableBundle);
        x2.a.b("JobInfoScheduler", "Scheduling upload for context %s with jobId=%d in %dms(Backend next call timestamp %d). Attempt %d", mVar, Integer.valueOf(c8), Long.valueOf(this.f182c.g(mVar.d(), u7, i8)), Long.valueOf(u7), Integer.valueOf(i8));
        jobScheduler.schedule(c9.build());
    }

    int c(u2.m mVar) {
        Adler32 adler32 = new Adler32();
        adler32.update(this.f180a.getPackageName().getBytes(Charset.forName("UTF-8")));
        adler32.update(mVar.b().getBytes(Charset.forName("UTF-8")));
        adler32.update(ByteBuffer.allocate(4).putInt(e3.a.a(mVar.d())).array());
        if (mVar.c() != null) {
            adler32.update(mVar.c());
        }
        return (int) adler32.getValue();
    }
}
