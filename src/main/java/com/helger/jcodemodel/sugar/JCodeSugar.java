package com.helger.jcodemodel.sugar;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JAtom;
import com.helger.jcodemodel.JAtomDouble;
import com.helger.jcodemodel.JAtomFloat;
import com.helger.jcodemodel.JAtomInt;
import com.helger.jcodemodel.JAtomLong;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JInvocation;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JStringLiteral;
import com.helger.jcodemodel.compile.DynamicClassLoader;
import com.helger.jcodemodel.compile.MemoryCodeWriter;
import com.helger.jcodemodel.exceptions.JCodeModelException;
import com.helger.jcodemodel.writer.JCMWriter;

public class JCodeSugar
{

  private final JCodeModel jcm;

  public JCodeSugar ()
  {
    this (new JCodeModel (), null, null, null, null);
  }

  public JCodeSugar (JCodeModel codemodel, JCMCreate create, JCMExpr expr, JCMRef ref, JCMWrite write)
  {
    jcm = codemodel;
    this.create = create == null ? new JCMCreate () : create;
    this.expr = expr == null ? new JCMExpr () : expr;
    this.ref = ref == null ? new JCMRef () : ref;
    this.write = write == null ? new JCMWrite () : write;
  }

  public class JCMCreate
  {
    @Nonnull
    public JDefinedClass _class (@Nonnull final String sFullyQualifiedClassName) throws JCodeModelException
    {
      return jcm._class (sFullyQualifiedClassName);
    }

    @Nonnull
    public JDefinedClass _class (final int nMods, @Nonnull final String sFullyQualifiedClassName)
        throws JCodeModelException
    {
      return jcm._class (nMods, sFullyQualifiedClassName);
    }

    @Nonnull
    public JDefinedClass _class (
        final int nMods,
        @Nonnull final String sFullyQualifiedClassName,
        @Nonnull final EClassType eClassType) throws JCodeModelException
    {
      return jcm._class (nMods, sFullyQualifiedClassName, eClassType);
    }

    @Nonnull
    public JPackage _package (@Nonnull final String sName)
    {
      return jcm._package (sName);
    }
  }

  public final transient JCMCreate create;

  public JCMCreate create ()
  {
    return create;
  }

  public class JCMExpr
  {

    @Nonnull
    public JInvocation _new (@Nonnull final AbstractJType aType)
    {
      return JExpr._new (aType);
    }

    @Nonnull
    public JAtom _this ()
    {
      return JExpr._this ();
    }

    @Nonnull
    public JAtom _super ()
    {
      return JExpr._super ();
    }

    @Nonnull
    public IJExpression dotClass (@Nonnull final AbstractJType aClass)
    {
      return JExpr.dotClass (aClass);
    }

    public AbstractJClass wildcard ()
    {
      return jcm.wildcard ();
    }
  }

  public final transient JCMExpr expr;

  public JCMExpr expr ()
  {
    return expr;
  }

  public class JCMRef
  {

    @Nonnull
    public JAtom _bool (final boolean n)
    {
      return JExpr.lit (n);
    }

    @Nonnull
    public JAtom _char (final char n)
    {
      return JExpr.lit (n);
    }

    @Nonnull
    public AbstractJType _class (@Nonnull final Class <?> aClass)
    {
      return jcm._ref (aClass);
    }

    @Nonnull
    public JAtomDouble _double (final double n)
    {
      return JExpr.lit (n);
    }

    @Nonnull
    public JAtomFloat _float (final float n)
    {
      return JExpr.lit (n);
    }

    @Nonnull
    public JAtomInt _int (final int n)
    {
      return JExpr.lit (n);
    }

    @Nonnull
    public JAtomLong _long (final long n)
    {
      return JExpr.lit (n);
    }

    public JAtom _null ()
    {
      return JExpr._null ();
    }

    public JStringLiteral string (@Nonnull final String sStr)
    {
      return JExpr.lit (sStr);
    }

  }

  public final transient JCMRef ref;

  public JCMRef ref ()
  {
    return ref;
  }

  public class JCMWrite
  {

    public void toFile (File f) throws IOException
    {
      new JCMWriter (jcm).build (f);
    }

    public DynamicClassLoader toMemory ()
    {
      return MemoryCodeWriter.from (jcm).compile ();
    }
  }

  public final transient JCMWrite write;

  public JCMWrite write ()
  {
    return write;
  }

}
