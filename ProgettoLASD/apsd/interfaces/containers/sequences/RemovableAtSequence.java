package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.RemovableContainer;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<T extends RemovableAtSequence<T, Data>, Data>
    extends Sequence<T, Data>, RemovableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of RemovableAtSequence                         */
  /* ************************************************************************ */

  void RemoveAt(Natural pos);

  default Data AtNRemove(Natural pos) {
    Data dat = GetAt(pos);
    RemoveAt(pos);
    return dat;
  }

  default void RemoveFirst() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    RemoveAt(Natural.ZERO);
  }

  default Data FirstNRemove() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return AtNRemove(Natural.ZERO);
  }

  default void RemoveLast() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    RemoveAt(Natural.Of(Size().ToLong() - 1));
  }

  default Data LastNRemove() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return AtNRemove(Natural.Of(Size().ToLong() - 1));
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  default boolean Remove(Data dat) {
    Natural pos = Search(dat);
    if (pos != null) {
      RemoveAt(pos);
      return true;
    }
    return false;
  }

}
