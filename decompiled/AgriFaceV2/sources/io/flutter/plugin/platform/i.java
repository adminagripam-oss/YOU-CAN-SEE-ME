package io.flutter.plugin.platform;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.core.view.a2;
import h6.o;
import io.flutter.plugin.platform.i;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private final Activity f7968a;

    /* renamed from: b  reason: collision with root package name */
    private final h6.o f7969b;

    /* renamed from: c  reason: collision with root package name */
    private final d f7970c;

    /* renamed from: d  reason: collision with root package name */
    private o.j f7971d;

    /* renamed from: e  reason: collision with root package name */
    private int f7972e;

    /* renamed from: f  reason: collision with root package name */
    final o.h f7973f;

    /* loaded from: classes.dex */
    class a implements o.h {
        a() {
        }

        @Override // h6.o.h
        public void a() {
            i.this.t();
        }

        @Override // h6.o.h
        public void e(boolean z7) {
            i.this.w(z7);
        }

        @Override // h6.o.h
        public CharSequence f(o.e eVar) {
            return i.this.r(eVar);
        }

        @Override // h6.o.h
        public void g(List list) {
            i.this.A(list);
        }

        @Override // h6.o.h
        public void h(o.c cVar) {
            i.this.x(cVar);
        }

        @Override // h6.o.h
        public void i() {
            i.this.y();
        }

        @Override // h6.o.h
        public void j(o.g gVar) {
            i.this.F(gVar);
        }

        @Override // h6.o.h
        public void k() {
            i.this.u();
        }

        @Override // h6.o.h
        public void l(String str) {
            i.this.v(str);
        }

        @Override // h6.o.h
        public void m(o.j jVar) {
            i.this.C(jVar);
        }

        @Override // h6.o.h
        public void n(int i8) {
            i.this.B(i8);
        }

        @Override // h6.o.h
        public void o(o.k kVar) {
            i.this.z(kVar);
        }

        @Override // h6.o.h
        public void p(String str) {
            i.this.D(str);
        }

        @Override // h6.o.h
        public void q(o.i iVar) {
            i.this.s(iVar);
        }

        @Override // h6.o.h
        public boolean r() {
            return i.this.p();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements View.OnSystemUiVisibilityChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View f7975a;

        b(View view) {
            this.f7975a = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(int i8) {
            h6.o oVar;
            boolean z7;
            if ((i8 & 4) == 0) {
                oVar = i.this.f7969b;
                z7 = true;
            } else {
                oVar = i.this.f7969b;
                z7 = false;
            }
            oVar.m(z7);
        }

        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(final int i8) {
            this.f7975a.post(new Runnable() { // from class: io.flutter.plugin.platform.j
                @Override // java.lang.Runnable
                public final void run() {
                    i.b.this.b(i8);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7977a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f7978b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f7979c;

        static {
            int[] iArr = new int[o.d.values().length];
            f7979c = iArr;
            try {
                iArr[o.d.DARK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7979c[o.d.LIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[o.l.values().length];
            f7978b = iArr2;
            try {
                iArr2[o.l.TOP_OVERLAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7978b[o.l.BOTTOM_OVERLAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr3 = new int[o.g.values().length];
            f7977a = iArr3;
            try {
                iArr3[o.g.STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7977a[o.g.LIGHT_IMPACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7977a[o.g.MEDIUM_IMPACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7977a[o.g.HEAVY_IMPACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7977a[o.g.SELECTION_CLICK.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        boolean a();

        void e(boolean z7);
    }

    public i(Activity activity, h6.o oVar, d dVar) {
        a aVar = new a();
        this.f7973f = aVar;
        this.f7968a = activity;
        this.f7969b = oVar;
        oVar.l(aVar);
        this.f7970c = dVar;
        this.f7972e = 1280;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(List list) {
        int i8 = list.size() == 0 ? 5894 : 1798;
        for (int i9 = 0; i9 < list.size(); i9++) {
            int i10 = c.f7978b[((o.l) list.get(i9)).ordinal()];
            if (i10 == 1) {
                i8 &= -5;
            } else if (i10 == 2) {
                i8 = i8 & (-513) & (-3);
            }
        }
        this.f7972e = i8;
        E();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B(int i8) {
        this.f7968a.setRequestedOrientation(i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(o.j jVar) {
        Window window = this.f7968a.getWindow();
        a2 a2Var = new a2(window, window.getDecorView());
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 30) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(201326592);
        }
        o.d dVar = jVar.f7345b;
        if (dVar != null) {
            int i9 = c.f7979c[dVar.ordinal()];
            if (i9 == 1) {
                a2Var.b(true);
            } else if (i9 == 2) {
                a2Var.b(false);
            }
        }
        Integer num = jVar.f7344a;
        if (num != null) {
            window.setStatusBarColor(num.intValue());
        }
        Boolean bool = jVar.f7346c;
        if (bool != null && i8 >= 29) {
            window.setStatusBarContrastEnforced(bool.booleanValue());
        }
        o.d dVar2 = jVar.f7348e;
        if (dVar2 != null) {
            int i10 = c.f7979c[dVar2.ordinal()];
            if (i10 == 1) {
                a2Var.a(true);
            } else if (i10 == 2) {
                a2Var.a(false);
            }
        }
        Integer num2 = jVar.f7347d;
        if (num2 != null) {
            window.setNavigationBarColor(num2.intValue());
        }
        Integer num3 = jVar.f7349f;
        if (num3 != null && i8 >= 28) {
            window.setNavigationBarDividerColor(num3.intValue());
        }
        Boolean bool2 = jVar.f7350g;
        if (bool2 != null && i8 >= 29) {
            window.setNavigationBarContrastEnforced(bool2.booleanValue());
        }
        this.f7971d = jVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        this.f7968a.startActivity(Intent.createChooser(intent, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p() {
        ClipDescription primaryClipDescription;
        ClipboardManager clipboardManager = (ClipboardManager) this.f7968a.getSystemService("clipboard");
        if (clipboardManager.hasPrimaryClip() && (primaryClipDescription = clipboardManager.getPrimaryClipDescription()) != null) {
            return primaryClipDescription.hasMimeType("text/*");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CharSequence r(o.e eVar) {
        ClipboardManager clipboardManager = (ClipboardManager) this.f7968a.getSystemService("clipboard");
        CharSequence charSequence = null;
        if (!clipboardManager.hasPrimaryClip()) {
            return null;
        }
        try {
            try {
                ClipData primaryClip = clipboardManager.getPrimaryClip();
                if (primaryClip == null) {
                    return null;
                }
                if (eVar != null && eVar != o.e.PLAIN_TEXT) {
                    return null;
                }
                ClipData.Item itemAt = primaryClip.getItemAt(0);
                CharSequence text = itemAt.getText();
                if (text == null) {
                    try {
                        Uri uri = itemAt.getUri();
                        if (uri == null) {
                            z5.b.g("PlatformPlugin", "Clipboard item contained no textual content nor a URI to retrieve it from.");
                            return null;
                        }
                        String scheme = uri.getScheme();
                        if (!scheme.equals("content")) {
                            z5.b.g("PlatformPlugin", "Clipboard item contains a Uri with scheme '" + scheme + "'that is unhandled.");
                            return null;
                        }
                        AssetFileDescriptor openTypedAssetFileDescriptor = this.f7968a.getContentResolver().openTypedAssetFileDescriptor(uri, "text/*", null);
                        text = itemAt.coerceToText(this.f7968a);
                        if (openTypedAssetFileDescriptor != null) {
                            openTypedAssetFileDescriptor.close();
                        }
                    } catch (IOException e8) {
                        e = e8;
                        charSequence = text;
                        z5.b.h("PlatformPlugin", "Failed to close AssetFileDescriptor while trying to read text from URI.", e);
                        return charSequence;
                    }
                }
                return text;
            } catch (IOException e9) {
                e = e9;
            }
        } catch (FileNotFoundException unused) {
            z5.b.g("PlatformPlugin", "Clipboard text was unable to be received from content URI.");
            return null;
        } catch (SecurityException e10) {
            z5.b.h("PlatformPlugin", "Attempted to get clipboard data that requires additional permission(s).\nSee the exception details for which permission(s) are required, and consider adding them to your Android Manifest as described in:\nhttps://developer.android.com/guide/topics/permissions/overview", e10);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(o.i iVar) {
        if (iVar == o.i.CLICK) {
            this.f7968a.getWindow().getDecorView().playSoundEffect(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        d dVar = this.f7970c;
        if (dVar == null || !dVar.a()) {
            Activity activity = this.f7968a;
            if (activity instanceof androidx.activity.s) {
                ((androidx.activity.s) activity).getOnBackPressedDispatcher().k();
            } else {
                activity.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        E();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(String str) {
        ((ClipboardManager) this.f7968a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(boolean z7) {
        d dVar = this.f7970c;
        if (dVar != null) {
            dVar.e(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(o.c cVar) {
        if (Build.VERSION.SDK_INT < 28) {
            this.f7968a.setTaskDescription(new ActivityManager.TaskDescription(cVar.f7319b, (Bitmap) null, cVar.f7318a));
            return;
        }
        h.a();
        this.f7968a.setTaskDescription(g.a(cVar.f7319b, 0, cVar.f7318a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        View decorView = this.f7968a.getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new b(decorView));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(o.k kVar) {
        int i8;
        if (kVar == o.k.LEAN_BACK) {
            i8 = 1798;
        } else if (kVar == o.k.IMMERSIVE) {
            i8 = 3846;
        } else if (kVar == o.k.IMMERSIVE_STICKY) {
            i8 = 5894;
        } else if (kVar != o.k.EDGE_TO_EDGE || Build.VERSION.SDK_INT < 29) {
            return;
        } else {
            i8 = 1792;
        }
        this.f7972e = i8;
        E();
    }

    public void E() {
        this.f7968a.getWindow().getDecorView().setSystemUiVisibility(this.f7972e);
        o.j jVar = this.f7971d;
        if (jVar != null) {
            C(jVar);
        }
    }

    void F(o.g gVar) {
        int i8;
        View decorView = this.f7968a.getWindow().getDecorView();
        int i9 = c.f7977a[gVar.ordinal()];
        int i10 = 1;
        if (i9 != 1) {
            if (i9 != 2) {
                i10 = 3;
                if (i9 != 3) {
                    i10 = 4;
                    if (i9 == 4) {
                        i8 = 6;
                    } else if (i9 != 5) {
                        return;
                    }
                }
            }
            decorView.performHapticFeedback(i10);
            return;
        }
        i8 = 0;
        decorView.performHapticFeedback(i8);
    }

    public void q() {
        this.f7969b.l(null);
    }
}
