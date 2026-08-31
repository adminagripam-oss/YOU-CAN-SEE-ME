package androidx.appcompat.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.app.c;
/* loaded from: classes.dex */
public class a0 extends Spinner {

    /* renamed from: i  reason: collision with root package name */
    private static final int[] f2188i = {16843505};

    /* renamed from: a  reason: collision with root package name */
    private final androidx.appcompat.widget.e f2189a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f2190b;

    /* renamed from: c  reason: collision with root package name */
    private n0 f2191c;

    /* renamed from: d  reason: collision with root package name */
    private SpinnerAdapter f2192d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f2193e;

    /* renamed from: f  reason: collision with root package name */
    private j f2194f;

    /* renamed from: g  reason: collision with root package name */
    int f2195g;

    /* renamed from: h  reason: collision with root package name */
    final Rect f2196h;

    /* loaded from: classes.dex */
    class a extends n0 {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ h f2197j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(View view, h hVar) {
            super(view);
            this.f2197j = hVar;
        }

        @Override // androidx.appcompat.widget.n0
        public androidx.appcompat.view.menu.p b() {
            return this.f2197j;
        }

        @Override // androidx.appcompat.widget.n0
        public boolean c() {
            if (a0.this.getInternalPopup().c()) {
                return true;
            }
            a0.this.b();
            return true;
        }
    }

