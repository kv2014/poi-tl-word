package com.kv.me;

import com.deepoove.poi.XWPFTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenWordCtx {
    private static final Logger log = LoggerFactory.getLogger(GenWordCtx.class);

    public static void main(String[] args) {
        String srcPath = "src/main/resources//tmpl/template.docx";
        String outPath = "target/out_template.docx";

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("title", "Poi-tl 模板引擎");
        dataMap.put("person", "abc");
        genWord(srcPath, outPath, dataMap);

    }

    private static void genWord(String srcPath, String outPath, Map<String, Object> dataMap) {
        log.debug("================start====================");

        XWPFTemplate template = null;
        FileOutputStream out = null;
        try {
            template = XWPFTemplate.compile(srcPath).render(dataMap);

            out = new FileOutputStream(outPath);
            template.write(out);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                out.flush();
                out.close();
                template.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        log.debug("====================end========================");
    }

}
