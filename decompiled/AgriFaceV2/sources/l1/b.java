package l1;

import android.database.Cursor;
import android.widget.Filter;
/* loaded from: classes.dex */
class b extends Filter {

    /* renamed from: a  reason: collision with root package name */
    a f8969a;

    /* loaded from: classes.dex */
    interface a {
        void a(Cursor cursor);

        Cursor b();

        CharSequence c(Cursor cursor);

        Cursor d(CharSequence charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar) {
        this.f8969a = aVar;
    }

    @Override // android.widget.Filter
    public CharSequence convertResultToString(Object obj) {
        return this.f8969a.c((Cursor) obj);
    }

    @Override // android.widget.Filter
    protected Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor d8 = this.f8969a.d(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (d8 != null) {
            filterResults.count = d8.getCount();
        } else {
            filterResults.count = 0;
            d8 = null;
        }
        filterResults.values = d8;
        return filterResults;
    }

    @Override // android.widget.Filter
    protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        Cursor b8 = this.f8969a.b();
        Object obj = filterResults.values;
        if (obj == null || obj == b8) {
            return;
        }
        this.f8969a.a((Cursor) obj);
    }
}
