package y4;

import com.google.crypto.tink.shaded.protobuf.z;
/* loaded from: classes.dex */
public enum z implements z.a {
    UNKNOWN_STATUS(0),
    ENABLED(1),
    DISABLED(2),
    DESTROYED(3),
    UNRECOGNIZED(-1);
    

    /* renamed from: k  reason: collision with root package name */
    private static final z.b f13348k = new z.b() { // from class: y4.z.a
    };

    /* renamed from: e  reason: collision with root package name */
    private final int f13350e;

    z(int i8) {
        this.f13350e = i8;
    }

    public static z e(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 != 3) {
                        return null;
                    }
                    return DESTROYED;
                }
                return DISABLED;
            }
            return ENABLED;
        }
        return UNKNOWN_STATUS;
    }

    public final int f() {
        if (this != UNRECOGNIZED) {
            return this.f13350e;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
