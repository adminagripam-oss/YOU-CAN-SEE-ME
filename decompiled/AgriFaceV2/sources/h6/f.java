package h6;

import android.window.BackEvent;
import i6.j;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7273a;

    /* renamed from: b  reason: collision with root package name */
    private final j.c f7274b;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            dVar.success(null);
        }
    }

    public f(a6.a aVar) {
        a aVar2 = new a();
        this.f7274b = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/backgesture", i6.o.f7550b);
        this.f7273a = jVar;
        jVar.e(aVar2);
    }

    private Map a(BackEvent backEvent) {
        float touchX;
        float touchY;
        float progress;
        int swipeEdge;
        HashMap hashMap = new HashMap(3);
        touchX = backEvent.getTouchX();
        touchY = backEvent.getTouchY();
        hashMap.put("touchOffset", (Float.isNaN(touchX) || Float.isNaN(touchY)) ? null : Arrays.asList(Float.valueOf(touchX), Float.valueOf(touchY)));
        progress = backEvent.getProgress();
        hashMap.put("progress", Float.valueOf(progress));
        swipeEdge = backEvent.getSwipeEdge();
        hashMap.put("swipeEdge", Integer.valueOf(swipeEdge));
        return hashMap;
    }

    public void b() {
        z5.b.f("BackGestureChannel", "Sending message to cancel back gesture");
        this.f7273a.c("cancelBackGesture", null);
    }

    public void c() {
        z5.b.f("BackGestureChannel", "Sending message to commit back gesture");
        this.f7273a.c("commitBackGesture", null);
    }

    public void d(BackEvent backEvent) {
        z5.b.f("BackGestureChannel", "Sending message to start back gesture");
        this.f7273a.c("startBackGesture", a(backEvent));
    }

    public void e(BackEvent backEvent) {
        z5.b.f("BackGestureChannel", "Sending message to update back gesture progress");
        this.f7273a.c("updateBackGestureProgress", a(backEvent));
    }
}
