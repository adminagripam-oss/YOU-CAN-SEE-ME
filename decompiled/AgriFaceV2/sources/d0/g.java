package d0;

import android.location.Location;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import y.f1;
/* loaded from: classes.dex */
public final class g {

    /* renamed from: c  reason: collision with root package name */
    private static final String f6363c = "g";

    /* renamed from: d  reason: collision with root package name */
    private static final ThreadLocal f6364d = new a();

    /* renamed from: e  reason: collision with root package name */
    private static final ThreadLocal f6365e = new b();

    /* renamed from: f  reason: collision with root package name */
    private static final ThreadLocal f6366f = new c();

    /* renamed from: g  reason: collision with root package name */
    private static final List f6367g = n();

    /* renamed from: h  reason: collision with root package name */
    private static final List f6368h = Arrays.asList("ImageWidth", "ImageLength", "PixelXDimension", "PixelYDimension", "Compression", "JPEGInterchangeFormat", "JPEGInterchangeFormatLength", "ThumbnailImageLength", "ThumbnailImageWidth", "ThumbnailOrientation");

    /* renamed from: a  reason: collision with root package name */
    private final q1.a f6369a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f6370b = false;

    /* loaded from: classes.dex */
    class a extends ThreadLocal {
        a() {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy:MM:dd", Locale.US);
        }
    }

    /* loaded from: classes.dex */
    class b extends ThreadLocal {
        b() {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", Locale.US);
        }
    }

    /* loaded from: classes.dex */
    class c extends ThreadLocal {
        c() {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class d {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            final double f6371a;

            a(double d8) {
                this.f6371a = d8;
            }

            double a() {
                return this.f6371a / 2.23694d;
            }
        }

        static a a(double d8) {
            return new a(d8 * 0.621371d);
        }

        static a b(double d8) {
            return new a(d8 * 1.15078d);
        }

        static a c(double d8) {
            return new a(d8);
        }
    }

    private g(q1.a aVar) {
        this.f6369a = aVar;
    }

    private void a() {
        long currentTimeMillis = System.currentTimeMillis();
        String f8 = f(currentTimeMillis);
        this.f6369a.g0("DateTime", f8);
        try {
            this.f6369a.g0("SubSecTime", Long.toString(currentTimeMillis - d(f8).getTime()));
        } catch (ParseException unused) {
        }
    }

    private static Date c(String str) {
        return ((SimpleDateFormat) f6364d.get()).parse(str);
    }

    private static Date d(String str) {
        return ((SimpleDateFormat) f6366f.get()).parse(str);
    }

    private static Date e(String str) {
        return ((SimpleDateFormat) f6365e.get()).parse(str);
    }

    private static String f(long j8) {
        return ((SimpleDateFormat) f6366f.get()).format(new Date(j8));
    }

    public static g h(File file) {
        return i(file.toString());
    }

    public static g i(String str) {
        return new g(new q1.a(str));
    }

    public static g j(androidx.camera.core.n nVar) {
        ByteBuffer c8 = nVar.i()[0].c();
        c8.rewind();
        byte[] bArr = new byte[c8.capacity()];
        c8.get(bArr);
        return k(new ByteArrayInputStream(bArr));
    }

    public static g k(InputStream inputStream) {
        return new g(new q1.a(inputStream));
    }

