import org.junit.Test;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by AlexXie on 2015/9/2.
 */
public class ProTest {

    @Test
    public void readFile() {
        com.balintimes.erp.util.common.PropertyUtil util = new com.balintimes.erp.util.common.PropertyUtil();
        util.getFile();
//        Properties prop = new Properties();
//        InputStream in = null;
//        try {
//
//            in = Thread.currentThread().getClass().getResourceAsStream("settings.properties");
//            prop.load(in);
//            Iterator<String> it = prop.stringPropertyNames().iterator();
//            while (it.hasNext()) {
//                String key = it.next();
//                System.out.println(key + ":" + prop.getProperty(key));
//            }
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
