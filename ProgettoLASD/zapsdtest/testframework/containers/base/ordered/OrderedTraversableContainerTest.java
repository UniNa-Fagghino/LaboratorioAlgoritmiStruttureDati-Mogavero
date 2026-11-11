package zapsdtest.testframework.containers.base.ordered;

import apsd.interfaces.containers.base.ordered.OrderedTraversableContainer;

import zapsdtest.testframework.containers.base.TraversableContainerTest;

public interface OrderedTraversableContainerTest<Data extends Comparable<? super Data>, Con extends OrderedTraversableContainer<Con, Data>>
        extends OrderedContainerTest<Data, Con>, TraversableContainerTest<Data, Con> {
}
