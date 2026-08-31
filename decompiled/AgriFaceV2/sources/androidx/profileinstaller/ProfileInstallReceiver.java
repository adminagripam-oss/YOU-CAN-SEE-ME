package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import androidx.profileinstaller.i;
/* loaded from: classes.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements i.c {
        a() {
        }

        @Override // androidx.profileinstaller.i.c
        public void a(int i8, Object obj) {
            i.f3899b.a(i8, obj);
        }

        @Override // androidx.profileinstaller.i.c
        public void b(int i8, Object obj) {
            i.f3899b.b(i8, obj);
            ProfileInstallReceiver.this.setResultCode(i8);
        }
    }

    static void a(i.c cVar) {
        Process.sendSignal(Process.myPid(), 10);
        cVar.b(12, null);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
            i.k(context, new g(), new a(), true);
        } else if ("androidx.profileinstaller.action.SKIP_FILE".equals(action)) {
            Bundle extras2 = intent.getExtras();
            if (extras2 != null) {
                String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                if ("WRITE_SKIP_FILE".equals(string)) {
                    i.l(context, new g(), new a());
                } else if ("DELETE_SKIP_FILE".equals(string)) {
                    i.c(context, new g(), new a());
                }
            }
        } else if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
            a(new a());
        } else if (!"androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) || (extras = intent.getExtras()) == null) {
        } else {
            String string2 = extras.getString("EXTRA_BENCHMARK_OPERATION");
            a aVar = new a();
            if ("DROP_SHADER_CACHE".equals(string2)) {
                androidx.profileinstaller.a.b(context, aVar);
            } else {
                aVar.b(16, null);
            }
        }
    }
}
