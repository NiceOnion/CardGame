package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketAdapter;
import com.github.czyzby.websocket.WebSocketListener;
import com.github.czyzby.websocket.WebSockets;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.sun.source.tree.CaseTree;

import java.net.Socket;

public class MyGdxGame extends Game {

	public static MyGdxGame INSTANCE;
	private int screenWidth, screenHeight;
	private OrthographicCamera camera;
	private WebSocket socket;
	private GameScreen gameScreen;


	public MyGdxGame(){
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.screenHeight = Gdx.graphics.getHeight();
		this.screenWidth = Gdx.graphics.getWidth();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false,screenWidth,screenHeight);
		connectSocket();
		setScreen(gameScreen = new GameScreen(camera));
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void connectSocket()
	{
		try {
			socket = WebSockets.newSocket(WebSockets.toWebSocketUrl("localhost", 8080));
			socket.setSendGracefully(true);
			socket.addListener(new WebSocketListener() {

				@Override
				public boolean onOpen(WebSocket webSocket) {
					Gdx.app.log("WebSockets", "Connected");
					return FULLY_HANDLED;
				}

				@Override
				public boolean onClose(WebSocket webSocket, int closeCode, String reason) {
					Gdx.app.log("WebSockets", "Disconnected");
					return FULLY_HANDLED;
				}

				@Override
				public boolean onMessage(WebSocket webSocket, String packet) {
					try {
						Gdx.app.log("WebSockets", "EventCommand recieved");
						String[] splitted = packet.split(";");
						String type = splitted[0];
						String Message = splitted[1];
						if (type.equals("ClientID")) {
							ClientID(Message);
						}
						if (type.equals("PlayerJoined")) {
							PlayerJoined(Message);
						}
						return FULLY_HANDLED;
					} catch (Exception e) {
						Gdx.app.log("WebSockets", e.getMessage());
						return NOT_HANDLED;
					}
				}

				@Override
				public boolean onMessage(WebSocket webSocket, byte[] packet) {
					return false;
				}

				@Override
				public boolean onError(WebSocket webSocket, Throwable error) {
					return false;
				}
			});
			socket.connect();
		}
		catch(Exception e){
		System.out.println(e);
		}
	}

	public void ClientID(String ID){
		Gdx.app.log("WebSockets", "this Clients ID is " + ID);
	}

	public void PlayerJoined(String ID){
		Gdx.app.log("WebSockets", "A new player with the id " + ID + " has joined!");
		//gameScreen.Function
	}



	};
