package com.github.wasiqb.boyka.config;

import static com.github.wasiqb.boyka.enums.Messages.INVALID_PLATFORM_FOR_OPERATION;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.util.Map;

import com.github.wasiqb.boyka.config.android.MobileSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.exception.FrameworkError;
import lombok.Data;

@Data
public class UISetting {
    private Map<String, MobileSetting> android;
    private Map<String, MobileSetting> ios;
    private PlaybackSetting            playback;

    public MobileSetting getMobileSetting (final ApplicationType applicationType, final String key) {
        if (applicationType == ApplicationType.IOS) {
            return requireNonNull (this.ios.get (key));
        } else if (applicationType == ApplicationType.ANDROID) {
            return requireNonNull (this.android.get (key));
        }
        throw new FrameworkError (format (INVALID_PLATFORM_FOR_OPERATION.getMessage (), applicationType));
    }
}
