package l4;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* loaded from: classes.dex */
public class k0 implements j4.a {

    /* renamed from: a  reason: collision with root package name */
    private final j4.a f9006a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f9007b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9008a;

        static {
            int[] iArr = new int[y4.i0.values().length];
            f9008a = iArr;
            try {
                iArr[y4.i0.RAW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9008a[y4.i0.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9008a[y4.i0.CRUNCHY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9008a[y4.i0.TINK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private k0(j4.a aVar, byte[] bArr) {
        this.f9006a = aVar;
        if (bArr.length != 0 && bArr.length != 5) {
            throw new IllegalArgumentException("identifier has an invalid length");
        }
        this.f9007b = bArr;
    }

    public static j4.a c(j4.a aVar, a5.a aVar2) {
        return new k0(aVar, aVar2.d());
    }

    public static j4.a d(t4.i iVar) {
        a5.a aVar;
        t4.d0 e8 = iVar.e(j4.g.a());
        j4.a aVar2 = (j4.a) t4.e.d().a(e8.f(), j4.a.class).b(e8.g());
        y4.i0 e9 = e8.e();
        int i8 = a.f9008a[e9.ordinal()];
        if (i8 == 1) {
            aVar = t4.v.f11883a;
        } else if (i8 == 2 || i8 == 3) {
            aVar = t4.v.a(iVar.a().intValue());
        } else if (i8 != 4) {
            throw new GeneralSecurityException("unknown output prefix type " + e9);
        } else {
            aVar = t4.v.b(iVar.a().intValue());
        }
        return new k0(aVar2, aVar.d());
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = this.f9007b;
        return bArr3.length == 0 ? this.f9006a.a(bArr, bArr2) : z4.f.a(bArr3, this.f9006a.a(bArr, bArr2));
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = this.f9007b;
        if (bArr3.length == 0) {
            return this.f9006a.b(bArr, bArr2);
        }
        if (t4.k0.e(bArr3, bArr)) {
            return this.f9006a.b(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("wrong prefix");
    }
}
