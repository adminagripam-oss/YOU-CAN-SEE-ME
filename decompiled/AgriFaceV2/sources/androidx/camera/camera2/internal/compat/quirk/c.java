package androidx.camera.camera2.internal.compat.quirk;

import b0.i2;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class c {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static List a(i2 i2Var) {
        ArrayList arrayList = new ArrayList();
        if (i2Var.a(ImageCapturePixelHDRPlusQuirk.class, ImageCapturePixelHDRPlusQuirk.f())) {
            arrayList.add(new ImageCapturePixelHDRPlusQuirk());
        }
        if (i2Var.a(ExtraCroppingQuirk.class, ExtraCroppingQuirk.h())) {
            arrayList.add(new ExtraCroppingQuirk());
        }
        if (i2Var.a(Nexus4AndroidLTargetAspectRatioQuirk.class, Nexus4AndroidLTargetAspectRatioQuirk.g())) {
            arrayList.add(new Nexus4AndroidLTargetAspectRatioQuirk());
        }
        if (i2Var.a(ExcludedSupportedSizesQuirk.class, ExcludedSupportedSizesQuirk.s())) {
            arrayList.add(new ExcludedSupportedSizesQuirk());
        }
        if (i2Var.a(CrashWhenTakingPhotoWithAutoFlashAEModeQuirk.class, CrashWhenTakingPhotoWithAutoFlashAEModeQuirk.f())) {
            arrayList.add(new CrashWhenTakingPhotoWithAutoFlashAEModeQuirk());
        }
        if (i2Var.a(PreviewPixelHDRnetQuirk.class, PreviewPixelHDRnetQuirk.f())) {
            arrayList.add(new PreviewPixelHDRnetQuirk());
        }
        if (i2Var.a(StillCaptureFlashStopRepeatingQuirk.class, StillCaptureFlashStopRepeatingQuirk.f())) {
            arrayList.add(new StillCaptureFlashStopRepeatingQuirk());
        }
        if (i2Var.a(ExtraSupportedSurfaceCombinationsQuirk.class, ExtraSupportedSurfaceCombinationsQuirk.k())) {
            arrayList.add(new ExtraSupportedSurfaceCombinationsQuirk());
        }
        if (i2Var.a(FlashAvailabilityBufferUnderflowQuirk.class, FlashAvailabilityBufferUnderflowQuirk.g())) {
            arrayList.add(new FlashAvailabilityBufferUnderflowQuirk());
        }
        if (i2Var.a(RepeatingStreamConstraintForVideoRecordingQuirk.class, RepeatingStreamConstraintForVideoRecordingQuirk.g())) {
            arrayList.add(new RepeatingStreamConstraintForVideoRecordingQuirk());
        }
        if (i2Var.a(TextureViewIsClosedQuirk.class, TextureViewIsClosedQuirk.f())) {
            arrayList.add(new TextureViewIsClosedQuirk());
        }
        if (i2Var.a(CaptureSessionOnClosedNotCalledQuirk.class, CaptureSessionOnClosedNotCalledQuirk.f())) {
            arrayList.add(new CaptureSessionOnClosedNotCalledQuirk());
        }
        if (i2Var.a(TorchIsClosedAfterImageCapturingQuirk.class, TorchIsClosedAfterImageCapturingQuirk.f())) {
            arrayList.add(new TorchIsClosedAfterImageCapturingQuirk());
        }
        if (i2Var.a(ZslDisablerQuirk.class, ZslDisablerQuirk.i())) {
            arrayList.add(new ZslDisablerQuirk());
        }
        if (i2Var.a(ExtraSupportedOutputSizeQuirk.class, ExtraSupportedOutputSizeQuirk.i())) {
            arrayList.add(new ExtraSupportedOutputSizeQuirk());
        }
        if (i2Var.a(InvalidVideoProfilesQuirk.class, InvalidVideoProfilesQuirk.s())) {
            arrayList.add(new InvalidVideoProfilesQuirk());
        }
        if (i2Var.a(Preview3AThreadCrashQuirk.class, Preview3AThreadCrashQuirk.f())) {
            arrayList.add(new Preview3AThreadCrashQuirk());
        }
        if (i2Var.a(SmallDisplaySizeQuirk.class, SmallDisplaySizeQuirk.g())) {
            arrayList.add(new SmallDisplaySizeQuirk());
        }
        if (i2Var.a(CaptureSessionShouldUseMrirQuirk.class, CaptureSessionShouldUseMrirQuirk.f())) {
            arrayList.add(new CaptureSessionShouldUseMrirQuirk());
        }
        return arrayList;
    }
}
