package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<T extends Vector<T, Data>, Data>
    extends ReallocableContainer<T>, MutableSequence<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  void ShiftLeft(Natural start, Natural end, Natural number);

  default void ShiftLeft(Natural position, Natural number) {
    ShiftLeft(position, Size(), number);
  }

  default void ShiftLeft(Natural position) {
    ShiftLeft(position, Natural.ONE);
  }

  default void ShiftFirstLeft() {
    ShiftLeft(Natural.ZERO);
  }

  default void ShiftLastLeft() {
    if (IsEmpty()) {
      return;
    }
    ShiftLeft(Natural.Of(Size().ToLong() - 1));
  }

  void ShiftRight(Natural start, Natural end, Natural number);

  default void ShiftRight(Natural position, Natural number) {
    ShiftRight(position, Size(), number);
  }

  default void ShiftRight(Natural position) {
    ShiftRight(position, Natural.ONE);
  }

  default void ShiftFirstRight() {
    ShiftRight(Natural.ZERO);
  }

  default void ShiftLastRight() {
    if (IsEmpty()) {
      return;
    }
    ShiftRight(Natural.Of(Size().ToLong() - 1));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  default Natural Size() {
    return ReallocableContainer.super.Size();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default T SubSequence(Natural from, Natural to) {
    T sub = Empty();
    if (from != null && to != null && from.compareTo(to) <= 0 && IsInBound(from)) {
      long s = from.ToLong();
      long e = Math.min(to.ToLong(), Size().ToLong() - 1);
      long len = e - s + 1;
      sub.Realloc(Natural.Of(len));
      for (long i = 0; i < len; i++) {
        sub.SetAt(GetAt(Natural.Of(s + i)), Natural.Of(i));
      }
    }
    return sub;
  }

}
