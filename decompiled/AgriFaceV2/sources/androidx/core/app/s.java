package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public final class s implements Iterable {

    /* renamed from: e  reason: collision with root package name */
    private final ArrayList f2920e = new ArrayList();

    /* renamed from: f  reason: collision with root package name */
    private final Context f2921f;

    /* loaded from: classes.dex */
    public interface a {
        Intent getSupportParentActivityIntent();
    }

    private s(Context context) {
        this.f2921f = context;
    }

    public static s k(Context context) {
        return new s(context);
    }

    public s b(Intent intent) {
        this.f2920e.add(intent);
        return this;
    }

    public s f(Activity activity) {
        Intent supportParentActivityIntent = activity instanceof a ? ((a) activity).getSupportParentActivityIntent() : null;
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = h.a(activity);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(this.f2921f.getPackageManager());
            }
            j(component);
            b(supportParentActivityIntent);
        }
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return this.f2920e.iterator();
    }

    public s j(ComponentName componentName) {
        int size = this.f2920e.size();
        try {
            Context context = this.f2921f;
            while (true) {
                Intent b8 = h.b(context, componentName);
                if (b8 == null) {
                    return this;
                }
                this.f2920e.add(size, b8);
                context = this.f2921f;
                componentName = b8.getComponent();
            }
        } catch (PackageManager.NameNotFoundException e8) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e8);
        }
    }

    public void l() {
        m(null);
    }

    public void m(Bundle bundle) {
        if (this.f2920e.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) this.f2920e.toArray(new Intent[0]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (androidx.core.content.a.k(this.f2921f, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.f2921f.startActivity(intent);
    }
}
