#! /bin/bash

source conffile

PATH="$PATH":"$TOOLCHAIN_DIR"
export PATH

CROSS_COMPILE="$TOOLCHAIN_PREFFIX"
export CROSS_COMPILE

ARCH="$TOOLCHAIN_ARCH"
export ARCH
