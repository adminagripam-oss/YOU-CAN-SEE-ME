package b0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public interface h1 {

    /* loaded from: classes.dex */
    public static abstract class a {
        public static a a(int i8, String str, int i9, int i10, int i11, int i12) {
            return new e(i8, str, i9, i10, i11, i12);
        }

        public abstract int b();

        public abstract int c();

        public abstract int d();

        public abstract String e();

        public abstract int f();

        public abstract int g();
    }

    /* loaded from: classes.dex */
    public static abstract class b implements h1 {
        public static b h(int i8, int i9, List list, List list2) {
            return new f(i8, i9, Collections.unmodifiableList(new ArrayList(list)), Collections.unmodifiableList(new ArrayList(list2)));
        }
    }

    /* loaded from: classes.dex */
    public static abstract class c {
        public static c a(int i8, String str, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
            return new g(i8, str, i9, i10, i11, i12, i13, i14, i15, i16);
        }

        public abstract int b();

        public abstract int c();

        public abstract int d();

        public abstract int e();

        public abstract int f();

        public abstract int g();

        public abstract int h();

        public abstract String i();

        public abstract int j();

        public abstract int k();
    }

    static String d(int i8) {
        switch (i8) {
            case 1:
                return "audio/3gpp";
            case 2:
                return "audio/amr-wb";
            case 3:
            case 4:
            case 5:
                return "audio/mp4a-latm";
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return "audio/vorbis";
            case 7:
                return "audio/opus";
            default:
                return "audio/none";
        }
    }

    static int e(int i8) {
        if (i8 != 3) {
            if (i8 != 4) {
                return i8 != 5 ? -1 : 39;
            }
            return 5;
        }
        return 2;
    }

    static String f(int i8) {
        switch (i8) {
            case 1:
                return "video/3gpp";
            case 2:
                return "video/avc";
            case 3:
                return "video/mp4v-es";
            case 4:
                return "video/x-vnd.on2.vp8";
            case 5:
                return "video/hevc";
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return "video/x-vnd.on2.vp9";
            case 7:
                return "video/dolby-vision";
            case 8:
                return "video/av01";
            default:
                return "video/none";
        }
    }

    int a();

    List b();

    List c();

    int g();
}
