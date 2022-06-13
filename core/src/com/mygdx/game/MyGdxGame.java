package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.WebSocketAdapter;
import com.github.czyzby.websocket.WebSocketListener;
import com.github.czyzby.websocket.WebSockets;
import com.mygdx.game.Object.PlayerAI;
import com.mygdx.game.Object.PlayerPaddle;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.sun.source.tree.CaseTree;

import java.io.FileWriter;
import java.net.Socket;
import java.util.Arrays;

import static java.lang.Float.parseFloat;

public class MyGdxGame extends Game {

	public static MyGdxGame INSTANCE;
	private int screenWidth, screenHeight;
	private OrthographicCamera camera;
	private WebSocket socket;
	private GameScreen gameScreen;
	private final float UPDATE_TIME = 1/60f;
	private float timer;



	public MyGdxGame(){
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.screenHeight = Gdx.graphics.getHeight();
		this.screenWidth = Gdx.graphics.getWidth();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false,screenWidth,screenHeight);
		gameScreen = new GameScreen(camera);
		connectSocket();
		setScreen(gameScreen);
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
						if(type.equals("PlayerLeft")){
							PlayerLeft(Message);
						}
						if(type.equals("UpdateOpponent")){
							UpdateOpponent(webSocket, Message);
						}
						return FULLY_HANDLED;
					} catch (Exception e) {
						Gdx.app.log("WebSockets", e.getMessage());
						return NOT_HANDLED;
					}
				}

				@Override
				public boolean onMessage(WebSocket webSocket, byte[] packet) {
					Gdx.app.log("WebSockets", "EventCommand received with byte packet!");
					GetPlayers(webSocket, Arrays.toString(packet));
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
		gameScreen.opponent.put(ID, new PlayerAI( MyGdxGame.INSTANCE.getScreenWidth() - 16, (float)MyGdxGame.INSTANCE.getScreenHeight()/2, gameScreen));
	}

	public void PlayerLeft(String ID){
		Gdx.app.log("WebSockets", "A new player with the id " + ID + " has joined!");
		gameScreen.opponent.remove(ID);
	}

	public void GetPlayers(WebSocket webSocket, String packet)
	{
		String[] splitted = packet.split(",");
		String ID = splitted[0];
		float X = parseFloat(splitted[1]);
		float Y = parseFloat(splitted[2]);
		gameScreen.opponent.put(ID, new PlayerAI(X, Y, gameScreen));
	}

	public void UpdateServer(float dt){
		timer += dt;
		if(timer > UPDATE_TIME && gameScreen.getPlayer() != null && gameScreen.getPlayer().hasMoved()){
			String data = "PlayerMoved," + gameScreen.getPlayer().getX() + "," + gameScreen.getPlayer().getY();
			socket.send(data);
		}
	}

	public void UpdateOpponent(WebSocket socket, String Packet){
		String[] splitted = Packet.split(",");
		String ID = splitted[0];
		double X = Double.parseDouble(splitted[1]);
		double Y = Double.parseDouble(splitted[2]);
		if(gameScreen.opponent.get(ID) != null){
			gameScreen.opponent.get(ID).setPosition((float) X, (float) Y);
		}
	}
}
