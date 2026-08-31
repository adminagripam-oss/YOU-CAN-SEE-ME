package k3;

import android.accounts.Account;
import android.view.View;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    private final Account f8366a;

    /* renamed from: b  reason: collision with root package name */
    private final Set f8367b;

    /* renamed from: c  reason: collision with root package name */
    private final Set f8368c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f8369d;

    /* renamed from: e  reason: collision with root package name */
    private final int f8370e;

    /* renamed from: f  reason: collision with root package name */
    private final View f8371f;

    /* renamed from: g  reason: collision with root package name */
    private final String f8372g;

    /* renamed from: h  reason: collision with root package name */
    private final String f8373h;

    /* renamed from: i  reason: collision with root package name */
    private final d4.a f8374i;

    /* renamed from: j  reason: collision with root package name */
    private Integer f8375j;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private Account f8376a;

        /* renamed from: b  reason: collision with root package name */
        private b1.b f8377b;

        /* renamed from: c  reason: collision with root package name */
        private String f8378c;

        /* renamed from: d  reason: collision with root package name */
        private String f8379d;

        /* renamed from: e  reason: collision with root package name */
        private final d4.a f8380e = d4.a.f6426k;

        public e a() {
            return new e(this.f8376a, this.f8377b, null, 0, null, this.f8378c, this.f8379d, this.f8380e, false);
        }

        public a b(String str) {
            this.f8378c = str;
            return this;
        }

        public final a c(Collection collection) {
            if (this.f8377b == null) {
                this.f8377b = new b1.b();
            }
            this.f8377b.addAll(collection);
            return this;
        }

        public final a d(Account account) {
            this.f8376a = account;
            return this;
        }

        public final a e(String str) {
            this.f8379d = str;
            return this;
        }
    }

    public e(Account account, Set set, Map map, int i8, View view, String str, String str2, d4.a aVar, boolean z7) {
        this.f8366a = account;
        Set emptySet = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.f8367b = emptySet;
        map = map == null ? Collections.emptyMap() : map;
        this.f8369d = map;
        this.f8371f = view;
        this.f8370e = i8;
        this.f8372g = str;
        this.f8373h = str2;
        this.f8374i = aVar == null ? d4.a.f6426k : aVar;
        HashSet hashSet = new HashSet(emptySet);
        Iterator it = map.values().iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        } else {
            this.f8368c = Collections.unmodifiableSet(hashSet);
        }
    }

    public Account a() {
        return this.f8366a;
    }

    public Account b() {
        Account account = this.f8366a;
        return account != null ? account : new Account("<<default account>>", "com.google");
    }

    public Set c() {
        return this.f8368c;
    }

    public String d() {
        return this.f8372g;
    }

    public Set e() {
        return this.f8367b;
    }

    public final d4.a f() {
        return this.f8374i;
    }

    public final Integer g() {
        return this.f8375j;
    }

    public final String h() {
        return this.f8373h;
    }

    public final void i(Integer num) {
        this.f8375j = num;
    }
}
