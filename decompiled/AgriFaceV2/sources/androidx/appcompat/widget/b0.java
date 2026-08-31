package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
/* loaded from: classes.dex */
final class b0 {

    /* renamed from: a  reason: collision with root package name */
    private TextView f2214a;

    /* renamed from: b  reason: collision with root package name */
    private TextClassifier f2215b;

    /* loaded from: classes.dex */
    private static final class a {
        static TextClassifier a(TextView textView) {
            TextClassificationManager textClassificationManager = (TextClassificationManager) textView.getContext().getSystemService(TextClassificationManager.class);
            return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0(TextView textView) {
        this.f2214a = (TextView) g1.e.j(textView);
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.f2215b;
        return textClassifier == null ? a.a(this.f2214a) : textClassifier;
    }

    public void b(TextClassifier textClassifier) {
        this.f2215b = textClassifier;
    }
}