    public static List n() {
        return Arrays.asList("ImageWidth", "ImageLength", "BitsPerSample", "Compression", "PhotometricInterpretation", "Orientation", "SamplesPerPixel", "PlanarConfiguration", "YCbCrSubSampling", "YCbCrPositioning", "XResolution", "YResolution", "ResolutionUnit", "StripOffsets", "RowsPerStrip", "StripByteCounts", "JPEGInterchangeFormat", "JPEGInterchangeFormatLength", "TransferFunction", "WhitePoint", "PrimaryChromaticities", "YCbCrCoefficients", "ReferenceBlackWhite", "DateTime", "ImageDescription", "Make", "Model", "Software", "Artist", "Copyright", "ExifVersion", "FlashpixVersion", "ColorSpace", "Gamma", "PixelXDimension", "PixelYDimension", "ComponentsConfiguration", "CompressedBitsPerPixel", "MakerNote", "UserComment", "RelatedSoundFile", "DateTimeOriginal", "DateTimeDigitized", "OffsetTime", "OffsetTimeOriginal", "OffsetTimeDigitized", "SubSecTime", "SubSecTimeOriginal", "SubSecTimeDigitized", "ExposureTime", "FNumber", "ExposureProgram", "SpectralSensitivity", "PhotographicSensitivity", "OECF", "SensitivityType", "StandardOutputSensitivity", "RecommendedExposureIndex", "ISOSpeed", "ISOSpeedLatitudeyyy", "ISOSpeedLatitudezzz", "ShutterSpeedValue", "ApertureValue", "BrightnessValue", "ExposureBiasValue", "MaxApertureValue", "SubjectDistance", "MeteringMode", "LightSource", "Flash", "SubjectArea", "FocalLength", "FlashEnergy", "SpatialFrequencyResponse", "FocalPlaneXResolution", "FocalPlaneYResolution", "FocalPlaneResolutionUnit", "SubjectLocation", "ExposureIndex", "SensingMethod", "FileSource", "SceneType", "CFAPattern", "CustomRendered", "ExposureMode", "WhiteBalance", "DigitalZoomRatio", "FocalLengthIn35mmFilm", "SceneCaptureType", "GainControl", "Contrast", "Saturation", "Sharpness", "DeviceSettingDescription", "SubjectDistanceRange", "ImageUniqueID", "CameraOwnerName", "BodySerialNumber", "LensSpecification", "LensMake", "LensModel", "LensSerialNumber", "GPSVersionID", "GPSLatitudeRef", "GPSLatitude", "GPSLongitudeRef", "GPSLongitude", "GPSAltitudeRef", "GPSAltitude", "GPSTimeStamp", "GPSSatellites", "GPSStatus", "GPSMeasureMode", "GPSDOP", "GPSSpeedRef", "GPSSpeed", "GPSTrackRef", "GPSTrack", "GPSImgDirectionRef", "GPSImgDirection", "GPSMapDatum", "GPSDestLatitudeRef", "GPSDestLatitude", "GPSDestLongitudeRef", "GPSDestLongitude", "GPSDestBearingRef", "GPSDestBearing", "GPSDestDistanceRef", "GPSDestDistance", "GPSProcessingMethod", "GPSAreaInformation", "GPSDateStamp", "GPSDifferential", "GPSHPositioningError", "InteroperabilityIndex", "ThumbnailImageLength", "ThumbnailImageWidth", "ThumbnailOrientation", "DNGVersion", "DefaultCropSize", "ThumbnailImage", "PreviewImageStart", "PreviewImageLength", "AspectFrame", "SensorBottomBorder", "SensorLeftBorder", "SensorRightBorder", "SensorTopBorder", "ISO", "JpgFromRaw", "Xmp", "NewSubfileType", "SubfileType");
    }

    private long x(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return d(str).getTime();
        } catch (ParseException unused) {
            return -1L;
        }
    }

    private long y(String str, String str2) {
        if (str == null && str2 == null) {
            return -1L;
        }
        if (str2 == null) {
            try {
                return c(str).getTime();
            } catch (ParseException unused) {
                return -1L;
            }
        } else if (str == null) {
            try {
                return e(str2).getTime();
            } catch (ParseException unused2) {
                return -1L;
            }
        } else {
            return x(str + " " + str2);
        }
    }

    public void A() {
        if (!this.f6370b) {
            a();
        }
        this.f6369a.b0();
    }

    public void b(Location location) {
        this.f6369a.h0(location);
    }

    public void g(g gVar) {
        ArrayList<String> arrayList = new ArrayList(f6367g);
        arrayList.removeAll(f6368h);
        for (String str : arrayList) {
            String m8 = this.f6369a.m(str);
            String m9 = gVar.f6369a.m(str);
            if (m8 != null && !m8.equals(m9)) {
                gVar.f6369a.g0(str, m8);
            }
        }
    }

    public void l() {
        int i8;
        switch (r()) {
            case 2:
                i8 = 1;
                break;
            case 3:
                i8 = 4;
                break;
            case 4:
                i8 = 3;
                break;
            case 5:
                i8 = 6;
                break;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                i8 = 5;
                break;
            case 7:
                i8 = 8;
                break;
            case 8:
                i8 = 7;
                break;
            default:
                i8 = 2;
                break;
        }
        this.f6369a.g0("Orientation", String.valueOf(i8));
    }

    public void m() {
        int i8;
        switch (r()) {
            case 2:
                i8 = 3;
                break;
            case 3:
                i8 = 2;
                break;
            case 4:
                i8 = 1;
                break;
            case 5:
                i8 = 8;
                break;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                i8 = 7;
                break;
            case 7:
                i8 = 6;
                break;
            case 8:
                i8 = 5;
                break;
            default:
                i8 = 4;
                break;
        }
        this.f6369a.g0("Orientation", String.valueOf(i8));
    }

    public String o() {
        return this.f6369a.m("ImageDescription");
    }