    /* loaded from: classes.dex */
    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!a0.this.getInternalPopup().c()) {
                a0.this.b();
            }
            ViewTreeObserver viewTreeObserver = a0.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                c.a(viewTreeObserver, this);
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class c {
        static void a(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class d {
        static int a(View view) {
            return view.getTextAlignment();
        }

        static int b(View view) {
            return view.getTextDirection();
        }

        static void c(View view, int i8) {
            view.setTextAlignment(i8);
        }

        static void d(View view, int i8) {
            view.setTextDirection(i8);
        }
    }

    /* loaded from: classes.dex */
    private static final class e {
        static void a(ThemedSpinnerAdapter themedSpinnerAdapter, Resources.Theme theme) {
            if (g1.c.a(themedSpinnerAdapter.getDropDownViewTheme(), theme)) {
                return;
            }
            themedSpinnerAdapter.setDropDownViewTheme(theme);
        }
    }

    /* loaded from: classes.dex */
    class f implements j, DialogInterface.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        androidx.appcompat.app.c f2200a;

        /* renamed from: b  reason: collision with root package name */
        private ListAdapter f2201b;

        /* renamed from: c  reason: collision with root package name */
        private CharSequence f2202c;

        f() {
        }

        @Override // androidx.appcompat.widget.a0.j
        public boolean c() {
            androidx.appcompat.app.c cVar = this.f2200a;
            if (cVar != null) {
                return cVar.isShowing();
            }
            return false;
        }

        @Override // androidx.appcompat.widget.a0.j
        public void d(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.a0.j
        public void dismiss() {
            androidx.appcompat.app.c cVar = this.f2200a;
            if (cVar != null) {
                cVar.dismiss();
                this.f2200a = null;
            }
        }

        @Override // androidx.appcompat.widget.a0.j
        public int e() {
            return 0;
        }

        @Override // androidx.appcompat.widget.a0.j
        public Drawable g() {
            return null;
        }

        @Override // androidx.appcompat.widget.a0.j
        public void i(CharSequence charSequence) {
            this.f2202c = charSequence;
        }

        @Override // androidx.appcompat.widget.a0.j
        public void j(int i8) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.a0.j
        public void k(int i8) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.a0.j
        public void l(int i8) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.a0.j
        public void m(int i8, int i9) {
            if (this.f2201b == null) {
                return;
            }
            c.a aVar = new c.a(a0.this.getPopupContext());
            CharSequence charSequence = this.f2202c;
            if (charSequence != null) {
                aVar.n(charSequence);
            }
            androidx.appcompat.app.c a8 = aVar.m(this.f2201b, a0.this.getSelectedItemPosition(), this).a();
            this.f2200a = a8;
            ListView i10 = a8.i();
            d.d(i10, i8);
            d.c(i10, i9);
            this.f2200a.show();
        }

        @Override // androidx.appcompat.widget.a0.j
        public int n() {
            return 0;
        }

        @Override // androidx.appcompat.widget.a0.j
        public CharSequence o() {
            return this.f2202c;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i8) {
            a0.this.setSelection(i8);
            if (a0.this.getOnItemClickListener() != null) {
                a0.this.performItemClick(null, i8, this.f2201b.getItemId(i8));
            }
            dismiss();
        }

        @Override // androidx.appcompat.widget.a0.j
        public void p(ListAdapter listAdapter) {
            this.f2201b = listAdapter;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class g implements ListAdapter, SpinnerAdapter {

        /* renamed from: a  reason: collision with root package name */
        private SpinnerAdapter f2204a;

        /* renamed from: b  reason: collision with root package name */
        private ListAdapter f2205b;

        public g(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f2204a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f2205b = (ListAdapter) spinnerAdapter;
            }
            if (theme == null || !(spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                return;
            }
            e.a((ThemedSpinnerAdapter) spinnerAdapter, theme);
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f2205b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f2204a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i8, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f2204a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i8, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i8) {
            SpinnerAdapter spinnerAdapter = this.f2204a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i8);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i8) {
            SpinnerAdapter spinnerAdapter = this.f2204a;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i8);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i8) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i8, View view, ViewGroup viewGroup) {
            return getDropDownView(i8, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f2204a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i8) {
            ListAdapter listAdapter = this.f2205b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i8);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f2204a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f2204a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* loaded from: classes.dex */
    class h extends p0 implements j {
        private CharSequence I;
        ListAdapter J;
        private final Rect K;
        private int L;

        /* loaded from: classes.dex */
        class a implements AdapterView.OnItemClickListener {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ a0 f2206a;

            a(a0 a0Var) {
                this.f2206a = a0Var;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i8, long j8) {
                a0.this.setSelection(i8);
                if (a0.this.getOnItemClickListener() != null) {
                    h hVar = h.this;
                    a0.this.performItemClick(view, i8, hVar.J.getItemId(i8));
                }
                h.this.dismiss();
            }
        }

        /* loaded from: classes.dex */
        class b implements ViewTreeObserver.OnGlobalLayoutListener {
            b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                h hVar = h.this;
                if (!hVar.Q(a0.this)) {
                    h.this.dismiss();
                    return;
                }
                h.this.O();
                h.super.a();
            }
        }

        /* loaded from: classes.dex */
        class c implements PopupWindow.OnDismissListener {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f2209a;

            c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f2209a = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = a0.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f2209a);
                }
            }
        }

        public h(Context context, AttributeSet attributeSet, int i8) {
            super(context, attributeSet, i8);
            this.K = new Rect();
            z(a0.this);
            F(true);
            K(0);
            H(new a(a0.this));
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x009a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void O() {
            /*
                r8 = this;
                android.graphics.drawable.Drawable r0 = r8.g()
                if (r0 == 0) goto L24
                androidx.appcompat.widget.a0 r1 = androidx.appcompat.widget.a0.this
                android.graphics.Rect r1 = r1.f2196h
                r0.getPadding(r1)
                androidx.appcompat.widget.a0 r0 = androidx.appcompat.widget.a0.this
                boolean r0 = androidx.appcompat.widget.i1.b(r0)
                if (r0 == 0) goto L1c
                androidx.appcompat.widget.a0 r0 = androidx.appcompat.widget.a0.this
                android.graphics.Rect r0 = r0.f2196h
                int r0 = r0.right
                goto L2e
            L1c:
                androidx.appcompat.widget.a0 r0 = androidx.appcompat.widget.a0.this
                android.graphics.Rect r0 = r0.f2196h
                int r0 = r0.left
                int r0 = -r0
                goto L2e
            L24:
                androidx.appcompat.widget.a0 r0 = androidx.appcompat.widget.a0.this
                android.graphics.Rect r0 = r0.f2196h
                r1 = 0
                r0.right = r1
                r0.left = r1
                r0 = r1
            L2e:
                androidx.appcompat.widget.a0 r1 = androidx.appcompat.widget.a0.this
                int r1 = r1.getPaddingLeft()
                androidx.appcompat.widget.a0 r2 = androidx.appcompat.widget.a0.this
                int r2 = r2.getPaddingRight()
                androidx.appcompat.widget.a0 r3 = androidx.appcompat.widget.a0.this
                int r3 = r3.getWidth()
                androidx.appcompat.widget.a0 r4 = androidx.appcompat.widget.a0.this
                int r5 = r4.f2195g
                r6 = -2
                if (r5 != r6) goto L78
                android.widget.ListAdapter r5 = r8.J
                android.widget.SpinnerAdapter r5 = (android.widget.SpinnerAdapter) r5
                android.graphics.drawable.Drawable r6 = r8.g()
                int r4 = r4.a(r5, r6)
                androidx.appcompat.widget.a0 r5 = androidx.appcompat.widget.a0.this
                android.content.Context r5 = r5.getContext()
                android.content.res.Resources r5 = r5.getResources()
                android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
                int r5 = r5.widthPixels
                androidx.appcompat.widget.a0 r6 = androidx.appcompat.widget.a0.this
                android.graphics.Rect r6 = r6.f2196h
                int r7 = r6.left
                int r5 = r5 - r7
                int r6 = r6.right
                int r5 = r5 - r6
                if (r4 <= r5) goto L70
                r4 = r5
            L70:
                int r5 = r3 - r1
                int r5 = r5 - r2
                int r4 = java.lang.Math.max(r4, r5)
                goto L7e
            L78:
                r4 = -1
                if (r5 != r4) goto L82
                int r4 = r3 - r1
                int r4 = r4 - r2
            L7e:
                r8.B(r4)
                goto L85
            L82:
                r8.B(r5)
            L85:
                androidx.appcompat.widget.a0 r4 = androidx.appcompat.widget.a0.this
                boolean r4 = androidx.appcompat.widget.i1.b(r4)
                if (r4 == 0) goto L9a
                int r3 = r3 - r2
                int r1 = r8.v()
                int r3 = r3 - r1
                int r1 = r8.P()
                int r3 = r3 - r1
                int r0 = r0 + r3
                goto La0
            L9a:
                int r2 = r8.P()
                int r1 = r1 + r2
                int r0 = r0 + r1
            La0:
                r8.l(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.a0.h.O():void");
        }

        public int P() {
            return this.L;
        }

        boolean Q(View view) {
            return androidx.core.view.p0.y(view) && view.getGlobalVisibleRect(this.K);
        }

        @Override // androidx.appcompat.widget.a0.j
        public void i(CharSequence charSequence) {
            this.I = charSequence;
        }

        @Override // androidx.appcompat.widget.a0.j
        public void k(int i8) {
            this.L = i8;
        }

        @Override // androidx.appcompat.widget.a0.j
        public void m(int i8, int i9) {
            ViewTreeObserver viewTreeObserver;
            boolean c8 = c();
            O();
            E(2);
            super.a();
            ListView h8 = h();
            h8.setChoiceMode(1);
            d.d(h8, i8);
            d.c(h8, i9);
            L(a0.this.getSelectedItemPosition());
            if (c8 || (viewTreeObserver = a0.this.getViewTreeObserver()) == null) {
                return;
            }
            b bVar = new b();
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
            G(new c(bVar));
        }

        @Override // androidx.appcompat.widget.a0.j
        public CharSequence o() {
            return this.I;
        }

        @Override // androidx.appcompat.widget.p0, androidx.appcompat.widget.a0.j
        public void p(ListAdapter listAdapter) {
            super.p(listAdapter);
            this.J = listAdapter;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class i extends View.BaseSavedState {
        public static final Parcelable.Creator<i> CREATOR = new a();

        /* renamed from: a  reason: collision with root package name */
        boolean f2211a;

        /* loaded from: classes.dex */
        class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public i createFromParcel(Parcel parcel) {
                return new i(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public i[] newArray(int i8) {
                return new i[i8];
            }
        }

        i(Parcel parcel) {
            super(parcel);
            this.f2211a = parcel.readByte() != 0;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeByte(this.f2211a ? (byte) 1 : (byte) 0);
        }

        i(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface j {
        boolean c();

        void d(Drawable drawable);

        void dismiss();

        int e();

        Drawable g();

        void i(CharSequence charSequence);

        void j(int i8);

        void k(int i8);

        void l(int i8);

        void m(int i8, int i9);

        int n();

        CharSequence o();

        void p(ListAdapter listAdapter);
    }

    public a0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.G);
    }

    int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i8 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i9 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i8) {
                view = null;
                i8 = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i9 = Math.max(i9, view.getMeasuredWidth());
        }
        if (drawable != null) {
            drawable.getPadding(this.f2196h);
            Rect rect = this.f2196h;
            return i9 + rect.left + rect.right;
        }
        return i9;
    }

    void b() {
        this.f2194f.m(d.b(this), d.a(this));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        androidx.appcompat.widget.e eVar = this.f2189a;
        if (eVar != null) {
            eVar.b();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        j jVar = this.f2194f;
        return jVar != null ? jVar.e() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        j jVar = this.f2194f;
        return jVar != null ? jVar.n() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f2194f != null ? this.f2195g : super.getDropDownWidth();
    }

    final j getInternalPopup() {
        return this.f2194f;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        j jVar = this.f2194f;
        return jVar != null ? jVar.g() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f2190b;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        j jVar = this.f2194f;
        return jVar != null ? jVar.o() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        androidx.appcompat.widget.e eVar = this.f2189a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        androidx.appcompat.widget.e eVar = this.f2189a;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        j jVar = this.f2194f;
        if (jVar == null || !jVar.c()) {
            return;
        }
        this.f2194f.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    protected void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        if (this.f2194f == null || View.MeasureSpec.getMode(i8) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i8)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        i iVar = (i) parcelable;
        super.onRestoreInstanceState(iVar.getSuperState());
        if (!iVar.f2211a || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new b());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        i iVar = new i(super.onSaveInstanceState());
        j jVar = this.f2194f;
        iVar.f2211a = jVar != null && jVar.c();
        return iVar;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        n0 n0Var = this.f2191c;
        if (n0Var == null || !n0Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        j jVar = this.f2194f;
        if (jVar != null) {
            if (jVar.c()) {
                return true;
            }
            b();
            return true;
        }
        return super.performClick();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        androidx.appcompat.widget.e eVar = this.f2189a;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        androidx.appcompat.widget.e eVar = this.f2189a;
        if (eVar != null) {
            eVar.g(i8);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i8) {
        j jVar = this.f2194f;
        if (jVar == null) {
            super.setDropDownHorizontalOffset(i8);
            return;
        }
        jVar.k(i8);
        this.f2194f.l(i8);
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i8) {
        j jVar = this.f2194f;
        if (jVar != null) {
            jVar.j(i8);
        } else {
            super.setDropDownVerticalOffset(i8);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i8) {
        if (this.f2194f != null) {
            this.f2195g = i8;
        } else {
            super.setDropDownWidth(i8);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        j jVar = this.f2194f;
        if (jVar != null) {
            jVar.d(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i8) {
        setPopupBackgroundDrawable(h.a.b(getPopupContext(), i8));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        j jVar = this.f2194f;
        if (jVar != null) {
            jVar.i(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        androidx.appcompat.widget.e eVar = this.f2189a;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        androidx.appcompat.widget.e eVar = this.f2189a;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    public a0(Context context, AttributeSet attributeSet, int i8) {
        this(context, attributeSet, i8, -1);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f2193e) {
            this.f2192d = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f2194f != null) {
            Context context = this.f2190b;
            if (context == null) {
                context = getContext();
            }
            this.f2194f.p(new g(spinnerAdapter, context.getTheme()));
        }
    }

    public a0(Context context, AttributeSet attributeSet, int i8, int i9) {
        this(context, attributeSet, i8, i9, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x005e, code lost:
        if (r11 == null) goto L8;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v7, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r6v0, types: [androidx.appcompat.widget.a0, android.view.View] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public a0(android.content.Context r7, android.util.AttributeSet r8, int r9, int r10, android.content.res.Resources.Theme r11) {
        /*
            r6 = this;
            r6.<init>(r7, r8, r9)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r6.f2196h = r0
            android.content.Context r0 = r6.getContext()
            androidx.appcompat.widget.x0.a(r6, r0)
            int[] r0 = g.j.f7041x2
            r1 = 0
            androidx.appcompat.widget.b1 r0 = androidx.appcompat.widget.b1.u(r7, r8, r0, r9, r1)
            androidx.appcompat.widget.e r2 = new androidx.appcompat.widget.e
            r2.<init>(r6)
            r6.f2189a = r2
            if (r11 == 0) goto L29
            androidx.appcompat.view.d r2 = new androidx.appcompat.view.d
            r2.<init>(r7, r11)
        L26:
            r6.f2190b = r2
            goto L39
        L29:
            int r11 = g.j.C2
            int r11 = r0.m(r11, r1)
            if (r11 == 0) goto L37
            androidx.appcompat.view.d r2 = new androidx.appcompat.view.d
            r2.<init>(r7, r11)
            goto L26
        L37:
            r6.f2190b = r7
        L39:
            r11 = -1
            r2 = 0
            if (r10 != r11) goto L69
            int[] r11 = androidx.appcompat.widget.a0.f2188i     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            android.content.res.TypedArray r11 = r7.obtainStyledAttributes(r8, r11, r9, r1)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            boolean r3 = r11.hasValue(r1)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L61
            if (r3 == 0) goto L4d
            int r10 = r11.getInt(r1, r1)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L61
        L4d:
            r11.recycle()
            goto L69
        L51:
            r3 = move-exception
            goto L57
        L53:
            r7 = move-exception
            goto L63
        L55:
            r3 = move-exception
            r11 = r2
        L57:
            java.lang.String r4 = "AppCompatSpinner"
            java.lang.String r5 = "Could not read android:spinnerMode"
            android.util.Log.i(r4, r5, r3)     // Catch: java.lang.Throwable -> L61
            if (r11 == 0) goto L69
            goto L4d
        L61:
            r7 = move-exception
            r2 = r11
        L63:
            if (r2 == 0) goto L68
            r2.recycle()
        L68:
            throw r7
        L69:
            r11 = 1
            if (r10 == 0) goto La6
            if (r10 == r11) goto L6f
            goto Lb6
        L6f:
            androidx.appcompat.widget.a0$h r10 = new androidx.appcompat.widget.a0$h
            android.content.Context r3 = r6.f2190b
            r10.<init>(r3, r8, r9)
            android.content.Context r3 = r6.f2190b
            int[] r4 = g.j.f7041x2
            androidx.appcompat.widget.b1 r1 = androidx.appcompat.widget.b1.u(r3, r8, r4, r9, r1)
            int r3 = g.j.B2
            r4 = -2
            int r3 = r1.l(r3, r4)
            r6.f2195g = r3
            int r3 = g.j.f7051z2
            android.graphics.drawable.Drawable r3 = r1.f(r3)
            r10.d(r3)
            int r3 = g.j.A2
            java.lang.String r3 = r0.n(r3)
            r10.i(r3)
            r1.v()
            r6.f2194f = r10
            androidx.appcompat.widget.a0$a r1 = new androidx.appcompat.widget.a0$a
            r1.<init>(r6, r10)
            r6.f2191c = r1
            goto Lb6
        La6:
            androidx.appcompat.widget.a0$f r10 = new androidx.appcompat.widget.a0$f
            r10.<init>()
            r6.f2194f = r10
            int r1 = g.j.A2
            java.lang.String r1 = r0.n(r1)
            r10.i(r1)
        Lb6:
            int r10 = g.j.f7046y2
            java.lang.CharSequence[] r10 = r0.p(r10)
            if (r10 == 0) goto Lce
            android.widget.ArrayAdapter r1 = new android.widget.ArrayAdapter
            r3 = 17367048(0x1090008, float:2.5162948E-38)
            r1.<init>(r7, r3, r10)
            int r7 = g.g.f6906s
            r1.setDropDownViewResource(r7)
            r6.setAdapter(r1)
        Lce:
            r0.v()
            r6.f2193e = r11
            android.widget.SpinnerAdapter r7 = r6.f2192d
            if (r7 == 0) goto Ldc
            r6.setAdapter(r7)
            r6.f2192d = r2
        Ldc:
            androidx.appcompat.widget.e r7 = r6.f2189a
            r7.e(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.a0.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }
}
