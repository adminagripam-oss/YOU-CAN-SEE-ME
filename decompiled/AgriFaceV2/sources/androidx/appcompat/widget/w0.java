package androidx.appcompat.widget;

import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
class w0 extends l1.c implements View.OnClickListener {

    /* renamed from: l  reason: collision with root package name */
    private final SearchView f2503l;

    /* renamed from: m  reason: collision with root package name */
    private final SearchableInfo f2504m;

    /* renamed from: n  reason: collision with root package name */
    private final Context f2505n;

    /* renamed from: o  reason: collision with root package name */
    private final WeakHashMap f2506o;

    /* renamed from: p  reason: collision with root package name */
    private final int f2507p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f2508q;

    /* renamed from: r  reason: collision with root package name */
    private int f2509r;

    /* renamed from: s  reason: collision with root package name */
    private ColorStateList f2510s;

    /* renamed from: t  reason: collision with root package name */
    private int f2511t;

    /* renamed from: u  reason: collision with root package name */
    private int f2512u;

    /* renamed from: v  reason: collision with root package name */
    private int f2513v;

    /* renamed from: w  reason: collision with root package name */
    private int f2514w;

    /* renamed from: x  reason: collision with root package name */
    private int f2515x;

    /* renamed from: y  reason: collision with root package name */
    private int f2516y;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f2517a;

        /* renamed from: b  reason: collision with root package name */
        public final TextView f2518b;

        /* renamed from: c  reason: collision with root package name */
        public final ImageView f2519c;

        /* renamed from: d  reason: collision with root package name */
        public final ImageView f2520d;

        /* renamed from: e  reason: collision with root package name */
        public final ImageView f2521e;

