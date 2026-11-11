package apsd.interfaces.containers.base.ordered;

import apsd.interfaces.containers.base.MembershipContainer;

/** Interface: MembershipContainer con dati ordinati. */
public interface OrderedContainer<T extends OrderedContainer<T, Data>, Data extends Comparable<? super Data>>
    extends MembershipContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of OrderedContainer                            */
  /* ************************************************************************ */

  Data Min();

  Data Max();

  Data Predecessor(Data dat);

  Data Successor(Data dat);

}
