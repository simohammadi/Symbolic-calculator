/**
 * Represents an assignment operation
 */
import java.util.Map;

public class Assignment 
  extends Binary 
{
  public Assignment(Sexpr left, Sexpr right) {
    super(left, right);
  }
  
  @Override 
  public int priority() {
    return 5;
  }
  
  public String getName() {
    return "=";
  }
    
  public Sexpr eval(Map<String,Sexpr> map) {
    Sexpr value = left.eval(map);
    map.put(right.getName(), value); 
    return value;
  }
}
  
  