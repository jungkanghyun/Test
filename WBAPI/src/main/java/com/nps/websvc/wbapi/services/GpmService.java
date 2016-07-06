/*
 * @(#)GpmService.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nps.websvc.wbapi.domain.NwAccessToken;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */

@Service
public class GpmService {

    public static final String CACHE_SSID = "nw_ssid";
    public static final String CACHE_ACCESS_TOKEN = "nw_access_token";

    private static final String CLIENT_ID = "PMANG";
    private static final String CLIENT_SECRET = "mqruablfarhxmq8x2x046zhs6b1yidaqznxdosy88i2cvq2okrnsba9h0mfu17l5";
    private static final String BASE_URL = "http://gpm.dq.nwz.kr";

    private static final String AUTHORIZE_URI = "/";
    private static final String API_URI = "/Gpm/api/";

    private static final String AUTHORIZE_API = "Oauth2/authorize";
    private static final String ACCESS_TOKEN_API = "Oauth2/access_token";
    private static final String SIGNOUT_API = "Oauth2/signout";
    private static final String ME_API = "Oauth2/me";
    private static final String CLIENT_CODE = "Oauth2/client_code";

    private static final String DEFAULT_SCOPE = "";

    private enum Type {
        GET, POST, PUT, DELETE
    }

    @Autowired
    private UserService userService;

    private static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
    }

    // private String accessToken;
    // private String ssid;

    private String scope;
    private String redirect_uri;

    public GpmService() {
        scope = GpmService.DEFAULT_SCOPE;
        redirect_uri = "http://www.pmang.com";
    }

    public Map<String, Object> signin(String userId, String password, String path, String[] extend, String passKey,
            String tracking) {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "signin");
        params.put("path", path);
        params.put("tracking", tracking);
        params.put("pass_key", passKey);
        params.put("username", userId);
        params.put("password", password);
        params.put("extends", extend);

        String response = execute(GpmService.AUTHORIZE_URI, "", "", GpmService.ACCESS_TOKEN_API, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);

        return result;
    }

    public Map<String, Object> sns(String provider, String providerAccessToken, String path,
            String providerRefreshToken, boolean offline, String[] extend) {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "sns");
        params.put("provider", provider);
        params.put("provider_access_token", providerAccessToken);
        params.put("provider_refresh_token", providerRefreshToken);
        params.put("offline", offline);
        params.put("path", path);
        params.put("extends", extend);
        params.put("tracking", provider.toUpperCase());

        String response = execute(GpmService.AUTHORIZE_URI, "", "", GpmService.ACCESS_TOKEN_API, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);

        return result;
    }

    public Map<String, Object> refresh_access_token(String path, String[] extend) {

        NwAccessToken nwAccessToken = userService.getNwAccessTokenFromCookie();
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "refresh_access_token");
        params.put("access_token", nwAccessToken.getNwAccessToken());
        params.put("ssid", nwAccessToken.getNwSsid());
        params.put("path", path);
        params.put("extends", extend);

        String response = execute(GpmService.AUTHORIZE_URI, "", "", GpmService.ACCESS_TOKEN_API, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);

        return result;
    }

    public Map<String, Object> refresh_token(String refreshToken, String path) {
        NwAccessToken nwAccessToken = userService.getNwAccessTokenFromCookie();
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);
        params.put("ssid", nwAccessToken.getNwSsid());
        params.put("path", path);

        String response = execute(GpmService.AUTHORIZE_URI, "", "", GpmService.ACCESS_TOKEN_API, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);

        return result;
    }

    public Map<String, Object> password(String userId, String password, String path, String[] extend, String tracking) {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("path", path);
        params.put("tracking", tracking);
        params.put("username", userId);
        params.put("password", password);
        params.put("extends", extend);

        String response = execute(GpmService.AUTHORIZE_URI, "", "", GpmService.ACCESS_TOKEN_API, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);

        return result;
    }

    public Map<String, Object> client_code(String[] extend) {
        NwAccessToken nwAccessToken = userService.getNwAccessTokenFromCookie();
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", nwAccessToken.getNwAccessToken());
        params.put("ssid", nwAccessToken.getNwSsid());
        params.put("extends", extend);

        String response = execute(GpmService.AUTHORIZE_URI, "", "", GpmService.CLIENT_CODE, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);

        return result;
    }

    public Map<String, Object> me(boolean fresh) {
        NwAccessToken nwAccessToken = userService.getNwAccessTokenFromCookie();
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", nwAccessToken.getNwAccessToken());
        params.put("ssid", nwAccessToken.getNwSsid());
        params.put("fresh", fresh);

        String response = execute(GpmService.AUTHORIZE_URI, "", "", GpmService.ME_API, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);

        return result;
    }

    public Map<String, Object> singout() {
        NwAccessToken nwAccessToken = userService.getNwAccessTokenFromCookie();
        Map<String, Object> params = new HashMap<>();
        params.put("ssid", nwAccessToken.getNwSsid());

        String response = execute(GpmService.AUTHORIZE_URI, "", "", GpmService.SIGNOUT_API, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);

        return result;
    }

    public Map<String, Object> get(String api, Map<String, Object> params) {
        String response = execute(GpmService.API_URI, "", "", api, Type.GET, params);
        Map<String, Object> result = objectFromJsonString(response);
        return result;
    }

    public Map<String, Object> set(String api, Map<String, Object> params) {
        String response = execute(GpmService.API_URI, "", "", api, Type.POST, params);
        Map<String, Object> result = objectFromJsonString(response);
        return result;
    }

    private String execute(String authorizeUri, String namespace, String path, String accessTokenApi, Type type,
            Map<String, Object> params) {

        String responseString = null;

        params.put("client_id", GpmService.CLIENT_ID);
        params.put("scope", scope);
        params.put("client_secret", GpmService.CLIENT_SECRET);
        params.put("redirect_uri", redirect_uri);
        params.put("circuit", false);

        String uri = getUri(authorizeUri + path + accessTokenApi, params);

        switch (type) {
        case GET:
        default:
            responseString = restTemplate.getForObject(uri, String.class, params);
            break;
        }

        return responseString;
    }

    private String getUri(String uri, Map<String, Object> params) {
        Map<String, Object> tmp = new HashMap<>(params);
        tmp.replaceAll((String a, Object b) -> b = a + "={" + a + "}");

        String query = "?";
        for (Object str : tmp.values()) {
            query += "&" + str;
        }
        return GpmService.BASE_URL + uri + query.replace("?&", "?");
    }

    private Map<String, Object> objectFromJsonString(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> results = null;

        try {
            results = objectMapper.readValue(jsonString, Map.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return results;
    }

}
