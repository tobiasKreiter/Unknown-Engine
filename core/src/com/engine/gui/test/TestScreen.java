package com.engine.gui.test;

import com.badlogic.gdx.graphics.Color;
import com.engine.gui.component.*;
import com.engine.gui.component.container.layout.table_layout.TableLayout;
import com.engine.gui.component.container.window.Window;
import com.engine.gui.graphics.Graphics;
import com.engine.gui.screen.Frame;
import com.engine.gui.screen.layer.ComponentLayer;
import com.engine.network.service.implementation.NetworkInterface;
import com.engine.network.service.implementation.NetworkListener;
import com.engine.network.service.message.ErrorMessage;
import com.engine.network.service.message.Message;

import java.util.ArrayList;

/**
 * Created by tobias on 22.07.2014.
 */
public class TestScreen extends Frame implements NetworkInterface {

    private float angle = 0;
    private float angleWODelta = 0;
    private float trans = 0;
    private boolean up = true;

    private Label label;
    private ArrayList<Label> labels = new ArrayList<Label>();

    private TableLayout tableLayout;

    private RadioButton radioButton = new RadioButton("Test-Button", 300, 100);

    int testInt = 0;

    private NetworkListener networkListener;

    private final int GAME_ID = 1;

    private DevelopmentLayer developmentLayer;

    private TextBox textBox;

    public TestScreen() {

        Graphics.initGraphics(); //Hola3

        initNetwork();

        developmentLayer = new DevelopmentLayer(this);

        ComponentLayer layer = new ComponentLayer(this);
        Window win = new Window(50, 100, 300, 300);
        Button btn = new Button("Test", 300, 400);
        btn.addInputListener(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                System.out.println("Geht");
                return false;
            }
        });
        Label label = new Label("Test2", 600, 600 - btn.getHeight());
        win.addChild(btn);
        win.addChild(label);
        layer.addComponent(win);

        TextField field = new TextField(400, 400,200,200);
        layer.addComponent(field);

        DropdownButton ddbtn = new DropdownButton(700, 300, layer);
        ddbtn.test("Swag");
        ddbtn.test("123");
        ddbtn.test("321");
        ddbtn.test("Swag");
        ddbtn.test("123");
        layer.addComponent(ddbtn);

        Label testLabel = new Label("asdasd", 700, 430);
        layer.addComponent(testLabel);

        addLayer(layer);
        addLayer(developmentLayer);

    }

    private void initNetwork() {
        networkListener = new NetworkListener(GAME_ID, this);
        try {
            networkListener.connect();
        } catch (Exception e) {
            System.out.println("Connection to server failed. Please contact Master Tobi!");
        }
    }


    @Override
    public void onNetworkResponse(Message message) {
        if (message.getType() == Message.EMPTY) {
            developmentLayer.updateUpdateStatus(true);
        }
    }

    @Override
    public void onNetworkFailure(ErrorMessage error) {
        developmentLayer.updateUpdateStatus(false);
    }

    @Override
    public String getUniqueID() {
        return null;
    }
}
