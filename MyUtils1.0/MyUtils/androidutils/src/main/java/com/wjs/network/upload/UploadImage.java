package com.wjs.network.upload;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UploadImage {
    URL url;
    String sCookie;
    HttpURLConnection conn;
    String boundary = "--------httppost123";
    Map<String, String> textParams = new HashMap<String, String>();
    Map<String, File> fileparams = new HashMap<String, File>();
    DataOutputStream ds;
  
    public UploadImage(String url) throws Exception {
        this.url = new URL(url);
    }  
    //重新设置要请求的服务器地址，即上传文件的地址。  
    public void setUrl(String url) throws Exception {
        this.url = new URL(url);
    }  
    public void setCookie(String cookie)
    {
    	this.sCookie=cookie;
    }
    //增加一个普通字符串数据到form表单数据中  
    public void addTextParameter(String name, String value) {
        textParams.put(name, value);  
    }  
    //增加一个文件到form表单数据中  
    public void addFileParameter(String name, File value) {
        fileparams.put(name, value);  
    }  
    // 清空所有已添加的form表单数据  
    public void clearAllParameters() {  
        textParams.clear();  
        fileparams.clear();  
    }  
    // 发送数据到服务器，返回一个字节包含服务器的返回结果的数组  
    public byte[] send() throws Exception {
        initConnection();  
        try {  
            conn.connect();  
        } catch (SocketTimeoutException e) {
            // something  
            throw new RuntimeException();
        }  
        ds = new DataOutputStream(conn.getOutputStream());
        writeFileParams();  
        writeStringParams();  
        paramsEnd();  
        InputStream in = conn.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int b;  
        while ((b = in.read()) != -1) {  
            out.write(b);  
        }  
        conn.disconnect();  
        return out.toByteArray();  
    }  
    //文件上传的connection的一些必须设置  
    private void initConnection() throws Exception {
        conn = (HttpURLConnection) this.url.openConnection();
        conn.setDoOutput(true);  
        conn.setUseCaches(false);  
        conn.setConnectTimeout(10000); //连接超时为10秒  
        conn.setRequestMethod("POST");  
        conn.setRequestProperty("Content-Type",  
                "multipart/form-data; boundary=" + boundary);  
        if(sCookie!=null)
        {
        	conn.setRequestProperty("Cookie", sCookie);
        }
    }  
    //普通字符串数据  
    private void writeStringParams() throws Exception {
        Set<String> keySet = textParams.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
            String name = it.next();
            String value = textParams.get(name);
            ds.writeBytes("--" + boundary + "\r\n");  
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name  
                    + "\"\r\n");  
            ds.writeBytes("\r\n");  
            ds.writeBytes(encode(value) + "\r\n");  
        }  
    }  
    //文件数据  
    private void writeFileParams() throws Exception {
        Set<String> keySet = fileparams.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
            String name = it.next();
            File value = fileparams.get(name);
            ds.writeBytes("--" + boundary + "\r\n");  
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name
                    + "\"; filename=\"" + encode(value.getName()) + "\"\r\n");
            ds.writeBytes("\r\n");
            ds.write(getBytes(value));  
            ds.writeBytes("\r\n");  
        }  
    }
    //把文件转换成字节数组
    private byte[] getBytes(File f) throws Exception {
        FileInputStream in = new FileInputStream(f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];  
        int n;  
        while ((n = in.read(b)) != -1) {  
            out.write(b, 0, n);  
        }  
        in.close();  
        return out.toByteArray();  
    }  
    //添加结尾数据  
    private void paramsEnd() throws Exception {
        ds.writeBytes("--" + boundary + "--" + "\r\n");  
        ds.writeBytes("\r\n");  
    }  
    // 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码  
    private String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }  
    public static void main(String[] args) throws Exception {
        UploadImage u = new UploadImage("http://li.itingwang.com:8004/tools/upload_ajax.ashx?action=InsurancePictureUpload");
        u.setCookie("");
        u.addFileParameter("filedata1", new File("E://d74b59b10c8f69861d37f169362e1636.jpg"));
        u.addFileParameter("filedata2", new File("E://device-2016-05-31-174143.png"));
        u.addTextParameter("text", "中文");  
        byte[] b = u.send();  
        String result = new String(b);
        System.out.println(result);
    }
}