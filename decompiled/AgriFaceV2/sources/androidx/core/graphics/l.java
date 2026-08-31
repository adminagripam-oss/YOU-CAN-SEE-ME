package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import androidx.core.content.res.d;
import f1.g;
import java.io.IOException;
/* loaded from: classes.dex */
public class l extends m {
    private Font g(FontFamily fontFamily, int i8) {
        FontStyle fontStyle = new FontStyle((i8 & 1) != 0 ? 700 : 400, (i8 & 2) != 0 ? 1 : 0);
        Font font = fontFamily.getFont(0);
        int h8 = h(fontStyle, font.getStyle());
        for (int i9 = 1; i9 < fontFamily.getSize(); i9++) {
            Font font2 = fontFamily.getFont(i9);
            int h9 = h(fontStyle, font2.getStyle());
            if (h9 < h8) {
                font = font2;
                h8 = h9;
            }
        }
        return font;
    }

    private static int h(FontStyle fontStyle, FontStyle fontStyle2) {
        return (Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100) + (fontStyle.getSlant() == fontStyle2.getSlant() ? 0 : 2);
    }

    @Override // androidx.core.graphics.m
    public Typeface a(Context context, d.c cVar, Resources resources, int i8) {
        d.C0023d[] a8;
        try {
            FontFamily.Builder builder = null;
            for (d.C0023d c0023d : cVar.a()) {
                try {
                    Font build = new Font.Builder(resources, c0023d.b()).setWeight(c0023d.e()).setSlant(c0023d.f() ? 1 : 0).setTtcIndex(c0023d.c()).setFontVariationSettings(c0023d.d()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } catch (IOException unused) {
                }
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(g(build2, i8).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // androidx.core.graphics.m
    public Typeface b(Context context, CancellationSignal cancellationSignal, g.b[] bVarArr, int i8) {
        ParcelFileDescriptor openFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily.Builder builder = null;
            for (g.b bVar : bVarArr) {
                try {
                    openFileDescriptor = contentResolver.openFileDescriptor(bVar.d(), "r", cancellationSignal);
                } catch (IOException unused) {
                }
                if (openFileDescriptor != null) {
                    try {
                        Font build = new Font.Builder(openFileDescriptor).setWeight(bVar.e()).setSlant(bVar.f() ? 1 : 0).setTtcIndex(bVar.c()).build();
                        if (builder == null) {
                            builder = new FontFamily.Builder(build);
                        } else {
                            builder.addFont(build);
                        }
                    } catch (Throwable th) {
                        try {
                            openFileDescriptor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                        break;
                    }
                } else if (openFileDescriptor == null) {
                }
                openFileDescriptor.close();
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(g(build2, i8).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // androidx.core.graphics.m
    public Typeface c(Context context, Resources resources, int i8, String str, int i9) {
        try {
            Font build = new Font.Builder(resources, i8).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.graphics.m
    public g.b f(g.b[] bVarArr, int i8) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
