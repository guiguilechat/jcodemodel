package com.helger.jcodemodel.blockmodel;

import com.helger.jcodemodel.IJExpression;

public class ForTestBuilder <T extends ChainedBlock <?>>
{
  private final ForBlock <T> creation;

  public ForTestBuilder (ForBlock <T> creation)
  {
    this.creation = creation;
  }

  public ForBlock <T> update (IJExpression aUpdate)
  {
    creation.forLoop ().update (aUpdate);
    return creation;
  }

  public ForBlock <T> update ()
  {
    return creation;
  }

}
