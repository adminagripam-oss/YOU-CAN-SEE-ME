package androidx.camera.video.internal.compat.quirk;

import b0.i2;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static List a(i2 i2Var) {
        ArrayList arrayList = new ArrayList();
        if (i2Var.a(MediaFormatMustNotUseFrameRateToFindEncoderQuirk.class, MediaFormatMustNotUseFrameRateToFindEncoderQuirk.f())) {
            arrayList.add(new MediaFormatMustNotUseFrameRateToFindEncoderQuirk());
        }
        if (i2Var.a(MediaCodecInfoReportIncorrectInfoQuirk.class, MediaCodecInfoReportIncorrectInfoQuirk.o())) {
            arrayList.add(new MediaCodecInfoReportIncorrectInfoQuirk());
        }
        if (i2Var.a(DeactivateEncoderSurfaceBeforeStopEncoderQuirk.class, DeactivateEncoderSurfaceBeforeStopEncoderQuirk.f())) {
            arrayList.add(new DeactivateEncoderSurfaceBeforeStopEncoderQuirk());
        }
        if (i2Var.a(CameraUseInconsistentTimebaseQuirk.class, CameraUseInconsistentTimebaseQuirk.h())) {
            arrayList.add(new CameraUseInconsistentTimebaseQuirk());
        }
        if (i2Var.a(ReportedVideoQualityNotSupportedQuirk.class, ReportedVideoQualityNotSupportedQuirk.k())) {
            arrayList.add(new ReportedVideoQualityNotSupportedQuirk());
        }
        if (i2Var.a(EncoderNotUsePersistentInputSurfaceQuirk.class, EncoderNotUsePersistentInputSurfaceQuirk.f())) {
            arrayList.add(new EncoderNotUsePersistentInputSurfaceQuirk());
        }
        if (i2Var.a(VideoEncoderCrashQuirk.class, VideoEncoderCrashQuirk.g())) {
            arrayList.add(new VideoEncoderCrashQuirk());
        }
        if (i2Var.a(ExcludeStretchedVideoQualityQuirk.class, ExcludeStretchedVideoQualityQuirk.m())) {
            arrayList.add(new ExcludeStretchedVideoQualityQuirk());
        }
        if (i2Var.a(MediaStoreVideoCannotWrite.class, MediaStoreVideoCannotWrite.h())) {
            arrayList.add(new MediaStoreVideoCannotWrite());
        }
        if (i2Var.a(AudioEncoderIgnoresInputTimestampQuirk.class, AudioEncoderIgnoresInputTimestampQuirk.g())) {
            arrayList.add(new AudioEncoderIgnoresInputTimestampQuirk());
        }
        if (i2Var.a(VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk.class, VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk.f())) {
            arrayList.add(new VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk());
        }
        if (i2Var.a(NegativeLatLongSavesIncorrectlyQuirk.class, NegativeLatLongSavesIncorrectlyQuirk.f())) {
            arrayList.add(new NegativeLatLongSavesIncorrectlyQuirk());
        }
        if (i2Var.a(AudioTimestampFramePositionIncorrectQuirk.class, AudioTimestampFramePositionIncorrectQuirk.m())) {
            arrayList.add(new AudioTimestampFramePositionIncorrectQuirk());
        }
        if (i2Var.a(ExtraSupportedResolutionQuirk.class, ExtraSupportedResolutionQuirk.g())) {
            arrayList.add(new ExtraSupportedResolutionQuirk());
        }
        if (i2Var.a(StretchedVideoResolutionQuirk.class, StretchedVideoResolutionQuirk.h())) {
            arrayList.add(new StretchedVideoResolutionQuirk());
        }
        if (i2Var.a(CodecStuckOnFlushQuirk.class, CodecStuckOnFlushQuirk.h())) {
            arrayList.add(new CodecStuckOnFlushQuirk());
        }
        if (i2Var.a(StopCodecAfterSurfaceRemovalCrashMediaServerQuirk.class, StopCodecAfterSurfaceRemovalCrashMediaServerQuirk.g())) {
            arrayList.add(new StopCodecAfterSurfaceRemovalCrashMediaServerQuirk());
        }
        if (i2Var.a(ExtraSupportedQualityQuirk.class, ExtraSupportedQualityQuirk.j())) {
            arrayList.add(new ExtraSupportedQualityQuirk());
        }
        if (i2Var.a(SignalEosOutputBufferNotComeQuirk.class, SignalEosOutputBufferNotComeQuirk.g())) {
            arrayList.add(new SignalEosOutputBufferNotComeQuirk());
        }
        if (i2Var.a(SizeCannotEncodeVideoQuirk.class, SizeCannotEncodeVideoQuirk.j())) {
            arrayList.add(new SizeCannotEncodeVideoQuirk());
        }
        return arrayList;
    }
}
