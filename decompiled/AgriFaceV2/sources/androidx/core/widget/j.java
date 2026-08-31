package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.i0;
/* loaded from: classes.dex */
public final class j implements i0 {
    private static CharSequence b(Context context, ClipData.Item item, int i8) {
        if ((i8 & 1) != 0) {
            CharSequence coerceToText = item.coerceToText(context);
            return coerceToText instanceof Spanned ? coerceToText.toString() : coerceToText;
        }
        return item.coerceToStyledText(context);
    }

    private static void c(Editable editable, CharSequence charSequence) {
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        int max = Math.max(0, Math.min(selectionStart, selectionEnd));
        int max2 = Math.max(0, Math.max(selectionStart, selectionEnd));
        Selection.setSelection(editable, max2);
        editable.replace(max, max2, charSequence);
    }

    @Override // androidx.core.view.i0
    public androidx.core.view.d a(View view, androidx.core.view.d dVar) {
        if (Log.isLoggable("ReceiveContent", 3)) {
            Log.d("ReceiveContent", "onReceive: " + dVar);
        }
        if (dVar.d() == 2) {
            return dVar;
        }
        ClipData b8 = dVar.b();
        int c8 = dVar.c();
        TextView textView = (TextView) view;
        Editable editable = (Editable) textView.getText();
        Context context = textView.getContext();
        boolean z7 = false;
        for (int i8 = 0; i8 < b8.getItemCount(); i8++) {
            CharSequence b9 = b(context, b8.getItemAt(i8), c8);
            if (b9 != null) {
                if (z7) {
                    editable.insert(Selection.getSelectionEnd(editable), "\n");
                    editable.insert(Selection.getSelectionEnd(editable), b9);
                } else {
                    c(editable, b9);
                    z7 = true;
                }
            }
        }
        return null;
    }
}
