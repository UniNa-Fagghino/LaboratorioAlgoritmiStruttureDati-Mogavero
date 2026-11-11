package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<LLList<Data>, Data> implements List<LLList<Data>, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  public LLList() {
    super();
  }

  public LLList(TraversableContainer<?, Data> con) {
    super(con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public LLList<Data> Empty() {
    return new LLList<>();
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  @Override
  public MutableForwardIterator<Data> FIterator() {
    return super.FIterator();
  }

  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return super.BIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    Box<LLNode<Data>> curr = headref;
    for (long i = 0; i < idx; i++) {
      curr = curr.Get().GetNext();
    }
    curr.Get().Set(dat);
  }

  @Override
  public void SetFirst(Data dat) {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("List is empty!");
    }
    headref.Get().Set(dat);
  }

  @Override
  public void SetLast(Data dat) {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("List is empty!");
    }
    tailref.Get().Set(dat);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  public void InsertFirst(Data dat) {
    LLNode<Data> node = new LLNode<>(dat, headref.Get());
    headref.Set(node);
    if (size.IsZero()) {
      tailref.Set(node);
    }
    size.Increment();
  }

  @Override
  public void InsertLast(Data dat) {
    LLNode<Data> node = new LLNode<>(dat);
    if (size.IsZero()) {
      headref.Set(node);
      tailref.Set(node);
    } else {
      tailref.Get().SetNext(node);
      tailref.Set(node);
    }
    size.Increment();
  }

  @Override
  public void InsertAt(Data dat, Natural pos) {
    long p = (pos == null) ? 0 : pos.ToLong();
    if (p < 0 || p > size.ToLong()) {
      throw new IndexOutOfBoundsException("Position out of bounds: " + p);
    }
    if (p == 0) {
      InsertFirst(dat);
    } else if (p == size.ToLong()) {
      InsertLast(dat);
    } else {
      Box<LLNode<Data>> prev = headref;
      for (long i = 0; i < p - 1; i++) {
        prev = prev.Get().GetNext();
      }
      LLNode<Data> node = new LLNode<>(dat, prev.Get().GetNext().Get());
      prev.Get().SetNext(node);
      size.Increment();
    }
  }

  @Override
  public boolean Insert(Data dat) {
    InsertLast(dat);
    return true;
  }

}
