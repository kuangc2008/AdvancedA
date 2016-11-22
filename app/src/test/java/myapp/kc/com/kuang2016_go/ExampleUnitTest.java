package myapp.kc.com.kuang2016_go;

import android.util.Log;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testDecode() {
        String aaa = "%E7%82%89%E7%9F%B3%E4%BC%A0%E8%AF%B4";

        try {
            String aaaGBk = URLDecoder.decode(aaa, "GBK");


            String aaaGBKUTF8 = new String(aaaGBk.getBytes("UTF-8"), "UTF-8");


            String initaaa = URLEncoder.encode(aaaGBKUTF8, "GBK");

            System.out.println("aa->" + aaaGBk + "  initA->" + initaaa + "  aa->" + aaaGBKUTF8);



            String aaaUTF8 = URLDecoder.decode(aaa, "UTF-8");
            String aaaGBKUTF82 = new String(aaaUTF8.getBytes("UTF-8"), "UTF-8");
            String initaaa2 = URLEncoder.encode(aaaGBKUTF82, "UTF-8");
            System.out.println("aa->" + aaaUTF8 + "  initA->" + initaaa2 +  " 22->" + aaaGBKUTF82);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}