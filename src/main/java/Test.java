import com.github.tsohr.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test implements Runnable {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

  private static final MediaType APPLICATION_JSON = MediaType.parse("application/json");

  private static final String url = "";

  private final static String amount = "11";

  private final static String price = "1";

  private final static String type = "sell-limit";

  private final static String source = "web";

  private final static String symbol = "gnxeth";

  private Long accountId;

  private String hbProToken;

  private OkHttpClient client = new OkHttpClient();

  public Test(Long accountId, String hbProToken) {
    this.accountId = accountId;
    this.hbProToken = hbProToken;
  }

  private void miaosha() {
    String origin = "https://www.huobipro.com";

    String acceptEncoding = "gzip, deflate, br";

    String acceptLanguage = "zh-CN";

    String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";

    String contentType = "application/json;charset=UTF-8";

    String accept = "application/json, text/plain, */*";

    String referer = "https://www.huobipro.com/zh-cn/hsr_usdt/exchange/";

    String authority = "api.huobipro.com";

    //String hbProToken = "ANe_ovZSg_djSuwEZKUCWankSb8Obm5NlEcSTGG5ACMY-uOP2m0-gvjE57ad1qDF";

    Headers.Builder headerBuild = new Headers.Builder();
    headerBuild.add("origin", origin);
    headerBuild.add("accept-encoding", acceptEncoding);
    headerBuild.add("accept-language", acceptLanguage);
    headerBuild.add("user-agent", userAgent);
    headerBuild.add("content-type", contentType);
    headerBuild.add("accept", accept);
    headerBuild.add("referer", referer);
    headerBuild.add("authority", authority);
    headerBuild.add("hb-pro-token", hbProToken);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("amount", amount);
    jsonObject.put("price", price);
    jsonObject.put("type", type);
    jsonObject.put("source", source);
    jsonObject.put("symbol", symbol);
    jsonObject.put("account-id", accountId);

    RequestBody requestBody =  RequestBody.create(APPLICATION_JSON, jsonObject.toString());

    Request request = new Request.Builder().headers(headerBuild.build()).url(url).post(requestBody).build();

    try {
      Response response = client.newCall(request).execute();
      String s = response.body().string();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    Date now = new Date();
    System.out.println("miaosha\t" + accountId + ":\t"+ dateFormat.format(now));
    miaosha();
    now = new Date();
    System.out.println(dateFormat.format(now));
  }


  public static void main(String[] args) throws IOException {

    ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

    Calendar calendar;
    int hours;
    int minutes;
    int seconds;

    while(true) {
      try {
        calendar = Calendar.getInstance();
        hours = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);
        seconds = calendar.get(Calendar.SECOND);

        if (hours == 3) {
          if (minutes == 46 && seconds == 57) {
            break;
          }
        }
        Thread.sleep(10);
      } catch (Throwable e) {

      }
    }

    Date now = new Date();
    System.out.println("miaosha\t" + ":\t"+ dateFormat.format(now));



    Test chrome188 = new Test(1221256L, "ZmN_Wh5p-z8yRvxI9LHtXjnN_BGrBFNVFbOQxiSH-DQY-uOP2m0-gvjE57ad1qDF");
    service.scheduleAtFixedRate(chrome188, 5, 10, TimeUnit.MILLISECONDS);

    //Test fire150 = new Test(100149L, "ANe_ovZSg_djSuwEZKUCWankSb8Obm5NlEcSTGG5ACMY-uOP2m0-gvjE57ad1qDF");
    //service.scheduleAtFixedRate(chrome188, 5, 10, TimeUnit.MILLISECONDS);

  }
}
