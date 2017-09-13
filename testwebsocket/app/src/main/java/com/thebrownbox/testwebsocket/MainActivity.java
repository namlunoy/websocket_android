package com.thebrownbox.testwebsocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://localhost:8080/Websocket_Server/server").build();
        HelloWebSocketListener listener = new HelloWebSocketListener();
        WebSocket socket = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    private void Log(String message){
        Log.d("XXX", message);
    }


    //============================================

    private final class HelloWebSocketListener extends WebSocketListener{
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send("Hello from android!");
            Log("Connected!");
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            Log("Message: " + bytes.hex());
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Log("Message: "+text);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            Log("CLosed!");
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            Log(t.getMessage());
        }
    }
}
