package utils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @Auther: YunHai
 * @Date: 2018/10/8 22:58
 * @Description:
 */
public class GetKeyBoard extends Frame implements Runnable{
    KeyBoardInput userInput;
    public GetKeyBoard(KeyBoardInput userInput){
        this.userInput = userInput;

    }


    @Override
    public void run() {

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                synchronized(userInput) {
                    userInput.code = e.getKeyCode();

                    userInput.notify();
                }
            }
        });
        setSize(100,100);
        setVisible(true);
    }
}
