package w5;

import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public abstract class f0 {
    public static List a(Cursor cursor, int i8) {
        String str;
        ArrayList arrayList = new ArrayList(i8);
        for (int i9 = 0; i9 < i8; i9++) {
            Object b8 = b(cursor, i9);
            if (x5.a.f12724c) {
                if (b8 == null) {
                    str = null;
                } else if (b8.getClass().isArray()) {
                    str = "array(" + b8.getClass().getComponentType().getName() + ")";
                } else {
                    str = b8.getClass().getName();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("column ");
                sb.append(i9);
                sb.append(" ");
                sb.append(cursor.getType(i9));
                sb.append(": ");
                sb.append(b8);
                sb.append(str == null ? "" : " (" + str + ")");
                Log.d("Sqflite", sb.toString());
            }
            arrayList.add(b8);
        }
        return arrayList;
    }

    public static Object b(Cursor cursor, int i8) {
        int type = cursor.getType(i8);
        if (type != 1) {
            if (type != 2) {
                if (type != 3) {
                    if (type != 4) {
                        return null;
                    }
                    return cursor.getBlob(i8);
                }
                return cursor.getString(i8);
            }
            return Double.valueOf(cursor.getDouble(i8));
        }
        return Long.valueOf(cursor.getLong(i8));
    }

    static Locale c(String str) {
        return Locale.forLanguageTag(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale d(String str) {
        return c(str);
    }
}
