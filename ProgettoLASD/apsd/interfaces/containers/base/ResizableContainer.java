package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer<T extends ResizableContainer<T>> extends ReallocableContainer<T> {

  /* ************************************************************************ */
  /* Specific member functions of ResizableContainer                          */
  /* ************************************************************************ */

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  default void Expand() {
    Expand(Natural.ONE);
  }

  void Expand(Natural num);

  default void Reduce() {
    Reduce(Natural.ONE);
  }

  void Reduce(Natural num);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  default void Grow(Natural dim) {
    long inc = (dim == null) ? 0 : dim.ToLong();
    long newCap = (long) Math.ceil((Size().ToLong() + inc) * GROW_FACTOR);
    Realloc(Natural.Of(newCap));
  }

  @Override
  default void Shrink() {
    long newCap = (long) Math.ceil(Capacity().ToLong() / SHRINK_FACTOR);
    Realloc(Natural.Of(newCap));
  }

}
