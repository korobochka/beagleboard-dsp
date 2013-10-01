SECTION = "console/utils"
PRIORITY = "optional"
DESCRIPTION = "DSP samples"
#LICENSE = "LGPL"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = "beagleboard"
PR = "r2"

#SRCREV = "8a9242a"
SRCREV = "8a9242a83b5d8af8ee25ed3e7aabd976384dfc2c"

SRC_URI = "git://gitorious.org/vjaquez-beagleboard/dsp-samples.git;protocol=git;branch=master \
			file://ping.patch"

S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

do_compile () {
	   oe_runmake NEW=1 DSP_TOOLS=/opt/ti/ccsv5/tools/compiler/c6000_7.4.4/ DSP_DOFFBUILD=/home/korobochka/temp/doffbuild/doffbuild/
}

do_install() {
	oe_runmake DESTDIR=${D} install
}

FILES_${PN} += "/lib/dsp/ /dspbridge/"
FILES_${PN}-dbg += "/dspbridge/.debug"
