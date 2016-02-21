import gui.*;

public class Test {
    public static void main(String[] args) {
        Thread t = new Thread(new Login());
        t.start();
    }
}
