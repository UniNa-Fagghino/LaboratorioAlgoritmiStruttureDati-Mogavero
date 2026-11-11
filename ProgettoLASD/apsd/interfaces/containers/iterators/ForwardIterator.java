package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data> {

  /* ************************************************************************ */
  /* Specific member functions of ForwardIterator                             */
  /* ************************************************************************ */

  void Next();

  default void Next(long steps) {
    while (steps > 0 && IsValid()) {
      Next();
      steps--;
    }
  }

  default void Next(Natural steps) {
    if (steps != null) {
      Next(steps.ToLong());
    }
  }

  default Data DataNNext() {
    Data dat = GetCurrent();
    Next();
    return dat;
  }

  default Data DatNNext() {
    return DataNNext();
  }

  default boolean ForEachForward(Predicate<Data> pred) {
    if (pred != null) {
      while (IsValid()) {
        if (pred.Apply(GetCurrent())) {
          return true;
        }
        Next();
      }
    }
    return false;
  }

}
