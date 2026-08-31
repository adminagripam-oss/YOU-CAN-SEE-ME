package androidx.profileinstaller;

import android.content.res.AssetManager;
import android.os.Build;
import androidx.profileinstaller.i;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final AssetManager f3868a;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f3869b;

    /* renamed from: c  reason: collision with root package name */
    private final i.c f3870c;

    /* renamed from: e  reason: collision with root package name */
    private final File f3872e;

    /* renamed from: f  reason: collision with root package name */
    private final String f3873f;

    /* renamed from: g  reason: collision with root package name */
    private final String f3874g;

    /* renamed from: h  reason: collision with root package name */
    private final String f3875h;

    /* renamed from: j  reason: collision with root package name */
    private d[] f3877j;

    /* renamed from: k  reason: collision with root package name */
    private byte[] f3878k;

    /* renamed from: i  reason: collision with root package name */
    private boolean f3876i = false;

    /* renamed from: d  reason: collision with root package name */
    private final byte[] f3871d = d();

    public c(AssetManager assetManager, Executor executor, i.c cVar, String str, String str2, String str3, File file) {
        this.f3868a = assetManager;
        this.f3869b = executor;
        this.f3870c = cVar;
        this.f3873f = str;
        this.f3874g = str2;
        this.f3875h = str3;
        this.f3872e = file;
    }

    private c b(d[] dVarArr, byte[] bArr) {
        i.c cVar;
        int i8;
        InputStream h8;
        try {
            h8 = h(this.f3868a, this.f3875h);
        } catch (FileNotFoundException e8) {
            e = e8;
            cVar = this.f3870c;
            i8 = 9;
            cVar.b(i8, e);
            return null;
        } catch (IOException e9) {
            e = e9;
            cVar = this.f3870c;
            i8 = 7;
            cVar.b(i8, e);
            return null;
        } catch (IllegalStateException e10) {
            e = e10;
            this.f3877j = null;
            cVar = this.f3870c;
            i8 = 8;
            cVar.b(i8, e);
            return null;
        }
        if (h8 != null) {
            this.f3877j = n.q(h8, n.o(h8, n.f3906b), bArr, dVarArr);
            h8.close();
            return this;
        }
        if (h8 != null) {
            h8.close();
        }
        return null;
    }

    private void c() {
        if (!this.f3876i) {
            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
        }
    }

    private static byte[] d() {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 > 34) {
            return null;
        }
        switch (i8) {
            case 26:
                return p.f3920d;
            case 27:
                return p.f3919c;
            case 28:
            case 29:
            case 30:
                return p.f3918b;
            case 31:
            case 32:
            case 33:
            case 34:
                return p.f3917a;
            default:
                return null;
        }
    }

    private InputStream f(AssetManager assetManager) {
        i.c cVar;
        int i8;
        try {
            return h(assetManager, this.f3874g);
        } catch (FileNotFoundException e8) {
            e = e8;
            cVar = this.f3870c;
            i8 = 6;
            cVar.b(i8, e);
            return null;
        } catch (IOException e9) {
            e = e9;
            cVar = this.f3870c;
            i8 = 7;
            cVar.b(i8, e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(int i8, Object obj) {
        this.f3870c.b(i8, obj);
    }

    private InputStream h(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e8) {
            String message = e8.getMessage();
            if (message != null && message.contains("compressed")) {
                this.f3870c.a(5, null);
            }
            return null;
        }
    }

    private d[] j(InputStream inputStream) {
        try {
        } catch (IOException e8) {
            this.f3870c.b(7, e8);
        }
        try {
            try {
                d[] w7 = n.w(inputStream, n.o(inputStream, n.f3905a), this.f3873f);
                try {
                    inputStream.close();
                    return w7;
                } catch (IOException e9) {
                    this.f3870c.b(7, e9);
                    return w7;
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e10) {
                    this.f3870c.b(7, e10);
                }
                throw th;
            }
        } catch (IOException e11) {
            this.f3870c.b(7, e11);
            inputStream.close();
            return null;
        } catch (IllegalStateException e12) {
            this.f3870c.b(8, e12);
            inputStream.close();
            return null;
        }
    }

    private static boolean k() {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 > 34) {
            return false;
        }
        switch (i8) {
            case 31:
            case 32:
            case 33:
            case 34:
                return true;
            default:
                return false;
        }
    }

    private void l(final int i8, final Object obj) {
        this.f3869b.execute(new Runnable() { // from class: androidx.profileinstaller.b
            @Override // java.lang.Runnable
            public final void run() {
                c.this.g(i8, obj);
            }
        });
    }

    public boolean e() {
        if (this.f3871d == null) {
            l(3, Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        if (!this.f3872e.exists()) {
            try {
                this.f3872e.createNewFile();
            } catch (IOException unused) {
                l(4, null);
                return false;
            }
        } else if (!this.f3872e.canWrite()) {
            l(4, null);
            return false;
        }
        this.f3876i = true;
        return true;
    }

    public c i() {
        c b8;
        c();
        if (this.f3871d == null) {
            return this;
        }
        InputStream f8 = f(this.f3868a);
        if (f8 != null) {
            this.f3877j = j(f8);
        }
        d[] dVarArr = this.f3877j;
        return (dVarArr == null || !k() || (b8 = b(dVarArr, this.f3871d)) == null) ? this : b8;
    }

    public c m() {
        i.c cVar;
        int i8;
        ByteArrayOutputStream byteArrayOutputStream;
        d[] dVarArr = this.f3877j;
        byte[] bArr = this.f3871d;
        if (dVarArr != null && bArr != null) {
            c();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    n.E(byteArrayOutputStream, bArr);
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e8) {
                e = e8;
                cVar = this.f3870c;
                i8 = 7;
                cVar.b(i8, e);
                this.f3877j = null;
                return this;
            } catch (IllegalStateException e9) {
                e = e9;
                cVar = this.f3870c;
                i8 = 8;
                cVar.b(i8, e);
                this.f3877j = null;
                return this;
            }
            if (!n.B(byteArrayOutputStream, bArr, dVarArr)) {
                this.f3870c.b(5, null);
                this.f3877j = null;
                byteArrayOutputStream.close();
                return this;
            }
            this.f3878k = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            this.f3877j = null;
        }
        return this;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [byte[], androidx.profileinstaller.d[]] */
    public boolean n() {
        byte[] bArr = this.f3878k;
        if (bArr == null) {
            return false;
        }
        c();
        try {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.f3872e);
                    e.l(byteArrayInputStream, fileOutputStream);
                    l(1, null);
                    fileOutputStream.close();
                    byteArrayInputStream.close();
                    return true;
                } catch (Throwable th) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                l(6, e8);
                return false;
            } catch (IOException e9) {
                l(7, e9);
                return false;
            }
        } finally {
            this.f3878k = null;
            this.f3877j = null;
        }
    }
}
