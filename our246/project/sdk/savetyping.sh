############################################################
#
# Save typing (or shell command history navigating)
# by defining various environment variables, aliases
# (for connecting to project directories) and shell
# functions for building modules and other artifacts.
#
############################################################

# Set a default project path
if [ "$PROJPATH" = "" ]; then
   PROJPATH=$HOME/our246/project
fi

# Set a default Sudoku user name
if [ "$USERNAME" = "" ]; then
   USERNAME=$USER
fi

# Set (and export) the necessary java compiler and virtual machine class path
export CLASSPATH=.:$PROJPATH/class:$PROJPATH/lib/junit.jar:$PROJPATH/lib/xstream.jar:/usr/java/jdk1.5.0_04/lib/tools.jar

alias pj='cd $PROJPATH'
alias ai='cd $PROJPATH/java/sudoku/ai'
alias al='cd $PROJPATH/java/sudoku/al'
alias cs='cd $PROJPATH/java/sudoku/cs'
alias ui='cd $PROJPATH/java/sudoku/ui'
alias si='cd $PROJPATH/java/sudoku/si'

# build the Builder
bb()
{
   pj
   javac -sourcepath java -d class java/Builder.java
}

# build module
b()
{
   java Builder
}

# build all modules
ba()
{
   pj
   java Builder warn
}

# run jalopy
jy()
{
    /home/cs246/jalopy $*
}

# build and signs all modules
bas()
{
   pj
   java Builder warn sign
}

# signs all modules
sign()
{
   pj
   jarsigner -keystore config/sudokuKeystore -storepass sudoku sudokuai.jar myself
   jarsigner -keystore config/sudokuKeystore -storepass sudoku sudokual.jar myself
   jarsigner -keystore config/sudokuKeystore -storepass sudoku sudokucs.jar myself
   jarsigner -keystore config/sudokuKeystore -storepass sudoku sudoku.jar myself
   jarsigner -keystore config/sudokuKeystore -storepass sudoku sudokusi.jar myself
   jarsigner -keystore config/sudokuKeystore -storepass sudoku sudokuui.jar myself
   jarsigner -keystore config/sudokuKeystore -storepass sudoku sudokusi.jar myself
   jarsigner -keystore config/sudokuKeystore -storepass sudoku lib/xstream.jar myself
}

# junit
junit()
{
   pj
   java $2 junit.swingui.TestRunner $1
}

# junit ng (no gui -- text ui only)
junitng()
{
   pj
   java $2 junit.textui.TestRunner $1
}

# make jar
mj()
{
   pj
   java Builder sudoku.jar
}

# make javadocs
mjd()
{
   pj
   java Builder jdoc
}

# make jpg files
jpg()
{
   for f in docs/*.uxf; do \
      java -jar /home/cs246/umlet6/umlet.jar \
         -action=convert -format=jpg -filename=$f; \
   done
}

# make jpg files without a graphics environment
jpgng()
{
   grep -q xml docs/*.uxf >/dev/null 2>&1 && \
   test -f /home/cs246/umlet6/umlet.jar && \
   test -d /home/cs246/umlet6/lib && \
   java -cp /home/cs246/umlet6/umlet.jar \
        -Djava.ext.dirs=/home/cs246/umlet6/lib GenJpg docs/*.uxf
}

# cvs update
up()
{
   cvs -q up -d -A
}

# cvs update for the whole project
jup()
{
   pj
   up
}

# start rmiregistry
rmi()
{
    rmiregistry
}

# start server
sdk()
{
   pj
   java sudoku.cs.ServerModel
}

# start client
uou()
{
   pj
   node=`/bin/hostname -i | cut -d. -f4`
   java -Duser.name="${1:-$USERNAME}" \
	-DRMIRegistryHostAddressSuffix=${2:-$node} $3 \
        -jar sudoku.jar
}

