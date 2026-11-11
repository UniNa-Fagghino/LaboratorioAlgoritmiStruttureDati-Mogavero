package apsd.interfaces.containers.sequences.ordered;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ordered.SortedIterableContainer;

/** Interface: OrderedSequence & SortedIterableContainer. */
public interface SortedSequence<T extends SortedSequence<T, Data>, Data extends Comparable<? super Data>>
    extends OrderedSequence<T, Data>, SortedIterableContainer<T, Data> {

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Exists(Data dat) {
    return Search(dat) != null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedContainer                 */
  /* ************************************************************************ */

  @Override
  default Data Min() {
    if (IsEmpty()) {
      return null;
    }
    return GetFirst();
  }

  @Override
  default Data Max() {
    if (IsEmpty()) {
      return null;
    }
    return GetLast();
  }

  @Override
  default Data Predecessor(Data dat) {
    Natural pos = SearchPredecessor(dat);
    if (pos == null) {
      return null;
    }
    return GetAt(pos);
  }

  @Override
  default Data Successor(Data dat) {
    Natural pos = SearchSuccessor(dat);
    if (pos == null) {
      return null;
    }
    return GetAt(pos);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    long low = 0;
    long high = Size().ToLong() - 1;
    while (low <= high) {
      long mid = (low + high) >>> 1;
      Data midVal = GetAt(Natural.Of(mid));
      int cmp = (midVal == null) ? -1 : midVal.compareTo(dat);
      if (cmp < 0) {
        low = mid + 1;
      } else if (cmp > 0) {
        high = mid - 1;
      } else {
        return Natural.Of(mid);
      }
    }
    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSequence                  */
  /* ************************************************************************ */

  @Override
  default Natural SearchPredecessor(Data dat) {
    if (dat == null) {
      throw new NullPointerException("Data cannot be null!");
    }
    if (IsEmpty()) {
      return null;
    }
    long low = 0;
    long high = Size().ToLong() - 1;
    long best = -1;
    while (low <= high) {
      long mid = (low + high) >>> 1;
      Data midVal = GetAt(Natural.Of(mid));
      int cmp = (midVal == null) ? -1 : midVal.compareTo(dat);
      if (cmp < 0) {
        best = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return (best == -1) ? null : Natural.Of(best);
  }

  @Override
  default Natural SearchSuccessor(Data dat) {
    if (dat == null) {
      throw new NullPointerException("Data cannot be null!");
    }
    if (IsEmpty()) {
      return null;
    }
    long low = 0;
    long high = Size().ToLong() - 1;
    long best = -1;
    while (low <= high) {
      long mid = (low + high) >>> 1;
      Data midVal = GetAt(Natural.Of(mid));
      int cmp = (midVal == null) ? -1 : midVal.compareTo(dat);
      if (cmp > 0) {
        best = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return (best == -1) ? null : Natural.Of(best);
  }

}
