package bebucht;

import java.util.HashSet;

public class StatusWechselGruppe extends HashSet<StatusWechsel>{

   public StatusWechselGruppe() {
   }
   
   public StatusWechselGruppe addStatusWechsel(Enum art, String buchungsText,Enum status) {
       StatusWechsel w= new StatusWechsel( art, buchungsText,status);
       add(w);
       return this;
   }
   
   StatusWechsel getWechsel(Enum<?> status) {
       if (status == null) {
           throw new IllegalArgumentException("Status darf nicht Null sein");
       }
       for(StatusWechsel w : this) {
           if (status .equals(w.getStatus())) {
               return w;
           }
       }
       return null;
   }

}
