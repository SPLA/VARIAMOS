package com.variamos.componentparser.main;
import org.antlr.v4.runtime.RecognitionException;

public class SyntaxErrorItem
{
  private int line; 
  private Object offendingSymbol; 
  private int column; 
  private String msg; 
  private RecognitionException oops;
  
  SyntaxErrorItem(int line, int column, String msg, Object symbol, RecognitionException oops)
  {
      this.line = line;
      this.column = column;
      this.msg = msg;
      this.offendingSymbol = symbol;
      this.oops = oops;
  }
  
  @Override
  public String toString()
  {
  	return String.format ("[%d:%d] %s", line, column, msg);
  }
}

