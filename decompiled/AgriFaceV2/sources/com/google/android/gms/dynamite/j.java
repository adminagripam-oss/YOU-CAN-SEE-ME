package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
/* loaded from: classes.dex */
final class j implements DynamiteModule.b {
    @Override // com.google.android.gms.dynamite.DynamiteModule.b
    public final DynamiteModule.b.C0065b a(Context context, String str, DynamiteModule.b.a aVar) {
        DynamiteModule.b.C0065b c0065b = new DynamiteModule.b.C0065b();
        int a8 = aVar.a(context, str);
        c0065b.f5107a = a8;
        int i8 = 1;
        int i9 = 0;
        int b8 = a8 != 0 ? aVar.b(context, str, false) : aVar.b(context, str, true);
        c0065b.f5108b = b8;
        int i10 = c0065b.f5107a;
        if (i10 != 0) {
            i9 = i10;
        } else if (b8 == 0) {
            i8 = 0;
            c0065b.f5109c = i8;
            return c0065b;
        }
        if (b8 < i9) {
            i8 = -1;
        }
        c0065b.f5109c = i8;
        return c0065b;
    }
}
