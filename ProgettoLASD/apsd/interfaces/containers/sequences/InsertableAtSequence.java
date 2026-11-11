package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.InsertableContainer;

/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<T extends InsertableAtSequence<T, Data>, Data>
    extends Sequence<T, Data>, InsertableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of InsertableAtSequence                        */
  /* ************************************************************************ */

  void InsertAt(Data dat, Natural pos);

  default void InsertFirst(Data dat) {
    InsertAt(dat, Natural.ZERO);
  }

  default void InsertLast(Data dat) {
    InsertAt(dat, Size());
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data dat) {
    InsertLast(dat);
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default T SubSequence(Natural from, Natural to) {
    T sub = Empty();
    if (from != null && to != null && from.compareTo(to) <= 0 && IsInBound(from)) {
      long end = Math.min(to.ToLong(), Size().ToLong() - 1);
      for (long i = from.ToLong(); i <= end; i++) {
        sub.InsertLast(GetAt(Natural.Of(i)));
      }
    }
    return sub;
  }

}
