package j4;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public abstract class t {

    /* renamed from: a  reason: collision with root package name */
    private static final CopyOnWriteArrayList f8278a = new CopyOnWriteArrayList();

    public static s a(String str) {
        Iterator it = f8278a.iterator();
        while (it.hasNext()) {
            s sVar = (s) it.next();
            if (sVar.b(str)) {
                return sVar;
            }
        }
        throw new GeneralSecurityException("No KMS client does support: " + str);
    }
}
