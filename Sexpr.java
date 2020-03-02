/**
 * Base class for all components in an arithmetic expression
 */

import java.util.Map;
  
public abstract class Sexpr {
  
  /**
   * @return the name of the operator, function, variable or constant
   */
  public abstract String getName(); 
   
  /**
   * Evaluate the symbolic expression
   */
  public abstract Sexpr eval(Map<String, Sexpr> map);
  
  /**
   * Return the priority. 
   * The default priority (100) is HIGH meaning that no parentheses 
   * are needed around the component.
   */
  public int priority() {
    return 100; 
  }
  
  /**
   * Differentiation operation
   * @return The derivative
   */
  public Sexpr diff(Sexpr x) {
    throw new EvaluationException("No differentiation rule for " + getName());  
  }
  
  /**
   * @return The numeric value of a Constant object
   */
  public double getValue() {
    throw new InternalErrorException( getName() + " does not have a value");
  }
  
  /**
   * @return true if the component is a Constant object
   */
  public boolean isConstant() {
    return false;
  }
  
  /**
   * @return true if the component is a Constant object with a specified value
   */
  public boolean isConstant(double x) {
    return false; 
  }
  
}


// Fråga 1:
/*
 * Abstrakta klasser kan inte instansieras
 * 
 */

// Fråga 2:
/*
 * is constant omdefineras i constant och priority omdefineras i operanderna
 */ 
 
// Fråga 3:
/*
 * protected inebär att barnklasser kan ändra på instantsvariablarna
 * 
 */

// Fråga 4:
/*
 * Map: No order
 * TreeMap: in Order.
 * 
 * Man kan annars inte skapa en ny map, utan man måste använda TreeMap för att kunna göra det. 
 */ 

// Fråga 5:
/*
 * 
 * 
 */ 