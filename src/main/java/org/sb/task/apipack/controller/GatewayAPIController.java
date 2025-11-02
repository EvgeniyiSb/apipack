package org.sb.task.apipack.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class GatewayAPIController {

    @RequestMapping("/servise/**")
    public ResponseEntity<Void> gateway(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpHeaders headers = new HttpHeaders();

        String url = request.getServletPath().replaceFirst("^/servise", "");
        String keyUrl = url;
        String valueUrl;

        int value = -1;

        System.out.println(url);

        Map<String, String> urlList = Map.of("/all", "/users/get/all",
                "/user/{n}", "/users/get/{n}",
                "/del/{n}", "/users/delete/{n}");

        try {
            Pattern pattern = Pattern.compile("[\\d]+$");
            Matcher matcher = pattern.matcher(url);

            if (matcher.find())
            {
                value = Integer.parseInt(matcher.group());
                keyUrl = url.replaceFirst("[\\d]+$", "") + "{n}";

            }

            valueUrl = urlList
                    .get(keyUrl)
                    .replace("{n}", Integer.toString(value));


            System.out.println(valueUrl);


            headers.setLocation(URI.create(valueUrl));
            headers.setAccessControlRequestMethod(HttpMethod.DELETE);

//            HttpEntity httpEntity = new HttpEntity<Object>(headers);
//            RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//            template.exchange(valueUrl, HttpMethod.DELETE, httpEntity, String.class);

            //кэширование
//            headers.setCacheControl("max-age=3600, public");

            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }catch (Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }
}
