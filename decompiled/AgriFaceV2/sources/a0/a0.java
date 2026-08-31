package a0;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;
import y.u0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a0 implements l0.y {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static a c(l0.z zVar, u0.g gVar) {
            return new d(zVar, gVar);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract u0.g a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract l0.z b();
    }

    private static Uri b(File file, File file2) {
        if (file2.exists()) {
            file2.delete();
        }
        if (file.renameTo(file2)) {
            return Uri.fromFile(file2);
        }
        throw new y.v0(1, "Failed to overwrite the file: " + file2.getAbsolutePath(), null);
    }

    private static Uri c(File file, u0.g gVar) {
        Uri insert;
        ContentResolver a8 = gVar.a();
        Objects.requireNonNull(a8);
        ContentValues contentValues = gVar.b() != null ? new ContentValues(gVar.b()) : new ContentValues();
        l(contentValues, 1);
        Uri uri = null;
        try {
            try {
                insert = a8.insert(gVar.f(), contentValues);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e8) {
            e = e8;
        } catch (SecurityException e9) {
            e = e9;
        }
        try {
            if (insert != null) {
                e(file, insert, a8);
                n(insert, a8, 0);
                return insert;
            }
            throw new y.v0(1, "Failed to insert a MediaStore URI.", null);
        } catch (IOException e10) {
            e = e10;
            uri = insert;
            throw new y.v0(1, "Failed to write to MediaStore URI: " + uri, e);
        } catch (SecurityException e11) {
            e = e11;
            uri = insert;
            throw new y.v0(1, "Failed to write to MediaStore URI: " + uri, e);
        } catch (Throwable th2) {
            th = th2;
            uri = insert;
            if (uri != null) {
                n(uri, a8, 0);
            }
            throw th;
        }
    }

    private static void d(File file, OutputStream outputStream) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    fileInputStream.close();
                    return;
                }
                outputStream.write(bArr, 0, read);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static void e(File file, Uri uri, ContentResolver contentResolver) {
        OutputStream openOutputStream = contentResolver.openOutputStream(uri);
        try {
            if (openOutputStream != null) {
                d(file, openOutputStream);
                openOutputStream.close();
                return;
            }
            throw new FileNotFoundException(uri + " cannot be resolved.");
        } catch (Throwable th) {
            if (openOutputStream != null) {
                try {
                    openOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static File f(u0.g gVar) {
        try {
            File c8 = gVar.c();
            if (c8 != null) {
                String parent = c8.getParent();
                return new File(parent, "CameraX" + UUID.randomUUID().toString() + g(c8));
            }
            return File.createTempFile("CameraX", ".tmp");
        } catch (IOException e8) {
            throw new y.v0(1, "Failed to create temp file.", e8);
        }
    }

    private static String g(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf >= 0 ? name.substring(lastIndexOf) : "";
    }

    private static boolean h(u0.g gVar) {
        return gVar.c() != null;
    }

    private static boolean i(u0.g gVar) {
        return (gVar.f() == null || gVar.a() == null || gVar.b() == null) ? false : true;
    }

    private static boolean j(u0.g gVar) {
        return gVar.e() != null;
    }

    static Uri k(File file, u0.g gVar) {
        Uri uri = null;
        try {
            try {
                if (i(gVar)) {
                    uri = c(file, gVar);
                } else if (j(gVar)) {
                    OutputStream e8 = gVar.e();
                    Objects.requireNonNull(e8);
                    d(file, e8);
                } else if (h(gVar)) {
                    File c8 = gVar.c();
                    Objects.requireNonNull(c8);
                    uri = b(file, c8);
                }
                return uri;
            } catch (IOException unused) {
                throw new y.v0(1, "Failed to write to OutputStream.", null);
            }
        } finally {
            file.delete();
        }
    }

    private static void l(ContentValues contentValues, int i8) {
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("is_pending", Integer.valueOf(i8));
        }
    }

    private static void m(File file, d0.g gVar, u0.g gVar2, int i8) {
        try {
            d0.g h8 = d0.g.h(file);
            gVar.g(h8);
            if (h8.s() == 0 && i8 != 0) {
                h8.z(i8);
            }
            u0.d d8 = gVar2.d();
            if (d8.b()) {
                h8.l();
            }
            if (d8.c()) {
                h8.m();
            }
            if (d8.a() != null) {
                h8.b(d8.a());
            }
            h8.A();
        } catch (IOException e8) {
            throw new y.v0(1, "Failed to update Exif data", e8);
        }
    }

    private static void n(Uri uri, ContentResolver contentResolver, int i8) {
        if (Build.VERSION.SDK_INT >= 29) {
            ContentValues contentValues = new ContentValues();
            l(contentValues, i8);
            contentResolver.update(uri, contentValues, null, null);
        }
    }

    private static void o(File file, byte[] bArr) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr, 0, new j0.c().b(bArr));
            fileOutputStream.close();
        } catch (IOException e8) {
            throw new y.v0(1, "Failed to write to temp file", e8);
        }
    }

    @Override // l0.y
    /* renamed from: a */
    public u0.h apply(a aVar) {
        l0.z b8 = aVar.b();
        u0.g a8 = aVar.a();
        File f8 = f(a8);
        o(f8, (byte[]) b8.c());
        d0.g d8 = b8.d();
        Objects.requireNonNull(d8);
        m(f8, d8, a8, b8.f());
        return new u0.h(k(f8, a8));
    }
}
