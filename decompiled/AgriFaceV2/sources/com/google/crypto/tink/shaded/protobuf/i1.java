package com.google.crypto.tink.shaded.protobuf;

import java.util.List;
/* loaded from: classes.dex */
public class i1 extends RuntimeException {

    /* renamed from: e  reason: collision with root package name */
    private final List f6113e;

    public i1(p0 p0Var) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.f6113e = null;
    }

    public a0 a() {
        return new a0(getMessage());
    }
}
