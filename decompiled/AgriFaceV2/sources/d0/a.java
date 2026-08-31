package d0;

import android.graphics.RectF;
import android.util.Rational;
import android.util.Size;
import java.util.Comparator;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Rational f6351a = new Rational(4, 3);

    /* renamed from: b  reason: collision with root package name */
    public static final Rational f6352b = new Rational(3, 4);

    /* renamed from: c  reason: collision with root package name */
    public static final Rational f6353c = new Rational(16, 9);

    /* renamed from: d  reason: collision with root package name */
    public static final Rational f6354d = new Rational(9, 16);

    /* renamed from: d0.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0076a implements Comparator {

        /* renamed from: a  reason: collision with root package name */
        private final Rational f6355a;

        /* renamed from: b  reason: collision with root package name */
        private final RectF f6356b;

        /* renamed from: c  reason: collision with root package name */
        private final Rational f6357c;

        public C0076a(Rational rational, Rational rational2) {
            this.f6355a = rational;
            this.f6357c = rational2 == null ? new Rational(4, 3) : rational2;
            this.f6356b = d(rational);
        }

        private float b(RectF rectF) {
            return rectF.width() * rectF.height();
        }

        private float c(RectF rectF, RectF rectF2) {
            return (rectF.width() < rectF2.width() ? rectF.width() : rectF2.width()) * (rectF.height() < rectF2.height() ? rectF.height() : rectF2.height());
        }

        private RectF d(Rational rational) {
            return rational.floatValue() == this.f6357c.floatValue() ? new RectF(0.0f, 0.0f, this.f6357c.getNumerator(), this.f6357c.getDenominator()) : rational.floatValue() > this.f6357c.floatValue() ? new RectF(0.0f, 0.0f, this.f6357c.getNumerator(), (rational.getDenominator() * this.f6357c.getNumerator()) / rational.getNumerator()) : new RectF(0.0f, 0.0f, (rational.getNumerator() * this.f6357c.getDenominator()) / rational.getDenominator(), this.f6357c.getDenominator());
        }

        private boolean e(RectF rectF, RectF rectF2) {
            return rectF.width() >= rectF2.width() && rectF.height() >= rectF2.height();
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Rational rational, Rational rational2) {
            if (rational.equals(rational2)) {
                return 0;
            }
            RectF d8 = d(rational);
            RectF d9 = d(rational2);
            boolean e8 = e(d8, this.f6356b);
            boolean e9 = e(d9, this.f6356b);
            if (e8 && e9) {
                return (int) Math.signum(b(d8) - b(d9));
            }
            if (e8) {
                return -1;
            }
            if (e9) {
                return 1;
            }
            return -((int) Math.signum(c(d8, this.f6356b) - c(d9, this.f6356b)));
        }
    }

    public static boolean a(Size size, Rational rational) {
        return b(size, rational, k0.d.f8305c);
    }

    public static boolean b(Size size, Rational rational, Size size2) {
        if (rational == null) {
            return false;
        }
        if (rational.equals(new Rational(size.getWidth(), size.getHeight()))) {
            return true;
        }
        if (k0.d.c(size) >= k0.d.c(size2)) {
            return c(size, rational);
        }
        return false;
    }

    private static boolean c(Size size, Rational rational) {
        int width = size.getWidth();
        int height = size.getHeight();
        Rational rational2 = new Rational(rational.getDenominator(), rational.getNumerator());
        int i8 = width % 16;
        if (i8 == 0 && height % 16 == 0) {
            return d(Math.max(0, height + (-16)), width, rational) || d(Math.max(0, width + (-16)), height, rational2);
        } else if (i8 == 0) {
            return d(height, width, rational);
        } else {
            if (height % 16 == 0) {
                return d(width, height, rational2);
            }
            return false;
        }
    }

    private static boolean d(int i8, int i9, Rational rational) {
        g1.e.a(i9 % 16 == 0);
        double numerator = (i8 * rational.getNumerator()) / rational.getDenominator();
        return numerator > ((double) Math.max(0, i9 + (-16))) && numerator < ((double) (i9 + 16));
    }
}
