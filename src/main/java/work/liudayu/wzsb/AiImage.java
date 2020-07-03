package work.liudayu.wzsb;

import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("all")
@RestController
public class AiImage {

    @RequestMapping(value = "/upload")
    String upload(MultipartFile file,HttpServletRequest request){

        final String APP_ID = "11007270";
        final String API_KEY = "DrsthdAGsEyrGdiEUVXjhD8o";
        final String SECRET_KEY = "PNNTNzj4KUcbA3RNNqFutKKOUjY1Nqoa";
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 参数为本地图片二进制数组
        byte[] data =null;
        try {
            InputStream input = file.getInputStream();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int numBytesRead = 0;
                while ((numBytesRead = input.read(buf)) != -1) {
                    output.write(buf, 0, numBytesRead);
                }
                data = output.toByteArray();
                output.close();
                input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject  res = client.basicGeneral(data , new HashMap<String, String>());
        String result = res.get("words_result").toString();
        result=result.substring(3,result.length()-3);
        String[] wordses = result.split("words");
        String info="";
        for(int i=1;i<wordses.length;i++){
            if(i==wordses.length-1){ //说明是最后一个数据
                info=info+wordses[i].substring(3,wordses[i].length());
            }else {
                info = info + wordses[i].substring(3, wordses[i].length() - 5);
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("data",info);
        System.out.println(info);
        return JSONArray.toJSONString(map);
    }
}