package l1;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import l1.b;
/* loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f8959a;

    /* renamed from: b  reason: collision with root package name */
    protected boolean f8960b;

    /* renamed from: c  reason: collision with root package name */
    protected Cursor f8961c;

    /* renamed from: d  reason: collision with root package name */
    protected Context f8962d;

    /* renamed from: e  reason: collision with root package name */
    protected int f8963e;

    /* renamed from: f  reason: collision with root package name */
    protected C0120a f8964f;

    /* renamed from: g  reason: collision with root package name */
    protected DataSetObserver f8965g;

    /* renamed from: h  reason: collision with root package name */
    protected l1.b f8966h;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l1.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0120a extends ContentObserver {
        C0120a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z7) {
            a.this.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f8959a = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f8959a = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z7) {
        f(context, cursor, z7 ? 1 : 2);
    }

    public void a(Cursor cursor) {
        Cursor j8 = j(cursor);
        if (j8 != null) {
            j8.close();
        }
    }

    @Override // l1.b.a
    public Cursor b() {
        return this.f8961c;
    }

    public abstract CharSequence c(Cursor cursor);

    public abstract void e(View view, Context context, Cursor cursor);

    void f(Context context, Cursor cursor, int i8) {
        b bVar;
        if ((i8 & 1) == 1) {
            i8 |= 2;
            this.f8960b = true;
        } else {
            this.f8960b = false;
        }
        boolean z7 = cursor != null;
        this.f8961c = cursor;
        this.f8959a = z7;
        this.f8962d = context;
        this.f8963e = z7 ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i8 & 2) == 2) {
            this.f8964f = new C0120a();
            bVar = new b();
        } else {
            bVar = null;
            this.f8964f = null;
        }
        this.f8965g = bVar;
        if (z7) {
            C0120a c0120a = this.f8964f;
            if (c0120a != null) {
                cursor.registerContentObserver(c0120a);
            }
            DataSetObserver dataSetObserver = this.f8965g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract View g(Context context, Cursor cursor, ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f8959a || (cursor = this.f8961c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i8, View view, ViewGroup viewGroup) {
        if (this.f8959a) {
            this.f8961c.moveToPosition(i8);
            if (view == null) {
                view = g(this.f8962d, this.f8961c, viewGroup);
            }
            e(view, this.f8962d, this.f8961c);
            return view;
        }
        return null;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f8966h == null) {
            this.f8966h = new l1.b(this);
        }
        return this.f8966h;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i8) {
        Cursor cursor;
        if (!this.f8959a || (cursor = this.f8961c) == null) {
            return null;
        }
        cursor.moveToPosition(i8);
        return this.f8961c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i8) {
        Cursor cursor;
        if (this.f8959a && (cursor = this.f8961c) != null && cursor.moveToPosition(i8)) {
            return this.f8961c.getLong(this.f8963e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i8, View view, ViewGroup viewGroup) {
        if (this.f8959a) {
            if (this.f8961c.moveToPosition(i8)) {
                if (view == null) {
                    view = h(this.f8962d, this.f8961c, viewGroup);
                }
                e(view, this.f8962d, this.f8961c);
                return view;
            }
            throw new IllegalStateException("couldn't move cursor to position " + i8);
        }
        throw new IllegalStateException("this should only be called when the cursor is valid");
    }

    public abstract View h(Context context, Cursor cursor, ViewGroup viewGroup);

    protected void i() {
        Cursor cursor;
        if (!this.f8960b || (cursor = this.f8961c) == null || cursor.isClosed()) {
            return;
        }
        this.f8959a = this.f8961c.requery();
    }

    public Cursor j(Cursor cursor) {
        Cursor cursor2 = this.f8961c;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0120a c0120a = this.f8964f;
            if (c0120a != null) {
                cursor2.unregisterContentObserver(c0120a);
            }
            DataSetObserver dataSetObserver = this.f8965g;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f8961c = cursor;
        if (cursor != null) {
            C0120a c0120a2 = this.f8964f;
            if (c0120a2 != null) {
                cursor.registerContentObserver(c0120a2);
            }
            DataSetObserver dataSetObserver2 = this.f8965g;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f8963e = cursor.getColumnIndexOrThrow("_id");
            this.f8959a = true;
            notifyDataSetChanged();
        } else {
            this.f8963e = -1;
            this.f8959a = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }
}
