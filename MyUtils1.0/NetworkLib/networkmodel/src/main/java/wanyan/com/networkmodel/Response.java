package wanyan.com.networkmodel;

import java.util.List;

/**
 * Created by WJS on 2016/7/26.
 */
public class Response
{
    private String result;
    private List<String> cookie;

    public String getResult() {
        return result;
    }

    public List<String> getCookie() {
        return cookie;
    }

    public void setCookie(List<String> cookie) {
        this.cookie = cookie;
    }

    public void setResult(String result) {
        this.result = result;
    }
}