package androidx.camera.camera2.internal.compat.quirk;

import b0.i2;
import b0.j2;
import b0.l2;
import java.util.ArrayList;
import s.b0;
import y.f1;
/* loaded from: classes.dex */
public abstract class a {
    public static l2 a(String str, b0 b0Var) {
        i2 a8 = j2.b().a();
        ArrayList arrayList = new ArrayList();
        if (a8.a(AeFpsRangeLegacyQuirk.class, AeFpsRangeLegacyQuirk.h(b0Var))) {
            arrayList.add(new AeFpsRangeLegacyQuirk(b0Var));
        }
        if (a8.a(AspectRatioLegacyApi21Quirk.class, AspectRatioLegacyApi21Quirk.g(b0Var))) {
            arrayList.add(new AspectRatioLegacyApi21Quirk());
        }
        if (a8.a(JpegHalCorruptImageQuirk.class, JpegHalCorruptImageQuirk.f(b0Var))) {
            arrayList.add(new JpegHalCorruptImageQuirk());
        }
        if (a8.a(JpegCaptureDownsizingQuirk.class, JpegCaptureDownsizingQuirk.f(b0Var))) {
            arrayList.add(new JpegCaptureDownsizingQuirk());
        }
        if (a8.a(CamcorderProfileResolutionQuirk.class, CamcorderProfileResolutionQuirk.g(b0Var))) {
            arrayList.add(new CamcorderProfileResolutionQuirk(b0Var));
        }
        if (a8.a(CaptureNoResponseQuirk.class, CaptureNoResponseQuirk.f(b0Var))) {
            arrayList.add(new CaptureNoResponseQuirk());
        }
        if (a8.a(LegacyCameraOutputConfigNullPointerQuirk.class, LegacyCameraOutputConfigNullPointerQuirk.f(b0Var))) {
            arrayList.add(new LegacyCameraOutputConfigNullPointerQuirk());
        }
        if (a8.a(LegacyCameraSurfaceCleanupQuirk.class, LegacyCameraSurfaceCleanupQuirk.g(b0Var))) {
            arrayList.add(new LegacyCameraSurfaceCleanupQuirk());
        }
        if (a8.a(ImageCaptureWashedOutImageQuirk.class, ImageCaptureWashedOutImageQuirk.f(b0Var))) {
            arrayList.add(new ImageCaptureWashedOutImageQuirk());
        }
        if (a8.a(CameraNoResponseWhenEnablingFlashQuirk.class, CameraNoResponseWhenEnablingFlashQuirk.f(b0Var))) {
            arrayList.add(new CameraNoResponseWhenEnablingFlashQuirk());
        }
        if (a8.a(YuvImageOnePixelShiftQuirk.class, YuvImageOnePixelShiftQuirk.l(b0Var))) {
            arrayList.add(new YuvImageOnePixelShiftQuirk());
        }
        if (a8.a(FlashTooSlowQuirk.class, FlashTooSlowQuirk.g(b0Var))) {
            arrayList.add(new FlashTooSlowQuirk());
        }
        if (a8.a(AfRegionFlipHorizontallyQuirk.class, AfRegionFlipHorizontallyQuirk.f(b0Var))) {
            arrayList.add(new AfRegionFlipHorizontallyQuirk());
        }
        if (a8.a(ConfigureSurfaceToSecondarySessionFailQuirk.class, ConfigureSurfaceToSecondarySessionFailQuirk.f(b0Var))) {
            arrayList.add(new ConfigureSurfaceToSecondarySessionFailQuirk());
        }
        if (a8.a(PreviewOrientationIncorrectQuirk.class, PreviewOrientationIncorrectQuirk.f(b0Var))) {
            arrayList.add(new PreviewOrientationIncorrectQuirk());
        }
        if (a8.a(CaptureSessionStuckQuirk.class, CaptureSessionStuckQuirk.f(b0Var))) {
            arrayList.add(new CaptureSessionStuckQuirk());
        }
        if (a8.a(ImageCaptureFlashNotFireQuirk.class, ImageCaptureFlashNotFireQuirk.f(b0Var))) {
            arrayList.add(new ImageCaptureFlashNotFireQuirk());
        }
        if (a8.a(ImageCaptureWithFlashUnderexposureQuirk.class, ImageCaptureWithFlashUnderexposureQuirk.f(b0Var))) {
            arrayList.add(new ImageCaptureWithFlashUnderexposureQuirk());
        }
        if (a8.a(ImageCaptureFailWithAutoFlashQuirk.class, ImageCaptureFailWithAutoFlashQuirk.f(b0Var))) {
            arrayList.add(new ImageCaptureFailWithAutoFlashQuirk());
        }
        if (a8.a(IncorrectCaptureStateQuirk.class, IncorrectCaptureStateQuirk.f(b0Var))) {
            arrayList.add(new IncorrectCaptureStateQuirk());
        }
        if (a8.a(TorchFlashRequiredFor3aUpdateQuirk.class, TorchFlashRequiredFor3aUpdateQuirk.k(b0Var))) {
            arrayList.add(new TorchFlashRequiredFor3aUpdateQuirk(b0Var));
        }
        if (a8.a(PreviewStretchWhenVideoCaptureIsBoundQuirk.class, PreviewStretchWhenVideoCaptureIsBoundQuirk.l())) {
            arrayList.add(new PreviewStretchWhenVideoCaptureIsBoundQuirk());
        }
        if (a8.a(PreviewDelayWhenVideoCaptureIsBoundQuirk.class, PreviewDelayWhenVideoCaptureIsBoundQuirk.f())) {
            arrayList.add(new PreviewDelayWhenVideoCaptureIsBoundQuirk());
        }
        if (a8.a(ImageCaptureFailedWhenVideoCaptureIsBoundQuirk.class, ImageCaptureFailedWhenVideoCaptureIsBoundQuirk.m())) {
            arrayList.add(new ImageCaptureFailedWhenVideoCaptureIsBoundQuirk());
        }
        if (a8.a(TemporalNoiseQuirk.class, TemporalNoiseQuirk.g(b0Var))) {
            arrayList.add(new TemporalNoiseQuirk());
        }
        if (a8.a(ImageCaptureFailedForVideoSnapshotQuirk.class, ImageCaptureFailedForVideoSnapshotQuirk.h())) {
            arrayList.add(new ImageCaptureFailedForVideoSnapshotQuirk());
        }
        l2 l2Var = new l2(arrayList);
        f1.a("CameraQuirks", "camera2 CameraQuirks = " + l2.d(l2Var));
        return l2Var;
    }
}
