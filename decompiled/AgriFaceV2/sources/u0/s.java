package u0;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordingConfiguration;
import android.os.Build;
import androidx.camera.video.internal.compat.quirk.AudioTimestampFramePositionIncorrectQuirk;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import u0.p;
import y.f1;
/* loaded from: classes.dex */
public class s implements p {

    /* renamed from: m  reason: collision with root package name */
    private static final long f12001m = TimeUnit.MILLISECONDS.toNanos(500);

    /* renamed from: a  reason: collision with root package name */
    private AudioRecord f12002a;

    /* renamed from: b  reason: collision with root package name */
    private final u0.a f12003b;

    /* renamed from: f  reason: collision with root package name */
    private final int f12007f;

    /* renamed from: g  reason: collision with root package name */
    private final int f12008g;

    /* renamed from: h  reason: collision with root package name */
    private p.a f12009h;

    /* renamed from: i  reason: collision with root package name */
    private Executor f12010i;

    /* renamed from: j  reason: collision with root package name */
    private long f12011j;

    /* renamed from: k  reason: collision with root package name */
    private AudioManager.AudioRecordingCallback f12012k;

    /* renamed from: c  reason: collision with root package name */
    private final AtomicBoolean f12004c = new AtomicBoolean(false);

    /* renamed from: d  reason: collision with root package name */
    private final AtomicBoolean f12005d = new AtomicBoolean(false);

    /* renamed from: e  reason: collision with root package name */
    private final AtomicReference f12006e = new AtomicReference(null);

    /* renamed from: l  reason: collision with root package name */
    private boolean f12013l = false;

    /* loaded from: classes.dex */
    class a extends AudioManager.AudioRecordingCallback {
        a() {
        }

