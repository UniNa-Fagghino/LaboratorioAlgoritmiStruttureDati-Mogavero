package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.TraversableContainer;

public interface Set<T extends Set<T, Data>, Data> extends Collection<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of Set */
  /* ************************************************************************ */

  default boolean IsEqual(Set<?, Data> set) {
    if (set == this) {
      return true;
    }
    if (set == null) {
      return false;
    }
    if (Size().ToLong() != set.Size().ToLong()) {
      return false;
    }
    boolean mismatch = TraverseForward(dat -> !set.Exists(dat));
    return !mismatch;
  }

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer */
  /* ************************************************************************ */

  @Override
  default boolean IsEqual(TraversableContainer<?, Data> con) {
    if (con instanceof Set) {
      return IsEqual((Set<?, Data>) con);
    }
    return Collection.super.IsEqual(con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection */
  /* ************************************************************************ */

  @Override
  default void Intersection(Collection<?, Data> col) {
    if (col == null) {
      Clear();
    } else {
      Filter(col::Exists);
    }
  }

}
