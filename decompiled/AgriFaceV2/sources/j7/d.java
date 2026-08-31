package j7;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class d {
    public static void a(Appendable appendable, Object obj, c7.l lVar) {
        CharSequence valueOf;
        d7.k.e(appendable, "<this>");
        if (lVar != null) {
            obj = lVar.g(obj);
        } else {
            if (!(obj == null ? true : obj instanceof CharSequence)) {
                if (obj instanceof Character) {
                    appendable.append(((Character) obj).charValue());
                    return;
                }
                valueOf = String.valueOf(obj);
                appendable.append(valueOf);
            }
        }
        valueOf = (CharSequence) obj;
        appendable.append(valueOf);
    }
}
