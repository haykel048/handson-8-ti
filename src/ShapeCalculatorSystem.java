import java.util.*;

// =============================================================
//        CHALLENGE PROJECT: Shape Calculator with Inheritance
// =============================================================

public class ShapeCalculatorSystem {
    public static void main(String[] args) {
        /*
         * CHALLENGE PROJECT: Shape Calculator with Inheritance
         *
         * Deskripsi: Sistem untuk menghitung luas dan keliling berbagai
         * bentuk geometri menggunakan inheritance hierarchy.
         */

        // ===== SHAPE CALCULATIONS =====
        System.out.println("=== SHAPE CALCULATOR SYSTEM ===\n");

        // Latihan 1: Create basic shapes
        // - Buat Circle dengan radius 7
        // - Buat Rectangle dengan panjang 10, lebar 5
        // - Buat Triangle dengan base 6, height 8
        // - Calculate dan display luas & keliling masing-masing

        // Ekspektasi Output:
        // Circle: Luas = 153.94, Keliling = 43.98
        // Rectangle: Luas = 50, Keliling = 30
        // Triangle: Luas = 24, Keliling = [calculated]

        Circle circle = new Circle(7);
        Rectangle rectangle = new Rectangle(10, 5);
        Triangle triangle = new Triangle(6, 8, 10); // sisi miring 10 agar segitiga valid

        System.out.printf("Circle: Luas = %.2f, Keliling = %.2f%n",
                circle.getArea(), circle.getPerimeter());
        System.out.printf("Rectangle: Luas = %.2f, Keliling = %.2f%n",
                rectangle.getArea(), rectangle.getPerimeter());
        System.out.printf("Triangle: Luas = %.2f, Keliling = %.2f%n%n",
                triangle.getArea(), triangle.getPerimeter());

        // Latihan 2: Square (special case of Rectangle)
        // - Buat Square dengan sisi 8
        // - Square extends Rectangle
        // - Override untuk memastikan panjang = lebar

        // Ekspektasi Output:
        // Square: Luas = 64, Keliling = 32

        Square square = new Square(8);
        System.out.printf("Square: Luas = %.2f, Keliling = %.2f%n%n",
                square.getArea(), square.getPerimeter());

        // Latihan 3: Compare shapes
        // - Buat method untuk compare area dari 2 shapes
        // - Find shape dengan area terbesar

        // Ekspektasi Output:
        // Circle memiliki area terbesar: 153.94

        Shape largest = Shape.compareArea(circle, rectangle);
        largest = Shape.compareArea(largest, triangle);
        largest = Shape.compareArea(largest, square);
        System.out.printf("%s memiliki area terbesar: %.2f%n%n",
                largest.getName(), largest.getArea());

        // Latihan 4: Scale shapes
        // - Implementasi method scale(factor)
        // - Scale circle dengan factor 2
        // - Scale rectangle dengan factor 0.5

        // Ekspektasi Output:
        // Original Circle area: 153.94
        // Scaled Circle area: 615.75
        // Scale factor: 2.0

        System.out.printf("Original Circle area: %.2f%n", circle.getArea());
        circle.scale(2);
        System.out.printf("Scaled Circle area: %.2f%n", circle.getArea());
        System.out.println("Scale factor: 2.0\n");

        rectangle.scale(0.5);
        System.out.printf("Scaled Rectangle area: %.2f%n%n", rectangle.getArea());

        // Latihan 5: Total area calculator
        // - Buat array/list of shapes
        // - Calculate total area dari semua shapes

        // Ekspektasi Output:
        // Total area dari semua shapes: 391.94

        List<Shape> shapes = Arrays.asList(circle, rectangle, triangle, square);
        double totalArea = 0;
        for (Shape s : shapes) totalArea += s.getArea();

        System.out.printf("Total area dari semua shapes: %.2f%n", totalArea);
    }
}

// =============================================================
//                        KELAS DASAR (SUPERCLASS)
// =============================================================

abstract class Shape {
    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract void scale(double factor);
    public abstract String getName();

    // Utility method untuk membandingkan area
    public static Shape compareArea(Shape s1, Shape s2) {
        return (s1.getArea() >= s2.getArea()) ? s1 : s2;
    }
}

// =============================================================
//                       SUBCLASS: CIRCLE
// =============================================================

class Circle extends Shape {
    private double radius;

    public Circle(double radius) { this.radius = radius; }

    @Override
    public double getArea() { return Math.PI * radius * radius; }

    @Override
    public double getPerimeter() { return 2 * Math.PI * radius; }

    @Override
    public void scale(double factor) { this.radius *= factor; }

    @Override
    public String getName() { return "Circle"; }
}

// =============================================================
//                      SUBCLASS: RECTANGLE
// =============================================================

class Rectangle extends Shape {
    protected double length;
    protected double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() { return length * width; }

    @Override
    public double getPerimeter() { return 2 * (length + width); }

    @Override
    public void scale(double factor) {
        this.length *= factor;
        this.width *= factor;
    }

    @Override
    public String getName() { return "Rectangle"; }
}

// =============================================================
//                        SUBCLASS: TRIANGLE
// =============================================================

class Triangle extends Shape {
    private double base, height, hypotenuse;

    public Triangle(double base, double height, double hypotenuse) {
        this.base = base;
        this.height = height;
        this.hypotenuse = hypotenuse;
    }

    @Override
    public double getArea() { return 0.5 * base * height; }

    @Override
    public double getPerimeter() { return base + height + hypotenuse; }

    @Override
    public void scale(double factor) {
        this.base *= factor;
        this.height *= factor;
        this.hypotenuse *= factor;
    }

    @Override
    public String getName() { return "Triangle"; }
}

// =============================================================
//                      SUBCLASS: SQUARE
// =============================================================

class Square extends Rectangle {
    public Square(double side) { super(side, side); }

    @Override
    public void scale(double factor) {
        this.length *= factor;
        this.width = this.length; // pastikan tetap bujur sangkar
    }

    @Override
    public String getName() { return "Square"; }
}

