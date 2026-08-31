package a4;

import java.util.Arrays;
/* loaded from: classes.dex */
public final class xe {

    /* renamed from: a  reason: collision with root package name */
    private final String f1279a;

    /* renamed from: b  reason: collision with root package name */
    private final we f1280b;

    /* renamed from: c  reason: collision with root package name */
    private we f1281c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ xe(String str, ue ueVar) {
        we weVar = new we(null);
        this.f1280b = weVar;
        this.f1281c = weVar;
        this.f1279a = str;
    }

    private final xe e(String str, Object obj) {
        ve veVar = new ve(null);
        this.f1281c.f1233c = veVar;
        this.f1281c = veVar;
        veVar.f1232b = obj;
        veVar.f1231a = str;
        return this;
    }

    public final xe a(String str, float f8) {
        e(str, String.valueOf(f8));
        return this;
    }

    public final xe b(String str, int i8) {
        e(str, String.valueOf(i8));
        return this;
    }

    public final xe c(String str, Object obj) {
        we weVar = new we(null);
        this.f1281c.f1233c = weVar;
        this.f1281c = weVar;
        weVar.f1232b = obj;
        weVar.f1231a = str;
        return this;
    }

    public final xe d(String str, boolean z7) {
        e("trackingEnabled", String.valueOf(z7));
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f1279a);
        sb.append('{');
        we weVar = this.f1280b.f1233c;
        String str = "";
        while (weVar != null) {
            Object obj = weVar.f1232b;
            sb.append(str);
            String str2 = weVar.f1231a;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            }
            weVar = weVar.f1233c;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }
}
