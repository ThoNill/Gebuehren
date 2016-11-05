#encoding: iso-8859-1
#language: de
Funktionalität: Gebührenberechnung

  Die Berechnung einer Prozentualen Gebühr
  
  Szenario: Prozentual
    Angenommen die prozentuale Gebühr ist 12 %
    Und der Betrag ist 100 
    Dann ist die Gebühr 12
    
  Szenario: Prozentual 
    Angenommen die prozentuale Gebühr ist 0 %
    Und der Betrag ist 100 
    Dann ist die Gebühr 0
    
  Szenario: Prozentual 
    Angenommen die prozentuale Gebühr ist 12 %
    Und der Betrag ist -100 
    Dann ist die Gebühr -12
    
  Szenario: Fix 
    Angenommen die fixe Gebühr ist 12
    Dann ist die Gebühr eben 12   
    
  Szenario: Überzahlungen
  	Angenommen der Zins für Überzahlungen ist 10 %
  	Und die Untergrenze für die Berechnung von Zinsen ist 20
  	Und Zinsen unter 5 werden nicht berücksichtigt
  	Und die Überzahlung ist 10
  	Dann ist der Zins 0    
  
   Szenariogrundriss: Überzahlungen
  	Angenommen der Zins für Überzahlungen ist <Zinssatz> %
  	Und die Untergrenze für die Berechnung von Zinsen ist <Betragsgrenze> 
  	Und Zinsen unter <Zinsgrenze> werden nicht berücksichtigt
  	Und die Überzahlung ist <Betrag>
  	Dann ist der Zins <Zins>  
  	
  	Beispiele:
     | Zinssatz | Betragsgrenze | Zinsgrenze | Betrag | Zins |
     | 12       |           20  | 0          | 10     |   0  |
     | 12       |           20  | 0          | 100    |  12  |
     | 12       |            0  | 20         | 100    |   0  |
     |  0       |           20  | 0          | 10     |   0  |
     |  0       |           20  | 0          | 100    |   0  |
     |  0       |            0  | 20         | 100    |   0  |
   
     
 
     