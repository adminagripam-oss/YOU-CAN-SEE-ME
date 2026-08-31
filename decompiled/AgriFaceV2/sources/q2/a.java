package q2;
/* loaded from: classes.dex */
public enum a {
    denied,
    deniedForever,
    whileInUse,
    always;

    /* renamed from: q2.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class C0143a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10315a;

        static {
            int[] iArr = new int[a.values().length];
            f10315a = iArr;
            try {
                iArr[a.denied.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10315a[a.deniedForever.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10315a[a.whileInUse.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10315a[a.always.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int e() {
        int i8 = C0143a.f10315a[ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                if (i8 != 3) {
                    if (i8 == 4) {
                        return 3;
                    }
                    throw new IndexOutOfBoundsException();
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }
}
