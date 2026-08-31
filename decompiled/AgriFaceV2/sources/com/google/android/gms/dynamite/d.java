package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
/* loaded from: classes.dex */
final class d implements DynamiteModule.b {
    @Override // com.google.android.gms.dynamite.DynamiteModule.b
    public final DynamiteModule.b.C0065b a(Context context, String str, DynamiteModule.b.a aVar) {
        DynamiteModule.b.C0065b c0065b = new DynamiteModule.b.C0065b();
        int b8 = aVar.b(context, str, true);
        c0065b.f5108b = b8;
        if (b8 != 0) {
            c0065b.f5109c = 1;
        } else {
            int a8 = aVar.a(context, str);
            c0065b.f5107a = a8;
            if (a8 != 0) {
                c0065b.f5109c = -1;
            }
        }
        return c0065b;
    }
}
