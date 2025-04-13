
import javafx.scene.shape.LineTo;

public class ClosedPolyLine extends PolyLine {

    public ClosedPolyLine() {
        super();
    }

    public ClosedPolyLine(Point... initialPoints) {
        super(initialPoints);
    }
    @Override
    public double getLength (){
        double originalLength = super.getLength();
        if (points.size() < 2) return originalLength;
        Line closingLine = new Line(points.get(points.size() - 1), points.get(0));

        return originalLength + closingLine.getLength();
    }

    @Override
    public String toString() {
        return "Замкнутая " + super.toString();
    }
}
