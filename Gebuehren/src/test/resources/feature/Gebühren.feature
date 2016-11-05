#encoding: iso-8859-1
#language: de
Funktionalit�t: Geb�hrenberechnung

  Die Berechnung einer Prozentualen Geb�hr
  
  Szenario: Prozentual
    Angenommen die prozentuale Geb�hr ist 12 %
    Und der Betrag ist 100 
    Dann ist die Geb�hr 12
    
  Szenario: Prozentual 
    Angenommen die prozentuale Geb�hr ist 0 %
    Und der Betrag ist 100 
    Dann ist die Geb�hr 0
    
  Szenario: Prozentual 
    Angenommen die prozentuale Geb�hr ist 12 %
    Und der Betrag ist -100 
    Dann ist die Geb�hr -12
    
  Szenario: Fix 
    Angenommen die fixe Geb�hr ist 12
    Dann ist die Geb�hr eben 12   
    
  Szenario: �berzahlungen
  	Angenommen der Zins f�r �berzahlungen ist 10 %
  	Und die Untergrenze f�r die Berechnung von Zinsen ist 20
  	Und Zinsen unter 5 werden nicht ber�cksichtigt
  	Und die �berzahlung ist 10
  	Dann ist der Zins 0    
  
   Szenariogrundriss: �berzahlungen
  	Angenommen der Zins f�r �berzahlungen ist <Zinssatz> %
  	Und die Untergrenze f�r die Berechnung von Zinsen ist <Betragsgrenze> 
  	Und Zinsen unter <Zinsgrenze> werden nicht ber�cksichtigt
  	Und die �berzahlung ist <Betrag>
  	Dann ist der Zins <Zins>  
  	
  	Beispiele:
     | Zinssatz | Betragsgrenze | Zinsgrenze | Betrag | Zins |
     | 12       |           20  | 0          | 10     |   0  |
     | 12       |           20  | 0          | 100    |  12  |
     | 12       |            0  | 20         | 100    |   0  |
     |  0       |           20  | 0          | 10     |   0  |
     |  0       |           20  | 0          | 100    |   0  |
     |  0       |            0  | 20         | 100    |   0  |
   
     
 
     