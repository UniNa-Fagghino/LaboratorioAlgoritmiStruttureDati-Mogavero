package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data> {

  /* ************************************************************************ */
  /* Specific member functions of BackwardIterator                            */
  /* ************************************************************************ */

  void Prev();

  default void Prev(long steps) {
    while (steps > 0 && IsValid()) {
      Prev();
      steps--;
    }
  }

  default void Prev(Natural steps) {
    if (steps != null) {
      Prev(steps.ToLong());
    }
  }

  default Data DataNPrev() {
    Data dat = GetCurrent();
    Prev();
    return dat;
  }

  default Data DatNPrev() {
    return DataNPrev();
  }

  default boolean ForEachBackward(Predicate<Data> pred) {
    if (pred != null) {
      while (IsValid()) {
        if (pred.Apply(GetCurrent())) {
          return true;
        }
        Prev();
      }
    }
    return false;
  }

}
