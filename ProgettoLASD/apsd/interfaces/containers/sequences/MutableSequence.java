package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<T extends MutableSequence<T, Data>, Data>
    extends Sequence<T, Data>, MutableIterableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of MutableSequence                             */
  /* ************************************************************************ */

  void SetAt(Data dat, Natural pos);

  default Data GetNSetAt(Data newdat, Natural pos) {
    Data old = GetAt(pos);
    SetAt(newdat, pos);
    return old;
  }

  default void SetFirst(Data dat) {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    SetAt(dat, Natural.ZERO);
  }

  default Data GetNSetFirst(Data newdat) {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return GetNSetAt(newdat, Natural.ZERO);
  }

  default void SetLast(Data dat) {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    SetAt(dat, Natural.Of(Size().ToLong() - 1));
  }

  default Data GetNSetLast(Data newdat) {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return GetNSetAt(newdat, Natural.Of(Size().ToLong() - 1));
  }

  default void Swap(Natural fst, Natural snd) {
    ExcIfOutOfBound(fst);
    ExcIfOutOfBound(snd);
    if (!fst.equals(snd)) {
      Data temp = GetAt(fst);
      SetAt(GetAt(snd), fst);
      SetAt(temp, snd);
    }
  }

}
