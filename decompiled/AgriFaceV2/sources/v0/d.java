package v0;

import android.content.Context;
import android.media.AudioRecord;
/* loaded from: classes.dex */
public abstract class d {
    public static void a(AudioRecord.Builder builder, Context context) {
        builder.setContext(context);
    }
}
