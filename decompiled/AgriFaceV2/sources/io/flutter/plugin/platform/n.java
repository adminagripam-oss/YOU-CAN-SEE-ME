package io.flutter.plugin.platform;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
class n implements m {

    /* renamed from: a  reason: collision with root package name */
    private final Map f7982a = new HashMap();

    @Override // io.flutter.plugin.platform.m
    public boolean a(String str, l lVar) {
        if (this.f7982a.containsKey(str)) {
            return false;
        }
        this.f7982a.put(str, lVar);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l b(String str) {
        return (l) this.f7982a.get(str);
    }
}
