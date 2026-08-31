package androidx.core.app;

import android.content.res.Configuration;
/* loaded from: classes.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f2839a;

    /* renamed from: b  reason: collision with root package name */
    private Configuration f2840b;

    public g(boolean z7) {
        this.f2839a = z7;
    }

    public final boolean a() {
        return this.f2839a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public g(boolean z7, Configuration configuration) {
        this(z7);
        d7.k.e(configuration, "newConfig");
        this.f2840b = configuration;
    }
}
