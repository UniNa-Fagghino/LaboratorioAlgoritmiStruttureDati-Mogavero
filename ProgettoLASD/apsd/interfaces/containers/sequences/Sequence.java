package apsd.interfaces.containers.sequences;

import java.util.Objects;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<T extends Sequence<T, Data>, Data> extends IterableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of Sequence                                    */
  /* ************************************************************************ */

  Data GetAt(Natural pos);

  default Data GetFirst() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return GetAt(Natural.ZERO);
  }

  default Data GetLast() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return GetAt(Natural.Of(Size().ToLong() - 1));
  }

  default Natural Search(Data dat) {
    ForwardIterator<Data> it = FIterator();
    long idx = 0;
    while (it.IsValid()) {
      if (Objects.equals(it.GetCurrent(), dat)) {
        return Natural.Of(idx);
      }
      it.Next();
      idx++;
    }
    return null;
  }

  default boolean IsInBound(Natural num) {
    return num != null && num.ToLong() < Size().ToLong();
  }

  default long ExcIfOutOfBound(Natural num) {
    if (!IsInBound(num)) {
      throw new IndexOutOfBoundsException("Position out of bounds: " + num);
    }
    return num.ToLong();
  }

  default boolean IsEqual(Sequence<?, Data> seq) {
    if (seq == this) {
      return true;
    }
    if (seq == null) {
      return false;
    }
    if (Size().ToLong() != seq.Size().ToLong()) {
      return false;
    }
    ForwardIterator<Data> it1 = FIterator();
    ForwardIterator<Data> it2 = seq.FIterator();
    while (it1.IsValid() && it2.IsValid()) {
      if (!Objects.equals(it1.GetCurrent(), it2.GetCurrent())) {
        return false;
      }
      it1.Next();
      it2.Next();
    }
    return !it1.IsValid() && !it2.IsValid();
  }

  T SubSequence(Natural from, Natural to);

}
