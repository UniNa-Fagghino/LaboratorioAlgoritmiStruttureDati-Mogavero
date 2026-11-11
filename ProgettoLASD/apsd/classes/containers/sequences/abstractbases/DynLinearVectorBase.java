package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<T extends DynLinearVectorBase<T, Data>, Data>
    extends LinearVectorBase<T, Data> implements DynVector<T, Data> {

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  protected long size = 0L;

  public DynLinearVectorBase() {
    super();
    size = 0L;
  }

  public DynLinearVectorBase(Natural inicapacity) {
    super(inicapacity);
    size = 0L;
  }

  public DynLinearVectorBase(TraversableContainer<?, Data> con) {
    super(con);
    size = (con == null) ? 0L : con.Size().ToLong();
  }

  /* ************************************************************************ */
  /* Specific member functions of DynLinearVectorBase                         */
  /* ************************************************************************ */

  @Override
  protected void ArrayAlloc(Natural dim) {
    super.ArrayAlloc(dim);
    size = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() {
    return Natural.Of(size);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    size = 0L;
    super.Clear();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  public void Realloc(Natural newsize) {
    super.Realloc(newsize);
    if (size > Capacity().ToLong()) {
      size = Capacity().ToLong();
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  @Override
  public void Expand(Natural num) {
    long n = (num == null) ? 0 : num.ToLong();
    long newSize = size + n;
    if (newSize > Capacity().ToLong()) {
      Grow(Natural.Of(n));
    }
    size = newSize;
  }

  @Override
  public void Reduce(Natural num) {
    long n = (num == null) ? 0 : num.ToLong();
    if (n > size) {
      throw new IndexOutOfBoundsException("Cannot reduce by " + n + ", current size is " + size);
    }
    size -= n;
    if (THRESHOLD_FACTOR * SHRINK_FACTOR * size <= Capacity().ToLong()) {
      Shrink();
    }
  }

}
