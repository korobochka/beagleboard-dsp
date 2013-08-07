#! /bin/bash

if [ ! -f conffile ];
	then
		echo "No config file found";
		exit 1;
	fi

source conffile
	
echo "This is current configuration:"
echo "	Yocto in use: $YOCTO_RELEASE_DIR"
echo "		build subdirectory: $YOCTO_BUILD_DIR"

