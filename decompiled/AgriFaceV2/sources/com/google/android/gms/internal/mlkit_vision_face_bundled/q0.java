package com.google.android.gms.internal.mlkit_vision_face_bundled;

import d5.c;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes.dex */
final class q0 implements d5.e {

    /* renamed from: f  reason: collision with root package name */
    private static final Charset f5541f = Charset.forName("UTF-8");

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f5542g;

    /* renamed from: h  reason: collision with root package name */
    private static final d5.c f5543h;

    /* renamed from: i  reason: collision with root package name */
    private static final d5.d f5544i;

    /* renamed from: a  reason: collision with root package name */
    private OutputStream f5545a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f5546b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f5547c;

    /* renamed from: d  reason: collision with root package name */
    private final d5.d f5548d;

    /* renamed from: e  reason: collision with root package name */
    private final u0 f5549e = new u0(this);

    static {
        c.b a8 = d5.c.a("key");
        k0 k0Var = new k0();
        k0Var.a(1);
        f5542g = a8.b(k0Var.b()).a();
        c.b a9 = d5.c.a("value");
        k0 k0Var2 = new k0();
        k0Var2.a(2);
        f5543h = a9.b(k0Var2.b()).a();
        f5544i = new d5.d() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.p0
            @Override // d5.d
            public final void a(Object obj, Object obj2) {
                q0.i((Map.Entry) obj, (d5.e) obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q0(OutputStream outputStream, Map map, Map map2, d5.d dVar) {
        this.f5545a = outputStream;
        this.f5546b = map;
        this.f5547c = map2;
        this.f5548d = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void i(Map.Entry entry, d5.e eVar) {
        eVar.c(f5542g, entry.getKey());
        eVar.c(f5543h, entry.getValue());
    }

    private static int j(d5.c cVar) {
        o0 o0Var = (o0) cVar.c(o0.class);
        if (o0Var != null) {
            return o0Var.zza();
        }
        throw new d5.b("Field has no @Protobuf config");
    }

    private final long k(d5.d dVar, Object obj) {
        l0 l0Var = new l0();
        try {
            OutputStream outputStream = this.f5545a;
            this.f5545a = l0Var;
            dVar.a(obj, this);
            this.f5545a = outputStream;
            long b8 = l0Var.b();
            l0Var.close();
            return b8;
        } catch (Throwable th) {
            try {
                l0Var.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static o0 l(d5.c cVar) {
        o0 o0Var = (o0) cVar.c(o0.class);
        if (o0Var != null) {
            return o0Var;
        }
        throw new d5.b("Field has no @Protobuf config");
    }

    private final q0 m(d5.d dVar, d5.c cVar, Object obj, boolean z7) {
        long k8 = k(dVar, obj);
        if (z7 && k8 == 0) {
            return this;
        }
        p((j(cVar) << 3) | 2);
        q(k8);
        dVar.a(obj, this);
        return this;
    }

    private final q0 n(d5.f fVar, d5.c cVar, Object obj, boolean z7) {
        this.f5549e.a(cVar, z7);
        fVar.a(obj, this.f5549e);
        return this;
    }

    private static ByteBuffer o(int i8) {
        return ByteBuffer.allocate(i8).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void p(int i8) {
        while (true) {
            int i9 = i8 & 127;
            if ((i8 & (-128)) == 0) {
                this.f5545a.write(i9);
                return;
            } else {
                this.f5545a.write(i9 | 128);
                i8 >>>= 7;
            }
        }
    }

    private final void q(long j8) {
        while (true) {
            int i8 = ((int) j8) & 127;
            if (((-128) & j8) == 0) {
                this.f5545a.write(i8);
                return;
            } else {
                this.f5545a.write(i8 | 128);
                j8 >>>= 7;
            }
        }
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
        this.f5545a.write(o(8).putDouble(d8).array());
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
        this.f5545a.write(o(4).putFloat(f8).array());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final d5.e e(d5.c cVar, Object obj, boolean z7) {
        if (obj != null) {
            if (obj instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) obj;
                if (!z7 || charSequence.length() != 0) {
                    p((j(cVar) << 3) | 2);
                    byte[] bytes = charSequence.toString().getBytes(f5541f);
                    p(bytes.length);
                    this.f5545a.write(bytes);
                    return this;
                }
            } else if (obj instanceof Collection) {
                for (Object obj2 : (Collection) obj) {
                    e(cVar, obj2, false);
                }
            } else if (obj instanceof Map) {
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    m(f5544i, cVar, entry, false);
                }
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
            } else if (!(obj instanceof byte[])) {
                d5.d dVar = (d5.d) this.f5546b.get(obj.getClass());
                if (dVar != null) {
                    m(dVar, cVar, obj, z7);
                    return this;
                }
                d5.f fVar = (d5.f) this.f5547c.get(obj.getClass());
                if (fVar != null) {
                    n(fVar, cVar, obj, z7);
                    return this;
                } else if (obj instanceof m0) {
                    f(cVar, ((m0) obj).zza(), true);
                    return this;
                } else if (obj instanceof Enum) {
                    f(cVar, ((Enum) obj).ordinal(), true);
                    return this;
                } else {
                    m(this.f5548d, cVar, obj, z7);
                    return this;
                }
            } else {
                byte[] bArr = (byte[]) obj;
                if (!z7 || bArr.length != 0) {
                    p((j(cVar) << 3) | 2);
                    p(bArr.length);
                    this.f5545a.write(bArr);
                    return this;
                }
            }
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final q0 f(d5.c cVar, int i8, boolean z7) {
        if (!z7 || i8 != 0) {
            o0 l8 = l(cVar);
            int ordinal = l8.zzb().ordinal();
            if (ordinal == 0) {
                p(l8.zza() << 3);
                p(i8);
            } else if (ordinal == 1) {
                p(l8.zza() << 3);
                p((i8 + i8) ^ (i8 >> 31));
            } else if (ordinal == 2) {
                p((l8.zza() << 3) | 5);
                this.f5545a.write(o(4).putInt(i8).array());
            }
        }
        return this;
    }

    final q0 g(d5.c cVar, long j8, boolean z7) {
        if (!z7 || j8 != 0) {
            o0 l8 = l(cVar);
            int ordinal = l8.zzb().ordinal();
            if (ordinal == 0) {
                p(l8.zza() << 3);
                q(j8);
            } else if (ordinal == 1) {
                p(l8.zza() << 3);
                q((j8 >> 63) ^ (j8 + j8));
            } else if (ordinal == 2) {
                p((l8.zza() << 3) | 1);
                this.f5545a.write(o(8).putLong(j8).array());
            }
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final q0 h(Object obj) {
        if (obj == null) {
            return this;
        }
        d5.d dVar = (d5.d) this.f5546b.get(obj.getClass());
        if (dVar != null) {
            dVar.a(obj, this);
            return this;
        }
        throw new d5.b("No encoder for ".concat(String.valueOf(obj.getClass())));
    }
}
