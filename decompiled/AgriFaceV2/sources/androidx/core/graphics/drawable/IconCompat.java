package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* renamed from: k  reason: collision with root package name */
    static final PorterDuff.Mode f2977k = PorterDuff.Mode.SRC_IN;

    /* renamed from: a  reason: collision with root package name */
    public int f2978a;

    /* renamed from: b  reason: collision with root package name */
    Object f2979b;

    /* renamed from: c  reason: collision with root package name */
    public byte[] f2980c;

    /* renamed from: d  reason: collision with root package name */
    public Parcelable f2981d;

    /* renamed from: e  reason: collision with root package name */
    public int f2982e;

    /* renamed from: f  reason: collision with root package name */
    public int f2983f;

    /* renamed from: g  reason: collision with root package name */
    public ColorStateList f2984g;

    /* renamed from: h  reason: collision with root package name */
    PorterDuff.Mode f2985h;

    /* renamed from: i  reason: collision with root package name */
    public String f2986i;

    /* renamed from: j  reason: collision with root package name */
    public String f2987j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static int a(Object obj) {
            if (Build.VERSION.SDK_INT >= 28) {
                return c.a(obj);
            }
            try {
                return ((Integer) obj.getClass().getMethod("getResId", new Class[0]).invoke(obj, new Object[0])).intValue();
            } catch (IllegalAccessException e8) {
                Log.e("IconCompat", "Unable to get icon resource", e8);
                return 0;
            } catch (NoSuchMethodException e9) {
                Log.e("IconCompat", "Unable to get icon resource", e9);
                return 0;
            } catch (InvocationTargetException e10) {
                Log.e("IconCompat", "Unable to get icon resource", e10);
                return 0;
            }
        }

        static String b(Object obj) {
            if (Build.VERSION.SDK_INT >= 28) {
                return c.b(obj);
            }
            try {
                return (String) obj.getClass().getMethod("getResPackage", new Class[0]).invoke(obj, new Object[0]);
            } catch (IllegalAccessException e8) {
                Log.e("IconCompat", "Unable to get icon package", e8);
                return null;
            } catch (NoSuchMethodException e9) {
                Log.e("IconCompat", "Unable to get icon package", e9);
                return null;
            } catch (InvocationTargetException e10) {
                Log.e("IconCompat", "Unable to get icon package", e10);
                return null;
            }
        }

        static int c(Object obj) {
            StringBuilder sb;
            if (Build.VERSION.SDK_INT >= 28) {
                return c.c(obj);
            }
            try {
                return ((Integer) obj.getClass().getMethod("getType", new Class[0]).invoke(obj, new Object[0])).intValue();
            } catch (IllegalAccessException e8) {
                e = e8;
                sb = new StringBuilder();
                sb.append("Unable to get icon type ");
                sb.append(obj);
                Log.e("IconCompat", sb.toString(), e);
                return -1;
            } catch (NoSuchMethodException e9) {
                e = e9;
                sb = new StringBuilder();
                sb.append("Unable to get icon type ");
                sb.append(obj);
                Log.e("IconCompat", sb.toString(), e);
                return -1;
            } catch (InvocationTargetException e10) {
                e = e10;
                sb = new StringBuilder();
                sb.append("Unable to get icon type ");
                sb.append(obj);
                Log.e("IconCompat", sb.toString(), e);
                return -1;
            }
        }

        static Uri d(Object obj) {
            if (Build.VERSION.SDK_INT >= 28) {
                return c.d(obj);
            }
            try {
                return (Uri) obj.getClass().getMethod("getUri", new Class[0]).invoke(obj, new Object[0]);
            } catch (IllegalAccessException e8) {
                Log.e("IconCompat", "Unable to get icon uri", e8);
                return null;
            } catch (NoSuchMethodException e9) {
                Log.e("IconCompat", "Unable to get icon uri", e9);
                return null;
            } catch (InvocationTargetException e10) {
                Log.e("IconCompat", "Unable to get icon uri", e10);
                return null;
            }
        }

        static Drawable e(Icon icon, Context context) {
            return icon.loadDrawable(context);
        }

        static Icon f(IconCompat iconCompat, Context context) {
            Icon createWithBitmap;
            Bitmap bitmap;
            switch (iconCompat.f2978a) {
                case androidx.swiperefreshlayout.widget.c.DEFAULT_SLINGSHOT_DISTANCE /* -1 */:
                    return (Icon) iconCompat.f2979b;
                case 0:
                default:
                    throw new IllegalArgumentException("Unknown type");
                case 1:
                    createWithBitmap = Icon.createWithBitmap((Bitmap) iconCompat.f2979b);
                    break;
                case 2:
                    createWithBitmap = Icon.createWithResource(iconCompat.c(), iconCompat.f2982e);
                    break;
                case 3:
                    createWithBitmap = Icon.createWithData((byte[]) iconCompat.f2979b, iconCompat.f2982e, iconCompat.f2983f);
                    break;
                case 4:
                    createWithBitmap = Icon.createWithContentUri((String) iconCompat.f2979b);
                    break;
                case 5:
                    bitmap = (Bitmap) iconCompat.f2979b;
                    createWithBitmap = b.b(bitmap);
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    if (Build.VERSION.SDK_INT >= 30) {
                        createWithBitmap = d.a(iconCompat.e());
                        break;
                    } else if (context == null) {
                        throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + iconCompat.e());
                    } else {
                        InputStream f8 = iconCompat.f(context);
                        if (f8 == null) {
                            throw new IllegalStateException("Cannot load adaptive icon from uri: " + iconCompat.e());
                        }
                        bitmap = BitmapFactory.decodeStream(f8);
                        createWithBitmap = b.b(bitmap);
                        break;
                    }
            }
            ColorStateList colorStateList = iconCompat.f2984g;
            if (colorStateList != null) {
                createWithBitmap.setTintList(colorStateList);
            }
            PorterDuff.Mode mode = iconCompat.f2985h;
            if (mode != IconCompat.f2977k) {
                createWithBitmap.setTintMode(mode);
            }
            return createWithBitmap;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        static Drawable a(Drawable drawable, Drawable drawable2) {
            return new AdaptiveIconDrawable(drawable, drawable2);
        }

        static Icon b(Bitmap bitmap) {
            return Icon.createWithAdaptiveBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        static int a(Object obj) {
            return ((Icon) obj).getResId();
        }

        static String b(Object obj) {
            return ((Icon) obj).getResPackage();
        }

        static int c(Object obj) {
            return ((Icon) obj).getType();
        }

        static Uri d(Object obj) {
            return ((Icon) obj).getUri();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {
        static Icon a(Uri uri) {
            return Icon.createWithAdaptiveBitmapContentUri(uri);
        }
    }

    public IconCompat() {
        this.f2978a = -1;
        this.f2980c = null;
        this.f2981d = null;
        this.f2982e = 0;
        this.f2983f = 0;
        this.f2984g = null;
        this.f2985h = f2977k;
        this.f2986i = null;
    }

    public static IconCompat a(Resources resources, String str, int i8) {
        g1.c.c(str);
        if (i8 != 0) {
            IconCompat iconCompat = new IconCompat(2);
            iconCompat.f2982e = i8;
            if (resources != null) {
                try {
                    iconCompat.f2979b = resources.getResourceName(i8);
                } catch (Resources.NotFoundException unused) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                iconCompat.f2979b = str;
            }
            iconCompat.f2987j = str;
            return iconCompat;
        }
        throw new IllegalArgumentException("Drawable resource ID must not be 0");
    }

    private static String k(int i8) {
        switch (i8) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return "URI_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public int b() {
        int i8 = this.f2978a;
        if (i8 == -1) {
            return a.a(this.f2979b);
        }
        if (i8 == 2) {
            return this.f2982e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String c() {
        int i8 = this.f2978a;
        if (i8 == -1) {
            return a.b(this.f2979b);
        }
        if (i8 == 2) {
            String str = this.f2987j;
            return (str == null || TextUtils.isEmpty(str)) ? ((String) this.f2979b).split(":", -1)[0] : this.f2987j;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int d() {
        int i8 = this.f2978a;
        return i8 == -1 ? a.c(this.f2979b) : i8;
    }

    public Uri e() {
        int i8 = this.f2978a;
        if (i8 == -1) {
            return a.d(this.f2979b);
        }
        if (i8 == 4 || i8 == 6) {
            return Uri.parse((String) this.f2979b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public InputStream f(Context context) {
        StringBuilder sb;
        String str;
        Uri e8 = e();
        String scheme = e8.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(e8);
            } catch (Exception e9) {
                e = e9;
                sb = new StringBuilder();
                str = "Unable to load image from URI: ";
            }
        } else {
            try {
                return new FileInputStream(new File((String) this.f2979b));
            } catch (FileNotFoundException e10) {
                e = e10;
                sb = new StringBuilder();
                str = "Unable to load image from path: ";
            }
        }
        sb.append(str);
        sb.append(e8);
        Log.w("IconCompat", sb.toString(), e);
        return null;
    }

    public void g() {
        Parcelable parcelable;
        this.f2985h = PorterDuff.Mode.valueOf(this.f2986i);
        switch (this.f2978a) {
            case androidx.swiperefreshlayout.widget.c.DEFAULT_SLINGSHOT_DISTANCE /* -1 */:
                parcelable = this.f2981d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                break;
            case 0:
            default:
                return;
            case 1:
            case 5:
                parcelable = this.f2981d;
                if (parcelable == null) {
                    byte[] bArr = this.f2980c;
                    this.f2979b = bArr;
                    this.f2978a = 3;
                    this.f2982e = 0;
                    this.f2983f = bArr.length;
                    return;
                }
                break;
            case 2:
            case 4:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                String str = new String(this.f2980c, Charset.forName("UTF-16"));
                this.f2979b = str;
                if (this.f2978a == 2 && this.f2987j == null) {
                    this.f2987j = str.split(":", -1)[0];
                    return;
                }
                return;
            case 3:
                this.f2979b = this.f2980c;
                return;
        }
        this.f2979b = parcelable;
    }

    public void h(boolean z7) {
        this.f2986i = this.f2985h.name();
        switch (this.f2978a) {
            case androidx.swiperefreshlayout.widget.c.DEFAULT_SLINGSHOT_DISTANCE /* -1 */:
                if (z7) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                break;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (z7) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ((Bitmap) this.f2979b).compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                    this.f2980c = byteArrayOutputStream.toByteArray();
                    return;
                }
                break;
            case 2:
                this.f2980c = ((String) this.f2979b).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f2980c = (byte[]) this.f2979b;
                return;
            case 4:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                this.f2980c = this.f2979b.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
        this.f2981d = (Parcelable) this.f2979b;
    }

    public Icon i() {
        return j(null);
    }

    public Icon j(Context context) {
        return a.f(this, context);
    }

    public String toString() {
        int height;
        if (this.f2978a == -1) {
            return String.valueOf(this.f2979b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(k(this.f2978a));
        switch (this.f2978a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f2979b).getWidth());
                sb.append("x");
                height = ((Bitmap) this.f2979b).getHeight();
                sb.append(height);
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f2987j);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(b())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f2982e);
                if (this.f2983f != 0) {
                    sb.append(" off=");
                    height = this.f2983f;
                    sb.append(height);
                    break;
                }
                break;
            case 4:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                sb.append(" uri=");
                sb.append(this.f2979b);
                break;
        }
        if (this.f2984g != null) {
            sb.append(" tint=");
            sb.append(this.f2984g);
        }
        if (this.f2985h != f2977k) {
            sb.append(" mode=");
            sb.append(this.f2985h);
        }
        sb.append(")");
        return sb.toString();
    }

    IconCompat(int i8) {
        this.f2980c = null;
        this.f2981d = null;
        this.f2982e = 0;
        this.f2983f = 0;
        this.f2984g = null;
        this.f2985h = f2977k;
        this.f2986i = null;
        this.f2978a = i8;
    }
}
