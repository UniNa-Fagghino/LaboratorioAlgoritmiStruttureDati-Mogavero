package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<T extends Queue<T, Data>, Data>
    extends ClearableContainer<T>, InsertableContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of Queue                                       */
  /* ************************************************************************ */

  Data Head();

  void Dequeue();

  default Data HeadNDequeue() {
    Data dat = Head();
    Dequeue();
    return dat;
  }

  void Enqueue(Data dat);

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
    Enqueue(dat);
    return true;
  }

}
