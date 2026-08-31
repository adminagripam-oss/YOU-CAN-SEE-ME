package t3;

import android.os.IBinder;
import java.lang.reflect.Field;
import k3.q;
import t3.a;
/* loaded from: classes.dex */
public final class b extends a.AbstractBinderC0152a {

    /* renamed from: e  reason: collision with root package name */
    private final Object f11811e;

    private b(Object obj) {
        this.f11811e = obj;
    }

    public static a d0(Object obj) {
        return new b(obj);
    }

    public static Object f(a aVar) {
        if (aVar instanceof b) {
            return ((b) aVar).f11811e;
        }
        IBinder asBinder = aVar.asBinder();
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        Field field = null;
        int i8 = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i8++;
                field = field2;
            }
        }
        if (i8 != 1) {
            throw new IllegalArgumentException("Unexpected number of IObjectWrapper declared fields: " + declaredFields.length);
        }
        q.g(field);
        if (field.isAccessible()) {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        field.setAccessible(true);
        try {
            return field.get(asBinder);
        } catch (IllegalAccessException e8) {
            throw new IllegalArgumentException("Could not access the field in remoteBinder.", e8);
        } catch (NullPointerException e9) {
            throw new IllegalArgumentException("Binder object is null.", e9);
        }
    }
}
