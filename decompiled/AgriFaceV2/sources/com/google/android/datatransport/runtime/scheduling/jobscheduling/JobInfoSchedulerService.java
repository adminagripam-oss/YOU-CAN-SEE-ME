package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Base64;
import u2.m;
import u2.r;
/* loaded from: classes.dex */
public class JobInfoSchedulerService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        String string = jobParameters.getExtras().getString("backendName");
        String string2 = jobParameters.getExtras().getString("extras");
        int i8 = jobParameters.getExtras().getInt("priority");
        int i9 = jobParameters.getExtras().getInt("attemptNumber");
        r.f(getApplicationContext());
        m.a d8 = m.a().b(string).d(e3.a.b(i8));
        if (string2 != null) {
            d8.c(Base64.decode(string2, 0));
        }
        r.c().e().g(d8.a(), i9, b.a(this, jobParameters));
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
