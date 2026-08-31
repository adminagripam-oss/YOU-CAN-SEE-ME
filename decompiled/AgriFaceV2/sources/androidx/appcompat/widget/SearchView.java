package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class SearchView extends o0 implements androidx.appcompat.view.c {

    /* renamed from: n0  reason: collision with root package name */
    static final o f2087n0;
    private Rect A;
    private int[] B;
    private int[] C;
    private final ImageView D;
    private final Drawable E;
    private final int F;
    private final int G;
    private final Intent H;
    private final Intent I;
    private final CharSequence J;
    View.OnFocusChangeListener K;
    private View.OnClickListener L;
    private boolean M;
    private boolean N;
    l1.a O;
    private boolean P;
    private CharSequence Q;
    private boolean R;
    private boolean S;
    private int T;
    private boolean U;
    private CharSequence V;
    private CharSequence W;

    /* renamed from: a0  reason: collision with root package name */
    private boolean f2088a0;

    /* renamed from: b0  reason: collision with root package name */
    private int f2089b0;

    /* renamed from: c0  reason: collision with root package name */
    SearchableInfo f2090c0;

    /* renamed from: d0  reason: collision with root package name */
    private Bundle f2091d0;

    /* renamed from: e0  reason: collision with root package name */
    private final Runnable f2092e0;

    /* renamed from: f0  reason: collision with root package name */
    private Runnable f2093f0;

    /* renamed from: g0  reason: collision with root package name */
    private final WeakHashMap f2094g0;

    /* renamed from: h0  reason: collision with root package name */
    private final View.OnClickListener f2095h0;

    /* renamed from: i0  reason: collision with root package name */
    View.OnKeyListener f2096i0;

    /* renamed from: j0  reason: collision with root package name */
    private final TextView.OnEditorActionListener f2097j0;

    /* renamed from: k0  reason: collision with root package name */
    private final AdapterView.OnItemClickListener f2098k0;

    /* renamed from: l0  reason: collision with root package name */
    private final AdapterView.OnItemSelectedListener f2099l0;

    /* renamed from: m0  reason: collision with root package name */
    private TextWatcher f2100m0;

    /* renamed from: p  reason: collision with root package name */
    final SearchAutoComplete f2101p;

    /* renamed from: q  reason: collision with root package name */
    private final View f2102q;

    /* renamed from: r  reason: collision with root package name */
    private final View f2103r;

    /* renamed from: s  reason: collision with root package name */
    private final View f2104s;

    /* renamed from: t  reason: collision with root package name */
    final ImageView f2105t;

    /* renamed from: u  reason: collision with root package name */
    final ImageView f2106u;

    /* renamed from: v  reason: collision with root package name */
    final ImageView f2107v;

    /* renamed from: w  reason: collision with root package name */
    final ImageView f2108w;

    /* renamed from: x  reason: collision with root package name */
    private final View f2109x;

    /* renamed from: y  reason: collision with root package name */
    private q f2110y;

    /* renamed from: z  reason: collision with root package name */
    private Rect f2111z;

    /* loaded from: classes.dex */
    public static class SearchAutoComplete extends androidx.appcompat.widget.d {

        /* renamed from: e  reason: collision with root package name */
        private int f2112e;

        /* renamed from: f  reason: collision with root package name */
        private SearchView f2113f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f2114g;

        /* renamed from: h  reason: collision with root package name */
        final Runnable f2115h;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.d();
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, g.a.f6804m);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i8 = configuration.screenWidthDp;
            int i9 = configuration.screenHeightDp;
            if (i8 < 960 || i9 < 720 || configuration.orientation != 2) {
                if (i8 < 600) {
                    return (i8 < 640 || i9 < 480) ? 160 : 192;
                }
                return 192;
            }
            return 256;
        }

        void b() {
            if (Build.VERSION.SDK_INT < 29) {
                SearchView.f2087n0.c(this);
                return;
            }
            k.b(this, 1);
            if (enoughToFilter()) {
                showDropDown();
            }
        }

        boolean c() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        void d() {
            if (this.f2114g) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f2114g = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f2112e <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.d, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f2114g) {
                removeCallbacks(this.f2115h);
                post(this.f2115h);
            }
            return onCreateInputConnection;
        }

        @Override // android.view.View
        protected void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected void onFocusChanged(boolean z7, int i8, Rect rect) {
            super.onFocusChanged(z7, i8, rect);
            this.f2113f.X();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i8, KeyEvent keyEvent) {
            if (i8 == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f2113f.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i8, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z7) {
            super.onWindowFocusChanged(z7);
            if (z7 && this.f2113f.hasFocus() && getVisibility() == 0) {
                this.f2114g = true;
                if (SearchView.K(getContext())) {
                    b();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        protected void replaceText(CharSequence charSequence) {
        }

        void setImeVisibility(boolean z7) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z7) {
                this.f2114g = false;
                removeCallbacks(this.f2115h);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (!inputMethodManager.isActive(this)) {
                this.f2114g = true;
            } else {
                this.f2114g = false;
                removeCallbacks(this.f2115h);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        void setSearchView(SearchView searchView) {
            this.f2113f = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i8) {
            super.setThreshold(i8);
            this.f2112e = i8;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i8) {
            super(context, attributeSet, i8);
            this.f2115h = new a();
            this.f2112e = getThreshold();
        }
    }

    /* loaded from: classes.dex */
    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
            SearchView.this.W(charSequence);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.d0();
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l1.a aVar = SearchView.this.O;
            if (aVar instanceof w0) {
                aVar.a(null);
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements View.OnFocusChangeListener {
        d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z7) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.K;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z7);
            }
        }
    }

    /* loaded from: classes.dex */
    class e implements View.OnLayoutChangeListener {
        e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
            SearchView.this.z();
        }
    }

    /* loaded from: classes.dex */
    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.f2105t) {
                searchView.T();
            } else if (view == searchView.f2107v) {
                searchView.P();
            } else if (view == searchView.f2106u) {
                searchView.U();
            } else if (view == searchView.f2108w) {
                searchView.Y();
            } else if (view == searchView.f2101p) {
                searchView.F();
            }
        }
    }

    /* loaded from: classes.dex */
    class g implements View.OnKeyListener {
        g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i8, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.f2090c0 == null) {
                return false;
            }
            if (!searchView.f2101p.isPopupShowing() || SearchView.this.f2101p.getListSelection() == -1) {
                if (!SearchView.this.f2101p.c() && keyEvent.hasNoModifiers() && keyEvent.getAction() == 1 && i8 == 66) {
                    view.cancelLongPress();
                    SearchView searchView2 = SearchView.this;
                    searchView2.N(0, null, searchView2.f2101p.getText().toString());
                    return true;
                }
                return false;
            }
            return SearchView.this.V(view, i8, keyEvent);
        }
    }

    /* loaded from: classes.dex */
    class h implements TextView.OnEditorActionListener {
        h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
            SearchView.this.U();
            return true;
        }
    }

    /* loaded from: classes.dex */
    class i implements AdapterView.OnItemClickListener {
        i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i8, long j8) {
            SearchView.this.Q(i8, 0, null);
        }
    }

    /* loaded from: classes.dex */
    class j implements AdapterView.OnItemSelectedListener {
        j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i8, long j8) {
            SearchView.this.R(i8);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class k {
        static void a(AutoCompleteTextView autoCompleteTextView) {
            autoCompleteTextView.refreshAutoCompleteResults();
        }

        static void b(SearchAutoComplete searchAutoComplete, int i8) {
            searchAutoComplete.setInputMethodMode(i8);
        }
    }

    /* loaded from: classes.dex */
    public interface l {
    }

    /* loaded from: classes.dex */
    public interface m {
    }

    /* loaded from: classes.dex */
    public interface n {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class o {

        /* renamed from: a  reason: collision with root package name */
        private Method f2127a;

        /* renamed from: b  reason: collision with root package name */
        private Method f2128b;

        /* renamed from: c  reason: collision with root package name */
        private Method f2129c;

        o() {
            this.f2127a = null;
            this.f2128b = null;
            this.f2129c = null;
            d();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f2127a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f2128b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f2129c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        private static void d() {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f2128b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void b(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f2127a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void c(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f2129c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class p extends m1.a {
        public static final Parcelable.Creator<p> CREATOR = new a();

        /* renamed from: c  reason: collision with root package name */
        boolean f2130c;

        /* loaded from: classes.dex */
        class a implements Parcelable.ClassLoaderCreator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public p createFromParcel(Parcel parcel) {
                return new p(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public p createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new p(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public p[] newArray(int i8) {
                return new p[i8];
            }
        }

        public p(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2130c = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f2130c + "}";
        }

        @Override // m1.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeValue(Boolean.valueOf(this.f2130c));
        }

        p(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* loaded from: classes.dex */
    private static class q extends TouchDelegate {

        /* renamed from: a  reason: collision with root package name */
        private final View f2131a;

        /* renamed from: b  reason: collision with root package name */
        private final Rect f2132b;

        /* renamed from: c  reason: collision with root package name */
        private final Rect f2133c;

        /* renamed from: d  reason: collision with root package name */
        private final Rect f2134d;

        /* renamed from: e  reason: collision with root package name */
        private final int f2135e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f2136f;

        public q(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f2135e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f2132b = new Rect();
            this.f2134d = new Rect();
            this.f2133c = new Rect();
            a(rect, rect2);
            this.f2131a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f2132b.set(rect);
            this.f2134d.set(rect);
            Rect rect3 = this.f2134d;
            int i8 = this.f2135e;
            rect3.inset(-i8, -i8);
            this.f2133c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z7;
            float f8;
            int i8;
            boolean z8;
            int x7 = (int) motionEvent.getX();
            int y7 = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z9 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z8 = this.f2136f;
                    if (z8 && !this.f2134d.contains(x7, y7)) {
                        z9 = z8;
                        z7 = false;
                    }
                } else {
                    if (action == 3) {
                        z8 = this.f2136f;
                        this.f2136f = false;
                    }
                    z7 = true;
                    z9 = false;
                }
                z9 = z8;
                z7 = true;
            } else {
                if (this.f2132b.contains(x7, y7)) {
                    this.f2136f = true;
                    z7 = true;
                }
                z7 = true;
                z9 = false;
            }
            if (z9) {
                if (!z7 || this.f2133c.contains(x7, y7)) {
                    Rect rect = this.f2133c;
                    f8 = x7 - rect.left;
                    i8 = y7 - rect.top;
                } else {
                    f8 = this.f2131a.getWidth() / 2;
                    i8 = this.f2131a.getHeight() / 2;
                }
                motionEvent.setLocation(f8, i8);
                return this.f2131a.dispatchTouchEvent(motionEvent);
            }
            return false;
        }
    }

    static {
        f2087n0 = Build.VERSION.SDK_INT < 29 ? new o() : null;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private Intent A(String str, Uri uri, String str2, String str3, int i8, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.W);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f2091d0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i8 != 0) {
            intent.putExtra("action_key", i8);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.f2090c0.getSearchActivity());
        return intent;
    }

    private Intent B(Cursor cursor, int i8, String str) {
        int i9;
        String o8;
        try {
            String o9 = w0.o(cursor, "suggest_intent_action");
            if (o9 == null) {
                o9 = this.f2090c0.getSuggestIntentAction();
            }
            if (o9 == null) {
                o9 = "android.intent.action.SEARCH";
            }
            String str2 = o9;
            String o10 = w0.o(cursor, "suggest_intent_data");
            if (o10 == null) {
                o10 = this.f2090c0.getSuggestIntentData();
            }
            if (o10 != null && (o8 = w0.o(cursor, "suggest_intent_data_id")) != null) {
                o10 = o10 + "/" + Uri.encode(o8);
            }
            return A(str2, o10 == null ? null : Uri.parse(o10), w0.o(cursor, "suggest_intent_extra_data"), w0.o(cursor, "suggest_intent_query"), i8, str);
        } catch (RuntimeException e8) {
            try {
                i9 = cursor.getPosition();
            } catch (RuntimeException unused) {
                i9 = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + i9 + " returned exception.", e8);
            return null;
        }
    }

    private Intent C(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f2091d0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent D(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    private void E() {
        this.f2101p.dismissDropDown();
    }

    private void G(View view, Rect rect) {
        view.getLocationInWindow(this.B);
        getLocationInWindow(this.C);
        int[] iArr = this.B;
        int i8 = iArr[1];
        int[] iArr2 = this.C;
        int i9 = i8 - iArr2[1];
        int i10 = iArr[0] - iArr2[0];
        rect.set(i10, i9, view.getWidth() + i10, view.getHeight() + i9);
    }

    private CharSequence H(CharSequence charSequence) {
        if (!this.M || this.E == null) {
            return charSequence;
        }
        int textSize = (int) (this.f2101p.getTextSize() * 1.25d);
        this.E.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.E), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private boolean I() {
        SearchableInfo searchableInfo = this.f2090c0;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = this.f2090c0.getVoiceSearchLaunchWebSearch() ? this.H : this.f2090c0.getVoiceSearchLaunchRecognizer() ? this.I : null;
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    static boolean K(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private boolean L() {
        return (this.P || this.U) && !J();
    }

    private void M(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e8) {
            Log.e("SearchView", "Failed launch activity: " + intent, e8);
        }
    }

    private boolean O(int i8, int i9, String str) {
        Cursor b8 = this.O.b();
        if (b8 == null || !b8.moveToPosition(i8)) {
            return false;
        }
        M(B(b8, i9, str));
        return true;
    }

    private void Z() {
        post(this.f2092e0);
    }

    private void a0(int i8) {
        CharSequence c8;
        Editable text = this.f2101p.getText();
        Cursor b8 = this.O.b();
        if (b8 == null) {
            return;
        }
        if (!b8.moveToPosition(i8) || (c8 = this.O.c(b8)) == null) {
            setQuery(text);
        } else {
            setQuery(c8);
        }
    }

    private void c0() {
        boolean z7 = true;
        boolean z8 = !TextUtils.isEmpty(this.f2101p.getText());
        if (!z8 && (!this.M || this.f2088a0)) {
            z7 = false;
        }
        this.f2107v.setVisibility(z7 ? 0 : 8);
        Drawable drawable = this.f2107v.getDrawable();
        if (drawable != null) {
            drawable.setState(z8 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    private void e0() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.f2101p;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(H(queryHint));
    }

    private void f0() {
        this.f2101p.setThreshold(this.f2090c0.getSuggestThreshold());
        this.f2101p.setImeOptions(this.f2090c0.getImeOptions());
        int inputType = this.f2090c0.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.f2090c0.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.f2101p.setInputType(inputType);
        l1.a aVar = this.O;
        if (aVar != null) {
            aVar.a(null);
        }
        if (this.f2090c0.getSuggestAuthority() != null) {
            w0 w0Var = new w0(getContext(), this, this.f2090c0, this.f2094g0);
            this.O = w0Var;
            this.f2101p.setAdapter(w0Var);
            ((w0) this.O).x(this.R ? 2 : 1);
        }
    }

    private void g0() {
        this.f2104s.setVisibility((L() && (this.f2106u.getVisibility() == 0 || this.f2108w.getVisibility() == 0)) ? 0 : 8);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(g.d.f6831e);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(g.d.f6832f);
    }

    private void h0(boolean z7) {
        this.f2106u.setVisibility((this.P && L() && hasFocus() && (z7 || !this.U)) ? 0 : 8);
    }

    private void i0(boolean z7) {
        this.N = z7;
        int i8 = 0;
        int i9 = z7 ? 0 : 8;
        boolean z8 = !TextUtils.isEmpty(this.f2101p.getText());
        this.f2105t.setVisibility(i9);
        h0(z8);
        this.f2102q.setVisibility(z7 ? 8 : 0);
        if (this.D.getDrawable() == null || this.M) {
            i8 = 8;
        }
        this.D.setVisibility(i8);
        c0();
        j0(!z8);
        g0();
    }

    private void j0(boolean z7) {
        int i8 = 8;
        if (this.U && !J() && z7) {
            this.f2106u.setVisibility(8);
            i8 = 0;
        }
        this.f2108w.setVisibility(i8);
    }

    private void setQuery(CharSequence charSequence) {
        this.f2101p.setText(charSequence);
        this.f2101p.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    void F() {
        if (Build.VERSION.SDK_INT >= 29) {
            k.a(this.f2101p);
            return;
        }
        o oVar = f2087n0;
        oVar.b(this.f2101p);
        oVar.a(this.f2101p);
    }

    public boolean J() {
        return this.N;
    }

    void N(int i8, String str, String str2) {
        getContext().startActivity(A("android.intent.action.SEARCH", null, null, str2, i8, str));
    }

    void P() {
        if (!TextUtils.isEmpty(this.f2101p.getText())) {
            this.f2101p.setText("");
            this.f2101p.requestFocus();
            this.f2101p.setImeVisibility(true);
        } else if (this.M) {
            clearFocus();
            i0(true);
        }
    }

    boolean Q(int i8, int i9, String str) {
        O(i8, 0, null);
        this.f2101p.setImeVisibility(false);
        E();
        return true;
    }

    boolean R(int i8) {
        a0(i8);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void S(CharSequence charSequence) {
        setQuery(charSequence);
    }

    void T() {
        i0(false);
        this.f2101p.requestFocus();
        this.f2101p.setImeVisibility(true);
        View.OnClickListener onClickListener = this.L;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    void U() {
        Editable text = this.f2101p.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        if (this.f2090c0 != null) {
            N(0, null, text.toString());
        }
        this.f2101p.setImeVisibility(false);
        E();
    }

    boolean V(View view, int i8, KeyEvent keyEvent) {
        if (this.f2090c0 != null && this.O != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i8 == 66 || i8 == 84 || i8 == 61) {
                return Q(this.f2101p.getListSelection(), 0, null);
            }
            if (i8 == 21 || i8 == 22) {
                this.f2101p.setSelection(i8 == 21 ? 0 : this.f2101p.length());
                this.f2101p.setListSelection(0);
                this.f2101p.clearListSelection();
                this.f2101p.b();
                return true;
            } else if (i8 == 19) {
                this.f2101p.getListSelection();
                return false;
            }
        }
        return false;
    }

    void W(CharSequence charSequence) {
        Editable text = this.f2101p.getText();
        this.W = text;
        boolean z7 = !TextUtils.isEmpty(text);
        h0(z7);
        j0(!z7);
        c0();
        g0();
        this.V = charSequence.toString();
    }

    void X() {
        i0(J());
        Z();
        if (this.f2101p.hasFocus()) {
            F();
        }
    }

    void Y() {
        Intent C;
        SearchableInfo searchableInfo = this.f2090c0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                C = D(this.H, searchableInfo);
            } else if (!searchableInfo.getVoiceSearchLaunchRecognizer()) {
                return;
            } else {
                C = C(this.I, searchableInfo);
            }
            getContext().startActivity(C);
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    public void b0(CharSequence charSequence, boolean z7) {
        this.f2101p.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.f2101p;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.W = charSequence;
        }
        if (!z7 || TextUtils.isEmpty(charSequence)) {
            return;
        }
        U();
    }

    @Override // androidx.appcompat.view.c
    public void c() {
        if (this.f2088a0) {
            return;
        }
        this.f2088a0 = true;
        int imeOptions = this.f2101p.getImeOptions();
        this.f2089b0 = imeOptions;
        this.f2101p.setImeOptions(imeOptions | 33554432);
        this.f2101p.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.S = true;
        super.clearFocus();
        this.f2101p.clearFocus();
        this.f2101p.setImeVisibility(false);
        this.S = false;
    }

    @Override // androidx.appcompat.view.c
    public void d() {
        b0("", false);
        clearFocus();
        i0(true);
        this.f2101p.setImeOptions(this.f2089b0);
        this.f2088a0 = false;
    }

    void d0() {
        int[] iArr = this.f2101p.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f2103r.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.f2104s.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public int getImeOptions() {
        return this.f2101p.getImeOptions();
    }

    public int getInputType() {
        return this.f2101p.getInputType();
    }

    public int getMaxWidth() {
        return this.T;
    }

    public CharSequence getQuery() {
        return this.f2101p.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.Q;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f2090c0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.J : getContext().getText(this.f2090c0.getHintId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSuggestionCommitIconResId() {
        return this.G;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSuggestionRowLayout() {
        return this.F;
    }

    public l1.a getSuggestionsAdapter() {
        return this.O;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.f2092e0);
        post(this.f2093f0);
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.o0, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        if (z7) {
            G(this.f2101p, this.f2111z);
            Rect rect = this.A;
            Rect rect2 = this.f2111z;
            rect.set(rect2.left, 0, rect2.right, i11 - i9);
            q qVar = this.f2110y;
            if (qVar != null) {
                qVar.a(this.A, this.f2111z);
                return;
            }
            q qVar2 = new q(this.A, this.f2111z, this.f2101p);
            this.f2110y = qVar2;
            setTouchDelegate(qVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001f, code lost:
        if (r0 <= 0) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004b  */
    @Override // androidx.appcompat.widget.o0, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r4, int r5) {
        /*
            r3 = this;
            boolean r0 = r3.J()
            if (r0 == 0) goto La
            super.onMeasure(r4, r5)
            return
        La:
            int r0 = android.view.View.MeasureSpec.getMode(r4)
            int r4 = android.view.View.MeasureSpec.getSize(r4)
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 == r1) goto L2c
            if (r0 == 0) goto L22
            if (r0 == r2) goto L1d
            goto L39
        L1d:
            int r0 = r3.T
            if (r0 <= 0) goto L39
            goto L30
        L22:
            int r4 = r3.T
            if (r4 <= 0) goto L27
            goto L39
        L27:
            int r4 = r3.getPreferredWidth()
            goto L39
        L2c:
            int r0 = r3.T
            if (r0 <= 0) goto L31
        L30:
            goto L35
        L31:
            int r0 = r3.getPreferredWidth()
        L35:
            int r4 = java.lang.Math.min(r0, r4)
        L39:
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            if (r0 == r1) goto L4b
            if (r0 == 0) goto L46
            goto L53
        L46:
            int r5 = r3.getPreferredHeight()
            goto L53
        L4b:
            int r0 = r3.getPreferredHeight()
            int r5 = java.lang.Math.min(r0, r5)
        L53:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r2)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r2)
            super.onMeasure(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.onMeasure(int, int):void");
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof p)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        p pVar = (p) parcelable;
        super.onRestoreInstanceState(pVar.a());
        i0(pVar.f2130c);
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        p pVar = new p(super.onSaveInstanceState());
        pVar.f2130c = J();
        return pVar;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z7) {
        super.onWindowFocusChanged(z7);
        Z();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i8, Rect rect) {
        if (!this.S && isFocusable()) {
            if (J()) {
                return super.requestFocus(i8, rect);
            }
            boolean requestFocus = this.f2101p.requestFocus(i8, rect);
            if (requestFocus) {
                i0(false);
            }
            return requestFocus;
        }
        return false;
    }

    public void setAppSearchData(Bundle bundle) {
        this.f2091d0 = bundle;
    }

    public void setIconified(boolean z7) {
        if (z7) {
            P();
        } else {
            T();
        }
    }

    public void setIconifiedByDefault(boolean z7) {
        if (this.M == z7) {
            return;
        }
        this.M = z7;
        i0(z7);
        e0();
    }

    public void setImeOptions(int i8) {
        this.f2101p.setImeOptions(i8);
    }

    public void setInputType(int i8) {
        this.f2101p.setInputType(i8);
    }

    public void setMaxWidth(int i8) {
        this.T = i8;
        requestLayout();
    }

    public void setOnCloseListener(l lVar) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.K = onFocusChangeListener;
    }

    public void setOnQueryTextListener(m mVar) {
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.L = onClickListener;
    }

    public void setOnSuggestionListener(n nVar) {
    }

    public void setQueryHint(CharSequence charSequence) {
        this.Q = charSequence;
        e0();
    }

    public void setQueryRefinementEnabled(boolean z7) {
        this.R = z7;
        l1.a aVar = this.O;
        if (aVar instanceof w0) {
            ((w0) aVar).x(z7 ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f2090c0 = searchableInfo;
        if (searchableInfo != null) {
            f0();
            e0();
        }
        boolean I = I();
        this.U = I;
        if (I) {
            this.f2101p.setPrivateImeOptions("nm");
        }
        i0(J());
    }

    public void setSubmitButtonEnabled(boolean z7) {
        this.P = z7;
        i0(J());
    }

    public void setSuggestionsAdapter(l1.a aVar) {
        this.O = aVar;
        this.f2101p.setAdapter(aVar);
    }

    void z() {
        if (this.f2109x.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.f2103r.getPaddingLeft();
            Rect rect = new Rect();
            boolean b8 = i1.b(this);
            int dimensionPixelSize = this.M ? resources.getDimensionPixelSize(g.d.f6829c) + resources.getDimensionPixelSize(g.d.f6830d) : 0;
            this.f2101p.getDropDownBackground().getPadding(rect);
            int i8 = rect.left;
            this.f2101p.setDropDownHorizontalOffset(b8 ? -i8 : paddingLeft - (i8 + dimensionPixelSize));
            this.f2101p.setDropDownWidth((((this.f2109x.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.E);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2111z = new Rect();
        this.A = new Rect();
        this.B = new int[2];
        this.C = new int[2];
        this.f2092e0 = new b();
        this.f2093f0 = new c();
        this.f2094g0 = new WeakHashMap();
        f fVar = new f();
        this.f2095h0 = fVar;
        this.f2096i0 = new g();
        h hVar = new h();
        this.f2097j0 = hVar;
        i iVar = new i();
        this.f2098k0 = iVar;
        j jVar = new j();
        this.f2099l0 = jVar;
        this.f2100m0 = new a();
        b1 u7 = b1.u(context, attributeSet, g.j.f6951f2, i8, 0);
        androidx.core.view.p0.L(this, context, g.j.f6951f2, attributeSet, u7.q(), i8, 0);
        LayoutInflater.from(context).inflate(u7.m(g.j.f7001p2, g.g.f6905r), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(g.f.C);
        this.f2101p = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.f2102q = findViewById(g.f.f6886y);
        View findViewById = findViewById(g.f.B);
        this.f2103r = findViewById;
        View findViewById2 = findViewById(g.f.I);
        this.f2104s = findViewById2;
        ImageView imageView = (ImageView) findViewById(g.f.f6884w);
        this.f2105t = imageView;
        ImageView imageView2 = (ImageView) findViewById(g.f.f6887z);
        this.f2106u = imageView2;
        ImageView imageView3 = (ImageView) findViewById(g.f.f6885x);
        this.f2107v = imageView3;
        ImageView imageView4 = (ImageView) findViewById(g.f.D);
        this.f2108w = imageView4;
        ImageView imageView5 = (ImageView) findViewById(g.f.A);
        this.D = imageView5;
        androidx.core.view.p0.Q(findViewById, u7.f(g.j.f7006q2));
        androidx.core.view.p0.Q(findViewById2, u7.f(g.j.f7026u2));
        imageView.setImageDrawable(u7.f(g.j.f7021t2));
        imageView2.setImageDrawable(u7.f(g.j.f6991n2));
        imageView3.setImageDrawable(u7.f(g.j.f6976k2));
        imageView4.setImageDrawable(u7.f(g.j.f7036w2));
        imageView5.setImageDrawable(u7.f(g.j.f7021t2));
        this.E = u7.f(g.j.f7016s2);
        g1.a(imageView, getResources().getString(g.h.f6918l));
        this.F = u7.m(g.j.f7031v2, g.g.f6904q);
        this.G = u7.m(g.j.f6981l2, 0);
        imageView.setOnClickListener(fVar);
        imageView3.setOnClickListener(fVar);
        imageView2.setOnClickListener(fVar);
        imageView4.setOnClickListener(fVar);
        searchAutoComplete.setOnClickListener(fVar);
        searchAutoComplete.addTextChangedListener(this.f2100m0);
        searchAutoComplete.setOnEditorActionListener(hVar);
        searchAutoComplete.setOnItemClickListener(iVar);
        searchAutoComplete.setOnItemSelectedListener(jVar);
        searchAutoComplete.setOnKeyListener(this.f2096i0);
        searchAutoComplete.setOnFocusChangeListener(new d());
        setIconifiedByDefault(u7.a(g.j.f6996o2, true));
        int e8 = u7.e(g.j.f6961h2, -1);
        if (e8 != -1) {
            setMaxWidth(e8);
        }
        this.J = u7.o(g.j.f6986m2);
        this.Q = u7.o(g.j.f7011r2);
        int j8 = u7.j(g.j.f6971j2, -1);
        if (j8 != -1) {
            setImeOptions(j8);
        }
        int j9 = u7.j(g.j.f6966i2, -1);
        if (j9 != -1) {
            setInputType(j9);
        }
        setFocusable(u7.a(g.j.f6956g2, true));
        u7.v();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.H = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.I = intent2;
        intent2.addFlags(268435456);
        View findViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.f2109x = findViewById3;
        if (findViewById3 != null) {
            findViewById3.addOnLayoutChangeListener(new e());
        }
        i0(this.M);
        e0();
    }
}
