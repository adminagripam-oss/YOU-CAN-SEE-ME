package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.o0;
import androidx.core.view.p0;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AlertController {
    NestedScrollView A;
    private Drawable C;
    private ImageView D;
    private TextView E;
    private TextView F;
    private View G;
    ListAdapter H;
    private int J;
    private int K;
    int L;
    int M;
    int N;
    int O;
    private boolean P;
    Handler R;

    /* renamed from: a  reason: collision with root package name */
    private final Context f1471a;

    /* renamed from: b  reason: collision with root package name */
    final p f1472b;

    /* renamed from: c  reason: collision with root package name */
    private final Window f1473c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1474d;

    /* renamed from: e  reason: collision with root package name */
    private CharSequence f1475e;

    /* renamed from: f  reason: collision with root package name */
    private CharSequence f1476f;

    /* renamed from: g  reason: collision with root package name */
    ListView f1477g;

    /* renamed from: h  reason: collision with root package name */
    private View f1478h;

    /* renamed from: i  reason: collision with root package name */
    private int f1479i;

    /* renamed from: j  reason: collision with root package name */
    private int f1480j;

    /* renamed from: k  reason: collision with root package name */
    private int f1481k;

    /* renamed from: l  reason: collision with root package name */
    private int f1482l;

    /* renamed from: m  reason: collision with root package name */
    private int f1483m;

    /* renamed from: o  reason: collision with root package name */
    Button f1485o;

    /* renamed from: p  reason: collision with root package name */
    private CharSequence f1486p;

    /* renamed from: q  reason: collision with root package name */
    Message f1487q;

    /* renamed from: r  reason: collision with root package name */
    private Drawable f1488r;

    /* renamed from: s  reason: collision with root package name */
    Button f1489s;

    /* renamed from: t  reason: collision with root package name */
    private CharSequence f1490t;

    /* renamed from: u  reason: collision with root package name */
    Message f1491u;

    /* renamed from: v  reason: collision with root package name */
    private Drawable f1492v;

    /* renamed from: w  reason: collision with root package name */
    Button f1493w;

    /* renamed from: x  reason: collision with root package name */
    private CharSequence f1494x;

    /* renamed from: y  reason: collision with root package name */
    Message f1495y;

    /* renamed from: z  reason: collision with root package name */
    private Drawable f1496z;

    /* renamed from: n  reason: collision with root package name */
    private boolean f1484n = false;
    private int B = 0;
    int I = -1;
    private int Q = 0;
    private final View.OnClickListener S = new a();

    /* loaded from: classes.dex */
    public static class RecycleListView extends ListView {

        /* renamed from: a  reason: collision with root package name */
        private final int f1497a;

        /* renamed from: b  reason: collision with root package name */
        private final int f1498b;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.j.f6936c2);
            this.f1498b = obtainStyledAttributes.getDimensionPixelOffset(g.j.f6941d2, -1);
            this.f1497a = obtainStyledAttributes.getDimensionPixelOffset(g.j.f6946e2, -1);
        }

        public void a(boolean z7, boolean z8) {
            if (z8 && z7) {
                return;
            }
            setPadding(getPaddingLeft(), z7 ? getPaddingTop() : this.f1497a, getPaddingRight(), z8 ? getPaddingBottom() : this.f1498b);
        }
    }

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            AlertController alertController = AlertController.this;
            Message obtain = ((view != alertController.f1485o || (message2 = alertController.f1487q) == null) && (view != alertController.f1489s || (message2 = alertController.f1491u) == null)) ? (view != alertController.f1493w || (message = alertController.f1495y) == null) ? null : Message.obtain(message) : Message.obtain(message2);
            if (obtain != null) {
                obtain.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.R.obtainMessage(1, alertController2.f1472b).sendToTarget();
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public AdapterView.OnItemSelectedListener N;

        /* renamed from: a  reason: collision with root package name */
        public final Context f1500a;

        /* renamed from: b  reason: collision with root package name */
        public final LayoutInflater f1501b;

        /* renamed from: d  reason: collision with root package name */
        public Drawable f1503d;

        /* renamed from: f  reason: collision with root package name */
        public CharSequence f1505f;

        /* renamed from: g  reason: collision with root package name */
        public View f1506g;

        /* renamed from: h  reason: collision with root package name */
        public CharSequence f1507h;

        /* renamed from: i  reason: collision with root package name */
        public CharSequence f1508i;

        /* renamed from: j  reason: collision with root package name */
        public Drawable f1509j;

        /* renamed from: k  reason: collision with root package name */
        public DialogInterface.OnClickListener f1510k;

        /* renamed from: l  reason: collision with root package name */
        public CharSequence f1511l;

        /* renamed from: m  reason: collision with root package name */
        public Drawable f1512m;

        /* renamed from: n  reason: collision with root package name */
        public DialogInterface.OnClickListener f1513n;

        /* renamed from: o  reason: collision with root package name */
        public CharSequence f1514o;

        /* renamed from: p  reason: collision with root package name */
        public Drawable f1515p;

        /* renamed from: q  reason: collision with root package name */
        public DialogInterface.OnClickListener f1516q;

        /* renamed from: s  reason: collision with root package name */
        public DialogInterface.OnCancelListener f1518s;

        /* renamed from: t  reason: collision with root package name */
        public DialogInterface.OnDismissListener f1519t;

        /* renamed from: u  reason: collision with root package name */
        public DialogInterface.OnKeyListener f1520u;

        /* renamed from: v  reason: collision with root package name */
        public CharSequence[] f1521v;

        /* renamed from: w  reason: collision with root package name */
        public ListAdapter f1522w;

        /* renamed from: x  reason: collision with root package name */
        public DialogInterface.OnClickListener f1523x;

        /* renamed from: y  reason: collision with root package name */
        public int f1524y;

        /* renamed from: z  reason: collision with root package name */
        public View f1525z;

        /* renamed from: c  reason: collision with root package name */
        public int f1502c = 0;

        /* renamed from: e  reason: collision with root package name */
        public int f1504e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean O = true;

        /* renamed from: r  reason: collision with root package name */
        public boolean f1517r = true;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a extends ArrayAdapter {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ RecycleListView f1526a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Context context, int i8, int i9, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i8, i9, charSequenceArr);
                this.f1526a = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i8, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i8, view, viewGroup);
                boolean[] zArr = b.this.F;
                if (zArr != null && zArr[i8]) {
                    this.f1526a.setItemChecked(i8, true);
                }
                return view2;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: androidx.appcompat.app.AlertController$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0005b extends CursorAdapter {

            /* renamed from: a  reason: collision with root package name */
            private final int f1528a;

            /* renamed from: b  reason: collision with root package name */
            private final int f1529b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ RecycleListView f1530c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ AlertController f1531d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0005b(Context context, Cursor cursor, boolean z7, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z7);
                this.f1530c = recycleListView;
                this.f1531d = alertController;
                Cursor cursor2 = getCursor();
                this.f1528a = cursor2.getColumnIndexOrThrow(b.this.L);
                this.f1529b = cursor2.getColumnIndexOrThrow(b.this.M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.f1528a));
                this.f1530c.setItemChecked(cursor.getPosition(), cursor.getInt(this.f1529b) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return b.this.f1501b.inflate(this.f1531d.M, viewGroup, false);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class c implements AdapterView.OnItemClickListener {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ AlertController f1533a;

            c(AlertController alertController) {
                this.f1533a = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i8, long j8) {
                b.this.f1523x.onClick(this.f1533a.f1472b, i8);
                if (b.this.H) {
                    return;
                }
                this.f1533a.f1472b.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class d implements AdapterView.OnItemClickListener {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ RecycleListView f1535a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ AlertController f1536b;

            d(RecycleListView recycleListView, AlertController alertController) {
                this.f1535a = recycleListView;
                this.f1536b = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i8, long j8) {
                boolean[] zArr = b.this.F;
                if (zArr != null) {
                    zArr[i8] = this.f1535a.isItemChecked(i8);
                }
                b.this.J.onClick(this.f1536b.f1472b, i8, this.f1535a.isItemChecked(i8));
            }
        }

        public b(Context context) {
            this.f1500a = context;
            this.f1501b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0090  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0095  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void b(androidx.appcompat.app.AlertController r10) {
            /*
                r9 = this;
                android.view.LayoutInflater r0 = r9.f1501b
                int r1 = r10.L
                r2 = 0
                android.view.View r0 = r0.inflate(r1, r2)
                androidx.appcompat.app.AlertController$RecycleListView r0 = (androidx.appcompat.app.AlertController.RecycleListView) r0
                boolean r1 = r9.G
                if (r1 == 0) goto L34
                android.database.Cursor r1 = r9.K
                if (r1 != 0) goto L25
                androidx.appcompat.app.AlertController$b$a r8 = new androidx.appcompat.app.AlertController$b$a
                android.content.Context r3 = r9.f1500a
                int r4 = r10.M
                r5 = 16908308(0x1020014, float:2.3877285E-38)
                java.lang.CharSequence[] r6 = r9.f1521v
                r1 = r8
                r2 = r9
                r7 = r0
                r1.<init>(r3, r4, r5, r6, r7)
                goto L68
            L25:
                androidx.appcompat.app.AlertController$b$b r8 = new androidx.appcompat.app.AlertController$b$b
                android.content.Context r3 = r9.f1500a
                android.database.Cursor r4 = r9.K
                r5 = 0
                r1 = r8
                r2 = r9
                r6 = r0
                r7 = r10
                r1.<init>(r3, r4, r5, r6, r7)
                goto L68
            L34:
                boolean r1 = r9.H
                if (r1 == 0) goto L3b
                int r1 = r10.N
                goto L3d
            L3b:
                int r1 = r10.O
            L3d:
                r4 = r1
                android.database.Cursor r1 = r9.K
                r2 = 16908308(0x1020014, float:2.3877285E-38)
                if (r1 == 0) goto L5a
                android.widget.SimpleCursorAdapter r8 = new android.widget.SimpleCursorAdapter
                android.content.Context r3 = r9.f1500a
                android.database.Cursor r5 = r9.K
                java.lang.String r1 = r9.L
                java.lang.String[] r6 = new java.lang.String[]{r1}
                int[] r7 = new int[]{r2}
                r2 = r8
                r2.<init>(r3, r4, r5, r6, r7)
                goto L68
            L5a:
                android.widget.ListAdapter r8 = r9.f1522w
                if (r8 == 0) goto L5f
                goto L68
            L5f:
                androidx.appcompat.app.AlertController$d r8 = new androidx.appcompat.app.AlertController$d
                android.content.Context r1 = r9.f1500a
                java.lang.CharSequence[] r3 = r9.f1521v
                r8.<init>(r1, r4, r2, r3)
            L68:
                r10.H = r8
                int r1 = r9.I
                r10.I = r1
                android.content.DialogInterface$OnClickListener r1 = r9.f1523x
                if (r1 == 0) goto L7b
                androidx.appcompat.app.AlertController$b$c r1 = new androidx.appcompat.app.AlertController$b$c
                r1.<init>(r10)
            L77:
                r0.setOnItemClickListener(r1)
                goto L85
            L7b:
                android.content.DialogInterface$OnMultiChoiceClickListener r1 = r9.J
                if (r1 == 0) goto L85
                androidx.appcompat.app.AlertController$b$d r1 = new androidx.appcompat.app.AlertController$b$d
                r1.<init>(r0, r10)
                goto L77
            L85:
                android.widget.AdapterView$OnItemSelectedListener r1 = r9.N
                if (r1 == 0) goto L8c
                r0.setOnItemSelectedListener(r1)
            L8c:
                boolean r1 = r9.H
                if (r1 == 0) goto L95
                r1 = 1
            L91:
                r0.setChoiceMode(r1)
                goto L9b
            L95:
                boolean r1 = r9.G
                if (r1 == 0) goto L9b
                r1 = 2
                goto L91
            L9b:
                r10.f1477g = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AlertController.b.b(androidx.appcompat.app.AlertController):void");
        }

        public void a(AlertController alertController) {
            View view = this.f1506g;
            if (view != null) {
                alertController.k(view);
            } else {
                CharSequence charSequence = this.f1505f;
                if (charSequence != null) {
                    alertController.p(charSequence);
                }
                Drawable drawable = this.f1503d;
                if (drawable != null) {
                    alertController.m(drawable);
                }
                int i8 = this.f1502c;
                if (i8 != 0) {
                    alertController.l(i8);
                }
                int i9 = this.f1504e;
                if (i9 != 0) {
                    alertController.l(alertController.c(i9));
                }
            }
            CharSequence charSequence2 = this.f1507h;
            if (charSequence2 != null) {
                alertController.n(charSequence2);
            }
            CharSequence charSequence3 = this.f1508i;
            if (charSequence3 != null || this.f1509j != null) {
                alertController.j(-1, charSequence3, this.f1510k, null, this.f1509j);
            }
            CharSequence charSequence4 = this.f1511l;
            if (charSequence4 != null || this.f1512m != null) {
                alertController.j(-2, charSequence4, this.f1513n, null, this.f1512m);
            }
            CharSequence charSequence5 = this.f1514o;
            if (charSequence5 != null || this.f1515p != null) {
                alertController.j(-3, charSequence5, this.f1516q, null, this.f1515p);
            }
            if (this.f1521v != null || this.K != null || this.f1522w != null) {
                b(alertController);
            }
            View view2 = this.f1525z;
            if (view2 != null) {
                if (this.E) {
                    alertController.s(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.r(view2);
                    return;
                }
            }
            int i10 = this.f1524y;
            if (i10 != 0) {
                alertController.q(i10);
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class c extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference f1538a;

        public c(DialogInterface dialogInterface) {
            this.f1538a = new WeakReference(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i8 = message.what;
            if (i8 == -3 || i8 == -2 || i8 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f1538a.get(), message.what);
            } else if (i8 != 1) {
            } else {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d extends ArrayAdapter {
        public d(Context context, int i8, int i9, CharSequence[] charSequenceArr) {
            super(context, i8, i9, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i8) {
            return i8;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, p pVar, Window window) {
        this.f1471a = context;
        this.f1472b = pVar;
        this.f1473c = window;
        this.R = new c(pVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, g.j.F, g.a.f6802k, 0);
        this.J = obtainStyledAttributes.getResourceId(g.j.G, 0);
        this.K = obtainStyledAttributes.getResourceId(g.j.I, 0);
        this.L = obtainStyledAttributes.getResourceId(g.j.K, 0);
        this.M = obtainStyledAttributes.getResourceId(g.j.L, 0);
        this.N = obtainStyledAttributes.getResourceId(g.j.N, 0);
        this.O = obtainStyledAttributes.getResourceId(g.j.J, 0);
        this.P = obtainStyledAttributes.getBoolean(g.j.M, true);
        this.f1474d = obtainStyledAttributes.getDimensionPixelSize(g.j.H, 0);
        obtainStyledAttributes.recycle();
        pVar.h(1);
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            while (childCount > 0) {
                childCount--;
                if (a(viewGroup.getChildAt(childCount))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private void b(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    private ViewGroup h(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private int i() {
        int i8 = this.K;
        return (i8 != 0 && this.Q == 1) ? i8 : this.J;
    }

    private void o(ViewGroup viewGroup, View view, int i8, int i9) {
        View findViewById = this.f1473c.findViewById(g.f.f6882u);
        View findViewById2 = this.f1473c.findViewById(g.f.f6881t);
        p0.X(view, i8, i9);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        if (findViewById2 != null) {
            viewGroup.removeView(findViewById2);
        }
    }

    private void t(ViewGroup viewGroup) {
        boolean z7;
        Button button;
        Button button2 = (Button) viewGroup.findViewById(16908313);
        this.f1485o = button2;
        button2.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f1486p) && this.f1488r == null) {
            this.f1485o.setVisibility(8);
            z7 = false;
        } else {
            this.f1485o.setText(this.f1486p);
            Drawable drawable = this.f1488r;
            if (drawable != null) {
                int i8 = this.f1474d;
                drawable.setBounds(0, 0, i8, i8);
                this.f1485o.setCompoundDrawables(this.f1488r, null, null, null);
            }
            this.f1485o.setVisibility(0);
            z7 = true;
        }
        Button button3 = (Button) viewGroup.findViewById(16908314);
        this.f1489s = button3;
        button3.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f1490t) && this.f1492v == null) {
            this.f1489s.setVisibility(8);
        } else {
            this.f1489s.setText(this.f1490t);
            Drawable drawable2 = this.f1492v;
            if (drawable2 != null) {
                int i9 = this.f1474d;
                drawable2.setBounds(0, 0, i9, i9);
                this.f1489s.setCompoundDrawables(this.f1492v, null, null, null);
            }
            this.f1489s.setVisibility(0);
            z7 |= true;
        }
        Button button4 = (Button) viewGroup.findViewById(16908315);
        this.f1493w = button4;
        button4.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.f1494x) && this.f1496z == null) {
            this.f1493w.setVisibility(8);
        } else {
            this.f1493w.setText(this.f1494x);
            Drawable drawable3 = this.f1496z;
            if (drawable3 != null) {
                int i10 = this.f1474d;
                drawable3.setBounds(0, 0, i10, i10);
                this.f1493w.setCompoundDrawables(this.f1496z, null, null, null);
            }
            this.f1493w.setVisibility(0);
            z7 |= true;
        }
        if (y(this.f1471a)) {
            if (z7) {
                button = this.f1485o;
            } else if (z7) {
                button = this.f1489s;
            } else if (z7) {
                button = this.f1493w;
            }
            b(button);
        }
        if (z7) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void u(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.f1473c.findViewById(g.f.f6883v);
        this.A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(16908299);
        this.F = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f1476f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.f1477g == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
        int indexOfChild = viewGroup2.indexOfChild(this.A);
        viewGroup2.removeViewAt(indexOfChild);
        viewGroup2.addView(this.f1477g, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    private void v(ViewGroup viewGroup) {
        View view = this.f1478h;
        if (view == null) {
            view = this.f1479i != 0 ? LayoutInflater.from(this.f1471a).inflate(this.f1479i, viewGroup, false) : null;
        }
        boolean z7 = view != null;
        if (!z7 || !a(view)) {
            this.f1473c.setFlags(131072, 131072);
        }
        if (!z7) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.f1473c.findViewById(g.f.f6875n);
        frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
        if (this.f1484n) {
            frameLayout.setPadding(this.f1480j, this.f1481k, this.f1482l, this.f1483m);
        }
        if (this.f1477g != null) {
            ((LinearLayout.LayoutParams) ((o0.a) viewGroup.getLayoutParams())).weight = 0.0f;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v10, types: [android.view.View] */
    private void w(ViewGroup viewGroup) {
        ViewGroup viewGroup2;
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            viewGroup2 = this.f1473c.findViewById(g.f.N);
        } else {
            this.D = (ImageView) this.f1473c.findViewById(16908294);
            if ((!TextUtils.isEmpty(this.f1475e)) && this.P) {
                TextView textView = (TextView) this.f1473c.findViewById(g.f.f6871j);
                this.E = textView;
                textView.setText(this.f1475e);
                int i8 = this.B;
                if (i8 != 0) {
                    this.D.setImageResource(i8);
                    return;
                }
                Drawable drawable = this.C;
                if (drawable != null) {
                    this.D.setImageDrawable(drawable);
                    return;
                }
                this.E.setPadding(this.D.getPaddingLeft(), this.D.getPaddingTop(), this.D.getPaddingRight(), this.D.getPaddingBottom());
                this.D.setVisibility(8);
                return;
            }
            this.f1473c.findViewById(g.f.N).setVisibility(8);
            this.D.setVisibility(8);
            viewGroup2 = viewGroup;
        }
        viewGroup2.setVisibility(8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0098, code lost:
        if (r1 != null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a3, code lost:
        if (r1 != null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a5, code lost:
        r1.setVisibility(0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void x() {
        /*
            r8 = this;
            android.view.Window r0 = r8.f1473c
            int r1 = g.f.f6880s
            android.view.View r0 = r0.findViewById(r1)
            int r1 = g.f.O
            android.view.View r1 = r0.findViewById(r1)
            int r2 = g.f.f6874m
            android.view.View r2 = r0.findViewById(r2)
            int r3 = g.f.f6872k
            android.view.View r3 = r0.findViewById(r3)
            int r4 = g.f.f6876o
            android.view.View r0 = r0.findViewById(r4)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            r8.v(r0)
            int r4 = g.f.O
            android.view.View r4 = r0.findViewById(r4)
            int r5 = g.f.f6874m
            android.view.View r5 = r0.findViewById(r5)
            int r6 = g.f.f6872k
            android.view.View r6 = r0.findViewById(r6)
            android.view.ViewGroup r1 = r8.h(r4, r1)
            android.view.ViewGroup r2 = r8.h(r5, r2)
            android.view.ViewGroup r3 = r8.h(r6, r3)
            r8.u(r2)
            r8.t(r3)
            r8.w(r1)
            int r0 = r0.getVisibility()
            r4 = 8
            r5 = 1
            r6 = 0
            if (r0 == r4) goto L58
            r0 = r5
            goto L59
        L58:
            r0 = r6
        L59:
            if (r1 == 0) goto L63
            int r7 = r1.getVisibility()
            if (r7 == r4) goto L63
            r7 = r5
            goto L64
        L63:
            r7 = r6
        L64:
            if (r3 == 0) goto L6e
            int r3 = r3.getVisibility()
            if (r3 == r4) goto L6e
            r3 = r5
            goto L6f
        L6e:
            r3 = r6
        L6f:
            if (r3 != 0) goto L7e
            if (r2 == 0) goto L7e
            int r4 = g.f.J
            android.view.View r4 = r2.findViewById(r4)
            if (r4 == 0) goto L7e
            r4.setVisibility(r6)
        L7e:
            if (r7 == 0) goto L9b
            androidx.core.widget.NestedScrollView r4 = r8.A
            if (r4 == 0) goto L87
            r4.setClipToPadding(r5)
        L87:
            java.lang.CharSequence r4 = r8.f1476f
            if (r4 != 0) goto L92
            android.widget.ListView r4 = r8.f1477g
            if (r4 == 0) goto L90
            goto L92
        L90:
            r1 = 0
            goto L98
        L92:
            int r4 = g.f.M
            android.view.View r1 = r1.findViewById(r4)
        L98:
            if (r1 == 0) goto La8
            goto La5
        L9b:
            if (r2 == 0) goto La8
            int r1 = g.f.K
            android.view.View r1 = r2.findViewById(r1)
            if (r1 == 0) goto La8
        La5:
            r1.setVisibility(r6)
        La8:
            android.widget.ListView r1 = r8.f1477g
            boolean r4 = r1 instanceof androidx.appcompat.app.AlertController.RecycleListView
            if (r4 == 0) goto Lb3
            androidx.appcompat.app.AlertController$RecycleListView r1 = (androidx.appcompat.app.AlertController.RecycleListView) r1
            r1.a(r7, r3)
        Lb3:
            if (r0 != 0) goto Lc7
            android.widget.ListView r0 = r8.f1477g
            if (r0 == 0) goto Lba
            goto Lbc
        Lba:
            androidx.core.widget.NestedScrollView r0 = r8.A
        Lbc:
            if (r0 == 0) goto Lc7
            if (r3 == 0) goto Lc1
            r6 = 2
        Lc1:
            r1 = r7 | r6
            r3 = 3
            r8.o(r2, r0, r1, r3)
        Lc7:
            android.widget.ListView r0 = r8.f1477g
            if (r0 == 0) goto Ldd
            android.widget.ListAdapter r1 = r8.H
            if (r1 == 0) goto Ldd
            r0.setAdapter(r1)
            int r1 = r8.I
            r2 = -1
            if (r1 <= r2) goto Ldd
            r0.setItemChecked(r1, r5)
            r0.setSelection(r1)
        Ldd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AlertController.x():void");
    }

    private static boolean y(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(g.a.f6801j, typedValue, true);
        return typedValue.data != 0;
    }

    public int c(int i8) {
        TypedValue typedValue = new TypedValue();
        this.f1471a.getTheme().resolveAttribute(i8, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView d() {
        return this.f1477g;
    }

    public void e() {
        this.f1472b.setContentView(i());
        x();
    }

    public boolean f(int i8, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.n(keyEvent);
    }

    public boolean g(int i8, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.n(keyEvent);
    }

    public void j(int i8, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i8, onClickListener);
        }
        if (i8 == -3) {
            this.f1494x = charSequence;
            this.f1495y = message;
            this.f1496z = drawable;
        } else if (i8 == -2) {
            this.f1490t = charSequence;
            this.f1491u = message;
            this.f1492v = drawable;
        } else if (i8 != -1) {
            throw new IllegalArgumentException("Button does not exist");
        } else {
            this.f1486p = charSequence;
            this.f1487q = message;
            this.f1488r = drawable;
        }
    }

    public void k(View view) {
        this.G = view;
    }

    public void l(int i8) {
        this.C = null;
        this.B = i8;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i8 == 0) {
                imageView.setVisibility(8);
                return;
            }
            imageView.setVisibility(0);
            this.D.setImageResource(this.B);
        }
    }

    public void m(Drawable drawable) {
        this.C = drawable;
        this.B = 0;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(8);
                return;
            }
            imageView.setVisibility(0);
            this.D.setImageDrawable(drawable);
        }
    }

    public void n(CharSequence charSequence) {
        this.f1476f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void p(CharSequence charSequence) {
        this.f1475e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void q(int i8) {
        this.f1478h = null;
        this.f1479i = i8;
        this.f1484n = false;
    }

    public void r(View view) {
        this.f1478h = view;
        this.f1479i = 0;
        this.f1484n = false;
    }

    public void s(View view, int i8, int i9, int i10, int i11) {
        this.f1478h = view;
        this.f1479i = 0;
        this.f1484n = true;
        this.f1480j = i8;
        this.f1481k = i9;
        this.f1482l = i10;
        this.f1483m = i11;
    }
}
