/**
 * Base class for unary operators
 */
public abstract class Operator 
  extends Unary
{
  public Operator(Sexpr operand) {
    super(operand);
  }

  @Override	  
  public String toString() {
    if ( this.priority() > operand.priority())
      return getName() + "(" + operand + ")";
    else
      return getName() + operand;
  }

  @Override	  
  public int priority() {
    return 40;
  }
}