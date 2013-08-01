#! /bin/sh

CARD="/dev/sdc"
MOUNT1="/media/boot"
MOUNT2="/media/rootfs"

umount $MOUNT1
umount $MOUNT2
cd /home/korobochka/beagleboard
./mkcard.sh $CARD
mount -t vfat "$CARD"1 $MOUNT1
