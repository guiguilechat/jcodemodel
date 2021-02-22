package com.helger.jcodemodel.blockmodel;

import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JConditional;

/**
 * an {@link SubBlock} that represents the then part of an if.
 *
 * @author glelouet
 *
 * @param <T>
 *        type of parent block
 */
public class ThenBlock <T extends ChainedBlock <?>> extends SubBlock <T, ThenBlock <T>>
{

  private final JConditional cond;

  /**
   * create a block representing the then case of the if condition.
   *
   * @param up
   * @param cond
   */
  public ThenBlock (T up, JConditional cond)
  {
    super (up, cond._then ());
    this.cond = cond;
  }

  private ElseBlock <T> elseBlock = null;

  ElseBlock <T> elseBlock ()
  {
    if (elseBlock == null)
      elseBlock = new ElseBlock <> (this, cond);
    return elseBlock;
  }

  public ThenBlock <ThenBlock <T>> elseIf (IJExpression expr)
  {
    return new ThenBlock <> (this, cond._elseif (expr));
  }
}
