package com.yaas.recodercodereviewservice.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CompileProcess3 {
    public CompileProcess3() {
    }

    public static String execute(String fileName) {
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer();
        StringBuffer errorOutput = new StringBuffer();
        BufferedReader successBufferReader = null;
        BufferedReader errorBufferReader = null;
        String msg = "";
        String resultMessage = "";
        List<String> cmdList = new ArrayList();
        if (System.getProperty("os.name").indexOf("Windows") > -1) {
            cmdList.add("cmd");
            cmdList.add("/c");
        } else {
            cmdList.add("/bin/sh");
            cmdList.add("-c");
        }

        int idx = fileName.indexOf(".");
        String idxResult = fileName.substring(0, idx);
        String language = fileName.substring(idx);
        System.out.println(language);
        String tmpCmd = "";
        if (language.equals(".java")) {
            tmpCmd = "javac C:/recoder/recoder-code/" + fileName + " -encoding UTF-8&&cd ..&&cd recoder-code&java " + idxResult;
            System.out.println("java 뭐가 나오는건가 이 배열은 \n" + tmpCmd.toString());
        } else if (language.equals(".c")) {
            tmpCmd = "gcc -o recoder C:/recoder/recoder-code/" + fileName + "&&recoder";
            System.out.println("c 뭐가 나오는건가 이 배열은 \n" + tmpCmd.toString());
        } else {
            tmpCmd = "g++ -o recoder C:/recoder/recoder-code/" + fileName + " -O2 -Wall -lm -static -std=gnu++98&&recoder";
            System.out.println("cpp 뭐가 나오는건가 이 배열은 \n" + tmpCmd.toString());
        }

        cmdList.add(tmpCmd);
        String[] array = (String[])cmdList.toArray(new String[cmdList.size()]);

        try {
            process = runtime.exec(array);
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "MS949"));

            while((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg + System.getProperty("line.separator"));
            }

            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "MS949"));

            while((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg + System.getProperty("line.separator"));
            }

            process.waitFor();
            if (process.exitValue() == 0) {
                System.out.println("성공");
                resultMessage = resultMessage + successOutput.toString();
                System.out.println(resultMessage);
            } else {
                System.out.println("비정상 종료");
                System.out.println(successOutput.toString());
                resultMessage = resultMessage + errorOutput.toString();
                System.out.println(resultMessage);
            }
        } catch (IOException var26) {
            var26.printStackTrace();
        } catch (InterruptedException var27) {
            var27.printStackTrace();
        } finally {
            try {
                process.destroy();
                if (successBufferReader != null) {
                    successBufferReader.close();
                }

                if (errorBufferReader != null) {
                    errorBufferReader.close();
                }
            } catch (IOException var25) {
                var25.printStackTrace();
            }

        }

        return resultMessage;
    }
}
