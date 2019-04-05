Ce répertoire contient :
  - le fichier jj permettant de spécifier l'analyseur lexical et l'analyseur syntaxique
    du langage domotique (repertoire scenarios\parser)
  - un exemple de programme domotique (fichier *.ldsd)
  
  
Compilation du parseur :
  1) Se placer dans le répertoire scenarios\parser.
  2) Lancer le fichier batch genererParseur.
     Ceci génère l'ensemble du code Java (via javacc) du parseur.
  3) Lancer le fichier batch genererApplication.
     Ceci compile l'application avec le parseur (via javac).
  
  
Exécution du parseur et interprétation du fichier domotique :
  Lancer le fichier batch run
  