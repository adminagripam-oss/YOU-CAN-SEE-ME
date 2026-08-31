package p1;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;
/* loaded from: classes.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    private final b f9997a;

    /* loaded from: classes.dex */
    private static class a extends b {

        /* renamed from: a  reason: collision with root package name */
        private final TextView f9998a;

        /* renamed from: b  reason: collision with root package name */
        private final d f9999b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f10000c = true;

        a(TextView textView) {
            this.f9998a = textView;
            this.f9999b = new d(textView);
        }

        private InputFilter[] d(InputFilter[] inputFilterArr) {
            int length = inputFilterArr.length;
            for (InputFilter inputFilter : inputFilterArr) {
                if (inputFilter == this.f9999b) {
                    return inputFilterArr;
                }
            }
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length + 1];
            System.arraycopy(inputFilterArr, 0, inputFilterArr2, 0, length);
            inputFilterArr2[length] = this.f9999b;
            return inputFilterArr2;
        }

        private SparseArray e(InputFilter[] inputFilterArr) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i8 = 0; i8 < inputFilterArr.length; i8++) {
                InputFilter inputFilter = inputFilterArr[i8];
                if (inputFilter instanceof d) {
                    sparseArray.put(i8, inputFilter);
                }
            }
            return sparseArray;
        }

        private InputFilter[] f(InputFilter[] inputFilterArr) {
            SparseArray e8 = e(inputFilterArr);
            if (e8.size() == 0) {
                return inputFilterArr;
            }
            int length = inputFilterArr.length;
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - e8.size()];
            int i8 = 0;
            for (int i9 = 0; i9 < length; i9++) {
                if (e8.indexOfKey(i9) < 0) {
                    inputFilterArr2[i8] = inputFilterArr[i9];
                    i8++;
                }
            }
            return inputFilterArr2;
        }

        private TransformationMethod h(TransformationMethod transformationMethod) {
            return transformationMethod instanceof h ? ((h) transformationMethod).a() : transformationMethod;
        }

        private void i() {
            this.f9998a.setFilters(a(this.f9998a.getFilters()));
        }

        private TransformationMethod k(TransformationMethod transformationMethod) {
            return ((transformationMethod instanceof h) || (transformationMethod instanceof PasswordTransformationMethod)) ? transformationMethod : new h(transformationMethod);
        }

        @Override // p1.f.b
        InputFilter[] a(InputFilter[] inputFilterArr) {
            return !this.f10000c ? f(inputFilterArr) : d(inputFilterArr);
        }

        @Override // p1.f.b
        void b(boolean z7) {
            if (z7) {
                j();
            }
        }

        @Override // p1.f.b
        void c(boolean z7) {
            this.f10000c = z7;
            j();
            i();
        }

        void g(boolean z7) {
            this.f10000c = z7;
        }

        void j() {
            this.f9998a.setTransformationMethod(l(this.f9998a.getTransformationMethod()));
        }

        TransformationMethod l(TransformationMethod transformationMethod) {
            return this.f10000c ? k(transformationMethod) : h(transformationMethod);
        }
    }

    /* loaded from: classes.dex */
    static class b {
        b() {
        }

        abstract InputFilter[] a(InputFilter[] inputFilterArr);

        abstract void b(boolean z7);

        abstract void c(boolean z7);
    }

    /* loaded from: classes.dex */
    private static class c extends b {

        /* renamed from: a  reason: collision with root package name */
        private final a f10001a;

        c(TextView textView) {
            this.f10001a = new a(textView);
        }

        private boolean d() {
            return !androidx.emoji2.text.e.h();
        }

        @Override // p1.f.b
        InputFilter[] a(InputFilter[] inputFilterArr) {
            return d() ? inputFilterArr : this.f10001a.a(inputFilterArr);
        }

        @Override // p1.f.b
        void b(boolean z7) {
            if (d()) {
                return;
            }
            this.f10001a.b(z7);
        }

        @Override // p1.f.b
        void c(boolean z7) {
            if (d()) {
                this.f10001a.g(z7);
            } else {
                this.f10001a.c(z7);
            }
        }
    }

    public f(TextView textView, boolean z7) {
        g1.e.k(textView, "textView cannot be null");
        this.f9997a = !z7 ? new c(textView) : new a(textView);
    }

    public InputFilter[] a(InputFilter[] inputFilterArr) {
        return this.f9997a.a(inputFilterArr);
    }

    public void b(boolean z7) {
        this.f9997a.b(z7);
    }

    public void c(boolean z7) {
        this.f9997a.c(z7);
    }
}
