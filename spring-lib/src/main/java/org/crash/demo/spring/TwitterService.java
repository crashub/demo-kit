package org.crash.demo.spring;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 */
public class TwitterService {

  public TwitterService() {
  }

  public String search(String keyword) throws IOException {
    HttpClient client = new DefaultHttpClient();
    HttpGet get = new HttpGet((new StringBuilder()).append("http://search.twitter.com/search.json?q=").append(keyword).toString());
    HttpResponse response = client.execute(get);
    return EntityUtils.toString(response.getEntity());
  }

}