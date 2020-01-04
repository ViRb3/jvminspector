
# JVM Inspector
> Attach to a running JVM and browse loaded classes

![ui](ui.png?)

## Features
This fork of [frontfact's jvminspector](https://github.com/frontfact/jvminspector) makes the project easier to use by providing some key new features:
- Maven source control
- Dynamic JDK dependency resolution
- Continuous Integration
- Pre-built releases

## Notes
- The JDK dependencies have been removed in Java 9+, therefore this tool will not work on those versions
- The JVM version that runs this tool must _exactly_ match the target, otherwise problems will occur
- The dynamic JDK dependency resolution is only tested on Windows, it will likely not work on Mac. However, it is easily fixable by updating the library paths in [Main.java](./src/main/java/jvminspector/Main.java)

## Building
Make sure you have [Maven](https://maven.apache.org/) installed and added to your `PATH`. Then, simply execute:
```
mvn package
```

## Dependencies
- JDK libraries (`sa-jdi.jar`, `tools.jar`) to dump bytecode
- [Fernflower](https://github.com/fesh0r/fernflower) to decompile bytecode into java code
- [RSyntaxTextArea](https://github.com/bobbylight/RSyntaxTextArea) to display java source code
