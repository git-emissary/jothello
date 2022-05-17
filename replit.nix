{ pkgs }: {
    deps = [
        pkgs.graalvm17-ce
        pkgs.maven
        pkgs.replitPackages.jdt-language-server
    ];

   environment.sessionVariables = {
    CLASSPATH="/home/runner/.m2/repository/junit/junit/4.8.2/junit-4.8.2.jar:/home/runner/.m2/repository/org/hamcrest/hamcrest/2.2";
  };
}