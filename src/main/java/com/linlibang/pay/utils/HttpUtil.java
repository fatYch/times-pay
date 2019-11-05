package com.linlibang.pay.utils;

import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * http工具类
 */
@Log4j
public class HttpUtil {

    /**
     * 获取请求IP
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        //代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        //如果没有代理，则获取真实ip
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


    /**
     * Send a get form
     *
     * @param url
     * @return response
     * @throws IOException
     */
    static public String get(String url) {
        return get(url, null);
    }

    /**
     * Send a get form
     *
     * @param url     Url as string
     * @param headers Optional map with headers
     * @return response   Response as string
     * @throws IOException
     */
    static public String get(String url,
                             Map<String, String> headers) {
        return fetch("GET", url, null, headers);
    }

    /**
     * Send a post form
     *
     * @param url     Url as string
     * @param body    Request body as string
     * @param headers Optional map with headers
     * @return response   Response as string
     * @throws IOException
     */
    static public String post(String url, String body,
                              Map<String, String> headers) throws IOException {
        return fetch("POST", url, body, headers);
    }

    /**
     * Send a post form
     *
     * @param url  Url as string
     * @param body Request body as string
     * @return response   Response as string
     * @throws IOException
     */
    static public String post(String url, String body) throws IOException {
        return post(url, body, null);
    }

    /**
     * Post a form with parameters
     *
     * @param url    Url as string
     * @param params map with parameters/values
     * @return response   Response as string
     * @throws IOException
     */
    static public String postForm(String url, Map<String, String> params)
            throws IOException {
        return postForm(url, params, null);
    }

    /**
     * Post a form with parameters
     *
     * @param url     Url as string
     * @param params  Map with parameters/values
     * @param headers Optional map with headers
     * @return response   Response as string
     * @throws IOException
     */
    static public String postForm(String url, Map<String, String> params,
                                  Map<String, String> headers) throws IOException {
        // set content type
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        // parse parameters
        String body = "";
        if (params != null) {
            boolean first = true;
            for (String param : params.keySet()) {
                if (first) {
                    first = false;
                } else {
                    body += "&";
                }
                String value = params.get(param);
                body += URLEncoder.encode(param, "UTF-8") + "=";
                body += URLEncoder.encode(value, "UTF-8");
            }
        }

        return post(url, body, headers);
    }

    /**
     * Send a put form
     *
     * @param url     Url as string
     * @param body    Request body as string
     * @param headers Optional map with headers
     * @return response   Response as string
     * @throws IOException
     */
    static public String put(String url, String body,
                             Map<String, String> headers) throws IOException {
        return fetch("PUT", url, body, headers);
    }

    /**
     * Send a put form
     *
     * @param url Url as string
     * @return response   Response as string
     * @throws IOException
     */
    static public String put(String url, String body) throws IOException {
        return put(url, body, null);
    }

    /**
     * Send a delete form
     *
     * @param url     Url as string
     * @param headers Optional map with headers
     * @return response   Response as string
     * @throws IOException
     */
    static public String delete(String url,
                                Map<String, String> headers) throws IOException {
        return fetch("DELETE", url, null, headers);
    }

    /**
     * Send a delete form
     *
     * @param url Url as string
     * @return response   Response as string
     * @throws IOException
     */
    static public String delete(String url) throws IOException {
        return delete(url, null);
    }

    /**
     * Append query parameters to given url
     *
     * @param url    Url as string
     * @param params Map with query parameters
     * @return url        Url with query parameters appended
     * @throws IOException
     */
    static public String appendQueryParams(String url,
                                           Map<String, String> params) throws IOException {
        String fullUrl = url;
        if (params != null) {
            boolean first = (fullUrl.indexOf('?') == -1);
            for (String param : params.keySet()) {
                if (first) {
                    fullUrl += '?';
                    first = false;
                } else {
                    fullUrl += '&';
                }
                String value = params.get(param);
                fullUrl += URLEncoder.encode(param, "UTF-8") + '=';
                fullUrl += URLEncoder.encode(value, "UTF-8");
            }
        }

        return fullUrl;
    }

    /**
     * Retrieve the query parameters from given url
     *
     * @param url Url containing query parameters
     * @return params     Map with query parameters
     * @throws IOException
     */
    static public Map<String, String> getQueryParams(String url)
            throws IOException {
        Map<String, String> params = new HashMap<String, String>();

        int start = url.indexOf('?');
        while (start != -1) {
            // read parameter name
            int equals = url.indexOf('=', start);
            String param = "";
            if (equals != -1) {
                param = url.substring(start + 1, equals);
            } else {
                param = url.substring(start + 1);
            }

            // read parameter value
            String value = "";
            if (equals != -1) {
                start = url.indexOf('&', equals);
                if (start != -1) {
                    value = url.substring(equals + 1, start);
                } else {
                    value = url.substring(equals + 1);
                }
            }

            params.put(URLDecoder.decode(param, "UTF-8"),
                    URLDecoder.decode(value, "UTF-8"));
        }

        return params;
    }

    /**
     * Returns the url without query parameters
     *
     * @param url Url containing query parameters
     * @return url        Url without query parameters
     * @throws IOException
     */
    static public String removeQueryParams(String url)
            throws IOException {
        int q = url.indexOf('?');
        if (q != -1) {
            return url.substring(0, q);
        } else {
            return url;
        }
    }

    /**
     * Send a form
     *
     * @param method  HTTP method, for example "GET" or "POST"
     * @param url     Url as string
     * @param body    Request body as string
     * @param headers Optional map with headers
     * @return response   Response as string
     * @throws IOException
     */
    static public String fetch(String method, String url, String body,
                               Map<String, String> headers) {
        try {
            // connection
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");


            // method
            if (method != null) {
                conn.setRequestMethod(method);
            }

            // headers
            if (headers != null) {
                for (String key : headers.keySet()) {
                    conn.addRequestProperty(key, headers.get(key));
                }
            }

            // body
            if (body != null) {
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write(body.getBytes());
                os.flush();
                os.close();
            }
            InputStream is = conn.getResponseCode() == HttpURLConnection.HTTP_OK ?
                    conn.getInputStream() : conn.getErrorStream();
            String response = streamToString(is);
            is.close();

            // handle redirects
            if (conn.getResponseCode() == 301) {
                String location = conn.getHeaderField("Location");
                return fetch(method, location, body, headers);
            }
            return response;
        } catch (Exception e) {
            log.error("网络访问错误:", e);
        }
        return null;
    }

    /**
     * Read an input stream into a string
     *
     * @param in
     * @return
     * @throws IOException
     */
    static public String streamToString(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }


}