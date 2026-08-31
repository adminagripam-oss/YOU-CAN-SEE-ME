package s6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class v extends u {
    public static boolean o(Iterable iterable, Object obj) {
        d7.k.e(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).contains(obj) : p(iterable, obj) >= 0;
    }

    public static final int p(Iterable iterable, Object obj) {
        d7.k.e(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(obj);
        }
        int i8 = 0;
        for (Object obj2 : iterable) {
            if (i8 < 0) {
                n.j();
            }
            if (d7.k.a(obj, obj2)) {
                return i8;
            }
            i8++;
        }
        return -1;
    }

    public static final Appendable q(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i8, CharSequence charSequence4, c7.l lVar) {
        d7.k.e(iterable, "<this>");
        d7.k.e(appendable, "buffer");
        d7.k.e(charSequence, "separator");
        d7.k.e(charSequence2, "prefix");
        d7.k.e(charSequence3, "postfix");
        d7.k.e(charSequence4, "truncated");
        appendable.append(charSequence2);
        int i9 = 0;
        for (Object obj : iterable) {
            i9++;
            if (i9 > 1) {
                appendable.append(charSequence);
            }
            if (i8 >= 0 && i9 > i8) {
                break;
            }
            j7.d.a(appendable, obj, lVar);
        }
        if (i8 >= 0 && i9 > i8) {
            appendable.append(charSequence4);
        }
        appendable.append(charSequence3);
        return appendable;
    }

    public static final String r(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i8, CharSequence charSequence4, c7.l lVar) {
        d7.k.e(iterable, "<this>");
        d7.k.e(charSequence, "separator");
        d7.k.e(charSequence2, "prefix");
        d7.k.e(charSequence3, "postfix");
        d7.k.e(charSequence4, "truncated");
        String sb = ((StringBuilder) q(iterable, new StringBuilder(), charSequence, charSequence2, charSequence3, i8, charSequence4, lVar)).toString();
        d7.k.d(sb, "toString(...)");
        return sb;
    }

    public static /* synthetic */ String s(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i8, CharSequence charSequence4, c7.l lVar, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i9 & 2) != 0 ? "" : charSequence2;
        String str2 = (i9 & 4) == 0 ? charSequence3 : "";
        if ((i9 & 8) != 0) {
            i8 = -1;
        }
        int i10 = i8;
        if ((i9 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i9 & 32) != 0) {
            lVar = null;
        }
        return r(iterable, charSequence, str, str2, i10, charSequence5, lVar);
    }

    public static Object t(List list) {
        d7.k.e(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(n.g(list));
    }

    public static final Collection u(Iterable iterable, Collection collection) {
        d7.k.e(iterable, "<this>");
        d7.k.e(collection, "destination");
        for (Object obj : iterable) {
            collection.add(obj);
        }
        return collection;
    }

    public static List v(Iterable iterable) {
        List f8;
        List b8;
        List x7;
        d7.k.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                f8 = n.f();
                return f8;
            } else if (size != 1) {
                x7 = x(collection);
                return x7;
            } else {
                b8 = m.b(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
                return b8;
            }
        }
        return n.i(w(iterable));
    }

    public static final List w(Iterable iterable) {
        List x7;
        d7.k.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            x7 = x((Collection) iterable);
            return x7;
        }
        return (List) u(iterable, new ArrayList());
    }

    public static List x(Collection collection) {
        d7.k.e(collection, "<this>");
        return new ArrayList(collection);
    }

    public static Set y(Iterable iterable) {
        Set b8;
        int a8;
        d7.k.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                b8 = j0.b();
                return b8;
            } else if (size == 1) {
                return i0.a(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
            } else {
                a8 = d0.a(collection.size());
                return (Set) u(iterable, new LinkedHashSet(a8));
            }
        }
        return j0.c((Set) u(iterable, new LinkedHashSet()));
    }

    public static List z(Iterable iterable, Iterable iterable2) {
        int k8;
        int k9;
        d7.k.e(iterable, "<this>");
        d7.k.e(iterable2, "other");
        Iterator it = iterable.iterator();
        Iterator it2 = iterable2.iterator();
        k8 = o.k(iterable, 10);
        k9 = o.k(iterable2, 10);
        ArrayList arrayList = new ArrayList(Math.min(k8, k9));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(r6.o.a(it.next(), it2.next()));
        }
        return arrayList;
    }
}
