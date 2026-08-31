package androidx.loader.app;

import androidx.lifecycle.m;
import androidx.lifecycle.o0;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes.dex */
public abstract class a {
    public static a b(m mVar) {
        return new b(mVar, ((o0) mVar).getViewModelStore());
    }

    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract void c();
}
