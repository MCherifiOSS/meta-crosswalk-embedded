DESCRIPTION = "Crosswalk is a web runtime for ambitious HTML5 applications."
HOMEPAGE = "https://crosswalk-project.org/"
LICENSE = "BSD-3-Clause"

# Customizable parts (set this variables at your local.conf):
#
# CROSSWALK_SERVICE_PARAMS: can be used for defining an URI to be loaded
# by crosswalk-service startup. The parameters are not restricted to URIs, for
# instance "--disable-web-security http://example.com" could be used for
# disabling CORS (although no recommended).
#
# CROSSWALK_SERVICE_DEBUG: if set to 1, crosswalk-service won't autostart.
#

RDEPENDS_${PN} = "crosswalk"

SRC_URI += "\
    file://LICENSE \
    file://crosswalk.service \
    file://xwalk-service \
    file://init \
    "

LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=ad296492125bc71530d06234d9bfebe0"

inherit update-rc.d systemd

INITSCRIPT_NAME = "crosswalk-service"
INITSCRIPT_PARAMS = "start 06 5 2 3 . stop 22 0 1 6 ."

SYSTEMD_SERVICE_${PN} = "crosswalk.service"

CROSSWALK_SERVICE_PARAMS ?= "https://crosswalk-project.org/"
CROSSWALK_SERVICE_DEBUG ?= "0"

do_install() {
    if [ "${CROSSWALK_SERVICE_DEBUG}" -eq "0" ]; then
        if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
            install -d ${D}${sysconfdir}/init.d
            install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/crosswalk-service
        else
            install -d ${D}${systemd_unitdir}/system
            install -m 0644 ${WORKDIR}/crosswalk.service ${D}${systemd_unitdir}/system
        fi
    fi

    install -d ${D}${bindir}/
    install -m 0755 ${WORKDIR}/xwalk-service ${D}${bindir}/xwalk-service

    echo exec xwalk ${CROSSWALK_SERVICE_PARAMS} >> ${D}${bindir}/xwalk-service
}
