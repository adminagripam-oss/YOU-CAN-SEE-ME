package p1;

import android.text.Editable;
/* loaded from: classes.dex */
final class b extends Editable.Factory {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f9986a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static volatile Editable.Factory f9987b;

    /* renamed from: c  reason: collision with root package name */
    private static Class f9988c;

    private b() {
        try {
            f9988c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, b.class.getClassLoader());
        } catch (Throwable unused) {
        }
    }

    public static Editable.Factory getInstance() {
        if (f9987b == null) {
            synchronized (f9986a) {
                if (f9987b == null) {
                    f9987b = new b();
                }
            }
        }
        return f9987b;
    }

    @Override // android.text.Editable.Factory
    public Editable newEditable(CharSequence charSequence) {
        Class cls = f9988c;
        return cls != null ? n1.c.c(cls, charSequence) : super.newEditable(charSequence);
    }
}
