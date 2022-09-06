import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class MatrixImageTest {
    MatrixImage matrix;

    @BeforeEach
    void initialize() {
        matrix = new MatrixImage(3, 3);
        matrix.field[0][0] = 10;
        matrix.field[2][2] = 3;
        matrix.field[1][1] = -2;
    }

    @Test
    void contrast() {
        assertThrows(InputMismatchException.class, () -> {
                matrix.contrast(new Coordinate(5, 0), new Coordinate(1, 1));},
                "contrast() does not throw exception.");
        assertThrows(InputMismatchException.class, () -> {
                    matrix.contrast(new Coordinate(0, 0), new Coordinate(1, 10));},
                "contrast() does not throw exception.");
        assertThrows(InputMismatchException.class, () -> {
                    matrix.contrast(new Coordinate(0, 0), new Coordinate(-3, 1));},
                "contrast() does not throw exception.");

        assertEquals(7.0, matrix.contrast(new Coordinate(0, 0), new Coordinate(2, 2)),
                "Contrast is wrong.");
        assertEquals(3.0, matrix.contrast(new Coordinate(1, 0), new Coordinate(2, 2)),
                "Contrast is wrong.");
        assertEquals(5.0, matrix.contrast(new Coordinate(1, 1), new Coordinate(2, 2)),
                "Contrast is wrong.");
    }

    @Test
    void removeVPath() {
        System.out.println(matrix);
        int[] path = {0, 1, 0};
        matrix.removeVPath(path);
        assertEquals(2, matrix.sizeX(), "Did not slice the matrix correctly.");
        assertEquals(0, matrix.field[0][0], "Vertex was not deleted.");
        assertEquals(3, matrix.field[1][2], "Deleted wrong matrix field.");
        assertNotEquals(-2, matrix.field[1][1], "Vertex was not deleted.");
        assertNotEquals(-2, matrix.field[1][0], "Wrong vertex was deleted.");
        System.out.println(matrix);
    }
}