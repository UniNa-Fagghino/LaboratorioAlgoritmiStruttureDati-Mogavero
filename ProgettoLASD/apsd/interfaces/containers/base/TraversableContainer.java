package apsd.interfaces.containers.base;

import java.util.Objects;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

/** Interface: MembershipContainer con supporto all'attraversamento. */
public interface TraversableContainer<T extends TraversableContainer<T, Data>, Data>
    extends MembershipContainer<T, Data> {

  /* ************************************************************************ */
  /* Specific member functions of TraversableContainer                        */
  /* ************************************************************************ */

  boolean TraverseForward(Predicate<Data> pred);

  boolean TraverseBackward(Predicate<Data> pred);

  default <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {
    if (fun == null) {
      return ini;
    }
    Box<Acc> accBox = new Box<>(ini);
    TraverseForward(dat -> {
      accBox.Set(fun.Apply(dat, accBox.Get()));
      return false;
    });
    return accBox.Get();
  }

  default <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
    if (fun == null) {
      return ini;
    }
    Box<Acc> accBox = new Box<>(ini);
    TraverseBackward(dat -> {
      accBox.Set(fun.Apply(dat, accBox.Get()));
      return false;
    });
    return accBox.Get();
  }

  default Natural Multiplicity(Data dat) {
    MutableNatural count = new MutableNatural(0);
    TraverseForward(d -> {
      if (Objects.equals(d, dat)) {
        count.Increment();
      }
      return false;
    });
    return Natural.Of(count.ToLong());
  }

  default boolean IsEqual(TraversableContainer<?, Data> con) {
    if (con == this) {
      return true;
    }
    if (con == null) {
      return false;
    }
    if (Size().ToLong() != con.Size().ToLong()) {
      return false;
    }
    boolean mismatch = TraverseForward(dat -> !Multiplicity(dat).equals(con.Multiplicity(dat)));
    return !mismatch;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Exists(Data dat) {
    return TraverseForward(d -> Objects.equals(d, dat));
  }

}
