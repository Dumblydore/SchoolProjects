import java.awt.*;

/**
 * Created by medwar40 on 11/11/15.
 */
public class Target {

    int radius, centerX, centerY, deltaX, deltaY;
    Color primaryColor;

    public Target(int radius, int centerX, int centerY, int deltaX, int deltaY) {
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.primaryColor = Color.red;
    }

    public void invert() {
        deltaX *= -1;
        deltaY *= -1;
    }

    public int getMinX() {
        return centerX - radius/2;
    }

    public int getMinY() {
        return centerY - radius/2;
    }

    public int getMaxX() {
        return centerX + radius/2;
    }

    public int getMaxY() {
        return centerY + radius/2;
    }

    public int getDeltaX() {
        centerX += deltaX;
        return centerX - radius;
    }

    public int getDeltaY() {
        centerY += deltaY;
        return centerY - radius ;
    }

    public boolean contains(int x, int y) {
        return (x >= getMinX() && x <= getMaxX()) && (y >= getMinY() && y <= getMaxY());
    }
}
