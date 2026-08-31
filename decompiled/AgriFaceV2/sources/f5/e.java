package f5;

import android.util.Base64;
import android.util.JsonWriter;
import d5.f;
import d5.g;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class e implements d5.e, g {

    /* renamed from: a  reason: collision with root package name */
    private e f6785a = null;

    /* renamed from: b  reason: collision with root package name */
    private boolean f6786b = true;

    /* renamed from: c  reason: collision with root package name */
    private final JsonWriter f6787c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f6788d;

    /* renamed from: e  reason: collision with root package name */
    private final Map f6789e;

    /* renamed from: f  reason: collision with root package name */
    private final d5.d f6790f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f6791g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(Writer writer, Map map, Map map2, d5.d dVar, boolean z7) {
        this.f6787c = new JsonWriter(writer);
        this.f6788d = map;
        this.f6789e = map2;
        this.f6790f = dVar;
        this.f6791g = z7;
    }

    private boolean l(Object obj) {
        return obj == null || obj.getClass().isArray() || (obj instanceof Collection) || (obj instanceof Date) || (obj instanceof Enum) || (obj instanceof Number);
    }

    private e o(String str, Object obj) {
        q();
        this.f6787c.name(str);
        if (obj == null) {
            this.f6787c.nullValue();
            return this;
        }
        return f(obj, false);
    }

    private e p(String str, Object obj) {
        if (obj == null) {
            return this;
        }
        q();
        this.f6787c.name(str);
        return f(obj, false);
    }

    private void q() {
        if (!this.f6786b) {
            throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
        }
        e eVar = this.f6785a;
        if (eVar != null) {
            eVar.q();
            this.f6785a.f6786b = false;
            this.f6785a = null;
            this.f6787c.endObject();
        }
    }

    @Override // d5.e
    public d5.e a(d5.c cVar, long j8) {
        return h(cVar.b(), j8);
    }

    @Override // d5.e
    public d5.e c(d5.c cVar, Object obj) {
        return i(cVar.b(), obj);
    }

    public e e(long j8) {
        q();
        this.f6787c.value(j8);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e f(Object obj, boolean z7) {
        int[] iArr;
        int i8 = 0;
        if (z7 && l(obj)) {
            Object[] objArr = new Object[1];
            objArr[0] = obj == null ? null : obj.getClass();
            throw new d5.b(String.format("%s cannot be encoded inline", objArr));
        } else if (obj == null) {
            this.f6787c.nullValue();
            return this;
        } else if (obj instanceof Number) {
            this.f6787c.value((Number) obj);
            return this;
        } else if (!obj.getClass().isArray()) {
            if (obj instanceof Collection) {
                this.f6787c.beginArray();
                for (Object obj2 : (Collection) obj) {
                    f(obj2, false);
                }
                this.f6787c.endArray();
                return this;
            } else if (obj instanceof Map) {
                this.f6787c.beginObject();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    Object key = entry.getKey();
                    try {
                        i((String) key, entry.getValue());
                    } catch (ClassCastException e8) {
                        throw new d5.b(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", key, key.getClass()), e8);
                    }
                }
                this.f6787c.endObject();
                return this;
            } else {
                d5.d dVar = (d5.d) this.f6788d.get(obj.getClass());
                if (dVar != null) {
                    return n(dVar, obj, z7);
                }
                f fVar = (f) this.f6789e.get(obj.getClass());
                if (fVar != null) {
                    fVar.a(obj, this);
                    return this;
                } else if (obj instanceof Enum) {
                    b(((Enum) obj).name());
                    return this;
                } else {
                    return n(this.f6790f, obj, z7);
                }
            }
        } else if (obj instanceof byte[]) {
            return k((byte[]) obj);
        } else {
            this.f6787c.beginArray();
            if (obj instanceof int[]) {
                int length = ((int[]) obj).length;
                while (i8 < length) {
                    this.f6787c.value(iArr[i8]);
                    i8++;
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                int length2 = jArr.length;
                while (i8 < length2) {
                    e(jArr[i8]);
                    i8++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                int length3 = dArr.length;
                while (i8 < length3) {
                    this.f6787c.value(dArr[i8]);
                    i8++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                int length4 = zArr.length;
                while (i8 < length4) {
                    this.f6787c.value(zArr[i8]);
                    i8++;
                }
            } else if (obj instanceof Number[]) {
                for (Number number : (Number[]) obj) {
                    f(number, false);
                }
            } else {
                for (Object obj3 : (Object[]) obj) {
                    f(obj3, false);
                }
            }
            this.f6787c.endArray();
            return this;
        }
    }

    @Override // d5.g
    /* renamed from: g */
    public e b(String str) {
        q();
        this.f6787c.value(str);
        return this;
    }

    public e h(String str, long j8) {
        q();
        this.f6787c.name(str);
        return e(j8);
    }

    public e i(String str, Object obj) {
        return this.f6791g ? p(str, obj) : o(str, obj);
    }

    @Override // d5.g
    /* renamed from: j */
    public e d(boolean z7) {
        q();
        this.f6787c.value(z7);
        return this;
    }

    public e k(byte[] bArr) {
        q();
        if (bArr == null) {
            this.f6787c.nullValue();
        } else {
            this.f6787c.value(Base64.encodeToString(bArr, 2));
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        q();
        this.f6787c.flush();
    }

    e n(d5.d dVar, Object obj, boolean z7) {
        if (!z7) {
            this.f6787c.beginObject();
        }
        dVar.a(obj, this);
        if (!z7) {
            this.f6787c.endObject();
        }
        return this;
    }
}
