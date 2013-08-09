SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "libbridge"
DESCRIPTION = "Texas Instruments OpenMAX IL."
# FIXME: Three licenses are used, not just LGPL
LICENSE = "LGPL"
PR = "r0"

PACKAGES += "${PN}-bin "
FILES_${PN} += "${bindir}/OMXAudioManager ${bindir}/OMXResourceManager ${libdir}/lib*.so "
FILES_${PN}-dev += "${includedir} ${libdir}/pkgconfig/*.pc "
FILES_${PN}-bin += "${bindir}/PostProcTest \
		${bindir}/AvPlayTest \
		${bindir}/VidDecTest_common \
		${bindir}AacDecoder_Test "

# We need to override this and make sure it's only -j1
PARALLEL_MAKE = "-j1"

SRC_URI = "https://www.omapzoom.org/gf/download/frsrelease/170/1399/tiopenmax-${PV}.tar.gz \
	file://Makefile.patch;apply=yes \
	file://videodectest.patch;apply=yes \
	file://postproc.patch;apply=yes \
	file://avplay.patch;apply=yes \
	file://ldflags.patch;apply=yes \
	file://libomxil-ti.pc \
	file://omap24xxvout.h \
	"

S = "${WORKDIR}/tiopenmax-${PV}"

do_compile_prepend() {
	install -m 0644 ${WORKDIR}/omap24xxvout.h ${S}/video/src/openmax_il/post_processor/inc/omap24xxvout.h
	install -m 0644 ${WORKDIR}/libomxil-ti.pc ${S}/libomxil.pc
}

do_compile() {
	oe_runmake \
		   PKGDIR=${S}/target \
		   CROSS=${HOST_PREFIX} \
		   BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		   BRIDGELIBDIR=${STAGING_LIBDIR} \
		   KERNELDIR=${STAGING_KERNEL_DIR} \
		   LDFLAGS="${LDFLAGS}" \
		   avplay.clobber avplay
}

do_install() {
	TARGET=${S}/target/target
	oe_libinstall -so -C ${TARGET}/lib libOMX_Core ${D}${libdir}
	oe_libinstall -so -C ${TARGET}/lib libLCML ${D}${libdir}
	oe_libinstall -so -C ${TARGET}/lib libRAM ${D}${libdir}
	oe_libinstall -so -C ${TARGET}/lib libOMX_Clock ${D}${libdir}
	oe_libinstall -so -C ${TARGET}/lib libOMX_ResourceManagerProxy ${D}${libdir}
	oe_libinstall -so -C ${TARGET}/lib libOMX.TI.Video.Decoder ${D}${libdir}
	oe_libinstall -so -C ${TARGET}/lib libOMX.TI.PostProc ${D}${libdir}
	oe_libinstall -so -C ${TARGET}/lib libOMX.TI.AAC.decode ${D}${libdir}

	install -d ${D}/${bindir}
	install -m 0755 ${TARGET}/bin/OMXResourceManager ${D}${bindir}
	install -m 0755 ${TARGET}/bin/OMXAudioManager ${D}${bindir}
	install -m 0755 ${TARGET}/omx/AacDecoder_Test ${D}${bindir}
	install -m 0755 ${TARGET}/omx/AvPlayTest ${D}${bindir}
	install -m 0755 ${TARGET}/omx/PostProcTest ${D}${bindir}
	install -m 0755 ${TARGET}/omx/VidDecTest_common ${D}${bindir}

	install -d ${D}/${includedir}/omx
	install -m 0644 ${TARGET}/include/omx/*.h ${D}/${includedir}/omx
	install -m 0644 ${TARGET}/include/system/*.h ${D}/${includedir}/omx

	install -d ${D}${libdir}/pkgconfig
	install -m 0644 ${S}/libomxil.pc ${D}${libdir}/pkgconfig/libomxil.pc
}

