package com.yaas.recodercodereviewservice.code;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.springframework.stereotype.Component;

@Component
public class FileGet {
    public FileGet() {
    }

    public String getFile(String reviewCodePath) {
        String code = "";

        try {
            FileInputStream input = new FileInputStream(reviewCodePath);
            InputStreamReader reader = new InputStreamReader(input, "UTF-8");

            BufferedReader in;
            int ch;
            for(in = new BufferedReader(reader); (ch = in.read()) != -1; code = code + (char)ch) {
            }

            in.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return code;
    }
}
