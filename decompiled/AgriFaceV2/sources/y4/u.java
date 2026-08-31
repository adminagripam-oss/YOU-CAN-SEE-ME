package y4;

import com.google.crypto.tink.shaded.protobuf.z;
/* loaded from: classes.dex */
public enum u implements z.a {
    UNKNOWN_HASH(0),
    SHA1(1),
    SHA384(2),
    SHA256(3),
    SHA512(4),
    SHA224(5),
    UNRECOGNIZED(-1);
    

    /* renamed from: m  reason: collision with root package name */
    private static final z.b f13327m = new z.b() { // from class: y4.u.a
    };

    /* renamed from: e  reason: collision with root package name */
    private final int f13329e;

    u(int i8) {
        this.f13329e = i8;
    }

    public static u e(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 != 3) {
                        if (i8 != 4) {
                            if (i8 != 5) {
                                return null;
                            }
                            return SHA224;
                        }
                        return SHA512;
                    }
                    return SHA256;
                }
                return SHA384;
            }
            return SHA1;
        }
        return UNKNOWN_HASH;
    }

    public final int f() {
        if (this != UNRECOGNIZED) {
            return this.f13329e;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
