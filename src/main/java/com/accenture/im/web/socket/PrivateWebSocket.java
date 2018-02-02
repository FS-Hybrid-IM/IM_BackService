package com.accenture.im.web.socket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@ServerEndpoint(value = "/websocket")
@Component
public class PrivateWebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, PrivateWebSocket> webSocketSet = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.put(this.session.getId(), this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage(session.getId() + "连接成功");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	String[] arlMessage = StringUtils.split(message, "$$$");
        System.out.println("来自客户端的消息:" + arlMessage[0]);
        if (StringUtils.isEmpty(arlMessage[1])) {
        	webSocketSet.keySet().forEach(key -> {
    	        //群发消息
    	        PrivateWebSocket item = webSocketSet.get(key);
    	        try {
    	            item.sendMessage(arlMessage[0]);
    	        } catch (IOException e) {
    	        }
        	});
        } else {
	        //群发消息
	        PrivateWebSocket item = webSocketSet.get(arlMessage[1]);
	        try {
	            item.sendMessage(arlMessage[0]);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
    	String[] arlMessage = StringUtils.split(message, "$$$");
        System.out.println("来自客户端的消息:" + arlMessage[0]);
        if (StringUtils.isEmpty(arlMessage[1])) {
        	webSocketSet.keySet().forEach(key -> {
    	        //群发消息
    	        PrivateWebSocket item = webSocketSet.get(key);
    	        try {
    	            item.sendMessage(arlMessage[0]);
    	        } catch (IOException e) {
    	        }
        	});
        } else {
	        //群发消息
	        PrivateWebSocket item = webSocketSet.get(arlMessage[1]);
	        try {
	            item.sendMessage(arlMessage[0]);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        PrivateWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        PrivateWebSocket.onlineCount--;
    }
}