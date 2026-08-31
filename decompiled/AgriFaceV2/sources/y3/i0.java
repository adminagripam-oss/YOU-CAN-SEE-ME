package y3;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public final class i0 {

    /* renamed from: i  reason: collision with root package name */
    private static final i f13235i = i.c("optional-module-barcode", "com.google.android.gms.vision.barcode");

    /* renamed from: a  reason: collision with root package name */
    private final String f13236a;

    /* renamed from: b  reason: collision with root package name */
    private final String f13237b;

    /* renamed from: c  reason: collision with root package name */
    private final b0 f13238c;

    /* renamed from: d  reason: collision with root package name */
    private final m5.m f13239d;

    /* renamed from: e  reason: collision with root package name */
    private final f4.j f13240e;

    /* renamed from: f  reason: collision with root package name */
    private final f4.j f13241f;

    /* renamed from: g  reason: collision with root package name */
    private final String f13242g;

    /* renamed from: h  reason: collision with root package name */
    private final int f13243h;

    public i0(Context context, final m5.m mVar, b0 b0Var, String str) {
        new HashMap();
        new HashMap();
        this.f13236a = context.getPackageName();
        this.f13237b = m5.c.a(context);
        this.f13239d = mVar;
        this.f13238c = b0Var;
        s0.a();
        this.f13242g = str;
        this.f13240e = m5.g.a().b(new Callable() { // from class: y3.g0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return i0.this.a();
            }
        });
        m5.g a8 = m5.g.a();
        Objects.requireNonNull(mVar);
        this.f13241f = a8.b(new Callable() { // from class: y3.h0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return m5.m.this.a();
            }
        });
        i iVar = f13235i;
        this.f13243h = iVar.containsKey(str) ? DynamiteModule.b(context, (String) iVar.get(str)) : -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ String a() {
        return k3.n.a().b(this.f13242g);
    }
}
