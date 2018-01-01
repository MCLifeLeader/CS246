
if [ BegatOMatic.java -nt BegatOMatic.class ]
then
   javac BegatOMatic.java
fi

tail -9 README > output.expected

java -Dstartverse=9 -Ddata="Enos,90,815:Cainan,70,840:Mahalaleel,65,830:Jared" -Dformat="{0}. And {1} lived {2} years, and begat {3}:
{4}. And {1} lived after he begat {3} {5} years, and begat sons and daughters:
{6}. And all the days of {1} were {7} years: and he died.
" BegatOMatic | tee output.actual

echo "====================================================================="
echo "diffing actual output (above) with expected output..."
echo "---------------------------------------------------------------------"
if diff output.actual output.expected
then
   echo "Congratulations, there were no differences!"
fi
echo "---------------------------------------------------------------------"
rm output.*

