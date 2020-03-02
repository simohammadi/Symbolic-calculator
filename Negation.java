/**
 * Represents the negation operation ("unary minus")
 */
import java.util.Map;

public class Negation
  extends Operator
{
  public Negation(Sexpr operand) {
    super(operand);
  }
  
  public String getName() {
    return "-";
  }
  
  public Sexpr eval(Map<String, Sexpr> map) {
    return Symbolic.negate(operand.eval(map));
  }
  
  public Sexpr diff(Sexpr x){
    return Symbolic.negate(operand.diff(x));
  }
}