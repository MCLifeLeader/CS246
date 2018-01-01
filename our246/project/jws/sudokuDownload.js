var detect;
var windowsIE;
function checkPlatform(string)
{
    place = detect.indexOf(string) + 1;
    thestring = string;
    return place;
}

static void main(String arg[])
{
   detect = navigator.userAgent.toLowerCase();
   windowsIE = (checkPlatform("msie") && checkPlatform("win"));
   if (windowsIE) 
   {
       document.write("<OBJECT
        codeBase=http://java.sun.com/update/1.5.0/jinstall-1_5_0_05-windows-i586.cab
        classid=clsid:5852F5ED-8BF4-11D4-A245-0080C6F74284 height=0 width=0>");
       document.write("<PARAM name=app
        VALUE=./sudoku.jnlp>");
	document.write("<PARAM NAME=back VALUE=false>");
	document.write("</OBJECT>");
    }
    else if (!webstartVersionCheck("1.5")) 
    {
       document.write("You need to download the a newer java run time environment.");
       document.write("<br> redirecting now");
       window.open("http://jdl.sun.com/webapps/getjava/BrowserRedirect?locale=en&host=java.com",
        "needdownload");
    }
    launchTID = setInterval('launchJNLP("./sudoku.jnlp")', 100);
}

function webstartVersionCheck(versionString) 
{
    // Mozilla may not recognize new plugins without this refresh
    navigator.plugins.refresh(true);
    // First, determine if Web Start is available
    if (navigator.mimeTypes['application/x-java-jnlp-file']) 
    {
        // Next, check for appropriate version family
        for (var i = 0; i < navigator.mimeTypes.length; ++i) 
        {
            pluginType = navigator.mimeTypes[i].type;
            if (pluginType == "application/x-java-applet;version=" + versionString) 
            {
                return true;
            }
        }
    }
}

function launchJNLP(app) 
{
    if (webstartVersionCheck("1.5")) 
    {
        clearInterval(launchTID);
        window.location = app;
    }
}
