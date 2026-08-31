package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
/* loaded from: classes.dex */
final class f implements DynamiteModule.b {
    @Override // com.google.android.gms.dynamite.DynamiteModule.b
    public final DynamiteModule.b.C0065b a(Context context, String str, DynamiteModule.b.a aVar) {
        DynamiteModule.b.C0065b c0065b = new DynamiteModule.b.C0065b();
        int b8 = aVar.b(context, str, false);
        c0065b.f5108b = b8;
        c0065b.f5109c = b8 != 0 ? 1 : 0;
        return c0065b;
    }
}
