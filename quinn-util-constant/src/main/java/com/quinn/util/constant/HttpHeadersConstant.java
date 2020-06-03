package com.quinn.util.constant;

/**
 * Http请求头信息常量
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public interface HttpHeadersConstant {

    /**
     * The HTTP Cache-Control header field name.
     */
    String CACHE_CONTROL = "Cache-Control";

    /**
     * The HTTP Content-Length header field name.
     */
    String CONTENT_LENGTH = "Content-Length";

    /**
     * The HTTP Content-Type header field name.
     */
    String CONTENT_TYPE = "Content-Type";

    /**
     * The HTTP Date header field name.
     */
    String DATE = "Date";

    /**
     * The HTTP Pragma header field name.
     */
    String PRAGMA = "Pragma";

    /**
     * The HTTP Via header field name.
     */

    String VIA = "Via";
    /**
     * The HTTP Warning header field name.
     */
    String WARNING = "Warning";

    /**
     * The HTTP Accept header field name.
     */
    String ACCEPT = "Accept";
    /**
     * The HTTP Accept-Charset header field name.
     */
    String ACCEPT_CHARSET = "Accept-Charset";
    /**
     * The HTTP Accept-Encoding header field name.
     */
    String ACCEPT_ENCODING = "Accept-Encoding";
    /**
     * The HTTP Accept-Language header field name.
     */
    String ACCEPT_LANGUAGE = "Accept-Language";
    /**
     * The HTTP Access-Control-Request-Headers header field name.
     */
    String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
    /**
     * The HTTP Access-Control-Request-Method header field name.
     */
    String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
    /**
     * The HTTP Authorization header field name.
     */
    String AUTHORIZATION = "Authorization";
    /**
     * The HTTP Connection header field name.
     */
    String CONNECTION = "Connection";
    /**
     * The HTTP Cookie header field name.
     */
    String COOKIE = "Cookie";
    /**
     * The HTTP Expect header field name.
     */
    String EXPECT = "Expect";
    /**
     * The HTTP From header field name.
     */
    String FROM = "From";
    /**
     * The HTTP Host header field name.
     */
    String HOST = "Host";
    /**
     * The HTTP If-Match header field name.
     */
    String IF_MATCH = "If-Match";
    /**
     * The HTTP If-Modified-Since header field name.
     */
    String IF_MODIFIED_SINCE = "If-Modified-Since";
    /**
     * The HTTP If-None-Match header field name.
     */
    String IF_NONE_MATCH = "If-None-Match";
    /**
     * The HTTP If-Range header field name.
     */
    String IF_RANGE = "If-Range";
    /**
     * The HTTP If-Unmodified-Since header field name.
     */
    String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    /**
     * The HTTP Last-Event-ID header field name.
     */
    String LAST_EVENT_ID = "Last-Event-ID";
    /**
     * The HTTP Max-Forwards header field name.
     */
    String MAX_FORWARDS = "Max-Forwards";
    /**
     * The HTTP Origin header field name.
     */
    String ORIGIN = "Origin";
    /**
     * The HTTP Proxy-Authorization header field name.
     */
    String PROXY_AUTHORIZATION = "Proxy-Authorization";
    /**
     * The HTTP Range header field name.
     */
    String RANGE = "Range";
    /**
     * The HTTP Referer header field name.
     */
    String REFERER = "Referer";
    /**
     * The HTTP TE header field name.
     */
    String TE = "TE";
    /**
     * The HTTP Upgrade header field name.
     */
    String UPGRADE = "Upgrade";
    /**
     * The HTTP User-Agent header field name.
     */
    String USER_AGENT = "User-Agent";

    // HTTP Response header fields

    /**
     * The HTTP Accept-Ranges header field name.
     */
    String ACCEPT_RANGES = "Accept-Ranges";
    /**
     * The HTTP Access-Control-Allow-Headers header field name.
     */
    String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    /**
     * The HTTP Access-Control-Allow-Methods header field name.
     */
    String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    /**
     * The HTTP Access-Control-Allow-Origin header field name.
     */
    String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    /**
     * The HTTP Access-Control-Allow-Credentials header field name.
     */
    String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    /**
     * The HTTP Access-Control-Expose-Headers header field name.
     */
    String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    /**
     * The HTTP Access-Control-Max-Age header field name.
     */
    String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    /**
     * The HTTP Age header field name.
     */
    String AGE = "Age";
    /**
     * The HTTP Allow header field name.
     */
    String ALLOW = "Allow";
    /**
     * The HTTP Content-Disposition header field name.
     */
    String CONTENT_DISPOSITION = "Content-Disposition";
    /**
     * The HTTP Content-Encoding header field name.
     */
    String CONTENT_ENCODING = "Content-Encoding";
    /**
     * The HTTP Content-Language header field name.
     */
    String CONTENT_LANGUAGE = "Content-Language";
    /**
     * The HTTP Content-Location header field name.
     */
    String CONTENT_LOCATION = "Content-Location";
    /**
     * The HTTP Content-MD5 header field name.
     */
    String CONTENT_MD5 = "Content-MD5";
    /**
     * The HTTP Content-Range header field name.
     */
    String CONTENT_RANGE = "Content-Range";
    /**
     * The HTTP ETag header field name.
     */
    String ETAG = "ETag";
    /**
     * The HTTP Expires header field name.
     */
    String EXPIRES = "Expires";
    /**
     * The HTTP Last-Modified header field name.
     */
    String LAST_MODIFIED = "Last-Modified";
    /**
     * The HTTP Link header field name.
     */
    String LINK = "Link";
    /**
     * The HTTP Location header field name.
     */
    String LOCATION = "Location";
    /**
     * The HTTP P3P header field name. Limited browser support.
     */
    String P3P = "P3P";
    /**
     * The HTTP Proxy-Authenticate header field name.
     */
    String PROXY_AUTHENTICATE = "Proxy-Authenticate";
    /**
     * The HTTP Refresh header field name. Non-standard header supported by most browsers.
     */
    String REFRESH = "Refresh";
    /**
     * The HTTP Retry-After header field name.
     */
    String RETRY_AFTER = "Retry-After";
    /**
     * The HTTP Server header field name.
     */
    String SERVER = "Server";
    /**
     * The HTTP Set-Cookie header field name.
     */
    String SET_COOKIE = "Set-Cookie";
    /**
     * The HTTP Set-Cookie2 header field name.
     */
    String SET_COOKIE2 = "Set-Cookie2";
    /**
     * The HTTP Trailer header field name.
     */
    String TRAILER = "Trailer";
    /**
     * The HTTP Transfer-Encoding header field name.
     */
    String TRANSFER_ENCODING = "Transfer-Encoding";
    /**
     * The HTTP Vary header field name.
     */
    String VARY = "Vary";
    /**
     * The HTTP WWW-Authenticate header field name.
     */
    String WWW_AUTHENTICATE = "WWW-Authenticate";

    // Common, non-standard HTTP header fields

    /**
     * The HTTP DNT header field name.
     */
    String DNT = "DNT";
    /**
     * The HTTP X-Content-Type-Options header field name.
     */
    String X_CONTENT_TYPE_OPTIONS = "X-Content-Type-Options";
    /**
     * The HTTP X-Do-Not-Track header field name.
     */
    String X_DO_NOT_TRACK = "X-Do-Not-Track";
    /**
     * The HTTP X-Forwarded-For header field name.
     */
    String X_FORWARDED_FOR = "X-Forwarded-For";
    /**
     * The HTTP X-Forwarded-Proto header field name.
     */
    String X_FORWARDED_PROTO = "X-Forwarded-Proto";
    /**
     * The HTTP X-Frame-Options header field name.
     */
    String X_FRAME_OPTIONS = "X-Frame-Options";
    /**
     * The HTTP X-Powered-By header field name.
     */
    String X_POWERED_BY = "X-Powered-By";
    /**
     * The HTTP X-Requested-With header field name.
     */
    String X_REQUESTED_WITH = "X-Requested-With";
    /**
     * The HTTP X-User-IP header field name.
     */
    String X_USER_IP = "X-User-IP";

    /**
     * The HTTP X-XSS-Protection header field name.
     */
    String X_XSS_PROTECTION = "X-XSS-Protection";

    /**
     * 相应内容类型：html
     */
    String CONTENT_TYPE_HTML = "text/html;charset=UTF-8";

}
