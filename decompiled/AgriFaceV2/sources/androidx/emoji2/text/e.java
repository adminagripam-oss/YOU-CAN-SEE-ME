package androidx.emoji2.text;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes.dex */
public class e {

    /* renamed from: n  reason: collision with root package name */
    private static final Object f3275n = new Object();

    /* renamed from: o  reason: collision with root package name */
    private static final Object f3276o = new Object();

    /* renamed from: p  reason: collision with root package name */
    private static volatile e f3277p;

    /* renamed from: b  reason: collision with root package name */
    private final Set f3279b;

    /* renamed from: e  reason: collision with root package name */
    private final b f3282e;

    /* renamed from: f  reason: collision with root package name */
    final g f3283f;

    /* renamed from: g  reason: collision with root package name */
    final boolean f3284g;

    /* renamed from: h  reason: collision with root package name */
    final boolean f3285h;

    /* renamed from: i  reason: collision with root package name */
    final int[] f3286i;

    /* renamed from: j  reason: collision with root package name */
    private final boolean f3287j;

    /* renamed from: k  reason: collision with root package name */
    private final int f3288k;

    /* renamed from: l  reason: collision with root package name */
    private final int f3289l;

    /* renamed from: m  reason: collision with root package name */
    private final d f3290m;

    /* renamed from: a  reason: collision with root package name */
    private final ReadWriteLock f3278a = new ReentrantReadWriteLock();

    /* renamed from: c  reason: collision with root package name */
    private volatile int f3280c = 3;

    /* renamed from: d  reason: collision with root package name */
    private final Handler f3281d = new Handler(Looper.getMainLooper());

    /* loaded from: classes.dex */
    private static final class a extends b {

        /* renamed from: b  reason: collision with root package name */
        private volatile androidx.emoji2.text.h f3291b;

        /* renamed from: c  reason: collision with root package name */
        private volatile l f3292c;

        /* renamed from: androidx.emoji2.text.e$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0036a extends h {
            C0036a() {
            }

            @Override // androidx.emoji2.text.e.h
            public void a(Throwable th) {
                a.this.f3294a.m(th);
            }

            @Override // androidx.emoji2.text.e.h
            public void b(l lVar) {
                a.this.d(lVar);
            }
        }

        a(e eVar) {
            super(eVar);
        }

        @Override // androidx.emoji2.text.e.b
        void a() {
            try {
                this.f3294a.f3283f.a(new C0036a());
            } catch (Throwable th) {
                this.f3294a.m(th);
            }
        }

        @Override // androidx.emoji2.text.e.b
        CharSequence b(CharSequence charSequence, int i8, int i9, int i10, boolean z7) {
            return this.f3291b.h(charSequence, i8, i9, i10, z7);
        }

        @Override // androidx.emoji2.text.e.b
        void c(EditorInfo editorInfo) {
            editorInfo.extras.putInt("android.support.text.emoji.emojiCompat_metadataVersion", this.f3292c.e());
            editorInfo.extras.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", this.f3294a.f3284g);
        }

        void d(l lVar) {
            if (lVar == null) {
                this.f3294a.m(new IllegalArgumentException("metadataRepo cannot be null"));
                return;
            }
            this.f3292c = lVar;
            l lVar2 = this.f3292c;
            i iVar = new i();
            d dVar = this.f3294a.f3290m;
            e eVar = this.f3294a;
            this.f3291b = new androidx.emoji2.text.h(lVar2, iVar, dVar, eVar.f3285h, eVar.f3286i);
            this.f3294a.n();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        final e f3294a;

        b(e eVar) {
            this.f3294a = eVar;
        }

        abstract void a();

        abstract CharSequence b(CharSequence charSequence, int i8, int i9, int i10, boolean z7);

        abstract void c(EditorInfo editorInfo);
    }

    /* loaded from: classes.dex */
    public static abstract class c {

        /* renamed from: a  reason: collision with root package name */
        final g f3295a;

        /* renamed from: b  reason: collision with root package name */
        boolean f3296b;

        /* renamed from: c  reason: collision with root package name */
        boolean f3297c;

