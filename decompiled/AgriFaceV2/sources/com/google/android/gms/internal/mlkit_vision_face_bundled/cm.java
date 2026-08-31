package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class cm implements Iterator {

    /* renamed from: e  reason: collision with root package name */
    private int f5186e = -1;

    /* renamed from: f  reason: collision with root package name */
    private boolean f5187f;

    /* renamed from: g  reason: collision with root package name */
    private Iterator f5188g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ gm f5189h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ cm(gm gmVar, bm bmVar) {
        this.f5189h = gmVar;
    }

    private final Iterator b() {
        Map map;
        if (this.f5188g == null) {
            map = this.f5189h.f5351g;
            this.f5188g = map.entrySet().iterator();
        }
        return this.f5188g;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i8;
        Map map;
        int i9 = this.f5186e + 1;
        gm gmVar = this.f5189h;
        i8 = gmVar.f5350f;
        if (i9 >= i8) {
            map = gmVar.f5351g;
            return !map.isEmpty() && b().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        int i8;
        Object[] objArr;
        this.f5187f = true;
        int i9 = this.f5186e + 1;
        this.f5186e = i9;
        gm gmVar = this.f5189h;
        i8 = gmVar.f5350f;
        if (i9 < i8) {
            objArr = gmVar.f5349e;
            return (am) objArr[i9];
        }
        return (Map.Entry) b().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i8;
        if (!this.f5187f) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.f5187f = false;
        this.f5189h.o();
        int i9 = this.f5186e;
        gm gmVar = this.f5189h;
        i8 = gmVar.f5350f;
        if (i9 >= i8) {
            b().remove();
            return;
        }
        this.f5186e = i9 - 1;
        gmVar.m(i9);
    }
}
