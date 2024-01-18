package org.cc.wc;

import org.cc.wc.model.WcCommand;
import org.cc.wc.model.enumerate.WcOption;

/**
 *
 * @author siangyekkhan
 * @date 18/1/24
 */
public class WcParser {
    public static WcCommand parse(String[] args) {
        if (args.length == 2) {
            WcOption wcOption = getWcOption(args[0]);
            if (wcOption == null) {
                throw new RuntimeException("Invalid option");
            }

            return new WcCommand(wcOption, args[1]);
        }

        if (args.length == 1) {
            if (args[0].charAt(0) == '-') {
                WcOption wcOption = getWcOption(args[0]);
                if (wcOption == null) {
                    throw new RuntimeException("Invalid option");
                }

                return new WcCommand(wcOption, null);
            } else {
                return new WcCommand(WcOption.DEFAULT, args[0]);
            }
        }

        if (args.length == 0) {
            return new WcCommand(WcOption.DEFAULT, null);
        }

        throw new RuntimeException("Invalid command");
    }

    private static WcOption getWcOption(String arg) {
        if (arg.charAt(0) != '-' || arg.length() != 2) {
            throw new RuntimeException("Invalid option");
        }

        String commandLineOption = arg.charAt(1) + "";

        return WcOption.getOption(commandLineOption);
    }
}
