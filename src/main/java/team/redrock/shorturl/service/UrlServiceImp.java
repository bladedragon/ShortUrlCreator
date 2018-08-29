package team.redrock.shorturl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.shorturl.DAO.UrlMapper;
import team.redrock.shorturl.Utility.HashUtility;
import team.redrock.shorturl.Utility.Response;
import team.redrock.shorturl.entity.Url;

import javax.annotation.Resource;
import javax.swing.text.Utilities;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UrlServiceImp implements UrlService {

   @Autowired
    UrlMapper urlMapper;

    HashUtility hashUtility = new HashUtility();

    @Autowired
    private RedisTemplate<Object ,Url> urlRedisTemplate ;
    @Autowired
    private RedisTemplate<String,String> redisTemplatel;

    @Override
    public Response saveUrl(String longurl, String shorturl ,int expire){
//        String path = "localhost:8080/ZS/";
        String path = "http://120.77.150.210:8081/ZS/";
        System.out.println(longurl);
        String md5url = hashUtility.getMD5Str(longurl);
        System.out.println(md5url);
        System.out.println(hashUtility.shortUrl(longurl));
        if(this.urlRedisTemplate.opsForHash().hasKey("shortlong",shorturl)){     //查询shortlong映射表
            return new Response("-1","短链接已存在");
        }
        Url url = new Url();
       Url url2 = this.urlRedisTemplate.opsForValue().get(md5url);


        int flag = 0;


        if(url2==null){
            Long urlid = this.urlRedisTemplate.opsForValue().increment("Id:",1);
            url.setId(urlid);
            url.setHashid(md5url);
            url.setShortpath(hashUtility.shortUrl(longurl));
            url.setLongpath(longurl);
            this.urlRedisTemplate.opsForHash().put("urlObject",md5url,url);//存入String-对象的表中
            Map<String,String> map = new HashMap<>();


            if(shorturl.equals("")){
                map.put(hashUtility.shortUrl(longurl),longurl);

                path += hashUtility.shortUrl(longurl);
            }else{
                map.put(shorturl,longurl);

                System.out.println(map);
                path += shorturl;
            }
            redisTemplatel.opsForHash().putAll("shortlong",map);
            //存入映射表
            //定义该网址是否存在在redis数据库里，1表示不在
            flag = 1;

        }else{
            if(shorturl==null){
                path += url2.getShortpath();
                return new Response("0",JSON.toJSONString(path));

            }else{
                return new Response("-1","该网址已存在映射关系");
            }

        }

        if(flag==1)
        {
            if(expire == -1){
                //如果没有设过期时间，则插入mysql数据库
                urlMapper.insert(url);

            }else{
                //设置过期时间
                this.urlRedisTemplate.expire("urlObject", expire, TimeUnit.HOURS);
                this.urlRedisTemplate.expire("shortlong",expire,TimeUnit.HOURS);
            }
        }
        return new Response("0",JSON.toJSONString(path));

    }

    @Override
    public String RestoreUrl(String shorturl){


        String longurl = (String) redisTemplatel.opsForHash().get("shortlong",shorturl);
        if(longurl!=null && !longurl.equals("")){
            return longurl;
        }else{
            System.out.println("未获得长链接");
        }

       return "http://www.17sucai.com/preview/1160620/2018-04-21/404/demo.html";
    }
}
