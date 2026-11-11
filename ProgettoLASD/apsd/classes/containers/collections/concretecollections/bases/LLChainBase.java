package apsd.classes.containers.collections.concretecollections.bases;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<T extends LLChainBase<T, Data>, Data> implements Chain<T, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  protected final MutableNatural size = new MutableNatural();
  protected final Box<LLNode<Data>> headref = new Box<>();
  protected final Box<LLNode<Data>> tailref = new Box<>();

  public LLChainBase() {}

  public LLChainBase(TraversableContainer<?, Data> con) {
    if (con != null) {
      con.TraverseForward(dat -> {
        Insert(dat);
        return false;
      });
    }
  }

  /* ************************************************************************ */
  /* Specific member functions of LLChainBase                                 */
  /* ************************************************************************ */

  protected class ListFRefIterator implements MutableForwardIterator<Box<LLNode<Data>>> {
    private Box<LLNode<Data>> curr = headref;

    @Override
    public boolean IsValid() {
      return curr != null && curr.Get() != null;
    }

    @Override
    public void Reset() {
      curr = headref;
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      return curr;
    }

    @Override
    public void Next() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      curr = curr.Get().GetNext();
    }

    @Override
    public void SetCurrent(Box<LLNode<Data>> box) {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      if (box != null) {
        curr.Set(box.Get());
      }
    }
  }

  protected ForwardIterator<Box<LLNode<Data>>> FRefIterator() {
    return new ListFRefIterator();
  }

  protected class ListBRefIterator implements MutableBackwardIterator<Box<LLNode<Data>>> {
    private final ArrayList<Box<LLNode<Data>>> refs = new ArrayList<>();
    private int currIdx = -1;

    public ListBRefIterator() {
      Reset();
    }

    @Override
    public boolean IsValid() {
      return currIdx >= 0 && currIdx < refs.size();
    }

    @Override
    public void Reset() {
      refs.clear();
      Box<LLNode<Data>> it = headref;
      while (it != null && it.Get() != null) {
        refs.add(it);
        it = it.Get().GetNext();
      }
      currIdx = refs.size() - 1;
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      return refs.get(currIdx);
    }

    @Override
    public void Prev() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      currIdx--;
    }

    @Override
    public void SetCurrent(Box<LLNode<Data>> box) {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      if (box != null) {
        refs.get(currIdx).Set(box.Get());
      }
    }
  }

  protected BackwardIterator<Box<LLNode<Data>>> BRefIterator() {
    return new ListBRefIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() {
    return Natural.Of(size.ToLong());
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    headref.Set(null);
    tailref.Set(null);
    size.Zero();
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) {
    Box<LLNode<Data>> prev = null;
    Box<LLNode<Data>> curr = headref;
    while (curr != null && curr.Get() != null) {
      if (Objects.equals(curr.Get().Get(), dat)) {
        if (prev == null) {
          FirstNRemove();
        } else if (curr == tailref || curr.Get() == tailref.Get()) {
          LastNRemove();
        } else {
          prev.Get().SetNext(curr.Get().GetNext().Get());
          size.Decrement();
        }
        return true;
      }
      prev = curr;
      curr = curr.Get().GetNext();
    }
    return false;
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  protected class ListFIterator implements MutableForwardIterator<Data> {
    private final ForwardIterator<Box<LLNode<Data>>> refIt = FRefIterator();

    @Override
    public boolean IsValid() {
      return refIt.IsValid();
    }

    @Override
    public void Reset() {
      refIt.Reset();
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      return refIt.GetCurrent().Get().Get();
    }

    @Override
    public void Next() {
      refIt.Next();
    }

    @Override
    public void SetCurrent(Data dat) {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      refIt.GetCurrent().Get().Set(dat);
    }
  }

  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new ListFIterator();
  }

  protected class ListBIterator implements MutableBackwardIterator<Data> {
    private final BackwardIterator<Box<LLNode<Data>>> refIt = BRefIterator();

    @Override
    public boolean IsValid() {
      return refIt.IsValid();
    }

    @Override
    public void Reset() {
      refIt.Reset();
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      return refIt.GetCurrent().Get().Get();
    }

    @Override
    public void Prev() {
      refIt.Prev();
    }

    @Override
    public void SetCurrent(Data dat) {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      refIt.GetCurrent().Get().Set(dat);
    }
  }

  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new ListBIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    Box<LLNode<Data>> curr = headref;
    for (long i = 0; i < idx; i++) {
      curr = curr.Get().GetNext();
    }
    return curr.Get().Get();
  }

  @Override
  public Data GetFirst() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("List is empty!");
    }
    return headref.Get().Get();
  }

  @Override
  public Data GetLast() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("List is empty!");
    }
    return tailref.Get().Get();
  }

  @Override
  public T SubSequence(Natural from, Natural to) {
    T sub = Empty();
    if (from != null && to != null && from.compareTo(to) <= 0 && IsInBound(from)) {
      long s = from.ToLong();
      long e = Math.min(to.ToLong(), size.ToLong() - 1);
      Box<LLNode<Data>> curr = headref;
      for (long i = 0; i < s; i++) {
        curr = curr.Get().GetNext();
      }
      for (long i = s; i <= e; i++) {
        sub.Insert(curr.Get().Get());
        curr = curr.Get().GetNext();
      }
    }
    return sub;
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  public Data FirstNRemove() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("List is empty!");
    }
    Data dat = headref.Get().Get();
    headref.Set(headref.Get().GetNext().Get());
    size.Decrement();
    if (size.IsZero()) {
      tailref.Set(null);
    }
    return dat;
  }

  @Override
  public void RemoveFirst() {
    FirstNRemove();
  }

  @Override
  public Data LastNRemove() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("List is empty!");
    }
    if (size.ToLong() == 1) {
      return FirstNRemove();
    }
    Box<LLNode<Data>> curr = headref;
    while (curr.Get().GetNext().Get() != tailref.Get()) {
      curr = curr.Get().GetNext();
    }
    Data dat = tailref.Get().Get();
    curr.Get().SetNext(null);
    tailref.Set(curr.Get());
    size.Decrement();
    return dat;
  }

  @Override
  public void RemoveLast() {
    LastNRemove();
  }

  @Override
  public Data AtNRemove(Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    if (idx == 0) {
      return FirstNRemove();
    }
    if (idx == size.ToLong() - 1) {
      return LastNRemove();
    }
    Box<LLNode<Data>> prev = headref;
    for (long i = 0; i < idx - 1; i++) {
      prev = prev.Get().GetNext();
    }
    Box<LLNode<Data>> target = prev.Get().GetNext();
    Data dat = target.Get().Get();
    prev.Get().SetNext(target.Get().GetNext().Get());
    size.Decrement();
    return dat;
  }

  @Override
  public void RemoveAt(Natural pos) {
    AtNRemove(pos);
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
    Box<LLNode<Data>> prev = null;
    Box<LLNode<Data>> curr = headref;
    while (curr != null && curr.Get() != null) {
      Data dat = curr.Get().Get();
      if (!fun.Apply(dat)) {
        modified = true;
        if (prev == null) {
          headref.Set(curr.Get().GetNext().Get());
          size.Decrement();
          if (size.IsZero()) {
            tailref.Set(null);
          }
          curr = headref;
          continue;
        } else {
          prev.Get().SetNext(curr.Get().GetNext().Get());
          if (curr.Get() == tailref.Get()) {
            tailref.Set(prev.Get());
          }
          size.Decrement();
          curr = prev.Get().GetNext();
          continue;
        }
      }
      prev = curr;
      curr = curr.Get().GetNext();
    }
    return modified;
  }

}
