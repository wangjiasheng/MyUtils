package wanyan.com.networkmodel.util;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import wanyan.com.networkmodel.Response;

/** 
 * Http����Ĺ����� 
 *  
 * @author zhy 
 *  
 */  
public class HttpUtils  
{  
  
    private static final int TIMEOUT_IN_MILLIONS = 5000;  
    public static String doGet(String urlStr)
    {  
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try  
        {  
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);  
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);  
            conn.setRequestMethod("GET");  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");
            if (conn.getResponseCode() == 200)
            {  
                is = conn.getInputStream();  
                baos = new ByteArrayOutputStream();
                int len = -1;  
                byte[] buf = new byte[128];  
  
                while ((len = is.read(buf)) != -1)  
                {  
                    baos.write(buf, 0, len);  
                }  
                baos.flush();  
                return baos.toString();  
            } else  
            {  
                throw new RuntimeException(" responseCode is not 200 ... ");
            }  
  
        } catch (Exception e)
        {  
            e.printStackTrace();  
        } finally  
        {  
            try  
            {  
                if (is != null)  
                    is.close();  
            } catch (IOException e)
            {  
            }  
            try  
            {  
                if (baos != null)  
                    baos.close();  
            } catch (IOException e)
            {  
            }  
            conn.disconnect();  
        }  
          
        return null ;  
  
    }  
  
    public static Response doPost(AsyncTask task,String url, String param, List<String> sCookie, int reptcont)
    {
        Log.i("wjs","re:"+reptcont);
        PrintWriter out = null;
        BufferedReader in = null;
        try
        {  
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");  
            conn.setUseCaches(false);  
            conn.setDoOutput(true);
            conn.setDoInput(true);
            if(sCookie!=null)
            {
                for(int i=0;i<sCookie.size();i++)
                {
                    if(i==0)
                    {
                        conn.addRequestProperty("Cookie", sCookie.get(0));
                    }
                    else
                    {
                        conn.setRequestProperty("Cookie", sCookie.get(i));
                    }
                }
            }
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);  
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);  
  
            if (param != null && !param.trim().equals(""))  
            {  
                out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.flush();
            }  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            StringBuilder builder=new StringBuilder();
            while ((line = in.readLine()) != null)
            {
                builder.append(line);
            }
            Map<String,List<String>> files=conn.getHeaderFields();
            List<String> s= files.get("Set-Cookie");
            if(s!=null)
            {
                sCookie = s;
            }
            if(StringUtils.isNotNull(builder.toString()))
            {
                Response response=new Response();
                response.setResult(builder.toString());
                response.setCookie(sCookie);
                return response;
            }
        }
        catch (Exception e)
        {  
            e.printStackTrace();  
        }  
        finally
        {  
            try  
            {  
                if (out != null)  
                {  
                    out.close();  
                }  
                if (in != null)  
                {  
                    in.close();  
                }  
            } catch (IOException ex)
            {  
                ex.printStackTrace();  
            }  
        }
        if(reptcont>0&&!task.isCancelled())
        {
            return doPost(task,url,param,sCookie,--reptcont);
        }
        else
        {
            Response response=new Response();
            response.setResult("");
            response.setCookie(sCookie);
            return response;
        }
    }
}  