/**
 * Methods for symbolic arithmetic.
 */
public class Symbolic {
  
  /**
   * Perform a symbolic/numeric addition
   * Note: The method should be elaborated to handle
   * handle several special cases (e.g addition of zero)
   */
  public static Sexpr add(Sexpr left, Sexpr right) {
    if ( left.isConstant() && right.isConstant() )
      return new Constant(left.getValue() + right.getValue());
    else if ( left.isConstant(0.) )
      return right;
    else if ( right.isConstant(0.) )
      return left;
    else if (right.toString().equals(left.toString()))
      return Symbolic.mul(new Constant(2.), left);
    else 
      return new Addition(left, right);
  }
  
  // Subtraction
  public static Sexpr sub(Sexpr left, Sexpr right) {
    if (left.isConstant() && right.isConstant()){
      return new Constant(left.getValue() - right.getValue());
    }else if(left.isConstant(0.)){
      return Symbolic.negate(right);
    }else if(right.isConstant(0.)){
      return left;
    }
    else if(left.toString().equals(right.toString())){
      return new Constant(0.);
    }
    else
      return new Subtraction(left, right);
  }
  
  
  // Division
  public static Sexpr div(Sexpr left, Sexpr right) {
    if (left.isConstant() && right.isConstant())
      return new Constant(left.getValue()/right.getValue());
    else if (left.isConstant(0.))
      return new Constant(0.);
    else if (right.isConstant(0.))
      throw new EvaluationException("Division By zero");
    else if (right.isConstant(1.))
      return left;
    else if (left.toString().equals(right.toString()))
      return new Constant(1.);
    else
      return new Division(left, right);
  }
  
  /**
   * Perform a symbolic/numeric multiplication
   * Note: The method should be elaborated to handle several
   * special cases (e.g multiplication with zero or one)
   */
  public static Sexpr mul(Sexpr left, Sexpr right) {
    if ( left.isConstant() && right.isConstant() )
      return new Constant(left.getValue() * right.getValue());
    else if ( left.isConstant(1.) )
      return right;
    else if ( right.isConstant(1.) )
      return left;
    else if (left.isConstant(0.) || right.isConstant(0.))
      return new Constant(0.);
    else 
      return new Multiplication(left, right);    
  }
  
  /**
   * Perform a symbolic/numeric negation
   */  
  public static Sexpr negate(Sexpr operand) {
    if (operand.isConstant())
      return new Constant(-operand.getValue());
    else
      return new Negation(operand);
  }
  
  /**
   * perform a symbolic cos
   */
  public static Sexpr cos(Sexpr operand) {
    if (operand.isConstant()){
      return new Constant(Math.cos(operand.getValue()));
    }else{
      return new Cos(operand);
    }
  }
  
  
  /**
   * perform a symbolic sin
   */
  public static Sexpr sin(Sexpr operand) {
    if (operand.isConstant()){
      return new Constant(Math.sin(operand.getValue()));
    }else{
      return new Sin(operand);
    }
  }
  /**
   * perform a symbolic exp
   */
  public static Sexpr exp(Sexpr operand) {
    if (operand.isConstant()){
      return new Constant(Math.exp(operand.getValue()));
    }else{
      return new Exp(operand);
    }
  }
  
  /**
   * perform a symbolic abs
   */
  public static Sexpr abs(Sexpr operand) {
    if (operand.isConstant()){
      return new Constant(Math.abs(operand.getValue()));
    }else{
      return new Abs(operand);
    }
  }
  
  /**
   * perform a symbolic log
   */
  public static Sexpr log(Sexpr operand) {
    if (operand.isConstant()){
      if(operand.getValue() > 0){
        return new Constant(Math.log(operand.getValue()));
      }else{
        throw new EvaluationException("Cant take log of negative numbers");
      }
    } else{
      return new Log(operand);
    }
  } 
}
