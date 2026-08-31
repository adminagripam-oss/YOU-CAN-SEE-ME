package t4;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class q {

    /* renamed from: b  reason: collision with root package name */
    private static final q f11877b = new q();

    /* renamed from: a  reason: collision with root package name */
    private final Map f11878a = new HashMap();

    q() {
    }

    public static q b() {
        return f11877b;
    }

    public synchronized j4.w a(String str) {
        if (!this.f11878a.containsKey(str)) {
            throw new GeneralSecurityException("Name " + str + " does not exist");
        }
        return (j4.w) this.f11878a.get(str);
    }

    public synchronized void c(String str, j4.w wVar) {
        if (!this.f11878a.containsKey(str)) {
            this.f11878a.put(str, wVar);
        } else if (((j4.w) this.f11878a.get(str)).equals(wVar)) {
        } else {
            throw new GeneralSecurityException("Parameters object with name " + str + " already exists (" + this.f11878a.get(str) + "), cannot insert " + wVar);
        }
    }

    public synchronized void d(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            c((String) entry.getKey(), (j4.w) entry.getValue());
        }
    }
}
