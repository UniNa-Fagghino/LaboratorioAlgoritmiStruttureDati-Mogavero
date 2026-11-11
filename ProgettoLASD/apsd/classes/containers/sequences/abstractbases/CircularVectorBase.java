package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<T extends CircularVectorBase<T, Data>, Data>
    extends VectorBase<T, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  protected long start = 0L;

  public CircularVectorBase() {
    super();
  }

  public CircularVectorBase(Natural inisize) {
    super(inisize);
  }

  public CircularVectorBase(TraversableContainer<?, Data> con) {
    super(con);
  }

  /* ************************************************************************ */
  /* Specific member functions of CircularVectorBase                          */
  /* ************************************************************************ */

  @Override
  protected void ArrayAlloc(Natural dim) {
    super.ArrayAlloc(dim);
    start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  @SuppressWarnings("unchecked")
  public void Realloc(Natural newsize) {
    long newCap = (newsize == null) ? 0 : newsize.ToLong();
    Data[] newArr = (Data[]) new Object[(int) newCap];
    if (arr != null && arr.length > 0) {
      long copyLen = Math.min(Capacity().ToLong(), newCap);
      for (long i = 0; i < copyLen; i++) {
        long realIdx = (start + i) % arr.length;
        newArr[(int) i] = arr[(int) realIdx];
      }
    }
    arr = newArr;
    start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    long realIdx = (start + idx) % arr.length;
    return arr[(int) realIdx];
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    long realIdx = (start + idx) % arr.length;
    arr[(int) realIdx] = dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  @Override
  public void ShiftLeft(Natural startPos, Natural endPos, Natural number) {
    long s = (startPos == null) ? 0 : startPos.ToLong();
    long e = (endPos == null) ? Size().ToLong() : Math.min(endPos.ToLong(), Size().ToLong());
    long n = (number == null) ? 0 : number.ToLong();
    if (s >= e || n == 0) {
      return;
    }
    for (long i = s; i < e; i++) {
      if (i - n >= 0) {
        SetAt(GetAt(Natural.Of(i)), Natural.Of(i - n));
      }
    }
    long nullStart = Math.max(s, e - n);
    for (long i = nullStart; i < e; i++) {
      SetAt(null, Natural.Of(i));
    }
  }

  @Override
  public void ShiftRight(Natural startPos, Natural endPos, Natural number) {
    long s = (startPos == null) ? 0 : startPos.ToLong();
    long e = (endPos == null) ? Size().ToLong() : Math.min(endPos.ToLong(), Size().ToLong());
    long n = (number == null) ? 0 : number.ToLong();
    if (s >= e || n == 0) {
      return;
    }
    for (long i = e - 1; i >= s; i--) {
      if (i + n < Size().ToLong()) {
        SetAt(GetAt(Natural.Of(i)), Natural.Of(i + n));
      }
    }
    long nullEnd = Math.min(e, s + n);
    for (long i = s; i < nullEnd; i++) {
      SetAt(null, Natural.Of(i));
    }
  }

}
