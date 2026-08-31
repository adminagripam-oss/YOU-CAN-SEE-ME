package s;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;
import java.util.HashMap;
import java.util.Map;
import y.f1;
/* loaded from: classes.dex */
public class t0 {

    /* renamed from: a  reason: collision with root package name */
    private final a f11510a;

    /* renamed from: b  reason: collision with root package name */
    private final v.m f11511b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f11512c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private final Map f11513d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private final Map f11514e = new HashMap();

    /* loaded from: classes.dex */
    interface a {
        StreamConfigurationMap a();

        Size[] b(int i8);

        Size[] c(int i8);

        int[] d();
    }

    private t0(StreamConfigurationMap streamConfigurationMap, v.m mVar) {
        this.f11510a = new u0(streamConfigurationMap);
        this.f11511b = mVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static t0 e(StreamConfigurationMap streamConfigurationMap, v.m mVar) {
        return new t0(streamConfigurationMap, mVar);
    }

    public Size[] a(int i8) {
        if (this.f11513d.containsKey(Integer.valueOf(i8))) {
            if (((Size[]) this.f11513d.get(Integer.valueOf(i8))) == null) {
                return null;
            }
            return (Size[]) ((Size[]) this.f11513d.get(Integer.valueOf(i8))).clone();
        }
        Size[] b8 = this.f11510a.b(i8);
        if (b8 != null && b8.length > 0) {
            b8 = this.f11511b.b(b8, i8);
        }
        this.f11513d.put(Integer.valueOf(i8), b8);
        if (b8 != null) {
            return (Size[]) b8.clone();
        }
        return null;
    }

    public int[] b() {
        int[] d8 = this.f11510a.d();
        if (d8 == null) {
            return null;
        }
        return (int[]) d8.clone();
    }

    public Size[] c(int i8) {
        if (this.f11512c.containsKey(Integer.valueOf(i8))) {
            if (((Size[]) this.f11512c.get(Integer.valueOf(i8))) == null) {
                return null;
            }
            return (Size[]) ((Size[]) this.f11512c.get(Integer.valueOf(i8))).clone();
        }
        Size[] c8 = this.f11510a.c(i8);
        if (c8 != null && c8.length != 0) {
            Size[] b8 = this.f11511b.b(c8, i8);
            this.f11512c.put(Integer.valueOf(i8), b8);
            return (Size[]) b8.clone();
        }
        f1.l("StreamConfigurationMapCompat", "Retrieved output sizes array is null or empty for format " + i8);
        return c8;
    }

    public StreamConfigurationMap d() {
        return this.f11510a.a();
    }
}
