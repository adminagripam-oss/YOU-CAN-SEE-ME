package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    protected final b1.a f3985a;

    /* renamed from: b  reason: collision with root package name */
    protected final b1.a f3986b;

    /* renamed from: c  reason: collision with root package name */
    protected final b1.a f3987c;

    public a(b1.a aVar, b1.a aVar2, b1.a aVar3) {
        this.f3985a = aVar;
        this.f3986b = aVar2;
        this.f3987c = aVar3;
    }

    private void N(b2.a aVar) {
        try {
            I(c(aVar.getClass()).getName());
        } catch (ClassNotFoundException e8) {
            throw new RuntimeException(aVar.getClass().getSimpleName() + " does not have a Parcelizer", e8);
        }
    }

    private Class c(Class cls) {
        Class cls2 = (Class) this.f3987c.get(cls.getName());
        if (cls2 == null) {
            Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
            this.f3987c.put(cls.getName(), cls3);
            return cls3;
        }
        return cls2;
    }

    private Method d(String str) {
        Method method = (Method) this.f3985a.get(str);
        if (method == null) {
            System.currentTimeMillis();
            Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
            this.f3985a.put(str, declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    private Method e(Class cls) {
        Method method = (Method) this.f3986b.get(cls.getName());
        if (method == null) {
            Class c8 = c(cls);
            System.currentTimeMillis();
            Method declaredMethod = c8.getDeclaredMethod("write", cls, a.class);
            this.f3986b.put(cls.getName(), declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    protected abstract void A(byte[] bArr);

    public void B(byte[] bArr, int i8) {
        w(i8);
        A(bArr);
    }

    protected abstract void C(CharSequence charSequence);

    public void D(CharSequence charSequence, int i8) {
        w(i8);
        C(charSequence);
    }

    protected abstract void E(int i8);

    public void F(int i8, int i9) {
        w(i9);
        E(i8);
    }

    protected abstract void G(Parcelable parcelable);

    public void H(Parcelable parcelable, int i8) {
        w(i8);
        G(parcelable);
    }

    protected abstract void I(String str);

    public void J(String str, int i8) {
        w(i8);
        I(str);
    }

    protected void K(b2.a aVar, a aVar2) {
        try {
            e(aVar.getClass()).invoke(null, aVar, aVar2);
        } catch (ClassNotFoundException e8) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e8);
        } catch (IllegalAccessException e9) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e9);
        } catch (NoSuchMethodException e10) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e10);
        } catch (InvocationTargetException e11) {
            if (!(e11.getCause() instanceof RuntimeException)) {
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e11);
            }
            throw ((RuntimeException) e11.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void L(b2.a aVar) {
        if (aVar == null) {
            I(null);
            return;
        }
        N(aVar);
        a b8 = b();
        K(aVar, b8);
        b8.a();
    }

    public void M(b2.a aVar, int i8) {
        w(i8);
        L(aVar);
    }

    protected abstract void a();

    protected abstract a b();

    public boolean f() {
        return false;
    }

    protected abstract boolean g();

    public boolean h(boolean z7, int i8) {
        return !m(i8) ? z7 : g();
    }

    protected abstract byte[] i();

    public byte[] j(byte[] bArr, int i8) {
        return !m(i8) ? bArr : i();
    }

    protected abstract CharSequence k();

    public CharSequence l(CharSequence charSequence, int i8) {
        return !m(i8) ? charSequence : k();
    }

    protected abstract boolean m(int i8);

    protected b2.a n(String str, a aVar) {
        try {
            return (b2.a) d(str).invoke(null, aVar);
        } catch (ClassNotFoundException e8) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e8);
        } catch (IllegalAccessException e9) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e9);
        } catch (NoSuchMethodException e10) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e10);
        } catch (InvocationTargetException e11) {
            if (e11.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e11.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e11);
        }
    }

    protected abstract int o();

    public int p(int i8, int i9) {
        return !m(i9) ? i8 : o();
    }

    protected abstract Parcelable q();

    public Parcelable r(Parcelable parcelable, int i8) {
        return !m(i8) ? parcelable : q();
    }

    protected abstract String s();

    public String t(String str, int i8) {
        return !m(i8) ? str : s();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b2.a u() {
        String s8 = s();
        if (s8 == null) {
            return null;
        }
        return n(s8, b());
    }

    public b2.a v(b2.a aVar, int i8) {
        return !m(i8) ? aVar : u();
    }

    protected abstract void w(int i8);

    public void x(boolean z7, boolean z8) {
    }

    protected abstract void y(boolean z7);

    public void z(boolean z7, int i8) {
        w(i8);
        y(z7);
    }
}
