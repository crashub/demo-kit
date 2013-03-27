# How to build CRaSH demos

## Obtaining CRaSH source code

CRaSH demos can be obtained by cloning the Git repository `git@github.com:crashub/demo-kit.git`

<pre><code>git clone git@github.com:crashub/demo-kit.git</code></pre>

## Building CRaSH demos

CRaSH demos are built with Maven.

<pre><code>mvn package</code></pre>

it will produce:

- `packaging/target/demo-kit-demos` : the exploded directory
- `packaging/target/demo-kit-demos.zip` : the demos as zip

The default output directory is `packaging/target`, but you can override it using `demo.output.directory` property as following:

<pre><code>mvn package -Ddemo.output.directory="/home/foo/demos"</code></pre>

## Install sublime text snippets

You can install some sublime text snippets to help you during the demo:

<pre><code>cd sublime-text
mvn install -Ddemo.sublimetext.directory="/path/to/sublime text"</code></pre>
Default OSX path is `/Users/foo/Library/Application Support/Sublime Text 2/Packages/User`

Provided snippets are :
- `jmx find`
- `jmx get`
- `jmx sort`
- `jmx dashboard`
- `jmx full`
- `twitter service`
- `twitter parse`
- `twitter print`
- `twitter command`
- `twitter argument`
- `twitter try`

## Run CRaSH demos

### Demo 1 : Standalone

- Run crash `./demo1-standalone/bin/crash.sh`

### Demo 2 : JDBC and JPA commands

- Run jboss `./demo2-jpa/jboss-as-7.1.1.Final/bin/standalone.sh`
- Attach crash using visualvm plugin
- Data source is available for demo using `jdbc open java:global/jdbc/crashJpaDemoDS`
- Entity manager factory is available for demo using `jpa open crashJpaDemoEMF`

### Demo 3 : JMX and dashboards from visualvm plugin

- Run tomcat `./demo3-jmx/apache-tomcat-7.0.35/bin/catalina.sh run`
- Attach crash `./demo1-standalone/bin/crash.sh <Bootstrap pid>`
- Base your demo on the monitor dashboard : `./demo1-standalone/cmd/monitor.groovy`
- Run jmeter: `jmeter -n -t ./demo3-jmx/crashDemo.jmx`

### Demo 4 : Spring integration and custom command

- Run tomcat `./demo4-spring/apache-tomcat-7.0.35/bin/catalina.sh run`
- Connect to crash using telnet: `telnet localhost 5000`
- Base your demo on the twitter command : `./demo1-standalone/cmd/monitor.groovy`