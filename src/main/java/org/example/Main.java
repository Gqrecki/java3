package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 5, new Color(255, 0, 0, 255));
        Shape triangle = new Triangle(3, 4, 5, new Color(0, 255, 0, 255));

        ShapeDescriber describer = new ShapeDescriber();
        describer.describe(rectangle);
        describer.describe(triangle);

        ShapeRenderer renderer = new ShapeRenderer();
        List<Shape> shapes = List.of(rectangle, triangle);
        renderer.render(shapes);
    }
}

abstract class Shape {
    private final Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getColorDescription() {
        return "Red: " + color.red() + ", Green: " + color.green() +
                ", Blue: " + color.blue() + ", Alpha: " + color.alpha();
    }

    public abstract double getArea();
    public abstract double getPerimeter();
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height, Color color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

class Triangle extends Shape {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c, Color color) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }
}

class ShapeDescriber {
    private static final Logger logger = LoggerFactory.getLogger(ShapeDescriber.class);

    public void describe(Shape shape) {
        logger.info("Shape description:");
        logger.info("Type: " + shape.getClass().getSimpleName());
        logger.info("Color: " + shape.getColorDescription());
        logger.info("Area: " + shape.getArea());
        logger.info("Perimeter: " + shape.getPerimeter());
    }
}

class ShapeRenderer {
    public void render(List<Shape> shapes) {
        for (Shape shape : shapes) {
            System.out.println("Rendering " + shape.getClass().getSimpleName() + "...");
            System.out.println("Color: " + shape.getColorDescription());
        }
    }
}

record Color(int red, int green, int blue, int alpha) {
    public Color {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255 || alpha < 0 || alpha > 255) {
            throw new IllegalArgumentException("0-255.");
        }
    }

    public Color(int red, int green, int blue) {
        this(red, green, blue, 255);
    }
}