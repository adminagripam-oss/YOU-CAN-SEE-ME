package d0;

import android.os.Build;
import android.util.Pair;
import b0.w;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import y.f1;
/* loaded from: classes.dex */
public class i {

    /* renamed from: c  reason: collision with root package name */
    static final String[] f6380c = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};

    /* renamed from: d  reason: collision with root package name */
    private static final k[] f6381d;

    /* renamed from: e  reason: collision with root package name */
    private static final k[] f6382e;

    /* renamed from: f  reason: collision with root package name */
    private static final k[] f6383f;

    /* renamed from: g  reason: collision with root package name */
    static final k[] f6384g;

    /* renamed from: h  reason: collision with root package name */
    private static final k[] f6385h;

    /* renamed from: i  reason: collision with root package name */
    static final k[][] f6386i;

    /* renamed from: j  reason: collision with root package name */
    static final HashSet f6387j;

    /* renamed from: a  reason: collision with root package name */
    private final List f6388a;

    /* renamed from: b  reason: collision with root package name */
    private final ByteOrder f6389b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f6390a;

        static {
            int[] iArr = new int[w.values().length];
            f6390a = iArr;
            try {
                iArr[w.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6390a[w.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6390a[w.FIRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: c  reason: collision with root package name */
        private static final Pattern f6391c = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");

        /* renamed from: d  reason: collision with root package name */
        private static final Pattern f6392d = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");

        /* renamed from: e  reason: collision with root package name */
        private static final Pattern f6393e = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");

        /* renamed from: f  reason: collision with root package name */
        static final List f6394f = Collections.list(new a());

        /* renamed from: a  reason: collision with root package name */
        final List f6395a = Collections.list(new C0077b());

        /* renamed from: b  reason: collision with root package name */
        private final ByteOrder f6396b;

        /* loaded from: classes.dex */
        class a implements Enumeration {

            /* renamed from: a  reason: collision with root package name */
            int f6397a = 0;

            a() {
            }

            @Override // java.util.Enumeration
            /* renamed from: a */
            public HashMap nextElement() {
                k[] kVarArr;
                HashMap hashMap = new HashMap();
                for (k kVar : i.f6386i[this.f6397a]) {
                    hashMap.put(kVar.f6413b, kVar);
                }
                this.f6397a++;
                return hashMap;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f6397a < i.f6386i.length;
            }
        }

        /* renamed from: d0.i$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0077b implements Enumeration {

            /* renamed from: a  reason: collision with root package name */
            int f6398a = 0;

            C0077b() {
            }

            @Override // java.util.Enumeration
            /* renamed from: a */
            public Map nextElement() {
                this.f6398a++;
                return new HashMap();
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f6398a < i.f6386i.length;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class c implements Enumeration {

            /* renamed from: a  reason: collision with root package name */
            final Enumeration f6400a;

            c() {
                this.f6400a = Collections.enumeration(b.this.f6395a);
            }

            @Override // java.util.Enumeration
            /* renamed from: a */
            public Map nextElement() {
                return new HashMap((Map) this.f6400a.nextElement());
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f6400a.hasMoreElements();
            }
        }

        b(ByteOrder byteOrder) {
            this.f6396b = byteOrder;
        }

        private static Pair b(String str) {
            if (str.contains(",")) {
                String[] split = str.split(",", -1);
                Pair b8 = b(split[0]);
                if (((Integer) b8.first).intValue() == 2) {
                    return b8;
                }
                for (int i8 = 1; i8 < split.length; i8++) {
                    Pair b9 = b(split[i8]);
                    int intValue = (((Integer) b9.first).equals(b8.first) || ((Integer) b9.second).equals(b8.first)) ? ((Integer) b8.first).intValue() : -1;
                    int intValue2 = (((Integer) b8.second).intValue() == -1 || !(((Integer) b9.first).equals(b8.second) || ((Integer) b9.second).equals(b8.second))) ? -1 : ((Integer) b8.second).intValue();
                    if (intValue == -1 && intValue2 == -1) {
                        return new Pair(2, -1);
                    }
                    if (intValue == -1) {
                        b8 = new Pair(Integer.valueOf(intValue2), -1);
                    } else if (intValue2 == -1) {
                        b8 = new Pair(Integer.valueOf(intValue), -1);
                    }
                }
                return b8;
            } else if (!str.contains("/")) {
                try {
                    try {
                        long parseLong = Long.parseLong(str);
                        int i9 = (parseLong > 0L ? 1 : (parseLong == 0L ? 0 : -1));
                        return (i9 < 0 || parseLong > 65535) ? i9 < 0 ? new Pair(9, -1) : new Pair(4, -1) : new Pair(3, 4);
                    } catch (NumberFormatException unused) {
                        return new Pair(2, -1);
                    }
                } catch (NumberFormatException unused2) {
                    Double.parseDouble(str);
                    return new Pair(12, -1);
                }
            } else {
                String[] split2 = str.split("/", -1);
                if (split2.length == 2) {
                    try {
                        long parseDouble = (long) Double.parseDouble(split2[0]);
                        long parseDouble2 = (long) Double.parseDouble(split2[1]);
                        if (parseDouble >= 0 && parseDouble2 >= 0) {
                            if (parseDouble <= 2147483647L && parseDouble2 <= 2147483647L) {
                                return new Pair(10, 5);
                            }
                            return new Pair(5, -1);
                        }
                        return new Pair(10, -1);
                    } catch (NumberFormatException unused3) {
                    }
                }
                return new Pair(2, -1);
            }
        }

        private void d(String str, String str2, List list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((Map) it.next()).containsKey(str)) {
                    return;
                }
            }
            e(str, str2, list);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x018a, code lost:
            if (r7 != r0) goto L97;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void e(java.lang.String r18, java.lang.String r19, java.util.List r20) {
            /*
                Method dump skipped, instructions count: 758
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: d0.i.b.e(java.lang.String, java.lang.String, java.util.List):void");
        }

        public i a() {
            ArrayList list = Collections.list(new c());
            if (!((Map) list.get(1)).isEmpty()) {
                d("ExposureProgram", String.valueOf(0), list);
                d("ExifVersion", "0230", list);
                d("ComponentsConfiguration", "1,2,3,0", list);
                d("MeteringMode", String.valueOf(0), list);
                d("LightSource", String.valueOf(0), list);
                d("FlashpixVersion", "0100", list);
                d("FocalPlaneResolutionUnit", String.valueOf(2), list);
                d("FileSource", String.valueOf(3), list);
                d("SceneType", String.valueOf(1), list);
                d("CustomRendered", String.valueOf(0), list);
                d("SceneCaptureType", String.valueOf(0), list);
                d("Contrast", String.valueOf(0), list);
                d("Saturation", String.valueOf(0), list);
                d("Sharpness", String.valueOf(0), list);
            }
            if (!((Map) list.get(2)).isEmpty()) {
                d("GPSVersionID", "2300", list);
                d("GPSSpeedRef", "K", list);
                d("GPSTrackRef", "T", list);
                d("GPSImgDirectionRef", "T", list);
                d("GPSDestBearingRef", "T", list);
                d("GPSDestDistanceRef", "K", list);
            }
            return new i(this.f6396b, list);
        }

        public b c(String str, String str2) {
            e(str, str2, this.f6395a);
            return this;
        }

        public b f(long j8) {
            return c("ExposureTime", String.valueOf(j8 / TimeUnit.SECONDS.toNanos(1L)));
        }

        public b g(w wVar) {
            int i8;
            if (wVar == w.UNKNOWN) {
                return this;
            }
            int i9 = a.f6390a[wVar.ordinal()];
            if (i9 == 1) {
                i8 = 0;
            } else if (i9 == 2) {
                i8 = 32;
            } else if (i9 != 3) {
                f1.l("ExifData", "Unknown flash state: " + wVar);
                return this;
            } else {
                i8 = 1;
            }
            if ((i8 & 1) == 1) {
                c("LightSource", String.valueOf(4));
            }
            return c("Flash", String.valueOf(i8));
        }

        public b h(float f8) {
            return c("FocalLength", new l(f8 * 1000.0f, 1000L).toString());
        }

        public b i(int i8) {
            return c("ImageLength", String.valueOf(i8));
        }

        public b j(int i8) {
            return c("ImageWidth", String.valueOf(i8));
        }

        public b k(int i8) {
            return c("SensitivityType", String.valueOf(3)).c("PhotographicSensitivity", String.valueOf(Math.min(65535, i8)));
        }

        public b l(float f8) {
            return c("FNumber", String.valueOf(f8));
        }

        public b m(int i8) {
            int i9;
            if (i8 == 0) {
                i9 = 1;
            } else if (i8 == 90) {
                i9 = 6;
            } else if (i8 == 180) {
                i9 = 3;
            } else if (i8 != 270) {
                f1.l("ExifData", "Unexpected orientation value: " + i8 + ". Must be one of 0, 90, 180, 270.");
                i9 = 0;
            } else {
                i9 = 8;
            }
            return c("Orientation", String.valueOf(i9));
        }

        public b n(c cVar) {
            int ordinal = cVar.ordinal();
            return c("WhiteBalance", ordinal != 0 ? ordinal != 1 ? null : String.valueOf(1) : String.valueOf(0));
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        AUTO,
        MANUAL
    }

    static {
        k[] kVarArr = {new k("ImageWidth", 256, 3, 4), new k("ImageLength", 257, 3, 4), new k("Make", 271, 2), new k("Model", 272, 2), new k("Orientation", 274, 3), new k("XResolution", 282, 5), new k("YResolution", 283, 5), new k("ResolutionUnit", 296, 3), new k("Software", 305, 2), new k("DateTime", 306, 2), new k("YCbCrPositioning", 531, 3), new k("SubIFDPointer", 330, 4), new k("ExifIFDPointer", 34665, 4), new k("GPSInfoIFDPointer", 34853, 4)};
        f6381d = kVarArr;
        k[] kVarArr2 = {new k("ExposureTime", 33434, 5), new k("FNumber", 33437, 5), new k("ExposureProgram", 34850, 3), new k("PhotographicSensitivity", 34855, 3), new k("SensitivityType", 34864, 3), new k("ExifVersion", 36864, 2), new k("DateTimeOriginal", 36867, 2), new k("DateTimeDigitized", 36868, 2), new k("ComponentsConfiguration", 37121, 7), new k("ShutterSpeedValue", 37377, 10), new k("ApertureValue", 37378, 5), new k("BrightnessValue", 37379, 10), new k("ExposureBiasValue", 37380, 10), new k("MaxApertureValue", 37381, 5), new k("MeteringMode", 37383, 3), new k("LightSource", 37384, 3), new k("Flash", 37385, 3), new k("FocalLength", 37386, 5), new k("SubSecTime", 37520, 2), new k("SubSecTimeOriginal", 37521, 2), new k("SubSecTimeDigitized", 37522, 2), new k("FlashpixVersion", 40960, 7), new k("ColorSpace", 40961, 3), new k("PixelXDimension", 40962, 3, 4), new k("PixelYDimension", 40963, 3, 4), new k("InteroperabilityIFDPointer", 40965, 4), new k("FocalPlaneResolutionUnit", 41488, 3), new k("SensingMethod", 41495, 3), new k("FileSource", 41728, 7), new k("SceneType", 41729, 7), new k("CustomRendered", 41985, 3), new k("ExposureMode", 41986, 3), new k("WhiteBalance", 41987, 3), new k("SceneCaptureType", 41990, 3), new k("Contrast", 41992, 3), new k("Saturation", 41993, 3), new k("Sharpness", 41994, 3)};
        f6382e = kVarArr2;
        k[] kVarArr3 = {new k("GPSVersionID", 0, 1), new k("GPSLatitudeRef", 1, 2), new k("GPSLatitude", 2, 5, 10), new k("GPSLongitudeRef", 3, 2), new k("GPSLongitude", 4, 5, 10), new k("GPSAltitudeRef", 5, 1), new k("GPSAltitude", 6, 5), new k("GPSTimeStamp", 7, 5), new k("GPSSpeedRef", 12, 2), new k("GPSTrackRef", 14, 2), new k("GPSImgDirectionRef", 16, 2), new k("GPSDestBearingRef", 23, 2), new k("GPSDestDistanceRef", 25, 2)};
        f6383f = kVarArr3;
        f6384g = new k[]{new k("SubIFDPointer", 330, 4), new k("ExifIFDPointer", 34665, 4), new k("GPSInfoIFDPointer", 34853, 4), new k("InteroperabilityIFDPointer", 40965, 4)};
        k[] kVarArr4 = {new k("InteroperabilityIndex", 1, 2)};
        f6385h = kVarArr4;
        f6386i = new k[][]{kVarArr, kVarArr2, kVarArr3, kVarArr4};
        f6387j = new HashSet(Arrays.asList("FNumber", "ExposureTime", "GPSTimeStamp"));
    }

    i(ByteOrder byteOrder, List list) {
        g1.e.m(list.size() == f6386i.length, "Malformed attributes list. Number of IFDs mismatch.");
        this.f6389b = byteOrder;
        this.f6388a = list;
    }

    public static b a() {
        return new b(ByteOrder.BIG_ENDIAN).c("Orientation", String.valueOf(1)).c("XResolution", "72/1").c("YResolution", "72/1").c("ResolutionUnit", String.valueOf(2)).c("YCbCrPositioning", String.valueOf(1)).c("Make", Build.MANUFACTURER).c("Model", Build.MODEL);
    }

    public static i b(androidx.camera.core.n nVar, int i8) {
        b a8 = a();
        if (nVar.p() != null) {
            nVar.p().a(a8);
        }
        a8.m(i8);
        return a8.j(nVar.getWidth()).i(nVar.getHeight()).a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map c(int i8) {
        int length = f6386i.length;
        g1.e.e(i8, 0, length, "Invalid IFD index: " + i8 + ". Index should be between [0, EXIF_TAGS.length] ");
        return (Map) this.f6388a.get(i8);
    }

    public ByteOrder d() {
        return this.f6389b;
    }
}
