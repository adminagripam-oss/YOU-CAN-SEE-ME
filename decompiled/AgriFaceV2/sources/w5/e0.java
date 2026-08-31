package w5;

import android.database.sqlite.SQLiteProgram;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class e0 {

    /* renamed from: a  reason: collision with root package name */
    private final String f12515a;

    /* renamed from: b  reason: collision with root package name */
    private final List f12516b;

    public e0(String str, List list) {
        this.f12515a = str;
        this.f12516b = list == null ? new ArrayList() : list;
    }

    private Object[] e(List list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Object obj : list) {
                arrayList.add(f(obj));
            }
        }
        return arrayList.toArray(new Object[0]);
    }

    private static Object f(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            byte[] bArr = new byte[list.size()];
            for (int i8 = 0; i8 < list.size(); i8++) {
                bArr[i8] = (byte) ((Integer) list.get(i8)).intValue();
            }
            return bArr;
        }
        return obj;
    }

    public void a(SQLiteProgram sQLiteProgram) {
        long j8;
        List list = this.f12516b;
        if (list != null) {
            int size = list.size();
            int i8 = 0;
            while (i8 < size) {
                Object f8 = f(this.f12516b.get(i8));
                int i9 = i8 + 1;
                if (f8 == null) {
                    sQLiteProgram.bindNull(i9);
                } else if (f8 instanceof byte[]) {
                    sQLiteProgram.bindBlob(i9, (byte[]) f8);
                } else if (f8 instanceof Double) {
                    sQLiteProgram.bindDouble(i9, ((Double) f8).doubleValue());
                } else {
                    if (f8 instanceof Integer) {
                        j8 = ((Integer) f8).intValue();
                    } else if (f8 instanceof Long) {
                        j8 = ((Long) f8).longValue();
                    } else if (f8 instanceof String) {
                        sQLiteProgram.bindString(i9, (String) f8);
                    } else if (!(f8 instanceof Boolean)) {
                        throw new IllegalArgumentException("Could not bind " + f8 + " from index " + i8 + ": Supported types are null, byte[], double, long, boolean and String");
                    } else {
                        j8 = ((Boolean) f8).booleanValue() ? 1L : 0L;
                    }
                    sQLiteProgram.bindLong(i9, j8);
                }
                i8 = i9;
            }
        }
    }

    public List b() {
        return this.f12516b;
    }

    public String c() {
        return this.f12515a;
    }

    public Object[] d() {
        return e(this.f12516b);
    }

    public boolean equals(Object obj) {
        if (obj instanceof e0) {
            e0 e0Var = (e0) obj;
            String str = this.f12515a;
            if (str != null) {
                if (!str.equals(e0Var.f12515a)) {
                    return false;
                }
            } else if (e0Var.f12515a != null) {
                return false;
            }
            if (this.f12516b.size() != e0Var.f12516b.size()) {
                return false;
            }
            for (int i8 = 0; i8 < this.f12516b.size(); i8++) {
                if ((this.f12516b.get(i8) instanceof byte[]) && (e0Var.f12516b.get(i8) instanceof byte[])) {
                    if (!Arrays.equals((byte[]) this.f12516b.get(i8), (byte[]) e0Var.f12516b.get(i8))) {
                        return false;
                    }
                } else if (!this.f12516b.get(i8).equals(e0Var.f12516b.get(i8))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.f12515a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f12515a);
        List list = this.f12516b;
        if (list == null || list.isEmpty()) {
            str = "";
        } else {
            str = " " + this.f12516b;
        }
        sb.append(str);
        return sb.toString();
    }
}
