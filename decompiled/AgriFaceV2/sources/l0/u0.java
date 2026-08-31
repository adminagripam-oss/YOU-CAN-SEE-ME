package l0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class u0 {
    public static String a(int i8) {
        ArrayList arrayList = new ArrayList();
        if ((i8 & 4) != 0) {
            arrayList.add("IMAGE_CAPTURE");
        }
        if ((i8 & 1) != 0) {
            arrayList.add("PREVIEW");
        }
        if ((i8 & 2) != 0) {
            arrayList.add("VIDEO_CAPTURE");
        }
        return String.join("|", arrayList);
    }

    public static boolean b(int i8, int i9) {
        return (i8 & i9) == i9;
    }
}
