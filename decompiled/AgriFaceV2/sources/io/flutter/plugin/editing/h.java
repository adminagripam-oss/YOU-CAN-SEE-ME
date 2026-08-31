package io.flutter.plugin.editing;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.InputMethodManager;
import h6.w;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.plugin.editing.i;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
/* loaded from: classes.dex */
public class h extends BaseInputConnection implements i.b {

    /* renamed from: a  reason: collision with root package name */
    private final View f7856a;

    /* renamed from: b  reason: collision with root package name */
    private final int f7857b;

    /* renamed from: c  reason: collision with root package name */
    private final w f7858c;

    /* renamed from: d  reason: collision with root package name */
    private final i f7859d;

    /* renamed from: e  reason: collision with root package name */
    private final EditorInfo f7860e;

    /* renamed from: f  reason: collision with root package name */
    private ExtractedTextRequest f7861f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f7862g;

    /* renamed from: h  reason: collision with root package name */
    private CursorAnchorInfo.Builder f7863h;

    /* renamed from: i  reason: collision with root package name */
    private ExtractedText f7864i;

    /* renamed from: j  reason: collision with root package name */
    private InputMethodManager f7865j;

    /* renamed from: k  reason: collision with root package name */
    private final Layout f7866k;

    /* renamed from: l  reason: collision with root package name */
    private io.flutter.plugin.editing.a f7867l;

    /* renamed from: m  reason: collision with root package name */
    private final a f7868m;

    /* renamed from: n  reason: collision with root package name */
    private int f7869n;

    /* loaded from: classes.dex */
    public interface a {
        boolean b(KeyEvent keyEvent);
    }

    public h(View view, int i8, w wVar, a aVar, i iVar, EditorInfo editorInfo) {
        this(view, i8, wVar, aVar, iVar, editorInfo, new FlutterJNI());
    }

