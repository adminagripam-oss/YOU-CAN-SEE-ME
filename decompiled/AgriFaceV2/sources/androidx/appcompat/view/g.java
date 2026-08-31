package androidx.appcompat.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.b1;
import androidx.appcompat.widget.k0;
import androidx.core.view.a0;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class g extends MenuInflater {

    /* renamed from: e  reason: collision with root package name */
    static final Class[] f1734e;

    /* renamed from: f  reason: collision with root package name */
    static final Class[] f1735f;

    /* renamed from: a  reason: collision with root package name */
    final Object[] f1736a;

    /* renamed from: b  reason: collision with root package name */
    final Object[] f1737b;

    /* renamed from: c  reason: collision with root package name */
    Context f1738c;

    /* renamed from: d  reason: collision with root package name */
    private Object f1739d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* renamed from: c  reason: collision with root package name */
        private static final Class[] f1740c = {MenuItem.class};

        /* renamed from: a  reason: collision with root package name */
        private Object f1741a;

        /* renamed from: b  reason: collision with root package name */
        private Method f1742b;

        public a(Object obj, String str) {
            this.f1741a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f1742b = cls.getMethod(str, f1740c);
            } catch (Exception e8) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e8);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f1742b.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f1742b.invoke(this.f1741a, menuItem)).booleanValue();
                }
                this.f1742b.invoke(this.f1741a, menuItem);
                return true;
            } catch (Exception e8) {
                throw new RuntimeException(e8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b {
        androidx.core.view.b A;
        private CharSequence B;
        private CharSequence C;
        private ColorStateList D = null;
        private PorterDuff.Mode E = null;

        /* renamed from: a  reason: collision with root package name */
        private Menu f1743a;

        /* renamed from: b  reason: collision with root package name */
        private int f1744b;

        /* renamed from: c  reason: collision with root package name */
        private int f1745c;

        /* renamed from: d  reason: collision with root package name */
        private int f1746d;

        /* renamed from: e  reason: collision with root package name */
        private int f1747e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f1748f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f1749g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f1750h;

        /* renamed from: i  reason: collision with root package name */
        private int f1751i;

        /* renamed from: j  reason: collision with root package name */
        private int f1752j;

        /* renamed from: k  reason: collision with root package name */
        private CharSequence f1753k;

        /* renamed from: l  reason: collision with root package name */
        private CharSequence f1754l;

        /* renamed from: m  reason: collision with root package name */
        private int f1755m;

        /* renamed from: n  reason: collision with root package name */
        private char f1756n;

        /* renamed from: o  reason: collision with root package name */
        private int f1757o;

        /* renamed from: p  reason: collision with root package name */
        private char f1758p;

        /* renamed from: q  reason: collision with root package name */
        private int f1759q;

        /* renamed from: r  reason: collision with root package name */
        private int f1760r;

        /* renamed from: s  reason: collision with root package name */
        private boolean f1761s;

        /* renamed from: t  reason: collision with root package name */
        private boolean f1762t;

        /* renamed from: u  reason: collision with root package name */
        private boolean f1763u;

        /* renamed from: v  reason: collision with root package name */
        private int f1764v;

        /* renamed from: w  reason: collision with root package name */
        private int f1765w;

        /* renamed from: x  reason: collision with root package name */
        private String f1766x;

        /* renamed from: y  reason: collision with root package name */
        private String f1767y;

        /* renamed from: z  reason: collision with root package name */
        private String f1768z;

        public b(Menu menu) {
            this.f1743a = menu;
            h();
        }

        private char c(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        private Object e(String str, Class[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, g.this.f1738c.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return constructor.newInstance(objArr);
            } catch (Exception e8) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e8);
                return null;
            }
        }

        private void i(MenuItem menuItem) {
            boolean z7 = false;
            menuItem.setChecked(this.f1761s).setVisible(this.f1762t).setEnabled(this.f1763u).setCheckable(this.f1760r >= 1).setTitleCondensed(this.f1754l).setIcon(this.f1755m);
            int i8 = this.f1764v;
            if (i8 >= 0) {
                menuItem.setShowAsAction(i8);
            }
            if (this.f1768z != null) {
                if (g.this.f1738c.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.this.b(), this.f1768z));
            }
            if (this.f1760r >= 2) {
                if (menuItem instanceof androidx.appcompat.view.menu.i) {
                    ((androidx.appcompat.view.menu.i) menuItem).t(true);
                } else if (menuItem instanceof j) {
                    ((j) menuItem).h(true);
                }
            }
            String str = this.f1766x;
            if (str != null) {
                menuItem.setActionView((View) e(str, g.f1734e, g.this.f1736a));
                z7 = true;
            }
            int i9 = this.f1765w;
            if (i9 > 0) {
                if (z7) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i9);
                }
            }
            androidx.core.view.b bVar = this.A;
            if (bVar != null) {
                a0.a(menuItem, bVar);
            }
            a0.c(menuItem, this.B);
            a0.g(menuItem, this.C);
            a0.b(menuItem, this.f1756n, this.f1757o);
            a0.f(menuItem, this.f1758p, this.f1759q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                a0.e(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                a0.d(menuItem, colorStateList);
            }
        }

        public void a() {
            this.f1750h = true;
            i(this.f1743a.add(this.f1744b, this.f1751i, this.f1752j, this.f1753k));
        }

        public SubMenu b() {
            this.f1750h = true;
            SubMenu addSubMenu = this.f1743a.addSubMenu(this.f1744b, this.f1751i, this.f1752j, this.f1753k);
            i(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean d() {
            return this.f1750h;
        }

        public void f(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = g.this.f1738c.obtainStyledAttributes(attributeSet, g.j.f6995o1);
            this.f1744b = obtainStyledAttributes.getResourceId(g.j.f7005q1, 0);
            this.f1745c = obtainStyledAttributes.getInt(g.j.f7015s1, 0);
            this.f1746d = obtainStyledAttributes.getInt(g.j.f7020t1, 0);
            this.f1747e = obtainStyledAttributes.getInt(g.j.f7025u1, 0);
            this.f1748f = obtainStyledAttributes.getBoolean(g.j.f7010r1, true);
            this.f1749g = obtainStyledAttributes.getBoolean(g.j.f7000p1, true);
            obtainStyledAttributes.recycle();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void g(AttributeSet attributeSet) {
            b1 t8 = b1.t(g.this.f1738c, attributeSet, g.j.f7030v1);
            this.f1751i = t8.m(g.j.f7045y1, 0);
            this.f1752j = (t8.j(g.j.B1, this.f1745c) & (-65536)) | (t8.j(g.j.C1, this.f1746d) & 65535);
            this.f1753k = t8.o(g.j.D1);
            this.f1754l = t8.o(g.j.E1);
            this.f1755m = t8.m(g.j.f7035w1, 0);
            this.f1756n = c(t8.n(g.j.F1));
            this.f1757o = t8.j(g.j.M1, 4096);
            this.f1758p = c(t8.n(g.j.G1));
            this.f1759q = t8.j(g.j.Q1, 4096);
            this.f1760r = t8.r(g.j.H1) ? t8.a(g.j.H1, false) : this.f1747e;
            this.f1761s = t8.a(g.j.f7050z1, false);
            this.f1762t = t8.a(g.j.A1, this.f1748f);
            this.f1763u = t8.a(g.j.f7040x1, this.f1749g);
            this.f1764v = t8.j(g.j.R1, -1);
            this.f1768z = t8.n(g.j.I1);
            this.f1765w = t8.m(g.j.J1, 0);
            this.f1766x = t8.n(g.j.L1);
            String n8 = t8.n(g.j.K1);
            this.f1767y = n8;
            boolean z7 = n8 != null;
            if (z7 && this.f1765w == 0 && this.f1766x == null) {
                this.A = (androidx.core.view.b) e(n8, g.f1735f, g.this.f1737b);
            } else {
                if (z7) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = t8.o(g.j.N1);
            this.C = t8.o(g.j.S1);
            if (t8.r(g.j.P1)) {
                this.E = k0.d(t8.j(g.j.P1, -1), this.E);
            } else {
                this.E = null;
            }
            if (t8.r(g.j.O1)) {
                this.D = t8.c(g.j.O1);
            } else {
                this.D = null;
            }
            t8.v();
            this.f1750h = false;
        }

        public void h() {
            this.f1744b = 0;
            this.f1745c = 0;
            this.f1746d = 0;
            this.f1747e = 0;
            this.f1748f = true;
            this.f1749g = true;
        }
    }

    static {
        Class[] clsArr = {Context.class};
        f1734e = clsArr;
        f1735f = clsArr;
    }

    public g(Context context) {
        super(context);
        this.f1738c = context;
        Object[] objArr = {context};
        this.f1736a = objArr;
        this.f1737b = objArr;
    }

    private Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:
        r6 = false;
        r7 = false;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0040, code lost:
        if (r6 != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
        if (r15 == 1) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0048, code lost:
        if (r15 == 2) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        if (r15 == 3) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004f, code lost:
        r15 = r13.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0053, code lost:
        if (r7 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0059, code lost:
        if (r15.equals(r8) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005b, code lost:
        r7 = false;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0062, code lost:
        if (r15.equals("group") == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0064, code lost:
        r0.h();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006c, code lost:
        if (r15.equals("item") == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0072, code lost:
        if (r0.d() != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0074, code lost:
        r15 = r0.A;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0076, code lost:
        if (r15 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007c, code lost:
        if (r15.a() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007e, code lost:
        r0.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0082, code lost:
        r0.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008a, code lost:
        if (r15.equals("menu") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008c, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008e, code lost:
        if (r7 == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0091, code lost:
        r15 = r13.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0099, code lost:
        if (r15.equals("group") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009b, code lost:
        r0.f(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a3, code lost:
        if (r15.equals("item") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a5, code lost:
        r0.g(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ad, code lost:
        if (r15.equals("menu") == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00af, code lost:
        c(r13, r14, r0.b());
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b7, code lost:
        r8 = r15;
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b9, code lost:
        r15 = r13.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00c5, code lost:
        throw new java.lang.RuntimeException("Unexpected end of document");
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c6, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(org.xmlpull.v1.XmlPullParser r13, android.util.AttributeSet r14, android.view.Menu r15) {
        /*
            r12 = this;
            androidx.appcompat.view.g$b r0 = new androidx.appcompat.view.g$b
            r0.<init>(r15)
            int r15 = r13.getEventType()
        L9:
            r1 = 2
            java.lang.String r2 = "menu"
            r3 = 1
            if (r15 != r1) goto L35
            java.lang.String r15 = r13.getName()
            boolean r4 = r15.equals(r2)
            if (r4 == 0) goto L1e
            int r15 = r13.next()
            goto L3b
        L1e:
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "Expecting menu, got "
            r14.append(r0)
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L35:
            int r15 = r13.next()
            if (r15 != r3) goto L9
        L3b:
            r4 = 0
            r5 = 0
            r6 = r4
            r7 = r6
            r8 = r5
        L40:
            if (r6 != 0) goto Lc6
            if (r15 == r3) goto Lbe
            java.lang.String r9 = "item"
            java.lang.String r10 = "group"
            if (r15 == r1) goto L8e
            r11 = 3
            if (r15 == r11) goto L4f
            goto Lb9
        L4f:
            java.lang.String r15 = r13.getName()
            if (r7 == 0) goto L5e
            boolean r11 = r15.equals(r8)
            if (r11 == 0) goto L5e
            r7 = r4
            r8 = r5
            goto Lb9
        L5e:
            boolean r10 = r15.equals(r10)
            if (r10 == 0) goto L68
            r0.h()
            goto Lb9
        L68:
            boolean r9 = r15.equals(r9)
            if (r9 == 0) goto L86
            boolean r15 = r0.d()
            if (r15 != 0) goto Lb9
            androidx.core.view.b r15 = r0.A
            if (r15 == 0) goto L82
            boolean r15 = r15.a()
            if (r15 == 0) goto L82
            r0.b()
            goto Lb9
        L82:
            r0.a()
            goto Lb9
        L86:
            boolean r15 = r15.equals(r2)
            if (r15 == 0) goto Lb9
            r6 = r3
            goto Lb9
        L8e:
            if (r7 == 0) goto L91
            goto Lb9
        L91:
            java.lang.String r15 = r13.getName()
            boolean r10 = r15.equals(r10)
            if (r10 == 0) goto L9f
            r0.f(r14)
            goto Lb9
        L9f:
            boolean r9 = r15.equals(r9)
            if (r9 == 0) goto La9
            r0.g(r14)
            goto Lb9
        La9:
            boolean r9 = r15.equals(r2)
            if (r9 == 0) goto Lb7
            android.view.SubMenu r15 = r0.b()
            r12.c(r13, r14, r15)
            goto Lb9
        Lb7:
            r8 = r15
            r7 = r3
        Lb9:
            int r15 = r13.next()
            goto L40
        Lbe:
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            java.lang.String r14 = "Unexpected end of document"
            r13.<init>(r14)
            throw r13
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.g.c(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    Object b() {
        if (this.f1739d == null) {
            this.f1739d = a(this.f1738c);
        }
        return this.f1739d;
    }

    @Override // android.view.MenuInflater
    public void inflate(int i8, Menu menu) {
        if (!(menu instanceof d1.a)) {
            super.inflate(i8, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                try {
                    xmlResourceParser = this.f1738c.getResources().getLayout(i8);
                    c(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
                } catch (XmlPullParserException e8) {
                    throw new InflateException("Error inflating menu XML", e8);
                }
            } catch (IOException e9) {
                throw new InflateException("Error inflating menu XML", e9);
            }
        } finally {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        }
    }
}
