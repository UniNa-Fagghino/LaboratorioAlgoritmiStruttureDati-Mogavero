package apsd.classes.containers.collections.concretecollections;

import java.util.Objects;
import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.ordered.SortedChain;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>>
    extends LLChainBase<LLSortedChain<Data>, Data>
    implements SortedChain<LLSortedChain<Data>, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  public LLSortedChain() {
    super();
  }

  public LLSortedChain(LLSortedChain<Data> chn) {
    super(chn);
  }

  public LLSortedChain(TraversableContainer<?, Data> con) {
    super(con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public LLSortedChain<Data> Empty() {
    return new LLSortedChain<>();
  }

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain                               */
  /* ************************************************************************ */

  protected LLNode<Data> PredFind(Data dat) {
    if (IsEmpty() || dat == null) {
      return null;
    }
    if (Objects.equals(headref.Get().Get(), dat)) {
      return null;
    }
    LLNode<Data> prev = headref.Get();
    while (prev.GetNext().Get() != null) {
      if (Objects.equals(prev.GetNext().Get().Get(), dat)) {
        return prev;
      }
      prev = prev.GetNext().Get();
    }
    return null;
  }

  protected LLNode<Data> PredPredFind(Data dat) {
    if (dat == null) {
      return null;
    }
    Data pred = Predecessor(dat);
    return PredFind(pred);
  }

  protected LLNode<Data> PredSuccFind(Data dat) {
    if (dat == null) {
      return null;
    }
    Data succ = Successor(dat);
    return PredFind(succ);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data dat) {
    if (dat == null) {
      return false;
    }
    if (IsEmpty() || headref.Get().Get().compareTo(dat) >= 0) {
      LLNode<Data> node = new LLNode<>(dat, headref.Get());
      headref.Set(node);
      if (size.IsZero()) {
        tailref.Set(node);
      }
      size.Increment();
      return true;
    }
    LLNode<Data> prev = headref.Get();
    while (prev.GetNext().Get() != null && prev.GetNext().Get().Get().compareTo(dat) < 0) {
      prev = prev.GetNext().Get();
    }
    LLNode<Data> node = new LLNode<>(dat, prev.GetNext().Get());
    prev.SetNext(node);
    if (prev == tailref.Get()) {
      tailref.Set(node);
    }
    size.Increment();
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) {
    if (IsEmpty() || dat == null) {
      return false;
    }
    if (Objects.equals(headref.Get().Get(), dat)) {
      FirstNRemove();
      return true;
    }
    LLNode<Data> pred = PredFind(dat);
    if (pred != null) {
      LLNode<Data> target = pred.GetNext().Get();
      pred.SetNext(target.GetNext().Get());
      if (target == tailref.Get()) {
        tailref.Set(pred);
      }
      size.Decrement();
      return true;
    }
    return false;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Natural Search(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    long idx = 0;
    Box<LLNode<Data>> curr = headref;
    while (curr != null && curr.Get() != null) {
      int cmp = curr.Get().Get().compareTo(dat);
      if (cmp == 0) {
        return Natural.Of(idx);
      }
      if (cmp > 0) {
        return null;
      }
      curr = curr.Get().GetNext();
      idx++;
    }
    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence                   */
  /* ************************************************************************ */

  @Override
  public Natural SearchPredecessor(Data dat) {
    if (dat == null) {
      throw new NullPointerException("Data cannot be null!");
    }
    if (IsEmpty()) {
      return null;
    }
    long best = -1;
    long idx = 0;
    Box<LLNode<Data>> curr = headref;
    while (curr != null && curr.Get() != null) {
      if (curr.Get().Get().compareTo(dat) < 0) {
        best = idx;
      } else {
        break;
      }
      curr = curr.Get().GetNext();
      idx++;
    }
    return (best == -1) ? null : Natural.Of(best);
  }

  @Override
  public Natural SearchSuccessor(Data dat) {
    if (dat == null) {
      throw new NullPointerException("Data cannot be null!");
    }
    if (IsEmpty()) {
      return null;
    }
    long idx = 0;
    Box<LLNode<Data>> curr = headref;
    while (curr != null && curr.Get() != null) {
      if (curr.Get().Get().compareTo(dat) > 0) {
        return Natural.Of(idx);
      }
      curr = curr.Get().GetNext();
      idx++;
    }
    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  @Override
  public Data Predecessor(Data dat) {
    Natural pos = SearchPredecessor(dat);
    if (pos == null) {
      return null;
    }
    return GetAt(pos);
  }

  @Override
  public void RemovePredecessor(Data dat) {
    PredecessorNRemove(dat);
  }

  @Override
  public Data PredecessorNRemove(Data dat) {
    Natural pos = SearchPredecessor(dat);
    if (pos == null) {
      return null;
    }
    return AtNRemove(pos);
  }

  @Override
  public Data Successor(Data dat) {
    Natural pos = SearchSuccessor(dat);
    if (pos == null) {
      return null;
    }
    return GetAt(pos);
  }

  @Override
  public void RemoveSuccessor(Data dat) {
    SuccessorNRemove(dat);
  }

  @Override
  public Data SuccessorNRemove(Data dat) {
    Natural pos = SearchSuccessor(dat);
    if (pos == null) {
      return null;
    }
    return AtNRemove(pos);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  @Override
  public boolean InsertIfAbsent(Data dat) {
    if (Exists(dat)) {
      return false;
    }
    return Insert(dat);
  }

  @Override
  public void RemoveOccurrences(Data dat) {
    while (Remove(dat)) {}
  }

}
