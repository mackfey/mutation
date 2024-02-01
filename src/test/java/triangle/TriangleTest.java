package triangle;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static triangle.Triangle.Type;
import static triangle.Triangle.Type.*;

/**
 * Test class for the Triangle implementation.
 */
@RunWith(Parameterized.class)
public class TriangleTest {
  static {
    Locale.setDefault(Locale.GERMAN);
  }
  @Parameter(0)
  public int a;
  @Parameter(1)
  public int b;
  @Parameter(2)
  public int c;
  @Parameter(3)
  public Type expected;

  @Before
  public void initialize() {
    Triangle a = new Triangle();
  }

  @Test
  public void testTriangle() {
    Type actual = Triangle.classify(a, b, c);

    assertEquals(expected, actual);
  }

  @Parameters(name = "{index}: ({0} {1} {2})->{3}")
  public static Collection testTable() {
    return Arrays.asList(new Object[][]
      {
      {0, 0, 0, INVALID},
      {1, 0, 0, INVALID},
      {1, 1, 0, INVALID},
      {1, 2, 3, INVALID},
      {1, 3, 2, INVALID},
      {3, 1, 2, INVALID},
      {2, 3, 4, SCALENE},
      {1, 1, 1, EQUILATERAL},
      {2, 2, 1, ISOSCELES},
      {2, 1, 2, ISOSCELES},
      {1, 2, 2, ISOSCELES},
      {2, 2, 5, INVALID},
      {2, 5, 2, INVALID},
      {5, 2, 2, INVALID},
      }
    );
   }
}
