package elementary;

public class Pin {
    public double x;
    public double y;

    public Pin(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Pin pin) {
        return Math.sqrt(((this.x - pin.x) * (this.x - pin.x)) + (this.y - pin.y) * (this.y - pin.y));
    }

    public double distanceTo(double x, double y) {
        return Math.sqrt(((this.x - x) * (this.x - x)) + (this.y - y) * (this.y - y));
    }

}
