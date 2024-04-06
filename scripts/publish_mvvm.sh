
# sync root gradle project for copying a files that required for build into subprojects
cd ../
./gradlew syncReleaseLibJars

# change dir to "mvvm", grant permission for "gradlew" executing and publish library to maven
cd mvvm
chmod +x gradlew
./gradlew publishAllPublicationsToSonatypeRepository closeAndReleaseSonatypeStagingRepository incrementMvvmLibVersion