# Happy Places App

Du entwickelst eine Happy Places App für Android mit Kotlin, die es Nutzenden ermöglicht, ihre
Lieblingsorte zu speichern, auf einer Karte anzuzeigen und mit persönlichen Notizen zu versehen.

Ihr erarbeitet die App im Team und jede*r gibt eine Textdatei ab, die auf das eigene Repository
verweist.

Erstelle eine Happy Places App, die folgende Funktionen umfasst:

- Orte speichern (mit Name, Beschreibung und Foto)
- Interaktive Karte zur Anzeige der Orte
- Persönliche Notizen zu den Orten hinzufügen

## Vorbereitung

- Erstelle ein neues Android-Projekt in Android Studio und wähle Kotlin als Programmiersprache.
- Nutze das OpenStreetMap SDK, um OSM in Deine Anwendung zu integrieren.

## Funktionale Anforderungen

- Orte speichern: Nutzende können Orte mit einem Titel, einer Beschreibung und einem Bild speichern
  und diese Orte werden in einer Liste angezeigt.
- Karte & Standort-Funktion: Die gespeicherten Orte werden auf einer interaktiven Karte angezeigt
  und es gibt die Möglichkeit, den aktuellen Standort zu nutzen, um neue Orte schneller
  hinzuzufügen.
- Persönliche Notizen: Jeder Ort kann mit individuellen Notizen versehen werden, die bearbeitet oder
  gelöscht werden können.

## Beispiel-Workflow:

1. Nutzende klicken auf „Neuen Ort hinzufügen“.
2. Namen und eine kurze Beschreibung eingeben.
3. Standort auf der Karte auswählen oder den aktuellen Standort nutzen.
4. Optional ein Foto hinzufügen.
5. Ort speichern und später ggf. Notizen ergänzen.

## Extra-Herausforderungen (optional)

Falls du die Grundfunktionen schnell umgesetzt hast, kannst du noch folgende Features einbauen:

1. Kategorien für Orte (z. B. Natur, Essen, Entspannung)
2. Navigation zu gespeicherten Orten mit Google Maps
3. Suchfunktion für gespeicherte Orte

## Genutzte externe Bibliotheken:

- [osmdroid](https://github.com/osmdroid/osmdroid) (archiviert & veraltet)
- ~~[osm-android-compose](https://github.com/utsmannn/osm-android-compose)~~

# TODO

https://sanaebadi97.medium.com/learn-how-to-work-with-osm-map-in-android-app-ac42f933cbd3
https://github.com/osmdroid/osmdroid/wiki/How-to-use-the-osmdroid-library-(Kotlin)#how-do-i-place-icons-on-the-map-with-a-click-listener