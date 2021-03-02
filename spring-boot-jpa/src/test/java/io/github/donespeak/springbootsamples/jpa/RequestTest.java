package io.github.donespeak.springbootsamples.jpa;

import com.google.gson.Gson;
import io.github.donespeak.springbootsamples.jpa.controller.TempController;
import org.json.JSONObject;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * @author DoneSpeak
 */
public class RequestTest {

    @Test
    public void test() throws URISyntaxException, UnsupportedEncodingException {
        HttpTest test = new HttpTest();
        
        String link = "http://s3proxy.bees360.com/bees360-dev/temp/1wSFEwvN?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20210113T082227Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3598&X-Amz-Credential=MUzvPVdy3rtc5Oqv/20210113/us-east-2/s3/aws4_request&X-Amz-Signature=d62133745803c4c1dceeda50492c6e267882d5b7db97ab193cdec7f67ed5bff0";
         link = encode(link);

        System.out.println(link);
        URI uri = new URI(link);
        String response = test.get(uri);
//        Gson gson = new Gson();
//        TempController.RequestPart requestPart = gson.fromJson(response, TempController.RequestPart.class);
        System.out.println(response);
//        System.out.println(requestPart.getRequestUri());
//        System.out.println(requestPart.getQueryString());
    }

    private String encode(String key) {
        String encodedKey = "";
        try {
            encodedKey = URLEncoder.encode(key, "utf-8");
            // 【空格】编码为【%20】而不是【+】
            // 【:】和【/】不进行编码
            encodedKey = encodedKey.replaceAll("\\+", "%20").replaceAll("\\%3A", ":")
                    .replaceAll("\\%2F", "/");
        } catch (UnsupportedEncodingException e) {
            // 不会发生的异常
            throw new IllegalStateException("unable to encode key `" + key + "`", e);
        }
        return encodedKey;
    }

    private String encode2(String key) {
        return key;
    }
}
