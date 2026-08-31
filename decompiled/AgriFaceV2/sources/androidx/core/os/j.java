package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;
/* loaded from: classes.dex */
final class j implements i {

    /* renamed from: a  reason: collision with root package name */
    private final LocaleList f3052a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(Object obj) {
        this.f3052a = (LocaleList) obj;
    }

    @Override // androidx.core.os.i
    public String a() {
        return this.f3052a.toLanguageTags();
    }

    @Override // androidx.core.os.i
    public Object b() {
        return this.f3052a;
    }

    public boolean equals(Object obj) {
        return this.f3052a.equals(((i) obj).b());
    }

    @Override // androidx.core.os.i
    public Locale get(int i8) {
        return this.f3052a.get(i8);
    }

    public int hashCode() {
        return this.f3052a.hashCode();
    }

    @Override // androidx.core.os.i
    public boolean isEmpty() {
        return this.f3052a.isEmpty();
    }

    @Override // androidx.core.os.i
    public int size() {
        return this.f3052a.size();
    }

    public String toString() {
        return this.f3052a.toString();
    }
}
