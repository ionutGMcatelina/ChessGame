import ChessModel.SahModel;

public class Main {
    public static void main(String[] args) {
        SahModel model = new SahModel();
        SettingsView settingsView = new SettingsView();
        SahView view = new SahView(settingsView);
        new SahController(model, view, settingsView);

        view.setSize(1400, 800);
        view.setVisible(true);
    }
}
