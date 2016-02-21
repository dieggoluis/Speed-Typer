import gui.Display;

public class Test {
    public static void main(String[] args) {
        Thread t = new Thread(new Display());
        t.start();
    }
}
