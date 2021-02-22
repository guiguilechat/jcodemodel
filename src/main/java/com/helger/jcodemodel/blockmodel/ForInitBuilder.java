package com.helger.jcodemodel.blockmodel;

import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JVar;

public class ForInitBuilder <T extends ChainedBlock <?>>
{
  private final ForBlock <T> creation;

  public ForInitBuilder (ForBlock <T> creation)
  {
    this.creation = creation;
  }

  public ForInitBuilder <T> init (AbstractJType aType, String sVarName, IJExpression aInitExpr)
  {
    JVar added = creation.forLoop ().init (aType, sVarName, aInitExpr);
    creation.addVar (added);
    return this;
  }

  public ForTestBuilder <T> test (IJExpression expr)
  {
    creation.forLoop ().test (expr);
    return new ForTestBuilder <> (creation);
  }

  public ForTestBuilder <T> test ()
  {
    return new ForTestBuilder <> (creation);
  }

}
