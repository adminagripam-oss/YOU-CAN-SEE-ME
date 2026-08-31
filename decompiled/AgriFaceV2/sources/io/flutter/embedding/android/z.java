package io.flutter.embedding.android;

import android.view.KeyEvent;
import i6.b;
import io.flutter.embedding.android.a0;
import io.flutter.embedding.android.b0;
import io.flutter.embedding.android.v;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class z implements a0.d {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f7709a;

    /* renamed from: b  reason: collision with root package name */
    private final HashMap f7710b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final HashMap f7711c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private final a0.b f7712d = new a0.b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7713a;

        static {
            int[] iArr = new int[v.b.values().length];
            f7713a = iArr;
            try {
                iArr[v.b.kDown.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7713a[v.b.kUp.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7713a[v.b.kRepeat.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public z(i6.b bVar) {
        b0.e[] a8;
        this.f7709a = bVar;
        for (b0.e eVar : b0.a()) {
            this.f7711c.put(Long.valueOf(eVar.f7580c), eVar);
        }
    }

    private static v.b e(KeyEvent keyEvent) {
        boolean z7 = keyEvent.getRepeatCount() > 0;
        int action = keyEvent.getAction();
        if (action == 0) {
            return z7 ? v.b.kRepeat : v.b.kDown;
        } else if (action == 1) {
            return v.b.kUp;
        } else {
            throw new AssertionError("Unexpected event type");
        }
    }

    private Long f(KeyEvent keyEvent) {
        Long l8 = (Long) b0.f7572b.get(Long.valueOf(keyEvent.getKeyCode()));
        return l8 != null ? l8 : Long.valueOf(j(keyEvent.getKeyCode(), 73014444032L));
    }

    private Long g(KeyEvent keyEvent) {
        int scanCode;
        long scanCode2 = keyEvent.getScanCode();
        if (scanCode2 == 0) {
            scanCode = keyEvent.getKeyCode();
        } else {
            Long l8 = (Long) b0.f7571a.get(Long.valueOf(scanCode2));
            if (l8 != null) {
                return l8;
            }
            scanCode = keyEvent.getScanCode();
        }
        return Long.valueOf(j(scanCode, 73014444032L));
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0144 A[LOOP:2: B:69:0x013e->B:71:0x0144, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean i(android.view.KeyEvent r18, io.flutter.embedding.android.a0.d.a r19) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.android.z.i(android.view.KeyEvent, io.flutter.embedding.android.a0$d$a):boolean");
    }

    private static long j(long j8, long j9) {
        return (j8 & 4294967295L) | j9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void k(a0.d.a aVar, ByteBuffer byteBuffer) {
        Boolean bool = Boolean.FALSE;
        if (byteBuffer != null) {
            byteBuffer.rewind();
            if (byteBuffer.capacity() != 0) {
                bool = Boolean.valueOf(byteBuffer.get() != 0);
            }
        } else {
            z5.b.g("KeyEmbedderResponder", "A null reply was received when sending a key event to the framework.");
        }
        aVar.a(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(b0.c cVar, long j8, KeyEvent keyEvent) {
        q(false, Long.valueOf(cVar.f7575b), Long.valueOf(j8), keyEvent.getEventTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(b0.c cVar, KeyEvent keyEvent) {
        q(false, Long.valueOf(cVar.f7575b), Long.valueOf(cVar.f7574a), keyEvent.getEventTime());
    }

    private void n(v vVar, final a0.d.a aVar) {
        this.f7709a.h("flutter/keydata", vVar.a(), aVar == null ? null : new b.InterfaceC0103b() { // from class: io.flutter.embedding.android.w
            @Override // i6.b.InterfaceC0103b
            public final void a(ByteBuffer byteBuffer) {
                z.k(a0.d.a.this, byteBuffer);
            }
        });
    }

    private void q(boolean z7, Long l8, Long l9, long j8) {
        v vVar = new v();
        vVar.f7682a = j8;
        vVar.f7683b = z7 ? v.b.kDown : v.b.kUp;
        vVar.f7685d = l8.longValue();
        vVar.f7684c = l9.longValue();
        vVar.f7688g = null;
        vVar.f7686e = true;
        vVar.f7687f = v.a.kKeyboard;
        if (l9.longValue() != 0 && l8.longValue() != 0) {
            if (!z7) {
                l8 = null;
            }
            r(l9, l8);
        }
        n(vVar, null);
    }

    @Override // io.flutter.embedding.android.a0.d
    public void a(KeyEvent keyEvent, a0.d.a aVar) {
        if (i(keyEvent, aVar)) {
            return;
        }
        q(true, 0L, 0L, 0L);
        aVar.a(true);
    }

    public Map h() {
        return Collections.unmodifiableMap(this.f7710b);
    }

    void o(b0.d dVar, boolean z7, long j8, final long j9, final KeyEvent keyEvent, ArrayList arrayList) {
        b0.c[] cVarArr = dVar.f7577b;
        boolean[] zArr = new boolean[cVarArr.length];
        Boolean[] boolArr = new Boolean[cVarArr.length];
        boolean z8 = false;
        int i8 = 0;
        while (true) {
            b0.c[] cVarArr2 = dVar.f7577b;
            boolean z9 = true;
            if (i8 >= cVarArr2.length) {
                break;
            }
            final b0.c cVar = cVarArr2[i8];
            boolean containsKey = this.f7710b.containsKey(Long.valueOf(cVar.f7574a));
            zArr[i8] = containsKey;
            if (cVar.f7575b == j8) {
                int i9 = a.f7713a[e(keyEvent).ordinal()];
                if (i9 != 1) {
                    if (i9 == 2) {
                        boolArr[i8] = Boolean.valueOf(zArr[i8]);
                    } else if (i9 == 3) {
                        if (!z7) {
                            arrayList.add(new Runnable() { // from class: io.flutter.embedding.android.y
                                @Override // java.lang.Runnable
                                public final void run() {
                                    z.this.m(cVar, keyEvent);
                                }
                            });
                        }
                        boolArr[i8] = Boolean.valueOf(zArr[i8]);
                    }
                    i8++;
                } else {
                    boolArr[i8] = Boolean.FALSE;
                    if (!z7) {
                        arrayList.add(new Runnable() { // from class: io.flutter.embedding.android.x
                            @Override // java.lang.Runnable
                            public final void run() {
                                z.this.l(cVar, j9, keyEvent);
                            }
                        });
                    }
                }
            } else if (!z8 && !containsKey) {
                z9 = false;
            }
            z8 = z9;
            i8++;
        }
        if (z7) {
            for (int i10 = 0; i10 < dVar.f7577b.length; i10++) {
                if (boolArr[i10] == null) {
                    if (z8) {
                        boolArr[i10] = Boolean.valueOf(zArr[i10]);
                    } else {
                        boolArr[i10] = Boolean.TRUE;
                        z8 = true;
                    }
                }
            }
            if (!z8) {
                boolArr[0] = Boolean.TRUE;
            }
        } else {
            for (int i11 = 0; i11 < dVar.f7577b.length; i11++) {
                if (boolArr[i11] == null) {
                    boolArr[i11] = Boolean.FALSE;
                }
            }
        }
        for (int i12 = 0; i12 < dVar.f7577b.length; i12++) {
            if (zArr[i12] != boolArr[i12].booleanValue()) {
                b0.c cVar2 = dVar.f7577b[i12];
                q(boolArr[i12].booleanValue(), Long.valueOf(cVar2.f7575b), Long.valueOf(cVar2.f7574a), keyEvent.getEventTime());
            }
        }
    }

    void p(b0.e eVar, boolean z7, long j8, KeyEvent keyEvent) {
        if (eVar.f7580c == j8 || eVar.f7581d == z7) {
            return;
        }
        boolean z8 = !this.f7710b.containsKey(Long.valueOf(eVar.f7579b));
        if (z8) {
            eVar.f7581d = !eVar.f7581d;
        }
        q(z8, Long.valueOf(eVar.f7580c), Long.valueOf(eVar.f7579b), keyEvent.getEventTime());
        if (!z8) {
            eVar.f7581d = !eVar.f7581d;
        }
        q(!z8, Long.valueOf(eVar.f7580c), Long.valueOf(eVar.f7579b), keyEvent.getEventTime());
    }

    void r(Long l8, Long l9) {
        if (l9 != null) {
            if (((Long) this.f7710b.put(l8, l9)) != null) {
                throw new AssertionError("The key was not empty");
            }
        } else if (((Long) this.f7710b.remove(l8)) == null) {
            throw new AssertionError("The key was empty");
        }
    }
}
