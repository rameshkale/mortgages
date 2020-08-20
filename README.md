

How to setup work space - </br>
</br>
1. Gradle idea plugin can be used to setup workspace, it will help to pull all required dependencies on local machine - gradle idea</br>
2. Gradle version gradle-6.5-bin.zip need to place in root directory to execute Dockerfile. </br>
3. Docker will take of installing required Java JDK version, Similary on local environment version 1.8 is required. </br>
4. gradle build will help to build code base plus generate JAXB bindings. </br>
5. gradle bootRun or java -jar <jarfilename> will help to run embedded container in tomcat. </br>
6. All required plugins to make jar bootable is places in Manifest.mf and respective start class to boot the application </br>
</br>
</br>
Sample out put when server starts, bootable application will start and open port generally before 10 seconds. </br>
</br>
</br>
Scenarios covered in code - </br>
</br>
Scenario 1, Retrieve mortgage applications in natural order of insertion. </br>
Scenario 2, Retrieve mortgage applications by Offer Date Ascending </br>
Scenario 3, Retrieve mortgage applications by Offer Date Descending  </br>
Scenario 4, Retrieve mortgage applications by Created Date Ascending </br>
Scenario 5, Retrieve mortgage applications by Created Date Descending  </br>
Scenario 6, SOAP Interface details for inserting offer using WSDL endpoint, cat command is showing soap request details, curl is executing WSDL request. 
Scenario 7, Inserting offer into application store using REST interface. </br>
Scenario 8, Offer more than six months old,  ERROR 101. </br>
Scenario 9, Error when user tries to insert offer with lower version, ERROR 102. </br>
Scenario 10, Update existing record when same version of application submitted to store. </br>
</br>


</br>  .   ____          _            __ _ _
</br> /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
</br>( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
</br> \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
</br>  '  |____| .__|_| |_|_| |_\__, | / / / /
</br> =========|_|==============|___/=/_/_/_/
</br> :: Spring Boot ::        (v2.2.2.RELEASE)
</br>
</br>2020-08-19 10:23:12.955  WARN 8969 --- [           main] o.s.boot.StartupInfoLogger               : InetAddress.getLocalHost().getHostName() took 5003 milliseconds to respond. Please verify your network configuration (macOS machines may need to add entries to /etc/hosts).
</br>2020-08-19 10:23:17.964  INFO 8969 --- [           main] com.mortgages.BootApplication            : Starting BootApplication on Rameshs-MacBook-Pro.local with PID 8969 (/Users/rameshkale/bc/copied-jks/remote/mortgages/ds-layer/ds-api/build/classes/java/main started by rameshkale in /Users/rameshkale/bc/copied-jks/remote/mortgages/ds-layer/ds-api)
</br>2020-08-19 10:23:17.965  INFO 8969 --- [           main] com.mortgages.BootApplication            : No active profile set, falling back to default profiles: default
</br>2020-08-19 10:23:18.979  INFO 8969 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9443 (https)
</br>2020-08-19 10:23:18.989  INFO 8969 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
</br>2020-08-19 10:23:18.990  INFO 8969 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.29]
</br>2020-08-19 10:23:19.108  INFO 8969 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
</br>2020-08-19 10:23:19.108  INFO 8969 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1099 ms
</br>2020-08-19 10:23:19.345  INFO 8969 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
</br>2020-08-19 10:23:24.932  INFO 8969 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9443 (https) with context path ''
</br>2020-08-19 10:23:24.936  INFO 8969 --- [           main] com.mortgages.BootApplication            : Started BootApplication in 22.353 seconds (JVM running for 22.767)
</br>2020-08-19 10:24:18.415  INFO 8969 --- [nio-9443-exec-6] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
</br>2020-08-19 10:24:18.415  INFO 8969 --- [nio-9443-exec-6] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
</br>2020-08-19 10:24:18.421  INFO 8969 --- [nio-9443-exec-6] o.s.web.servlet.DispatcherServlet        : Completed initialization in 6 ms










