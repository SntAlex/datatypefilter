# Data Type File Filter

- Java version: 1.8
- Build automation tool: Maven 3.9.2

- Dependencies:
    - [Apache Commons CLI v. 1.6.0](https://mvnrepository.com/artifact/commons-cli/commons-cli/1.6.0) - to parse options
      from command line easily
    - [Jackson v. 2.16.1](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.16.1) - to
      deserialize data from configuration file **appsettings.json**

1) **appsettings.json** file is required, there I added all options with description. There can be added more options to
   integrate it in application. It's located in resources folder
2) terminal should point the folder location, where jar is located (ex. file located in **/home/username/filter**
   folder, you should open terminal there to start **jar -java** command, it's what I faced on linux while testing)
3) Input files should be in path, there the jar file is located