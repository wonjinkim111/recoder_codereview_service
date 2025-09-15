package com.yaas.recodercodereviewservice.code;

import com.yaas.recodercodereviewservice.entity.Reviews;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileSaveLinux {
    private static final Logger log = LoggerFactory.getLogger(FileSaveLinux.class);

    public FileSaveLinux() {
    }

    public String setFileStore(Reviews reviews, long reviewId) {
        log.info(String.format("< FileSave class >", new Object[0]));
        String fileName = "";
        String timeStamp = (new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")).format(new Date());
        try {
          if (reviews.getReviewLanguage() == 0) {
            fileName = "Recoder" + reviewId + "_" + reviews.getMenteeId() + "_" + timeStamp + ".java";
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/usr/src/recoder/java/" + fileName), "UTF-8"));
            writer(reviews, fw);
          } else if (reviews.getReviewLanguage() == 1) {
            fileName = reviewId + "_" + reviews.getMenteeId() + "_" + timeStamp + ".c";
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/usr/src/recoder/c/" + fileName), "UTF-8"));
            writer(reviews, fw);
          } else {
            fileName = reviewId + "_" + reviews.getMenteeId() + "_" + timeStamp + ".cpp";
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/usr/src/recoder/cpp/" + fileName), "UTF-8"));
            writer(reviews, fw);
          } 
        } catch (Exception e) {
          e.printStackTrace();
        } 
        return fileName;
      }
      
      public void writer(Reviews reviews, BufferedWriter fw) {
        try {
          fw.write(reviews.getReviewCode());
          fw.flush();
          fw.close();
        } catch (Exception e) {
          e.printStackTrace();
        } 
      }
}
