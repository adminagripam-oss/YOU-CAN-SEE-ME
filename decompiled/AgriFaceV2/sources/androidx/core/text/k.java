package androidx.core.text;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
/* loaded from: classes.dex */
public abstract class k implements Spannable {

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final TextPaint f3058a;

        /* renamed from: b  reason: collision with root package name */
        private final TextDirectionHeuristic f3059b;

        /* renamed from: c  reason: collision with root package name */
        private final int f3060c;

        /* renamed from: d  reason: collision with root package name */
        private final int f3061d;

        /* renamed from: e  reason: collision with root package name */
        final PrecomputedText.Params f3062e;

        /* renamed from: androidx.core.text.k$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0030a {

            /* renamed from: a  reason: collision with root package name */
            private final TextPaint f3063a;

            /* renamed from: c  reason: collision with root package name */
            private int f3065c = 1;

            /* renamed from: d  reason: collision with root package name */
            private int f3066d = 1;

            /* renamed from: b  reason: collision with root package name */
            private TextDirectionHeuristic f3064b = TextDirectionHeuristics.FIRSTSTRONG_LTR;

            public C0030a(TextPaint textPaint) {
                this.f3063a = textPaint;
            }

            public a a() {
                return new a(this.f3063a, this.f3064b, this.f3065c, this.f3066d);
            }

            public C0030a b(int i8) {
                this.f3065c = i8;
                return this;
            }

            public C0030a c(int i8) {
                this.f3066d = i8;
                return this;
            }

            public C0030a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f3064b = textDirectionHeuristic;
                return this;
            }
        }

        public a(PrecomputedText.Params params) {
            TextPaint textPaint;
            TextDirectionHeuristic textDirection;
            int breakStrategy;
            int hyphenationFrequency;
            textPaint = params.getTextPaint();
            this.f3058a = textPaint;
            textDirection = params.getTextDirection();
            this.f3059b = textDirection;
            breakStrategy = params.getBreakStrategy();
            this.f3060c = breakStrategy;
            hyphenationFrequency = params.getHyphenationFrequency();
            this.f3061d = hyphenationFrequency;
            this.f3062e = Build.VERSION.SDK_INT < 29 ? null : params;
        }

        public boolean a(a aVar) {
            if (this.f3060c == aVar.b() && this.f3061d == aVar.c() && this.f3058a.getTextSize() == aVar.e().getTextSize() && this.f3058a.getTextScaleX() == aVar.e().getTextScaleX() && this.f3058a.getTextSkewX() == aVar.e().getTextSkewX() && this.f3058a.getLetterSpacing() == aVar.e().getLetterSpacing() && TextUtils.equals(this.f3058a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()) && this.f3058a.getFlags() == aVar.e().getFlags() && this.f3058a.getTextLocales().equals(aVar.e().getTextLocales())) {
                return this.f3058a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f3058a.getTypeface().equals(aVar.e().getTypeface());
            }
            return false;
        }

        public int b() {
            return this.f3060c;
        }

        public int c() {
            return this.f3061d;
        }

        public TextDirectionHeuristic d() {
            return this.f3059b;
        }

        public TextPaint e() {
            return this.f3058a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof a) {
                a aVar = (a) obj;
                return a(aVar) && this.f3059b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            return g1.c.b(Float.valueOf(this.f3058a.getTextSize()), Float.valueOf(this.f3058a.getTextScaleX()), Float.valueOf(this.f3058a.getTextSkewX()), Float.valueOf(this.f3058a.getLetterSpacing()), Integer.valueOf(this.f3058a.getFlags()), this.f3058a.getTextLocales(), this.f3058a.getTypeface(), Boolean.valueOf(this.f3058a.isElegantTextHeight()), this.f3059b, Integer.valueOf(this.f3060c), Integer.valueOf(this.f3061d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f3058a.getTextSize());
            sb.append(", textScaleX=" + this.f3058a.getTextScaleX());
            sb.append(", textSkewX=" + this.f3058a.getTextSkewX());
            sb.append(", letterSpacing=" + this.f3058a.getLetterSpacing());
            sb.append(", elegantTextHeight=" + this.f3058a.isElegantTextHeight());
            sb.append(", textLocale=" + this.f3058a.getTextLocales());
            sb.append(", typeface=" + this.f3058a.getTypeface());
            sb.append(", variationSettings=" + this.f3058a.getFontVariationSettings());
            sb.append(", textDir=" + this.f3059b);
            sb.append(", breakStrategy=" + this.f3060c);
            sb.append(", hyphenationFrequency=" + this.f3061d);
            sb.append("}");
            return sb.toString();
        }

        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i8, int i9) {
            PrecomputedText.Params params;
            PrecomputedText.Params.Builder breakStrategy;
            PrecomputedText.Params.Builder hyphenationFrequency;
            PrecomputedText.Params.Builder textDirection;
            if (Build.VERSION.SDK_INT >= 29) {
                breakStrategy = j.a(textPaint).setBreakStrategy(i8);
                hyphenationFrequency = breakStrategy.setHyphenationFrequency(i9);
                textDirection = hyphenationFrequency.setTextDirection(textDirectionHeuristic);
                params = textDirection.build();
            } else {
                params = null;
            }
            this.f3062e = params;
            this.f3058a = textPaint;
            this.f3059b = textDirectionHeuristic;
            this.f3060c = i8;
            this.f3061d = i9;
        }
    }
}
