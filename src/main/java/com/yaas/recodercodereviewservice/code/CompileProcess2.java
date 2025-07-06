package com.yaas.recodercodereviewservice.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CompileProcess2 {
    public CompileProcess2() {
    }

    public static String execute(String fileName) {
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer();
        StringBuffer errorOutput = new StringBuffer();
        BufferedReader successBufferReader = null;
        BufferedReader errorBufferReader = null;
        String msg = "";
        String successResult = "";
        List<String> cmdList = new ArrayList();
        if (System.getProperty("os.name").indexOf("Windows") > -1) {
            cmdList.add("cmd");
            cmdList.add("/c");
        } else {
            cmdList.add("/bin/sh");
            cmdList.add("-c");
        }

        int idx = fileName.indexOf(".");
        fileName.substring(0, idx);
        String tmpCmd = "";
        tmpCmd = "cd ..&&cd recoder-code&&java test";
        cmdList.add(tmpCmd);
        System.out.println("뭐가 나오는건가 이 배열은 \n" + tmpCmd.toString());
        String[] array = (String[])cmdList.toArray(new String[cmdList.size()]);

        try {
            process = runtime.exec(array);
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

            while((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg + System.getProperty("line.separator"));
            }

            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));

            while((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg + System.getProperty("line.separator"));
            }

            process.waitFor();
            if (process.exitValue() == 0) {
                System.out.println("성공");
                successResult = successResult + successOutput.toString();
                System.out.println(successResult);
            } else {
                System.out.println("비정상 종료");
                System.out.println(successOutput.toString());
            }
        } catch (IOException var25) {
            var25.printStackTrace();
        } catch (InterruptedException var26) {
            var26.printStackTrace();
        } finally {
            try {
                process.destroy();
                if (successBufferReader != null) {
                    successBufferReader.close();
                }

                if (errorBufferReader != null) {
                    errorBufferReader.close();
                }
            } catch (IOException var24) {
                var24.printStackTrace();
            }

        }

        return successResult;
    }
}
