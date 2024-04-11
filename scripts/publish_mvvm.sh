#!/bin/bash

# sync root gradle project for copying a files that required for build into subprojects
cd ../
./gradlew :mvvm:core:publishAllPublicationsToSonatypeRepository \
      :mvvm:koin:publishAllPublicationsToSonatypeRepository \
      incrementMvvmLibVersion