/**
 * Parser
 * See syntax diagrams for documentation
 */

import java.util.TreeSet;
public class Parser {
  
  private Stokenizer tokenizer;
  private TreeSet<String> functions;  // Names of recognized functions
  
  /**
   * Constructs a parser
   * @param tokenizer an initialized tokenizer
   */
  public Parser(Stokenizer tokenizer) {  
    this.tokenizer = tokenizer; 
    // Create a set with the names of the recognized functions
    functions = new TreeSet<String>();
    functions.add("sin");
    functions.add("cos");
    functions.add("exp");
    functions.add("log");
    functions.add("abs");
  }
  
  /**
   * Handles an assignment
   */
  public Sexpr assignment() {
    Sexpr value = expression();
    while (tokenizer.getChar() == '=') {
      tokenizer.nextToken();
      if (tokenizer.isWord()) {
        value = new Assignment(value, new Variable(tokenizer.getWord()));
        tokenizer.nextToken();
      } else {
        System.out.print("Expected '=' but found " + tokenizer.toString());
        throw new SyntaxException("Expected variable after '='");
      }
    }
    
    return value;
  }
  
  /**
   * Handles a sum of terms 
   */
  public Sexpr expression() {
    Sexpr sum = term();
    int c;
    while ((c=tokenizer.getChar()) == '+' || (c =tokenizer.getChar()) == '-') {
      tokenizer.nextToken();
      if (c=='+') {
        sum = new Addition(sum, term());
      }else
        sum = new Subtraction(sum, term());
    }
    return sum;
  }
  
  /**
   * Handles a product of factors
   */
  public Sexpr term() {
    int c;
    Sexpr prod = factor();
    while ((c=tokenizer.getChar()) == '*' || (c=tokenizer.getChar()) == '/'){
      tokenizer.nextToken();
      if (c=='*') {
        prod = new Multiplication(prod, factor());
      }else
        prod = new Division(prod, factor());
    }
    return prod;
  }
  
  /**
   * Handles a differentiation:
   * <primary>[' <variable> [' <variable> ...]]
   */
  public Sexpr factor() {
    Sexpr prim =  primary();
    while(tokenizer.getChar() == '\''){
      tokenizer.nextToken();
      if(tokenizer.isWord()){
        prim = new Differentiation(prim, new Variable(tokenizer.getWord()));
        tokenizer.nextToken();
      }else
        throw new SyntaxException("missing identifier after '\''");
    }
    return prim;            
  }
  
  /**
   * Handles a primary  (see syntax diagram)
   */
  public Sexpr primary() {
    
    // PARANTHESIS
    Sexpr result = null;
    if (tokenizer.getChar() == '(') {          
      tokenizer.nextToken(); 
      result = assignment();
      if (tokenizer.getChar() == ')') {
        tokenizer.nextToken();
      } else {
        throw new SyntaxException("Expected ')'");
      }
    } 
    
    // Abs
    else if(tokenizer.getChar() == '|'){
      tokenizer.nextToken();
      Sexpr r = assignment();
      if(tokenizer.getChar() == '|'){
        tokenizer.nextToken();
        result = new Abs(r);
      }else 
        throw new SyntaxException("Miss a '|'");
    }
    
    // Unary minus
    else if (tokenizer.getChar() == '-') {
      tokenizer.nextToken();
      result = new Negation(primary());  
    } 
    // Quotation
    else if (tokenizer.getChar() == '"') {
      tokenizer.nextToken();
      result = new Quotation(primary());  
    } 
    // function
    else if(functions.contains(tokenizer.getToken())){
      result = unaryFunction();
    }
    
    // Number
    else if (tokenizer.isNumber()) {           
      result = new Constant(tokenizer.getNumber());
      tokenizer.nextToken();
    } 
    // Evaluation
    else if (tokenizer.getChar()=='&') {
      tokenizer.nextToken();
      result = new Evaluation(primary());
    }
    
    // Word
    else if (tokenizer.isWord()) {
      result = new Variable(tokenizer.getWord());
      tokenizer.nextToken();
    } else {
      throw new SyntaxException("Expected number, word, '-', " + "'\"', '&' or '('");
    }
    
    return result;
  }
  
  // UNARYFUCNTION
  public Sexpr unaryFunction() {
    String func = tokenizer.getWord();
    Sexpr arg;
    tokenizer.nextToken();
    if (tokenizer.getChar()=='(') {
      arg = primary();
    } else {
      throw new SyntaxException("'(' förväntades efter '" + func +"'");
    }
    if (func.equals("exp")){
      return new Exp(arg);
    }
    else if (func.equals("log")){
      return new Log(arg);
    }
    else if (func.equals("sin")){
      return new Sin(arg);
    }
    else if (func.equals("cos")){
      return new Cos(arg);
    }
    else if (func.equals("abs")){
      return new Abs(arg);
    }
    else{
      throw new RuntimeException("Internt fel! Odefinierad funktion " + func);
    }
  }
  
  
  
  
  
  public Sexpr function(String functionName) {
    throw 
      new EvaluationException("Handling of functions not yet implemented");    
  }
  
}


