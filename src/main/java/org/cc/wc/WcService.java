package org.cc.wc;

import org.cc.wc.model.WcCommand;
import org.cc.wc.model.enumerate.WcOption;

import java.io.*;
import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 *
 * @author siangyekkhan
 * @date 18/1/24
 */
public class WcService {
    public static void execute(WcCommand wcCommand) throws IOException {
        InputStream inputStream;
        String fileName = "";
        try {
            WcOption wcOption = wcCommand.getWcOption();
            String filePath = wcCommand.getFilePath();
            if (filePath == null) {
                inputStream = System.in;
                if (inputStream == null) {
                    System.err.println("No input");
                    return;
                }
            } else {
                File file = new File(filePath);
                fileName = file.getPath();
                if (!file.exists()) {
                    System.err.println("File not found");
                    return;
                }
                inputStream = Files.newInputStream(file.toPath());
            }
            switch (wcOption) {
                case WORD:
                    countWords(inputStream);
                    break;
                case LINE:
                    countLines(inputStream);
                    break;
                case CHARACTER:
                    countCharacters(inputStream);
                    break;
                case BYTE:
                    countBytes(inputStream);
                    break;
                default:
                    countAll(inputStream);
                    break;
            }
            System.out.println(" " + fileName);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    private static void countWords(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
        int count = 0;
        int c;
        boolean newWord = true;
        while ((c = br.read()) != -1) {
            if (Character.isWhitespace(c)) {
                newWord = true;
                continue;
            }
            if (newWord) {
                newWord = false;
                count++;
            }
        }

        System.out.print("    " + count);
    }

    private static void countLines(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
        int count = 0;
        while (br.readLine() != null) {
            count ++;
        }

        System.out.print("    " + count);
    }

    private static void countCharacters(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
        int count = 0;
        while (br.read() != -1) {
            count++;
        }

        System.out.print("    " + count);
    }

    private static void countBytes(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
        int count = 0;
        int c;
        while ((c = br.read()) != -1) {
            byte[] bytes = Character.toString((char) c).getBytes(UTF_8);
            count += bytes.length;
        }
        System.out.print("    " + count);
    }

    private static void countAll(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
        StringBuilder result = new StringBuilder();
        int lineCount = 0;
        int wordCount = 0;
        int byteCount = 0;
        int c;
        boolean newWord = true;
//        boolean newLine = true;

        while ((c = br.read()) != -1) {
            byte[] bytes = Character.toString((char) c).getBytes(UTF_8);
            byteCount += bytes.length;

            if (Character.isWhitespace(c)) {
                newWord = true;
                if (c == '\n') {
                    lineCount++;
                }
                continue;
            }
            if (newWord) {
                newWord = false;
                wordCount++;
            }
        }

        result.append("    ");
        result.append(lineCount).append("   ");
        result.append(wordCount).append("  ");
        //line -> word -> byte
        result.append(byteCount);

        System.out.print(result);
    }
}
