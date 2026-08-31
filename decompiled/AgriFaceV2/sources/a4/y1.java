package a4;

import d5.c;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes.dex */
final class y1 implements d5.e {

    /* renamed from: f  reason: collision with root package name */
    private static final Charset f1284f = Charset.forName("UTF-8");

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f1285g;

    /* renamed from: h  reason: collision with root package name */
    private static final d5.c f1286h;

    /* renamed from: i  reason: collision with root package name */
    private static final d5.d f1287i;

    /* renamed from: a  reason: collision with root package name */
    private OutputStream f1288a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f1289b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f1290c;

    /* renamed from: d  reason: collision with root package name */
    private final d5.d f1291d;

    /* renamed from: e  reason: collision with root package name */
    private final d2 f1292e = new d2(this);

    static {
        c.b a8 = d5.c.a("key");
        s1 s1Var = new s1();
        s1Var.a(1);
        f1285g = a8.b(s1Var.b()).a();
        c.b a9 = d5.c.a("value");
        s1 s1Var2 = new s1();
        s1Var2.a(2);
        f1286h = a9.b(s1Var2.b()).a();
        f1287i = new d5.d() { // from class: a4.x1
            @Override // d5.d
            public final void a(Object obj, Object obj2) {
                y1.i((Map.Entry) obj, (d5.e) obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public y1(OutputStream outputStream, Map map, Map map2, d5.d dVar) {
        this.f1288a = outputStream;
        this.f1289b = map;
        this.f1290c = map2;
        this.f1291d = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void i(Map.Entry entry, d5.e eVar) {
        eVar.c(f1285g, entry.getKey());
        eVar.c(f1286h, entry.getValue());
    }

    private static int j(d5.c cVar) {
        w1 w1Var = (w1) cVar.c(w1.class);
        if (w1Var != null) {
            return w1Var.zza();
        }
        throw new d5.b("Field has no @Protobuf config");
    }

    private final long k(d5.d dVar, Object obj) {
        t1 t1Var = new t1();
        try {
            OutputStream outputStream = this.f1288a;
            this.f1288a = t1Var;
            dVar.a(obj, this);
            this.f1288a = outputStream;
            long b8 = t1Var.b();
            t1Var.close();
            return b8;
        } catch (Throwable th) {
            try {
                t1Var.close();
            } catch (Throwable th2) {
                try {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                } catch (Exception unused) {
                }
            }
            throw th;
        }
    }

    private static w1 l(d5.c cVar) {
        w1 w1Var = (w1) cVar.c(w1.class);
        if (w1Var != null) {
            return w1Var;
        }
        throw new d5.b("Field has no @Protobuf config");
    }

    private final y1 m(d5.d dVar, d5.c cVar, Object obj, boolean z7) {
        long k8 = k(dVar, obj);
        if (z7 && k8 == 0) {
            return this;
        }
        p((j(cVar) << 3) | 2);
        q(k8);
        dVar.a(obj, this);
        return this;
    }

    private final y1 n(d5.f fVar, d5.c cVar, Object obj, boolean z7) {
        this.f1292e.a(cVar, z7);
        fVar.a(obj, this.f1292e);
        return this;
    }

    private static ByteBuffer o(int i8) {
        return ByteBuffer.allocate(i8).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void p(int i8) {
        while ((i8 & (-128)) != 0) {
            this.f1288a.write((i8 & 127) | 128);
            i8 >>>= 7;
        }
        this.f1288a.write(i8 & 127);
    }

    private final void q(long j8) {
        while (((-128) & j8) != 0) {
            this.f1288a.write((((int) j8) & 127) | 128);
            j8 >>>= 7;
        }
        this.f1288a.write(((int) j8) & 127);
    }

    @Override // d5.e
    public final /* synthetic */ d5.e a(d5.c cVar, long j8) {
        g(cVar, j8, true);
        return this;
    }

    final d5.e b(d5.c cVar, double d8, boolean z7) {
        if (z7 && d8 == 0.0d) {
            return this;
        }
        p((j(cVar) << 3) | 1);
        this.f1288a.write(o(8).putDouble(d8).array());
        return this;
    }

    @Override // d5.e
    public final d5.e c(d5.c cVar, Object obj) {
        e(cVar, obj, true);
        return this;
    }

    final d5.e d(d5.c cVar, float f8, boolean z7) {
        if (z7 && f8 == 0.0f) {
            return this;
        }
        p((j(cVar) << 3) | 5);
        this.f1288a.write(o(4).putFloat(f8).array());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final d5.e e(d5.c cVar, Object obj, boolean z7) {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z7 && charSequence.length() == 0) {
                return this;
            }
            p((j(cVar) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(f1284f);
            p(bytes.length);
            this.f1288a.write(bytes);
            return this;
        } else if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                e(cVar, obj2, false);
            }
            return this;
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                m(f1287i, cVar, entry, false);
            }
            return this;
        } else if (obj instanceof Double) {
            b(cVar, ((Double) obj).doubleValue(), z7);
            return this;
        } else if (obj instanceof Float) {
            d(cVar, ((Float) obj).floatValue(), z7);
            return this;
        } else if (obj instanceof Number) {
            g(cVar, ((Number) obj).longValue(), z7);
            return this;
        } else if (obj instanceof Boolean) {
            f(cVar, ((Boolean) obj).booleanValue() ? 1 : 0, z7);
            return this;
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (z7 && bArr.length == 0) {
                return this;
            }
            p((j(cVar) << 3) | 2);
            p(bArr.length);
            this.f1288a.write(bArr);
            return this;
        } else {
            d5.d dVar = (d5.d) this.f1289b.get(obj.getClass());
            if (dVar != null) {
                m(dVar, cVar, obj, z7);
                return this;
            }
            d5.f fVar = (d5.f) this.f1290c.get(obj.getClass());
            if (fVar != null) {
                n(fVar, cVar, obj, z7);
                return this;
            } else if (obj instanceof u1) {
                f(cVar, ((u1) obj).zza(), true);
                return this;
            } else if (obj instanceof Enum) {
                f(cVar, ((Enum) obj).ordinal(), true);
                return this;
            } else {
                m(this.f1291d, cVar, obj, z7);
                return this;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final y1 f(d5.c cVar, int i8, boolean z7) {
        if (z7 && i8 == 0) {
            return this;
        }
        w1 l8 = l(cVar);
        v1 v1Var = v1.DEFAULT;
        int ordinal = l8.zzb().ordinal();
        if (ordinal == 0) {
            p(l8.zza() << 3);
            p(i8);
        } else if (ordinal == 1) {
            p(l8.zza() << 3);
            p((i8 + i8) ^ (i8 >> 31));
        } else if (ordinal == 2) {
            p((l8.zza() << 3) | 5);
            this.f1288a.write(o(4).putInt(i8).array());
        }
        return this;
    }

    final y1 g(d5.c cVar, long j8, boolean z7) {
        if (z7 && j8 == 0) {
            return this;
        }
        w1 l8 = l(cVar);
        v1 v1Var = v1.DEFAULT;
        int ordinal = l8.zzb().ordinal();
        if (ordinal == 0) {
            p(l8.zza() << 3);
            q(j8);
        } else if (ordinal == 1) {
            p(l8.zza() << 3);
            q((j8 >> 63) ^ (j8 + j8));
        } else if (ordinal == 2) {
            p((l8.zza() << 3) | 1);
            this.f1288a.write(o(8).putLong(j8).array());
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final y1 h(Object obj) {
        if (obj == null) {
            return this;
        }
        d5.d dVar = (d5.d) this.f1289b.get(obj.getClass());
        if (dVar != null) {
            dVar.a(obj, this);
            return this;
        }
        throw new d5.b("No encoder for ".concat(String.valueOf(obj.getClass())));
    }
}
