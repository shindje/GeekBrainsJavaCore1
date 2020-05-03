package hw8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartNewGameWindow extends JFrame {
    private static final int WIN_HEIGHT = 500;
    private static final int WIN_WIDTH  = 400;
    private static final int WIN_POS_X  = 650;
    private static final int WIN_POS_Y  = 250;
    private static final int MIN_FIELD_SIZE  = 3;
    private static final int MAX_FIELD_SIZE  = 10;
    private static final int MIN_WIN_LENGTH = 3;

    private JRadioButton jrbHumVsAi;
    private JRadioButton jrbHumVsHum;
    private ButtonGroup gameMode;

    private JSlider jsFieldSizeX;
    private JSlider jsFieldSizeY;
    private JSlider jsWinLength;

    private Color fieldColor = Color.orange;
    private JButton fieldColorButton = new JButton("Choose field color");
    private Color userColor = Color.yellow;
    private JButton userColorButton = new JButton("Choose player 1 color");
    private Color aiColor = Color.green;
    private JButton aiColorButton = new JButton("Choose ai/player 2 color");

    private GameWindow gameWindow;

    public StartNewGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBounds(WIN_POS_X,WIN_POS_Y,WIN_WIDTH,WIN_HEIGHT);
        setTitle("TicTacToe");
        setResizable(false);
        setLayout(new GridLayout(13,1));

        //gameMode
        add(new JLabel("Choose gameMode:"));
        jrbHumVsAi = new JRadioButton("Hum vs Ai",true);
        jrbHumVsHum = new JRadioButton("Hum vs Hum");
        gameMode = new ButtonGroup();
        gameMode.add(jrbHumVsAi);
        gameMode.add(jrbHumVsHum);
        add(jrbHumVsAi);
        add(jrbHumVsHum);

        //choose colors
        fieldColorButton.setBackground(fieldColor);
        fieldColorButton.addActionListener(e -> {
            fieldColor = shooseColor(fieldColorButton, fieldColor);
        });
        add(fieldColorButton);

        userColorButton.setBackground(userColor);
        userColorButton.addActionListener(e -> {
            userColor = shooseColor(userColorButton, userColor);
        });
        add(userColorButton);

        aiColorButton.setBackground(aiColor);
        aiColorButton.addActionListener(e -> {
            aiColor = shooseColor(aiColorButton, aiColor);
        });
        add(aiColorButton);

        //size
        //dots to win
        add(new JLabel("Choose field horizontal size:"));
        jsFieldSizeX = new JSlider(MIN_FIELD_SIZE,MAX_FIELD_SIZE,MIN_FIELD_SIZE);
        add(jsFieldSizeX);
        jsFieldSizeX.setMajorTickSpacing(1);
        jsFieldSizeX.setPaintLabels(true);
        jsFieldSizeX.setPaintTicks(true);

        jsFieldSizeX.addChangeListener(e->{
            int fieldSizeX = jsFieldSizeX.getValue();
            int fieldSizeY = jsFieldSizeY.getValue();
            jsWinLength.setMaximum(Math.min(fieldSizeX, fieldSizeY));
        });

        add(new JLabel("Choose field vertical size:"));
        jsFieldSizeY = new JSlider(MIN_FIELD_SIZE,MAX_FIELD_SIZE,MIN_FIELD_SIZE);
        add(jsFieldSizeY);
        jsFieldSizeY.setMajorTickSpacing(1);
        jsFieldSizeY.setPaintLabels(true);
        jsFieldSizeY.setPaintTicks(true);

        jsFieldSizeY.addChangeListener(e->{
            int fieldSizeX = jsFieldSizeX.getValue();
            int fieldSizeY = jsFieldSizeY.getValue();
            jsWinLength.setMaximum(Math.min(fieldSizeX, fieldSizeY));
        });

        add(new JLabel("Choose winning length:"));
        jsWinLength = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_WIN_LENGTH);
        add(jsWinLength);
        jsWinLength.setMajorTickSpacing(1);
        jsWinLength.setPaintLabels(true);
        jsWinLength.setPaintTicks(true);


        //buttonOk
        JButton btnStartGame = new JButton("Start a game");
        add(btnStartGame);
        btnStartGame.addActionListener(e->{
            btnStartGameClick();
        });

        setVisible(false);
    }

    private Color shooseColor(JButton button, Color color) {
        Color chooseColor = JColorChooser.showDialog(gameWindow,
                "Choose color", color);
        if(chooseColor != null){
            button.setBackground(chooseColor);
            return chooseColor;
        } else
            return color;
    }

    private void btnStartGameClick(){
        setVisible(false);
        int gameMode;
        if(jrbHumVsAi.isSelected()){
            gameMode = BattleMap.MODE_H_V_A;
        } else {
            gameMode = BattleMap.MODE_H_V_H;
        }
        int fieldSizeX = jsFieldSizeX.getValue();
        int fieldSizeY = jsFieldSizeY.getValue();
        int winLength = jsWinLength.getValue();

        Logic.gameMode = gameMode;
        Logic.SIZE_X = fieldSizeX;
        Logic.SIZE_Y = fieldSizeY;
        Logic.DOTS_TO_WIN = winLength;
        Logic.initMap();
        Logic.printMap();
        Logic.gameFinished = false;

        gameWindow.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLength, fieldColor, userColor, aiColor);

    }
}
