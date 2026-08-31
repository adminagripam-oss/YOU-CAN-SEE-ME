package o1;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    protected int f9820a;

    /* renamed from: b  reason: collision with root package name */
    protected ByteBuffer f9821b;

    /* renamed from: c  reason: collision with root package name */
    private int f9822c;

    /* renamed from: d  reason: collision with root package name */
    private int f9823d;

    /* renamed from: e  reason: collision with root package name */
    d f9824e = d.a();

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(int i8) {
        return i8 + this.f9821b.getInt(i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b(int i8) {
        if (i8 < this.f9823d) {
            return this.f9821b.getShort(this.f9822c + i8);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(int i8, ByteBuffer byteBuffer) {
        short s8;
        this.f9821b = byteBuffer;
        if (byteBuffer != null) {
            this.f9820a = i8;
            int i9 = i8 - byteBuffer.getInt(i8);
            this.f9822c = i9;
            s8 = this.f9821b.getShort(i9);
        } else {
            s8 = 0;
            this.f9820a = 0;
            this.f9822c = 0;
        }
        this.f9823d = s8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d(int i8) {
        int i9 = i8 + this.f9820a;
        return i9 + this.f9821b.getInt(i9) + 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int e(int i8) {
        int i9 = i8 + this.f9820a;
        return this.f9821b.getInt(i9 + this.f9821b.getInt(i9));
    }
}
