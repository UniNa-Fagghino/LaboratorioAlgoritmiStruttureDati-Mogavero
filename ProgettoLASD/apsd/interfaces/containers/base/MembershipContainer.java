package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla verifica di appartenenza. */
public interface MembershipContainer<T extends MembershipContainer<T, Data>, Data> extends Container<T> {

  /* ************************************************************************ */
  /* Specific member functions of MembershipContainer                         */
  /* ************************************************************************ */

  boolean Exists(Data dat);

}
