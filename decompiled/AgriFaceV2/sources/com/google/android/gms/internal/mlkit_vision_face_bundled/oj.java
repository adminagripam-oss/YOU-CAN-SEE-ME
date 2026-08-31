package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
final class oj extends qj {

    /* renamed from: b  reason: collision with root package name */
    private int f5503b;

    /* renamed from: c  reason: collision with root package name */
    private int f5504c;

    /* renamed from: d  reason: collision with root package name */
    private int f5505d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ oj(byte[] bArr, int i8, int i9, boolean z7, nj njVar) {
        super(null);
        this.f5505d = Integer.MAX_VALUE;
        this.f5503b = 0;
    }

    public final int c(int i8) {
        int i9 = this.f5505d;
        this.f5505d = 0;
        int i10 = this.f5503b + this.f5504c;
        this.f5503b = i10;
        if (i10 > 0) {
            this.f5504c = i10;
            this.f5503b = i10 - i10;
        } else {
            this.f5504c = 0;
        }
        return i9;
    }
}
