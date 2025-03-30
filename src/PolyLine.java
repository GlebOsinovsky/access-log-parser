import java.util.ArrayList;

public class PolyLine {
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
        StringBuilder str = new StringBuilder("Ломаная линия [");
        //        return "Ломаная Линия"+ points ;
        for (int i = 0; i < points.size(); i++) {
            str.append(points.get(i));
            if (i != points.size() -1) {
                str.append(",");
            }
        }
        str.append("]");
        return str.toString();
    }
}


