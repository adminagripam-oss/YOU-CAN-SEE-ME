package h6;

import i6.a;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.g;
import java.util.HashMap;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final i6.a f7268a;

    /* renamed from: b  reason: collision with root package name */
    public final FlutterJNI f7269b;

    /* renamed from: c  reason: collision with root package name */
    private b f7270c;

    /* renamed from: d  reason: collision with root package name */
    public final a.d f7271d;

    /* renamed from: h6.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0096a implements a.d {
        C0096a() {
        }

        @Override // i6.a.d
        public void a(Object obj, a.e eVar) {
            if (a.this.f7270c == null) {
                eVar.a(null);
                return;
            }
            HashMap hashMap = (HashMap) obj;
            String str = (String) hashMap.get("type");
            HashMap hashMap2 = (HashMap) hashMap.get("data");
            z5.b.f("AccessibilityChannel", "Received " + str + " message.");
            str.hashCode();
            char c8 = 65535;
            switch (str.hashCode()) {
                case -1140076541:
                    if (str.equals("tooltip")) {
                        c8 = 0;
                        break;
                    }
                    break;
                case -649620375:
                    if (str.equals("announce")) {
                        c8 = 1;
                        break;
                    }
                    break;
                case 114595:
                    if (str.equals("tap")) {
                        c8 = 2;
                        break;
                    }
                    break;
                case 97604824:
                    if (str.equals("focus")) {
                        c8 = 3;
                        break;
                    }
                    break;
                case 114203431:
                    if (str.equals("longPress")) {
                        c8 = 4;
                        break;
                    }
                    break;
            }
            switch (c8) {
                case 0:
                    String str2 = (String) hashMap2.get("message");
                    if (str2 != null) {
                        a.this.f7270c.d(str2);
                        break;
                    }
                    break;
                case 1:
                    String str3 = (String) hashMap2.get("message");
                    if (str3 != null) {
                        a.this.f7270c.a(str3);
                        break;
                    }
                    break;
                case 2:
                    Integer num = (Integer) hashMap.get("nodeId");
                    if (num != null) {
                        a.this.f7270c.g(num.intValue());
                        break;
                    }
                    break;
                case 3:
                    Integer num2 = (Integer) hashMap.get("nodeId");
                    if (num2 != null) {
                        a.this.f7270c.e(num2.intValue());
                        break;
                    }
                    break;
                case 4:
                    Integer num3 = (Integer) hashMap.get("nodeId");
                    if (num3 != null) {
                        a.this.f7270c.f(num3.intValue());
                        break;
                    }
                    break;
            }
            eVar.a(null);
        }
    }

    /* loaded from: classes.dex */
    public interface b extends FlutterJNI.a {
        void a(String str);

        void d(String str);

        void e(int i8);

        void f(int i8);

        void g(int i8);
    }

    public a(a6.a aVar, FlutterJNI flutterJNI) {
        C0096a c0096a = new C0096a();
        this.f7271d = c0096a;
        i6.a aVar2 = new i6.a(aVar, "flutter/accessibility", i6.n.f7547a);
        this.f7268a = aVar2;
        aVar2.e(c0096a);
        this.f7269b = flutterJNI;
    }

    public void b(int i8, g.EnumC0108g enumC0108g) {
        this.f7269b.dispatchSemanticsAction(i8, enumC0108g);
    }

    public void c(int i8, g.EnumC0108g enumC0108g, Object obj) {
        this.f7269b.dispatchSemanticsAction(i8, enumC0108g, obj);
    }

    public void d() {
        this.f7269b.setSemanticsEnabled(false);
    }

    public void e() {
        this.f7269b.setSemanticsEnabled(true);
    }

    public void f(int i8) {
        this.f7269b.setAccessibilityFeatures(i8);
    }

    public void g(b bVar) {
        this.f7270c = bVar;
        this.f7269b.setAccessibilityDelegate(bVar);
    }
}
