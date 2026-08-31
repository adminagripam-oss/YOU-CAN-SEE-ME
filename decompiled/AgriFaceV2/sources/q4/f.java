package q4;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import t4.d0;
import t4.i;
import t4.k0;
import t4.v;
import y4.i0;
/* loaded from: classes.dex */
public class f implements j4.f {

    /* renamed from: a  reason: collision with root package name */
    private final j4.f f10343a;

    /* renamed from: b  reason: collision with root package name */
    private final i0 f10344b;

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f10345c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10346a;

        static {
            int[] iArr = new int[i0.values().length];
            f10346a = iArr;
            try {
                iArr[i0.RAW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10346a[i0.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10346a[i0.CRUNCHY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10346a[i0.TINK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private f(j4.f fVar, i0 i0Var, byte[] bArr) {
        this.f10343a = fVar;
        this.f10344b = i0Var;
        this.f10345c = bArr;
    }

    public static j4.f c(i iVar) {
        a5.a aVar;
        d0 e8 = iVar.e(j4.g.a());
        j4.f fVar = (j4.f) t4.e.d().a(e8.f(), j4.f.class).b(e8.g());
        i0 e9 = e8.e();
        int i8 = a.f10346a[e9.ordinal()];
        if (i8 == 1) {
            aVar = v.f11883a;
        } else if (i8 == 2 || i8 == 3) {
            aVar = v.a(iVar.a().intValue());
        } else if (i8 != 4) {
            throw new GeneralSecurityException("unknown output prefix type " + e9.f());
        } else {
            aVar = v.b(iVar.a().intValue());
        }
        return new f(fVar, e9, aVar.d());
    }

    @Override // j4.f
    public byte[] a(byte[] bArr, byte[] bArr2) {
        return this.f10344b == i0.RAW ? this.f10343a.a(bArr, bArr2) : z4.f.a(this.f10345c, this.f10343a.a(bArr, bArr2));
    }

    @Override // j4.f
    public byte[] b(byte[] bArr, byte[] bArr2) {
        if (this.f10344b == i0.RAW) {
            return this.f10343a.b(bArr, bArr2);
        }
        if (k0.e(this.f10345c, bArr)) {
            return this.f10343a.b(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("wrong prefix");
    }
}
