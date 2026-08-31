package y5;
/* loaded from: classes.dex */
public abstract class a extends b {
    @Override // y5.f
    public void error(String str, String str2, Object obj) {
        m().error(str, str2, obj);
    }

    protected abstract f m();

    @Override // y5.f
    public void success(Object obj) {
        m().success(obj);
    }
}
