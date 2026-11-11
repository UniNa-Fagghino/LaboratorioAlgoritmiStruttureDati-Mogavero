package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Reallocable;

/** Interface: ClearableContainer che è anche Reallocable. */
public interface ReallocableContainer<T extends ReallocableContainer<T>> extends ClearableContainer<T>, Reallocable {

  /* ************************************************************************ */
  /* Specific member functions of ReallocableContainer                        */
  /* ************************************************************************ */

  double GROW_FACTOR = 2.0; // Must be strictly greater than 1.
  double SHRINK_FACTOR = 2.0; // Must be strictly greater than 1.

  Natural Capacity();

  default void Grow() {
    Grow(Natural.ONE);
  }

  default void Grow(Natural dim) {
    long inc = (dim == null) ? 0 : dim.ToLong();
    long newCap = (long) Math.ceil((Size().ToLong() + inc) * GROW_FACTOR);
    Realloc(Natural.Of(newCap));
  }

  default void Shrink() {
    long newCap = (long) Math.ceil(Capacity().ToLong() / SHRINK_FACTOR);
    Realloc(Natural.Of(newCap));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  default Natural Size() {
    return Capacity();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  default void Clear() {
    Realloc(Natural.ZERO);
  }

}