    private boolean b(int i8) {
        if (i8 == 16908319) {
            setSelection(0, this.f7859d.length());
            return true;
        } else if (i8 == 16908320) {
            int selectionStart = Selection.getSelectionStart(this.f7859d);
            int selectionEnd = Selection.getSelectionEnd(this.f7859d);
            if (selectionStart != selectionEnd) {
                int min = Math.min(selectionStart, selectionEnd);
                int max = Math.max(selectionStart, selectionEnd);
                ((ClipboardManager) this.f7856a.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", this.f7859d.subSequence(min, max)));
                this.f7859d.delete(min, max);
                setSelection(min, min);
            }
            return true;
        } else if (i8 == 16908321) {
            int selectionStart2 = Selection.getSelectionStart(this.f7859d);
            int selectionEnd2 = Selection.getSelectionEnd(this.f7859d);
            if (selectionStart2 != selectionEnd2) {
                ((ClipboardManager) this.f7856a.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", this.f7859d.subSequence(Math.min(selectionStart2, selectionEnd2), Math.max(selectionStart2, selectionEnd2))));
            }
            return true;
        } else if (i8 == 16908322) {
            ClipData primaryClip = ((ClipboardManager) this.f7856a.getContext().getSystemService("clipboard")).getPrimaryClip();
            if (primaryClip != null) {
                CharSequence coerceToText = primaryClip.getItemAt(0).coerceToText(this.f7856a.getContext());
                int max2 = Math.max(0, Selection.getSelectionStart(this.f7859d));
                int max3 = Math.max(0, Selection.getSelectionEnd(this.f7859d));
                int min2 = Math.min(max2, max3);
                int max4 = Math.max(max2, max3);
                if (min2 != max4) {
                    this.f7859d.delete(min2, max4);
                }
                this.f7859d.insert(min2, coerceToText);
                int length = min2 + coerceToText.length();
                setSelection(length, length);
            }
            return true;
        } else {
            return false;
        }
    }

    private CursorAnchorInfo c() {
        CursorAnchorInfo.Builder builder = this.f7863h;
        if (builder == null) {
            this.f7863h = new CursorAnchorInfo.Builder();
        } else {
            builder.reset();
        }
        this.f7863h.setSelectionRange(this.f7859d.i(), this.f7859d.h());
        int g8 = this.f7859d.g();
        int f8 = this.f7859d.f();
        if (g8 < 0 || f8 <= g8) {
            this.f7863h.setComposingText(-1, "");
        } else {
            this.f7863h.setComposingText(g8, this.f7859d.toString().subSequence(g8, f8));
        }
        return this.f7863h.build();
    }

    private ExtractedText d(ExtractedTextRequest extractedTextRequest) {
        ExtractedText extractedText = this.f7864i;
        extractedText.startOffset = 0;
        extractedText.partialStartOffset = -1;
        extractedText.partialEndOffset = -1;
        extractedText.selectionStart = this.f7859d.i();
        this.f7864i.selectionEnd = this.f7859d.h();
        this.f7864i.text = (extractedTextRequest == null || (extractedTextRequest.flags & 1) == 0) ? this.f7859d.toString() : this.f7859d;
        return this.f7864i;
    }

    private boolean e(boolean z7, boolean z8) {
        int selectionStart = Selection.getSelectionStart(this.f7859d);
        int selectionEnd = Selection.getSelectionEnd(this.f7859d);
        boolean z9 = false;
        if (selectionStart < 0 || selectionEnd < 0) {
            return false;
        }
        int max = z7 ? Math.max(this.f7867l.b(this.f7859d, selectionEnd), 0) : Math.min(this.f7867l.a(this.f7859d, selectionEnd), this.f7859d.length());
        if (selectionStart == selectionEnd && !z8) {
            z9 = true;
        }
        if (z9) {
            setSelection(max, max);
        } else {
            setSelection(selectionStart, max);
        }
        return true;
    }

    private boolean g(boolean z7, boolean z8) {
        int selectionStart = Selection.getSelectionStart(this.f7859d);
        int selectionEnd = Selection.getSelectionEnd(this.f7859d);
        boolean z9 = false;
        if (selectionStart < 0 || selectionEnd < 0) {
            return false;
        }
        if (selectionStart == selectionEnd && !z8) {
            z9 = true;
        }
        beginBatchEdit();
        if (z9) {
            if (z7) {
                Selection.moveUp(this.f7859d, this.f7866k);
            } else {
                Selection.moveDown(this.f7859d, this.f7866k);
            }
            int selectionStart2 = Selection.getSelectionStart(this.f7859d);
            setSelection(selectionStart2, selectionStart2);
        } else {
            if (z7) {
                Selection.extendUp(this.f7859d, this.f7866k);
            } else {
                Selection.extendDown(this.f7859d, this.f7866k);
            }
            setSelection(Selection.getSelectionStart(this.f7859d), Selection.getSelectionEnd(this.f7859d));
        }
        endBatchEdit();
        return true;
    }

    private byte[] h(InputStream inputStream, int i8) {
        int i9;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[i8];
        while (true) {
            try {
                i9 = inputStream.read(bArr);
            } catch (IOException unused) {
                i9 = -1;
            }
            if (i9 == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i9);
        }
    }

    @Override // io.flutter.plugin.editing.i.b
    public void a(boolean z7, boolean z8, boolean z9) {
        this.f7865j.updateSelection(this.f7856a, this.f7859d.i(), this.f7859d.h(), this.f7859d.g(), this.f7859d.f());
        ExtractedTextRequest extractedTextRequest = this.f7861f;
        if (extractedTextRequest != null) {
            this.f7865j.updateExtractedText(this.f7856a, extractedTextRequest.token, d(extractedTextRequest));
        }
        if (this.f7862g) {
            this.f7865j.updateCursorAnchorInfo(this.f7856a, c());
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        this.f7859d.b();
        this.f7869n++;
        return super.beginBatchEdit();
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int i8) {
        return super.clearMetaKeyStates(i8);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public void closeConnection() {
        super.closeConnection();
        this.f7859d.l(this);
        while (this.f7869n > 0) {
            endBatchEdit();
            this.f7869n--;
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitContent(InputContentInfo inputContentInfo, int i8, Bundle bundle) {
        if ((i8 & 1) != 0) {
            try {
                inputContentInfo.requestPermission();
                if (inputContentInfo.getDescription().getMimeTypeCount() > 0) {
                    inputContentInfo.requestPermission();
                    Uri contentUri = inputContentInfo.getContentUri();
                    String mimeType = inputContentInfo.getDescription().getMimeType(0);
                    Context context = this.f7856a.getContext();
                    if (contentUri != null) {
                        try {
                            InputStream openInputStream = context.getContentResolver().openInputStream(contentUri);
                            if (openInputStream != null) {
                                byte[] h8 = h(openInputStream, 65536);
                                HashMap hashMap = new HashMap();
                                hashMap.put("mimeType", mimeType);
                                hashMap.put("data", h8);
                                hashMap.put("uri", contentUri.toString());
                                this.f7858c.b(this.f7857b, hashMap);
                                inputContentInfo.releasePermission();
                                return true;
                            }
                        } catch (FileNotFoundException unused) {
                            inputContentInfo.releasePermission();
                            return false;
                        }
                    }
                    inputContentInfo.releasePermission();
                }
            } catch (Exception unused2) {
            }
        }
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i8) {
        return super.commitText(charSequence, i8);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int i8, int i9) {
        if (this.f7859d.i() == -1) {
            return true;
        }
        return super.deleteSurroundingText(i8, i9);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int i8, int i9) {
        return super.deleteSurroundingTextInCodePoints(i8, i9);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        boolean endBatchEdit = super.endBatchEdit();
        this.f7869n--;
        this.f7859d.d();
        return endBatchEdit;
    }

    public boolean f(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 21) {
                return e(true, keyEvent.isShiftPressed());
            }
            if (keyEvent.getKeyCode() == 22) {
                return e(false, keyEvent.isShiftPressed());
            }
            if (keyEvent.getKeyCode() == 19) {
                return g(true, keyEvent.isShiftPressed());
            }
            if (keyEvent.getKeyCode() == 20) {
                return g(false, keyEvent.isShiftPressed());
            }
            if (keyEvent.getKeyCode() == 66 || keyEvent.getKeyCode() == 160) {
                EditorInfo editorInfo = this.f7860e;
                if ((editorInfo.inputType & 131072) == 0) {
                    performEditorAction(editorInfo.imeOptions & 255);
                    return true;
                }
            }
            int selectionStart = Selection.getSelectionStart(this.f7859d);
            int selectionEnd = Selection.getSelectionEnd(this.f7859d);
            int unicodeChar = keyEvent.getUnicodeChar();
            if (selectionStart >= 0 && selectionEnd >= 0 && unicodeChar != 0) {
                int min = Math.min(selectionStart, selectionEnd);
                int max = Math.max(selectionStart, selectionEnd);
                beginBatchEdit();
                if (min != max) {
                    this.f7859d.delete(min, max);
                }
                this.f7859d.insert(min, (CharSequence) String.valueOf((char) unicodeChar));
                int i8 = min + 1;
                setSelection(i8, i8);
                endBatchEdit();
                return true;
            }
        }
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        return super.finishComposingText();
    }

    @Override // android.view.inputmethod.BaseInputConnection
    public Editable getEditable() {
        return this.f7859d;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i8) {
        boolean z7 = (i8 & 1) != 0;
        if (z7 == (this.f7861f == null)) {
            StringBuilder sb = new StringBuilder();
            sb.append("The input method toggled text monitoring ");
            sb.append(z7 ? "on" : "off");
            z5.b.a("InputConnectionAdaptor", sb.toString());
        }
        this.f7861f = z7 ? extractedTextRequest : null;
        return d(extractedTextRequest);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int i8) {
        beginBatchEdit();
        boolean b8 = b(i8);
        endBatchEdit();
        return b8;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performEditorAction(int i8) {
        if (i8 == 0) {
            this.f7858c.o(this.f7857b);
        } else if (i8 == 1) {
            this.f7858c.g(this.f7857b);
        } else if (i8 == 2) {
            this.f7858c.f(this.f7857b);
        } else if (i8 == 3) {
            this.f7858c.l(this.f7857b);
        } else if (i8 == 4) {
            this.f7858c.m(this.f7857b);
        } else if (i8 == 5) {
            this.f7858c.h(this.f7857b);
        } else if (i8 != 7) {
            this.f7858c.e(this.f7857b);
        } else {
            this.f7858c.j(this.f7857b);
        }
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String str, Bundle bundle) {
        this.f7858c.i(this.f7857b, str, bundle);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int i8) {
        if ((i8 & 1) != 0) {
            this.f7865j.updateCursorAnchorInfo(this.f7856a, c());
        }
        boolean z7 = (i8 & 2) != 0;
        if (z7 != this.f7862g) {
            StringBuilder sb = new StringBuilder();
            sb.append("The input method toggled cursor monitoring ");
            sb.append(z7 ? "on" : "off");
            z5.b.a("InputConnectionAdaptor", sb.toString());
        }
        this.f7862g = z7;
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent keyEvent) {
        return this.f7868m.b(keyEvent);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int i8, int i9) {
        return super.setComposingRegion(i8, i9);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i8) {
        beginBatchEdit();
        boolean commitText = charSequence.length() == 0 ? super.commitText(charSequence, i8) : super.setComposingText(charSequence, i8);
        endBatchEdit();
        return commitText;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setSelection(int i8, int i9) {
        beginBatchEdit();
        boolean selection = super.setSelection(i8, i9);
        endBatchEdit();
        return selection;
    }

    public h(View view, int i8, w wVar, a aVar, i iVar, EditorInfo editorInfo, FlutterJNI flutterJNI) {
        super(view, true);
        this.f7862g = false;
        this.f7864i = new ExtractedText();
        this.f7869n = 0;
        this.f7856a = view;
        this.f7857b = i8;
        this.f7858c = wVar;
        this.f7859d = iVar;
        iVar.a(this);
        this.f7860e = editorInfo;
        this.f7868m = aVar;
        this.f7867l = new io.flutter.plugin.editing.a(flutterJNI);
        this.f7866k = new DynamicLayout(iVar, new TextPaint(), Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.f7865j = (InputMethodManager) view.getContext().getSystemService("input_method");
    }
}
