package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
abstract class hm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(mj mjVar) {
        String str;
        StringBuilder sb = new StringBuilder(mjVar.j());
        for (int i8 = 0; i8 < mjVar.j(); i8++) {
            int b8 = mjVar.b(i8);
            if (b8 == 34) {
                str = "\\\"";
            } else if (b8 == 39) {
                str = "\\'";
            } else if (b8 != 92) {
                switch (b8) {
                    case 7:
                        str = "\\a";
                        break;
                    case 8:
                        str = "\\b";
                        break;
                    case 9:
                        str = "\\t";
                        break;
                    case 10:
                        str = "\\n";
                        break;
                    case 11:
                        str = "\\v";
                        break;
                    case 12:
                        str = "\\f";
                        break;
                    case 13:
                        str = "\\r";
                        break;
                    default:
                        if (b8 < 32 || b8 > 126) {
                            sb.append('\\');
                            sb.append((char) (((b8 >>> 6) & 3) + 48));
                            sb.append((char) (((b8 >>> 3) & 7) + 48));
                            b8 = (b8 & 7) + 48;
                        }
                        sb.append((char) b8);
                        continue;
                        break;
                }
            } else {
                str = "\\\\";
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
