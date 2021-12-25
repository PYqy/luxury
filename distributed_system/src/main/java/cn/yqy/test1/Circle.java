package cn.yqy.test1;

public class Circle {


    public static void main(String[] args) {
        Circle c = new Circle(10);
        Cone<Circle> cone1 = new Cone<Circle>(c);
        cone1.setHeight(16);
        System.out.println("Circle volumn = " + cone1.computerVolume());


        //2
        Rect r = new Rect(15, 10);
        Cone<Rect> cone2 = new Cone<Rect>(r);
        cone2.setHeight(10);
        System.out.println("rect volumn = " + cone2.computerVolume());

        YueQi y = new YueQi();
        Singer s = new Singer();
        Computer<Singer, YueQi> model = new Chorus<Singer, YueQi>();
        model.makeCHorus(s, y);

    }

    double area, radius;

    public Circle(double r) {
        this.radius = r;
        System.out.println("radius=" + radius);

    }

    @Override
    public String toString() {
        area = radius * radius * Math.PI;
        return "" + area;
    }
}

class Rect {
    double sideA, sideB, area;

    public Rect(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public String toString() {
        area = sideA * sideB;
        return "" + area;
    }
}

class Cone<E> {

    double height, area;
    E bootom;

    Cone(E b) {
        bootom = b;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double computerVolume() {
        String s = bootom.toString();
        double area = Double.parseDouble(s);
        return (1.0 / 3.0) * area * height;
    }
}

