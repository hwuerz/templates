## Template: java11-steep

The Java11 Steep template uses gradle as a build system.

Gradle version: 6.3

### Structure

This template is based on the official java11 template.
The main *.java files are unchanged. 
If there are any updates, they can be applied to this template too.

Changes in comparison to the official java11 template:
- `entrypoint` Project
  - Added the class `com.openfaas.function.Handler`
    This is required because we need to wrap the function Handler (defined in the `function` package).
    Our function should be called with a steep object. 
    The wrapper defined in the new class creates such an object.
  - Added the test `com.openfaas.function.Handler` which tests the handler wrapper.
  - `build.gradle` contains dependencies to `jackson-databind` and `jackson-module-kotlin`
- `function` Project
  - Deleted the package `com.openfaas.function` It is now implemented in the `entrypoint` package.
  - Added the package `io.steep.faas` This implementation receives the steep object created by the wrapper in `entrypoint`.
- `model` Project
  - Added the interface `io.steep.faas.IHandler`
- `build.gradle` and `settings.gradle` include Steep. 
  Please note, that it is included via Git Submodule. 
  The specified versions in gradle and the submodule have to match.