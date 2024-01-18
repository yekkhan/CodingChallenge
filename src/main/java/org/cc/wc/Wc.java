package org.cc.wc;

import org.cc.wc.model.WcCommand;

import java.io.IOException;

/**
 *
 * @author siangyekkhan
 */
public class Wc
{
    public static void main( String[] args ) throws IOException {
        WcCommand wcCommand;
        try {
            wcCommand = WcParser.parse(args);
            WcService.execute(wcCommand);
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