        @Override // android.media.AudioManager.AudioRecordingCallback
        public void onRecordingConfigChanged(List list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                AudioRecordingConfiguration audioRecordingConfiguration = (AudioRecordingConfiguration) it.next();
                if (v0.b.a(audioRecordingConfiguration) == s.this.f12002a.getAudioSessionId()) {
                    s.this.n(v0.c.b(audioRecordingConfiguration));
                    return;
                }
            }
        }
    }

    public s(u0.a aVar, Context context) {
        if (!l(aVar.f(), aVar.e(), aVar.b())) {
            throw new UnsupportedOperationException(String.format("The combination of sample rate %d, channel count %d and audio format %d is not supported.", Integer.valueOf(aVar.f()), Integer.valueOf(aVar.e()), Integer.valueOf(aVar.b())));
        }
        this.f12003b = aVar;
        this.f12008g = aVar.d();
        int j8 = j(aVar.f(), aVar.e(), aVar.b());
        g1.e.l(j8 > 0);
        int i8 = j8 * 2;
        this.f12007f = i8;
        AudioRecord h8 = h(i8, aVar, context);
        this.f12002a = h8;
        e(h8);
    }

    private static void e(AudioRecord audioRecord) {
        if (audioRecord.getState() == 1) {
            return;
        }
        audioRecord.release();
        throw new p.b("Unable to initialize AudioRecord");
    }

    private void f() {
        g1.e.m(!this.f12004c.get(), "AudioStream has been released.");
    }

    private void g() {
        g1.e.m(this.f12005d.get(), "AudioStream has not been started.");
    }

    private static AudioRecord h(int i8, u0.a aVar, Context context) {
        int i9 = Build.VERSION.SDK_INT;
        AudioFormat build = new AudioFormat.Builder().setSampleRate(aVar.f()).setChannelMask(t.b(aVar.e())).setEncoding(aVar.b()).build();
        AudioRecord.Builder b8 = v0.a.b();
        if (i9 >= 31 && context != null) {
            v0.d.a(b8, context);
        }
        v0.a.d(b8, aVar.c());
        v0.a.c(b8, build);
        v0.a.e(b8, i8);
        return v0.a.a(b8);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long i() {
        /*
            r9 = this;
            boolean r0 = r9.f12013l
            r1 = -1
            if (r0 != 0) goto L3b
            android.media.AudioTimestamp r0 = new android.media.AudioTimestamp
            r0.<init>()
            android.media.AudioRecord r3 = r9.f12002a
            r4 = 0
            int r3 = v0.b.b(r3, r0, r4)
            if (r3 != 0) goto L34
            u0.a r3 = r9.f12003b
            int r3 = r3.f()
            long r4 = r9.f12011j
            long r3 = u0.t.c(r3, r4, r0)
            long r5 = java.lang.System.nanoTime()
            long r5 = r3 - r5
            long r5 = java.lang.Math.abs(r5)
            long r7 = u0.s.f12001m
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L3c
            r0 = 1
            r9.f12013l = r0
            goto L3b
        L34:
            java.lang.String r0 = "AudioStreamImpl"
            java.lang.String r3 = "Unable to get audio timestamp"
            y.f1.l(r0, r3)
        L3b:
            r3 = r1
        L3c:
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L44
            long r3 = java.lang.System.nanoTime()
        L44:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.s.i():long");
    }

    private static int j(int i8, int i9, int i10) {
        return AudioRecord.getMinBufferSize(i8, t.a(i9), i10);
    }

    private static boolean k() {
        return androidx.camera.video.internal.compat.quirk.a.b(AudioTimestampFramePositionIncorrectQuirk.class) != null;
    }

    public static boolean l(int i8, int i9, int i10) {
        return i8 > 0 && i9 > 0 && j(i8, i9, i10) > 0;
    }

    @Override // u0.p
    public p.c a(ByteBuffer byteBuffer) {
        long j8;
        f();
        g();
        int read = this.f12002a.read(byteBuffer, this.f12007f);
        if (read > 0) {
            byteBuffer.limit(read);
            j8 = i();
            this.f12011j += t.g(read, this.f12008g);
        } else {
            j8 = 0;
        }
        return p.c.c(read, j8);
    }

    @Override // u0.p
    public void b(p.a aVar, Executor executor) {
        boolean z7 = true;
        g1.e.m(!this.f12005d.get(), "AudioStream can not be started when setCallback.");
        f();
        if (aVar != null && executor == null) {
            z7 = false;
        }
        g1.e.b(z7, "executor can't be null with non-null callback.");
        this.f12009h = aVar;
        this.f12010i = executor;
        if (Build.VERSION.SDK_INT >= 29) {
            AudioManager.AudioRecordingCallback audioRecordingCallback = this.f12012k;
            if (audioRecordingCallback != null) {
                v0.c.d(this.f12002a, audioRecordingCallback);
            }
            if (aVar == null) {
                return;
            }
            if (this.f12012k == null) {
                this.f12012k = new a();
            }
            v0.c.c(this.f12002a, executor, this.f12012k);
        }
    }

    void n(final boolean z7) {
        Executor executor = this.f12010i;
        final p.a aVar = this.f12009h;
        if (executor == null || aVar == null || Objects.equals(this.f12006e.getAndSet(Boolean.valueOf(z7)), Boolean.valueOf(z7))) {
            return;
        }
        executor.execute(new Runnable() { // from class: u0.r
            @Override // java.lang.Runnable
            public final void run() {
                p.a.this.b(z7);
            }
        });
    }

    @Override // u0.p
    public void release() {
        AudioManager.AudioRecordingCallback audioRecordingCallback;
        if (this.f12004c.getAndSet(true)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29 && (audioRecordingCallback = this.f12012k) != null) {
            v0.c.d(this.f12002a, audioRecordingCallback);
        }
        this.f12002a.release();
    }

    @Override // u0.p
    public void start() {
        f();
        boolean z7 = true;
        if (this.f12005d.getAndSet(true)) {
            return;
        }
        if (k()) {
            e(this.f12002a);
        }
        this.f12002a.startRecording();
        boolean z8 = false;
        if (this.f12002a.getRecordingState() != 3) {
            this.f12005d.set(false);
            throw new p.b("Unable to start AudioRecord with state: " + this.f12002a.getRecordingState());
        }
        this.f12011j = 0L;
        this.f12013l = false;
        this.f12006e.set(null);
        if (Build.VERSION.SDK_INT >= 29) {
            AudioRecordingConfiguration a8 = v0.c.a(this.f12002a);
            if (a8 == null || !v0.c.b(a8)) {
                z7 = false;
            }
            z8 = z7;
        }
        n(z8);
    }

    @Override // u0.p
    public void stop() {
        f();
        if (this.f12005d.getAndSet(false)) {
            this.f12002a.stop();
            if (this.f12002a.getRecordingState() != 1) {
                f1.l("AudioStreamImpl", "Failed to stop AudioRecord with state: " + this.f12002a.getRecordingState());
            }
            if (k()) {
                this.f12002a.release();
                this.f12002a = h(this.f12007f, this.f12003b, null);
            }
        }
    }
}
