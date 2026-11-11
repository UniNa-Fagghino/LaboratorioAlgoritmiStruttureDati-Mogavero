package apsd.interfaces.containers.base.ordered;

import apsd.classes.utilities.Box;
import apsd.interfaces.containers.base.TraversableContainer;

/** Interface: OrderedContainer & TraversableContainer. */
public interface OrderedTraversableContainer<T extends OrderedTraversableContainer<T, Data>, Data extends Comparable<? super Data>>
    extends OrderedContainer<T, Data>, TraversableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of OrderedTraversableContainer                 */
  /* ************************************************************************ */

  @Override
  default Data Min() {
    if (IsEmpty()) {
      return null;
    }
    Box<Data> minBox = new Box<>(null);
    TraverseForward(dat -> {
      if (minBox.Get() == null || (dat != null && dat.compareTo(minBox.Get()) < 0)) {
        minBox.Set(dat);
      }
      return false;
    });
    return minBox.Get();
  }

  @Override
  default Data Max() {
    if (IsEmpty()) {
      return null;
    }
    Box<Data> maxBox = new Box<>(null);
    TraverseForward(dat -> {
      if (maxBox.Get() == null || (dat != null && dat.compareTo(maxBox.Get()) > 0)) {
        maxBox.Set(dat);
      }
      return false;
    });
    return maxBox.Get();
  }

  @Override
  default Data Predecessor(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    Box<Data> predBox = new Box<>(null);
    TraverseForward(d -> {
      if (d != null && d.compareTo(dat) < 0) {
        if (predBox.Get() == null || d.compareTo(predBox.Get()) > 0) {
          predBox.Set(d);
        }
      }
      return false;
    });
    return predBox.Get();
  }

  @Override
  default Data Successor(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    Box<Data> succBox = new Box<>(null);
    TraverseForward(d -> {
      if (d != null && d.compareTo(dat) > 0) {
        if (succBox.Get() == null || d.compareTo(succBox.Get()) < 0) {
          succBox.Set(d);
        }
      }
      return false;
    });
    return succBox.Get();
  }

}
