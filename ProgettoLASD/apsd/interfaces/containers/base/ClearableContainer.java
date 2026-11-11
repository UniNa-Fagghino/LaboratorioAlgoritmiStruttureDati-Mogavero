package apsd.interfaces.containers.base;

import apsd.interfaces.traits.Clearable;

/** Interface: Container che è anche Clearable. */
public interface ClearableContainer<T extends ClearableContainer<T>> extends Container<T>, Clearable {
}
