package com.google.android.datatransport.runtime.scheduling.jobscheduling;
/* loaded from: classes.dex */
final /* synthetic */ class a implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private static final a f4938e = new a();

    private a() {
    }

    public static Runnable a() {
        return f4938e;
    }

    @Override // java.lang.Runnable
    public void run() {
        AlarmManagerSchedulerBroadcastReceiver.a();
    }
}