        public a(View view) {
            this.f2517a = (TextView) view.findViewById(16908308);
            this.f2518b = (TextView) view.findViewById(16908309);
            this.f2519c = (ImageView) view.findViewById(16908295);
            this.f2520d = (ImageView) view.findViewById(16908296);
            this.f2521e = (ImageView) view.findViewById(g.f.f6878q);
        }
    }

    public w0(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.f2508q = false;
        this.f2509r = 1;
        this.f2511t = -1;
        this.f2512u = -1;
        this.f2513v = -1;
        this.f2514w = -1;
        this.f2515x = -1;
        this.f2516y = -1;
        this.f2503l = searchView;
        this.f2504m = searchableInfo;
        this.f2507p = searchView.getSuggestionCommitIconResId();
        this.f2505n = context;
        this.f2506o = weakHashMap;
    }

    private void A(String str, Drawable drawable) {
        if (drawable != null) {
            this.f2506o.put(str, drawable.getConstantState());
        }
    }

    private void B(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    private Drawable k(String str) {
        Drawable.ConstantState constantState = (Drawable.ConstantState) this.f2506o.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private CharSequence l(CharSequence charSequence) {
        if (this.f2510s == null) {
            TypedValue typedValue = new TypedValue();
            this.f2505n.getTheme().resolveAttribute(g.a.H, typedValue, true);
            this.f2510s = this.f2505n.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f2510s, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private Drawable m(ComponentName componentName) {
        String obj;
        ActivityInfo activityInfo;
        int iconResource;
        PackageManager packageManager = this.f2505n.getPackageManager();
        try {
            activityInfo = packageManager.getActivityInfo(componentName, 128);
            iconResource = activityInfo.getIconResource();
        } catch (PackageManager.NameNotFoundException e8) {
            obj = e8.toString();
        }
        if (iconResource == 0) {
            return null;
        }
        Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
        if (drawable == null) {
            obj = "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString();
            Log.w("SuggestionsAdapter", obj);
            return null;
        }
        return drawable;
    }

    private Drawable n(ComponentName componentName) {
        String flattenToShortString = componentName.flattenToShortString();
        if (!this.f2506o.containsKey(flattenToShortString)) {
            Drawable m8 = m(componentName);
            this.f2506o.put(flattenToShortString, m8 != null ? m8.getConstantState() : null);
            return m8;
        }
        Drawable.ConstantState constantState = (Drawable.ConstantState) this.f2506o.get(flattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.f2505n.getResources());
    }

    public static String o(Cursor cursor, String str) {
        return w(cursor, cursor.getColumnIndex(str));
    }

    private Drawable p() {
        Drawable n8 = n(this.f2504m.getSearchActivity());
        return n8 != null ? n8 : this.f2505n.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable q(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return r(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream openInputStream = this.f2505n.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            Drawable createFromStream = Drawable.createFromStream(openInputStream, null);
            try {
                openInputStream.close();
            } catch (IOException e8) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e8);
            }
            return createFromStream;
        } catch (FileNotFoundException e9) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e9.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e9.getMessage());
        return null;
    }

    private Drawable s(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f2505n.getPackageName() + "/" + parseInt;
            Drawable k8 = k(str2);
            if (k8 != null) {
                return k8;
            }
            Drawable d8 = androidx.core.content.a.d(this.f2505n, parseInt);
            A(str2, d8);
            return d8;
        } catch (Resources.NotFoundException unused) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable k9 = k(str);
            if (k9 != null) {
                return k9;
            }
            Drawable q8 = q(Uri.parse(str));
            A(str, q8);
            return q8;
        }
    }

    private Drawable t(Cursor cursor) {
        int i8 = this.f2514w;
        if (i8 == -1) {
            return null;
        }
        Drawable s8 = s(cursor.getString(i8));
        return s8 != null ? s8 : p();
    }

    private Drawable u(Cursor cursor) {
        int i8 = this.f2515x;
        if (i8 == -1) {
            return null;
        }
        return s(cursor.getString(i8));
    }

    private static String w(Cursor cursor, int i8) {
        if (i8 == -1) {
            return null;
        }
        try {
            return cursor.getString(i8);
        } catch (Exception e8) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e8);
            return null;
        }
    }

    private void y(ImageView imageView, Drawable drawable, int i8) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i8);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void z(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    @Override // l1.a, l1.b.a
    public void a(Cursor cursor) {
        if (this.f2508q) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.a(cursor);
            if (cursor != null) {
                this.f2511t = cursor.getColumnIndex("suggest_text_1");
                this.f2512u = cursor.getColumnIndex("suggest_text_2");
                this.f2513v = cursor.getColumnIndex("suggest_text_2_url");
                this.f2514w = cursor.getColumnIndex("suggest_icon_1");
                this.f2515x = cursor.getColumnIndex("suggest_icon_2");
                this.f2516y = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e8) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e8);
        }
    }

    @Override // l1.a, l1.b.a
    public CharSequence c(Cursor cursor) {
        String o8;
        String o9;
        if (cursor == null) {
            return null;
        }
        String o10 = o(cursor, "suggest_intent_query");
        if (o10 != null) {
            return o10;
        }
        if (!this.f2504m.shouldRewriteQueryFromData() || (o9 = o(cursor, "suggest_intent_data")) == null) {
            if (!this.f2504m.shouldRewriteQueryFromText() || (o8 = o(cursor, "suggest_text_1")) == null) {
                return null;
            }
            return o8;
        }
        return o9;
    }

    @Override // l1.b.a
    public Cursor d(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        if (this.f2503l.getVisibility() == 0 && this.f2503l.getWindowVisibility() == 0) {
            try {
                Cursor v7 = v(this.f2504m, charSequence2, 50);
                if (v7 != null) {
                    v7.getCount();
                    return v7;
                }
            } catch (RuntimeException e8) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e8);
            }
        }
        return null;
    }

    @Override // l1.a
    public void e(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i8 = this.f2516y;
        int i9 = i8 != -1 ? cursor.getInt(i8) : 0;
        if (aVar.f2517a != null) {
            z(aVar.f2517a, w(cursor, this.f2511t));
        }
        if (aVar.f2518b != null) {
            String w7 = w(cursor, this.f2513v);
            CharSequence l8 = w7 != null ? l(w7) : w(cursor, this.f2512u);
            if (TextUtils.isEmpty(l8)) {
                TextView textView = aVar.f2517a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f2517a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f2517a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f2517a.setMaxLines(1);
                }
            }
            z(aVar.f2518b, l8);
        }
        ImageView imageView = aVar.f2519c;
        if (imageView != null) {
            y(imageView, t(cursor), 4);
        }
        ImageView imageView2 = aVar.f2520d;
        if (imageView2 != null) {
            y(imageView2, u(cursor), 8);
        }
        int i10 = this.f2509r;
        if (i10 != 2 && (i10 != 1 || (i9 & 1) == 0)) {
            aVar.f2521e.setVisibility(8);
            return;
        }
        aVar.f2521e.setVisibility(0);
        aVar.f2521e.setTag(aVar.f2517a.getText());
        aVar.f2521e.setOnClickListener(this);
    }

    @Override // l1.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i8, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i8, view, viewGroup);
        } catch (RuntimeException e8) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e8);
            View g8 = g(this.f2505n, b(), viewGroup);
            if (g8 != null) {
                ((a) g8.getTag()).f2517a.setText(e8.toString());
            }
            return g8;
        }
    }

    @Override // l1.a, android.widget.Adapter
    public View getView(int i8, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i8, view, viewGroup);
        } catch (RuntimeException e8) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e8);
            View h8 = h(this.f2505n, b(), viewGroup);
            if (h8 != null) {
                ((a) h8.getTag()).f2517a.setText(e8.toString());
            }
            return h8;
        }
    }

    @Override // l1.c, l1.a
    public View h(Context context, Cursor cursor, ViewGroup viewGroup) {
        View h8 = super.h(context, cursor, viewGroup);
        h8.setTag(new a(h8));
        ((ImageView) h8.findViewById(g.f.f6878q)).setImageResource(this.f2507p);
        return h8;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        B(b());
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        B(b());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f2503l.S((CharSequence) tag);
        }
    }

    Drawable r(Uri uri) {
        int parseInt;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.f2505n.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    parseInt = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else if (size != 2) {
                throw new FileNotFoundException("More than two path segments: " + uri);
            } else {
                parseInt = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (parseInt != 0) {
                return resourcesForApplication.getDrawable(parseInt);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    Cursor v(SearchableInfo searchableInfo, String str, int i8) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i8 > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i8));
        }
        return this.f2505n.getContentResolver().query(fragment.build(), null, suggestSelection, strArr2, null);
    }

    public void x(int i8) {
        this.f2509r = i8;
    }
}
