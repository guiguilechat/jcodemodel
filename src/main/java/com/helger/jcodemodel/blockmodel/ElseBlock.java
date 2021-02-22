package com.helger.jcodemodel.blockmodel;

import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JConditional;

public class ElseBlock <T extends ChainedBlock <?>> extends SubBlock <T, ThenBlock <T>>
{

  private final ThenBlock <T> thenBlock;

  public ElseBlock (ThenBlock <T> thenBlock, JConditional cond)
  {
    super (thenBlock.up (), cond._else ());
    this.thenBlock = thenBlock;
  }

  public ThenBlock <T> thenBlock ()
  {
    return thenBlock;
  }

  public ThenBlock <ThenBlock <T>> elseIf (IJExpression expr)
  {
    return thenBlock ().elseIf (expr);
  }

}
