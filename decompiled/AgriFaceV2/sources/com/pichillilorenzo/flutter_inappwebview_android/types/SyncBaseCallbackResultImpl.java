package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.concurrent.CountDownLatch;
/* loaded from: classes.dex */
public class SyncBaseCallbackResultImpl<T> extends BaseCallbackResultImpl<T> {
    public final CountDownLatch latch = new CountDownLatch(1);
    public T result = null;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
    public void defaultBehaviour(T t8) {
        this.latch.countDown();
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult, i6.j.d
    public void error(String str, String str2, Object obj) {
        this.latch.countDown();
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult, i6.j.d
    public void notImplemented() {
        defaultBehaviour(null);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult, i6.j.d
    public void success(Object obj) {
        T decodeResult = decodeResult(obj);
        this.result = decodeResult;
        if (decodeResult == null ? nullSuccess() : nonNullSuccess(decodeResult)) {
            defaultBehaviour(decodeResult);
        } else {
            this.latch.countDown();
        }
    }
}
