package a4;
/* loaded from: classes.dex */
abstract class h0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Object obj) {
        return (int) (Integer.rotateLeft((int) ((obj == null ? 0 : obj.hashCode()) * (-862048943)), 15) * 461845907);
    }
}
