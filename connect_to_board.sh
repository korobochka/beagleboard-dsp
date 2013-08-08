#! /bin/bash

source conffile

if [[ $EUID -ne 0 ]];
then
	echo "Only root can use this script, try this:"
	echo "	sudo $0"
	exit 1;
fi

killall screen
screen "$BOARD_DEV" 115200
