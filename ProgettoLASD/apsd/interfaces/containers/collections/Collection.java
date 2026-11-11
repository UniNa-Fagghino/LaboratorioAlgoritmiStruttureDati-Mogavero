package apsd.interfaces.containers.collections;

import java.util.ArrayList;
import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.RemovableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

public interface Collection<T extends Collection<T, Data>, Data>
    extends ClearableContainer<T>, InsertableContainer<T, Data>, RemovableContainer<T, Data>, IterableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of Collection                                  */
  /* ************************************************************************ */

  default boolean InsertIfAbsent(Data dat) {
    if (!Exists(dat)) {
      return Insert(dat);
    }
    return false;
  }

  default void RemoveOccurrences(Data dat) {
    while (Remove(dat)) {}
  }

  default boolean Filter(Predicate<Data> fun) {
    if (fun == null) {
      return false;
    }
    boolean modified = false;
    ArrayList<Data> toRemove = new ArrayList<>();
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      Data dat = it.GetCurrent();
      if (!fun.Apply(dat)) {
        toRemove.add(dat);
      }
      it.Next();
    }
    for (Data dat : toRemove) {
      if (Remove(dat)) {
        modified = true;
      }
    }
    return modified;
  }

  default void Union(Collection<?, Data> col) {
    if (col != null) {
      col.TraverseForward(dat -> {
        Insert(dat);
        return false;
      });
    }
  }

  default void Difference(Collection<?, Data> col) {
    if (col != null) {
      col.TraverseForward(dat -> {
        Remove(dat);
        return false;
      });
    }
  }

  default void Intersection(Collection<?, Data> col) {
    if (col == null) {
      Clear();
    } else {
      Filter(col::Exists);
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  default void Clear() {
    while (!IsEmpty()) {
      ForwardIterator<Data> it = FIterator();
      if (!it.IsValid()) {
        break;
      }
      Remove(it.GetCurrent());
    }
  }

}
