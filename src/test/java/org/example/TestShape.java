package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestShape {
    @Test
    void testRectangleArea() {
        Shape rectangle = new Rectangle(10, 5, new Color(255, 0, 0, 255));
        assertEquals(50, rectangle.getArea());
    }

    @Test
    void testRectanglePerimeter() {
        Shape rectangle = new Rectangle(10, 5, new Color(255, 0, 0, 255));
        assertEquals(30, rectangle.getPerimeter());
    }

    @Test
    void testTriangleArea() {
        Shape triangle = new Triangle(3, 4, 5, new Color(0, 255, 0, 255));
        assertEquals(6, triangle.getArea(), 0.01);
    }

    @Test
    void testTrianglePerimeter() {
        Shape triangle = new Triangle(3, 4, 5, new Color(0, 255, 0, 255));
        assertEquals(12, triangle.getPerimeter());
    }

    @Test
    void testColorValidation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Color(256, 0, 0));
        assertEquals("0-255.", exception.getMessage());
    }
}