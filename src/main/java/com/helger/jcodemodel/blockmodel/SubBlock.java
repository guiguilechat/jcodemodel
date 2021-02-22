package com.helger.jcodemodel.blockmodel;

import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JVar;

/**
 * a block that is contained in an upper block.
 * <p>the {@link #var(String)} call in that case delegates the call to the {@link #up()} block if not found.</p>
 *
 * @author glelouet
 *
 * @param <T>
 *        the upper block class
 * @param <Self>
 *        this class. Used to create sub blocks that can return this as their {@link #up}
 */
public class SubBlock <T extends ChainedBlock <?>, Self extends SubBlock <T, ?>> extends ChainedBlock <Self>
{

  private final T up;

  public T up ()
  {
    return up;
  }

  public SubBlock (T up, JBlock block)
  {
    super (block);
    this.up = up;
  }

  @Override
  public JVar var (String name)
  {
    JVar ret = super.var (name);
    return ret == null ? up ().var (name) : ret;
  }

  public Self _return (IJExpression aExpr)
  {
    block ()._return (aExpr);
    return self ();
  }

}
