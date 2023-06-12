package abstractClasses;

public abstract class DrawableInstance {
    protected int x;
    protected int y;
    protected String model;

    public DrawableInstance(String model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
