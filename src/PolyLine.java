import java.util.ArrayList;

public class PolyLine implements Measurable{
    ArrayList<Point> points;

    public PolyLine() {
        points = new ArrayList<>();
    }

    public PolyLine(Point... initialPoints) {
        this();
        for (Point a : initialPoints) {
            points.add(a);
        }
    }

    public Line[] getLines() {
        if (points.size() < 2) return new Line[0];
        Line[] lines = new Line[points.size() - 1];
        for (int i = 0; i < points.size()-1; i++) {
            lines[i] = new Line(points.get(i), points.get(i + 1));
        }
        return lines;
    }

    public double getLength() {
        double sum = 0;
        for (Line line : getLines()) {
            sum += line.getLength();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Ломаная Линия: "+ points ;
    }
}