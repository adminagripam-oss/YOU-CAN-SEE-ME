package p7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
/* loaded from: classes.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public static final l f10159a = new l();

    private l() {
    }

    private final Object a(String str, ClassLoader classLoader, Class cls) {
        Class<?> cls2 = Class.forName(str, false, classLoader);
        if (cls.isAssignableFrom(cls2)) {
            return cls.cast(cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        }
        throw new IllegalArgumentException(("Expected service of class " + cls + ", but found " + cls2).toString());
    }

    private final List b(Class cls, ClassLoader classLoader) {
        List v7;
        try {
            return d(cls, classLoader);
        } catch (Throwable unused) {
            v7 = s6.v.v(ServiceLoader.load(cls, classLoader));
            return v7;
        }
    }

    private final List e(URL url) {
        boolean i8;
        String z7;
        String E;
        String z8;
        String url2 = url.toString();
        i8 = j7.l.i(url2, "jar", false, 2, null);
        if (!i8) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            try {
                List f8 = f10159a.f(bufferedReader);
                a7.a.a(bufferedReader, null);
                return f8;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    a7.a.a(bufferedReader, th);
                    throw th2;
                }
            }
        }
        z7 = j7.m.z(url2, "jar:file:", null, 2, null);
        E = j7.m.E(z7, '!', null, 2, null);
        z8 = j7.m.z(url2, "!/", null, 2, null);
        JarFile jarFile = new JarFile(E, false);
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(jarFile.getInputStream(new ZipEntry(z8)), "UTF-8"));
            List f9 = f10159a.f(bufferedReader2);
            a7.a.a(bufferedReader2, null);
            jarFile.close();
            return f9;
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                try {
                    jarFile.close();
                    throw th4;
                } catch (Throwable th5) {
                    r6.b.a(th3, th5);
                    throw th3;
                }
            }
        }
    }

    private final List f(BufferedReader bufferedReader) {
        List v7;
        String F;
        CharSequence G;
        boolean z7;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                v7 = s6.v.v(linkedHashSet);
                return v7;
            }
            F = j7.m.F(readLine, "#", null, 2, null);
            G = j7.m.G(F);
            String obj = G.toString();
            int i8 = 0;
            while (true) {
                if (i8 >= obj.length()) {
                    z7 = true;
                    break;
                }
                char charAt = obj.charAt(i8);
                if (!(charAt == '.' || Character.isJavaIdentifierPart(charAt))) {
                    z7 = false;
                    break;
                }
                i8++;
            }
            if (!z7) {
                throw new IllegalArgumentException(("Illegal service provider class name: " + obj).toString());
            }
            if (obj.length() > 0) {
                linkedHashSet.add(obj);
            }
        }
    }

    public final List c() {
        v vVar;
        if (m.a()) {
            try {
                ArrayList arrayList = new ArrayList(2);
                v vVar2 = null;
                try {
                    vVar = (v) v.class.cast(Class.forName("l7.a", true, v.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (ClassNotFoundException unused) {
                    vVar = null;
                }
                if (vVar != null) {
                    arrayList.add(vVar);
                }
                try {
                    vVar2 = (v) v.class.cast(Class.forName("kotlinx.coroutines.test.internal.TestMainDispatcherFactory", true, v.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (ClassNotFoundException unused2) {
                }
                if (vVar2 != null) {
                    arrayList.add(vVar2);
                    return arrayList;
                }
                return arrayList;
            } catch (Throwable unused3) {
                return b(v.class, v.class.getClassLoader());
            }
        }
        return b(v.class, v.class.getClassLoader());
    }

    public final List d(Class cls, ClassLoader classLoader) {
        Set<String> y7;
        int k8;
        ArrayList<URL> list = Collections.list(classLoader.getResources("META-INF/services/" + cls.getName()));
        d7.k.d(list, "list(this)");
        ArrayList arrayList = new ArrayList();
        for (URL url : list) {
            s6.s.l(arrayList, f10159a.e(url));
        }
        y7 = s6.v.y(arrayList);
        if (!y7.isEmpty()) {
            k8 = s6.o.k(y7, 10);
            ArrayList arrayList2 = new ArrayList(k8);
            for (String str : y7) {
                arrayList2.add(f10159a.a(str, classLoader, cls));
            }
            return arrayList2;
        }
        throw new IllegalArgumentException("No providers were loaded with FastServiceLoader".toString());
    }
}
