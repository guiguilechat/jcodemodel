package com.helger.jcodemodel.blockmodel;

import com.helger.jcodemodel.JForLoop;

public class ForBlock <T extends ChainedBlock <?>> extends SubBlock <T, ForBlock <T>>
{

  private final JForLoop forLoop;

  public JForLoop forLoop ()
  {
    return forLoop;
  }

  public ForBlock (T up, JForLoop forloop)
  {
    super (up, forloop.body ());
    this.forLoop = forloop;
  }

}
