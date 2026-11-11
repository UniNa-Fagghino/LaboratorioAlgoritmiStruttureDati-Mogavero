package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<T extends IterableContainer<T, Data>, Data>
    extends TraversableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of IterableContainer                           */
  /* ************************************************************************ */

  ForwardIterator<Data> FIterator();

  BackwardIterator<Data> BIterator();

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  @Override
  default boolean TraverseForward(Predicate<Data> pred) {
    ForwardIterator<Data> it = FIterator();
    if (it == null) {
      return false;
    }
    return it.ForEachForward(pred);
  }

  @Override
  default boolean TraverseBackward(Predicate<Data> pred) {
    BackwardIterator<Data> it = BIterator();
    if (it == null) {
      return false;
    }
    return it.ForEachBackward(pred);
  }

}
