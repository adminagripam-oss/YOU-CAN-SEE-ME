package v4;

import j4.v;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import t4.d0;
import y4.i0;
/* loaded from: classes.dex */
public final class o implements v {

    /* renamed from: d  reason: collision with root package name */
    private static final byte[] f12382d = {0};

    /* renamed from: a  reason: collision with root package name */
    private final v f12383a;

    /* renamed from: b  reason: collision with root package name */
    private final i0 f12384b;

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f12385c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f12386a;

        static {
            int[] iArr = new int[i0.values().length];
            f12386a = iArr;
            try {
                iArr[i0.RAW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12386a[i0.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f12386a[i0.CRUNCHY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f12386a[i0.TINK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private o(v vVar, i0 i0Var, byte[] bArr) {
        this.f12383a = vVar;
        this.f12384b = i0Var;
        this.f12385c = bArr;
    }

    public static v c(t4.i iVar) {
        a5.a aVar;
        d0 e8 = iVar.e(j4.g.a());
        v vVar = (v) t4.e.d().a(e8.f(), v.class).b(e8.g());
        i0 e9 = e8.e();
        int i8 = a.f12386a[e9.ordinal()];
        if (i8 == 1) {
            aVar = t4.v.f11883a;
        } else if (i8 == 2 || i8 == 3) {
            aVar = t4.v.a(iVar.a().intValue());
        } else if (i8 != 4) {
            throw new GeneralSecurityException("unknown output prefix type");
        } else {
            aVar = t4.v.b(iVar.a().intValue());
        }
        return new o(vVar, e9, aVar.d());
    }

    @Override // j4.v
    public void a(byte[] bArr, byte[] bArr2) {
        if (bArr.length < 10) {
            throw new GeneralSecurityException("tag too short");
        }
        if (this.f12384b.equals(i0.LEGACY)) {
            bArr2 = z4.f.a(bArr2, f12382d);
        }
        byte[] bArr3 = new byte[0];
        if (!this.f12384b.equals(i0.RAW)) {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            bArr = Arrays.copyOfRange(bArr, 5, bArr.length);
            bArr3 = copyOf;
        }
        if (!Arrays.equals(this.f12385c, bArr3)) {
            throw new GeneralSecurityException("wrong prefix");
        }
        this.f12383a.a(bArr, bArr2);
    }

    @Override // j4.v
    public byte[] b(byte[] bArr) {
        if (this.f12384b.equals(i0.LEGACY)) {
            bArr = z4.f.a(bArr, f12382d);
        }
        return z4.f.a(this.f12385c, this.f12383a.b(bArr));
    }
}
