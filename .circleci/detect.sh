#!/bin/bash

echo "Detect Shell Script ${SCRIPT_VERSION}"

get_path_separator() {
  # Performs a check to see if the system is Windows based.
  if [[ `uname` == *"NT"* ]] || [[ `uname` == *"UWIN"* ]]; then
    echo "\\"
  else
    echo "/"
  fi
}

# DETECT_LATEST_RELEASE_VERSION should be set in your
# environment if you wish to use a version different
# from LATEST.
DETECT_RELEASE_VERSION=${DETECT_LATEST_RELEASE_VERSION}

# To override the default version key, specify a
# different DETECT_VERSION_KEY in your environment and
# *that* key will be used to get the download url from
# artifactory. These DETECT_VERSION_KEY values are
# properties in Artifactory that resolve to download
# urls for the detect jar file. As of 2020-05-27, the
# available DETECT_VERSION_KEY values are:
#
# Every new major version of detect will have its own
# DETECT_LATEST_X key.
DETECT_VERSION_KEY=${DETECT_VERSION_KEY:-DETECT_LATEST}

# You can specify your own download url from
# artifactory which can bypass using the property keys
# (this is mainly for QA purposes only)
DETECT_SOURCE=${DETECT_SOURCE:-}

# To override the default location of /tmp, specify
# your own DETECT_JAR_DOWNLOAD_DIR in your environment and
# *that* location will be used.
# *NOTE* We currently do not support spaces in the
# DETECT_JAR_DOWNLOAD_DIR.
DEFAULT_DETECT_JAR_DOWNLOAD_DIR="${HOME}$(get_path_separator)synopsys-detect$(get_path_separator)download"
if [[ -z "${DETECT_JAR_DOWNLOAD_DIR}" ]]; then
	# If new name not set: Try old name for backward compatibility
    DETECT_JAR_DOWNLOAD_DIR=${DETECT_JAR_PATH:-${DEFAULT_DETECT_JAR_DOWNLOAD_DIR}}
fi
DETECT_JAR_DOWNLOAD_DIR=${DETECT_JAR_DOWNLOAD_DIR:-${DEFAULT_DETECT_JAR_DOWNLOAD_DIR}}

# To control which java detect will use to run, specify
# the path in in DETECT_JAVA_PATH or JAVA_HOME in your
# environment, or ensure that java is first on the path.
# DETECT_JAVA_PATH will take precedence over JAVA_HOME.
# JAVA_HOME will take precedence over the path.
# Note: DETECT_JAVA_PATH should point directly to the
# java executable. For JAVA_HOME the java executable is
# expected to be in JAVA_HOME/bin/java
DETECT_JAVA_PATH=${DETECT_JAVA_PATH:-}

# If you want to pass any java options to the
# invocation, specify DETECT_JAVA_OPTS in your
# environment. For example, to specify a 6 gigabyte
# heap size, you would set DETECT_JAVA_OPTS=-Xmx6G.
DETECT_JAVA_OPTS=${DETECT_JAVA_OPTS:-}

# If you want to pass any additional options to
# curl, specify DETECT_CURL_OPTS in your environment.
# For example, to specify a proxy, you would set
# DETECT_CURL_OPTS=--proxy http://myproxy:3128
DETECT_CURL_OPTS=${DETECT_CURL_OPTS:-}

# If you only want to download the appropriate jar file set
# this to 1 in your environment. This can be useful if you
# want to invoke the jar yourself but do not want to also
# get and update the jar file when a new version releases.
DETECT_DOWNLOAD_ONLY=${DETECT_DOWNLOAD_ONLY:-0}

SCRIPT_ARGS="$@"
LOGGABLE_SCRIPT_ARGS=""

# This provides a way to get the script version (via, say, grep/sed). Do not change.
SCRIPT_VERSION=2.4.0

echo "Detect Shell Script ${SCRIPT_VERSION}"

DETECT_BINARY_REPO_URL=https://sig-repo.synopsys.com

for i in $*; do
  if [[ $i == --blackduck.hub.password=* ]]; then
    LOGGABLE_SCRIPT_ARGS="$LOGGABLE_SCRIPT_ARGS --blackduck.hub.password=<redacted>"
  elif [[ $i == --blackduck.hub.proxy.password=* ]]; then
    LOGGABLE_SCRIPT_ARGS="$LOGGABLE_SCRIPT_ARGS --blackduck.hub.proxy.password=<redacted>"
  elif [[ $i == --blackduck.hub.api.token=* ]]; then
    LOGGABLE_SCRIPT_ARGS="$LOGGABLE_SCRIPT_ARGS --blackduck.hub.api.token=<redacted>"
  elif [[ $i == --blackduck.password=* ]]; then
    LOGGABLE_SCRIPT_ARGS="$LOGGABLE_SCRIPT_ARGS --blackduck.password=<redacted>"
  elif [[ $i == --blackduck.proxy.password=* ]]; then
    LOGGABLE_SCRIPT_ARGS="$LOGGABLE_SCRIPT_ARGS --blackduck.proxy.password=<redacted>"
  elif [[ $i == --blackduck.api.token=* ]]; then
    LOGGABLE_SCRIPT_ARGS="$LOGGABLE_SCRIPT_ARGS --blackduck.api.token=<redacted>"
  elif [[ $i == --polaris.access.token=* ]]; then
    LOGGABLE_SCRIPT_ARGS="$LOGGABLE_SCRIPT_ARGS --polaris.access.token=<redacted>"
  else
    LOGGABLE_SCRIPT_ARGS="$LOGGABLE_SCRIPT_ARGS $i"
  fi
