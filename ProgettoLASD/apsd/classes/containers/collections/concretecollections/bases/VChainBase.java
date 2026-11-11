package apsd.classes.containers.collections.concretecollections.bases;

import java.util.Objects;
import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract list base implementation on (dynamic circular) vector. */
abstract public class VChainBase<T extends VChainBase<T, Data>, Data> implements Chain<T, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  protected DynVector<?, Data> vec = new DynCircularVector<>();

  public VChainBase() {
    vec = new DynCircularVector<>();
  }

  public VChainBase(TraversableContainer<?, Data> con) {
    vec = new DynCircularVector<>(con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() {
    return vec.Size();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    vec.Clear();
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  public ForwardIterator<Data> FIterator() {
    return vec.FIterator();
  }

  @Override
  public BackwardIterator<Data> BIterator() {
    return vec.BIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural pos) {
    return vec.GetAt(pos);
  }

  @Override
  public Data GetFirst() {
    return vec.GetFirst();
  }

  @Override
  public Data GetLast() {
    return vec.GetLast();
  }

  @Override
  public T SubSequence(Natural from, Natural to) {
    T sub = Empty();
    if (from != null && to != null && from.compareTo(to) <= 0 && IsInBound(from)) {
      long s = from.ToLong();
      long e = Math.min(to.ToLong(), Size().ToLong() - 1);
      for (long i = s; i <= e; i++) {
        sub.Insert(vec.GetAt(Natural.Of(i)));
      }
    }
    return sub;
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  public void RemoveAt(Natural pos) {
    vec.RemoveAt(pos);
  }

  @Override
  public Data AtNRemove(Natural pos) {
    return vec.AtNRemove(pos);
  }

  @Override
  public void RemoveFirst() {
    vec.RemoveFirst();
  }

  @Override
  public Data FirstNRemove() {
    return vec.FirstNRemove();
  }

  @Override
  public void RemoveLast() {
    vec.RemoveLast();
  }

  @Override
  public Data LastNRemove() {
    return vec.LastNRemove();
  }

  @Override
  public boolean Remove(Data dat) {
    for (long i = 0; i < Size().ToLong(); i++) {
      if (Objects.equals(vec.GetAt(Natural.Of(i)), dat)) {
        vec.AtNRemove(Natural.Of(i));
        return true;
      }
    }
    return false;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  @Override
  public boolean Filter(Predicate<Data> fun) {
    if (fun == null) {
      return false;
    }
    boolean modified = false;
    long i = 0;
    while (i < Size().ToLong()) {
      Data dat = vec.GetAt(Natural.Of(i));
      if (!fun.Apply(dat)) {
        vec.AtNRemove(Natural.Of(i));
        modified = true;
      } else {
        i++;
      }
    }
    return modified;
  }

}
