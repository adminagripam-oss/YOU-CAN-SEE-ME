package g6;

import io.flutter.plugins.GeneratedPluginRegistrant;
import z5.b;
/* loaded from: classes.dex */
public abstract class a {
    public static void a(io.flutter.embedding.engine.a aVar) {
        try {
            GeneratedPluginRegistrant.class.getDeclaredMethod("registerWith", io.flutter.embedding.engine.a.class).invoke(null, aVar);
        } catch (Exception e8) {
            b.b("GeneratedPluginsRegister", "Tried to automatically register plugins with FlutterEngine (" + aVar + ") but could not find or invoke the GeneratedPluginRegistrant.");
            b.c("GeneratedPluginsRegister", "Received exception while registering", e8);
        }
    }
}
