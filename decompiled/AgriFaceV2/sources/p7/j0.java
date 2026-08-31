package p7;
/* loaded from: classes.dex */
abstract /* synthetic */ class j0 {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10156a = Runtime.getRuntime().availableProcessors();

    public static final int a() {
        return f10156a;
    }

    public static final String b(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }
}
