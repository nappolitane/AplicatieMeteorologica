# Repository pentru tema 2 de laborator la IP

## Descriere

Acest repository contine un proiect realizat in IntelliJ pentru o aplicatie simpla cu interfata grafica, creata cu ajutorul librariilor JavaFX, folosind Maven. Este o aplicatie pentru verificarea vremii in diferite orase din diferite tari ale lumii si se foloseste de OpenWeatherMap API pentru a obtine informatiile necesare si librarira Gson pentru parsarea datelor care sunt in format JSON.

## Build and Run

### JavaFX

Pentru a putea rula acest proiect mai intai trebuie sa aveti libraria JavaFX. Descarcati biblioteca javaFX de la adresa https://gluonhq.com/download/javafx-15-0-1-sdk-windows/ iar apoi, la setarile de rulare ale aplicatiei(din dreapta sus) adaugati in sectiunea VM arguments urmatoarea linie:
--module-path <calea_unde_ati_dezarhivat_biblioteca_javafx>\lib --add-modules javafx.controls,javafx.fxml

### GSON

Pentru a instala libraria gson in proiectul intellij urmati pasii de mai jos:
1. Click dreapta pe folderul cu numele proiectului din tab-ul cu fisiere (stanga sus)
2. Selectati Open Module Settings
3. Click pe Global Libraries
4. Click pe plusul de sus din mijloc
5. From maven...
6. Introduceti "gson" si apasati pe lupa de cautare
7. Alegeti ultima versiune de com.google.code.gson:gson(ultima versiune)
8. Dati ok pentru a adauga libraria in proiect

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
