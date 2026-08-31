package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import androidx.emoji2.text.e;
import androidx.emoji2.text.l;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    private final e.i f3314a;

    /* renamed from: b  reason: collision with root package name */
    private final l f3315b;

    /* renamed from: c  reason: collision with root package name */
    private e.d f3316c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f3317d;

    /* renamed from: e  reason: collision with root package name */
    private final int[] f3318e;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {
        static int a(CharSequence charSequence, int i8, int i9) {
            int length = charSequence.length();
            if (i8 < 0 || length < i8 || i9 < 0) {
                return -1;
            }
            while (true) {
                boolean z7 = false;
                while (i9 != 0) {
                    i8--;
                    if (i8 < 0) {
                        return z7 ? -1 : 0;
                    }
                    char charAt = charSequence.charAt(i8);
                    if (z7) {
                        if (!Character.isHighSurrogate(charAt)) {
                            return -1;
                        }
                        i9--;
                    } else if (!Character.isSurrogate(charAt)) {
                        i9--;
                    } else if (Character.isHighSurrogate(charAt)) {
                        return -1;
                    } else {
                        z7 = true;
                    }
                }
                return i8;
            }
        }

        static int b(CharSequence charSequence, int i8, int i9) {
            int length = charSequence.length();
            if (i8 < 0 || length < i8 || i9 < 0) {
                return -1;
            }
            while (true) {
                boolean z7 = false;
                while (i9 != 0) {
                    if (i8 >= length) {
                        if (z7) {
                            return -1;
                        }
                        return length;
                    }
                    char charAt = charSequence.charAt(i8);
                    if (z7) {
                        if (!Character.isLowSurrogate(charAt)) {
                            return -1;
                        }
                        i9--;
                        i8++;
                    } else if (!Character.isSurrogate(charAt)) {
                        i9--;
                        i8++;
                    } else if (Character.isLowSurrogate(charAt)) {
                        return -1;
                    } else {
                        i8++;
                        z7 = true;
                    }
                }
                return i8;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private int f3319a = 1;

        /* renamed from: b  reason: collision with root package name */
        private final l.a f3320b;

        /* renamed from: c  reason: collision with root package name */
        private l.a f3321c;

        /* renamed from: d  reason: collision with root package name */
        private l.a f3322d;

        /* renamed from: e  reason: collision with root package name */
        private int f3323e;

        /* renamed from: f  reason: collision with root package name */
        private int f3324f;

        /* renamed from: g  reason: collision with root package name */
        private final boolean f3325g;

        /* renamed from: h  reason: collision with root package name */
        private final int[] f3326h;

        b(l.a aVar, boolean z7, int[] iArr) {
            this.f3320b = aVar;
            this.f3321c = aVar;
            this.f3325g = z7;
            this.f3326h = iArr;
        }

        private static boolean d(int i8) {
            return i8 == 65039;
        }

        private static boolean f(int i8) {
            return i8 == 65038;
        }

        private int g() {
            this.f3319a = 1;
            this.f3321c = this.f3320b;
            this.f3324f = 0;
            return 1;
        }

        private boolean h() {
            if (this.f3321c.b().j() || d(this.f3323e)) {
                return true;
            }
            if (this.f3325g) {
                if (this.f3326h == null) {
                    return true;
                }
                if (Arrays.binarySearch(this.f3326h, this.f3321c.b().b(0)) < 0) {
                    return true;
                }
            }
            return false;
        }

        int a(int i8) {
            l.a a8 = this.f3321c.a(i8);
            int i9 = 2;
            if (this.f3319a != 2) {
                if (a8 != null) {
                    this.f3319a = 2;
                    this.f3321c = a8;
                    this.f3324f = 1;
                }
                i9 = g();
            } else if (a8 != null) {
                this.f3321c = a8;
                this.f3324f++;
            } else {
                if (!f(i8)) {
                    if (!d(i8)) {
                        if (this.f3321c.b() != null) {
                            i9 = 3;
                            if (this.f3324f != 1 || h()) {
                                this.f3322d = this.f3321c;
                                g();
                            }
                        }
                    }
                }
                i9 = g();
            }
            this.f3323e = i8;
            return i9;
        }

        g b() {
            return this.f3321c.b();
        }

        g c() {
            return this.f3322d.b();
        }

        boolean e() {
            return this.f3319a == 2 && this.f3321c.b() != null && (this.f3324f > 1 || h());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(l lVar, e.i iVar, e.d dVar, boolean z7, int[] iArr) {
        this.f3314a = iVar;
        this.f3315b = lVar;
        this.f3316c = dVar;
        this.f3317d = z7;
        this.f3318e = iArr;
    }

    private void a(Spannable spannable, g gVar, int i8, int i9) {
        spannable.setSpan(this.f3314a.a(gVar), i8, i9, 33);
    }

    private static boolean b(Editable editable, KeyEvent keyEvent, boolean z7) {
        n1.b[] bVarArr;
        if (g(keyEvent)) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (!f(selectionStart, selectionEnd) && (bVarArr = (n1.b[]) editable.getSpans(selectionStart, selectionEnd, n1.b.class)) != null && bVarArr.length > 0) {
            for (n1.b bVar : bVarArr) {
                int spanStart = editable.getSpanStart(bVar);
                int spanEnd = editable.getSpanEnd(bVar);
                if ((z7 && spanStart == selectionStart) || ((!z7 && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(InputConnection inputConnection, Editable editable, int i8, int i9, boolean z7) {
        int max;
        int min;
        if (editable != null && inputConnection != null && i8 >= 0 && i9 >= 0) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (f(selectionStart, selectionEnd)) {
                return false;
            }
            if (z7) {
                max = a.a(editable, selectionStart, Math.max(i8, 0));
                min = a.b(editable, selectionEnd, Math.max(i9, 0));
                if (max == -1 || min == -1) {
                    return false;
                }
            } else {
                max = Math.max(selectionStart - i8, 0);
                min = Math.min(selectionEnd + i9, editable.length());
            }
            n1.b[] bVarArr = (n1.b[]) editable.getSpans(max, min, n1.b.class);
            if (bVarArr != null && bVarArr.length > 0) {
                for (n1.b bVar : bVarArr) {
                    int spanStart = editable.getSpanStart(bVar);
                    int spanEnd = editable.getSpanEnd(bVar);
                    max = Math.min(spanStart, max);
                    min = Math.max(spanEnd, min);
                }
                int max2 = Math.max(max, 0);
                int min2 = Math.min(min, editable.length());
                inputConnection.beginBatchEdit();
                editable.delete(max2, min2);
                inputConnection.endBatchEdit();
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(Editable editable, int i8, KeyEvent keyEvent) {
        if (i8 != 67 ? i8 != 112 ? false : b(editable, keyEvent, true) : b(editable, keyEvent, false)) {
            MetaKeyKeyListener.adjustMetaAfterKeypress(editable);
            return true;
        }
        return false;
    }

    private boolean e(CharSequence charSequence, int i8, int i9, g gVar) {
        if (gVar.d() == 0) {
            gVar.k(this.f3316c.a(charSequence, i8, i9, gVar.h()));
        }
        return gVar.d() == 2;
    }

    private static boolean f(int i8, int i9) {
        return i8 == -1 || i9 == -1 || i8 != i9;
    }

    private static boolean g(KeyEvent keyEvent) {
        return !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0122, code lost:
        ((n1.c) r11).d();
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x009f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0046 A[Catch: all -> 0x0129, TryCatch #0 {all -> 0x0129, blocks: (B:7:0x000e, B:10:0x0013, B:12:0x0017, B:14:0x0024, B:19:0x0037, B:21:0x003f, B:23:0x0042, B:25:0x0046, B:27:0x0052, B:28:0x0055, B:30:0x0062, B:36:0x0071, B:37:0x007d, B:41:0x0098, B:49:0x00a8, B:52:0x00b4, B:53:0x00be, B:54:0x00c8, B:56:0x00cf, B:57:0x00d4, B:59:0x00df, B:61:0x00e6, B:65:0x00f0, B:68:0x00fc, B:69:0x0102, B:71:0x010b, B:16:0x002c), top: B:85:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fc A[Catch: all -> 0x0129, TryCatch #0 {all -> 0x0129, blocks: (B:7:0x000e, B:10:0x0013, B:12:0x0017, B:14:0x0024, B:19:0x0037, B:21:0x003f, B:23:0x0042, B:25:0x0046, B:27:0x0052, B:28:0x0055, B:30:0x0062, B:36:0x0071, B:37:0x007d, B:41:0x0098, B:49:0x00a8, B:52:0x00b4, B:53:0x00be, B:54:0x00c8, B:56:0x00cf, B:57:0x00d4, B:59:0x00df, B:61:0x00e6, B:65:0x00f0, B:68:0x00fc, B:69:0x0102, B:71:0x010b, B:16:0x002c), top: B:85:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x010b A[Catch: all -> 0x0129, TRY_LEAVE, TryCatch #0 {all -> 0x0129, blocks: (B:7:0x000e, B:10:0x0013, B:12:0x0017, B:14:0x0024, B:19:0x0037, B:21:0x003f, B:23:0x0042, B:25:0x0046, B:27:0x0052, B:28:0x0055, B:30:0x0062, B:36:0x0071, B:37:0x007d, B:41:0x0098, B:49:0x00a8, B:52:0x00b4, B:53:0x00be, B:54:0x00c8, B:56:0x00cf, B:57:0x00d4, B:59:0x00df, B:61:0x00e6, B:65:0x00f0, B:68:0x00fc, B:69:0x0102, B:71:0x010b, B:16:0x002c), top: B:85:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00d4 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.CharSequence h(java.lang.CharSequence r11, int r12, int r13, int r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.h.h(java.lang.CharSequence, int, int, int, boolean):java.lang.CharSequence");
    }
}
