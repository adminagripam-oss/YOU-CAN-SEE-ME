package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import u2.m;
import u2.r;
/* loaded from: classes.dex */
public class AlarmManagerSchedulerBroadcastReceiver extends BroadcastReceiver {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String queryParameter = intent.getData().getQueryParameter("backendName");
        String queryParameter2 = intent.getData().getQueryParameter("extras");
        int intValue = Integer.valueOf(intent.getData().getQueryParameter("priority")).intValue();
        int i8 = intent.getExtras().getInt("attemptNumber");
        r.f(context);
        m.a d8 = m.a().b(queryParameter).d(e3.a.b(intValue));
        if (queryParameter2 != null) {
            d8.c(Base64.decode(queryParameter2, 0));
        }
        r.c().e().g(d8.a(), i8, a.a());
    }
}
