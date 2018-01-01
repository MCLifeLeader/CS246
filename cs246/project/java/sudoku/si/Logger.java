// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/Logger.java,v 1.2 2006/06/16 21:36:33 neffr Exp $
package sudoku.si;

import java.io.*;

/**
 * An abstract parent class for Loggers whose static getInstance() method
 * determines what type of Logger to create, creates it, and gives that
 * Logger's instance back to the caller.
 */
public class Logger
{
   /**
    * A constant used as a convenient identifier for the "none"
    * (i.e., non-existent) verbosity level.
    */
   public static final int NONE = 0;

   /**
    * A constant used as a convenient identifier for the "fatal"
    * verbosity level.
    */
   public static final int FATAL = 1;

   /**
    * A constant used as a convenient identifier for the "error"
    * verbosity level.
    */
   public static final int ERROR = 2;

   /**
    * A constant used as a convenient identifier for the "warning"
    * verbosity level.
    */
   public static final int WARN = 3;

   /**
    * A constant used as a convenient identifier for the "information"
    * verbosity level.
    */
   public static final int INFO = 4;

   /**
    * A constant used as a convenient identifier for the "debug"
    * verbosity level.
    */
   public static final int DEBUG = 5;

   /**
    * The maximum verbosity level (DEBUG by default).
    */
   int maxlevel = DEBUG;

   /**
    * The context name.
    */
   String context;

   /**
    * The target of the log operations.
    */
   transient Object target;

   /**
    * Constructs a new Logger instance.
    */
   public Logger()
   {
      // intentionally empty
   }

   /**
    * The default logger used as a default value
    * for getting logger configuration.
    */
   private static Logger cDefaultLogger = new Logger();

   /**
    * Finds a Logger object based on implicit code context.
    * The context is where in the code this method is called,
    * either at the method, the class or the package level.
    *
    * @return the implicitly named Logger object.
    */
   public static Logger find()
   {
      return find(getContext());
   }

   /**
    * Finds a Logger object based on explicit code context.
    *
    * @param name the context name
    *
    * @return the explicitly named Logger object.
    */
   private static Logger find(String name)
   {
      Config config = Config.find(name);
      String loggerName = name + ".logger";
      Logger logger =
         (Logger) config.getInternal(loggerName, cDefaultLogger);

      if (logger == cDefaultLogger)
      {
         String className = config.getInternal(loggerName + ".type",
               ConsoleLogger.class.getName());
         logger = (Logger) config.getInternal(className, Logger.class);
         logger.setLevel(config.getInternal(loggerName + ".level", DEBUG));
         // save for future reference
         config.setInternal(loggerName, logger);
      }

      return logger;
   }

   /**
    * Gets the context of this logger (where it was called from)
    * at the class level.
    */
   protected static String getContext()
   {
      return Context.get(Logger.class.getPackage().getName()).atClass;
   }

   /**
    * Gets the method context (the method where the log method was called).
    *
    * @return the method context name.
    */
   protected String getMethodContext()
   {
      return Context.get(Context.getPackageName(Logger.class)).atMethod;
   }

   /**
    * A convenience method for "fatal" verbosity level
    * logging of messages.
    *
    * @param message the message to be logged.
    */
   public static void fatal(String message)
   {
      Logger.find().fatalInternal(message);
   }

   /**
    * A convenience method for "error" verbosity level
    * logging of messages.
    *
    * @param message the message to be logged.
    */
   public static void error(String message)
   {
      Logger.find().errorInternal(message);
   }

   /**
    * A convenience method for "warning" verbosity level
    * logging of messages.
    *
    * @param message the message to be logged.
    */
   public static void warn(String message)
   {
      Logger.find().warnInternal(message);
   }

   /**
    * A convenience method for "information" verbosity level
    * logging of messages.
    *
    * @param message the message to be logged.
    */
   public static void info(String message)
   {
      Logger.find().infoInternal(message);
   }

