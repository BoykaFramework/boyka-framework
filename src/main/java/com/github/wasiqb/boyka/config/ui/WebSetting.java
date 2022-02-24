package com.github.wasiqb.boyka.config.ui;

import static com.github.wasiqb.boyka.enums.Protocol.HTTP;
import static com.github.wasiqb.boyka.utils.StringUtils.interpolate;

import java.util.Map;

import com.github.wasiqb.boyka.enums.Browser;
import com.github.wasiqb.boyka.enums.CloudProviders;
import com.github.wasiqb.boyka.enums.Protocol;
import lombok.Data;

@Data
public class WebSetting {
    private Browser             browser  = Browser.NONE;
    private Map<String, Object> capabilities;
    private CloudProviders      cloud    = CloudProviders.NONE;
    private String              host     = "localhost";
    private String              password;
    private int                 port;
    private Protocol            protocol = HTTP;
    private String              userName;

    public String getPassword () {
        return interpolate (this.password);
    }

    public String getUserName () {
        return interpolate (this.userName);
    }
}
