package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class f extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    g f1890a;

    /* renamed from: b  reason: collision with root package name */
    private int f1891b = -1;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1892c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f1893d;

    /* renamed from: e  reason: collision with root package name */
    private final LayoutInflater f1894e;

    /* renamed from: f  reason: collision with root package name */
    private final int f1895f;

    public f(g gVar, LayoutInflater layoutInflater, boolean z7, int i8) {
        this.f1893d = z7;
        this.f1894e = layoutInflater;
        this.f1890a = gVar;
        this.f1895f = i8;
        a();
    }

    void a() {
        i v7 = this.f1890a.v();
        if (v7 != null) {
            ArrayList z7 = this.f1890a.z();
            int size = z7.size();
            for (int i8 = 0; i8 < size; i8++) {
                if (((i) z7.get(i8)) == v7) {
                    this.f1891b = i8;
                    return;
                }
            }
        }
        this.f1891b = -1;
    }

    public g b() {
        return this.f1890a;
    }

    @Override // android.widget.Adapter
    /* renamed from: c */
    public i getItem(int i8) {
        ArrayList z7 = this.f1893d ? this.f1890a.z() : this.f1890a.E();
        int i9 = this.f1891b;
        if (i9 >= 0 && i8 >= i9) {
            i8++;
        }
        return (i) z7.get(i8);
    }

    public void d(boolean z7) {
        this.f1892c = z7;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList z7 = this.f1893d ? this.f1890a.z() : this.f1890a.E();
        int i8 = this.f1891b;
        int size = z7.size();
        return i8 < 0 ? size : size - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i8) {
        return i8;
    }

    @Override // android.widget.Adapter
    public View getView(int i8, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f1894e.inflate(this.f1895f, viewGroup, false);
        }
        int groupId = getItem(i8).getGroupId();
        int i9 = i8 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f1890a.F() && groupId != (i9 >= 0 ? getItem(i9).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.f1892c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.d(getItem(i8), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
