FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://iwlwifi.cfg \
    file://silent.cfg \
    "

module_autoload_iwlwifi = "iwlwifi"
