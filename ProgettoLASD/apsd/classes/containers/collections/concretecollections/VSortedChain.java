package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.ordered.SortedChain;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>>
    extends VChainBase<VSortedChain<Data>, Data>
    implements SortedChain<VSortedChain<Data>, Data> {

  /* ************************************************************************ */
  /* Fields and constructors */
  /* ************************************************************************ */

  public VSortedChain() {
    super();
  }

  public VSortedChain(TraversableContainer<?, Data> con) {
    super();
    if (con != null) {
      con.TraverseForward(dat -> {
        Insert(dat);
        return false;
      });
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  public VSortedChain<Data> Empty() {
    return new VSortedChain<>();
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data dat) {
    if (dat == null) {
      return false;
    }
    long low = 0;
    long high = Size().ToLong() - 1;
    long insPos = Size().ToLong();
    while (low <= high) {
      long mid = (low + high) >>> 1;
      Data midVal = vec.GetAt(Natural.Of(mid));
      int cmp = (midVal == null) ? -1 : midVal.compareTo(dat);
      if (cmp >= 0) {
        insPos = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    ((DynVector<?, Data>) vec).InsertAt(dat, Natural.Of(insPos));
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Chain */
  /* ************************************************************************ */

  @Override
  public boolean InsertIfAbsent(Data dat) {
    if (Exists(dat)) {
      return false;
    }
    return Insert(dat);
  }

  @Override
  public void RemoveOccurrences(Data dat) {
    while (Remove(dat)) {
    }
  }

}
