package k6;

import android.view.PointerIcon;
import h6.m;
import java.util.HashMap;
/* loaded from: classes.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static HashMap f8710c;

    /* renamed from: a  reason: collision with root package name */
    private final c f8711a;

    /* renamed from: b  reason: collision with root package name */
    private final m f8712b;

    /* renamed from: k6.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0116a implements m.b {
        C0116a() {
        }

        @Override // h6.m.b
        public void a(String str) {
            a.this.f8711a.setPointerIcon(a.this.d(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends HashMap {
        b() {
            put("alias", 1010);
            put("allScroll", 1013);
            put("basic", 1000);
            put("cell", 1006);
            put("click", 1002);
            put("contextMenu", 1001);
            put("copy", 1011);
            put("forbidden", 1012);
            put("grab", 1020);
            put("grabbing", 1021);
            put("help", 1003);
            put("move", 1013);
            put("none", 0);
            put("noDrop", 1012);
            put("precise", 1007);
            put("text", 1008);
            put("resizeColumn", 1014);
            put("resizeDown", 1015);
            put("resizeUpLeft", 1016);
            put("resizeDownRight", 1017);
            put("resizeLeft", 1014);
            put("resizeLeftRight", 1014);
            put("resizeRight", 1014);
            put("resizeRow", 1015);
            put("resizeUp", 1015);
            put("resizeUpDown", 1015);
            put("resizeUpLeft", 1017);
            put("resizeUpRight", 1016);
            put("resizeUpLeftDownRight", 1017);
            put("resizeUpRightDownLeft", 1016);
            put("verticalText", 1009);
            put("wait", 1004);
            put("zoomIn", 1018);
            put("zoomOut", 1019);
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        PointerIcon b(int i8);

        void setPointerIcon(PointerIcon pointerIcon);
    }

    public a(c cVar, m mVar) {
        this.f8711a = cVar;
        this.f8712b = mVar;
        mVar.b(new C0116a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointerIcon d(String str) {
        if (f8710c == null) {
            f8710c = new b();
        }
        return this.f8711a.b(((Integer) f8710c.getOrDefault(str, 1000)).intValue());
    }

    public void c() {
        this.f8712b.b(null);
    }
}
