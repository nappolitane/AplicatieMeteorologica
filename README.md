# Repository pentru tema 2 de laborator la IP

## Descriere

Aceasta aplicatie este o aplicatie simpla de verificarea vremii in diferite orase din diferite tari ale lumii si foloseste OpenWeatherMap API si un librarira Gson pentru parsarea datelor JSON.

## Diagrame

### Diagrama de secventa

![alt text](https://github.com/nappolitane/AplicatieMeteorologica/diagrams/SequenceDiagram.png?raw=true)

### Diagrama de activitati

![alt text](https://github.com/nappolitane/AplicatieMeteorologica/diagrams/ActivityDiagram.png?raw=true)

## Teste

### JUnit

Am creat clasa WeatherAPIManagerTest pentru testarea clasei WeatherAPIManager.
Testarea consta in apelarea functiei ce apeleaza API-ul cu un nume de oras random si asteptarea valorii de return a acesteia.

### Mocking

Am creat clasa LogsManagerTest pentru testarea clasei LogsManager.
Am creat un mock al clasei LogsManager si am apelat functia care scrie in fisierul de log si asteptam ca raspuns valoarea 0.
