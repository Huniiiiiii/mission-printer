package mission;

import mission.controller.printerController;
import mission.model.ink;
import mission.utils.AsciiGenerator;
import mission.view.printerView;

public class Application {
    public static void main(String[] args) {
        printerController controller = new printerController(
                new AsciiGenerator(), new ink(), new printerView()
        );
        controller.run();
    }
}