done

run() {
  get_detect
  if [[ ${DETECT_DOWNLOAD_ONLY} -eq 0 ]]; then
    run_detect
  fi
}

get_detect() {
  PATH_SEPARATOR=$(get_path_separator)
  USE_LOCAL=0
  LOCAL_FILE="${DETECT_JAR_DOWNLOAD_DIR}${PATH_SEPARATOR}synopsys-detect-last-downloaded-jar.txt"
  if [[ -z "${DETECT_SOURCE}" ]]; then
    if [[ -z "${DETECT_RELEASE_VERSION}" ]]; then
      VERSION_CURL_CMD="curl ${DETECT_CURL_OPTS} --silent --header \"X-Result-Detail: info\" '${DETECT_BINARY_REPO_URL}/api/storage/bds-integrations-release/com/synopsys/integration/synopsys-detect?properties=${DETECT_VERSION_KEY}'"
      VERSION_EXTRACT_CMD="${VERSION_CURL_CMD} | grep \"${DETECT_VERSION_KEY}\" | sed 's/[^[]*[^\"]*\"\([^\"]*\).*/\1/'"
      DETECT_SOURCE=$(eval ${VERSION_EXTRACT_CMD})
      if [[ -z "${DETECT_SOURCE}" ]]; then
        echo "Unable to derive the location of ${DETECT_VERSION_KEY} from response to: ${VERSION_CURL_CMD}"
        USE_LOCAL=1
      fi
    else
      DETECT_SOURCE="${DETECT_BINARY_REPO_URL}/bds-integrations-release/com/synopsys/integration/synopsys-detect/${DETECT_RELEASE_VERSION}/synopsys-detect-${DETECT_RELEASE_VERSION}.jar"
    fi
  fi

  if [[ USE_LOCAL -eq 0 ]]; then
    echo "Will look for : ${DETECT_SOURCE}"
  else
    echo "Will look for : ${LOCAL_FILE}"
  fi

  if [[ USE_LOCAL -eq 1 ]] && [[ -f "${LOCAL_FILE}" ]]; then
    echo "Found local file ${LOCAL_FILE}"
    DETECT_FILENAME=`cat ${LOCAL_FILE}`
  elif [[ USE_LOCAL -eq 1 ]]; then
    echo "${LOCAL_FILE} is missing and unable to communicate with a Detect source."
    exit -1
  else
    DETECT_FILENAME=${DETECT_FILENAME:-$(awk -F "/" '{print $NF}' <<< $DETECT_SOURCE)}
  fi
  DETECT_DESTINATION="${DETECT_JAR_DOWNLOAD_DIR}${PATH_SEPARATOR}${DETECT_FILENAME}"

  USE_REMOTE=1
  if [[ USE_LOCAL -ne 1 ]] && [[ ! -f "${DETECT_DESTINATION}" ]]; then
    echo "You don't have the current file, so it will be downloaded."
  else
    echo "You have already downloaded the latest file, so the local file will be used."
    USE_REMOTE=0
  fi

  if [ ${USE_REMOTE} -eq 1 ]; then
    echo "getting ${DETECT_SOURCE} from remote"
    TEMP_DETECT_DESTINATION="${DETECT_DESTINATION}-temp"
    curlReturn=$(curl ${DETECT_CURL_OPTS} --silent -w "%{http_code}" -L -o "${TEMP_DETECT_DESTINATION}" --create-dirs "${DETECT_SOURCE}")
    if [[ 200 -eq ${curlReturn} ]]; then
      mv "${TEMP_DETECT_DESTINATION}" "${DETECT_DESTINATION}"
      if [[ -f ${LOCAL_FILE} ]]; then
        rm "${LOCAL_FILE}"
      fi
      echo "${DETECT_FILENAME}" >> "${LOCAL_FILE}"
      echo "saved ${DETECT_SOURCE} to ${DETECT_DESTINATION}"
    else
      echo "The curl response was ${curlReturn}, which is not successful - please check your configuration and environment."
      exit -1
    fi
  fi
}

set_detect_java_path() {
  PATH_SEPARATOR=$(get_path_separator)

  if [[ -n "${DETECT_JAVA_PATH}" ]]; then
    echo "Java Source: DETECT_JAVA_PATH=${DETECT_JAVA_PATH}"
  elif [[ -n "${JAVA_HOME}" ]]; then
    DETECT_JAVA_PATH="${JAVA_HOME}${PATH_SEPARATOR}bin${PATH_SEPARATOR}java"
    echo "Java Source: JAVA_HOME${PATH_SEPARATOR}bin${PATH_SEPARATOR}java=${DETECT_JAVA_PATH}"
  else
    echo "Java Source: PATH"
    DETECT_JAVA_PATH="java"
  fi
}

run_detect() {
  set_detect_java_path

  JAVACMD="\"${DETECT_JAVA_PATH}\" ${DETECT_JAVA_OPTS} -jar \"${DETECT_DESTINATION}\""
  echo "running Detect: ${JAVACMD} ${LOGGABLE_SCRIPT_ARGS}"

  eval "${JAVACMD} ${SCRIPT_ARGS}"
  RESULT=$?
  echo "Result code of ${RESULT}, exiting"
  exit ${RESULT}
}

run