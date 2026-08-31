package io.flutter.plugin.editing;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private CharSequence f7888a;

    /* renamed from: b  reason: collision with root package name */
    private CharSequence f7889b;

    /* renamed from: c  reason: collision with root package name */
    private int f7890c;

    /* renamed from: d  reason: collision with root package name */
    private int f7891d;

    /* renamed from: e  reason: collision with root package name */
    private int f7892e;

    /* renamed from: f  reason: collision with root package name */
    private int f7893f;

    /* renamed from: g  reason: collision with root package name */
    private int f7894g;

    /* renamed from: h  reason: collision with root package name */
    private int f7895h;

    public k(CharSequence charSequence, int i8, int i9, int i10, int i11) {
        this.f7892e = i8;
        this.f7893f = i9;
        this.f7894g = i10;
        this.f7895h = i11;
        a(charSequence, "", -1, -1);
    }

    private void a(CharSequence charSequence, CharSequence charSequence2, int i8, int i9) {
        this.f7888a = charSequence;
        this.f7889b = charSequence2;
        this.f7890c = i8;
        this.f7891d = i9;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("oldText", this.f7888a.toString());
            jSONObject.put("deltaText", this.f7889b.toString());
            jSONObject.put("deltaStart", this.f7890c);
            jSONObject.put("deltaEnd", this.f7891d);
            jSONObject.put("selectionBase", this.f7892e);
            jSONObject.put("selectionExtent", this.f7893f);
            jSONObject.put("composingBase", this.f7894g);
            jSONObject.put("composingExtent", this.f7895h);
        } catch (JSONException e8) {
            z5.b.b("TextEditingDelta", "unable to create JSONObject: " + e8);
        }
        return jSONObject;
    }

    public k(CharSequence charSequence, int i8, int i9, CharSequence charSequence2, int i10, int i11, int i12, int i13) {
        this.f7892e = i10;
        this.f7893f = i11;
        this.f7894g = i12;
        this.f7895h = i13;
        a(charSequence, charSequence2.toString(), i8, i9);
    }
}
