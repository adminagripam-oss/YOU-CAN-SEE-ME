package io.flutter.plugin.editing;

import io.flutter.embedding.engine.FlutterJNI;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f7855a;

    public a(FlutterJNI flutterJNI) {
        this.f7855a = flutterJNI;
    }

    public int a(CharSequence charSequence, int i8) {
        int length = charSequence.length();
        int i9 = length - 1;
        if (i8 >= i9) {
            return length;
        }
        int codePointAt = Character.codePointAt(charSequence, i8);
        int charCount = Character.charCount(codePointAt);
        int i10 = i8 + charCount;
        int i11 = 0;
        if (i10 == 0) {
            return 0;
        }
        if (codePointAt == 10) {
            if (Character.codePointAt(charSequence, i10) == 13) {
                charCount++;
            }
            return i8 + charCount;
        } else if (g(codePointAt)) {
            if (i10 >= i9 || !g(Character.codePointAt(charSequence, i10))) {
                return i10;
            }
            int i12 = i8;
            while (i12 > 0 && g(Character.codePointBefore(charSequence, i8))) {
                i12 -= Character.charCount(Character.codePointBefore(charSequence, i8));
                i11++;
            }
            if (i11 % 2 == 0) {
                charCount += 2;
            }
            return i8 + charCount;
        } else {
            if (f(codePointAt)) {
                charCount += Character.charCount(codePointAt);
            }
            if (codePointAt == 8419) {
                int codePointBefore = Character.codePointBefore(charSequence, i10);
                int charCount2 = i10 + Character.charCount(codePointBefore);
                if (charCount2 < length && i(codePointBefore)) {
                    int codePointAt2 = Character.codePointAt(charSequence, charCount2);
                    if (f(codePointAt2)) {
                        charCount += Character.charCount(codePointBefore) + Character.charCount(codePointAt2);
                    }
                } else if (f(codePointBefore)) {
                    charCount += Character.charCount(codePointBefore);
                }
                return i8 + charCount;
            }
            if (c(codePointAt)) {
                boolean z7 = false;
                int i13 = 0;
                do {
                    if (z7) {
                        charCount += Character.charCount(codePointAt) + i13 + 1;
                        z7 = false;
                    }
                    if (d(codePointAt)) {
                        break;
                    }
                    if (i10 < length) {
                        codePointAt = Character.codePointAt(charSequence, i10);
                        i10 += Character.charCount(codePointAt);
                        if (codePointAt != 8419) {
                            if (!d(codePointAt) && !i(codePointAt)) {
                                if (codePointAt == 8205) {
                                    codePointAt = Character.codePointAt(charSequence, i10);
                                    i10 += Character.charCount(codePointAt);
                                    if (i10 >= length || !i(codePointAt)) {
                                        i13 = 0;
                                    } else {
                                        codePointAt = Character.codePointAt(charSequence, i10);
                                        int charCount3 = Character.charCount(codePointAt);
                                        i10 += Character.charCount(codePointAt);
                                        i13 = charCount3;
                                    }
                                    z7 = true;
                                    if (i10 < length || !z7) {
                                        break;
                                        break;
                                    }
                                }
                            } else {
                                charCount += Character.charCount(codePointAt) + 0;
                                break;
                            }
                        } else {
                            int codePointBefore2 = Character.codePointBefore(charSequence, i10);
                            int charCount4 = i10 + Character.charCount(codePointBefore2);
                            if (charCount4 < length && i(codePointBefore2)) {
                                int codePointAt3 = Character.codePointAt(charSequence, charCount4);
                                if (f(codePointAt3)) {
                                    charCount += Character.charCount(codePointBefore2) + Character.charCount(codePointAt3);
                                }
                            } else if (f(codePointBefore2)) {
                                charCount += Character.charCount(codePointBefore2);
                            }
                            return i8 + charCount;
                        }
                    }
                    i13 = 0;
                    if (i10 < length) {
                        break;
                    }
                } while (c(codePointAt));
            }
            return i8 + charCount;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x014d A[EDGE_INSN: B:101:0x014d->B:95:0x014d ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int b(java.lang.CharSequence r9, int r10) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugin.editing.a.b(java.lang.CharSequence, int):int");
    }

    public boolean c(int i8) {
        return this.f7855a.isCodePointEmoji(i8);
    }

    public boolean d(int i8) {
        return this.f7855a.isCodePointEmojiModifier(i8);
    }

    public boolean e(int i8) {
        return this.f7855a.isCodePointEmojiModifierBase(i8);
    }

    public boolean f(int i8) {
        return (48 <= i8 && i8 <= 57) || i8 == 35 || i8 == 42;
    }

    public boolean g(int i8) {
        return this.f7855a.isCodePointRegionalIndicator(i8);
    }

    public boolean h(int i8) {
        return 917536 <= i8 && i8 <= 917630;
    }

    public boolean i(int i8) {
        return this.f7855a.isCodePointVariantSelector(i8);
    }
}
