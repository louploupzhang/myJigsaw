import com.kleer.ui.GameJFrame;
import com.kleer.ui.LoginJFrame;

public class App {
    public static void main(String[] args) {
        new LoginJFrame();
        //new GameJFrame();
    }

    //Conflict1
    public String logout(){
        return "Query success!";
    }

    public String query(){
        return "Query success!";
    }
}
}
