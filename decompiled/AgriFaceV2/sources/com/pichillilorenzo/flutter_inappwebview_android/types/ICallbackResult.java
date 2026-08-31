package com.pichillilorenzo.flutter_inappwebview_android.types;

import i6.j;
/* loaded from: classes.dex */
public interface ICallbackResult<T> extends j.d {
    T decodeResult(Object obj);

    void defaultBehaviour(T t8);

    @Override // i6.j.d
    /* synthetic */ void error(String str, String str2, Object obj);

    boolean nonNullSuccess(T t8);

    @Override // i6.j.d
    /* synthetic */ void notImplemented();

    boolean nullSuccess();

    @Override // i6.j.d
    /* synthetic */ void success(Object obj);
}
