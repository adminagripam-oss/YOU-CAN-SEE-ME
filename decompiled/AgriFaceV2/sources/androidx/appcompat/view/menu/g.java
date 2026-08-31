package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class g implements d1.a {
    private static final int[] A = {1, 4, 5, 3, 2, 0};

    /* renamed from: a  reason: collision with root package name */
    private final Context f1896a;

    /* renamed from: b  reason: collision with root package name */
    private final Resources f1897b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1898c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1899d;

    /* renamed from: e  reason: collision with root package name */
    private a f1900e;

    /* renamed from: m  reason: collision with root package name */
    private ContextMenu.ContextMenuInfo f1908m;

    /* renamed from: n  reason: collision with root package name */
    CharSequence f1909n;

    /* renamed from: o  reason: collision with root package name */
    Drawable f1910o;

    /* renamed from: p  reason: collision with root package name */
    View f1911p;

    /* renamed from: x  reason: collision with root package name */
    private i f1919x;

    /* renamed from: z  reason: collision with root package name */
    private boolean f1921z;

    /* renamed from: l  reason: collision with root package name */
    private int f1907l = 0;

    /* renamed from: q  reason: collision with root package name */
    private boolean f1912q = false;

    /* renamed from: r  reason: collision with root package name */
    private boolean f1913r = false;

    /* renamed from: s  reason: collision with root package name */
    private boolean f1914s = false;

    /* renamed from: t  reason: collision with root package name */
    private boolean f1915t = false;

    /* renamed from: u  reason: collision with root package name */
    private boolean f1916u = false;

    /* renamed from: v  reason: collision with root package name */
    private ArrayList f1917v = new ArrayList();

    /* renamed from: w  reason: collision with root package name */
    private CopyOnWriteArrayList f1918w = new CopyOnWriteArrayList();

    /* renamed from: y  reason: collision with root package name */
    private boolean f1920y = false;

    /* renamed from: f  reason: collision with root package name */
    private ArrayList f1901f = new ArrayList();

    /* renamed from: g  reason: collision with root package name */
    private ArrayList f1902g = new ArrayList();

    /* renamed from: h  reason: collision with root package name */
    private boolean f1903h = true;

    /* renamed from: i  reason: collision with root package name */
    private ArrayList f1904i = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    private ArrayList f1905j = new ArrayList();

    /* renamed from: k  reason: collision with root package name */
    private boolean f1906k = true;

    /* loaded from: classes.dex */
    public interface a {
        boolean a(g gVar, MenuItem menuItem);

        void b(g gVar);
    }

    /* loaded from: classes.dex */
    public interface b {
        boolean a(i iVar);
    }

    public g(Context context) {
        this.f1896a = context;
        this.f1897b = context.getResources();
        c0(true);
    }

    private static int B(int i8) {
        int i9 = ((-65536) & i8) >> 16;
        if (i9 >= 0) {
            int[] iArr = A;
            if (i9 < iArr.length) {
                return (i8 & 65535) | (iArr[i9] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void N(int i8, boolean z7) {
        if (i8 < 0 || i8 >= this.f1901f.size()) {
            return;
        }
        this.f1901f.remove(i8);
        if (z7) {
            K(true);
        }
    }

    private void W(int i8, CharSequence charSequence, int i9, Drawable drawable, View view) {
        Resources C = C();
        if (view != null) {
            this.f1911p = view;
            this.f1909n = null;
            this.f1910o = null;
        } else {
            if (i8 > 0) {
                this.f1909n = C.getText(i8);
            } else if (charSequence != null) {
                this.f1909n = charSequence;
            }
            if (i9 > 0) {
                this.f1910o = androidx.core.content.a.d(u(), i9);
            } else if (drawable != null) {
                this.f1910o = drawable;
            }
            this.f1911p = null;
        }
        K(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0019, code lost:
        if (androidx.core.view.t0.h(android.view.ViewConfiguration.get(r2.f1896a), r2.f1896a) != false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c0(boolean r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L1c
            android.content.res.Resources r3 = r2.f1897b
            android.content.res.Configuration r3 = r3.getConfiguration()
            int r3 = r3.keyboard
            r0 = 1
            if (r3 == r0) goto L1c
            android.content.Context r3 = r2.f1896a
            android.view.ViewConfiguration r3 = android.view.ViewConfiguration.get(r3)
            android.content.Context r1 = r2.f1896a
            boolean r3 = androidx.core.view.t0.h(r3, r1)
            if (r3 == 0) goto L1c
            goto L1d
        L1c:
            r0 = 0
        L1d:
            r2.f1899d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.g.c0(boolean):void");
    }

    private i g(int i8, int i9, int i10, int i11, CharSequence charSequence, int i12) {
        return new i(this, i8, i9, i10, i11, charSequence, i12);
    }

    private void i(boolean z7) {
        if (this.f1918w.isEmpty()) {
            return;
        }
        e0();
        Iterator it = this.f1918w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            m mVar = (m) weakReference.get();
            if (mVar == null) {
                this.f1918w.remove(weakReference);
            } else {
                mVar.f(z7);
            }
        }
        d0();
    }

    private boolean j(r rVar, m mVar) {
        if (this.f1918w.isEmpty()) {
            return false;
        }
        boolean e8 = mVar != null ? mVar.e(rVar) : false;
        Iterator it = this.f1918w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            m mVar2 = (m) weakReference.get();
            if (mVar2 == null) {
                this.f1918w.remove(weakReference);
            } else if (!e8) {
                e8 = mVar2.e(rVar);
            }
        }
        return e8;
    }

    private static int n(ArrayList arrayList, int i8) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((i) arrayList.get(size)).f() <= i8) {
                return size + 1;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean A() {
        return this.f1915t;
    }

    Resources C() {
        return this.f1897b;
    }

    public g D() {
        return this;
    }

    public ArrayList E() {
        if (this.f1903h) {
            this.f1902g.clear();
            int size = this.f1901f.size();
            for (int i8 = 0; i8 < size; i8++) {
                i iVar = (i) this.f1901f.get(i8);
                if (iVar.isVisible()) {
                    this.f1902g.add(iVar);
                }
            }
            this.f1903h = false;
            this.f1906k = true;
            return this.f1902g;
        }
        return this.f1902g;
    }

    public boolean F() {
        return this.f1920y;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean G() {
        return this.f1898c;
    }

    public boolean H() {
        return this.f1899d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I(i iVar) {
        this.f1906k = true;
        K(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(i iVar) {
        this.f1903h = true;
        K(true);
    }

    public void K(boolean z7) {
        if (this.f1912q) {
            this.f1913r = true;
            if (z7) {
                this.f1914s = true;
                return;
            }
            return;
        }
        if (z7) {
            this.f1903h = true;
            this.f1906k = true;
        }
        i(z7);
    }

    public boolean L(MenuItem menuItem, int i8) {
        return M(menuItem, null, i8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
        if (r1 != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002d, code lost:
        e(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003c, code lost:
        if ((r9 & 1) == 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0068, code lost:
        if (r1 == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean M(android.view.MenuItem r7, androidx.appcompat.view.menu.m r8, int r9) {
        /*
            r6 = this;
            androidx.appcompat.view.menu.i r7 = (androidx.appcompat.view.menu.i) r7
            r0 = 0
            if (r7 == 0) goto L6c
            boolean r1 = r7.isEnabled()
            if (r1 != 0) goto Lc
            goto L6c
        Lc:
            boolean r1 = r7.k()
            androidx.core.view.b r2 = r7.b()
            r3 = 1
            if (r2 == 0) goto L1f
            boolean r4 = r2.a()
            if (r4 == 0) goto L1f
            r4 = r3
            goto L20
        L1f:
            r4 = r0
        L20:
            boolean r5 = r7.j()
            if (r5 == 0) goto L31
            boolean r7 = r7.expandActionView()
            r1 = r1 | r7
            if (r1 == 0) goto L6b
        L2d:
            r6.e(r3)
            goto L6b
        L31:
            boolean r5 = r7.hasSubMenu()
            if (r5 != 0) goto L3f
            if (r4 == 0) goto L3a
            goto L3f
        L3a:
            r7 = r9 & 1
            if (r7 != 0) goto L6b
            goto L2d
        L3f:
            r9 = r9 & 4
            if (r9 != 0) goto L46
            r6.e(r0)
        L46:
            boolean r9 = r7.hasSubMenu()
            if (r9 != 0) goto L58
            androidx.appcompat.view.menu.r r9 = new androidx.appcompat.view.menu.r
            android.content.Context r0 = r6.u()
            r9.<init>(r0, r6, r7)
            r7.x(r9)
        L58:
            android.view.SubMenu r7 = r7.getSubMenu()
            androidx.appcompat.view.menu.r r7 = (androidx.appcompat.view.menu.r) r7
            if (r4 == 0) goto L63
            r2.e(r7)
        L63:
            boolean r7 = r6.j(r7, r8)
            r1 = r1 | r7
            if (r1 != 0) goto L6b
            goto L2d
        L6b:
            return r1
        L6c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.g.M(android.view.MenuItem, androidx.appcompat.view.menu.m, int):boolean");
    }

    public void O(m mVar) {
        Iterator it = this.f1918w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            m mVar2 = (m) weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
                this.f1918w.remove(weakReference);
            }
        }
    }

    public void P(Bundle bundle) {
        MenuItem findItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(t());
        int size = size();
        for (int i8 = 0; i8 < size; i8++) {
            MenuItem item = getItem(i8);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).P(bundle);
            }
        }
        int i9 = bundle.getInt("android:menu:expandedactionview");
        if (i9 <= 0 || (findItem = findItem(i9)) == null) {
            return;
        }
        findItem.expandActionView();
    }

    public void Q(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i8 = 0; i8 < size; i8++) {
            MenuItem item = getItem(i8);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).Q(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(t(), sparseArray);
        }
    }

    public void R(a aVar) {
        this.f1900e = aVar;
    }

    public g S(int i8) {
        this.f1907l = i8;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void T(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f1901f.size();
        e0();
        for (int i8 = 0; i8 < size; i8++) {
            i iVar = (i) this.f1901f.get(i8);
            if (iVar.getGroupId() == groupId && iVar.m() && iVar.isCheckable()) {
                iVar.s(iVar == menuItem);
            }
        }
        d0();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g U(int i8) {
        W(0, null, i8, null, null);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g V(Drawable drawable) {
        W(0, null, 0, drawable, null);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g X(int i8) {
        W(i8, null, 0, null, null);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g Y(CharSequence charSequence) {
        W(0, charSequence, 0, null, null);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g Z(View view) {
        W(0, null, 0, null, view);
        return this;
    }

    protected MenuItem a(int i8, int i9, int i10, CharSequence charSequence) {
        int B = B(i10);
        i g8 = g(i8, i9, i10, B, charSequence, this.f1907l);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.f1908m;
        if (contextMenuInfo != null) {
            g8.v(contextMenuInfo);
        }
        ArrayList arrayList = this.f1901f;
        arrayList.add(n(arrayList, B), g8);
        K(true);
        return g8;
    }

    public void a0(boolean z7) {
        this.f1915t = z7;
    }

    @Override // android.view.Menu
    public MenuItem add(int i8) {
        return a(0, 0, 0, this.f1897b.getString(i8));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i8, int i9, int i10, ComponentName componentName, Intent[] intentArr, Intent intent, int i11, MenuItem[] menuItemArr) {
        int i12;
        PackageManager packageManager = this.f1896a.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i11 & 1) == 0) {
            removeGroup(i8);
        }
        for (int i13 = 0; i13 < size; i13++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i13);
            int i14 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i14 < 0 ? intent : intentArr[i14]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i8, i9, i10, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i12 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i12] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i8) {
        return addSubMenu(0, 0, 0, this.f1897b.getString(i8));
    }

    public void b(m mVar) {
        c(mVar, this.f1896a);
    }

    public void b0(boolean z7) {
        this.f1921z = z7;
    }

    public void c(m mVar, Context context) {
        this.f1918w.add(new WeakReference(mVar));
        mVar.d(context, this);
        this.f1906k = true;
    }

    @Override // android.view.Menu
    public void clear() {
        i iVar = this.f1919x;
        if (iVar != null) {
            f(iVar);
        }
        this.f1901f.clear();
        K(true);
    }

    public void clearHeader() {
        this.f1910o = null;
        this.f1909n = null;
        this.f1911p = null;
        K(false);
    }

    @Override // android.view.Menu
    public void close() {
        e(true);
    }

    public void d() {
        a aVar = this.f1900e;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    public void d0() {
        this.f1912q = false;
        if (this.f1913r) {
            this.f1913r = false;
            K(this.f1914s);
        }
    }

    public final void e(boolean z7) {
        if (this.f1916u) {
            return;
        }
        this.f1916u = true;
        Iterator it = this.f1918w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            m mVar = (m) weakReference.get();
            if (mVar == null) {
                this.f1918w.remove(weakReference);
            } else {
                mVar.b(this, z7);
            }
        }
        this.f1916u = false;
    }

    public void e0() {
        if (this.f1912q) {
            return;
        }
        this.f1912q = true;
        this.f1913r = false;
        this.f1914s = false;
    }

    public boolean f(i iVar) {
        boolean z7 = false;
        if (!this.f1918w.isEmpty() && this.f1919x == iVar) {
            e0();
            Iterator it = this.f1918w.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                m mVar = (m) weakReference.get();
                if (mVar == null) {
                    this.f1918w.remove(weakReference);
                } else {
                    z7 = mVar.i(this, iVar);
                    if (z7) {
                        break;
                    }
                }
            }
            d0();
            if (z7) {
                this.f1919x = null;
            }
        }
        return z7;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i8) {
        MenuItem findItem;
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = (i) this.f1901f.get(i9);
            if (iVar.getItemId() == i8) {
                return iVar;
            }
            if (iVar.hasSubMenu() && (findItem = iVar.getSubMenu().findItem(i8)) != null) {
                return findItem;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i8) {
        return (MenuItem) this.f1901f.get(i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h(g gVar, MenuItem menuItem) {
        a aVar = this.f1900e;
        return aVar != null && aVar.a(gVar, menuItem);
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.f1921z) {
            return true;
        }
        int size = size();
        for (int i8 = 0; i8 < size; i8++) {
            if (((i) this.f1901f.get(i8)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i8, KeyEvent keyEvent) {
        return p(i8, keyEvent) != null;
    }

    public boolean k(i iVar) {
        boolean z7 = false;
        if (this.f1918w.isEmpty()) {
            return false;
        }
        e0();
        Iterator it = this.f1918w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            m mVar = (m) weakReference.get();
            if (mVar == null) {
                this.f1918w.remove(weakReference);
            } else {
                z7 = mVar.j(this, iVar);
                if (z7) {
                    break;
                }
            }
        }
        d0();
        if (z7) {
            this.f1919x = iVar;
        }
        return z7;
    }

    public int l(int i8) {
        return m(i8, 0);
    }

    public int m(int i8, int i9) {
        int size = size();
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < size) {
            if (((i) this.f1901f.get(i9)).getGroupId() == i8) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public int o(int i8) {
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            if (((i) this.f1901f.get(i9)).getItemId() == i8) {
                return i9;
            }
        }
        return -1;
    }

    i p(int i8, KeyEvent keyEvent) {
        ArrayList arrayList = this.f1917v;
        arrayList.clear();
        q(arrayList, i8, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (i) arrayList.get(0);
        }
        boolean G = G();
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = (i) arrayList.get(i9);
            char alphabeticShortcut = G ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (G && alphabeticShortcut == '\b' && i8 == 67))) {
                return iVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i8, int i9) {
        return L(findItem(i8), i9);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i8, KeyEvent keyEvent, int i9) {
        i p8 = p(i8, keyEvent);
        boolean L = p8 != null ? L(p8, i9) : false;
        if ((i9 & 2) != 0) {
            e(true);
        }
        return L;
    }

    void q(List list, int i8, KeyEvent keyEvent) {
        boolean G = G();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i8 == 67) {
            int size = this.f1901f.size();
            for (int i9 = 0; i9 < size; i9++) {
                i iVar = (i) this.f1901f.get(i9);
                if (iVar.hasSubMenu()) {
                    ((g) iVar.getSubMenu()).q(list, i8, keyEvent);
                }
                char alphabeticShortcut = G ? iVar.getAlphabeticShortcut() : iVar.getNumericShortcut();
                if (((modifiers & 69647) == ((G ? iVar.getAlphabeticModifiers() : iVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (G && alphabeticShortcut == '\b' && i8 == 67)) && iVar.isEnabled()) {
                        list.add(iVar);
                    }
                }
            }
        }
    }

    public void r() {
        ArrayList E = E();
        if (this.f1906k) {
            Iterator it = this.f1918w.iterator();
            boolean z7 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                m mVar = (m) weakReference.get();
                if (mVar == null) {
                    this.f1918w.remove(weakReference);
                } else {
                    z7 |= mVar.g();
                }
            }
            if (z7) {
                this.f1904i.clear();
                this.f1905j.clear();
                int size = E.size();
                for (int i8 = 0; i8 < size; i8++) {
                    i iVar = (i) E.get(i8);
                    (iVar.l() ? this.f1904i : this.f1905j).add(iVar);
                }
            } else {
                this.f1904i.clear();
                this.f1905j.clear();
                this.f1905j.addAll(E());
            }
            this.f1906k = false;
        }
    }

    @Override // android.view.Menu
    public void removeGroup(int i8) {
        int l8 = l(i8);
        if (l8 >= 0) {
            int size = this.f1901f.size() - l8;
            int i9 = 0;
            while (true) {
                int i10 = i9 + 1;
                if (i9 >= size || ((i) this.f1901f.get(l8)).getGroupId() != i8) {
                    break;
                }
                N(l8, false);
                i9 = i10;
            }
            K(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i8) {
        N(o(i8), true);
    }

    public ArrayList s() {
        r();
        return this.f1904i;
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i8, boolean z7, boolean z8) {
        int size = this.f1901f.size();
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = (i) this.f1901f.get(i9);
            if (iVar.getGroupId() == i8) {
                iVar.t(z8);
                iVar.setCheckable(z7);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z7) {
        this.f1920y = z7;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i8, boolean z7) {
        int size = this.f1901f.size();
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = (i) this.f1901f.get(i9);
            if (iVar.getGroupId() == i8) {
                iVar.setEnabled(z7);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i8, boolean z7) {
        int size = this.f1901f.size();
        boolean z8 = false;
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = (i) this.f1901f.get(i9);
            if (iVar.getGroupId() == i8 && iVar.y(z7)) {
                z8 = true;
            }
        }
        if (z8) {
            K(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z7) {
        this.f1898c = z7;
        K(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f1901f.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String t() {
        return "android:menu:actionviewstates";
    }

    public Context u() {
        return this.f1896a;
    }

    public i v() {
        return this.f1919x;
    }

    public Drawable w() {
        return this.f1910o;
    }

    public CharSequence x() {
        return this.f1909n;
    }

    public View y() {
        return this.f1911p;
    }

    public ArrayList z() {
        r();
        return this.f1905j;
    }

    @Override // android.view.Menu
    public MenuItem add(int i8, int i9, int i10, int i11) {
        return a(i8, i9, i10, this.f1897b.getString(i11));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i8, int i9, int i10, int i11) {
        return addSubMenu(i8, i9, i10, this.f1897b.getString(i11));
    }

    @Override // android.view.Menu
    public MenuItem add(int i8, int i9, int i10, CharSequence charSequence) {
        return a(i8, i9, i10, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i8, int i9, int i10, CharSequence charSequence) {
        i iVar = (i) a(i8, i9, i10, charSequence);
        r rVar = new r(this.f1896a, this, iVar);
        iVar.x(rVar);
        return rVar;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }
}
