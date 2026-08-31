package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class nl {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f5494a;

    static {
        char[] cArr = new char[80];
        f5494a = cArr;
        Arrays.fill(cArr, ' ');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(ll llVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        d(llVar, sb, 0);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(StringBuilder sb, int i8, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                b(sb, i8, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                b(sb, i8, str, entry);
            }
        } else {
            sb.append('\n');
            c(i8, sb);
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toLowerCase(str.charAt(0)));
                for (int i9 = 1; i9 < str.length(); i9++) {
                    char charAt = str.charAt(i9);
                    if (Character.isUpperCase(charAt)) {
                        sb2.append("_");
                    }
                    sb2.append(Character.toLowerCase(charAt));
                }
                str = sb2.toString();
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(hm.a(new lj(((String) obj).getBytes(uk.f5673a))));
                sb.append('\"');
            } else if (obj instanceof mj) {
                sb.append(": \"");
                sb.append(hm.a((mj) obj));
                sb.append('\"');
            } else if (obj instanceof ok) {
                sb.append(" {");
                d((ok) obj, sb, i8 + 2);
                sb.append("\n");
                c(i8, sb);
                sb.append("}");
            } else if (!(obj instanceof Map.Entry)) {
                sb.append(": ");
                sb.append(obj);
            } else {
                int i10 = i8 + 2;
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                b(sb, i10, "key", entry2.getKey());
                b(sb, i10, "value", entry2.getValue());
                sb.append("\n");
                c(i8, sb);
                sb.append("}");
            }
        }
    }

    private static void c(int i8, StringBuilder sb) {
        while (i8 > 0) {
            int i9 = 80;
            if (i8 <= 80) {
                i9 = i8;
            }
            sb.append(f5494a, 0, i9);
            i8 -= i9;
        }
    }

    private static void d(ll llVar, StringBuilder sb, int i8) {
        int i9;
        Object obj;
        Method method;
        String substring;
        Object j8;
        Method method2;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = llVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i10 = 0;
        while (true) {
            i9 = 3;
            if (i10 >= length) {
                break;
            }
            Method method3 = declaredMethods[i10];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith("get")) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i10++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring2 = ((String) entry.getKey()).substring(i9);
            if (substring2.endsWith("List") && !substring2.endsWith("OrBuilderList") && !substring2.equals("List") && (method2 = (Method) entry.getValue()) != null && method2.getReturnType().equals(List.class)) {
                substring = substring2.substring(0, substring2.length() - 4);
                j8 = ok.j(method2, llVar, new Object[0]);
            } else if (!substring2.endsWith("Map") || substring2.equals("Map") || (method = (Method) entry.getValue()) == null || !method.getReturnType().equals(Map.class) || method.isAnnotationPresent(Deprecated.class) || !Modifier.isPublic(method.getModifiers())) {
                if (hashSet.contains("set".concat(substring2)) && (!substring2.endsWith("Bytes") || !treeMap.containsKey("get".concat(String.valueOf(substring2.substring(0, substring2.length() - 5)))))) {
                    Method method4 = (Method) entry.getValue();
                    Method method5 = (Method) hashMap.get("has".concat(substring2));
                    if (method4 != null) {
                        Object j9 = ok.j(method4, llVar, new Object[0]);
                        if (method5 != null) {
                            if (!((Boolean) ok.j(method5, llVar, new Object[0])).booleanValue()) {
                            }
                            b(sb, i8, substring2, j9);
                        } else if (j9 instanceof Boolean) {
                            if (!((Boolean) j9).booleanValue()) {
                            }
                            b(sb, i8, substring2, j9);
                        } else if (j9 instanceof Integer) {
                            if (((Integer) j9).intValue() == 0) {
                            }
                            b(sb, i8, substring2, j9);
                        } else if (j9 instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) j9).floatValue()) == 0) {
                            }
                            b(sb, i8, substring2, j9);
                        } else if (j9 instanceof Double) {
                            if (Double.doubleToRawLongBits(((Double) j9).doubleValue()) == 0) {
                            }
                            b(sb, i8, substring2, j9);
                        } else {
                            if (j9 instanceof String) {
                                obj = "";
                            } else if (j9 instanceof mj) {
                                obj = mj.f5439f;
                            } else if (j9 instanceof ll) {
                                if (j9 == ((ll) j9).c()) {
                                }
                                b(sb, i8, substring2, j9);
                            } else {
                                if ((j9 instanceof Enum) && ((Enum) j9).ordinal() == 0) {
                                }
                                b(sb, i8, substring2, j9);
                            }
                            if (j9.equals(obj)) {
                            }
                            b(sb, i8, substring2, j9);
                        }
                    }
                }
                i9 = 3;
            } else {
                substring = substring2.substring(0, substring2.length() - 3);
                j8 = ok.j(method, llVar, new Object[0]);
            }
            b(sb, i8, substring, j8);
            i9 = 3;
        }
        if (llVar instanceof lk) {
            Iterator f8 = ((lk) llVar).zzb.f();
            while (f8.hasNext()) {
                Map.Entry entry2 = (Map.Entry) f8.next();
                mk mkVar = (mk) entry2.getKey();
                b(sb, i8, "[202056002]", entry2.getValue());
            }
        }
        km kmVar = ((ok) llVar).zzc;
        if (kmVar != null) {
            kmVar.i(sb, i8);
        }
    }
}
