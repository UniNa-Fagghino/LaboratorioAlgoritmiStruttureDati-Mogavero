package apsd.interfaces.containers.collections.ordered;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.ordered.SortedRemovableAtSequence;

public interface SortedChain<T extends SortedChain<T, Data>, Data extends Comparable<? super Data>>
    extends OrderedChain<T, Data>, SortedRemovableAtSequence<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of SortedChain                                 */
  /* ************************************************************************ */

  default void Intersection(SortedChain<?, Data> chn) {
    if (chn == null) {
      Clear();
    } else {
      Filter(chn::Exists);
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat) {
    return SortedRemovableAtSequence.super.Search(dat);
  }

}
