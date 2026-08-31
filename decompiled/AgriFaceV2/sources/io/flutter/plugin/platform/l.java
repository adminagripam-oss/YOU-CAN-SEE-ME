package io.flutter.plugin.platform;

import android.content.Context;
/* loaded from: classes.dex */
public abstract class l {
    private final i6.h createArgsCodec;

    public l(i6.h hVar) {
        this.createArgsCodec = hVar;
    }

    public abstract k create(Context context, int i8, Object obj);

    public final i6.h getCreateArgsCodec() {
        return this.createArgsCodec;
    }
}
