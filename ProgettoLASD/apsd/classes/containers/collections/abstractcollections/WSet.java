package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WSetBase;
import apsd.classes.containers.collections.concretecollections.VList;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;

/** Object: Wrapper set implementation via chain. */
public class WSet<Data> extends WSetBase<WSet<Data>, Data, Chain<?, Data>> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  public WSet() {
    super();
  }

  public WSet(TraversableContainer<?, Data> con) {
    super(con);
  }

  public WSet(Chain<?, Data> chn, TraversableContainer<?, Data> con) {
    super(chn, con);
  }

  /* ************************************************************************ */
  /* Specific member functions of WSet                                        */
  /* ************************************************************************ */

  @Override
  @SuppressWarnings("unchecked")
  protected void ChainAlloc() {
    chn = (Chain<?, Data>) (Chain<?, ?>) new VList<Data>();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public WSet<Data> Empty() {
    return new WSet<>();
  }

}
