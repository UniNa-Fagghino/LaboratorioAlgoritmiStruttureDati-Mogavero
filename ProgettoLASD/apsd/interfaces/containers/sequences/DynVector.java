package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<T extends DynVector<T, Data>, Data>
    extends ResizableContainer<T>, InsertableAtSequence<T, Data>, RemovableAtSequence<T, Data>, Vector<T, Data> {

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  default void InsertAt(Data dat, Natural pos) {
    if (pos == null || pos.ToLong() > Size().ToLong()) {
      throw new IndexOutOfBoundsException("Position out of bounds: " + pos);
    }
    Expand();
    ShiftRight(pos);
    SetAt(dat, pos);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default void RemoveAt(Natural pos) {
    AtNRemove(pos);
  }

  @Override
  default Data AtNRemove(Natural pos) {
    ExcIfOutOfBound(pos);
    Data dat = GetAt(pos);
    ShiftLeft(Natural.Of(pos.ToLong() + 1));
    Reduce();
    return dat;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default T SubSequence(Natural from, Natural to) {
    return InsertableAtSequence.super.SubSequence(from, to);
  }

}
