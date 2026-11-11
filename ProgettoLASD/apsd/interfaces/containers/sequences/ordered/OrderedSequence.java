package apsd.interfaces.containers.sequences.ordered;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ordered.OrderedIterableContainer;
import apsd.interfaces.containers.sequences.Sequence;

/** Interface: Sequence & OrderedIterableContainer. */
public interface OrderedSequence<T extends OrderedSequence<T, Data>, Data extends Comparable<? super Data>>
    extends Sequence<T, Data>, OrderedIterableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of OrderedSequence                             */
  /* ************************************************************************ */

  default Natural SearchPredecessor(Data dat) {
    if (dat == null) {
      throw new NullPointerException("Data cannot be null!");
    }
    Data pred = Predecessor(dat);
    return (pred == null) ? null : Search(pred);
  }

  default Natural SearchSuccessor(Data dat) {
    if (dat == null) {
      throw new NullPointerException("Data cannot be null!");
    }
    Data succ = Successor(dat);
    return (succ == null) ? null : Search(succ);
  }

}
