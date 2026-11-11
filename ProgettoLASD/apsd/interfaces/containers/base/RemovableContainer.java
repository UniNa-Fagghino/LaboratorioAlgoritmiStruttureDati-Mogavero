package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<T extends RemovableContainer<T, Data>, Data> extends Container<T> {

  /* ************************************************************************ */
  /* Specific member functions of RemovableContainer                          */
  /* ************************************************************************ */

  boolean Remove(Data dat);

  default boolean AllRemoved(TraversableContainer<?, Data> con) {
    if (con == null) {
      return false;
    }
    Box<Boolean> all = new Box<>(true);
    con.TraverseForward(dat -> {
      if (!Remove(dat)) {
        all.Set(false);
      }
      return false;
    });
    return all.Get();
  }

  default boolean SomeRemoved(TraversableContainer<?, Data> con) {
    if (con == null) {
      return false;
    }
    Box<Boolean> some = new Box<>(false);
    con.TraverseForward(dat -> {
      if (Remove(dat)) {
        some.Set(true);
      }
      return false;
    });
    return some.Get();
  }

}
