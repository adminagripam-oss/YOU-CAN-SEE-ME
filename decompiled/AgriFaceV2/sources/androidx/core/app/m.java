package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import c.a;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class m {

    /* renamed from: d  reason: collision with root package name */
    private static String f2896d;

    /* renamed from: g  reason: collision with root package name */
    private static e f2899g;

    /* renamed from: a  reason: collision with root package name */
    private final Context f2900a;

    /* renamed from: b  reason: collision with root package name */
    private final NotificationManager f2901b;

    /* renamed from: c  reason: collision with root package name */
    private static final Object f2895c = new Object();

    /* renamed from: e  reason: collision with root package name */
    private static Set f2897e = new HashSet();

    /* renamed from: f  reason: collision with root package name */
    private static final Object f2898f = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static boolean a(NotificationManager notificationManager) {
            return notificationManager.areNotificationsEnabled();
        }

        static int b(NotificationManager notificationManager) {
            return notificationManager.getImportance();
        }
    }

    /* loaded from: classes.dex */
    static class b {
        static void a(NotificationManager notificationManager, NotificationChannel notificationChannel) {
            notificationManager.createNotificationChannel(notificationChannel);
        }

        static void b(NotificationManager notificationManager, NotificationChannelGroup notificationChannelGroup) {
            notificationManager.createNotificationChannelGroup(notificationChannelGroup);
        }

        static void c(NotificationManager notificationManager, List<NotificationChannelGroup> list) {
            notificationManager.createNotificationChannelGroups(list);
        }

        static void d(NotificationManager notificationManager, List<NotificationChannel> list) {
            notificationManager.createNotificationChannels(list);
        }

        static void e(NotificationManager notificationManager, String str) {
            notificationManager.deleteNotificationChannel(str);
        }

        static void f(NotificationManager notificationManager, String str) {
            notificationManager.deleteNotificationChannelGroup(str);
        }

        static String g(NotificationChannel notificationChannel) {
            return notificationChannel.getId();
        }

        static String h(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getId();
        }

        static NotificationChannel i(NotificationManager notificationManager, String str) {
            return notificationManager.getNotificationChannel(str);
        }

        static List<NotificationChannelGroup> j(NotificationManager notificationManager) {
            return notificationManager.getNotificationChannelGroups();
        }

        static List<NotificationChannel> k(NotificationManager notificationManager) {
            return notificationManager.getNotificationChannels();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c implements f {

        /* renamed from: a  reason: collision with root package name */
        final String f2902a;

        /* renamed from: b  reason: collision with root package name */
        final int f2903b;

        /* renamed from: c  reason: collision with root package name */
        final String f2904c;

        /* renamed from: d  reason: collision with root package name */
        final Notification f2905d;

        c(String str, int i8, String str2, Notification notification) {
            this.f2902a = str;
            this.f2903b = i8;
            this.f2904c = str2;
            this.f2905d = notification;
        }

        @Override // androidx.core.app.m.f
        public void a(c.a aVar) {
            aVar.V(this.f2902a, this.f2903b, this.f2904c, this.f2905d);
        }

        public String toString() {
            return "NotifyTask[packageName:" + this.f2902a + ", id:" + this.f2903b + ", tag:" + this.f2904c + "]";
        }
    }

    /* loaded from: classes.dex */
    private static class d {

        /* renamed from: a  reason: collision with root package name */
        final ComponentName f2906a;

        /* renamed from: b  reason: collision with root package name */
        final IBinder f2907b;

        d(ComponentName componentName, IBinder iBinder) {
            this.f2906a = componentName;
            this.f2907b = iBinder;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class e implements Handler.Callback, ServiceConnection {

        /* renamed from: a  reason: collision with root package name */
        private final Context f2908a;

        /* renamed from: b  reason: collision with root package name */
        private final HandlerThread f2909b;

        /* renamed from: c  reason: collision with root package name */
        private final Handler f2910c;

        /* renamed from: d  reason: collision with root package name */
        private final Map f2911d = new HashMap();

        /* renamed from: e  reason: collision with root package name */
        private Set f2912e = new HashSet();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            final ComponentName f2913a;

            /* renamed from: c  reason: collision with root package name */
            c.a f2915c;

            /* renamed from: b  reason: collision with root package name */
            boolean f2914b = false;

            /* renamed from: d  reason: collision with root package name */
            ArrayDeque f2916d = new ArrayDeque();

            /* renamed from: e  reason: collision with root package name */
            int f2917e = 0;

            a(ComponentName componentName) {
                this.f2913a = componentName;
            }
        }

        e(Context context) {
            this.f2908a = context;
            HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
            this.f2909b = handlerThread;
            handlerThread.start();
            this.f2910c = new Handler(handlerThread.getLooper(), this);
        }

        private boolean a(a aVar) {
            if (aVar.f2914b) {
                return true;
            }
            boolean bindService = this.f2908a.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(aVar.f2913a), this, 33);
            aVar.f2914b = bindService;
            if (bindService) {
                aVar.f2917e = 0;
            } else {
                Log.w("NotifManCompat", "Unable to bind to listener " + aVar.f2913a);
                this.f2908a.unbindService(this);
            }
            return aVar.f2914b;
        }

        private void b(a aVar) {
            if (aVar.f2914b) {
                this.f2908a.unbindService(this);
                aVar.f2914b = false;
            }
            aVar.f2915c = null;
        }

        private void c(f fVar) {
            j();
            for (a aVar : this.f2911d.values()) {
                aVar.f2916d.add(fVar);
                g(aVar);
            }
        }

        private void d(ComponentName componentName) {
            a aVar = (a) this.f2911d.get(componentName);
            if (aVar != null) {
                g(aVar);
            }
        }

        private void e(ComponentName componentName, IBinder iBinder) {
            a aVar = (a) this.f2911d.get(componentName);
            if (aVar != null) {
                aVar.f2915c = a.AbstractBinderC0061a.d(iBinder);
                aVar.f2917e = 0;
                g(aVar);
            }
        }

        private void f(ComponentName componentName) {
            a aVar = (a) this.f2911d.get(componentName);
            if (aVar != null) {
                b(aVar);
            }
        }

        private void g(a aVar) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + aVar.f2913a + ", " + aVar.f2916d.size() + " queued tasks");
            }
            if (aVar.f2916d.isEmpty()) {
                return;
            }
            if (!a(aVar) || aVar.f2915c == null) {
                i(aVar);
                return;
            }
            while (true) {
                f fVar = (f) aVar.f2916d.peek();
                if (fVar == null) {
                    break;
                }
                try {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Sending task " + fVar);
                    }
                    fVar.a(aVar.f2915c);
                    aVar.f2916d.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Remote service has died: " + aVar.f2913a);
                    }
                } catch (RemoteException e8) {
                    Log.w("NotifManCompat", "RemoteException communicating with " + aVar.f2913a, e8);
                }
            }
            if (aVar.f2916d.isEmpty()) {
                return;
            }
            i(aVar);
        }

        private void i(a aVar) {
            if (this.f2910c.hasMessages(3, aVar.f2913a)) {
                return;
            }
            int i8 = aVar.f2917e + 1;
            aVar.f2917e = i8;
            if (i8 <= 6) {
                int i9 = (1 << (i8 - 1)) * 1000;
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Scheduling retry for " + i9 + " ms");
                }
                this.f2910c.sendMessageDelayed(this.f2910c.obtainMessage(3, aVar.f2913a), i9);
                return;
            }
            Log.w("NotifManCompat", "Giving up on delivering " + aVar.f2916d.size() + " tasks to " + aVar.f2913a + " after " + aVar.f2917e + " retries");
            aVar.f2916d.clear();
        }

        private void j() {
            Set d8 = m.d(this.f2908a);
            if (d8.equals(this.f2912e)) {
                return;
            }
            this.f2912e = d8;
            List<ResolveInfo> queryIntentServices = this.f2908a.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet<ComponentName> hashSet = new HashSet();
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (d8.contains(resolveInfo.serviceInfo.packageName)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            for (ComponentName componentName2 : hashSet) {
                if (!this.f2911d.containsKey(componentName2)) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                    }
                    this.f2911d.put(componentName2, new a(componentName2));
                }
            }
            Iterator it = this.f2911d.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!hashSet.contains(entry.getKey())) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                    }
                    b((a) entry.getValue());
                    it.remove();
                }
            }
        }

        public void h(f fVar) {
            this.f2910c.obtainMessage(0, fVar).sendToTarget();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i8 = message.what;
            if (i8 == 0) {
                c((f) message.obj);
                return true;
            } else if (i8 == 1) {
                d dVar = (d) message.obj;
                e(dVar.f2906a, dVar.f2907b);
                return true;
            } else if (i8 == 2) {
                f((ComponentName) message.obj);
                return true;
            } else if (i8 != 3) {
                return false;
            } else {
                d((ComponentName) message.obj);
                return true;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName);
            }
            this.f2910c.obtainMessage(1, new d(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName);
            }
            this.f2910c.obtainMessage(2, componentName).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface f {
        void a(c.a aVar);
    }

    private m(Context context) {
        this.f2900a = context;
        this.f2901b = (NotificationManager) context.getSystemService("notification");
    }

    public static m c(Context context) {
        return new m(context);
    }

    public static Set d(Context context) {
        Set set;
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (f2895c) {
            if (string != null) {
                if (!string.equals(f2896d)) {
                    String[] split = string.split(":", -1);
                    HashSet hashSet = new HashSet(split.length);
                    for (String str : split) {
                        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                        if (unflattenFromString != null) {
                            hashSet.add(unflattenFromString.getPackageName());
                        }
                    }
                    f2897e = hashSet;
                    f2896d = string;
                }
            }
            set = f2897e;
        }
        return set;
    }

    private void g(f fVar) {
        synchronized (f2898f) {
            if (f2899g == null) {
                f2899g = new e(this.f2900a.getApplicationContext());
            }
            f2899g.h(fVar);
        }
    }

    private static boolean h(Notification notification) {
        Bundle a8 = j.a(notification);
        return a8 != null && a8.getBoolean("android.support.useSideChannel");
    }

    public boolean a() {
        return a.a(this.f2901b);
    }

    public void b(NotificationChannel notificationChannel) {
        b.a(this.f2901b, notificationChannel);
    }

    public void e(int i8, Notification notification) {
        f(null, i8, notification);
    }

    public void f(String str, int i8, Notification notification) {
        if (!h(notification)) {
            this.f2901b.notify(str, i8, notification);
            return;
        }
        g(new c(this.f2900a.getPackageName(), i8, str, notification));
        this.f2901b.cancel(str, i8);
    }
}
