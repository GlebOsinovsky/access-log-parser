public class Line {
    Point startPoint;
    Point endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(int x1, int y1, int x2, int y2){
        this(new Point(x1,y1),new Point(x2,y2));
    }
    public double getLenght(){
        int dx = endPoint.x-startPoint.x;
        int dy = endPoint.y-startPoint.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public String toString() {
        return "Линия от " + startPoint + " " + endPoint  ;
    }
}
