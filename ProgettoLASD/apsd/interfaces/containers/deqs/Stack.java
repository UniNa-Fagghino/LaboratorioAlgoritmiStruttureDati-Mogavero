package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<T extends Stack<T, Data>, Data>
    extends ClearableContainer<T>, InsertableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of Stack                                       */
  /* ************************************************************************ */

  Data Top();

  void Pop();

  default Data TopNPop() {
    Data dat = Top();
    Pop();
    return dat;
  }

  default void SwapTop(Data dat) {
    TopNSwap(dat);
  }

  default Data TopNSwap(Data newdat) {
    Data old = Top();
    Pop();
    Push(newdat);
    return old;
  }

  void Push(Data dat);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  void Clear();

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data dat) {
    Push(dat);
    return true;
  }

}
