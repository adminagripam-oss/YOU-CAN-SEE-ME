package com.pichillilorenzo.flutter_inappwebview_android.types;

import i6.i;
import i6.j;
/* loaded from: classes.dex */
public class ChannelDelegateImpl implements IChannelDelegate {
    private j channel;

    public ChannelDelegateImpl(j jVar) {
        this.channel = jVar;
        jVar.e(this);
    }

    public void dispose() {
        j jVar = this.channel;
        if (jVar != null) {
            jVar.e(null);
            this.channel = null;
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate
    public j getChannel() {
        return this.channel;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
    }
}
