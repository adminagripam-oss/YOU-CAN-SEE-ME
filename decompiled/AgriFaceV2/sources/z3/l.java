package z3;

import d5.c;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes.dex */
final class l implements d5.e {

    /* renamed from: f  reason: collision with root package name */
    private static final Charset f13841f = Charset.forName("UTF-8");

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f13842g;

    /* renamed from: h  reason: collision with root package name */
    private static final d5.c f13843h;

    /* renamed from: i  reason: collision with root package name */
    private static final d5.d f13844i;

    /* renamed from: a  reason: collision with root package name */
    private OutputStream f13845a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f13846b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f13847c;

    /* renamed from: d  reason: collision with root package name */
    private final d5.d f13848d;

    /* renamed from: e  reason: collision with root package name */
    private final p f13849e = new p(this);

    static {
        c.b a8 = d5.c.a("key");
        f fVar = new f();
        fVar.a(1);
        f13842g = a8.b(fVar.b()).a();
        c.b a9 = d5.c.a("value");
        f fVar2 = new f();
        fVar2.a(2);
        f13843h = a9.b(fVar2.b()).a();
        f13844i = new d5.d() { // from class: z3.k
            @Override // d5.d
            public final void a(Object obj, Object obj2) {
                l.i((Map.Entry) obj, (d5.e) obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(OutputStream outputStream, Map map, Map map2, d5.d dVar) {
        this.f13845a = outputStream;
        this.f13846b = map;
        this.f13847c = map2;
        this.f13848d = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void i(Map.Entry entry, d5.e eVar) {
        eVar.c(f13842g, entry.getKey());
        eVar.c(f13843h, entry.getValue());
    }

    private static int j(d5.c cVar) {
        j jVar = (j) cVar.c(j.class);
        if (jVar != null) {
            return jVar.zza();
        }
        throw new d5.b("Field has no @Protobuf config");
    }

    private final long k(d5.d dVar, Object obj) {
        g gVar = new g();
        try {
            OutputStream outputStream = this.f13845a;
            this.f13845a = gVar;
            dVar.a(obj, this);
            this.f13845a = outputStream;
            long b8 = gVar.b();
            gVar.close();
            return b8;
        } catch (Throwable th) {
            try {
                gVar.close();
            } catch (Throwable th2) {
                try {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                } catch (Exception unused) {
                }
            }
            throw th;
        }
    }

    private static j l(d5.c cVar) {
        j jVar = (j) cVar.c(j.class);
        if (jVar != null) {
            return jVar;
        }
        throw new d5.b("Field has no @Protobuf config");
    }

    private final l m(d5.d dVar, d5.c cVar, Object obj, boolean z7) {
        long k8 = k(dVar, obj);
        if (z7 && k8 == 0) {
            return this;
        }
        p((j(cVar) << 3) | 2);
        q(k8);
        dVar.a(obj, this);
        return this;
    }

    private final l n(d5.f fVar, d5.c cVar, Object obj, boolean z7) {
        this.f13849e.a(cVar, z7);
        fVar.a(obj, this.f13849e);
        return this;
    }

    private static ByteBuffer o(int i8) {
        return ByteBuffer.allocate(i8).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void p(int i8) {
        while ((i8 & (-128)) != 0) {
            this.f13845a.write((i8 & 127) | 128);
            i8 >>>= 7;
        }
        this.f13845a.write(i8 & 127);
    }

    private final void q(long j8) {
        while (((-128) & j8) != 0) {
            this.f13845a.write((((int) j8) & 127) | 128);
            j8 >>>= 7;
        }
        this.f13845a.write(((int) j8) & 127);
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
        this.f13845a.write(o(8).putDouble(d8).array());
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
        this.f13845a.write(o(4).putFloat(f8).array());
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
            byte[] bytes = charSequence.toString().getBytes(f13841f);
            p(bytes.length);
            this.f13845a.write(bytes);
            return this;
        } else if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                e(cVar, obj2, false);
            }
            return this;
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                m(f13844i, cVar, entry, false);
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
            this.f13845a.write(bArr);
            return this;
        } else {
            d5.d dVar = (d5.d) this.f13846b.get(obj.getClass());
            if (dVar != null) {
                m(dVar, cVar, obj, z7);
                return this;
            }
            d5.f fVar = (d5.f) this.f13847c.get(obj.getClass());
            if (fVar != null) {
                n(fVar, cVar, obj, z7);
                return this;
            } else if (obj instanceof h) {
                f(cVar, ((h) obj).zza(), true);
                return this;
            } else if (obj instanceof Enum) {
                f(cVar, ((Enum) obj).ordinal(), true);
                return this;
            } else {
                m(this.f13848d, cVar, obj, z7);
                return this;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final l f(d5.c cVar, int i8, boolean z7) {
        if (z7 && i8 == 0) {
            return this;
        }
        j l8 = l(cVar);
        i iVar = i.DEFAULT;
        int ordinal = l8.zzb().ordinal();
        if (ordinal == 0) {
            p(l8.zza() << 3);
            p(i8);
        } else if (ordinal == 1) {
            p(l8.zza() << 3);
            p((i8 + i8) ^ (i8 >> 31));
        } else if (ordinal == 2) {
            p((l8.zza() << 3) | 5);
            this.f13845a.write(o(4).putInt(i8).array());
        }
        return this;
    }

    final l g(d5.c cVar, long j8, boolean z7) {
        if (z7 && j8 == 0) {
            return this;
        }
        j l8 = l(cVar);
        i iVar = i.DEFAULT;
        int ordinal = l8.zzb().ordinal();
        if (ordinal == 0) {
            p(l8.zza() << 3);
            q(j8);
        } else if (ordinal == 1) {
            p(l8.zza() << 3);
            q((j8 >> 63) ^ (j8 + j8));
        } else if (ordinal == 2) {
            p((l8.zza() << 3) | 1);
            this.f13845a.write(o(8).putLong(j8).array());
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final l h(Object obj) {
        if (obj == null) {
            return this;
        }
        d5.d dVar = (d5.d) this.f13846b.get(obj.getClass());
        if (dVar != null) {
            dVar.a(obj, this);
            return this;
        }
        throw new d5.b("No encoder for ".concat(String.valueOf(obj.getClass())));
    }
}
