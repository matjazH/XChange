package org.knowm.xchange.gemini.service.streaming;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.gemini.dto.marketdata.GeminiStreamingResponse;
import org.knowm.xchange.service.streaming.ExchangeEvent;
import org.knowm.xchange.service.streaming.ExchangeStreamingConfiguration;
import org.knowm.xchange.service.streaming.StreamingExchangeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by tabtrader on 03/10/2016.
 */
public class GeminiStreamingService implements StreamingExchangeService {

  private WebSocket webSocket;
  private ExchangeStreamingConfiguration configuration;
  private ObjectMapper objectMapper = new ObjectMapper();
  // private final ExchangeEventListener exchangeEventListener;
  private final BlockingQueue<ExchangeEvent> consumerEventQueue = new LinkedBlockingQueue<>();

  public GeminiStreamingService(ExchangeStreamingConfiguration configuration) {

    this.configuration = configuration;
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  private OkHttpClient createOkHttpClient() {
    OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build();
    return client;
  }

  private Request createRequest(String pair) {
    return new Request.Builder()
        .url("wss://api.gemini.com/v1/marketdata/" + pair + "?heartbeat=true")
        .build();
  }

  @Override
  public void connect() {

    OkHttpClient okHttpClient = createOkHttpClient();
    Request request = createRequest("BTCUSD");

    WebSocketCall call = WebSocketCall.create(okHttpClient, request);
    call.enqueue(new WebSocketListener() {

      @Override
      public void onOpen(WebSocket webSocket, Response response) {
        GeminiStreamingService.this.webSocket = webSocket;
        System.out.println("onOpen " + response);
      }

      @Override
      public void onFailure(IOException e, Response response) {
        System.out.println("onFailure " + e.getLocalizedMessage());
      }

      @Override
      public void onMessage(ResponseBody responseBody) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
        GeminiStreamingResponse response = fromJson(reader);

        System.out.println(response);
      }

      @Override
      public void onPong(Buffer payload) {
        System.out.println("onPong " + payload);
      }

      @Override
      public void onClose(int code, String reason){
        System.out.println("onClose " + code + " " + reason);
      }
    });
  }

  @Override
  public void disconnect() {
    if (webSocket != null) {
      try {
        webSocket.close(-1, null);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public ExchangeEvent getNextEvent() throws InterruptedException {
    return null;
  }

  @Override
  public int countEventsAvailable() {
    return 0;
  }

  @Override
  public void send(String msg) {
    throw new NotAvailableFromExchangeException();
  }

  @Override
  public org.java_websocket.WebSocket.READYSTATE getWebSocketStatus() {
    return null;
  }

  private GeminiStreamingResponse fromJson(BufferedReader reader) {
    try {
      GeminiStreamingResponse event = objectMapper.readValue(reader, GeminiStreamingResponse.class);
      return event;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void main(String[] args) {

    GeminiStreamingService wsTest = new GeminiStreamingService(null);
    wsTest.connect();
  }

}
