import ch.qos.logback.classic.AsyncAppender
import static ch.qos.logback.classic.Level.INFO

scan("30 seconds")

def cwd = System.getenv("PWD")
def logDir = "${cwd}/logs"
//def ant = new AntBuilder()
//ant.mkdir(dir: logDir)

def CONSOLE = "console"
def FILE = "file"
def FILE_INTERNAL = "file-internal"
def PATTERN = "%d %-5level [%-10.10thread] %-20.20logger: %msg%n"

appender(CONSOLE, ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = PATTERN
  }
}

appender(FILE_INTERNAL, RollingFileAppender) {
  file = "${logDir}/hotjava.log"
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "${logDir}/hotjava.log%d{yyyy-MM-dd}.log"
    maxHistory = 10
    totalSizeCap = "5MB"
  }
  encoder(PatternLayoutEncoder) {
    pattern = PATTERN
  }
}
appender(FILE, AsyncAppender) {
  appenderRef(FILE_INTERNAL)
}

logger("org.densebrain", INFO, [CONSOLE, FILE], false)
root(WARN, [CONSOLE, FILE])