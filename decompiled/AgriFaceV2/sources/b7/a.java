package b7;

import d7.d;
import d7.k;
import d7.s;
import h7.b;
/* loaded from: classes.dex */
public abstract class a {
    public static final Class a(b bVar) {
        k.e(bVar, "<this>");
        Class d8 = ((d) bVar).d();
        k.c(d8, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return d8;
    }

    public static final Class b(b bVar) {
        k.e(bVar, "<this>");
        Class d8 = ((d) bVar).d();
        if (!d8.isPrimitive()) {
            k.c(d8, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
            return d8;
        }
        String name = d8.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals("double")) {
                    d8 = Double.class;
                    break;
                }
                break;
            case 104431:
                if (name.equals("int")) {
                    d8 = Integer.class;
                    break;
                }
                break;
            case 3039496:
                if (name.equals("byte")) {
                    d8 = Byte.class;
                    break;
                }
                break;
            case 3052374:
                if (name.equals("char")) {
                    d8 = Character.class;
                    break;
                }
                break;
            case 3327612:
                if (name.equals("long")) {
                    d8 = Long.class;
                    break;
                }
                break;
            case 3625364:
                if (name.equals("void")) {
                    d8 = Void.class;
                    break;
                }
                break;
            case 64711720:
                if (name.equals("boolean")) {
                    d8 = Boolean.class;
                    break;
                }
                break;
            case 97526364:
                if (name.equals("float")) {
                    d8 = Float.class;
                    break;
                }
                break;
            case 109413500:
                if (name.equals("short")) {
                    d8 = Short.class;
                    break;
                }
                break;
        }
        k.c(d8, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
        return d8;
    }

    public static final b c(Class cls) {
        k.e(cls, "<this>");
        return s.b(cls);
    }
}
