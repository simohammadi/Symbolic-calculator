//class Abs
import java.util.Map;
public class Abs extends Function {
  
  public Abs(Sexpr operand) {
    super(operand);
  }
  
  public String toString() {
    return getName() + operand + getName();
  }
  
  public String getName() {
    return "Abs";
  }
  
  public Sexpr eval(Map<String,Sexpr> map) {
    return Symbolic.abs(operand.eval(map));
  }
}