package com.helger.jcodemodel.blockmodel;

import java.util.HashMap;
import java.util.Map;

import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JVar;

/**
 * a block that allows to make sub blocks.
 *
 * @author glelouet
 *
 * @param <Self>
 *        self type, returned as type parameter of sub blocks.
 */
public class ChainedBlock <Self extends ChainedBlock <?>>
{

  @SuppressWarnings("unchecked")
  protected Self self ()
  {
    return (Self) this;
  }

  private final JBlock block;

  public JBlock block ()
  {
    return block;
  }

  public ChainedBlock (JBlock block)
  {
    this.block = block;
  }

  public ThenBlock <Self> _if (IJExpression expr)
  {
    return new ThenBlock <> (self (), block ()._if (expr));
  }

  public SubBlock <Self, ?> _do (IJExpression expr)
  {
    return new SubBlock <> (self (), block ()._do (expr).body ());
  }

  public SubBlock <Self, ?> _while (IJExpression expr)
  {
    return new SubBlock <> (self (), block ()._while (expr).body ());
  }

  public ForInitBuilder <Self> _for ()
  {
    ForBlock <Self> created = new ForBlock <> (self (), block ()._for ());
    return new ForInitBuilder <> (created);
  }

  public ForInitBuilder <Self> _for (AbstractJType aType, String sVarName, IJExpression aInitExpr)
  {
    return _for ().init (aType, sVarName, aInitExpr);
  }

  public SubBlock <Self, ?> subBlock (boolean bBracesRequired, boolean bIndentRequired)
  {
    JBlock created = block ().block (bBracesRequired, bIndentRequired);
    return new SubBlock <> (self (), created);
  }

  // variables management

  private Map <String, JVar> contextVariables = new HashMap <> ();

  void addVar (JVar var)
  {
    contextVariables.put (var.name (), var);
  }

  /**
   * find the variable with given name
   *
   * @param name
   *        name of the variable
   * @return null if not such a variable. Last scope variable with given name otherwise.
   */
  public JVar var (String name)
  {
    return contextVariables.get (name);
  }

}