        /* renamed from: d  reason: collision with root package name */
        int[] f3298d;

        /* renamed from: e  reason: collision with root package name */
        Set f3299e;

        /* renamed from: f  reason: collision with root package name */
        boolean f3300f;

        /* renamed from: g  reason: collision with root package name */
        int f3301g = -16711936;

        /* renamed from: h  reason: collision with root package name */
        int f3302h = 0;

        /* renamed from: i  reason: collision with root package name */
        d f3303i = new androidx.emoji2.text.d();

        /* JADX INFO: Access modifiers changed from: protected */
        public c(g gVar) {
            g1.e.k(gVar, "metadataLoader cannot be null.");
            this.f3295a = gVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final g a() {
            return this.f3295a;
        }

        public c b(int i8) {
            this.f3302h = i8;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        boolean a(CharSequence charSequence, int i8, int i9, int i10);
    }

    /* renamed from: androidx.emoji2.text.e$e  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0037e {
        public void a(Throwable th) {
        }

        public void b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private final List f3304e;

        /* renamed from: f  reason: collision with root package name */
        private final Throwable f3305f;

        /* renamed from: g  reason: collision with root package name */
        private final int f3306g;

        f(AbstractC0037e abstractC0037e, int i8) {
            this(Arrays.asList((AbstractC0037e) g1.e.k(abstractC0037e, "initCallback cannot be null")), i8, null);
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.f3304e.size();
            int i8 = 0;
            if (this.f3306g != 1) {
                while (i8 < size) {
                    ((AbstractC0037e) this.f3304e.get(i8)).a(this.f3305f);
                    i8++;
                }
                return;
            }
            while (i8 < size) {
                ((AbstractC0037e) this.f3304e.get(i8)).b();
                i8++;
            }
        }

        f(Collection collection, int i8) {
            this(collection, i8, null);
        }

        f(Collection collection, int i8, Throwable th) {
            g1.e.k(collection, "initCallbacks cannot be null");
            this.f3304e = new ArrayList(collection);
            this.f3306g = i8;
            this.f3305f = th;
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        void a(h hVar);
    }

    /* loaded from: classes.dex */
    public static abstract class h {
        public abstract void a(Throwable th);

        public abstract void b(l lVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class i {
        i() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public n1.b a(androidx.emoji2.text.g gVar) {
            return new n1.d(gVar);
        }
    }

    private e(c cVar) {
        this.f3284g = cVar.f3296b;
        this.f3285h = cVar.f3297c;
        this.f3286i = cVar.f3298d;
        this.f3287j = cVar.f3300f;
        this.f3288k = cVar.f3301g;
        this.f3283f = cVar.f3295a;
        this.f3289l = cVar.f3302h;
        this.f3290m = cVar.f3303i;
        b1.b bVar = new b1.b();
        this.f3279b = bVar;
        Set set = cVar.f3299e;
        if (set != null && !set.isEmpty()) {
            bVar.addAll(cVar.f3299e);
        }
        this.f3282e = new a(this);
        l();
    }

    public static e b() {
        e eVar;
        synchronized (f3275n) {
            eVar = f3277p;
            g1.e.m(eVar != null, "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
        }
        return eVar;
    }

    public static boolean e(InputConnection inputConnection, Editable editable, int i8, int i9, boolean z7) {
        return androidx.emoji2.text.h.c(inputConnection, editable, i8, i9, z7);
    }

    public static boolean f(Editable editable, int i8, KeyEvent keyEvent) {
        return androidx.emoji2.text.h.d(editable, i8, keyEvent);
    }

    public static e g(c cVar) {
        e eVar = f3277p;
        if (eVar == null) {
            synchronized (f3275n) {
                eVar = f3277p;
                if (eVar == null) {
                    eVar = new e(cVar);
                    f3277p = eVar;
                }
            }
        }
        return eVar;
    }

    public static boolean h() {
        return f3277p != null;
    }

    private boolean j() {
        return d() == 1;
    }

    private void l() {
        this.f3278a.writeLock().lock();
        try {
            if (this.f3289l == 0) {
                this.f3280c = 0;
            }
            this.f3278a.writeLock().unlock();
            if (d() == 0) {
                this.f3282e.a();
            }
        } catch (Throwable th) {
            this.f3278a.writeLock().unlock();
            throw th;
        }
    }

    public int c() {
        return this.f3288k;
    }

    public int d() {
        this.f3278a.readLock().lock();
        try {
            return this.f3280c;
        } finally {
            this.f3278a.readLock().unlock();
        }
    }

    public boolean i() {
        return this.f3287j;
    }

    public void k() {
        g1.e.m(this.f3289l == 1, "Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        if (j()) {
            return;
        }
        this.f3278a.writeLock().lock();
        try {
            if (this.f3280c == 0) {
                return;
            }
            this.f3280c = 0;
            this.f3278a.writeLock().unlock();
            this.f3282e.a();
        } finally {
            this.f3278a.writeLock().unlock();
        }
    }

    void m(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.f3278a.writeLock().lock();
        try {
            this.f3280c = 2;
            arrayList.addAll(this.f3279b);
            this.f3279b.clear();
            this.f3278a.writeLock().unlock();
            this.f3281d.post(new f(arrayList, this.f3280c, th));
        } catch (Throwable th2) {
            this.f3278a.writeLock().unlock();
            throw th2;
        }
    }

    void n() {
        ArrayList arrayList = new ArrayList();
        this.f3278a.writeLock().lock();
        try {
            this.f3280c = 1;
            arrayList.addAll(this.f3279b);
            this.f3279b.clear();
            this.f3278a.writeLock().unlock();
            this.f3281d.post(new f(arrayList, this.f3280c));
        } catch (Throwable th) {
            this.f3278a.writeLock().unlock();
            throw th;
        }
    }

    public CharSequence o(CharSequence charSequence) {
        return p(charSequence, 0, charSequence == null ? 0 : charSequence.length());
    }

    public CharSequence p(CharSequence charSequence, int i8, int i9) {
        return q(charSequence, i8, i9, Integer.MAX_VALUE);
    }

    public CharSequence q(CharSequence charSequence, int i8, int i9, int i10) {
        return r(charSequence, i8, i9, i10, 0);
    }

    public CharSequence r(CharSequence charSequence, int i8, int i9, int i10, int i11) {
        boolean z7;
        g1.e.m(j(), "Not initialized yet");
        g1.e.h(i8, "start cannot be negative");
        g1.e.h(i9, "end cannot be negative");
        g1.e.h(i10, "maxEmojiCount cannot be negative");
        g1.e.b(i8 <= i9, "start should be <= than end");
        if (charSequence == null) {
            return null;
        }
        g1.e.b(i8 <= charSequence.length(), "start should be < than charSequence length");
        g1.e.b(i9 <= charSequence.length(), "end should be < than charSequence length");
        if (charSequence.length() == 0 || i8 == i9) {
            return charSequence;
        }
        if (i11 != 1) {
            z7 = i11 != 2 ? this.f3284g : false;
        } else {
            z7 = true;
        }
        return this.f3282e.b(charSequence, i8, i9, i10, z7);
    }

    public void s(AbstractC0037e abstractC0037e) {
        g1.e.k(abstractC0037e, "initCallback cannot be null");
        this.f3278a.writeLock().lock();
        try {
            if (this.f3280c != 1 && this.f3280c != 2) {
                this.f3279b.add(abstractC0037e);
            }
            this.f3281d.post(new f(abstractC0037e, this.f3280c));
        } finally {
            this.f3278a.writeLock().unlock();
        }
    }

    public void t(AbstractC0037e abstractC0037e) {
        g1.e.k(abstractC0037e, "initCallback cannot be null");
        this.f3278a.writeLock().lock();
        try {
            this.f3279b.remove(abstractC0037e);
        } finally {
            this.f3278a.writeLock().unlock();
        }
    }

    public void u(EditorInfo editorInfo) {
        if (!j() || editorInfo == null) {
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        this.f3282e.c(editorInfo);
    }
}
