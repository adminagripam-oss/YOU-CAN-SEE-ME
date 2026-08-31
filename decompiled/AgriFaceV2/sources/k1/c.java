package k1;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import androidx.core.view.d;
import androidx.core.view.p0;
import g1.e;
/* loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends InputConnectionWrapper {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ b f8312a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(InputConnection inputConnection, boolean z7, b bVar) {
            super(inputConnection, z7);
            this.f8312a = bVar;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean commitContent(InputContentInfo inputContentInfo, int i8, Bundle bundle) {
            if (this.f8312a.a(d.f(inputContentInfo), i8, bundle)) {
                return true;
            }
            return super.commitContent(inputContentInfo, i8, bundle);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        boolean a(d dVar, int i8, Bundle bundle);
    }

    private static b b(final View view) {
        e.j(view);
        return new b() { // from class: k1.b
            @Override // k1.c.b
            public final boolean a(d dVar, int i8, Bundle bundle) {
                boolean e8;
                e8 = c.e(view, dVar, i8, bundle);
                return e8;
            }
        };
    }

    public static InputConnection c(View view, InputConnection inputConnection, EditorInfo editorInfo) {
        return d(inputConnection, editorInfo, b(view));
    }

    public static InputConnection d(InputConnection inputConnection, EditorInfo editorInfo, b bVar) {
        g1.c.d(inputConnection, "inputConnection must be non-null");
        g1.c.d(editorInfo, "editorInfo must be non-null");
        g1.c.d(bVar, "onCommitContentListener must be non-null");
        return new a(inputConnection, false, bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean e(View view, d dVar, int i8, Bundle bundle) {
        if ((i8 & 1) != 0) {
            try {
                dVar.d();
                Parcelable parcelable = (Parcelable) dVar.e();
                bundle = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable);
            } catch (Exception e8) {
                Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e8);
                return false;
            }
        }
        return p0.H(view, new d.a(new ClipData(dVar.b(), new ClipData.Item(dVar.a())), 2).d(dVar.c()).b(bundle).a()) == null;
    }
}
