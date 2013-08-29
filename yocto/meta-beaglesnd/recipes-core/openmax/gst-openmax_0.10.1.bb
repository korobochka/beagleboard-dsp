DESCRIPTION = "GstOpenMAX is a GStreamer plug-in that allows communication with OpenMAX IL components"
LICENSE = "CLOSED"
DEPENDS = "gstreamer"

TAG = "v${PV}"
SRC_URI = "git://github.com/felipec/gst-openmax.git;protocol=git;tag=${TAG} \
	"

S = "${WORKDIR}/git"

export GST_CVS="no"
EXTRA_OECONF_append = "--enable-experimental --disable-valgrind "

inherit autotools

do_unpack2() {
	echo "${TAG}" > ${S}/.version
}

addtask unpack2 after do_unpack before do_patch

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomx.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-0.10/libgstomx.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
