package h6;

import android.view.KeyEvent;
import h6.i;
import i6.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    public final i6.a f7281a;

    /* loaded from: classes.dex */
    public interface a {
        void a(boolean z7);
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final KeyEvent f7282a;

        /* renamed from: b  reason: collision with root package name */
        public final Character f7283b;

        public b(KeyEvent keyEvent, Character ch) {
            this.f7282a = keyEvent;
            this.f7283b = ch;
        }
    }

    public i(i6.b bVar) {
        this.f7281a = new i6.a(bVar, "flutter/keyevent", i6.e.f7533a);
    }

    private static a.e b(final a aVar) {
        return new a.e() { // from class: h6.h
            @Override // i6.a.e
            public final void a(Object obj) {
                i.d(i.a.this, obj);
            }
        };
    }

    private Map c(b bVar, boolean z7) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", z7 ? "keyup" : "keydown");
        hashMap.put("keymap", "android");
        hashMap.put("flags", Integer.valueOf(bVar.f7282a.getFlags()));
        hashMap.put("plainCodePoint", Integer.valueOf(bVar.f7282a.getUnicodeChar(0)));
        hashMap.put("codePoint", Integer.valueOf(bVar.f7282a.getUnicodeChar()));
        hashMap.put("keyCode", Integer.valueOf(bVar.f7282a.getKeyCode()));
        hashMap.put("scanCode", Integer.valueOf(bVar.f7282a.getScanCode()));
        hashMap.put("metaState", Integer.valueOf(bVar.f7282a.getMetaState()));
        Character ch = bVar.f7283b;
        if (ch != null) {
            hashMap.put("character", ch.toString());
        }
        hashMap.put("source", Integer.valueOf(bVar.f7282a.getSource()));
        hashMap.put("deviceId", Integer.valueOf(bVar.f7282a.getDeviceId()));
        hashMap.put("repeatCount", Integer.valueOf(bVar.f7282a.getRepeatCount()));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(a aVar, Object obj) {
        boolean z7 = false;
        if (obj != null) {
            try {
                z7 = ((JSONObject) obj).getBoolean("handled");
            } catch (JSONException e8) {
                z5.b.b("KeyEventChannel", "Unable to unpack JSON message: " + e8);
            }
        }
        aVar.a(z7);
    }

    public void e(b bVar, boolean z7, a aVar) {
        this.f7281a.d(c(bVar, z7), b(aVar));
    }
}
