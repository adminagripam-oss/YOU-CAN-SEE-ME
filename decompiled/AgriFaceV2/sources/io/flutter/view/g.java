package io.flutter.view;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.LocaleSpan;
import android.text.style.TtsSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import h6.a;
import io.flutter.plugin.platform.q;
import io.flutter.view.g;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class g extends AccessibilityNodeProvider {

    /* renamed from: a  reason: collision with root package name */
    private final View f8041a;

    /* renamed from: b  reason: collision with root package name */
    private final h6.a f8042b;

    /* renamed from: c  reason: collision with root package name */
    private final AccessibilityManager f8043c;

    /* renamed from: d  reason: collision with root package name */
    private final AccessibilityViewEmbedder f8044d;

    /* renamed from: e  reason: collision with root package name */
    private final q f8045e;

    /* renamed from: f  reason: collision with root package name */
    private final ContentResolver f8046f;

    /* renamed from: g  reason: collision with root package name */
    private final Map f8047g;

    /* renamed from: h  reason: collision with root package name */
    private final Map f8048h;

    /* renamed from: i  reason: collision with root package name */
    private l f8049i;

    /* renamed from: j  reason: collision with root package name */
    private Integer f8050j;

    /* renamed from: k  reason: collision with root package name */
    private Integer f8051k;

    /* renamed from: l  reason: collision with root package name */
    private int f8052l;

    /* renamed from: m  reason: collision with root package name */
    private l f8053m;

    /* renamed from: n  reason: collision with root package name */
    private l f8054n;

    /* renamed from: o  reason: collision with root package name */
    private l f8055o;

    /* renamed from: p  reason: collision with root package name */
    private final List f8056p;

    /* renamed from: q  reason: collision with root package name */
    private int f8057q;

    /* renamed from: r  reason: collision with root package name */
    private Integer f8058r;

    /* renamed from: s  reason: collision with root package name */
    private k f8059s;

    /* renamed from: t  reason: collision with root package name */
    private boolean f8060t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f8061u;

    /* renamed from: v  reason: collision with root package name */
    private final a.b f8062v;

    /* renamed from: w  reason: collision with root package name */
    private final AccessibilityManager.AccessibilityStateChangeListener f8063w;

    /* renamed from: x  reason: collision with root package name */
    private final AccessibilityManager.TouchExplorationStateChangeListener f8064x;

    /* renamed from: y  reason: collision with root package name */
    private final ContentObserver f8065y;

    /* renamed from: z  reason: collision with root package name */
    private static final int f8040z = ((EnumC0108g.SCROLL_RIGHT.f8102e | EnumC0108g.SCROLL_LEFT.f8102e) | EnumC0108g.SCROLL_UP.f8102e) | EnumC0108g.SCROLL_DOWN.f8102e;
    private static final int A = ((((((((((i.HAS_CHECKED_STATE.f8129e | i.IS_CHECKED.f8129e) | i.IS_SELECTED.f8129e) | i.IS_TEXT_FIELD.f8129e) | i.IS_FOCUSED.f8129e) | i.HAS_ENABLED_STATE.f8129e) | i.IS_ENABLED.f8129e) | i.IS_IN_MUTUALLY_EXCLUSIVE_GROUP.f8129e) | i.HAS_TOGGLED_STATE.f8129e) | i.IS_TOGGLED.f8129e) | i.IS_FOCUSABLE.f8129e) | i.IS_SLIDER.f8129e;
    private static int B = 267386881;
    static int C = (EnumC0108g.DID_GAIN_ACCESSIBILITY_FOCUS.f8102e & EnumC0108g.DID_LOSE_ACCESSIBILITY_FOCUS.f8102e) & EnumC0108g.SHOW_ON_SCREEN.f8102e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements a.b {
        a() {
        }

        @Override // h6.a.b
        public void a(String str) {
            g.this.f8041a.announceForAccessibility(str);
        }

        @Override // io.flutter.embedding.engine.FlutterJNI.a
        public void b(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            for (ByteBuffer byteBuffer2 : byteBufferArr) {
                byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
            }
            g.this.d0(byteBuffer, strArr, byteBufferArr);
        }

        @Override // io.flutter.embedding.engine.FlutterJNI.a
        public void c(ByteBuffer byteBuffer, String[] strArr) {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            g.this.c0(byteBuffer, strArr);
        }

        @Override // h6.a.b
        public void d(String str) {
            if (Build.VERSION.SDK_INT >= 28) {
                return;
            }
            AccessibilityEvent I = g.this.I(0, 32);
            I.getText().add(str);
            g.this.U(I);
        }

        @Override // h6.a.b
        public void e(int i8) {
            g.this.T(i8, 8);
        }

        @Override // h6.a.b
        public void f(int i8) {
            g.this.T(i8, 2);
        }

        @Override // h6.a.b
        public void g(int i8) {
            g.this.T(i8, 1);
        }
    }

    /* loaded from: classes.dex */
    class b implements AccessibilityManager.AccessibilityStateChangeListener {
        b() {
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z7) {
            if (g.this.f8061u) {
                return;
            }
            g gVar = g.this;
            if (z7) {
                gVar.f8042b.g(g.this.f8062v);
                g.this.f8042b.e();
            } else {
                gVar.Y(false);
                g.this.f8042b.g(null);
                g.this.f8042b.d();
            }
            if (g.this.f8059s != null) {
                g.this.f8059s.a(z7, g.this.f8043c.isTouchExplorationEnabled());
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends ContentObserver {
        c(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z7) {
            onChange(z7, null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z7, Uri uri) {
            if (g.this.f8061u) {
                return;
            }
            if (Settings.Global.getFloat(g.this.f8046f, "transition_animation_scale", 1.0f) == 0.0f) {
                g.f(g.this, f.DISABLE_ANIMATIONS.f8080e);
            } else {
                g.e(g.this, ~f.DISABLE_ANIMATIONS.f8080e);
            }
            g.this.V();
        }
    }

    /* loaded from: classes.dex */
    class d implements AccessibilityManager.TouchExplorationStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AccessibilityManager f8069a;

        d(AccessibilityManager accessibilityManager) {
            this.f8069a = accessibilityManager;
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z7) {
            if (g.this.f8061u) {
                return;
            }
            if (!z7) {
                g.this.Y(false);
                g.this.N();
            }
            if (g.this.f8059s != null) {
                g.this.f8059s.a(this.f8069a.isEnabled(), z7);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class e {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8071a;

        static {
            int[] iArr = new int[o.values().length];
            f8071a = iArr;
            try {
                iArr[o.SPELLOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8071a[o.LOCALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum f {
        ACCESSIBLE_NAVIGATION(1),
        INVERT_COLORS(2),
        DISABLE_ANIMATIONS(4),
        BOLD_TEXT(8),
        REDUCE_MOTION(16),
        HIGH_CONTRAST(32),
        ON_OFF_SWITCH_LABELS(64);
        

        /* renamed from: e  reason: collision with root package name */
        final int f8080e;

        f(int i8) {
            this.f8080e = i8;
        }
    }

    /* renamed from: io.flutter.view.g$g  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0108g {
        TAP(1),
        LONG_PRESS(2),
        SCROLL_LEFT(4),
        SCROLL_RIGHT(8),
        SCROLL_UP(16),
        SCROLL_DOWN(32),
        INCREASE(64),
        DECREASE(128),
        SHOW_ON_SCREEN(256),
        MOVE_CURSOR_FORWARD_BY_CHARACTER(512),
        MOVE_CURSOR_BACKWARD_BY_CHARACTER(1024),
        SET_SELECTION(2048),
        COPY(4096),
        CUT(8192),
        PASTE(16384),
        DID_GAIN_ACCESSIBILITY_FOCUS(32768),
        DID_LOSE_ACCESSIBILITY_FOCUS(65536),
        CUSTOM_ACTION(131072),
        DISMISS(262144),
        MOVE_CURSOR_FORWARD_BY_WORD(524288),
        MOVE_CURSOR_BACKWARD_BY_WORD(1048576),
        SET_TEXT(2097152),
        FOCUS(4194304);
        

        /* renamed from: e  reason: collision with root package name */
        public final int f8102e;

        EnumC0108g(int i8) {
            this.f8102e = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        private int f8103a = -1;

        /* renamed from: b  reason: collision with root package name */
        private int f8104b = -1;

        /* renamed from: c  reason: collision with root package name */
        private int f8105c = -1;

        /* renamed from: d  reason: collision with root package name */
        private String f8106d;

        /* renamed from: e  reason: collision with root package name */
        private String f8107e;

        h() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum i {
        HAS_CHECKED_STATE(1),
        IS_CHECKED(2),
        IS_SELECTED(4),
        IS_BUTTON(8),
        IS_TEXT_FIELD(16),
        IS_FOCUSED(32),
        HAS_ENABLED_STATE(64),
        IS_ENABLED(128),
        IS_IN_MUTUALLY_EXCLUSIVE_GROUP(256),
        IS_HEADER(512),
        IS_OBSCURED(1024),
        SCOPES_ROUTE(2048),
        NAMES_ROUTE(4096),
        IS_HIDDEN(8192),
        IS_IMAGE(16384),
        IS_LIVE_REGION(32768),
        HAS_TOGGLED_STATE(65536),
        IS_TOGGLED(131072),
        HAS_IMPLICIT_SCROLLING(262144),
        IS_MULTILINE(524288),
        IS_READ_ONLY(1048576),
        IS_FOCUSABLE(2097152),
        IS_LINK(4194304),
        IS_SLIDER(8388608),
        IS_KEYBOARD_KEY(16777216),
        IS_CHECK_STATE_MIXED(33554432),
        HAS_EXPANDED_STATE(67108864),
        IS_EXPANDED(134217728);
        

        /* renamed from: e  reason: collision with root package name */
        final int f8129e;

        i(int i8) {
            this.f8129e = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class j extends n {

        /* renamed from: d  reason: collision with root package name */
        String f8130d;

        private j() {
            super(null);
        }

        /* synthetic */ j(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public interface k {
        void a(boolean z7, boolean z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class l {
        private p B;
        private int D;
        private int E;
        private int F;
        private int G;
        private float H;
        private float I;
        private float J;
        private String K;
        private String L;
        private float M;
        private float N;
        private float O;
        private float P;
        private float[] Q;
        private l R;
        private List U;
        private h V;
        private h W;
        private float[] Y;

        /* renamed from: a  reason: collision with root package name */
        final g f8131a;

        /* renamed from: a0  reason: collision with root package name */
        private float[] f8132a0;

        /* renamed from: b0  reason: collision with root package name */
        private Rect f8134b0;

        /* renamed from: c  reason: collision with root package name */
        private int f8135c;

        /* renamed from: d  reason: collision with root package name */
        private int f8136d;

        /* renamed from: e  reason: collision with root package name */
        private int f8137e;

        /* renamed from: f  reason: collision with root package name */
        private int f8138f;

        /* renamed from: g  reason: collision with root package name */
        private int f8139g;

        /* renamed from: h  reason: collision with root package name */
        private int f8140h;

        /* renamed from: i  reason: collision with root package name */
        private int f8141i;

        /* renamed from: j  reason: collision with root package name */
        private int f8142j;

        /* renamed from: k  reason: collision with root package name */
        private int f8143k;

        /* renamed from: l  reason: collision with root package name */
        private float f8144l;

        /* renamed from: m  reason: collision with root package name */
        private float f8145m;

        /* renamed from: n  reason: collision with root package name */
        private float f8146n;

        /* renamed from: o  reason: collision with root package name */
        private String f8147o;

        /* renamed from: p  reason: collision with root package name */
        private String f8148p;

        /* renamed from: q  reason: collision with root package name */
        private List f8149q;

        /* renamed from: r  reason: collision with root package name */
        private String f8150r;

        /* renamed from: s  reason: collision with root package name */
        private List f8151s;

        /* renamed from: t  reason: collision with root package name */
        private String f8152t;

        /* renamed from: u  reason: collision with root package name */
        private List f8153u;

        /* renamed from: v  reason: collision with root package name */
        private String f8154v;

        /* renamed from: w  reason: collision with root package name */
        private List f8155w;

        /* renamed from: x  reason: collision with root package name */
        private String f8156x;

        /* renamed from: y  reason: collision with root package name */
        private List f8157y;

        /* renamed from: z  reason: collision with root package name */
        private String f8158z;

        /* renamed from: b  reason: collision with root package name */
        private int f8133b = -1;
        private int A = -1;
        private boolean C = false;
        private List S = new ArrayList();
        private List T = new ArrayList();
        private boolean X = true;
        private boolean Z = true;

        l(g gVar) {
            this.f8131a = gVar;
        }

        private float A0(float f8, float f9, float f10, float f11) {
            return Math.max(f8, Math.max(f9, Math.max(f10, f11)));
        }

        private float B0(float f8, float f9, float f10, float f11) {
            return Math.min(f8, Math.min(f9, Math.min(f10, f11)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean C0(l lVar, p6.d dVar) {
            return (lVar == null || lVar.l0(dVar) == null) ? false : true;
        }

        private void D0(float[] fArr, float[] fArr2, float[] fArr3) {
            Matrix.multiplyMV(fArr, 0, fArr2, 0, fArr3, 0);
            float f8 = fArr[3];
            fArr[0] = fArr[0] / f8;
            fArr[1] = fArr[1] / f8;
            fArr[2] = fArr[2] / f8;
            fArr[3] = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void E0(float[] fArr, Set set, boolean z7) {
            set.add(this);
            if (this.Z) {
                z7 = true;
            }
            if (z7) {
                if (this.f8132a0 == null) {
                    this.f8132a0 = new float[16];
                }
                if (this.Q == null) {
                    this.Q = new float[16];
                }
                Matrix.multiplyMM(this.f8132a0, 0, fArr, 0, this.Q, 0);
                float[] fArr2 = {this.M, this.N, 0.0f, 1.0f};
                float[] fArr3 = new float[4];
                float[] fArr4 = new float[4];
                float[] fArr5 = new float[4];
                float[] fArr6 = new float[4];
                D0(fArr3, this.f8132a0, fArr2);
                fArr2[0] = this.O;
                fArr2[1] = this.N;
                D0(fArr4, this.f8132a0, fArr2);
                fArr2[0] = this.O;
                fArr2[1] = this.P;
                D0(fArr5, this.f8132a0, fArr2);
                fArr2[0] = this.M;
                fArr2[1] = this.P;
                D0(fArr6, this.f8132a0, fArr2);
                if (this.f8134b0 == null) {
                    this.f8134b0 = new Rect();
                }
                this.f8134b0.set(Math.round(B0(fArr3[0], fArr4[0], fArr5[0], fArr6[0])), Math.round(B0(fArr3[1], fArr4[1], fArr5[1], fArr6[1])), Math.round(A0(fArr3[0], fArr4[0], fArr5[0], fArr6[0])), Math.round(A0(fArr3[1], fArr4[1], fArr5[1], fArr6[1])));
                this.Z = false;
            }
            int i8 = -1;
            for (l lVar : this.S) {
                lVar.A = i8;
                i8 = lVar.f8133b;
                lVar.E0(this.f8132a0, set, z7);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void F0(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
            this.C = true;
            this.K = this.f8150r;
            this.L = this.f8148p;
            this.D = this.f8135c;
            this.E = this.f8136d;
            this.F = this.f8139g;
            this.G = this.f8140h;
            this.H = this.f8144l;
            this.I = this.f8145m;
            this.J = this.f8146n;
            this.f8135c = byteBuffer.getInt();
            this.f8136d = byteBuffer.getInt();
            this.f8137e = byteBuffer.getInt();
            this.f8138f = byteBuffer.getInt();
            this.f8139g = byteBuffer.getInt();
            this.f8140h = byteBuffer.getInt();
            this.f8141i = byteBuffer.getInt();
            this.f8142j = byteBuffer.getInt();
            this.f8143k = byteBuffer.getInt();
            this.f8144l = byteBuffer.getFloat();
            this.f8145m = byteBuffer.getFloat();
            this.f8146n = byteBuffer.getFloat();
            int i8 = byteBuffer.getInt();
            this.f8147o = i8 == -1 ? null : strArr[i8];
            int i9 = byteBuffer.getInt();
            this.f8148p = i9 == -1 ? null : strArr[i9];
            this.f8149q = q0(byteBuffer, byteBufferArr);
            int i10 = byteBuffer.getInt();
            this.f8150r = i10 == -1 ? null : strArr[i10];
            this.f8151s = q0(byteBuffer, byteBufferArr);
            int i11 = byteBuffer.getInt();
            this.f8152t = i11 == -1 ? null : strArr[i11];
            this.f8153u = q0(byteBuffer, byteBufferArr);
            int i12 = byteBuffer.getInt();
            this.f8154v = i12 == -1 ? null : strArr[i12];
            this.f8155w = q0(byteBuffer, byteBufferArr);
            int i13 = byteBuffer.getInt();
            this.f8156x = i13 == -1 ? null : strArr[i13];
            this.f8157y = q0(byteBuffer, byteBufferArr);
            int i14 = byteBuffer.getInt();
            this.f8158z = i14 == -1 ? null : strArr[i14];
            this.B = p.e(byteBuffer.getInt());
            this.M = byteBuffer.getFloat();
            this.N = byteBuffer.getFloat();
            this.O = byteBuffer.getFloat();
            this.P = byteBuffer.getFloat();
            if (this.Q == null) {
                this.Q = new float[16];
            }
            for (int i15 = 0; i15 < 16; i15++) {
                this.Q[i15] = byteBuffer.getFloat();
            }
            this.X = true;
            this.Z = true;
            int i16 = byteBuffer.getInt();
            this.S.clear();
            this.T.clear();
            for (int i17 = 0; i17 < i16; i17++) {
                l z7 = this.f8131a.z(byteBuffer.getInt());
                z7.R = this;
                this.S.add(z7);
            }
            for (int i18 = 0; i18 < i16; i18++) {
                l z8 = this.f8131a.z(byteBuffer.getInt());
                z8.R = this;
                this.T.add(z8);
            }
            int i19 = byteBuffer.getInt();
            if (i19 == 0) {
                this.U = null;
                return;
            }
            List list = this.U;
            if (list == null) {
                this.U = new ArrayList(i19);
            } else {
                list.clear();
            }
            for (int i20 = 0; i20 < i19; i20++) {
                h y7 = this.f8131a.y(byteBuffer.getInt());
                if (y7.f8105c == EnumC0108g.TAP.f8102e) {
                    this.V = y7;
                } else if (y7.f8105c == EnumC0108g.LONG_PRESS.f8102e) {
                    this.W = y7;
                } else {
                    this.U.add(y7);
                }
                this.U.add(y7);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g0(List list) {
            if (x0(i.SCOPES_ROUTE)) {
                list.add(this);
            }
            for (l lVar : this.S) {
                lVar.g0(list);
            }
        }

        private SpannableString h0(String str, List list) {
            if (str == null) {
                return null;
            }
            SpannableString spannableString = new SpannableString(str);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    n nVar = (n) it.next();
                    int i8 = e.f8071a[nVar.f8161c.ordinal()];
                    if (i8 == 1) {
                        spannableString.setSpan(new TtsSpan.Builder("android.type.verbatim").build(), nVar.f8159a, nVar.f8160b, 0);
                    } else if (i8 == 2) {
                        spannableString.setSpan(new LocaleSpan(Locale.forLanguageTag(((j) nVar).f8130d)), nVar.f8159a, nVar.f8160b, 0);
                    }
                }
            }
            return spannableString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean i0() {
            String str;
            String str2 = this.f8148p;
            if (str2 == null && this.L == null) {
                return false;
            }
            return str2 == null || (str = this.L) == null || !str2.equals(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean j0() {
            return (Float.isNaN(this.f8144l) || Float.isNaN(this.H) || this.H == this.f8144l) ? false : true;
        }

        private void k0() {
            if (this.X) {
                this.X = false;
                if (this.Y == null) {
                    this.Y = new float[16];
                }
                if (Matrix.invertM(this.Y, 0, this.Q, 0)) {
                    return;
                }
                Arrays.fill(this.Y, 0.0f);
            }
        }

        private l l0(p6.d dVar) {
            for (l lVar = this.R; lVar != null; lVar = lVar.R) {
                if (dVar.test(lVar)) {
                    return lVar;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Rect m0() {
            return this.f8134b0;
        }

        static /* synthetic */ int n(l lVar, int i8) {
            int i9 = lVar.f8140h + i8;
            lVar.f8140h = i9;
            return i9;
        }

        private CharSequence n0() {
            return h0(this.f8156x, this.f8157y);
        }

        static /* synthetic */ int o(l lVar, int i8) {
            int i9 = lVar.f8140h - i8;
            lVar.f8140h = i9;
            return i9;
        }

        private CharSequence o0() {
            return h0(this.f8148p, this.f8149q);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String p0() {
            String str;
            if (!x0(i.NAMES_ROUTE) || (str = this.f8148p) == null || str.isEmpty()) {
                for (l lVar : this.S) {
                    String p02 = lVar.p0();
                    if (p02 != null && !p02.isEmpty()) {
                        return p02;
                    }
                }
                return null;
            }
            return this.f8148p;
        }

        private List q0(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) {
            int i8 = byteBuffer.getInt();
            if (i8 == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i8);
            for (int i9 = 0; i9 < i8; i9++) {
                int i10 = byteBuffer.getInt();
                int i11 = byteBuffer.getInt();
                o oVar = o.values()[byteBuffer.getInt()];
                int i12 = e.f8071a[oVar.ordinal()];
                if (i12 == 1) {
                    byteBuffer.getInt();
                    m mVar = new m(null);
                    mVar.f8159a = i10;
                    mVar.f8160b = i11;
                    mVar.f8161c = oVar;
                    arrayList.add(mVar);
                } else if (i12 == 2) {
                    ByteBuffer byteBuffer2 = byteBufferArr[byteBuffer.getInt()];
                    j jVar = new j(null);
                    jVar.f8159a = i10;
                    jVar.f8160b = i11;
                    jVar.f8161c = oVar;
                    jVar.f8130d = Charset.forName("UTF-8").decode(byteBuffer2).toString();
                    arrayList.add(jVar);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence r0() {
            CharSequence[] charSequenceArr = {o0(), n0()};
            CharSequence charSequence = null;
            for (int i8 = 0; i8 < 2; i8++) {
                CharSequence charSequence2 = charSequenceArr[i8];
                if (charSequence2 != null && charSequence2.length() > 0) {
                    charSequence = (charSequence == null || charSequence.length() == 0) ? charSequence2 : TextUtils.concat(charSequence, ", ", charSequence2);
                }
            }
            return charSequence;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence s0() {
            return h0(this.f8150r, this.f8151s);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence t0() {
            CharSequence[] charSequenceArr = {s0(), o0(), n0()};
            CharSequence charSequence = null;
            for (int i8 = 0; i8 < 3; i8++) {
                CharSequence charSequence2 = charSequenceArr[i8];
                if (charSequence2 != null && charSequence2.length() > 0) {
                    charSequence = (charSequence == null || charSequence.length() == 0) ? charSequence2 : TextUtils.concat(charSequence, ", ", charSequence2);
                }
            }
            return charSequence;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean u0(EnumC0108g enumC0108g) {
            return (enumC0108g.f8102e & this.E) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean v0(i iVar) {
            return (iVar.f8129e & this.D) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean w0(EnumC0108g enumC0108g) {
            return (enumC0108g.f8102e & this.f8136d) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean x0(i iVar) {
            return (iVar.f8129e & this.f8135c) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public l y0(float[] fArr, boolean z7) {
            float f8 = fArr[3];
            boolean z8 = false;
            float f9 = fArr[0] / f8;
            float f10 = fArr[1] / f8;
            if (f9 < this.M || f9 >= this.O || f10 < this.N || f10 >= this.P) {
                return null;
            }
            float[] fArr2 = new float[4];
            for (l lVar : this.T) {
                if (!lVar.x0(i.IS_HIDDEN)) {
                    lVar.k0();
                    Matrix.multiplyMV(fArr2, 0, lVar.Y, 0, fArr, 0);
                    l y02 = lVar.y0(fArr2, z7);
                    if (y02 != null) {
                        return y02;
                    }
                }
            }
            if (z7 && this.f8141i != -1) {
                z8 = true;
            }
            if (z0() || z8) {
                return this;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean z0() {
            String str;
            String str2;
            String str3;
            if (x0(i.SCOPES_ROUTE)) {
                return false;
            }
            if (x0(i.IS_FOCUSABLE)) {
                return true;
            }
            return ((this.f8136d & (~g.f8040z)) == 0 && (this.f8135c & g.A) == 0 && ((str = this.f8148p) == null || str.isEmpty()) && (((str2 = this.f8150r) == null || str2.isEmpty()) && ((str3 = this.f8156x) == null || str3.isEmpty()))) ? false : true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class m extends n {
        private m() {
            super(null);
        }

        /* synthetic */ m(a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class n {

        /* renamed from: a  reason: collision with root package name */
        int f8159a;

        /* renamed from: b  reason: collision with root package name */
        int f8160b;

        /* renamed from: c  reason: collision with root package name */
        o f8161c;

        private n() {
        }

        /* synthetic */ n(a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum o {
        SPELLOUT,
        LOCALE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum p {
        UNKNOWN,
        LTR,
        RTL;

        public static p e(int i8) {
            return i8 != 1 ? i8 != 2 ? UNKNOWN : LTR : RTL;
        }
    }

    public g(View view, h6.a aVar, AccessibilityManager accessibilityManager, ContentResolver contentResolver, q qVar) {
        this(view, aVar, accessibilityManager, contentResolver, new AccessibilityViewEmbedder(view, 65536), qVar);
    }

    private l A() {
        return (l) this.f8047g.get(0);
    }

    private void B(float f8, float f9, boolean z7) {
        l y02;
        if (this.f8047g.isEmpty() || (y02 = A().y0(new float[]{f8, f9, 0.0f, 1.0f}, z7)) == this.f8055o) {
            return;
        }
        if (y02 != null) {
            T(y02.f8133b, 128);
        }
        l lVar = this.f8055o;
        if (lVar != null) {
            T(lVar.f8133b, 256);
        }
        this.f8055o = y02;
    }

    private boolean D(l lVar) {
        if (lVar.x0(i.SCOPES_ROUTE)) {
            return false;
        }
        return (lVar.t0() == null && (lVar.f8136d & (~C)) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean F(l lVar, l lVar2) {
        return lVar2 == lVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean G(l lVar) {
        return lVar.x0(i.HAS_IMPLICIT_SCROLLING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AccessibilityEvent I(int i8, int i9) {
        AccessibilityEvent H = H(i9);
        H.setPackageName(this.f8041a.getContext().getPackageName());
        H.setSource(this.f8041a, i8);
        return H;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        l lVar = this.f8055o;
        if (lVar != null) {
            T(lVar.f8133b, 256);
            this.f8055o = null;
        }
    }

    private void O(l lVar) {
        String p02 = lVar.p0();
        if (p02 == null) {
            p02 = " ";
        }
        if (Build.VERSION.SDK_INT >= 28) {
            X(p02);
            return;
        }
        AccessibilityEvent I = I(lVar.f8133b, 32);
        I.getText().add(p02);
        U(I);
    }

    private boolean P(l lVar, int i8, Bundle bundle, boolean z7) {
        int i9 = bundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
        boolean z8 = bundle.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN");
        int i10 = lVar.f8139g;
        int i11 = lVar.f8140h;
        R(lVar, i9, z7, z8);
        if (i10 != lVar.f8139g || i11 != lVar.f8140h) {
            String str = lVar.f8150r != null ? lVar.f8150r : "";
            AccessibilityEvent I = I(lVar.f8133b, 8192);
            I.getText().add(str);
            I.setFromIndex(lVar.f8139g);
            I.setToIndex(lVar.f8140h);
            I.setItemCount(str.length());
            U(I);
        }
        if (i9 == 1) {
            if (z7) {
                EnumC0108g enumC0108g = EnumC0108g.MOVE_CURSOR_FORWARD_BY_CHARACTER;
                if (lVar.w0(enumC0108g)) {
                    this.f8042b.c(i8, enumC0108g, Boolean.valueOf(z8));
                    return true;
                }
            }
            if (z7) {
                return false;
            }
            EnumC0108g enumC0108g2 = EnumC0108g.MOVE_CURSOR_BACKWARD_BY_CHARACTER;
            if (lVar.w0(enumC0108g2)) {
                this.f8042b.c(i8, enumC0108g2, Boolean.valueOf(z8));
                return true;
            }
            return false;
        } else if (i9 != 2) {
            return i9 == 4 || i9 == 8 || i9 == 16;
        } else {
            if (z7) {
                EnumC0108g enumC0108g3 = EnumC0108g.MOVE_CURSOR_FORWARD_BY_WORD;
                if (lVar.w0(enumC0108g3)) {
                    this.f8042b.c(i8, enumC0108g3, Boolean.valueOf(z8));
                    return true;
                }
            }
            if (z7) {
                return false;
            }
            EnumC0108g enumC0108g4 = EnumC0108g.MOVE_CURSOR_BACKWARD_BY_WORD;
            if (lVar.w0(enumC0108g4)) {
                this.f8042b.c(i8, enumC0108g4, Boolean.valueOf(z8));
                return true;
            }
            return false;
        }
    }

    private boolean Q(l lVar, int i8, Bundle bundle) {
        String string = (bundle == null || !bundle.containsKey("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE")) ? "" : bundle.getString("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE");
        this.f8042b.c(i8, EnumC0108g.SET_TEXT, string);
        lVar.f8150r = string;
        lVar.f8151s = null;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0022, code lost:
        if (r6 != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0024, code lost:
        r5 = r4.f8150r.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002c, code lost:
        r4.f8140h = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0031, code lost:
        r4.f8140h = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
        if (r5.find() != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
        io.flutter.view.g.l.n(r4, r5.start(1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008d, code lost:
        if (r5.find() != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008f, code lost:
        r5 = r5.start(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c1, code lost:
        if (r5.find() != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e6, code lost:
        if (r5.find() != false) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void R(io.flutter.view.g.l r4, int r5, boolean r6, boolean r7) {
        /*
            r3 = this;
            int r0 = io.flutter.view.g.l.l(r4)
            if (r0 < 0) goto L111
            int r0 = io.flutter.view.g.l.j(r4)
            if (r0 >= 0) goto Le
            goto L111
        Le:
            r0 = 1
            if (r5 == r0) goto Le9
            r1 = 2
            r2 = 0
            if (r5 == r1) goto L94
            r1 = 4
            if (r5 == r1) goto L36
            r0 = 8
            if (r5 == r0) goto L22
            r0 = 16
            if (r5 == r0) goto L22
            goto L108
        L22:
            if (r6 == 0) goto L31
        L24:
            java.lang.String r5 = io.flutter.view.g.l.r(r4)
            int r5 = r5.length()
        L2c:
            io.flutter.view.g.l.m(r4, r5)
            goto L108
        L31:
            io.flutter.view.g.l.m(r4, r2)
            goto L108
        L36:
            if (r6 == 0) goto L6b
            int r5 = io.flutter.view.g.l.l(r4)
            java.lang.String r1 = io.flutter.view.g.l.r(r4)
            int r1 = r1.length()
            if (r5 >= r1) goto L6b
            java.lang.String r5 = "(?!^)(\\n)"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)
            java.lang.String r6 = io.flutter.view.g.l.r(r4)
            int r1 = io.flutter.view.g.l.l(r4)
            java.lang.String r6 = r6.substring(r1)
            java.util.regex.Matcher r5 = r5.matcher(r6)
            boolean r6 = r5.find()
            if (r6 == 0) goto L24
        L62:
            int r5 = r5.start(r0)
            io.flutter.view.g.l.n(r4, r5)
            goto L108
        L6b:
            if (r6 != 0) goto L108
            int r5 = io.flutter.view.g.l.l(r4)
            if (r5 <= 0) goto L108
            java.lang.String r5 = "(?s:.*)(\\n)"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)
            java.lang.String r6 = io.flutter.view.g.l.r(r4)
            int r1 = io.flutter.view.g.l.l(r4)
            java.lang.String r6 = r6.substring(r2, r1)
            java.util.regex.Matcher r5 = r5.matcher(r6)
            boolean r6 = r5.find()
            if (r6 == 0) goto L31
        L8f:
            int r5 = r5.start(r0)
            goto L2c
        L94:
            if (r6 == 0) goto Lc4
            int r5 = io.flutter.view.g.l.l(r4)
            java.lang.String r1 = io.flutter.view.g.l.r(r4)
            int r1 = r1.length()
            if (r5 >= r1) goto Lc4
            java.lang.String r5 = "\\p{L}(\\b)"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)
            java.lang.String r6 = io.flutter.view.g.l.r(r4)
            int r1 = io.flutter.view.g.l.l(r4)
            java.lang.String r6 = r6.substring(r1)
            java.util.regex.Matcher r5 = r5.matcher(r6)
            r5.find()
            boolean r6 = r5.find()
            if (r6 == 0) goto L24
            goto L62
        Lc4:
            if (r6 != 0) goto L108
            int r5 = io.flutter.view.g.l.l(r4)
            if (r5 <= 0) goto L108
            java.lang.String r5 = "(?s:.*)(\\b)\\p{L}"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)
            java.lang.String r6 = io.flutter.view.g.l.r(r4)
            int r1 = io.flutter.view.g.l.l(r4)
            java.lang.String r6 = r6.substring(r2, r1)
            java.util.regex.Matcher r5 = r5.matcher(r6)
            boolean r6 = r5.find()
            if (r6 == 0) goto L108
            goto L8f
        Le9:
            if (r6 == 0) goto Lfd
            int r5 = io.flutter.view.g.l.l(r4)
            java.lang.String r1 = io.flutter.view.g.l.r(r4)
            int r1 = r1.length()
            if (r5 >= r1) goto Lfd
            io.flutter.view.g.l.n(r4, r0)
            goto L108
        Lfd:
            if (r6 != 0) goto L108
            int r5 = io.flutter.view.g.l.l(r4)
            if (r5 <= 0) goto L108
            io.flutter.view.g.l.o(r4, r0)
        L108:
            if (r7 != 0) goto L111
            int r5 = io.flutter.view.g.l.l(r4)
            io.flutter.view.g.l.k(r4, r5)
        L111:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.view.g.R(io.flutter.view.g$l, int, boolean, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U(AccessibilityEvent accessibilityEvent) {
        if (this.f8043c.isEnabled()) {
            this.f8041a.getParent().requestSendAccessibilityEvent(this.f8041a, accessibilityEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        this.f8042b.f(this.f8052l);
    }

    private void W(int i8) {
        AccessibilityEvent I = I(i8, 2048);
        I.setContentChangeTypes(1);
        U(I);
    }

    private void X(String str) {
        this.f8041a.setAccessibilityPaneTitle(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y(boolean z7) {
        if (this.f8060t == z7) {
            return;
        }
        this.f8060t = z7;
        this.f8052l = z7 ? this.f8052l | f.ACCESSIBLE_NAVIGATION.f8080e : this.f8052l & (~f.ACCESSIBLE_NAVIGATION.f8080e);
        V();
    }

    private void Z() {
        int i8;
        View view = this.f8041a;
        if (view == null || view.getResources() == null) {
            return;
        }
        i8 = this.f8041a.getResources().getConfiguration().fontWeightAdjustment;
        this.f8052l = i8 != Integer.MAX_VALUE && i8 >= 300 ? this.f8052l | f.BOLD_TEXT.f8080e : this.f8052l & (~f.BOLD_TEXT.f8080e);
        V();
    }

    private boolean b0(final l lVar) {
        return lVar.f8142j > 0 && (l.C0(this.f8049i, new p6.d() { // from class: io.flutter.view.e
            @Override // p6.d
            public final boolean test(Object obj) {
                boolean F;
                F = g.F(g.l.this, (g.l) obj);
                return F;
            }
        }) || !l.C0(this.f8049i, new p6.d() { // from class: io.flutter.view.f
            @Override // p6.d
            public final boolean test(Object obj) {
                boolean G;
                G = g.G((g.l) obj);
                return G;
            }
        }));
    }

    static /* synthetic */ int e(g gVar, int i8) {
        int i9 = i8 & gVar.f8052l;
        gVar.f8052l = i9;
        return i9;
    }

    private void e0(l lVar) {
        View c8;
        Integer num;
        lVar.R = null;
        if (lVar.f8141i != -1 && (num = this.f8050j) != null && this.f8044d.platformViewOfNode(num.intValue()) == this.f8045e.c(lVar.f8141i)) {
            T(this.f8050j.intValue(), 65536);
            this.f8050j = null;
        }
        if (lVar.f8141i != -1 && (c8 = this.f8045e.c(lVar.f8141i)) != null) {
            c8.setImportantForAccessibility(4);
        }
        l lVar2 = this.f8049i;
        if (lVar2 == lVar) {
            T(lVar2.f8133b, 65536);
            this.f8049i = null;
        }
        if (this.f8053m == lVar) {
            this.f8053m = null;
        }
        if (this.f8055o == lVar) {
            this.f8055o = null;
        }
    }

    static /* synthetic */ int f(g gVar, int i8) {
        int i9 = i8 | gVar.f8052l;
        gVar.f8052l = i9;
        return i9;
    }

    private AccessibilityEvent u(int i8, String str, String str2) {
        AccessibilityEvent I = I(i8, 16);
        I.setBeforeText(str);
        I.getText().add(str2);
        int i9 = 0;
        while (i9 < str.length() && i9 < str2.length() && str.charAt(i9) == str2.charAt(i9)) {
            i9++;
        }
        if (i9 < str.length() || i9 < str2.length()) {
            I.setFromIndex(i9);
            int length = str.length() - 1;
            int length2 = str2.length() - 1;
            while (length >= i9 && length2 >= i9 && str.charAt(length) == str2.charAt(length2)) {
                length--;
                length2--;
            }
            I.setRemovedCount((length - i9) + 1);
            I.setAddedCount((length2 - i9) + 1);
            return I;
        }
        return null;
    }

    private boolean v() {
        int i8;
        Activity e8 = p6.h.e(this.f8041a.getContext());
        if (e8 == null || e8.getWindow() == null) {
            return false;
        }
        i8 = e8.getWindow().getAttributes().layoutInDisplayCutoutMode;
        return i8 == 2 || i8 == 0;
    }

    private Rect x(Rect rect) {
        Rect rect2 = new Rect(rect);
        int[] iArr = new int[2];
        this.f8041a.getLocationOnScreen(iArr);
        rect2.offset(iArr[0], iArr[1]);
        return rect2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public h y(int i8) {
        h hVar = (h) this.f8048h.get(Integer.valueOf(i8));
        if (hVar == null) {
            h hVar2 = new h();
            hVar2.f8104b = i8;
            hVar2.f8103a = B + i8;
            this.f8048h.put(Integer.valueOf(i8), hVar2);
            return hVar2;
        }
        return hVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public l z(int i8) {
        l lVar = (l) this.f8047g.get(Integer.valueOf(i8));
        if (lVar == null) {
            l lVar2 = new l(this);
            lVar2.f8133b = i8;
            this.f8047g.put(Integer.valueOf(i8), lVar2);
            return lVar2;
        }
        return lVar;
    }

    public boolean C() {
        return this.f8043c.isEnabled();
    }

    public boolean E() {
        return this.f8043c.isTouchExplorationEnabled();
    }

    public AccessibilityEvent H(int i8) {
        return AccessibilityEvent.obtain(i8);
    }

    public AccessibilityNodeInfo J(View view) {
        return AccessibilityNodeInfo.obtain(view);
    }

    public AccessibilityNodeInfo K(View view, int i8) {
        return AccessibilityNodeInfo.obtain(view, i8);
    }

    public boolean L(MotionEvent motionEvent) {
        return M(motionEvent, false);
    }

    public boolean M(MotionEvent motionEvent, boolean z7) {
        if (this.f8043c.isTouchExplorationEnabled() && !this.f8047g.isEmpty()) {
            l y02 = A().y0(new float[]{motionEvent.getX(), motionEvent.getY(), 0.0f, 1.0f}, z7);
            if (y02 != null && y02.f8141i != -1) {
                if (z7) {
                    return false;
                }
                return this.f8044d.onAccessibilityHoverEvent(y02.f8133b, motionEvent);
            }
            if (motionEvent.getAction() == 9 || motionEvent.getAction() == 7) {
                B(motionEvent.getX(), motionEvent.getY(), z7);
            } else if (motionEvent.getAction() != 10) {
                z5.b.a("flutter", "unexpected accessibility hover event: " + motionEvent);
                return false;
            } else {
                N();
            }
            return true;
        }
        return false;
    }

    public void S() {
        this.f8061u = true;
        this.f8045e.d();
        a0(null);
        this.f8043c.removeAccessibilityStateChangeListener(this.f8063w);
        this.f8043c.removeTouchExplorationStateChangeListener(this.f8064x);
        this.f8046f.unregisterContentObserver(this.f8065y);
        this.f8042b.g(null);
    }

    public void T(int i8, int i9) {
        if (this.f8043c.isEnabled()) {
            U(I(i8, i9));
        }
    }

    public void a0(k kVar) {
        this.f8059s = kVar;
    }

    void c0(ByteBuffer byteBuffer, String[] strArr) {
        while (byteBuffer.hasRemaining()) {
            h y7 = y(byteBuffer.getInt());
            y7.f8105c = byteBuffer.getInt();
            int i8 = byteBuffer.getInt();
            String str = null;
            y7.f8106d = i8 == -1 ? null : strArr[i8];
            int i9 = byteBuffer.getInt();
            if (i9 != -1) {
                str = strArr[i9];
            }
            y7.f8107e = str;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:219:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0467  */
    @Override // android.view.accessibility.AccessibilityNodeProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.accessibility.AccessibilityNodeInfo createAccessibilityNodeInfo(int r14) {
        /*
            Method dump skipped, instructions count: 1185
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.view.g.createAccessibilityNodeInfo(int):android.view.accessibility.AccessibilityNodeInfo");
    }

    void d0(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
        l lVar;
        l lVar2;
        float f8;
        float f9;
        WindowInsets rootWindowInsets;
        Integer valueOf;
        View c8;
        ArrayList arrayList = new ArrayList();
        while (byteBuffer.hasRemaining()) {
            l z7 = z(byteBuffer.getInt());
            z7.F0(byteBuffer, strArr, byteBufferArr);
            if (!z7.x0(i.IS_HIDDEN)) {
                if (z7.x0(i.IS_FOCUSED)) {
                    this.f8053m = z7;
                }
                if (z7.C) {
                    arrayList.add(z7);
                }
                if (z7.f8141i != -1 && !this.f8045e.b(z7.f8141i) && (c8 = this.f8045e.c(z7.f8141i)) != null) {
                    c8.setImportantForAccessibility(0);
                }
            }
        }
        HashSet hashSet = new HashSet();
        l A2 = A();
        ArrayList<l> arrayList2 = new ArrayList();
        if (A2 != null) {
            float[] fArr = new float[16];
            Matrix.setIdentityM(fArr, 0);
            if ((Build.VERSION.SDK_INT >= 28 ? v() : true) && (rootWindowInsets = this.f8041a.getRootWindowInsets()) != null) {
                if (!this.f8058r.equals(Integer.valueOf(rootWindowInsets.getSystemWindowInsetLeft()))) {
                    A2.Z = true;
                    A2.X = true;
                }
                this.f8058r = Integer.valueOf(rootWindowInsets.getSystemWindowInsetLeft());
                Matrix.translateM(fArr, 0, valueOf.intValue(), 0.0f, 0.0f);
            }
            A2.E0(fArr, hashSet, false);
            A2.g0(arrayList2);
        }
        l lVar3 = null;
        for (l lVar4 : arrayList2) {
            if (!this.f8056p.contains(Integer.valueOf(lVar4.f8133b))) {
                lVar3 = lVar4;
            }
        }
        if (lVar3 == null && arrayList2.size() > 0) {
            lVar3 = (l) arrayList2.get(arrayList2.size() - 1);
        }
        if (lVar3 != null && (lVar3.f8133b != this.f8057q || arrayList2.size() != this.f8056p.size())) {
            this.f8057q = lVar3.f8133b;
            O(lVar3);
        }
        this.f8056p.clear();
        for (l lVar5 : arrayList2) {
            this.f8056p.add(Integer.valueOf(lVar5.f8133b));
        }
        Iterator it = this.f8047g.entrySet().iterator();
        while (it.hasNext()) {
            l lVar6 = (l) ((Map.Entry) it.next()).getValue();
            if (!hashSet.contains(lVar6)) {
                e0(lVar6);
                it.remove();
            }
        }
        W(0);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            l lVar7 = (l) it2.next();
            if (lVar7.j0()) {
                AccessibilityEvent I = I(lVar7.f8133b, 4096);
                float f10 = lVar7.f8144l;
                float f11 = lVar7.f8145m;
                if (Float.isInfinite(lVar7.f8145m)) {
                    if (f10 > 70000.0f) {
                        f10 = 70000.0f;
                    }
                    f11 = 100000.0f;
                }
                if (Float.isInfinite(lVar7.f8146n)) {
                    f8 = f11 + 100000.0f;
                    if (f10 < -70000.0f) {
                        f10 = -70000.0f;
                    }
                    f9 = f10 + 100000.0f;
                } else {
                    f8 = f11 - lVar7.f8146n;
                    f9 = f10 - lVar7.f8146n;
                }
                if (lVar7.u0(EnumC0108g.SCROLL_UP) || lVar7.u0(EnumC0108g.SCROLL_DOWN)) {
                    I.setScrollY((int) f9);
                    I.setMaxScrollY((int) f8);
                } else if (lVar7.u0(EnumC0108g.SCROLL_LEFT) || lVar7.u0(EnumC0108g.SCROLL_RIGHT)) {
                    I.setScrollX((int) f9);
                    I.setMaxScrollX((int) f8);
                }
                if (lVar7.f8142j > 0) {
                    I.setItemCount(lVar7.f8142j);
                    I.setFromIndex(lVar7.f8143k);
                    int i8 = 0;
                    for (l lVar8 : lVar7.T) {
                        if (!lVar8.x0(i.IS_HIDDEN)) {
                            i8++;
                        }
                    }
                    I.setToIndex((lVar7.f8143k + i8) - 1);
                }
                U(I);
            }
            if (lVar7.x0(i.IS_LIVE_REGION) && lVar7.i0()) {
                W(lVar7.f8133b);
            }
            l lVar9 = this.f8049i;
            if (lVar9 != null && lVar9.f8133b == lVar7.f8133b) {
                i iVar = i.IS_SELECTED;
                if (!lVar7.v0(iVar) && lVar7.x0(iVar)) {
                    AccessibilityEvent I2 = I(lVar7.f8133b, 4);
                    I2.getText().add(lVar7.f8148p);
                    U(I2);
                }
            }
            l lVar10 = this.f8053m;
            if (lVar10 != null && lVar10.f8133b == lVar7.f8133b && ((lVar2 = this.f8054n) == null || lVar2.f8133b != this.f8053m.f8133b)) {
                this.f8054n = this.f8053m;
                U(I(lVar7.f8133b, 8));
            } else if (this.f8053m == null) {
                this.f8054n = null;
            }
            l lVar11 = this.f8053m;
            if (lVar11 != null && lVar11.f8133b == lVar7.f8133b) {
                i iVar2 = i.IS_TEXT_FIELD;
                if (lVar7.v0(iVar2) && lVar7.x0(iVar2) && ((lVar = this.f8049i) == null || lVar.f8133b == this.f8053m.f8133b)) {
                    String str = lVar7.K != null ? lVar7.K : "";
                    String str2 = lVar7.f8150r != null ? lVar7.f8150r : "";
                    AccessibilityEvent u7 = u(lVar7.f8133b, str, str2);
                    if (u7 != null) {
                        U(u7);
                    }
                    if (lVar7.F != lVar7.f8139g || lVar7.G != lVar7.f8140h) {
                        AccessibilityEvent I3 = I(lVar7.f8133b, 8192);
                        I3.getText().add(str2);
                        I3.setFromIndex(lVar7.f8139g);
                        I3.setToIndex(lVar7.f8140h);
                        I3.setItemCount(str2.length());
                        U(I3);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0016, code lost:
        if (r2 != null) goto L11;
     */
    @Override // android.view.accessibility.AccessibilityNodeProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.accessibility.AccessibilityNodeInfo findFocus(int r2) {
        /*
            r1 = this;
            r0 = 1
            if (r2 == r0) goto L7
            r0 = 2
            if (r2 == r0) goto L1d
            goto L27
        L7:
            io.flutter.view.g$l r2 = r1.f8053m
            if (r2 == 0) goto L14
        Lb:
            int r2 = io.flutter.view.g.l.a(r2)
        Lf:
            android.view.accessibility.AccessibilityNodeInfo r2 = r1.createAccessibilityNodeInfo(r2)
            return r2
        L14:
            java.lang.Integer r2 = r1.f8051k
            if (r2 == 0) goto L1d
        L18:
            int r2 = r2.intValue()
            goto Lf
        L1d:
            io.flutter.view.g$l r2 = r1.f8049i
            if (r2 == 0) goto L22
            goto Lb
        L22:
            java.lang.Integer r2 = r1.f8050j
            if (r2 == 0) goto L27
            goto L18
        L27:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.view.g.findFocus(int):android.view.accessibility.AccessibilityNodeInfo");
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public boolean performAction(int i8, int i9, Bundle bundle) {
        int i10;
        if (i8 >= 65536) {
            boolean performAction = this.f8044d.performAction(i8, i9, bundle);
            if (performAction && i9 == 128) {
                this.f8050j = null;
            }
            return performAction;
        }
        l lVar = (l) this.f8047g.get(Integer.valueOf(i8));
        boolean z7 = false;
        if (lVar == null) {
            return false;
        }
        switch (i9) {
            case 16:
                this.f8042b.b(i8, EnumC0108g.TAP);
                return true;
            case 32:
                this.f8042b.b(i8, EnumC0108g.LONG_PRESS);
                return true;
            case 64:
                if (this.f8049i == null) {
                    this.f8041a.invalidate();
                }
                this.f8049i = lVar;
                this.f8042b.b(i8, EnumC0108g.DID_GAIN_ACCESSIBILITY_FOCUS);
                HashMap hashMap = new HashMap();
                hashMap.put("type", "didGainFocus");
                hashMap.put("nodeId", Integer.valueOf(lVar.f8133b));
                this.f8042b.f7268a.c(hashMap);
                T(i8, 32768);
                if (lVar.w0(EnumC0108g.INCREASE) || lVar.w0(EnumC0108g.DECREASE)) {
                    T(i8, 4);
                }
                return true;
            case 128:
                l lVar2 = this.f8049i;
                if (lVar2 != null && lVar2.f8133b == i8) {
                    this.f8049i = null;
                }
                Integer num = this.f8050j;
                if (num != null && num.intValue() == i8) {
                    this.f8050j = null;
                }
                this.f8042b.b(i8, EnumC0108g.DID_LOSE_ACCESSIBILITY_FOCUS);
                T(i8, 65536);
                return true;
            case 256:
                return P(lVar, i8, bundle, true);
            case 512:
                return P(lVar, i8, bundle, false);
            case 4096:
                EnumC0108g enumC0108g = EnumC0108g.SCROLL_UP;
                if (!lVar.w0(enumC0108g)) {
                    enumC0108g = EnumC0108g.SCROLL_LEFT;
                    if (!lVar.w0(enumC0108g)) {
                        enumC0108g = EnumC0108g.INCREASE;
                        if (!lVar.w0(enumC0108g)) {
                            return false;
                        }
                        lVar.f8150r = lVar.f8152t;
                        lVar.f8151s = lVar.f8153u;
                        T(i8, 4);
                    }
                }
                this.f8042b.b(i8, enumC0108g);
                return true;
            case 8192:
                EnumC0108g enumC0108g2 = EnumC0108g.SCROLL_DOWN;
                if (!lVar.w0(enumC0108g2)) {
                    enumC0108g2 = EnumC0108g.SCROLL_RIGHT;
                    if (!lVar.w0(enumC0108g2)) {
                        enumC0108g2 = EnumC0108g.DECREASE;
                        if (!lVar.w0(enumC0108g2)) {
                            return false;
                        }
                        lVar.f8150r = lVar.f8154v;
                        lVar.f8151s = lVar.f8155w;
                        T(i8, 4);
                    }
                }
                this.f8042b.b(i8, enumC0108g2);
                return true;
            case 16384:
                this.f8042b.b(i8, EnumC0108g.COPY);
                return true;
            case 32768:
                this.f8042b.b(i8, EnumC0108g.PASTE);
                return true;
            case 65536:
                this.f8042b.b(i8, EnumC0108g.CUT);
                return true;
            case 131072:
                HashMap hashMap2 = new HashMap();
                if (bundle != null && bundle.containsKey("ACTION_ARGUMENT_SELECTION_START_INT") && bundle.containsKey("ACTION_ARGUMENT_SELECTION_END_INT")) {
                    z7 = true;
                }
                if (z7) {
                    hashMap2.put("base", Integer.valueOf(bundle.getInt("ACTION_ARGUMENT_SELECTION_START_INT")));
                    i10 = bundle.getInt("ACTION_ARGUMENT_SELECTION_END_INT");
                } else {
                    hashMap2.put("base", Integer.valueOf(lVar.f8140h));
                    i10 = lVar.f8140h;
                }
                hashMap2.put("extent", Integer.valueOf(i10));
                this.f8042b.c(i8, EnumC0108g.SET_SELECTION, hashMap2);
                l lVar3 = (l) this.f8047g.get(Integer.valueOf(i8));
                lVar3.f8139g = ((Integer) hashMap2.get("base")).intValue();
                lVar3.f8140h = ((Integer) hashMap2.get("extent")).intValue();
                return true;
            case 1048576:
                this.f8042b.b(i8, EnumC0108g.DISMISS);
                return true;
            case 2097152:
                return Q(lVar, i8, bundle);
            case 16908342:
                this.f8042b.b(i8, EnumC0108g.SHOW_ON_SCREEN);
                return true;
            default:
                h hVar = (h) this.f8048h.get(Integer.valueOf(i9 - B));
                if (hVar != null) {
                    this.f8042b.c(i8, EnumC0108g.CUSTOM_ACTION, Integer.valueOf(hVar.f8104b));
                    return true;
                }
                return false;
        }
    }

    public boolean w(View view, View view2, AccessibilityEvent accessibilityEvent) {
        Integer recordFlutterId;
        if (this.f8044d.requestSendAccessibilityEvent(view, view2, accessibilityEvent) && (recordFlutterId = this.f8044d.getRecordFlutterId(view, accessibilityEvent)) != null) {
            int eventType = accessibilityEvent.getEventType();
            if (eventType == 8) {
                this.f8051k = recordFlutterId;
                this.f8053m = null;
                return true;
            } else if (eventType == 128) {
                this.f8055o = null;
                return true;
            } else if (eventType == 32768) {
                this.f8050j = recordFlutterId;
                this.f8049i = null;
                return true;
            } else if (eventType != 65536) {
                return true;
            } else {
                this.f8051k = null;
                this.f8050j = null;
                return true;
            }
        }
        return false;
    }

    public g(View view, h6.a aVar, AccessibilityManager accessibilityManager, ContentResolver contentResolver, AccessibilityViewEmbedder accessibilityViewEmbedder, q qVar) {
        this.f8047g = new HashMap();
        this.f8048h = new HashMap();
        this.f8052l = 0;
        this.f8056p = new ArrayList();
        this.f8057q = 0;
        this.f8058r = 0;
        this.f8060t = false;
        this.f8061u = false;
        this.f8062v = new a();
        b bVar = new b();
        this.f8063w = bVar;
        c cVar = new c(new Handler());
        this.f8065y = cVar;
        this.f8041a = view;
        this.f8042b = aVar;
        this.f8043c = accessibilityManager;
        this.f8046f = contentResolver;
        this.f8044d = accessibilityViewEmbedder;
        this.f8045e = qVar;
        bVar.onAccessibilityStateChanged(accessibilityManager.isEnabled());
        accessibilityManager.addAccessibilityStateChangeListener(bVar);
        d dVar = new d(accessibilityManager);
        this.f8064x = dVar;
        dVar.onTouchExplorationStateChanged(accessibilityManager.isTouchExplorationEnabled());
        accessibilityManager.addTouchExplorationStateChangeListener(dVar);
        cVar.onChange(false);
        contentResolver.registerContentObserver(Settings.Global.getUriFor("transition_animation_scale"), false, cVar);
        if (Build.VERSION.SDK_INT >= 31) {
            Z();
        }
        qVar.a(this);
    }
}
