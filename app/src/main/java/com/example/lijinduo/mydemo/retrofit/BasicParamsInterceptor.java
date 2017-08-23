package com.example.lijinduo.mydemo.retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/23
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BasicParamsInterceptor implements Interceptor {
    /** headerLines 参数List */
    private List<String> headerLinesList = new ArrayList<>();
    /** header 参数Map */
    private Map<String, String> headerParamsMap = new HashMap<>();
    /** url 参数Map */
    private Map<String, String> queryParamsMap  = new HashMap<>();
    /** body 参数Map */
    private Map<String, String> paramsMap       = new HashMap<>();
    /** 动态参数加入 */
    private IBasicDynamic iBasicDynamic;

    private BasicParamsInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request        = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        ///////////////////////////////////////////////////////////////////////////
        // process header params inject
        ///////////////////////////////////////////////////////////////////////////
        Headers.Builder headerBuilder = request.headers().newBuilder();
        if (headerParamsMap.size() > 0) {
            Iterator iterator = headerParamsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (headerLinesList.size() > 0) {
            for (String line : headerLinesList) {
                headerBuilder.add(line);
            }
        }
        requestBuilder.headers(headerBuilder.build());

        ///////////////////////////////////////////////////////////////////////////
        // process queryParams inject whatever it's GET or POST
        ///////////////////////////////////////////////////////////////////////////
        if (queryParamsMap.size() > 0) {
            injectParamsIntoUrl(request, requestBuilder, queryParamsMap);
        }

        ///////////////////////////////////////////////////////////////////////////
        // process post body inject
        ///////////////////////////////////////////////////////////////////////////
        if (null != iBasicDynamic) {
            iBasicDynamic.dynamicParams(paramsMap);
        }
        if (request.method().equals("POST")) {
            if (null != request.body() && request.body() instanceof MultipartBody) {
                MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
                multipartBodyBuilder.setType(MultipartBody.FORM);
                if (paramsMap.size() > 0) {
                    Iterator iterator = paramsMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        multipartBodyBuilder.addFormDataPart((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                for (MultipartBody.Part part : ((MultipartBody) request.body()).parts()) {
                    multipartBodyBuilder.addPart(part);
                }
                requestBuilder.post(multipartBodyBuilder.build());
            } else {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                if (paramsMap.size() > 0) {
                    Iterator iterator = paramsMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        formBodyBuilder.add((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                FormBody formBody       = formBodyBuilder.build();
                String   postBodyString = bodyToString(request.body());
                postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
                requestBuilder.post(RequestBody.create(formBody.contentType(), postBodyString));
            }
        } else {
            // if can't inject into body, then inject into url
            injectParamsIntoUrl(request, requestBuilder, paramsMap);
        }
        request = requestBuilder.build();
        return chain.proceed(request);
    }

    // func to inject params into url
    private void injectParamsIntoUrl(Request request, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        if (paramsMap.size() > 0) {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }
        requestBuilder.url(httpUrlBuilder.build());
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null)
                request.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (IOException e) {
            return "did not work";
        }
    }

    public static class Builder {
        BasicParamsInterceptor interceptor;

        public Builder() {
            interceptor = new BasicParamsInterceptor();
        }

        public Builder addParam(String key, String value) {
            interceptor.paramsMap.put(key, value);
            return this;
        }

        public Builder addParamsMap(Map<String, String> paramsMap) {
            interceptor.paramsMap.putAll(paramsMap);
            return this;
        }

        public Builder addHeaderParam(String key, String value) {
            interceptor.headerParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParamsMap(Map<String, String> headerParamsMap) {
            interceptor.headerParamsMap.putAll(headerParamsMap);
            return this;
        }

        public Builder addHeaderLine(String headerLine) {
            int index = headerLine.indexOf(":");
            if (index == -1) {
                throw new IllegalArgumentException("Unexpected header: " + headerLine);
            }
            interceptor.headerLinesList.add(headerLine);
            return this;
        }

        public Builder addHeaderLinesList(List<String> headerLinesList) {
            for (String headerLine : headerLinesList) {
                int index = headerLine.indexOf(":");
                if (index == -1) {
                    throw new IllegalArgumentException("Unexpected header: " + headerLine);
                }
                interceptor.headerLinesList.add(headerLine);
            }
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            interceptor.queryParamsMap.put(key, value);
            return this;
        }

        public Builder addQueryParamsMap(Map<String, String> queryParamsMap) {
            interceptor.queryParamsMap.putAll(queryParamsMap);
            return this;
        }

        public BasicParamsInterceptor build() {
            return interceptor;
        }
    }

    /**
     * @return 获取 query 和 post 参数map
     */
    public Map<String, String> getParamsMap() {
        Map<String, String> map = new HashMap<>();
        map.putAll(queryParamsMap);
        map.putAll(paramsMap);
        return map;
    }

    public IBasicDynamic getIBasicDynamic() {
        return iBasicDynamic;
    }

    public void setIBasicDynamic(IBasicDynamic iBasicDynamic) {
        this.iBasicDynamic = iBasicDynamic;
    }
}
