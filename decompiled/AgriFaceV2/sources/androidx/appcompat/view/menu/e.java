package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    Context f1879a;

    /* renamed from: b  reason: collision with root package name */
    LayoutInflater f1880b;

    /* renamed from: c  reason: collision with root package name */
    g f1881c;

    /* renamed from: d  reason: collision with root package name */
    ExpandedMenuView f1882d;

    /* renamed from: e  reason: collision with root package name */
    int f1883e;

    /* renamed from: f  reason: collision with root package name */
    int f1884f;

    /* renamed from: g  reason: collision with root package name */
    int f1885g;

    /* renamed from: h  reason: collision with root package name */
    private m.a f1886h;

    /* renamed from: i  reason: collision with root package name */
    a f1887i;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        private int f1888a = -1;

        public a() {
            a();
        }

        void a() {
            i v7 = e.this.f1881c.v();
            if (v7 != null) {
                ArrayList z7 = e.this.f1881c.z();
                int size = z7.size();
                for (int i8 = 0; i8 < size; i8++) {
                    if (((i) z7.get(i8)) == v7) {
                        this.f1888a = i8;
                        return;
                    }
                }
            }
            this.f1888a = -1;
        }

        @Override // android.widget.Adapter
        /* renamed from: b */
        public i getItem(int i8) {
            ArrayList z7 = e.this.f1881c.z();
            int i9 = i8 + e.this.f1883e;
            int i10 = this.f1888a;
            if (i10 >= 0 && i9 >= i10) {
                i9++;
            }
            return (i) z7.get(i9);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f1881c.z().size() - e.this.f1883e;
            return this.f1888a < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i8) {
            return i8;
        }

        @Override // android.widget.Adapter
        public View getView(int i8, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f1880b.inflate(eVar.f1885g, viewGroup, false);
            }
            ((n.a) view).d(getItem(i8), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public e(int i8, int i9) {
        this.f1885g = i8;
        this.f1884f = i9;
    }

    public ListAdapter a() {
        if (this.f1887i == null) {
            this.f1887i = new a();
        }
        return this.f1887i;
    }

    @Override // androidx.appcompat.view.menu.m
    public void b(g gVar, boolean z7) {
        m.a aVar = this.f1886h;
        if (aVar != null) {
            aVar.b(gVar, z7);
        }
    }

    public n c(ViewGroup viewGroup) {
        if (this.f1882d == null) {
            this.f1882d = (ExpandedMenuView) this.f1880b.inflate(g.g.f6894g, viewGroup, false);
            if (this.f1887i == null) {
                this.f1887i = new a();
            }
            this.f1882d.setAdapter((ListAdapter) this.f1887i);
            this.f1882d.setOnItemClickListener(this);
        }
        return this.f1882d;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // androidx.appcompat.view.menu.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(android.content.Context r3, androidx.appcompat.view.menu.g r4) {
        /*
            r2 = this;
            int r0 = r2.f1884f
            if (r0 == 0) goto L14
            android.view.ContextThemeWrapper r0 = new android.view.ContextThemeWrapper
            int r1 = r2.f1884f
            r0.<init>(r3, r1)
            r2.f1879a = r0
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r0)
        L11:
            r2.f1880b = r3
            goto L23
        L14:
            android.content.Context r0 = r2.f1879a
            if (r0 == 0) goto L23
            r2.f1879a = r3
            android.view.LayoutInflater r0 = r2.f1880b
            if (r0 != 0) goto L23
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            goto L11
        L23:
            r2.f1881c = r4
            androidx.appcompat.view.menu.e$a r3 = r2.f1887i
            if (r3 == 0) goto L2c
            r3.notifyDataSetChanged()
        L2c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.e.d(android.content.Context, androidx.appcompat.view.menu.g):void");
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean e(r rVar) {
        if (rVar.hasVisibleItems()) {
            new h(rVar).d(null);
            m.a aVar = this.f1886h;
            if (aVar != null) {
                aVar.c(rVar);
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(boolean z7) {
        a aVar = this.f1887i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean g() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void k(m.a aVar) {
        this.f1886h = aVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i8, long j8) {
        this.f1881c.M(this.f1887i.getItem(i8), this, 0);
    }

    public e(Context context, int i8) {
        this(i8, 0);
        this.f1879a = context;
        this.f1880b = LayoutInflater.from(context);
    }
}
