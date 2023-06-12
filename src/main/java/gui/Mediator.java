package gui;

public class Mediator {
    private Window window;
    private Game game;
    public Mediator(Window window, Game game) {
        this.window = window;
        this.game = game;
    }

    public Mediator() {

    }

    public void startGame() {

    }

    public void stopGame() {

    }

    public void showGame() {

    }

    public void showMenu() {

    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
