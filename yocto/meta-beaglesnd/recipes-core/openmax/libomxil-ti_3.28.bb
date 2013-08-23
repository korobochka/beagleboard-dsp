SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "libbridge"
DESCRIPTION = "Texas Instruments OpenMAX IL."
HOMEPAGE = "http://omappedia.org/wiki/OpenMAX_Project_Main"
LICENSE = "CLOSED"
PR = "r1"

#PACKAGES += "${PN}-extras ${PN}-extras-dbg ${PN}-tests ${PN}-tests-dbg "

LEAD_SONAME = "libOMX_Core.so"

TAG="v${PV}-3"
SRC_URI = "git://gitorious.org/vjaquez-beagleboard/ti-omxil.git;protocol=git;tag=${TAG} \
	file://fixbuild.patch \
	file://libomxil-ti.pc \
	file://omap24xxvout.h \
	"
S = "${WORKDIR}/git"

do_compile_prepend() {
	if [ ! -f ${TMPDIR}/sysroots/${MACHINE}/${includedir}/linux/videodev.h ]; then
		ln -s ${TMPDIR}/sysroots/${MACHINE}/${includedir}/linux/videodev2.h ${TMPDIR}/sysroots/${MACHINE}/${includedir}/linux/videodev.h
	fi	
	install -m 0644 ${WORKDIR}/omap24xxvout.h ${S}/src/components/postproc/omap24xxvout.h
	install -m 0644 ${WORKDIR}/libomxil-ti.pc ${S}/libomxil.pc
}

do_compile() {
	oe_runmake KERNELDIR="${STAGING_KERNEL_DIR}"
}

do_install() {
	oe_libinstall -so -C src libOMX_Core ${D}${libdir}
	oe_libinstall -so -C src libLCML ${D}${libdir}
	oe_libinstall -so -C src libRAM ${D}${libdir}
	oe_libinstall -so -C src libOMX_Clock ${D}${libdir}
	oe_libinstall -so -C src libOMX_ResourceManagerProxy ${D}${libdir}

	install -d ${D}${includedir}/omx
	install -m 0644 ${S}/include/*.h ${D}${includedir}/omx

	oe_runmake DESTDIR=${D} KERNELDIR=${STAGING_KERNEL_DIR} install

	install -d ${D}${libdir}/pkgconfig
	install -m 0644 ${S}/libomxil.pc ${D}${libdir}/pkgconfig/libomxil.pc
}

