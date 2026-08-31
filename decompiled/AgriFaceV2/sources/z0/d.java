package z0;

import java.io.File;
/* loaded from: classes.dex */
public abstract class d {
    public static boolean a(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        return parentFile.exists() ? parentFile.isDirectory() : parentFile.mkdirs();
    }
}
