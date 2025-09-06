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
        List<String> cmdList = new ArrayList<>();

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
        String tmpCmd = "";

        if (language.equals(".java")) {
            // 리눅스 컨테이너 경로 기반
            tmpCmd = "cd /usr/src/recoder/java && javac -encoding UTF-8 " + fileName +
                     " && java " + idxResult;
            System.out.println("java cmd: " + tmpCmd);
        } else if (language.equals(".c")) {
            tmpCmd = "cd /usr/src/recoder/c && gcc -o " + idxResult + " " + fileName +
                     " && ./" + idxResult;
            System.out.println("c cmd: " + tmpCmd);
        } else if (language.equals(".cpp")) {
            tmpCmd = "cd /usr/src/recoder/cpp && g++ -o " + idxResult + " " + fileName +
                     " -O2 -Wall -lm -static -std=gnu++98 && ./" + idxResult;
            System.out.println("cpp cmd: " + tmpCmd);
        }

        cmdList.add(tmpCmd);
        String[] array = cmdList.toArray(new String[cmdList.size()]);

        try {
            process = runtime.exec(array);

            // 리눅스는 기본 UTF-8
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            while ((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg).append(System.lineSeparator());
            }

            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
            while ((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg).append(System.lineSeparator());
            }

            process.waitFor();
            if (process.exitValue() == 0) {
                resultMessage = successOutput.toString();
                System.out.println("성공: \n" + resultMessage);
            } else {
                resultMessage = errorOutput.toString();
                System.out.println("비정상 종료: \n" + resultMessage);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (process != null) process.destroy();
                if (successBufferReader != null) successBufferReader.close();
                if (errorBufferReader != null) errorBufferReader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return resultMessage;
    }
}
