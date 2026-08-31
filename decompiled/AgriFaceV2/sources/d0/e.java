package d0;

import android.util.Size;
import java.util.Comparator;
/* loaded from: classes.dex */
public final class e implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    private boolean f6362a;

    public e() {
        this(false);
    }

    @Override // java.util.Comparator
    /* renamed from: a */
    public int compare(Size size, Size size2) {
        int signum = Long.signum((size.getWidth() * size.getHeight()) - (size2.getWidth() * size2.getHeight()));
        return this.f6362a ? signum * (-1) : signum;
    }

    public e(boolean z7) {
        this.f6362a = z7;
    }
}
