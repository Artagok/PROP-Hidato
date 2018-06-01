# PROP-Hidato
## 2017/2018-Q2
* Carlos Aires
* Víctor Vallejo
* Pau Núñez

### **------------------------------------------------ Tuto GIT ------------------------------------------------**

* Obrir consola i cd fins a la carpeta on hi ha el projecte (és la carpeta que té una carpeta anomenada .git, és oculta `ls -la` per veure-la)

**Important**, cada vegada que comenceu a treballar és possible que algú hagi canviat coses i el vostre local estigui desactualitzat:
* `git pull`
___
Quan vulgueu pujar arxius perquè heu fet canvis:
* `git add [fitxers a pujar]`
* `git commit -m "Comentari bla bla bla"`
* `git push origin master`

     `git status` us mostra què teniu diferent al local respecte al que està en línia  
     `git diff` us ensenya les línies de codi que heu eliminat (-) i les que heu afegit (+)  
     `git log` mostra el log dels commits que s'han fet  
     `git remote add origin https://github.com/user/repo.git` per afegir i fer un alias de la paraula *origin* amb el repositori remot (per poder fer `git pull` sense args)  
     `git remote set-url origin git://new.url.here` per modificar l'alias *origin* per si us heu equivocat (no us deixeu el .git al final)
    > En el nostre cas URL = https://github.com/Artagok/PROP-Hidato.git
