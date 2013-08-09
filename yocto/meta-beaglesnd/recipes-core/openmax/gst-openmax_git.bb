DESCRIPTION = "GstOpenMAX is a GStreamer plug-in that allows communication with OpenMAX IL components"
#LICENSE = "LGPLv2"
LICENSE = "CLOSED"
DEPENDS = "gstreamer"
PR = "r1"

TAG = "v0.10.0.5"
#SRCREV = "69f66df"
SRCREV = "69f66df484c6430274da7f8ef646edcaca28d9e7"

SRC_URI = "git://gitorious.org/vjaquez-beagleboard/gst-openmax.git;protocol=git;branch=omap"

S = "${WORKDIR}/git"
EXTRA_OECONF_append = "--enable-experimental "

inherit autotools

do_unpack2() {
	echo "${TAG}-${SRCREV}" > ${S}/.version
}

do_install() {
	autotools_stage_all
}

FILES_${PN} += "${libdir}/gstreamer-0.10/*.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.*a"

addtask unpack2 after do_unpack before do_patch
