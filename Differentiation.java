//class Differentiation
import java.util.Map;
public class Differentiation extends Binary {
  
  public Differentiation(Sexpr left, Sexpr right) {
    super(left, right); 
  }
  
  public String getName() {
    return "'";
  }
  
  public Sexpr eval(Map<String,Sexpr> map) {
    Sexpr der = left.eval(map);
    return left.eval(map).diff(right);
  }
  
  public int priority() {
    return 30;
  }  
}