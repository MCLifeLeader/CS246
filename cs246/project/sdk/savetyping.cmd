@echo   off
rem     "$*" is replaced by all the parameters on the command line.

set projpath=H:\cs246\project
set classpath=%PROJPATH%\class;%PROJPATH%\lib\junit.jar;%PROJPATH%\lib\xstream.jar;%classpath%

doskey  ls=dir $*
doskey  rm=del $*
doskey  cp=copy $*
doskey  mv=move $*
doskey  diff=fc $*
doskey  grep=find $*
doskey  m=more $*
doskey  ?=doskey /m
doskey  jc=javac $*
doskey  j=java $*

rem     shortcuts to the project directories
doskey  pj=cd /d %projpath%
doskey  ai=cd /d %projpath%\java\sudoku\ai
doskey  al=cd /d %projpath%\java\sudoku\al
doskey  cs=cd /d %projpath%\java\sudoku\cs
doskey  ui=cd /d %projpath%\java\sudoku\ui
doskey  si=cd /d %projpath%\java\sudoku\si

rem     build the builder
doskey  bb=cd /d %projpath% $T javac -d class java/Builder.java

rem     build module
doskey  b=java Builder

rem     build all modules
doskey  ba=cd /d %projpath% $T java Builder warn

rem     junit
doskey  junit=cd /d %projpath% $T java $2 junit.swingui.TestRunner $1

rem     make jar
doskey  mj=cd /d %projpath% $T java Builder sudoku.jar

rem     make javadocs
doskey  mjd=cd /d %projpath% $T java Builder jdoc

rem     start rmiregistry
doskey  rmi=start rmiregistry

rem     start server
doskey  sdk=cd /d %projpath% $T java sudoku.cs.ServerModel

rem     start client
doskey  uou=cd /d %projpath% $T java -jar sudoku.jar
