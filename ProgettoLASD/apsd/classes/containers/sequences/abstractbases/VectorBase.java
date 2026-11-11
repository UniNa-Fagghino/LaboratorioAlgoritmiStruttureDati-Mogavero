package apsd.classes.containers.sequences.abstractbases;

import java.util.NoSuchElementException;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<T extends VectorBase<T, Data>, Data> implements Vector<T, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  protected Data[] arr = null;

  public VectorBase() {
    ArrayAlloc(Natural.ZERO);
  }

  public VectorBase(Natural inisize) {
    ArrayAlloc(inisize);
  }

  public VectorBase(TraversableContainer<?, Data> con) {
    if (con == null) {
      ArrayAlloc(Natural.ZERO);
    } else {
      ArrayAlloc(con.Size());
      MutableNatural idx = new MutableNatural(0);
      con.TraverseForward(dat -> {
        arr[(int) idx.GetLongNIncrement()] = dat;
        return false;
      });
    }
  }

  /* ************************************************************************ */
  /* Specific member functions of VectorBase                                  */
  /* ************************************************************************ */

  @SuppressWarnings("unchecked")
  protected void ArrayAlloc(Natural dim) {
    long d = (dim == null) ? 0 : dim.ToLong();
    arr = (Data[]) new Object[(int) d];
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    ArrayAlloc(Natural.ZERO);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  @Override
  public Natural Capacity() {
    return Natural.Of(arr == null ? 0 : arr.length);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  protected class VectorFIterator implements MutableForwardIterator<Data> {
    private long curr = 0L;

    @Override
    public boolean IsValid() {
      return curr < Size().ToLong();
    }

    @Override
    public void Reset() {
      curr = 0L;
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      return GetAt(Natural.Of(curr));
    }

    @Override
    public void Next() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      curr++;
    }

    @Override
    public void SetCurrent(Data dat) {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      SetAt(dat, Natural.Of(curr));
    }
  }

  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new VectorFIterator();
  }

  protected class VectorBIterator implements MutableBackwardIterator<Data> {
    private long curr = Size().ToLong() - 1;

    @Override
    public boolean IsValid() {
      return curr >= 0 && curr < Size().ToLong();
    }

    @Override
    public void Reset() {
      curr = Size().ToLong() - 1;
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      return GetAt(Natural.Of(curr));
    }

    @Override
    public void Prev() {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      curr--;
    }

    @Override
    public void SetCurrent(Data dat) {
      if (!IsValid()) {
        throw new NoSuchElementException("Iterator is not valid!");
      }
      SetAt(dat, Natural.Of(curr));
    }
  }

  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new VectorBIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  abstract public Data GetAt(Natural pos);

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  abstract public void SetAt(Data dat, Natural pos);

}
