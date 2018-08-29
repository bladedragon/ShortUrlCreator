package team.redrock.shorturl.service;

import team.redrock.shorturl.Utility.Response;

public interface UrlService {
    Response saveUrl(String longurl, String shorturl , int expire);
    String RestoreUrl(String shorturl);
}
