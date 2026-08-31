package e7;

import java.util.Random;
/* loaded from: classes.dex */
public abstract class a extends c {
    @Override // e7.c
    public int b() {
        return d().nextInt();
    }

    @Override // e7.c
    public int c(int i8) {
        return d().nextInt(i8);
    }

    public abstract Random d();
}
