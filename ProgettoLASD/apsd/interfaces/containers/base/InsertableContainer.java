package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<T extends InsertableContainer<T, Data>, Data> extends Container<T> {

  /* ************************************************************************ */
  /* Specific member functions of InsertableContainer                         */
  /* ************************************************************************ */

  boolean Insert(Data dat);

  default boolean AllInserted(TraversableContainer<?, Data> con) {
    if (con == null) {
      return false;
    }
    Box<Boolean> all = new Box<>(true);
    con.TraverseForward(dat -> {
      if (!Insert(dat)) {
        all.Set(false);
      }
      return false;
    });
    return all.Get();
  }

  default boolean SomeInserted(TraversableContainer<?, Data> con) {
    if (con == null) {
      return false;
    }
    Box<Boolean> some = new Box<>(false);
    con.TraverseForward(dat -> {
      if (Insert(dat)) {
        some.Set(true);
      }
      return false;
    });
    return some.Get();
  }

}
