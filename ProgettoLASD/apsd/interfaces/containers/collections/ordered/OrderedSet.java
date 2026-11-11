package apsd.interfaces.containers.collections.ordered;

import apsd.interfaces.containers.collections.Set;

public interface OrderedSet<T extends OrderedSet<T, Data>, Data extends Comparable<? super Data>>
    extends Set<T, Data>, OrderedCollection<T, Data> {
}
