package s5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import i6.j;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class b {
    public static o5.a a(Map map, Context context, j.d dVar) {
        String str = (String) map.get("type");
        if (str == null || !str.equals("bitmap")) {
            if (str != null && str.equals("file")) {
                try {
                    return o5.a.d(context, Uri.fromFile(new File((String) map.get("path"))));
                } catch (IOException e8) {
                    Log.e("ImageError", "Getting Image failed");
                    Log.e("ImageError", e8.toString());
                    dVar.error("InputImageConverterError", e8.toString(), e8);
                    return null;
                }
            } else if (str == null || !str.equals("bytes")) {
                dVar.error("InputImageConverterError", "Invalid Input Image", null);
                return null;
            } else {
                try {
                    Map map2 = (Map) map.get("metadata");
                    Object obj = map.get("bytes");
                    Objects.requireNonNull(obj);
                    byte[] bArr = (byte[]) obj;
                    Object obj2 = map2.get("image_format");
                    Objects.requireNonNull(obj2);
                    int parseInt = Integer.parseInt(obj2.toString());
                    Object obj3 = map2.get("rotation");
                    Objects.requireNonNull(obj3);
                    int parseInt2 = Integer.parseInt(obj3.toString());
                    Object obj4 = map2.get("width");
                    Objects.requireNonNull(obj4);
                    int intValue = Double.valueOf(obj4.toString()).intValue();
                    Object obj5 = map2.get("height");
                    Objects.requireNonNull(obj5);
                    int intValue2 = Double.valueOf(obj5.toString()).intValue();
                    if (parseInt != 17 && parseInt != 842094169) {
                        dVar.error("InputImageConverterError", "ImageFormat is not supported.", null);
                        return null;
                    }
                    return o5.a.b(bArr, intValue, intValue2, parseInt2, parseInt);
                } catch (Exception e9) {
                    Log.e("ImageError", "Getting Image failed");
                    Log.e("ImageError", e9.toString());
                    dVar.error("InputImageConverterError", e9.toString(), e9);
                    return null;
                }
            }
        }
        try {
            byte[] bArr2 = (byte[]) map.get("bitmapData");
            if (bArr2 == null) {
                dVar.error("InputImageConverterError", "Bitmap data is null", null);
                return null;
            }
            Object obj6 = map.get("rotation");
            int intValue3 = obj6 != null ? ((Integer) obj6).intValue() : 0;
            try {
                Map map3 = (Map) map.get("metadata");
                if (map3 != null) {
                    Object obj7 = map3.get("width");
                    Objects.requireNonNull(obj7);
                    int intValue4 = Double.valueOf(obj7.toString()).intValue();
                    Object obj8 = map3.get("height");
                    Objects.requireNonNull(obj8);
                    Bitmap createBitmap = Bitmap.createBitmap(intValue4, Double.valueOf(obj8.toString()).intValue(), Bitmap.Config.ARGB_8888);
                    IntBuffer allocate = IntBuffer.allocate(bArr2.length / 4);
                    for (int i8 = 0; i8 < bArr2.length; i8 += 4) {
                        allocate.put(((bArr2[i8] & 255) << 16) | ((bArr2[i8 + 3] & 255) << 24) | ((bArr2[i8 + 1] & 255) << 8) | (bArr2[i8 + 2] & 255));
                    }
                    allocate.rewind();
                    createBitmap.copyPixelsFromBuffer(allocate);
                    return o5.a.a(createBitmap, intValue3);
                }
            } catch (Exception e10) {
                Log.e("ImageError", "Error creating bitmap from raw data", e10);
            }
            try {
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length);
                if (decodeByteArray == null) {
                    dVar.error("InputImageConverterError", "Failed to decode bitmap from the provided data", null);
                    return null;
                }
                return o5.a.a(decodeByteArray, intValue3);
            } catch (Exception e11) {
                Log.e("ImageError", "Getting Bitmap failed", e11);
                dVar.error("InputImageConverterError", e11.toString(), e11);
                return null;
            }
        } catch (Exception e12) {
            Log.e("ImageError", "Getting Bitmap failed");
            Log.e("ImageError", e12.toString());
            dVar.error("InputImageConverterError", e12.toString(), e12);
            return null;
        }
    }
}
