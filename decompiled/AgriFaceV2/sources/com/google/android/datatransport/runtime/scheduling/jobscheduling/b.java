package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;
/* loaded from: classes.dex */
final /* synthetic */ class b implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private final JobInfoSchedulerService f4939e;

    /* renamed from: f  reason: collision with root package name */
    private final JobParameters f4940f;

    private b(JobInfoSchedulerService jobInfoSchedulerService, JobParameters jobParameters) {
        this.f4939e = jobInfoSchedulerService;
        this.f4940f = jobParameters;
    }

    public static Runnable a(JobInfoSchedulerService jobInfoSchedulerService, JobParameters jobParameters) {
        return new b(jobInfoSchedulerService, jobParameters);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4939e.jobFinished(this.f4940f, false);
    }
}
