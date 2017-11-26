package com.disnodeteam.dogelogger;

import android.os.Debug;
import android.util.Log;

import java.net.InetSocketAddress;
import java.security.PublicKey;
import java.util.logging.Logger;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;





public class WebsocketServer extends WebSocketServer
{
    public WebSocket currentSocket = null;
    public WebsocketServer(InetSocketAddress address) {
        super(address);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onError(WebSocket arg0, Exception arg1) {
        // TODO Auto-generated method stub
        Log.e("WS-SERVER", arg1.getMessage());

    }

    @Override
    public void onStart() {
        Log.d("WS-SERVER", "Started");
    }

    @Override
    public void onMessage(WebSocket arg0, String arg1) {
        // TODO Auto-generated method stub
        Log.i("WS-SERVER", "MESSAGE:" + arg1);

    }

    @Override
    public void onOpen(WebSocket arg0, ClientHandshake arg1) {
        // TODO Auto-generated method stub
        if(currentSocket != null && currentSocket.getRemoteSocketAddress() != null){
            Log.i("WS-SERVER", "Closing Previous Connection: " + currentSocket.getRemoteSocketAddress());
            currentSocket.send("Other Connection Established.");
            currentSocket.close();
            currentSocket = null;
        }
        Log.d("WS-SERVER", "Connection: " + arg0.getRemoteSocketAddress());
        arg0.send("WELCOME");
        currentSocket = arg0;
        DogeLogger.SendData();
    }



    public void SendString(String toSend){
        if(currentSocket !=null){
            currentSocket.send(toSend);
        }
    }
}