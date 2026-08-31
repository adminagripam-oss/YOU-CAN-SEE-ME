package y4;

import com.google.crypto.tink.shaded.protobuf.z;
/* loaded from: classes.dex */
public enum i0 implements z.a {
    UNKNOWN_PREFIX(0),
    TINK(1),
    LEGACY(2),
    RAW(3),
    CRUNCHY(4),
    WITH_ID_REQUIREMENT(5),
    UNRECOGNIZED(-1);
    

    /* renamed from: m  reason: collision with root package name */
    private static final z.b f13300m = new z.b() { // from class: y4.i0.a
    };

    /* renamed from: e  reason: collision with root package name */
    private final int f13302e;

    i0(int i8) {
        this.f13302e = i8;
    }

    public static i0 e(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 != 3) {
                        if (i8 != 4) {
                            if (i8 != 5) {
                                return null;
                            }
                            return WITH_ID_REQUIREMENT;
                        }
                        return CRUNCHY;
                    }
                    return RAW;
                }
                return LEGACY;
            }
            return TINK;
        }
        return UNKNOWN_PREFIX;
    }

    public final int f() {
        if (this != UNRECOGNIZED) {
            return this.f13302e;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
