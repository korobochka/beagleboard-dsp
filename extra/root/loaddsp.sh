#! /bin/sh

depmod -a

modprobe mailbox
modprobe mailbox_mach
modprobe tidspbridge base_img=/lib/dsp/baseimage.dof

