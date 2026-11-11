package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: IterableContainer con supporto all'iterazione mutabile. */
public interface MutableIterableContainer<T extends MutableIterableContainer<T, Data>, Data>
    extends IterableContainer<T, Data> {

  /* ************************************************************************ */
  /* Override specific member functions of IterableContainer                  */
  /* ************************************************************************ */

  @Override
  MutableForwardIterator<Data> FIterator();

  @Override
  MutableBackwardIterator<Data> BIterator();

}
