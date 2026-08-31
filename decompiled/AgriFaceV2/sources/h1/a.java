package h1;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
/* loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* renamed from: a  reason: collision with root package name */
    private final int f7136a;

    /* renamed from: b  reason: collision with root package name */
    private final r f7137b;

    /* renamed from: c  reason: collision with root package name */
    private final int f7138c;

    public a(int i8, r rVar, int i9) {
        this.f7136a = i8;
        this.f7137b = rVar;
        this.f7138c = i9;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f7136a);
        this.f7137b.N(this.f7138c, bundle);
    }
}