    public int p() {
        return this.f6369a.o("ImageLength", 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.location.Location q() {
        /*
            r16 = this;
            r0 = r16
            q1.a r1 = r0.f6369a
            java.lang.String r2 = "GPSProcessingMethod"
            java.lang.String r1 = r1.m(r2)
            q1.a r2 = r0.f6369a
            double[] r2 = r2.s()
            q1.a r3 = r0.f6369a
            r4 = 0
            double r6 = r3.l(r4)
            q1.a r3 = r0.f6369a
            java.lang.String r8 = "GPSSpeed"
            double r8 = r3.n(r8, r4)
            q1.a r3 = r0.f6369a
            java.lang.String r10 = "GPSSpeedRef"
            java.lang.String r3 = r3.m(r10)
            java.lang.String r10 = "K"
            if (r3 != 0) goto L2d
            r3 = r10
        L2d:
            q1.a r11 = r0.f6369a
            java.lang.String r12 = "GPSDateStamp"
            java.lang.String r11 = r11.m(r12)
            q1.a r12 = r0.f6369a
            java.lang.String r13 = "GPSTimeStamp"
            java.lang.String r12 = r12.m(r13)
            long r11 = r0.y(r11, r12)
            if (r2 != 0) goto L45
            r1 = 0
            return r1
        L45:
            if (r1 != 0) goto L49
            java.lang.String r1 = d0.g.f6363c
        L49:
            android.location.Location r13 = new android.location.Location
            r13.<init>(r1)
            r1 = 0
            r14 = r2[r1]
            r13.setLatitude(r14)
            r14 = 1
            r1 = r2[r14]
            r13.setLongitude(r1)
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 == 0) goto L61
            r13.setAltitude(r6)
        L61:
            int r1 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r1 == 0) goto Lad
            int r1 = r3.hashCode()
            r2 = 75
            if (r1 == r2) goto L8a
            r2 = 77
            if (r1 == r2) goto L80
            r2 = 78
            if (r1 == r2) goto L76
            goto L92
        L76:
            java.lang.String r1 = "N"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L92
            r1 = r14
            goto L93
        L80:
            java.lang.String r1 = "M"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L92
            r1 = 0
            goto L93
        L8a:
            boolean r1 = r3.equals(r10)
            if (r1 == 0) goto L92
            r1 = 2
            goto L93
        L92:
            r1 = -1
        L93:
            if (r1 == 0) goto La1
            if (r1 == r14) goto L9c
            d0.g$d$a r1 = d0.g.d.a(r8)
            goto La5
        L9c:
            d0.g$d$a r1 = d0.g.d.b(r8)
            goto La5
        La1:
            d0.g$d$a r1 = d0.g.d.c(r8)
        La5:
            double r1 = r1.a()
            float r1 = (float) r1
            r13.setSpeed(r1)
        Lad:
            r1 = -1
            int r1 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r1 == 0) goto Lb6
            r13.setTime(r11)
        Lb6:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: d0.g.q():android.location.Location");
    }

    public int r() {
        return this.f6369a.o("Orientation", 0);
    }

    public int s() {
        switch (r()) {
            case 3:
            case 4:
                return 180;
            case 5:
                return 270;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
            case 7:
                return 90;
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public long t() {
        long x7 = x(this.f6369a.m("DateTimeOriginal"));
        if (x7 == -1) {
            return -1L;
        }
        String m8 = this.f6369a.m("SubSecTimeOriginal");
        if (m8 != null) {
            try {
                long parseLong = Long.parseLong(m8);
                while (parseLong > 1000) {
                    parseLong /= 10;
                }
                return x7 + parseLong;
            } catch (NumberFormatException unused) {
                return x7;
            }
        }
        return x7;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "Exif{width=%s, height=%s, rotation=%d, isFlippedVertically=%s, isFlippedHorizontally=%s, location=%s, timestamp=%s, description=%s}", Integer.valueOf(u()), Integer.valueOf(p()), Integer.valueOf(s()), Boolean.valueOf(w()), Boolean.valueOf(v()), q(), Long.valueOf(t()), o());
    }

    public int u() {
        return this.f6369a.o("ImageWidth", 0);
    }

    public boolean v() {
        return r() == 2;
    }

    public boolean w() {
        int r8 = r();
        return r8 == 4 || r8 == 5 || r8 == 7;
    }

    public void z(int i8) {
        int r8;
        q1.a aVar;
        if (i8 % 90 != 0) {
            f1.l(f6363c, String.format(Locale.US, "Can only rotate in right angles (eg. 0, 90, 180, 270). %d is unsupported.", Integer.valueOf(i8)));
            aVar = this.f6369a;
            r8 = 0;
        } else {
            int i9 = i8 % 360;
            r8 = r();
            while (i9 < 0) {
                i9 += 90;
                switch (r8) {
                    case 2:
                        r8 = 5;
                        break;
                    case 3:
                    case 8:
                        r8 = 6;
                        break;
                    case 4:
                        r8 = 7;
                        break;
                    case 5:
                        r8 = 4;
                        break;
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        r8 = 1;
                        break;
                    case 7:
                        r8 = 2;
                        break;
                    default:
                        r8 = 8;
                        break;
                }
            }
            while (i9 > 0) {
                i9 -= 90;
                switch (r8) {
                    case 2:
                        r8 = 7;
                        break;
                    case 3:
                        r8 = 8;
                        break;
                    case 4:
                        r8 = 5;
                        break;
                    case 5:
                        r8 = 2;
                        break;
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        r8 = 3;
                        break;
                    case 7:
                        r8 = 4;
                        break;
                    case 8:
                        r8 = 1;
                        break;
                    default:
                        r8 = 6;
                        break;
                }
            }
            aVar = this.f6369a;
        }
        aVar.g0("Orientation", String.valueOf(r8));
    }
}
