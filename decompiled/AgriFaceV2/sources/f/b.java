package f;

import android.content.Context;
import android.content.Intent;
import d7.g;
import d7.k;
import f.a;
import g7.f;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import r6.j;
import r6.o;
import s6.d0;
import s6.e0;
import s6.v;
/* loaded from: classes.dex */
public final class b extends f.a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f6624a = new a(null);

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final Intent a(String[] strArr) {
            k.e(strArr, "input");
            Intent putExtra = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr);
            k.d(putExtra, "Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)");
            return putExtra;
        }
    }

    @Override // f.a
    /* renamed from: d */
    public Intent a(Context context, String[] strArr) {
        k.e(context, "context");
        k.e(strArr, "input");
        return f6624a.a(strArr);
    }

    @Override // f.a
    /* renamed from: e */
    public a.C0084a b(Context context, String[] strArr) {
        int a8;
        int a9;
        Map d8;
        k.e(context, "context");
        k.e(strArr, "input");
        boolean z7 = true;
        if (strArr.length == 0) {
            d8 = e0.d();
            return new a.C0084a(d8);
        }
        int length = strArr.length;
        int i8 = 0;
        while (true) {
            if (i8 >= length) {
                break;
            }
            if (!(androidx.core.content.a.a(context, strArr[i8]) == 0)) {
                z7 = false;
                break;
            }
            i8++;
        }
        if (z7) {
            a8 = d0.a(strArr.length);
            a9 = f.a(a8, 16);
            LinkedHashMap linkedHashMap = new LinkedHashMap(a9);
            for (String str : strArr) {
                j a10 = o.a(str, Boolean.TRUE);
                linkedHashMap.put(a10.c(), a10.d());
            }
            return new a.C0084a(linkedHashMap);
        }
        return null;
    }

    @Override // f.a
    /* renamed from: f */
    public Map c(int i8, Intent intent) {
        Map d8;
        List h8;
        List z7;
        Map g8;
        Map d9;
        Map d10;
        if (i8 != -1) {
            d10 = e0.d();
            return d10;
        } else if (intent == null) {
            d9 = e0.d();
            return d9;
        } else {
            String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
            int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
            if (intArrayExtra == null || stringArrayExtra == null) {
                d8 = e0.d();
                return d8;
            }
            ArrayList arrayList = new ArrayList(intArrayExtra.length);
            for (int i9 : intArrayExtra) {
                arrayList.add(Boolean.valueOf(i9 == 0));
            }
            h8 = s6.j.h(stringArrayExtra);
            z7 = v.z(h8, arrayList);
            g8 = e0.g(z7);
            return g8;
        }
    }
}
