package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class vl implements il {

    /* renamed from: a  reason: collision with root package name */
    private final ll f5849a;

    /* renamed from: b  reason: collision with root package name */
    private final String f5850b;

    /* renamed from: c  reason: collision with root package name */
    private final Object[] f5851c;

    /* renamed from: d  reason: collision with root package name */
    private final int f5852d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public vl(ll llVar, String str, Object[] objArr) {
        this.f5849a = llVar;
        this.f5850b = str;
        this.f5851c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.f5852d = charAt;
            return;
        }
        int i8 = charAt & 8191;
        int i9 = 1;
        int i10 = 13;
        while (true) {
            int i11 = i9 + 1;
            char charAt2 = str.charAt(i9);
            if (charAt2 < 55296) {
                this.f5852d = i8 | (charAt2 << i10);
                return;
            }
            i8 |= (charAt2 & 8191) << i10;
            i10 += 13;
            i9 = i11;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.il
    public final int a() {
        int i8 = this.f5852d;
        if ((i8 & 1) != 0) {
            return 1;
        }
        return (i8 & 4) == 4 ? 3 : 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String b() {
        return this.f5850b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object[] c() {
        return this.f5851c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.il
    public final ll zza() {
        return this.f5849a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.il
    public final boolean zzb() {
        return (this.f5852d & 2) == 2;
    }
}
