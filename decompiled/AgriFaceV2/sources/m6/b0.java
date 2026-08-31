package m6;

import android.app.Activity;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b0 {

    /* renamed from: a  reason: collision with root package name */
    boolean f9230a = false;

    /* loaded from: classes.dex */
    static final class a implements i6.m {

        /* renamed from: a  reason: collision with root package name */
        boolean f9231a = false;

        /* renamed from: b  reason: collision with root package name */
        final c f9232b;

        a(c cVar) {
            this.f9232b = cVar;
        }

        @Override // i6.m
        public boolean a(int i8, String[] strArr, int[] iArr) {
            c cVar;
            String str;
            String str2;
            if (this.f9231a || i8 != 9796) {
                return false;
            }
            this.f9231a = true;
            if (iArr.length == 0 || iArr[0] != 0) {
                cVar = this.f9232b;
                str = "CameraAccessDenied";
                str2 = "Camera access permission was denied.";
            } else if (iArr.length <= 1 || iArr[1] == 0) {
                this.f9232b.a(null, null);
                return true;
            } else {
                cVar = this.f9232b;
                str = "AudioAccessDenied";
                str2 = "Audio access permission was denied.";
            }
            cVar.a(str, str2);
            return true;
        }
    }

    /* loaded from: classes.dex */
    interface b {
        void a(i6.m mVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface c {
        void a(String str, String str2);
    }

    private boolean b(Activity activity) {
        return androidx.core.content.a.a(activity, "android.permission.RECORD_AUDIO") == 0;
    }

    private boolean c(Activity activity) {
        return androidx.core.content.a.a(activity, "android.permission.CAMERA") == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(c cVar, String str, String str2) {
        this.f9230a = false;
        cVar.a(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(Activity activity, b bVar, boolean z7, final c cVar) {
        if (this.f9230a) {
            cVar.a("CameraPermissionsRequestOngoing", "Another request is ongoing and multiple requests cannot be handled at once.");
        } else if (c(activity) && (!z7 || b(activity))) {
            cVar.a(null, null);
        } else {
            bVar.a(new a(new c() { // from class: m6.a0
                @Override // m6.b0.c
                public final void a(String str, String str2) {
                    b0.this.d(cVar, str, str2);
                }
            }));
            this.f9230a = true;
            androidx.core.app.b.s(activity, z7 ? new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"} : new String[]{"android.permission.CAMERA"}, 9796);
        }
    }
}
