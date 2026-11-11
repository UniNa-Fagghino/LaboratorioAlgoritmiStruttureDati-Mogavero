package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<T extends LinearVectorBase<T, Data>, Data>
    extends VectorBase<T, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  public LinearVectorBase() {
    super();
  }

  public LinearVectorBase(Natural inisize) {
    super(inisize);
  }

  public LinearVectorBase(TraversableContainer<?, Data> con) {
    super(con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  @SuppressWarnings("unchecked")
  public void Realloc(Natural newsize) {
    long newCap = (newsize == null) ? 0 : newsize.ToLong();
    Data[] newArr = (Data[]) new Object[(int) newCap];
    if (arr != null) {
      int copyLen = Math.min(arr.length, (int) newCap);
      System.arraycopy(arr, 0, newArr, 0, copyLen);
    }
    arr = newArr;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    return arr[(int) idx];
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    arr[(int) idx] = dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  @Override
  public void ShiftLeft(Natural start, Natural end, Natural number) {
    long s = (start == null) ? 0 : start.ToLong();
    long e = (end == null) ? Size().ToLong() : Math.min(end.ToLong(), Size().ToLong());
    long n = (number == null) ? 0 : number.ToLong();
    if (s >= e || n == 0) {
      return;
    }
    for (long i = s; i < e; i++) {
      if (i - n >= 0) {
        arr[(int) (i - n)] = arr[(int) i];
      }
    }
    long nullStart = Math.max(s, e - n);
    for (long i = nullStart; i < e; i++) {
      arr[(int) i] = null;
    }
  }

  @Override
  public void ShiftRight(Natural start, Natural end, Natural number) {
    long s = (start == null) ? 0 : start.ToLong();
    long e = (end == null) ? Size().ToLong() : Math.min(end.ToLong(), Size().ToLong());
    long n = (number == null) ? 0 : number.ToLong();
    if (s >= e || n == 0) {
      return;
    }
    for (long i = e - 1; i >= s; i--) {
      if (i + n < Size().ToLong()) {
        arr[(int) (i + n)] = arr[(int) i];
      }
    }
    long nullEnd = Math.min(e, s + n);
    for (long i = s; i < nullEnd; i++) {
      arr[(int) i] = null;
    }
  }

}
