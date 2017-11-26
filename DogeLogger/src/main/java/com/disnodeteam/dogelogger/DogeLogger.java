package com.disnodeteam.dogelogger;

/**
 * Created by Alex on 11/12/2017.
 */

import android.util.Log;

import org.java_websocket.WebSocket;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DogeLogger{

    public static WebsocketServer server = null;

    public class LogEvent{
        public String timeStamp;
        public String EventName;
    }

    public static class LogData{
        public String Key;
        public String Value;
        public LogData(String key, String value){
            Key = key;
            Value = value;
        }

        public String ConvertToPacket(){
            return "LOG_DATA|"+ Key + "=" + Value + ";";
        }
    }

    private static List<LogData> LogValues = new ArrayList<>();
    private static List<LogEvent> LogEvents = new ArrayList<>();

    public static void StartServer(){

        InetSocketAddress inetSockAddress = new InetSocketAddress(38301);
        server = new WebsocketServer(inetSockAddress);

        server.start();
        LogVar("Server", "Started");
    }

    public static void LogVar(String key, String val){

        LogData newData = new LogData(key,val);
        if(dataKeyIndex(key) == -1){
            LogValues.add(newData);
        }

        SendData();
    }

    public static void SendData(){
        String finalPacket = "";

        Log.d("WS-SERVER", "Sending: " + finalPacket);
        server.SendString(finalPacket);
    }

    // Helpers

    private static int dataKeyIndex(String key){
        for(int i=0;i<LogValues.size();i++){
            if(LogValues.get(i).Key == key){
                return i;
            }
        }

        return -1;
    }
}
