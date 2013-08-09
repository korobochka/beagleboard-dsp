FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://bb.cfg"

PRINC := "${@int(PRINC) + 1}"
