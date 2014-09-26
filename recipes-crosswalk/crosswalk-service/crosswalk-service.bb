DESCRIPTION = "Crosswalk is a web runtime for ambitious HTML5 applications."
HOMEPAGE = "https://crosswalk-project.org/"
LICENSE = "BSD-3-Clause"

RDEPENDS_${PN} = "crosswalk hexgl"

SRC_URI += "\
    file://LICENSE \
    file://crosswalk.service \
    file://init \
    "

LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=ad296492125bc71530d06234d9bfebe0"

inherit update-rc.d systemd

INITSCRIPT_NAME = "crosswalk-service"
INITSCRIPT_PARAMS = "start 06 5 2 3 . stop 22 0 1 6 ."

SYSTEMD_SERVICE_${PN} = "crosswalk.service"

do_install() {
    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/crosswalk-service
    else
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/crosswalk.service ${D}${systemd_unitdir}/system
    fi
}
