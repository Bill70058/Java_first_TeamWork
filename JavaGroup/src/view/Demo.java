package view;

import org.junit.Test;
import utils.GetKeyBoard;
import utils.KeyBoardInput;
import view.impl.ZeroTimeViewImpl;

/**
 * @Auther: YunHai
 * @Date: 2018/10/9 00:38
 * @Description:
 */
public class Demo {


    public static void main(String[] args) {
        KeyBoardInput userInput = new KeyBoardInput();
        Thread thread1 = new Thread(new GetKeyBoard(userInput));
        Thread thread2 = new Thread(new ZeroTimeViewImpl(userInput));
        thread1.start();
        thread2.start();
    }


}
