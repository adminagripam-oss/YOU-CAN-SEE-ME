package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class gm extends AbstractMap {

    /* renamed from: e  reason: collision with root package name */
    private Object[] f5349e;

    /* renamed from: f  reason: collision with root package name */
    private int f5350f;

    /* renamed from: h  reason: collision with root package name */
    private boolean f5352h;

    /* renamed from: i  reason: collision with root package name */
    private volatile em f5353i;

    /* renamed from: g  reason: collision with root package name */
    private Map f5351g = Collections.emptyMap();

    /* renamed from: j  reason: collision with root package name */
    private Map f5354j = Collections.emptyMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ gm(fm fmVar) {
    }

    private final int l(Comparable comparable) {
        int i8 = this.f5350f - 1;
        int i9 = 0;
        if (i8 >= 0) {
            int compareTo = comparable.compareTo(((am) this.f5349e[i8]).d());
            if (compareTo > 0) {
                return -(i8 + 2);
            }
            if (compareTo == 0) {
                return i8;
            }
        }
        while (i9 <= i8) {
            int i10 = (i9 + i8) / 2;
            int compareTo2 = comparable.compareTo(((am) this.f5349e[i10]).d());
            if (compareTo2 < 0) {
                i8 = i10 - 1;
            } else if (compareTo2 <= 0) {
                return i10;
            } else {
                i9 = i10 + 1;
            }
        }
        return -(i9 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object m(int i8) {
        o();
        Object value = ((am) this.f5349e[i8]).getValue();
        Object[] objArr = this.f5349e;
        System.arraycopy(objArr, i8 + 1, objArr, i8, (this.f5350f - i8) - 1);
        this.f5350f--;
        if (!this.f5351g.isEmpty()) {
            Iterator it = n().entrySet().iterator();
            Object[] objArr2 = this.f5349e;
            int i9 = this.f5350f;
            Map.Entry entry = (Map.Entry) it.next();
            objArr2[i9] = new am(this, (Comparable) entry.getKey(), entry.getValue());
            this.f5350f++;
            it.remove();
        }
        return value;
    }

    private final SortedMap n() {
        o();
        if (this.f5351g.isEmpty() && !(this.f5351g instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.f5351g = treeMap;
            this.f5354j = treeMap.descendingMap();
        }
        return (SortedMap) this.f5351g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void o() {
        if (this.f5352h) {
            throw new UnsupportedOperationException();
        }
    }

    public void a() {
        if (this.f5352h) {
            return;
        }
        this.f5351g = this.f5351g.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.f5351g);
        this.f5354j = this.f5354j.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.f5354j);
        this.f5352h = true;
    }

    public final int c() {
        return this.f5350f;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        o();
        if (this.f5350f != 0) {
            this.f5349e = null;
            this.f5350f = 0;
        }
        if (this.f5351g.isEmpty()) {
            return;
        }
        this.f5351g.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return l(comparable) >= 0 || this.f5351g.containsKey(comparable);
    }

    public final Iterable d() {
        return this.f5351g.isEmpty() ? Collections.emptySet() : this.f5351g.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.f5353i == null) {
            this.f5353i = new em(this, null);
        }
        return this.f5353i;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        Object entrySet;
        Object entrySet2;
        if (this == obj) {
            return true;
        }
        if (obj instanceof gm) {
            gm gmVar = (gm) obj;
            int size = size();
            if (size == gmVar.size()) {
                int i8 = this.f5350f;
                if (i8 == gmVar.f5350f) {
                    for (int i9 = 0; i9 < i8; i9++) {
                        if (!g(i9).equals(gmVar.g(i9))) {
                            return false;
                        }
                    }
                    if (i8 == size) {
                        return true;
                    }
                    entrySet = this.f5351g;
                    entrySet2 = gmVar.f5351g;
                } else {
                    entrySet = entrySet();
                    entrySet2 = gmVar.entrySet();
                }
                return entrySet.equals(entrySet2);
            }
            return false;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: f */
    public final Object put(Comparable comparable, Object obj) {
        o();
        int l8 = l(comparable);
        if (l8 >= 0) {
            return ((am) this.f5349e[l8]).setValue(obj);
        }
        o();
        if (this.f5349e == null) {
            this.f5349e = new Object[16];
        }
        int i8 = -(l8 + 1);
        if (i8 >= 16) {
            return n().put(comparable, obj);
        }
        if (this.f5350f == 16) {
            am amVar = (am) this.f5349e[15];
            this.f5350f = 15;
            n().put(amVar.d(), amVar.getValue());
        }
        Object[] objArr = this.f5349e;
        int length = objArr.length;
        System.arraycopy(objArr, i8, objArr, i8 + 1, (16 - i8) - 1);
        this.f5349e[i8] = new am(this, comparable, obj);
        this.f5350f++;
        return null;
    }

    public final Map.Entry g(int i8) {
        if (i8 < this.f5350f) {
            return (am) this.f5349e[i8];
        }
        throw new ArrayIndexOutOfBoundsException(i8);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int l8 = l(comparable);
        return l8 >= 0 ? ((am) this.f5349e[l8]).getValue() : this.f5351g.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int i8 = this.f5350f;
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            i9 += this.f5349e[i10].hashCode();
        }
        return this.f5351g.size() > 0 ? i9 + this.f5351g.hashCode() : i9;
    }

    public final boolean j() {
        return this.f5352h;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        o();
        Comparable comparable = (Comparable) obj;
        int l8 = l(comparable);
        if (l8 >= 0) {
            return m(l8);
        }
        if (this.f5351g.isEmpty()) {
            return null;
        }
        return this.f5351g.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f5350f + this.f5351g.size();
    }
}
