package hw8;

import hw7.calculator.Calculator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class BattleMap extends JPanel {
    private GameWindow gameWindow;

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;

    private int cellHeight;
    private int cellWidth;

    private Color userColor;
    private Color aiColor;

    private char nextMove;

    private boolean isInit = false;
    private int result;
    private String resultImage;

    private int panelWidth;
    private int panelHeight;

    public BattleMap(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }

    private void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if(!Logic.gameFinished){
            result = Logic.setHumanXY(cellX, cellY, nextMove);
            if (result != Logic.INVALID_MOVE) {
                System.out.println(cellX + " " + cellY);
                repaint();
                if (Logic.gameMode == MODE_H_V_H) {
                    nextMove = (nextMove == Logic.DOT_X)? Logic.DOT_O: Logic.DOT_X;
                }

                if(Logic.gameFinished) {
                    String resultText = "";
                    String img = null;
                    if (result == Logic.AI_WINS && Logic.gameMode == MODE_H_V_A) {
                        img = "src/hw8/aiwins.jpg";
                        resultText = "AI wins!";
                    }

                    if (result == Logic.HUMAN_WINS || (result == Logic.AI_WINS && Logic.gameMode == MODE_H_V_H)) {
                        img = "src/hw8/youwin.jpg";
                        if (result == Logic.HUMAN_WINS) {
                            resultText = "Player 1 wins!";
                        } else {
                            resultText = "Player 2 wins!";
                        }
                    }

                    if (result == Logic.DRAW) {
                        img = "src/hw8/draw.jpg";
                        resultText = "Draw";
                    }

                    JOptionPane.showMessageDialog(this,
                            resultText,
                            "Game over",
                            JOptionPane.INFORMATION_MESSAGE);

                    resultImage = img;
                    repaint();
                }
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isInit) {
            drawImage("src/hw8/main.jpg", g);
            return;
        } else {
            if (resultImage != null) {
                drawImage(resultImage, g);
            } else {
                render(g);
            }
        }
    }

    private void render(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setStroke(new BasicStroke(5));

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < Logic.SIZE_X ; i++) {
            for (int j = 0; j < Logic.SIZE_Y; j++) {
                if(Logic.map[i][j]==Logic.DOT_O){
                    drawO(g,i,j);
                }
                if(Logic.map[i][j]==Logic.DOT_X){
                    drawX(g,i,j);
                }
            }
        }

        //draw win line
        if (Logic.gameFinished) {
            if (result != Logic.DRAW) {
                g2D.setStroke(new BasicStroke(15));

                g.setColor(Color.red);
                g.drawLine(Logic.winLine[0] * cellWidth + cellWidth/2, Logic.winLine[1] * cellHeight + cellHeight/2,
                        Logic.winLine[2] * cellWidth + cellWidth/2, Logic.winLine[3] * cellHeight + cellHeight/2);
            }
            return;
        }
    }

    private void drawImage(String fileName, Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File(fileName)), 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawO(Graphics g, int cellX, int cellY){
        Graphics2D g2D = (Graphics2D)g;
        g.setColor(aiColor);
        g.drawOval(cellX*cellWidth , cellY*cellHeight, cellWidth,cellHeight );

    }
    private void drawX(Graphics g, int cellX, int cellY){
        g.setColor(userColor);
        g.drawLine(cellX*cellWidth , cellY*cellHeight,
                (cellX+1)*cellWidth , (cellY+1)*cellHeight );
        g.drawLine(cellX*cellWidth , (cellY+1)*cellHeight,
                (cellX+1)*cellWidth ,  cellY*cellHeight);
    }


    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength, Color fieldColor, Color userColor, Color aiColor) {
//        System.out.println(gameMode + " " + fieldSizeX + " " + fieldSizeY + " " + winLength);
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        setBackground(fieldColor);
        this.userColor = userColor;
        this.aiColor = aiColor;
        isInit = true;
        result = Logic.CONTINUE;
        nextMove = Logic.DOT_X;
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;
        resultImage = null;
        repaint();
    }
}
