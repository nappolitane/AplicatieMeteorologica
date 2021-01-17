# Repository pentru tema 2 de laborator la IP

## Descriere

Acest repository contine un proiect realizat in IntelliJ pentru o aplicatie simpla cu interfata grafica, creata cu ajutorul librariilor JavaFX, folosind Maven. Este o aplicatie pentru verificarea vremii in diferite orase din diferite tari ale lumii si se foloseste de OpenWeatherMap API pentru a obtine informatiile necesare si librarira Gson pentru parsarea datelor care sunt in format JSON.

## Diagrame

### Diagrama de secventa

![picture](diagrams/SequenceDiagram.png 'Sequence Diagram')

### Diagrama de activitati

![picture](diagrams/ActivityDiagram.png 'Activity Diagram')

## Teste

### JUnit

Am creat clasa WeatherAPIManagerTest pentru testarea clasei WeatherAPIManager.
Testarea consta in apelarea functiei ce apeleaza API-ul cu un nume de oras random si asteptarea valorii de return a acesteia.

### Mocking

Am creat clasa LogsManagerTest pentru testarea clasei LogsManager.
Am creat un mock al clasei LogsManager si am apelat functia care scrie in fisierul de log si asteptam ca raspuns valoarea 0.