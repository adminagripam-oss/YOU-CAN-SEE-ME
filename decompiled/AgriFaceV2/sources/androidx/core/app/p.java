package androidx.core.app;

import android.content.res.Configuration;
/* loaded from: classes.dex */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f2918a;

    /* renamed from: b  reason: collision with root package name */
    private Configuration f2919b;

    public p(boolean z7) {
        this.f2918a = z7;
    }

    public final boolean a() {
        return this.f2918a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public p(boolean z7, Configuration configuration) {
        this(z7);
        d7.k.e(configuration, "newConfig");
        this.f2919b = configuration;
    }
}
