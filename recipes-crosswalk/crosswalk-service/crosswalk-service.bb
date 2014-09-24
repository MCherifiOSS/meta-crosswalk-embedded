DESCRIPTION = "Crosswalk is a web runtime for ambitious HTML5 applications."
HOMEPAGE = "https://crosswalk-project.org/"
LICENSE = "BSD-3-Clause"

RDEPENDS_${PN} = "crosswalk hexgl"

SRC_URI += "file://crosswalk-service \
    file://LICENSE"

LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=ad296492125bc71530d06234d9bfebe0"

inherit update-rc.d

INITSCRIPT_NAME = "crosswalk-service"
INITSCRIPT_PARAMS = "start 06 5 2 3 . stop 22 0 1 6 ."

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/crosswalk-service ${D}${sysconfdir}/init.d/crosswalk-service
}
