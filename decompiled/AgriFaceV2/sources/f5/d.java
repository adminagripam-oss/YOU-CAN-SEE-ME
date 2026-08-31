package f5;

import d5.f;
import d5.g;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
/* loaded from: classes.dex */
public final class d implements e5.b {

    /* renamed from: e  reason: collision with root package name */
    private static final d5.d f6775e = f5.a.b();

    /* renamed from: f  reason: collision with root package name */
    private static final f f6776f = f5.b.b();

    /* renamed from: g  reason: collision with root package name */
    private static final f f6777g = c.b();

    /* renamed from: h  reason: collision with root package name */
    private static final b f6778h = new b(null);

    /* renamed from: a  reason: collision with root package name */
    private final Map f6779a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final Map f6780b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private d5.d f6781c = f6775e;

    /* renamed from: d  reason: collision with root package name */
    private boolean f6782d = false;

    /* loaded from: classes.dex */
    class a implements d5.a {
        a() {
        }

        @Override // d5.a
        public String a(Object obj) {
            StringWriter stringWriter = new StringWriter();
            try {
                b(obj, stringWriter);
            } catch (IOException unused) {
            }
            return stringWriter.toString();
        }

        @Override // d5.a
        public void b(Object obj, Writer writer) {
            e eVar = new e(writer, d.this.f6779a, d.this.f6780b, d.this.f6781c, d.this.f6782d);
            eVar.f(obj, false);
            eVar.m();
        }
    }

    /* loaded from: classes.dex */
    private static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        private static final DateFormat f6784a;

        static {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            f6784a = simpleDateFormat;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        private b() {
        }

        /* synthetic */ b(a aVar) {
            this();
        }

        @Override // d5.f
        /* renamed from: b */
        public void a(Date date, g gVar) {
            gVar.b(f6784a.format(date));
        }
    }

    public d() {
        m(String.class, f6776f);
        m(Boolean.class, f6777g);
        m(Date.class, f6778h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void i(Object obj, d5.e eVar) {
        throw new d5.b("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
    }

    public d5.a f() {
        return new a();
    }

    public d g(e5.a aVar) {
        aVar.a(this);
        return this;
    }

    public d h(boolean z7) {
        this.f6782d = z7;
        return this;
    }

    @Override // e5.b
    /* renamed from: l */
    public d a(Class cls, d5.d dVar) {
        this.f6779a.put(cls, dVar);
        this.f6780b.remove(cls);
        return this;
    }

    public d m(Class cls, f fVar) {
        this.f6780b.put(cls, fVar);
        this.f6779a.remove(cls);
        return this;
    }
}
