package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.i0;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class l0 implements k0 {
    private static int i(int i8, Object obj, Object obj2) {
        j0 j0Var = (j0) obj;
        androidx.appcompat.app.w.a(obj2);
        if (j0Var.isEmpty()) {
            return 0;
        }
        Iterator it = j0Var.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw null;
        }
        return 0;
    }

    private static j0 j(Object obj, Object obj2) {
        j0 j0Var = (j0) obj;
        j0 j0Var2 = (j0) obj2;
        if (!j0Var2.isEmpty()) {
            if (!j0Var.j()) {
                j0Var = j0Var.m();
            }
            j0Var.l(j0Var2);
        }
        return j0Var;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k0
    public Object a(Object obj, Object obj2) {
        return j(obj, obj2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k0
    public Object b(Object obj) {
        return j0.e().m();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k0
    public int c(int i8, Object obj, Object obj2) {
        return i(i8, obj, obj2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k0
    public boolean d(Object obj) {
        return !((j0) obj).j();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k0
    public Object e(Object obj) {
        ((j0) obj).k();
        return obj;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k0
    public i0.a f(Object obj) {
        androidx.appcompat.app.w.a(obj);
        throw null;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k0
    public Map g(Object obj) {
        return (j0) obj;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k0
    public Map h(Object obj) {
        return (j0) obj;
    }
}