   /**
    * A convenience method for "debug" verbosity level
    * logging of messages.
    *
    * @param message the message to be logged.
    */
   public static void debug(String message)
   {
      Logger.find().debugInternal(message);
   }

   /**
    * A convenience method for "fatal" verbosity level
    * logging of messages.
    *
    * @param object the message to be logged
    *               represented as a generic object.
    */
   public static void fatal(Object object)
   {
      Logger.find().fatalInternal(object.toString());
   }

   /**
    * A convenience method for "error" verbosity level
    * logging of messages.
    *
    * @param object the message to be logged
    *               represented as a generic object.
    */
   public static void error(Object object)
   {
      Logger.find().errorInternal(object.toString());
   }

   /**
    * A convenience method for "warning" verbosity level
    * logging of messages.
    *
    * @param object the message to be logged
    *               represented as a generic object.
    */
   public static void warn(Object object)
   {
      Logger.find().warnInternal(object.toString());
   }

   /**
    * A convenience method for "information" verbosity level
    * logging of messages.
    *
    * @param object the message to be logged
    *               represented as a generic object.
    */
   public static void info(Object object)
   {
      Logger.find().infoInternal(object.toString());
   }

   /**
    * A convenience method for "debug" verbosity level
    * logging of messages.
    *
    * @param object the message to be logged
    *               represented as a generic object.
    */
   public static void debug(Object object)
   {
      Logger.find().debugInternal(object.toString());
   }

   /**
    * Internal method that checks to see if the log level is
    * sufficiently high to log the message
    * (with a call to logMessage(String)).
    *
    * @param message the message to be logged.
    * @param level the verbosity level of the message.
    */
   void log(String message, int level)
   {
      if (level <= maxlevel)
      {
         logMessage(message);
      }
   }

   /**
    * Empty method that is meant to be overridden
    * by a subclass to do specific types of logging.
    *
    * @param message the message to be logged.
    */
   void logMessage(String message)
   {
      // Override in subclass to do logger-specific logging
   }

   /**
    * Logs a FATAL level message.
    *
    * @param message the message to be logged.
    */
   void fatalInternal(String message)
   {
      log(message, FATAL);
   }

   /**
    * Logs an ERROR level message.
    *
    * @param message the message to be logged.
    */
   void errorInternal(String message)
   {
      log(message, ERROR);
   }

   /**
    * Logs a WARN level message.
    *
    * @param message the message to be logged.
    */
   void warnInternal(String message)
   {
      log(message, WARN);
   }

   /**
    * Logs an INFO level message.
    *
    * @param message the message to be logged.
    */
   void infoInternal(String message)
   {
      log(message, INFO);
   }

   /**
    * Logs a DEBUG level message.
    *
    * @param message the message to be logged.
    */
   void debugInternal(String message)
   {
      log(message, DEBUG);
   }

   /**
    * Initialization method used by the child classes.
    */
   public void init()
   {
   }

   /**
    * Sets target.
    *
    * @param obj the reference to the target object.
    */
   public static void setTarget(Object obj)
   {
      Logger.find().setTargetInternal(obj);
   }

   /**
    * Redirects the logger target.
    *
    * @param obj the reference to the target object.
    */
   public void redirectTo(Object obj)
   {
      setTargetInternal(obj);
   }

   /**
    * Sets the target using subclass-specific means.
    *
    * @param obj the reference to the target object.
    */
   protected void setTargetInternal(Object obj)
   {
      // override in subclass
   }

   /**
    * Sets maxlevel, if specified; otherwise leaves it at default (DEBUG).
    *
    * @param level the new maxlevel
    */
   private void setLevel(int level)
   {
      switch (level)
      {
         case FATAL:
         case ERROR:
         case WARN:
         case INFO:
         case DEBUG:
         {
            maxlevel = level;

            break;
         }

         default:
            maxlevel = DEBUG;
      }
   }
}
