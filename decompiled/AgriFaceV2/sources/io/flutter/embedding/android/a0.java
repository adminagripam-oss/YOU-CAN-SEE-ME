package io.flutter.embedding.android;

import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import h6.j;
import io.flutter.plugin.editing.h;
import java.util.HashSet;
import java.util.Map;
/* loaded from: classes.dex */
public class a0 implements h.a, j.b {

    /* renamed from: a  reason: collision with root package name */
    protected final d[] f7561a;

    /* renamed from: b  reason: collision with root package name */
    private final HashSet f7562b = new HashSet();

    /* renamed from: c  reason: collision with root package name */
    private final e f7563c;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private int f7564a = 0;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Character a(int i8) {
            char c8 = (char) i8;
            if ((Integer.MIN_VALUE & i8) != 0) {
                int i9 = i8 & Integer.MAX_VALUE;
                int i10 = this.f7564a;
                if (i10 != 0) {
                    i9 = KeyCharacterMap.getDeadChar(i10, i9);
                }
                this.f7564a = i9;
            } else {
                int i11 = this.f7564a;
                if (i11 != 0) {
                    int deadChar = KeyCharacterMap.getDeadChar(i11, i8);
                    if (deadChar > 0) {
                        c8 = (char) deadChar;
                    }
                    this.f7564a = 0;
                }
            }
            return Character.valueOf(c8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c {

        /* renamed from: a  reason: collision with root package name */
        final KeyEvent f7565a;

        /* renamed from: b  reason: collision with root package name */
        int f7566b;

        /* renamed from: c  reason: collision with root package name */
        boolean f7567c = false;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class a implements d.a {

            /* renamed from: a  reason: collision with root package name */
            boolean f7569a;

            private a() {
                this.f7569a = false;
            }

            @Override // io.flutter.embedding.android.a0.d.a
            public void a(boolean z7) {
                if (this.f7569a) {
                    throw new IllegalStateException("The onKeyEventHandledCallback should be called exactly once.");
                }
                this.f7569a = true;
                c cVar = c.this;
                int i8 = cVar.f7566b - 1;
                cVar.f7566b = i8;
                boolean z8 = z7 | cVar.f7567c;
                cVar.f7567c = z8;
                if (i8 != 0 || z8) {
                    return;
                }
                a0.this.e(cVar.f7565a);
            }
        }

        c(KeyEvent keyEvent) {
            this.f7566b = a0.this.f7561a.length;
            this.f7565a = keyEvent;
        }

        public d.a a() {
            return new a();
        }
    }

    /* loaded from: classes.dex */
    public interface d {

        /* loaded from: classes.dex */
        public interface a {
            void a(boolean z7);
        }

        void a(KeyEvent keyEvent, a aVar);
    }

    /* loaded from: classes.dex */
    public interface e {
        void a(KeyEvent keyEvent);

        boolean c(KeyEvent keyEvent);

        i6.b getBinaryMessenger();
    }

    public a0(e eVar) {
        this.f7563c = eVar;
        this.f7561a = new d[]{new z(eVar.getBinaryMessenger()), new u(new h6.i(eVar.getBinaryMessenger()))};
        new h6.j(eVar.getBinaryMessenger()).b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(KeyEvent keyEvent) {
        e eVar = this.f7563c;
        if (eVar == null || eVar.c(keyEvent)) {
            return;
        }
        this.f7562b.add(keyEvent);
        this.f7563c.a(keyEvent);
        if (this.f7562b.remove(keyEvent)) {
            z5.b.g("KeyboardManager", "A redispatched key event was consumed before reaching KeyboardManager");
        }
    }

    @Override // h6.j.b
    public Map a() {
        return ((z) this.f7561a[0]).h();
    }

    @Override // io.flutter.plugin.editing.h.a
    public boolean b(KeyEvent keyEvent) {
        if (this.f7562b.remove(keyEvent)) {
            return false;
        }
        if (this.f7561a.length <= 0) {
            e(keyEvent);
            return true;
        }
        c cVar = new c(keyEvent);
        for (d dVar : this.f7561a) {
            dVar.a(keyEvent, cVar.a());
        }
        return true;
    }

    public void d() {
        int size = this.f7562b.size();
        if (size > 0) {
            z5.b.g("KeyboardManager", "A KeyboardManager was destroyed with " + String.valueOf(size) + " unhandled redispatch event(s).");
        }
    }
}
