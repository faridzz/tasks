public class Run implements Runnable {

  public static void main(String[] args) {
    Run n = new Run();
    Thread t = new Thread(n);
    t.start();
    Server server = new Server(5000);
    
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    Atm client = new Atm("127.0.0.1", 5000);
  }
}
