package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<WStack<Data>, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  protected final List<?, Data> lst;

  public WStack() {
    this.lst = new VList<>();
  }

  @SuppressWarnings("unchecked")
  public WStack(List<?, Data> lst) {
    this.lst = (lst == null) ? new VList<>() : (List<?, Data>) ((List<?, ?>) lst.Empty());
  }

  public WStack(TraversableContainer<?, Data> con) {
    this.lst = new VList<>(con);
  }

  @SuppressWarnings("unchecked")
  public WStack(List<?, Data> lst, TraversableContainer<?, Data> con) {
    List<?, Data> target = (lst == null) ? new VList<>() : (List<?, Data>) ((List<?, ?>) lst.Empty());
    if (con != null) {
      con.TraverseForward(dat -> {
        ((List<?, Data>) target).Insert(dat);
        return false;
      });
    }
    this.lst = target;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() {
    return lst.Size();
  }

  @Override
  public WStack<Data> Empty() {
    return new WStack<>(lst.Empty());
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    lst.Clear();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Stack                            */
  /* ************************************************************************ */

  @Override
  public Data Top() {
    if (lst.IsEmpty()) {
      return null;
    }
    return lst.GetLast();
  }

  @Override
  public void Pop() {
    if (!lst.IsEmpty()) {
      lst.RemoveLast();
    }
  }

  @Override
  public Data TopNPop() {
    if (lst.IsEmpty()) {
      return null;
    }
    return lst.LastNRemove();
  }

  @Override
  public void SwapTop(Data dat) {
    if (lst.IsEmpty()) {
      Push(dat);
    } else {
      lst.SetLast(dat);
    }
  }

  @Override
  public Data TopNSwap(Data newdat) {
    if (lst.IsEmpty()) {
      Push(newdat);
      return null;
    }
    return lst.GetNSetLast(newdat);
  }

  @Override
  public void Push(Data dat) {
    lst.InsertLast(dat);
  }

}
