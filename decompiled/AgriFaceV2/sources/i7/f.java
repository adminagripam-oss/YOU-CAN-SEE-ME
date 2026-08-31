package i7;

import d7.k;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class f extends e {

    /* loaded from: classes.dex */
    public static final class a implements b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Iterator f7555a;

        public a(Iterator it) {
            this.f7555a = it;
        }

        @Override // i7.b
        public Iterator iterator() {
            return this.f7555a;
        }
    }

    public static b a(Iterator it) {
        k.e(it, "<this>");
        return b(new a(it));
    }

    public static final b b(b bVar) {
        k.e(bVar, "<this>");
        return bVar instanceof i7.a ? bVar : new i7.a(bVar);
    }
}
