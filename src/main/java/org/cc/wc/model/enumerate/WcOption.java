package org.cc.wc.model.enumerate;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author siangyekkhan
 * @date 18/1/24
 */
public enum WcOption {
    /**
     * Word count
     */
    WORD("w"),
    /**
     * Line count
     */
    LINE("l"),
    /**
     * Character count
     */
    CHARACTER("m"),
    /**
     * Byte count
     */
    BYTE("c"),
    DEFAULT("");

    private static final Map<String, WcOption> map;

    private final String commandLineOption;

    static {
        map = new HashMap<>();
        map.put(WORD.commandLineOption, WORD);
        map.put(LINE.commandLineOption, LINE);
        map.put(CHARACTER.commandLineOption, CHARACTER);
        map.put(BYTE.commandLineOption, BYTE);
    }

    WcOption(String commandLineOption) {
        this.commandLineOption = commandLineOption;
    }

    public String getCommandLineOption() {
        return commandLineOption;
    }

    public static WcOption getOption(String commandLineOption) {
        return map.get(commandLineOption);
    }
}
