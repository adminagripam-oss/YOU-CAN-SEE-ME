package io.flutter.plugin.platform;

import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private io.flutter.view.g f7932a;

    public boolean a(MotionEvent motionEvent, boolean z7) {
        io.flutter.view.g gVar = this.f7932a;
        if (gVar == null) {
            return false;
        }
        return gVar.M(motionEvent, z7);
    }

    public boolean b(View view, View view2, AccessibilityEvent accessibilityEvent) {
        io.flutter.view.g gVar = this.f7932a;
        if (gVar == null) {
            return false;
        }
        return gVar.w(view, view2, accessibilityEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(io.flutter.view.g gVar) {
        this.f7932a = gVar;
    }
}
