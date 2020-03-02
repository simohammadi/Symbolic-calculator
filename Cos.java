//class Cos
import java.util.Map;
public class Cos extends Function {
  
  public Cos(Sexpr operand) {
    super(operand);
  }
  
  public String getName() {
    return "cos";
  }
  
  public Sexpr eval(Map<String,Sexpr> map) {
    return Symbolic.cos(operand.eval(map));
  }  
  
  public Sexpr diff(Sexpr x) {
    return Symbolic.negate(Symbolic.mul(operand.diff(x), Symbolic.sin(operand)));
  }
}