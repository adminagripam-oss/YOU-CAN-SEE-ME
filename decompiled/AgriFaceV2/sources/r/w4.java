package r;

import b0.h3;
/* loaded from: classes.dex */
public abstract class w4 {

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10839a;

        static {
            int[] iArr = new int[h3.b.values().length];
            f10839a = iArr;
            try {
                iArr[h3.b.IMAGE_CAPTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10839a[h3.b.VIDEO_CAPTURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10839a[h3.b.STREAM_SHARING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10839a[h3.b.PREVIEW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10839a[h3.b.IMAGE_ANALYSIS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static int a(h3.b bVar, int i8) {
        int i9 = a.f10839a[bVar.ordinal()];
        return i9 != 1 ? i9 != 2 ? 1 : 3 : i8 == 2 ? 5 : 2;
    }

    public static int b(h3.b bVar, int i8) {
        int i9 = a.f10839a[bVar.ordinal()];
        return i9 != 1 ? i9 != 2 ? 1 : 3 : i8 == 2 ? 5 : 1;
    }
}
