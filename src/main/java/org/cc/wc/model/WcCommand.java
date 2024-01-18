package org.cc.wc.model;

import org.cc.wc.model.enumerate.WcOption;

/**
 *
 * @author siangyekkhan
 * @date 18/1/24
 */
public class WcCommand {
    WcOption wcOption;
    String filePath;

    public WcOption getWcOption() {
        return wcOption;
    }

    public void setWcOption(WcOption wcOption) {
        this.wcOption = wcOption;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public WcCommand(WcOption wcOption, String filePath) {
        this.wcOption = wcOption;
        this.filePath = filePath;
    }
}
