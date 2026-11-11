package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<T extends Chain<T, Data>, Data>
    extends Collection<T, Data>, RemovableAtSequence<T, Data> {

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  default boolean Remove(Data dat) {
    return RemovableAtSequence.super.Remove(dat);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat) {
    return RemovableAtSequence.super.Search(dat);
  }

}
