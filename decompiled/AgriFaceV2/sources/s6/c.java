package s6;

import java.util.AbstractList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class c extends AbstractList implements List {
    public abstract int b();

    public abstract Object f(int i8);

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ Object remove(int i8) {
        return f(i8);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return b();
    }
}
