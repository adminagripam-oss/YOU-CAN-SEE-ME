package androidx.appcompat.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class t0 extends Resources {

    /* renamed from: a  reason: collision with root package name */
    private final Resources f2486a;

    public t0(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f2486a = resources;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Drawable a(int i8) {
        return super.getDrawable(i8);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int i8) {
        return this.f2486a.getAnimation(i8);
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int i8) {
        return this.f2486a.getBoolean(i8);
    }

    @Override // android.content.res.Resources
    public int getColor(int i8) {
        return this.f2486a.getColor(i8);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i8) {
        return this.f2486a.getColorStateList(i8);
    }

    @Override // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.f2486a.getConfiguration();
    }

    @Override // android.content.res.Resources
    public float getDimension(int i8) {
        return this.f2486a.getDimension(i8);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int i8) {
        return this.f2486a.getDimensionPixelOffset(i8);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int i8) {
        return this.f2486a.getDimensionPixelSize(i8);
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.f2486a.getDisplayMetrics();
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i8, Resources.Theme theme) {
        return androidx.core.content.res.f.d(this.f2486a, i8, theme);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i8, int i9) {
        return androidx.core.content.res.f.e(this.f2486a, i8, i9, null);
    }

    @Override // android.content.res.Resources
    public float getFraction(int i8, int i9, int i10) {
        return this.f2486a.getFraction(i8, i9, i10);
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return this.f2486a.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int i8) {
        return this.f2486a.getIntArray(i8);
    }

    @Override // android.content.res.Resources
    public int getInteger(int i8) {
        return this.f2486a.getInteger(i8);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i8) {
        return this.f2486a.getLayout(i8);
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int i8) {
        return this.f2486a.getMovie(i8);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i8, int i9) {
        return this.f2486a.getQuantityString(i8, i9);
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int i8, int i9) {
        return this.f2486a.getQuantityText(i8, i9);
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int i8) {
        return this.f2486a.getResourceEntryName(i8);
    }

    @Override // android.content.res.Resources
    public String getResourceName(int i8) {
        return this.f2486a.getResourceName(i8);
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int i8) {
        return this.f2486a.getResourcePackageName(i8);
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int i8) {
        return this.f2486a.getResourceTypeName(i8);
    }

    @Override // android.content.res.Resources
    public String getString(int i8) {
        return this.f2486a.getString(i8);
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int i8) {
        return this.f2486a.getStringArray(i8);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i8) {
        return this.f2486a.getText(i8);
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int i8) {
        return this.f2486a.getTextArray(i8);
    }

    @Override // android.content.res.Resources
    public void getValue(int i8, TypedValue typedValue, boolean z7) {
        this.f2486a.getValue(i8, typedValue, z7);
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int i8, int i9, TypedValue typedValue, boolean z7) {
        j.a.a(this.f2486a, i8, i9, typedValue, z7);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int i8) {
        return this.f2486a.getXml(i8);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.f2486a.obtainAttributes(attributeSet, iArr);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int i8) {
        return this.f2486a.obtainTypedArray(i8);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i8) {
        return this.f2486a.openRawResource(i8);
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i8) {
        return this.f2486a.openRawResourceFd(i8);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) {
        this.f2486a.parseBundleExtra(str, attributeSet, bundle);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) {
        this.f2486a.parseBundleExtras(xmlResourceParser, bundle);
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.f2486a;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i8, int i9, Resources.Theme theme) {
        return androidx.core.content.res.f.e(this.f2486a, i8, i9, theme);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i8, int i9, Object... objArr) {
        return this.f2486a.getQuantityString(i8, i9, objArr);
    }

    @Override // android.content.res.Resources
    public String getString(int i8, Object... objArr) {
        return this.f2486a.getString(i8, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i8, CharSequence charSequence) {
        return this.f2486a.getText(i8, charSequence);
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z7) {
        this.f2486a.getValue(str, typedValue, z7);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i8, TypedValue typedValue) {
        return this.f2486a.openRawResource(i8, typedValue);
    }
}
