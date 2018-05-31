# PROP-Hidato
## 2017/2018-Q2
* Carlos Aires
* Víctor Vallejo
* Pau Núñez

### **------------------------- Tuto GIT -------------------------**

* Obrir consola i cd fins a la carpeta on hi ha el projecte (és la carpeta que té una carpeta anomenada .git, és oculta `ls -la` per veure-la)

**Important**, cada vegada que comenceu a treballar és possible que algú hagi canviat coses i el vostre local estigui desactualitzat:
* `git pull`
___
Quan vulgueu pujar arxius perquè heu fet canvis:
* `git add [fitxers a pujar]`
   `git status` us mostra què teniu diferent al local respecte al que està en línia
   `git diff` us ensenya les línies de codi que heu eliminat (-) i les que heu afegit (+)
* `git commit -m "Comentari bla bla bla"`
   `git log` mostra el log dels commits que s'han fet
* `git push origin master`
