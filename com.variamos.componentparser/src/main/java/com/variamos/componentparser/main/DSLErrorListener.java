package com.variamos.componentparser.main;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class DSLErrorListener extends BaseErrorListener
{
  private List<SyntaxErrorItem> items;
  
  public DSLErrorListener()
  {
      this.items = new ArrayList<SyntaxErrorItem>();
  }
  
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
  {
      items.add (new SyntaxErrorItem(line, charPositionInLine, msg, offendingSymbol, e));
  }

  public boolean hasErrors()
  {
      return this.items.size() > 0;
  }
  
  @Override
  public String toString()
  {
      if(!hasErrors()) return "0 errors";
      StringBuilder builder = new StringBuilder();
      for(SyntaxErrorItem s : items)
      {
          builder.append (String.format ("%s\n", s));
      }
      return builder.toString();
  }
}
