package e6;

import android.content.Context;
import io.flutter.embedding.engine.d;
import io.flutter.plugin.platform.m;
import io.flutter.view.TextureRegistry;
/* loaded from: classes.dex */
public interface a {

    /* renamed from: e6.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0083a {
        String a(String str);
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Context f6613a;

        /* renamed from: b  reason: collision with root package name */
        private final io.flutter.embedding.engine.a f6614b;

        /* renamed from: c  reason: collision with root package name */
        private final i6.b f6615c;

        /* renamed from: d  reason: collision with root package name */
        private final TextureRegistry f6616d;

        /* renamed from: e  reason: collision with root package name */
        private final m f6617e;

        /* renamed from: f  reason: collision with root package name */
        private final InterfaceC0083a f6618f;

        /* renamed from: g  reason: collision with root package name */
        private final d f6619g;

        public b(Context context, io.flutter.embedding.engine.a aVar, i6.b bVar, TextureRegistry textureRegistry, m mVar, InterfaceC0083a interfaceC0083a, d dVar) {
            this.f6613a = context;
            this.f6614b = aVar;
            this.f6615c = bVar;
            this.f6616d = textureRegistry;
            this.f6617e = mVar;
            this.f6618f = interfaceC0083a;
            this.f6619g = dVar;
        }

        public Context a() {
            return this.f6613a;
        }

        public i6.b b() {
            return this.f6615c;
        }

        public InterfaceC0083a c() {
            return this.f6618f;
        }

        public m d() {
            return this.f6617e;
        }

        public TextureRegistry e() {
            return this.f6616d;
        }
    }

    void onAttachedToEngine(b bVar);

    void onDetachedFromEngine(b bVar);
}
