package l1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public abstract class c extends a {

    /* renamed from: i  reason: collision with root package name */
    private int f8970i;

    /* renamed from: j  reason: collision with root package name */
    private int f8971j;

    /* renamed from: k  reason: collision with root package name */
    private LayoutInflater f8972k;

    public c(Context context, int i8, Cursor cursor, boolean z7) {
        super(context, cursor, z7);
        this.f8971j = i8;
        this.f8970i = i8;
        this.f8972k = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // l1.a
    public View g(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f8972k.inflate(this.f8971j, viewGroup, false);
    }

    @Override // l1.a
    public View h(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f8972k.inflate(this.f8970i, viewGroup, false);
    }
}
