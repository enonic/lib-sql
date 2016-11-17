node {
  env.JAVA_HOME="${tool 'jdk8'}"

  stage 'checkout'
  checkout scm

  stage 'build'
  sh './gradlew clean buld'
}
