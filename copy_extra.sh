#! /bin/bash

source conffile

if [[ $EUID -ne 0 ]];
then
	echo "Only root can use this script, try this:"
	echo "	sudo $0"
	exit 1;
fi

cp -r ./extra/root/  "$NFS_DIR"/home/
cp ./extra/faad/libgstfaad.so "$NFS_DIR"/usr/lib/gstreamer-0.10/
cp ./extra/faad/libfaad.so.2.0.0 "$NFS_DIR"/usr/lib/
cp ./extra/faad/libfaad.so.2 "$NFS_DIR"/usr/lib/

echo "DONE";
