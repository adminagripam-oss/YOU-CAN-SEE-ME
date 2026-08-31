package g7;

import java.util.NoSuchElementException;
import s6.a0;
/* loaded from: classes.dex */
public final class b extends a0 {

    /* renamed from: e  reason: collision with root package name */
    private final int f7130e;

    /* renamed from: f  reason: collision with root package name */
    private final int f7131f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f7132g;

    /* renamed from: h  reason: collision with root package name */
    private int f7133h;

    public b(int i8, int i9, int i10) {
        this.f7130e = i10;
        this.f7131f = i9;
        boolean z7 = true;
        if (i10 <= 0 ? i8 < i9 : i8 > i9) {
            z7 = false;
        }
        this.f7132g = z7;
        this.f7133h = z7 ? i8 : i9;
    }

    @Override // s6.a0
    public int b() {
        int i8 = this.f7133h;
        if (i8 != this.f7131f) {
            this.f7133h = this.f7130e + i8;
        } else if (!this.f7132g) {
            throw new NoSuchElementException();
        } else {
            this.f7132g = false;
        }
        return i8;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f7132g;
    }
}
