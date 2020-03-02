// public Sexpr primary() {
//    Sexpr result;
//    
//    // NUmber
//    if (tokenizer.isNumber()) {
//      result = new Constant(tokenizer.getNumber());
//      tokenizer.nextToken();
//    } else if(tokenizer.getChar() == '('){//Paranteser
//      
//      tokenizer.nextToken();
//      result = assignment();
//      if(tokenizer.getChar() != ')'){
//        throw new SyntaxException("Syntax error: Expected ’)’");
//        //System.out.println("error");
//      }else{
//        tokenizer.nextToken();
//      }
//    }
//    
//    // Abs
//    if(tokenizer.getChar() == '|'){
//      tokenizer.nextToken();
//      Sexpr r = assignment();
//      if(tokenizer.getChar() == '|'){
//        tokenizer.nextToken();
//        result = new Abs(r);
//      }else 
//        throw new SyntaxException("Miss a '|'");
//    }
//    
//    //Word like cos, log, .....
//    else if(tokenizer.isWord()){
//      String func = tokenizer.getWord();
//      if(functions.contains(func)){// Functions name
//        if(tokenizer.nextToken() == ')'){
//          throw new SyntaxException("YOU FORGOT A PARANTHESE");
//        } else{
//          result = primary();
//        }
//        
//        //sin
//        if(func.equals("sin")){
//          result = new Sin(result);
//        }
//        //cos
//        else if(func.equals("cos")){
//          result = new Cos(result);
//        }
//        //log
//        else if(func.equals("log")){
//          result = new Log(result);
//        }
//        // Abs
//        else if(func.equals("abs")){
//          result = new Abs(result);
//        }
//        //exp
//        else {
//          result = new Exp(result);
//        }
//        
//      }
//      else if(variables.containsKey(func)){
//        
//        tokenizer.nextToken();
//        result = variables.get(func);
//        
//      }
//      else{
//        throw new EvaluationException( "Evaluation error. Undefined variable:" + tokenizer.getToken());
//        //System.out.println("error");
//      }
//      
//    }
//    else if(tokenizer.getChar() == '-'){
//      tokenizer.nextToken();
//      result = new Negation(primary());
//      
//    }
//    
//    else { 
//      throw new SyntaxException( "Syntax error. Expected number, word or ’(’");
//      //System.out.println("Error");
//    }
//    return result;
//  }